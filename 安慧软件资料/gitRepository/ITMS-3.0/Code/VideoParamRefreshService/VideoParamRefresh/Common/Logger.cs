using System;
using log4net.Appender;
using log4net;
using System.IO;
using log4net.Config;
using log4net.Layout;

namespace VideoParamRefresh.Common
{
    public class Logger
    {
        private static bool _DebugEnabled = false;
        private static bool _ErrorEnabled = false;
        private static bool _FatalEnabled = false;
        private static bool _InfoEnabled = false;
        private static ILog _infoLogger = null;
        private static ILog _errorLogger = null;
        private static ILog _syncLogger = null;
        private static bool _WarnEnabled = false;
        private static string NowDateString = "";
        private static Object _LockObject = new object();
        private static string _LogFolder = @"C:\LDVideoRefreshService_LOG\";
        private static string _LogLevel = "debug";
        private static bool _LogOn = true;
        private Logger() { }

        public static void SetLogOn()
        {
            _LogOn = true;
        }

        public static void SetLogOff()
        {
            _LogOn = false;
        }

        internal static void Initialize()
        {
            _LogFolder = Config.Instance.LogFolder;
            _LogLevel = Config.Instance.LogLevel;
            NowDateString = string.Empty;
            _LogOn = true;
        }

        #region 初始化Logger对象

