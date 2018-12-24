using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using VideoParamRefresh.Common;
using VideoParamRefresh.Model;

namespace VideoParamRefresh.DAL
{
    public class VideoDal
    { 
        #region GetWebSvcAddress 
        public static List<HikServerValue> GetWebSvcAddress()
        {
            try
            {
                List<HikServerValue> lstSvcAddress = new List<HikServerValue>();
                //string sql = "SELECT distinct SERVER_IP, " +
                //             "       SERVER_PORT, SERVER_TYPE, " +
                //             "       CONTEX, SUFFIX " +
                //             "  FROM T_DEVICE_SERVER_TYPE " +
                //             " WHERE SERVER_TYPE = '8'";
                string sqlGetServer = "SELECT S.SERVER_IP,A.SERVER_PORT,A.APP_TYPE,A.CONTEX,A.SUFFIX,A.SERVER_APP_ID " +
                    "FROM T_DEVICE_SERVER_APPLICATION A,T_DEVICE_SERVER S  WHERE A.APP_TYPE='8'" +
                    "AND A.SERVER_ID=S.SERVER_ID";

                string sqlGetServerPlatID = "SELECT O.SERVER_PLAT_ID " +
                "FROM T_DEVICE_SERVER_APPLICATION A,T_DEVICE_SERVER_APP_SSO_PLAT O" +
                " WHERE A.SERVER_APP_ID=O.SERVER_APP_ID AND A.SERVER_APP_ID='{0}'";
                using (BaseDAO dao = new BaseDAO())
                {
                    IDataReader sqlRead = dao.ExcuteReader(sqlGetServer, null);
                    while (sqlRead.Read())
                    {
                        //服务器类型 8:视频WEB服务器类型
                        HikServerValue svcInfo = new HikServerValue();
                        // svcInfo.serverId = (sqlRead[0] != DBNull.Value) ? sqlRead[0].ToString() : null;
                        svcInfo.hikSvcIp = (sqlRead[0] != DBNull.Value) ? sqlRead[0].ToString() : null;
                        svcInfo.hikSvcPort = (sqlRead[1] != DBNull.Value) ? sqlRead[1].ToString() : null;
                        svcInfo.svcType = (sqlRead[2] != DBNull.Value) ? sqlRead[2].ToString() : null;
                        svcInfo.context = (sqlRead[3] != DBNull.Value) ? sqlRead[3].ToString() : null;
                        svcInfo.suffix = (sqlRead[4] != DBNull.Value) ? sqlRead[4].ToString() : null;
                        svcInfo.serverAppId = (sqlRead[5] != DBNull.Value) ? sqlRead[5].ToString() : null;
                        lstSvcAddress.Add(svcInfo);
                    }
                    if (lstSvcAddress.Count > 0)
                    {

                        foreach (HikServerValue hikServer in lstSvcAddress)
                        {

                            sqlRead = dao.ExcuteReader(String.Format(sqlGetServerPlatID, hikServer.serverAppId), null);
                            while (sqlRead.Read())
                            {
                                if (sqlRead[0] != DBNull.Value)
                                {
                                    hikServer.serverPlatIds.Add(sqlRead[0].ToString());
                                }
                            }
                        }
                    }
                }

                return lstSvcAddress;
            }
            catch (Exception ex)
            {
                Logger.Error("GetWebSvcAddress失败，" + ex.Message + ",stack trace:" + ex.StackTrace);
                return null;
            }
        }

        #endregion  

        #region UpdateCameraParamById
        public static void UpdateCameraParamById(HikServerValue hikServer,Dictionary<string, string> dicCameraInfo)
        {
            int UpdateSuccessNum = 0;
            int UpdateFailNum = 0;
            DateTime UpdateStart = DateTime.Now;
            Logger.Info("更新数据库中视频预览参数开始");
            using (BaseDAO dao = new BaseDAO())
            {
                foreach (KeyValuePair<string, string> value in dicCameraInfo)
                {
                    string Key = value.Key;
                    try
                    {
                        string updateVideoSql = String.Format("UPDATE T_DEVICE_VIDEO V SET PREVIEW_PARAM='{0}' WHERE EXISTS (SELECT 1 FROM T_DEVICE_SYS S WHERE V.DEVICE_ID=S.DEVICE_ID AND S.SERVER_PLAT_ID IN ('{1}') AND V.CAMERA_SN='{2}')",
                            value.Value,
                            String.Join("','",hikServer.serverPlatIds.ToArray()),
                            value.Key);
                        string updateComponentSql = String.Format("UPDATE T_DEVICE_SYS_COMPONENT C SET PREVIEW_PARAM='{0}' WHERE EXISTS (SELECT 1 FROM T_DEVICE_SYS S WHERE C.DEVICE_ID=S.DEVICE_ID AND S.SERVER_PLAT_ID IN ('{1}') AND C.CAMERA_SN='{2}')",
                            value.Value,
                            String.Join("','", hikServer.serverPlatIds.ToArray()),
                            value.Key);
                        int Res = dao.ExcuteNonQuery(updateVideoSql, null);
                        #region 调式
                        if (Res > 0)
                        {
                            Logger.Debug("更新视频相机预览参数成功,CAMERA_SN：" + value.Key);
                            UpdateSuccessNum += Res;
                        }
                        else
                        {
                            Logger.Debug("更新视频相机预览参数不成功,CAMERA_SN:" + value.Key + "不存在");
                            UpdateSuccessNum++;
                        }

                        Res = dao.ExcuteNonQuery(updateComponentSql, null);
                        if (Res > 0)
                        {
                            Logger.Debug("更新视频组件预览参数成功,CAMERA_SN：" + value.Key);
                            UpdateSuccessNum += Res;
                        }
                        else
                        {
                            Logger.Debug("更新视频组件预览参数不成功，CAMERA_SN：" + value.Key + "不存在");
                            UpdateFailNum++;
                        } 
                        #endregion
                    }
                    catch (Exception ex)
                    {
                        //记日志
                        Logger.Error("UpdateCameraParam失败，" + value.Key + ":" + ex.Message + ",stack trace:" + ex.StackTrace);
                        continue;
                    }
                }
            }
            DateTime UpdateEnd = DateTime.Now;
            Logger.Info("更新数据库中视频预览参数结束,共耗时：" + (UpdateEnd - UpdateStart).TotalSeconds + "秒，共需要更新个数：" +
                dicCameraInfo.Count + ",其中跟新成功个数：" + UpdateSuccessNum.ToString() + ",失败个数：" + UpdateFailNum.ToString());
        }
        #endregion

