package cy.its.service.common.dataModel;

import java.util.Date;

public class TrafficEvent extends Model {

	/**
	 * 机构代码
	 */
	@Mapper("ORG_CODE")
	private String orgCode;

	/**
	 * 系统编号
	 */
	@Mapper("DEVICE_SYS_NBR")
	private String deviceSysNbr;

	/**
	 * 点位代码
	 */
	@Mapper("SITE_CODE")
	private String siteCode;

	/**
	 * 路况预警事件类别。1 流量预警 2 气象预警 3 交通事件 4 人工记录
	 */
	@Mapper("ALARM_EVENT_TYPE")
	private String alarmEventType;

	/**
	 * 交通事件类型 
	 *   300 抛洒物
	 *   301 拥堵 
	 *   302 停车 
	 *   303 违停 
	 *   304 逆行 
	 *   305 行人 
	 *   306 遗留物，抛洒物碎片
	 *   307 烟雾
	 */
	@Mapper("SUB_ALARM_EVENT")
	private String subAlarmEvent;

	/**
	 * 预警开始时间
	 */
	@Mapper("START_ALARM_TIME")
	private Date startAlarmTime;

	/**
	 * 预警描述
	 */
	@Mapper("ALARM_DESC")
	private String alarmDesc;

	/**
	 * 机构权限过滤代码
	 */
	@Mapper("ORG_PRIVILEGE_CODE")
	private String orgPrivilegeCode;

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}

	public void setDeviceSysNbr(String deviceSysNbr) {
		this.deviceSysNbr = deviceSysNbr;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getAlarmEventType() {
		return alarmEventType;
	}

	public void setAlarmEventType(String alarmEventType) {
		this.alarmEventType = alarmEventType;
	}

	public String getSubAlarmEvent() {
		return subAlarmEvent;
	}

	public void setSubAlarmEvent(String subAlarmEvent) {
		this.subAlarmEvent = subAlarmEvent;
	}

	public Date getStartAlarmTime() {
		return startAlarmTime;
	}

	public void setStartAlarmTime(Date startAlarmTime) {
		this.startAlarmTime = startAlarmTime;
	}

	public String getAlarmDesc() {
		return alarmDesc;
	}

	public void setAlarmDesc(String alarmDesc) {
		this.alarmDesc = alarmDesc;
	}

	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}
	
    private String deviceId;
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
}