        /// <summary>
        /// 初始化Logger对象
        /// </summary>
        private static void Init()
        {
            string now = DateTime.Now.ToString("yyyyMMdd");
            //ValidateLogPathName();
            if (!NowDateString.Equals(now))
            {
                lock (_LockObject)
                {
                    if (!NowDateString.Equals(now))
                    {
                        NowDateString = now;
                        string sLevel = _LogLevel;
                        string infoLogFile = _LogFolder + NowDateString + "_info.log";
                        string errorLogFile = _LogFolder+NowDateString+"_error.log";
                        string debugLogFile = _LogFolder + NowDateString + "_debug.log"; 
                        try
                        {
                            _LogFolder = Config.Instance.LogFolder;
                        }
                        catch (SystemException ex)
                        {
                            Console.WriteLine("Configuration file error:" + ex.Message);
                        }

                        //_LogFolder = @"C:\LDVideoRefreshService_LOG\";

                        if (_LogFolder.EndsWith(@"\"))
                        {
                            infoLogFile = _LogFolder + NowDateString + ".log";
                            errorLogFile = _LogFolder + NowDateString + "_errordata.log"; 
                        }
                        else
                        {
                            infoLogFile = _LogFolder + @"\" + NowDateString + ".log";
                            errorLogFile = _LogFolder + @"\" + NowDateString + "_errordata.log"; 
                        }

                        string sPattern = "%d %-5p -%m%n";
                        string dataPattern = "%m%n";
                        string infoLogger = "ReceiverInfoLogger";
                        string errorLogger = "ReceiverErrorLogger";
                        string syncLogger = "ReceiverSyncLogger";
                        string infoFileAppender = "RecInfoFileAppender";
                        string errorFileAppender = "RecErrorFileAppender";
                        string syncFileAppender = "RecSyncFileAppender";
                        try
                        {
                            log4net.Layout.PatternLayout oLayout = new log4net.Layout.PatternLayout(sPattern);
                            log4net.Repository.Hierarchy.Hierarchy hier =
                                    log4net.LogManager.GetLoggerRepository() as log4net.Repository.Hierarchy.Hierarchy;

                            if (_infoLogger != null)
                            {
                                if (hier != null)
                                {
                                    IAppender iinfoAppender = hier.GetLogger(infoLogger, hier.LoggerFactory).GetAppender(infoFileAppender);
                                    if (iinfoAppender != null)
                                    {
                                        iinfoAppender.Close();
                                        hier.GetLogger(infoLogger, hier.LoggerFactory).RemoveAppender(infoFileAppender);
                                    }

                                    IAppender ierrorAppender = hier.GetLogger(errorLogger, hier.LoggerFactory).GetAppender(errorFileAppender);
                                    if (ierrorAppender != null)
                                    {
                                        ierrorAppender.Close();
                                        hier.GetLogger(errorLogger, hier.LoggerFactory).RemoveAppender(errorFileAppender);
                                    }

                                    IAppender isyncAppender = hier.GetLogger(syncLogger, hier.LoggerFactory).GetAppender(syncFileAppender);
                                    if (ierrorAppender != null)
                                    {
                                        isyncAppender.Close();
                                        hier.GetLogger(syncLogger, hier.LoggerFactory).RemoveAppender(syncFileAppender);
                                    }
                                }
                            }
                            else
                            {
                                if (hier != null)
                                {
                                    hier.LoggerFactory.MakeNewLoggerInstance(infoLogger);
                                    hier.LoggerFactory.MakeNewLoggerInstance(errorLogger);
                                    hier.LoggerFactory.MakeNewLoggerInstance(syncLogger);
                                }
                            }

                            FileAppender infoAppender = new log4net.Appender.FileAppender(new PatternLayout(sPattern), infoLogFile, true);
                            infoAppender.Name = infoFileAppender;

                            FileAppender errorAppender = new log4net.Appender.FileAppender(new PatternLayout(dataPattern), errorLogFile, true);
                            errorAppender.Name = errorFileAppender; 

                            if (hier != null)
                            {
                                hier.GetLogger(infoLogger, hier.LoggerFactory).AddAppender(infoAppender);
                                hier.GetLogger(errorLogger, hier.LoggerFactory).AddAppender(errorAppender); 
                            }
                        }
                        catch (Exception)
                        {
                            ConsoleAppender oAppender = new ConsoleAppender();
                            log4net.Config.BasicConfigurator.Configure(oAppender);
                        }
                        _infoLogger = LogManager.GetLogger(infoLogger);
                        _errorLogger = LogManager.GetLogger(errorLogger);
                        _syncLogger = LogManager.GetLogger(syncLogger);
                        SetLevel(sLevel);
                    }
                }
            }
        }

        private static void ValidateLogPathName()
        {
            string sFile = DateTime.Now.ToString("yyyyMMdd");
            BakLogFileIfMaxSize(_LogFolder + sFile, _LogFolder);
        }

        private static void BakLogFileIfMaxSize(String sFile, string destPath)
        {
            int iMax = 50;
            FileInfo fInfo = new FileInfo(sFile);
            if (fInfo.Exists)
            {
                long iFileSize = fInfo.Length / 1024 / 1024;
                if (iFileSize > iMax)
                {
                    fInfo.MoveTo(destPath + DateTime.Now.ToString("yyyyMMddHHmm"));
                    fInfo.Create();
                }
            }
        }
        #endregion

        #region 设置Log的层次

        /// <summary>
        /// 设置Log的层次
        /// </summary>
        /// <param name="sLevel">层次</param>
        private static void SetLevel(string sLevel)
        {
            sLevel = sLevel.ToUpper();
            if (sLevel.Equals("DEBUG"))
            {
                _FatalEnabled = true;
                _ErrorEnabled = true;
                _WarnEnabled = true;
                _InfoEnabled = true;
                _DebugEnabled = true;
            }
            else if (sLevel.Equals("INFO"))
            {
                _FatalEnabled = true;
                _ErrorEnabled = true;
                _WarnEnabled = true;
                _InfoEnabled = true;
                _DebugEnabled = false;
            }
            else if (sLevel.Equals("WARN"))
            {
                _FatalEnabled = true;
                _ErrorEnabled = true;
                _WarnEnabled = true;
                _InfoEnabled = false;
                _DebugEnabled = false;
            }
            else if (sLevel.Equals("ERROR"))
            {
                _FatalEnabled = true;
                _ErrorEnabled = true;
                _WarnEnabled = false;
                _InfoEnabled = false;
                _DebugEnabled = false;
            }
            else if (sLevel.Equals("FATAL"))
            {
                _FatalEnabled = true;
                _ErrorEnabled = false;
                _WarnEnabled = false;
                _InfoEnabled = false;
                _DebugEnabled = false;
            }
        }
        #endregion

        #region 输出日志

        /// <summary>
        /// 输出调试日志
        /// </summary>
        /// <param name="message">日志内容</param>
        internal static void Debug(object message)
        {
            if (!_LogOn)
            {
                return;
            }
            Init();
            if (_DebugEnabled)
            {
                _infoLogger.Debug(message);
            }
        }

        /// <summary>
        /// 输出信息日志
        /// </summary>
        /// <param name="message">日志内容</param>
        internal static void Info(object message)
        {
            if (!_LogOn)
            {
                return;
            }
            Init();
            if (_InfoEnabled)
            {
                _infoLogger.Info(message);
            }
        }

        /// <summary>
        /// 输出警告日志
        /// </summary>
        /// <param name="message">日志内容</param>
        internal static void Warn(object message)
        {
            if (!_LogOn)
            {
                return;
            }
            Init();
            if (_WarnEnabled)
            {
                _infoLogger.Warn(message);
            }
        }

        /// <summary>
        /// 输出错误日志
        /// </summary>
        /// <param name="message">日志内容</param>
        internal static void Error(object message)
        {
            if (!_LogOn)
            {
                return;
            }
            Init();
            if (_ErrorEnabled)
            {
                _infoLogger.Error(message);
            }
        }

        /// <summary>
        /// 输出错误日志
        /// </summary>
        /// <param name="message">日志内容</param>
        internal static void ErrorData(object datamessage)
        {
            if (!_LogOn)
            {
                return;
            }
            Init();
            if (_ErrorEnabled)
            {
                _errorLogger.Error(datamessage);
            }
        }

        /// <summary>
        /// 输出错误日志
        /// </summary>
        /// <param name="message">日志内容</param>
        internal static void Error(Exception e)
        {
            Error(e.Message + Environment.NewLine + e.StackTrace);
        }

        /// <summary>
        /// 输出重要错误日志
        /// </summary>
        /// <param name="message">日志内容</param>
        internal static void Fatal(object message)
        {
            if (!_LogOn)
            {
                return;
            }
            Init();
            if (_FatalEnabled)
            {
                _infoLogger.Fatal(message);
            }
        }

        #endregion
        /// <summary>
        /// 输出数据同步日志
        /// </summary>
        /// <param name="message">日志内容</param>
        internal static void Sync(object datamessage)
        {
            if (!_LogOn)
            {
                return;
            }
            Init();
            if (_ErrorEnabled)
            {
                _syncLogger.Error(DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss.fff") + datamessage);
            }
        }
    }
}
