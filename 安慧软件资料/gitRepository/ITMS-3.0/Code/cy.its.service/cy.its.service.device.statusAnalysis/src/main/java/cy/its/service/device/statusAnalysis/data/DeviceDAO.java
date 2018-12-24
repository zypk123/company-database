package cy.its.service.device.statusAnalysis.data;

import java.util.List;

import cy.its.service.common.dataAccess.DbExecuter;
import cy.its.service.device.statusAnalysis.model.DeviceCfg;
import cy.its.service.device.statusAnalysis.model.StatusLog;


/**
 * @Title: DeviceDAO.java
 * @Package cy.its.service.device.statusAnalysis.data
 * @Description: 设备配置信息数据库访问类
 * @author STJ lijun@cychina.cn
 * @date 2015年11月4日 下午3:20:09
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */
public class DeviceDAO {
	
	public static List<DeviceCfg> getDeviceCfg(String[] devTypes) throws Exception {
		for (int i = 0; i < devTypes.length; i++) {
			devTypes[i] = "'" + devTypes[i].trim() + "'";
		}

 		return DbExecuter.executeDataset(DeviceCfg.class, String.format(SQL, String.join(",", devTypes)));
	}
	
	public static List<StatusLog> getDeviceStatusLog() throws Exception {
 		return DbExecuter.executeDataset(StatusLog.class, LOGSQL);
	}

	
	static final String SQL = 
			"select s.device_id deviceId,                                 "+
			"       s.device_sys_nbr deviceSysNbr,                        "+
			"       o.org_code orgCode,                                   "+
			"       o.org_privilege_code orgPrivilegeCode,                "+
			"       s.device_type deviceType,                             "+
			"       st.site_code siteCode,                                "+
			"       s.status_Type statusType,                             "+
			"       s.status_Update_Time statusUpdateTime,                "+
			"       c.sys_component_id sysComponentId,                    "+
			"       c.device_nbr deviceNbr,                               "+
//			"       s.disable_flag disableFlag,                           "+
			"       s.start_time startTime,                               "+
			"       s.end_time endTime                                    "+
			"  from t_device_sys s left join t_device_sys_component c     "+
			"  on s.DEVICE_ID = c.device_id                               "+
			"  left join t_sys_org o on s.org_id = o.org_id, t_sys_site st "+
			"where s.site_id = st.site_id and                             "+
			"      s.use_status_flag = '1' and                            "+
			"      s.device_type in (%s)                                   "  ;

	static final String LOGSQL = 
			"SELECT                                        "+
	        "  DEVICE_ID deviceId, STATUS_TYPE statusType, "+
            "       START_TIME startTime, END_TIME endTime "+
			"  FROM (SELECT ROW_NUMBER() OVER(             "+
			"               PARTITION BY L.DEVICE_ID       "+
			"               ORDER BY L.START_TIME DESC) RN,"+
			"               L.*                            "+
			"          FROM T_DEVICE_STATUS_LOG L          "+
			"         WHERE L.START_TIME > SYSDATE - 3)    "+
			" WHERE RN = 1                                 ";
}
