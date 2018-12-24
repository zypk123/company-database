package cy.its.service.common.dataModel;

import java.util.Date;

public class DeviceAlarm {
	
	/**
	* 唯一标识
	*/
	@Mapper("ALARM_ID")
	private String alarmId;
	
	/**
	* 1:设备报警
	2:服务器报警
	3:其他
	*/
	@Mapper("ALARM_TYPE")
	private String alarmType;
	
	/**
	* 设备类，对应code:478
	其他可为空
	*/
	@Mapper("ALARM_SUB_TYPE")
	private String alarmSubType;
	
	/**
	* 报警关联的设备ID，可以是设备，也可以是服务器等
	*/
	@Mapper("ALARM_DEVICE_ID")
	private String alarmDeviceId;
	
	/**
	* 报警开始时间
	*/
	@Mapper("START_TIME")
	private Date startTime;
	
	/**
	* 报警结束时间，可以为空
	*/
	@Mapper("END_TIME")
	private Date endTime;
	
	/**
	* 1前端设备上传
	2系统分析
	3人工添加
	*/
	@Mapper("COLLECT_WAY")
	private String collectWay;
	
	/**
	* 故障描述
	*/
	@Mapper("ALARM_DISC")
	private String alarmDisc;
	
	/**
	* 机构权限编码，用作权限过滤
	*/
	@Mapper("ORG_PRIVILEGE_CODE")
	private String orgPrivilegeCode;
	
	public String getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}

	public String getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	public String getAlarmSubType() {
		return alarmSubType;
	}

	public void setAlarmSubType(String alarmSubType) {
		this.alarmSubType = alarmSubType;
	}

	public String getAlarmDeviceId() {
		return alarmDeviceId;
	}

	public void setAlarmDeviceId(String alarmDeviceId) {
		this.alarmDeviceId = alarmDeviceId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getCollectWay() {
		return collectWay;
	}

	public void setCollectWay(String collectWay) {
		this.collectWay = collectWay;
	}

	public String getAlarmDisc() {
		return alarmDisc;
	}

	public void setAlarmDisc(String alarmDisc) {
		this.alarmDisc = alarmDisc;
	}

	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}

}
