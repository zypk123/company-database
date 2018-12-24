using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using VideoParamRefresh.Common;
using VideoParamRefresh.BLL;
using VideoParamRefresh.DAL;
using System.Threading;
using Libs.RabbitMQClient; 

namespace VideoParamRefresh
{
    public class VideoParamRefreshService
    {
        private static VideoParamRefreshService _instance = null;
         #region 构造函数
        /// <summary>
        /// 私有构造函数
        /// </summary>
        private VideoParamRefreshService()
        {
        }
        #endregion

        #region  产生本类实例
        /// <summary>
        /// 产生本类实例
        /// </summary>
        /// <returns></returns>
        public static VideoParamRefreshService GetInsntace()
        {
            if (_instance == null)
            {
                lock (typeof(VideoParamRefreshService))
                {
                    if (_instance == null)
                    {
                        _instance = new VideoParamRefreshService();
                    }
                }
            }
            return _instance;
        }
        #endregion


        #region 启动信息接收服务
        /// <summary>
        /// 启动信息接收服务
        /// </summary>
        public void Start()
        {  
            try
            {
                Boolean isa= Convert.ToBoolean(0);
                Initialize();
                //puzzle,在HikVideoServiceAccess 120行处，设置断点，过一会系统所有的Timer都不再执行了
                System.Threading.Timer s1 = new System.Threading.Timer(ThreadRefresh, null, 0, Timeout.Infinite);// 实例化Timer类，设置间隔时间为时间间隔×1000毫秒；
                
            }
            catch (Exception e)
            {
                Logger.Error(e);
                throw e;
            }
        }  

        public void ThreadRefresh(object state)
        {

            Logger.Info("开始进程,版本号:20160616");

            try
            {
                while (true)
                {
                    RefreshCamersStatus(null);
                    RefreshCamersParam(null);
                    Thread.Sleep(Int16.Parse(Config.Instance.RefreshInterval) * 1000);
                }
            }
            catch (Exception ex)
            {
                Logger.Error("视频参数刷新失败:" + ex.StackTrace);
            }
        } 

        #endregion

        /// <summary>
        /// 初始化
        /// </summary>
        private void Initialize()
        {
            // 加载配置信息
            Config.Instance.Load();

            // 加载日志所需配置信息
            Logger.Initialize(); 

            //加载mq信息
            // 启动MQ交互处理
            MQExchange.Start();
        }

        private static readonly object lockParamObj = new object();

        private void RefreshCamersParam(object obj)
        {
            try
            {
                lock (lockParamObj)
                {
                    DateTime startTime = DateTime.Now;
                    Logger.Info("定时器获取视频参数开始，开始时间：" + DateTime.Now.ToString());

                    VideoBll.GetAllCameraParam(string.Empty);

                    Logger.Info("定时器获取视频参数完成，结束时间：" + DateTime.Now.ToString() + ",共耗时：" + (DateTime.Now - startTime).TotalSeconds + "秒");
                }
            }
            catch (Exception ex)
            {
                Logger.Error("RefreshCamersParam(exception):" + ex.Message);
            }
        }

        private void RefreshCamersStatus(object obj)
        {
            try
            {
                lock (lockParamObj)
                {
                    DateTime startTime = DateTime.Now;
                    Logger.Info("定时器获取视频状态开始，开始时间：" + DateTime.Now.ToString());

                    List<DeviceStatusResultDao> deviceLst = VideoBll.GetAllCameraStatus(string.Empty);
                    Logger.Info("设备状态发送MQ队列开始，开始时间：" + DateTime.Now.ToString());
                    //Dictionary<string, bool> statusDic = new Dictionary<string, bool>();
                    //statusDic.Add("dddd", true);
                    if (deviceLst.Count > 0)
                    {
                        List<SurveyUpgrade_DeviceStatusInfo> statusLst = new List<SurveyUpgrade_DeviceStatusInfo>();

                        foreach (var deviceStatusResult in deviceLst)
                        {
                            foreach (var key in deviceStatusResult.relateDatabaseKeys)
                            {                                
                            SurveyUpgrade_DeviceStatusInfo info = new SurveyUpgrade_DeviceStatusInfo();
                            //converter
                            info.deviceID = key;
                            info.deviceStatus = deviceStatusResult.Status==0?1:0;//和海康是反过来的，3.0里0是在线，1离线
                            statusLst.Add(info);
                            }
                        }                        

                        if (MQExchange.Publish("ah_devicestatus_key", statusLst))
                        {
                            Logger.Info("发布设备状态结果成功:" + JsonUtil.SerializeObject(statusLst));
                        }
                        else
                        {
                            Logger.Info("发布设备状态结果失败:" + JsonUtil.SerializeObject(statusLst));
                        }
                    }

                    Logger.Info("定时器刷新设备状态完成，结束时间：" + DateTime.Now.ToString() + ",共耗时：" + (DateTime.Now - startTime).TotalSeconds + "秒");
                }
            }
            catch (Exception ex)
            {
                Logger.Error("RefreshCamersStatus(exception):" + ex.Message);
            }
        }
    }
}
