using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Configuration;

namespace VideoParamRefresh.Common
{
    public class Config
    {
        #region 变量及构造函数
        private Config() { }
        private static Config instance;
        public static Config Instance
        {
            get
            {
                if (instance == null)
                {
                    instance = new Config();
                }
                return instance;
            }
        }
        #endregion

        #region 变量定义
        // 日志目录
        internal string LogFolder { get; private set; }
        internal string LogLevel { get; private set; } 
        internal string ConnStr { get; private set; }
        internal string Hik8600V323ServerId { get; private set; }
        internal string DefaultPwd { get; private set; }
        internal string RefreshInterval { get; private set; }
        internal string RabbitMqServer { get; private set; }
        internal string RabbitMqUser { get; private set; }
        internal string RabbitMqPwd { get; private set; } 
        #endregion

        public void Load()
        {
            try
            {
                //日志目录
                LogFolder = ConfigurationManager.AppSettings["LogFolder"].ToString();
                LogLevel = ConfigurationManager.AppSettings["LogLevel"].ToString(); 
                ConnStr = ConfigurationManager.AppSettings["ConnStr"].ToString();
                Hik8600V323ServerId = ConfigurationManager.AppSettings["Hik8600V323ServerId"].ToString();
                DefaultPwd = ConfigurationManager.AppSettings["DefaultPwd"].ToString();
                RefreshInterval = ConfigurationManager.AppSettings["RefreshInterval"].ToString();
                RabbitMqServer = ConfigurationManager.AppSettings["RabbitMqServer"].ToString();
                RabbitMqUser = ConfigurationManager.AppSettings["RabbitMqUser"].ToString();
                RabbitMqPwd = ConfigurationManager.AppSettings["RabbitMqPwd"].ToString(); 
            }
            catch (Exception ex)
            {
                Logger.Error("读取配置文件失败。原因:" + ex.Message);
            }
        }

    }
}