        #region UpdateCameraParamById2
        public static void UpdateCameraPlayBackParamById(HikServerValue hikServer, Dictionary<string, string> dicCameraInfo)
        {
            int UpdateSuccessNum = 0;
            int UpdateFailNum = 0;
            DateTime UpdateStart = DateTime.Now;
            Logger.Info("更新数据库中视频回放参数开始" );
            using (BaseDAO dao = new BaseDAO())
            {
                foreach (KeyValuePair<string, string> value in dicCameraInfo)
                {
                    string Key = value.Key;
                    try
                    {
                        string updateVideoSql = String.Format("UPDATE T_DEVICE_VIDEO V SET PLAYBACK_PARAM='{0}' WHERE EXISTS (SELECT 1 FROM T_DEVICE_SYS S WHERE V.DEVICE_ID=S.DEVICE_ID AND S.SERVER_PLAT_ID IN ('{1}') AND V.CAMERA_SN='{2}')",
                            value.Value,
                            String.Join("','", hikServer.serverPlatIds.ToArray()),
                            value.Key);
                        string updateComponentSql = String.Format("UPDATE T_DEVICE_SYS_COMPONENT C SET PLAYBACK_PARAM='{0}' WHERE EXISTS (SELECT 1 FROM T_DEVICE_SYS S WHERE C.DEVICE_ID=S.DEVICE_ID AND S.SERVER_PLAT_ID IN ('{1}') AND C.CAMERA_SN='{2}')",
                            value.Value,
                            String.Join("','", hikServer.serverPlatIds.ToArray()),
                            value.Key);
                        int Res = dao.ExcuteNonQuery(updateVideoSql, null);
                        #region 调式
                        if (Res > 0)
                        {
                            Logger.Debug("更新视频相机回放参数成,CAMERA_SN:" + value.Key);
                            UpdateSuccessNum += Res;
                        }
                        else
                        {
                            Logger.Debug("更新视频相机参数不成功,CAMERA_SN:" + value.Key + "不存在");
                            UpdateSuccessNum++;
                        } 
                        Res = dao.ExcuteNonQuery(updateComponentSql, null);
                        if (Res > 0)
                        {
                            Logger.Debug("更新视频组件回放参数成,CAMERA_SN:" + value.Key);
                            UpdateSuccessNum += Res;
                        }
                        else
                        {
                            Logger.Debug("更新视频组件回放参数失败，CAMERA_SN：" + value.Key + "不存在");
                            UpdateFailNum++;
                        } 
                        #endregion
                    }
                    catch (Exception ex)
                    {
                        //记日志
                        Logger.Error("UpdateCameraParam失败，" + value.Key + ":" + ex.Message + ",stack trace:" + ex.StackTrace);
                        continue;
                    }
                }
            }
            DateTime UpdateEnd = DateTime.Now;
            Logger.Info("更新数据库中视频回放参数结束，共耗时：" + (UpdateEnd - UpdateStart).TotalSeconds + "秒，共需要更新个数：" +
                dicCameraInfo.Count + ",其中跟新成功个数：" + UpdateSuccessNum.ToString() + ",失败个数：" + UpdateFailNum.ToString());
        }
        #endregion   

        #region UpdateCameraStatus2 更新相机状态
        
