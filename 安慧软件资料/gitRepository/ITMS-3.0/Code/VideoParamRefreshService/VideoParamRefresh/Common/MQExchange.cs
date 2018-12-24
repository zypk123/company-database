using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Libs.RabbitMQClient;
using Libs.RabbitMQClient.Element;
using VideoParamRefresh.Common;

namespace VideoParamRefresh.Common
{

    class MQExchange
    {
        static Dictionary<string, Action<string>> SubMethod;
        static Logger Logger;

        static MQExchange()
        { 
            SubMethod = new Dictionary<string, Action<string>>(1);
        }

        /// <summary>
        /// 订阅处理
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <param name="routeKey"></param>
        /// <param name="subMethod"></param>
        public static void Subscribe(string routeKey, Action<string> subMethod)
        {
            lock (SubMethod)
            {
                if (SubMethod.ContainsKey(routeKey))
                {
                    SubMethod.Remove(routeKey);
                }

                SubMethod.Add(routeKey, subMethod);

                if (Logger == null)
                {
                    Start();
                }
            }
        }

        /// <summary>
        /// 停止处理
        /// </summary>
        public static void Stop()
        {
            RabbitMQGateWay.CloseSubscriber();
            RabbitMQGateWay.ClosePublisher();
        }

        /// <summary>
        /// 发布处理
        /// </summary>
        /// <param name="routeKey"></param>
        /// <param name="obj"></param>
        public static bool Publish(string routeKey, object obj)
        {
            return RabbitMQGateWay.PublishMessage(obj, routeKey);
        }

        /// <summary>
        /// 订阅消息转发
        /// </summary>
        /// <param name="msg"></param>
        /// <param name="rKey"></param>
        /// <param name="qName"></param>
        /// <returns></returns>
        private static bool HandleMessage(string msg, string rKey)
        {
            try
            {
                if (!string.IsNullOrEmpty(msg))
                {
                    if (SubMethod.ContainsKey(rKey))
                    {
                        SubMethod[rKey](msg);
                    }
                }
            }
            catch (Exception)
            {
            }

            return true;
        }

        /// <summary>
        /// 开始处理
        /// </summary>
        public static void Start()
        {

            RabbitMQGateWay.InitiaizeLogger(Logger.Info, Logger.Error);

            MQAddress mqAddr = new MQAddress(
                Config.Instance.RabbitMqServer,
                Config.Instance.RabbitMqUser,
                Config.Instance.RabbitMqPwd);

            Exchange exChange = new Exchange("SURVEY_CENTER", true, false, ExchangeType.TOPIC);

            RabbitMQGateWay.InitializePublisher(mqAddr, exChange);

            //RabbitMQGateWay.InitializeSubscriber(
            //    new BindQueueHandler(mqAddr, exChange, new QueueHandler[] {
            //        new QueueHandler(new Queue[]{ new Queue("TrafficSection_Flow", false, true, ConstValue.RouteKey.ROUTE_KEY_FLOW) }, HandleMessage),
            //        new QueueHandler(new Queue[]{ new Queue("TrafficSection_RtMsgStartup", false, true, ConstValue.RouteKey.ROUTKEY_RTMSG_STARTUP) }, HandleMessage)}));
        }

    }
}
