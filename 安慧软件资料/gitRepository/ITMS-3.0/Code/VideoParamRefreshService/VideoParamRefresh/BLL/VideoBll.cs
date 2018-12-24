using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using VideoParamRefresh.Model;
using VideoParamRefresh.Common;
using VideoParamRefresh.BLL.HikVideoAccess;

namespace VideoParamRefresh.BLL
{
    public class VideoBll
    { 
        static Dictionary<string, List<VideoEntry>> cameraParams = new Dictionary<string, List<VideoEntry>>();
        public static void Init()
        {
            try
            { 
            }
            catch (Exception ex)
            {
                Logger.Error(ex.Message);
            }


        }

        /// <summary>
        /// 获取所有视频参数
        /// </summary>
        /// <param name="serverId"></param>
        public static void GetAllCameraParam(string serverId)
        {
            serverId = string.IsNullOrEmpty(serverId) ? string.Empty : serverId;
            //lock (cameraParams)
            //{
            //    if (cameraParams.ContainsKey(serverId))
            //    {
            //        return cameraParams[serverId];
            //    }
            //}

            List<VideoEntry> lstParams = new List<VideoEntry>(); 
            HikVideoServiceAccess videoServiceAccess = new HikVideoServiceAccess();
            List<VideoEntry> lstParamsTmp = videoServiceAccess.GetAllCameraParam(serverId);
            if (lstParamsTmp != null && lstParamsTmp.Count > 0)
            {
                lstParams.AddRange(lstParamsTmp);
            } 

            //lock (cameraParams)
            //{
            //    cameraParams.Remove(serverId);
            //    cameraParams.Add(serverId, lstParams);
            //} 
        }

        /// <summary>
        /// 获取所有视频状态
        /// </summary>
        /// <param name="serverId"></param>
        public static List<DeviceStatusResultDao> GetAllCameraStatus(string serverId)
        { 
            Dictionary<string, bool> status = new Dictionary<string, bool>();
            serverId = string.IsNullOrEmpty(serverId) ? string.Empty : serverId;
            //lock (cameraParams)
            //{
            //    if (cameraParams.ContainsKey(serverId))
            //    {
            //        return cameraParams[serverId];
            //    }
            //}

            List<VideoEntry> lstParams = new List<VideoEntry>();
            HikVideoServiceAccess videoServiceAccess = new HikVideoServiceAccess();
            return videoServiceAccess.GetAllCameraStatus(serverId);
        }
    }
}
