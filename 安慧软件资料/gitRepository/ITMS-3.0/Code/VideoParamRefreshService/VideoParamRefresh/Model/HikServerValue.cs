using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace VideoParamRefresh.Model
{
    public class HikServerValue
    {
        public string serverId;
        public string svcType; //6:视频用户接入服务器 7:视频存储管理服务器
        public string hikSvcIp;
        public string hikSvcUserName;
        public string hikSvcPassword;
        public string hikSvcPort;
        public string orgCode;
        public string context; //虚拟地址
        public string suffix;
        public string serverAppId;
        public string webSvcAddr
        {
            get
            { 
                return string.Format("http://{0}:{1}{2}?{3}", hikSvcIp, hikSvcPort, context, suffix);
            }
        }
        public List<String> serverPlatIds = new List<string>();
    }
}
