using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace VideoParamRefresh.Common
{
    public class SurveyUpgrade_DeviceStatusInfo
    {
        public String deviceID { get; set; }

        public String deviceNo { get; set; }

        public String softVersion { get; set; }

        public int deviceManuf { get; set; }

        public String statusTime { get; set; }

        public String deviceIp { get; set; }

        public String roadCode { get; set; }

        public String roadInfo { get; set; }

        public int timeDiff { get; set; }

        public int deviceStatus { get; set; }

        public String faultCode { get; set; }

        public String deviceStartTime { get; set; }

        public String deviceCurrTime { get; set; }

        public String gpsLocationInfo { get; set; }

        public int deviceType { get; set; }

        public String departmentCode { get; set; }

        public String gatewayIp { get; set; }

        public String paramProxyName { get; set; }

        public String controlProxyName { get; set; }

        public int deviceParamPort { get; set; }

        public int deviceControlPort { get; set; }
    }
}
