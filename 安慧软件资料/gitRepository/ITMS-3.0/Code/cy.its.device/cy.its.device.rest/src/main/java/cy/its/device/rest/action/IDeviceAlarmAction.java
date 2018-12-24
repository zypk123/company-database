package cy.its.device.rest.action;

import java.util.Map;

import cy.its.device.rest.dto.AlarmQueryDto;

public interface IDeviceAlarmAction {

	/**
	 * 报警签收
	 * @return
	 */
	public String alarmSign(String alarmId);
	
	
	/**
	 * 报警处理
	 */
	public String handle(String alarmId);
	
	/**
	 * 查找报警信息
	 * @return
	 */
	public Map findAlarmInfo(AlarmQueryDto dto);
}
