using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using VideoParamRefresh.Model;
using VideoParamRefresh.DAL;
using VideoParamRefresh.Common;
using Hik8600V323Service;
using System.Xml;
using System.Net;
using System.Web.Services.Protocols;

namespace VideoParamRefresh.BLL.HikVideoAccess
{

    public class HikVideoServiceAccess
    {  
        internal List<VideoEntry> GetAllCameraParam(string serverId)
        {
            List<VideoEntry> lstRslt = null;
            try
            {
                // 获取流媒体web服务地址 
                //List<HikServerValue> lstHikServer = new List<HikServerValue>();//1080
                //lstHikServer.Add(new HikServerValue() { hikSvcIp = "192.168.10.94", hikSvcPort = "8011", hikSvcUserName = "admin", hikSvcPassword = "12345" });

                //#region 调试
                //foreach (var svcVlaue in lstHikServer)
                //{
                //    Logger.Debug("GetAllCameraParam-海康8600平台信息：" + svcVlaue.hikSvcIp + "," + svcVlaue.hikSvcUserName + "," + svcVlaue.hikSvcPassword + "," + svcVlaue.hikSvcPort);
                //}
                //#endregion

                List<VideoEntry> lstCameraParam = new List<VideoEntry>(); 
                #region 云南8600V3.2.3平台 
                List<HikServerValue> lstHikServer = VideoDal.GetWebSvcAddress();//Config.Instance.Hik8600V323ServerId//"ConfigValue.Value[Hik8600V323ServerId]"

                    #region 访问流媒体web服务
                    string hikWebSvcUrl; 
                    Hik8600V323ThirdInfoService.ThirdInfoService hik8600V323ThirdInfoService = null;
                    
                    foreach (HikServerValue hikServer in lstHikServer)
                    {
                        if (String.IsNullOrEmpty(hikServer.context) || String.IsNullOrEmpty(hikServer.suffix))
                        {
                            continue;
                        }
                        hikWebSvcUrl = hikServer.webSvcAddr;
                        try
                        { 

                            #region 登陆海康8600V3.2.3平台 
                            hik8600V323ThirdInfoService = new Hik8600V323ThirdInfoService.ThirdInfoService(hikWebSvcUrl);//"http://10.166.193.252:8011/services/ThirdInfoService?wsdl");
                            Logger.Debug("8600V3.2.3Web服务地址：" + hikWebSvcUrl);
                            string SessionId = "";
                            string ZoneId = "";
                            string ControlUnitId = "";
                            IPHostEntry ipHost = Dns.Resolve(Dns.GetHostName()); //获取客户端IP地址
                            IPAddress ipAddr = ipHost.AddressList[0];

                            StringBuilder logingXml = new StringBuilder();
                            logingXml.Append("<?xml version='1.0'?>");
                            logingXml.Append("<requestInfo>");
                            logingXml.Append("<userName>" + "admin" + "</userName>");//因为数据库没有字段，目前写死
                            logingXml.Append("<pwd>" + Config.Instance.DefaultPwd + "</pwd>");
                            logingXml.Append("<clientIp>" + Dns.Resolve(Dns.GetHostName()) + "</clientIp>");
                            logingXml.Append("<clientPort>" + "2255" + "</clientPort>");
                            logingXml.Append("<cmsIP>" + hikServer.hikSvcIp + "</cmsIP>");
                            logingXml.Append("</requestInfo>");
                            string logingReturn = hik8600V323ThirdInfoService.userLogin(logingXml.ToString());
                            if (logingReturn.Length > 0)
                            {
                                XmlDocument loginDoc = new XmlDocument();
                                loginDoc.LoadXml(logingReturn);
                                //解析登录参数
                                XmlNodeList returnNoteList = loginDoc.SelectNodes("responseInfo/sessionId");
                                SessionId = returnNoteList[0].InnerText;
                                if (String.IsNullOrEmpty(SessionId))
                                {
                                    throw new Exception(logingReturn);
                                }
                                else
                                {
                                    XmlNodeList returnNoteList2 = loginDoc.SelectNodes("responseInfo/dataInfo");
                                    ZoneId = returnNoteList2[0]["netZoneId"].InnerText;
                                    Logger.Info("登陆8600V3.2.3平台(" + hikServer.hikSvcIp + ")成功，SessonId：" + SessionId + ",网域ID" + ZoneId);
                                }
                            }
                            #endregion

                            #region 获取相机列表
                            StringBuilder getCameraRequestXml = new StringBuilder();
                            getCameraRequestXml.Append("<?xml version='1.0'?>");
                            getCameraRequestXml.Append("<requestInfo>");
                            getCameraRequestXml.Append("<sessionId>" + SessionId + "</sessionId>");//SessonId.sessionId
                            getCameraRequestXml.Append("<commonPageReq>");
                            getCameraRequestXml.Append("<currentPage>1</currentPage>");
                            getCameraRequestXml.Append("<pageSize>200</pageSize>");
                            getCameraRequestXml.Append("</commonPageReq>");
                            getCameraRequestXml.Append("<dataReq>");
                            getCameraRequestXml.Append("<controlUnitId>0</controlUnitId>");
                            getCameraRequestXml.Append("<regionId>0</regionId>");
                            getCameraRequestXml.Append("</dataReq>");
                            getCameraRequestXml.Append("</requestInfo>");

                            string castr = getCameraRequestXml.ToString();
                            string jkd = hik8600V323ThirdInfoService.getAllCamera(castr);

                            List<CameraInfoForSdkNew> cameraInfoArr = new List<CameraInfoForSdkNew>();
                            if (jkd.Length > 0)
                            {
                                //jkd = jkd.Replace("\n ","");
                                //jkd = jkd.Replace(" ", "");
                                XmlDocument xmlDoc = new XmlDocument();
                                xmlDoc.LoadXml(jkd);
                                XmlNodeList xmlNoteList = xmlDoc.SelectNodes("responseInfo/cameraInfoList/cameraInfo");
                                foreach (XmlNode xNode in xmlNoteList)
                                {
                                    CameraInfoForSdkNew cameraInfo = new CameraInfoForSdkNew();
                                    cameraInfo.cameraId = Convert.ToInt32(xNode["cameraId"].InnerText);
                                    cameraInfo.indexCode = xNode["indexCode"].InnerText;
                                    cameraInfo.deviceId = Convert.ToInt32(xNode["deviceId"].InnerText);
                                    cameraInfo.chanNum = Convert.ToInt32(xNode["chanNum"].InnerText);
                                    cameraInfo.recLacation = new int?[] { Convert.ToInt32(xNode.SelectNodes("recLacationList")[0].InnerText) };
                                    cameraInfoArr.Add(cameraInfo);
                                }
                            } 
                            
                            #endregion

                            #region 获取相机参数 
                            foreach (CameraInfoForSdkNew cameraInfo in cameraInfoArr)
                            {
                                //    try
                                //    {
                                //        //hik8600V323Service.getPreviewGroupResource

                                //        //Hik8600V323Service.DeviceInfoDTO deviceTmp = deviceInfoArr.Where(z => z.deviceId == cameraInfo.deviceId).FirstOrDefault();

                                //        if (deviceTmp != null && !string.IsNullOrEmpty(deviceTmp.name) && !string.IsNullOrEmpty(deviceTmp.networkAddr))
                                //        {
                                StringBuilder previewParamRequestXml = new StringBuilder();
                                previewParamRequestXml.Append("<?xml version='1.0'?>");
                                previewParamRequestXml.Append("<requestInfo>");
                                previewParamRequestXml.Append("<sessionId>" + SessionId + "</sessionId>");
                                previewParamRequestXml.Append("<previewParam>");
                                previewParamRequestXml.Append("<cameraId>" + cameraInfo.cameraId.Value + "</cameraId>");
                                previewParamRequestXml.Append("<netZoneID>" + ZoneId + "</netZoneID>");
                                previewParamRequestXml.Append("</previewParam>");
                                previewParamRequestXml.Append("</requestInfo>");

                                

                                VideoEntry cameraParamUnit = new VideoEntry();
                                //监控点ID
                                cameraParamUnit.CameraId = cameraInfo.indexCode;
                                     

                                string r = hik8600V323ThirdInfoService.getPreviewParam(previewParamRequestXml.ToString());
                                if (r.Length > 0)
                                {
                                    int i = r.IndexOf("<Parament>");
                                    r = r.Substring(i, r.Length - i);
                                }
                                cameraParamUnit.PreviewParam = r;

                                StringBuilder playBackParamRequestXml = new StringBuilder();
                                playBackParamRequestXml.Append("<?xml version='1.0'?>");
                                playBackParamRequestXml.Append("<requestInfo>");
                                playBackParamRequestXml.Append("<sessionId>" + SessionId + "</sessionId>");
                                playBackParamRequestXml.Append("<playbackParam>");
                                playBackParamRequestXml.Append("<cameraId>" + cameraInfo.cameraId.Value + "</cameraId>");
                                playBackParamRequestXml.Append("<recordLocation>" + cameraInfo.recLacation[0] + "</recordLocation>");
                                playBackParamRequestXml.Append("<playWndIndex>1</playWndIndex>");
                                playBackParamRequestXml.Append("<netZoneID>" + ZoneId + "</netZoneID>");
                                playBackParamRequestXml.Append("<startTime></startTime>");
                                playBackParamRequestXml.Append("<endTime></endTime>");
                                playBackParamRequestXml.Append("<recordType>3</recordType>");
                                playBackParamRequestXml.Append("</playbackParam>");
                                playBackParamRequestXml.Append("</requestInfo>");
                                string t = hik8600V323ThirdInfoService.getPlaybackParam(playBackParamRequestXml.ToString());

                                if (t.Length > 0)
                                {
                                    int i = t.IndexOf("<ZoneID>");
                                    t = t.Substring(i, t.Length - i);
                                }
                                cameraParamUnit.PlaybackParam = t;
                                lstCameraParam.Add(cameraParamUnit);


                                 
                                //    }
                                //}
                                //catch (Exception ex)
                                //{
                                //    Logger.Info("GetAllHikCameraParam函数获取WEB设备信息失败：循环相机列表错误,ERROR:" + ex.Message);
                                //    continue;
                                //}
                            }
                            #endregion

                            #region 拼接视频字符串 插入数据库  List<CameraParamUnit> lstCameraParam

                            if (lstCameraParam.Count > 0)
                            {
                                Dictionary<string, string> dicCameraInfo = new Dictionary<string, string>(); //KEY:IP|PORT, VALEU:相机参数字符串
                                foreach (VideoEntry CameraInfo in lstCameraParam)
                                {
                                    if (!dicCameraInfo.ContainsKey(CameraInfo.CameraId))
                                    {
                                        string HikCameraParams = CameraInfo.PreviewParam;
                                        dicCameraInfo.Add(CameraInfo.CameraId, HikCameraParams);
                                        //测试
                                        //Logger.Info("KEY：" + CameraInfo.CameraId + ",VALUE:" + HikCameraParams);
                                    }
                                }
                                //更新数据库
                                if (dicCameraInfo.Count > 0)
                                {
                                    VideoDal.UpdateCameraParamById(hikServer, dicCameraInfo);
                                }

                                Dictionary<string, string> dicCameraInfo2 = new Dictionary<string, string>(); //KEY:IP|PORT, VALEU:相机参数字符串
                                foreach (VideoEntry CameraInfo in lstCameraParam)
                                {
                                    if (!dicCameraInfo2.ContainsKey(CameraInfo.CameraId))
                                    {
                                        string HikCameraParams = CameraInfo.PlaybackParam;
                                        dicCameraInfo2.Add(CameraInfo.CameraId, HikCameraParams);
                                        //测试
                                        //Logger.Info("KEY：" + CameraInfo.CameraId + ",VALUE:" + HikCameraParams);
                                    }
                                }
                                //更新数据库
                                if (dicCameraInfo2.Count > 0)
                                {
                                    VideoDal.UpdateCameraPlayBackParamById(hikServer, dicCameraInfo2);
                                }
                            }

                            #endregion
                        }
                        catch (Exception e)
                        {
                            Logger.Error("连接" + hikWebSvcUrl + "失败," + e.Message + e.StackTrace);
                            continue;
                        }
                        finally
                        {
                            CloseHik8600V323(hik8600V323ThirdInfoService);
                            hik8600V323ThirdInfoService = null;
                        } 
                    #endregion
                }
                #endregion 
            }
            catch (Exception e)
            {
                Logger.Error(e);
            }

            return lstRslt;
        }

