package cy.its.device.domain.service;

import java.util.List;

import cy.its.device.domain.criteria.DeviceAlarmCriteria;
import cy.its.device.domain.model.DeviceAlarm;
import cy.its.device.domain.model.DeviceAlarmHandle;

public interface IDeviceAlarmService {
	
	/**
	 * 查找报警信息 
	 * @param criteria
	 * @return
	 */
	public List<DeviceAlarm> findDeviceAlarm(DeviceAlarmCriteria criteria);
	
	/**
	 * 签收报警
	 * @param alarmId
	 */
	public void signAlarm(String alarmId,String userId);
	
	/**
	 * 处置报警
	 */
	public DeviceAlarmHandle handleAlarm(DeviceAlarmHandle handle);
	
	/**
	 * 根据ID查找报警信息
	 * @param alarmId
	 * @return
	 */
	public DeviceAlarm deviceAlarmOfId(String alarmId);
	
	/**
	 * 根据报警ID查找处置信息
	 * @param alarm
	 * @return
	 */
	public List<DeviceAlarmHandle> findHandleByAlarmId(String alarmId);
	
	
}