        public static void UpdateCameraStatus(Dictionary<string, bool> dicCameraInfo)
        {
            int UpdateSuccessNum = 0;
            int UpdateFailNum = 0;
            DateTime UpdateStart = DateTime.Now;
            Logger.Info("更新数据库中视频状态开始，开始时间：" + UpdateStart.ToString());
            string logInfo = string.Empty;
            //using (DbAccess dao = new DbAccess())
            //{
            //    foreach (KeyValuePair<string, bool> value in dicCameraInfo)
            //    {
            //        //string[] Key = value.Key.Split(':');
            //        try
            //        {
            //            string Sql = "";
            //            //if (!string.IsNullOrEmpty(Key[1]))
            //            Sql = "UPDATE D_DEVICE_CAMERA SET STATUS='" + (value.Value ? "0" : "1") + "' WHERE CAMERA_SN = '" + value.Key + "'";
            //            //else
            //            //    Sql = "UPDATE D_DEVICE_CAMERA SET STATUS='" + (value.Value ? "0" : "1") + "' WHERE CAMERA_IP = '" + Key[0] + "' AND CAMERA_PORT is null";
            //            int Res = dao.ExecuteNonQuery(Sql, null);
            //            #region 调式
            //            if (Res > 0)
            //            {
            //                logInfo += value.Key + "成功,";
            //                // log.Debug("更新视频相机状态成功：" + value.Key + "," + value.Value + ",更新个数=" + Res );
            //                UpdateSuccessNum++;
            //            }
            //            else
            //            {
            //                logInfo += value.Key + "不成功,";
            //                //log.Debug("更新视频相机状态不成功：" + value.Key + "," + value.Value);
            //                UpdateFailNum++;
            //            }
            //            #endregion
            //        }
            //        catch (Exception ex)
            //        {
            //            //记日志
            //            log.Error("更新视频相机状态失败，" + value.Key + ":" + ex.Message);
            //            continue;
            //        }
            //    }
            //}
            //log.Debug("数据库详细信息=" + logInfo);
            //DateTime UpdateEnd = DateTime.Now;
            //log.Info("更新数据库中视频状态结束，结束时间：" + UpdateEnd.ToString() + ",共耗时：" + (UpdateEnd - UpdateStart).TotalSeconds + "秒，共需要更新个数：" +
            //    dicCameraInfo.Count + ",其中更新成功个数：" + UpdateSuccessNum.ToString() + ",失败个数：" + UpdateFailNum.ToString());
        }
        #endregion

        #region MyRegion
        public static Dictionary<string,List<string>> GetCameraKeyValuePare(HikServerValue hikServer)
        {
            Dictionary<string, List<string>> dicResult = new Dictionary<string, List<string>>();
            string baseDeviceSql = "SELECT V.CAMERA_SN,S.DEVICE_SYS_NBR FROM T_DEVICE_VIDEO V,T_DEVICE_SYS S" +
                        " WHERE V.DEVICE_ID=S.DEVICE_ID" +
                        " AND S.SERVER_PLAT_ID='{0}'";

            string baseComponentSql="SELECT C.CAMERA_SN, C.DEVICE_KEY"+
                        " FROM T_DEVICE_SYS_COMPONENT C,"+
                        " T_DEVICE_SYS   S  WHERE C.DEVICE_ID=S.DEVICE_ID AND S.SERVER_PLAT_ID ='{0}'";
            using (BaseDAO dao = new BaseDAO())
            {
                if(hikServer.serverPlatIds!=null&&hikServer.serverPlatIds.Count>0){
                    foreach (var serverPlatId in hikServer.serverPlatIds)
	                {
                        string sql = String.Format(baseDeviceSql, serverPlatId);
                        IDataReader sqlRead = dao.ExcuteReader(sql, null);
                         
                         while (sqlRead.Read())
                         {
                             string cameraSn = (sqlRead[0] != DBNull.Value) ? sqlRead[0].ToString() : null;
                             string deviceSysNbr = (sqlRead[1] != DBNull.Value) ? sqlRead[1].ToString() : null;
                             if (cameraSn != null)
                             {
                                 if (dicResult.ContainsKey(cameraSn))
                                 {
                                     dicResult[cameraSn].Add(deviceSysNbr);
                                 }
                                 else
                                 {
                                     dicResult[cameraSn] = new List<string>(){
                                     deviceSysNbr};
                                 }
                             }
                         }

                         sql = String.Format(baseComponentSql, serverPlatId);
                         sqlRead = dao.ExcuteReader(sql, null);

                         while (sqlRead.Read())
                         {
                             string cameraSn = (sqlRead[0] != DBNull.Value) ? sqlRead[0].ToString() : null;
                             string deviceSysNbr = (sqlRead[1] != DBNull.Value) ? sqlRead[1].ToString() : null;
                             if (cameraSn != null)
                             {
                                 if (dicResult.ContainsKey(cameraSn))
                                 {
                                     dicResult[cameraSn].Add(deviceSysNbr);
                                 }
                                 else
                                 {
                                     dicResult[cameraSn] = new List<string>(){
                                     deviceSysNbr};
                                 }
                             }
                         }
	                }                    
                }                
            }
            return dicResult;
        }
        #endregion
    }
}