        internal List<DeviceStatusResultDao> GetAllCameraStatus(string serverId)
        {
            List<DeviceStatusResultDao> lstResult = new List<DeviceStatusResultDao>();
            try
            {
                List<VideoEntry> lstCameraParam = new List<VideoEntry>(); 
                #region 云南8600V3.2.3平台 
                List<HikServerValue> lstHikServer = VideoDal.GetWebSvcAddress();//Config.Instance.Hik8600V323ServerId//"ConfigValue.Value[Hik8600V323ServerId]"
                String videoIPAndPort;
                #region 访问流媒体web服务
                string hikWebSvcUrl; 
                Hik8600V323ThirdInfoService.ThirdInfoService hik8600V323ThirdInfoService = null;
                    
                foreach (HikServerValue hikServer in lstHikServer)
                {
                    if (String.IsNullOrEmpty(hikServer.context) || String.IsNullOrEmpty(hikServer.suffix))
                    {
                        continue;
                    }
                    hikWebSvcUrl = hikServer.webSvcAddr;
                    try
                    { 
                        #region 登陆海康8600V3.2.3平台 
                        hik8600V323ThirdInfoService = new Hik8600V323ThirdInfoService.ThirdInfoService(hikWebSvcUrl);//"http://10.166.193.252:8011/services/ThirdInfoService?wsdl");
                        Logger.Debug("8600V3.2.3Web服务地址：" + hikWebSvcUrl);
                        string SessionId = "";
                        string ZoneId = "";
                        string ControlUnitId = "";
                        IPHostEntry ipHost = Dns.Resolve(Dns.GetHostName()); //获取客户端IP地址
                        IPAddress ipAddr = ipHost.AddressList[0];

                        StringBuilder logingXml = new StringBuilder();
                        logingXml.Append("<?xml version='1.0'?>");
                        logingXml.Append("<requestInfo>");
                        logingXml.Append("<userName>" + "admin" + "</userName>");//因为数据库没有字段，目前写死
                        logingXml.Append("<pwd>" + Config.Instance.DefaultPwd + "</pwd>");
                        logingXml.Append("<clientIp>" + Dns.Resolve(Dns.GetHostName()) + "</clientIp>");
                        logingXml.Append("<clientPort>" + "2255" + "</clientPort>");
                        logingXml.Append("<cmsIP>" + hikServer.hikSvcIp + "</cmsIP>");
                        logingXml.Append("</requestInfo>");
                        string logingReturn = hik8600V323ThirdInfoService.userLogin(logingXml.ToString());
                        if (logingReturn.Length > 0)
                        {
                            XmlDocument loginDoc = new XmlDocument();
                            loginDoc.LoadXml(logingReturn);
                            //解析登录参数
                            XmlNodeList returnNoteList = loginDoc.SelectNodes("responseInfo/sessionId");
                            SessionId = returnNoteList[0].InnerText;
                            if (String.IsNullOrEmpty(SessionId))
                            {
                                throw new Exception(logingReturn);
                            }
                            else
                            {
                                XmlNodeList returnNoteList2 = loginDoc.SelectNodes("responseInfo/dataInfo");
                                ZoneId = returnNoteList2[0]["netZoneId"].InnerText;
                                Logger.Info("登陆8600V3.2.3平台(" + hikServer.hikSvcIp + ")成功，SessonId：" + SessionId + ",网域ID" + ZoneId);
                            }
                        }
                        #endregion

                        #region 获取相机状态
                        StringBuilder getCameraRequestXml = new StringBuilder();
                        getCameraRequestXml.Append("<?xml version='1.0'?>");
                        getCameraRequestXml.Append("<requestInfo>");
                        getCameraRequestXml.Append("<sessionId>" + SessionId + "</sessionId>");//SessonId.sessionId
                        getCameraRequestXml.Append("<commonPageReq>");
                        getCameraRequestXml.Append("<currentPage>1</currentPage>");
                        getCameraRequestXml.Append("<pageSize>200</pageSize>");
                        getCameraRequestXml.Append("</commonPageReq>");
                        getCameraRequestXml.Append("<dataReq>");
                        getCameraRequestXml.Append("<controlUnitId>0</controlUnitId>");
                        getCameraRequestXml.Append("<regionId>0</regionId>");
                        getCameraRequestXml.Append("</dataReq>");
                        getCameraRequestXml.Append("</requestInfo>");

                        string castr = getCameraRequestXml.ToString();
                        string jkd = hik8600V323ThirdInfoService.getCameraStatus(castr);

                        List<CameraInfoForSdkNew> cameraInfoArr = new List<CameraInfoForSdkNew>();
                        if (jkd.Length > 0)
                        {
                            //jkd = jkd.Replace("\n ","");
                            //jkd = jkd.Replace(" ", "");
                            XmlDocument xmlDoc = new XmlDocument();
                            xmlDoc.LoadXml(jkd);
                            XmlNodeList xmlNoteList = xmlDoc.SelectNodes("responseInfo/cameraStatusList/cameraStatus");
                            foreach (XmlNode xNode in xmlNoteList)
                            {
                                CameraInfoForSdkNew cameraInfo = new CameraInfoForSdkNew();
                                cameraInfo.cameraId = Convert.ToInt32(xNode["cameraId"].InnerText);
                                cameraInfo.indexCode = xNode["indexCode"].InnerText;
                                cameraInfo.deviceId = Convert.ToInt32(xNode["deviceId"].InnerText);
                                cameraInfo.chanNum = Convert.ToInt32(xNode["isOnline"].InnerText);
                                cameraInfoArr.Add(cameraInfo);
                            }
                        }
                        Logger.Info("共获取到的设备状态数量为:" + cameraInfoArr.Count);
                        #endregion  

                        #region 从数据库获取值，拼接成状态dic

                        Dictionary<string, List<string>> dicKeyPair = VideoDal.GetCameraKeyValuePare(hikServer);
                        Logger.Info("从数据库获取到的备案的相机数量为:" + dicKeyPair.Count);
                         
                        if (dicKeyPair.Count > 0 && cameraInfoArr.Count>0)
                        {                            
                            foreach (CameraInfoForSdkNew cameraInfo in cameraInfoArr)
                            {
                                try
                                {
                                    if (dicKeyPair.ContainsKey(cameraInfo.indexCode))
                                    {
                                        DeviceStatusResultDao dao = new DeviceStatusResultDao();
                                        dao.CameraSn = cameraInfo.indexCode;
                                        //8600V3.2.3平台1在线，0离线
                                        dao.Status = (int)cameraInfo.chanNum;
                                        dao.relateDatabaseKeys = new List<string>();
                                        foreach (var deviceSysNbr in dicKeyPair[cameraInfo.indexCode])
                                        {
                                            dao.relateDatabaseKeys.Add(deviceSysNbr);
                                        }
                                        Logger.Debug("获取匹配设备状态:" + cameraInfo.indexCode + ",状态:" + ((cameraInfo.chanNum.Value)).ToString() + (cameraInfo.chanNum.Value == 0 ? "离线" : "在线"));
                                    }
                                }
                                catch (Exception ex)
                                {
                                    throw;
                                }
                            } 
                        } 
                    }
                    #endregion
                    catch (Exception e)
                    {
                        Logger.Error("连接" + hikWebSvcUrl + "失败," + e.Message + e.StackTrace);
                        continue;
                    }
                    finally
                    {
                        CloseHik8600V323(hik8600V323ThirdInfoService);
                        hik8600V323ThirdInfoService = null;
                    } 
                    #endregion
                }
                #endregion 
            }
            catch (Exception e)
            {
                Logger.Error(e);
            }

            return lstResult;
        }

        private static void CloseHik8600V323(HttpWebClientProtocol hik8600V323Service)
        {
            try
            {
                if (hik8600V323Service != null)
                {
                    hik8600V323Service.Abort();
                    hik8600V323Service.Dispose();
                }
            }
            catch (Exception ex)
            {
                Logger.Error(ex.Message);
            }
        }

        /// <summary>
        /// 根据WEB服务中获取的相机录像位置类型获取回放参数中的录像类型
        /// </summary>
        /// <param name="recCode">录像位置类型: 0-无录像，2-设备录像，4-nvr录像，6-设备+nvr录像</param>
        /// <returns>录像类型: 0-IPSAN录像，1-设备录像，2-NVR录像，3-DSNVR录像, 4-CVR录像, 5-热备NVR录像</returns>
        private static int getRecTypeByRecCode(int? recCode)
        {
            int reclocation = 0;

            if (recCode == 2)
            {
                reclocation = 1;
            }
            else if (recCode == 4 || recCode == 6)
            {
                reclocation = 2;
            }

            return reclocation;
        }

        static int[] DefaultPresetArray = new int[3] { 14, 15, 16 };  
    }
}
