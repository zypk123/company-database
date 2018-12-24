package cy.its.service.device.statusChecker.data;

import java.util.ArrayList;
import java.util.List;

import cy.its.service.common.StringUtil;
import cy.its.service.common.dataAccess.DbExecuter;
import cy.its.service.common.log.LogManager;
import cy.its.service.device.statusChecker.model.DeviceCfg;
import cy.its.service.device.statusChecker.model.StatusLog;


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
		String sql = null;
		try {
		    List<String> lst =  new ArrayList<String>(devTypes.length);
			for (String s : devTypes) {
				lst.add("'" + s.trim() + "'");
			}
			
			sql = String.format(SQL, String.join(",", lst));
	 		return DbExecuter.executeDataset(DeviceCfg.class, sql);
		} catch (Exception e) {
			LogManager.debug("设备配置查询出错:" + sql + System.lineSeparator() + StringUtil.getErrorDetail(e));
			throw e;
		}
	}
	
	public static List<StatusLog> getDeviceStatusLog() throws Exception {
 		return DbExecuter.executeDataset(StatusLog.class, LOGSQL);
	}

	
	static final String SQL = 
			        "select d.deviceSysId,                                  "+
					"       d.deviceSysNbr,                                 "+
					"       d.architecture,                                 "+
					"       o.org_code orgCode,                             "+
					"       o.org_privilege_code orgPrivilegeCode,          "+
					"       d.deviceType,                                   "+
					"       st.site_code         siteCode,                  "+
					"       d.cameraId,                                     "+
					"       d.cameraNbr,                                    "+
					"       d.cameraKey                                     "+
					"  from (select s.device_id deviceSysId,                "+
					"               s.device_sys_nbr deviceSysNbr,          "+
					"               s.device_type deviceType,               "+
					"               s.architecture,                         "+
					"               s.site_id,                              "+
					"               s.org_id,                               "+
					"               s.use_status_flag,                      "+
					"               '' cameraId,                            "+
					"               '' cameraNbr,                           "+
					"               '' cameraKey                            "+
					"          from t_device_sys s                          "+
					"         where s.architecture = '1'                    "+
					"        union all                                      "+
					"        select s.device_id        deviceSysId,         "+
					"               s.device_sys_nbr   deviceSysNbr,        "+
					"               s.device_type      deviceType,          "+
					"               s.architecture,                         "+
					"               s.site_id,                              "+
					"               s.org_id,                               "+
					"               s.use_status_flag,                      "+
					"               c.sys_component_id cameraId,            "+
					"               c.device_nbr       cameraNbr,           "+
					"               c.device_key       cameraKey            "+
					"          from t_device_sys s, t_device_sys_component c"+
					"         where s.device_id = c.device_id               "+
					"           and c.device_key is not null                "+
					"           and s.architecture = '2') d                 "+
					"  left join t_sys_org o                                "+
					"    on d.org_id = o.org_id                             "+
					"  left join t_sys_site st                              "+
					"    on d.site_id = st.site_id                          "+
					" where d.use_status_flag = '1'                         "+
					"   and d.deviceType in (%s)                            ";

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
