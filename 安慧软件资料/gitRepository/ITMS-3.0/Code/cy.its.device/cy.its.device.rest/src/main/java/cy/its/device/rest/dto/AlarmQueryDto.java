package cy.its.device.rest.dto;

import cy.its.com.dto.BaseDto;
import cy.its.device.domain.criteria.DeviceAlarmCriteria;

/**
 * 报警查询类
 * @author dell
 *
 */
public class AlarmQueryDto extends BaseDto{

	private String alarmType;
	
	private String startDateTime;
	
	private String endDateTime;

	public String getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

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

	public DeviceAlarmCriteria convertToCriteria() {
		DeviceAlarmCriteria criteria = new DeviceAlarmCriteria();
		criteria.setStartDateTime(this.startDateTime);
		criteria.setEndDateTime(this.endDateTime);
		criteria.setAlarmType(this.alarmType);
		criteria.setOrgPrivilegeCode(this.getCurrentOrgPrivilegeCode());
		criteria.setPageNum(this.getPageNumber());
		criteria.setPageSize(this.getPageSize());
		criteria.setOrderName(this.getOrderName());
		criteria.setOrderType(this.getOrderType());
		return criteria;
	}
	
}
