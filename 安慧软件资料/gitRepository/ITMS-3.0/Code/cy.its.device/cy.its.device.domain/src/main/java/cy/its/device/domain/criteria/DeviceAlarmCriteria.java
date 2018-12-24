package cy.its.device.domain.criteria;

import cy.its.com.domain.Criteria;

public class DeviceAlarmCriteria extends Criteria{

	//开始时间
	private String startDateTime;
	
	//结束时间
	private String endDateTime;
	
	//类型
	private String alarmType;
	
	//机构权限编码
	private String orgPrivilegeCode;

	public String getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}
}
