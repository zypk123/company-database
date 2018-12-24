using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace VideoParamRefresh.Model
{

    public class VideoEntry
    {
        /// <summary>
        /// 服务器ID
        /// </summary>
        public string ServerId { get; set; }
        /// <summary>
        /// 服务器IP
        /// </summary>
        public string ServerIp { get; set; }
        /// <summary>
        /// 球机/相机ID
        /// </summary>
        public string CameraId { get; set; }
        /// <summary>
        /// 球机名称
        /// </summary>
        public string CameraName { get; set; }
        /// <summary>
        /// 球机IP
        /// </summary>
        public string Ip { get; set; }
        /// <summary>
        /// 球机端口
        /// </summary>
        public string Port { get; set; }
        /// <summary>
        /// 登陆球机用户名
        /// </summary>
        public string ConnUser { get; set; }
        /// <summary>
        /// 密码
        /// </summary>
        public string ConnPsd { get; set; }

        public string TypeCode { get; set; }
        /// <summary>
        /// 通道
        /// </summary>
        public string Channel { get; set; }

        public string RegionId { get; set; }

        public string ControlUnitId { get; set; }

        public int IsOnLone { get; set; }

        public string DeviceId { get; set; }

        public string RecType { get; set; }

        public string ZoonId { get; set; }

        public string GBCameraId { get; set; } //国标设备相机ID

        public string CameraIndexcode { get; set; }//国标录像计划id，传监控点编号

        public string VrmServerIp { get; set; }

        public string VrmServerPort { get; set; }

        public string PreviewParam { get; set; }

        public string PlaybackParam { get; set; }
    }
}
