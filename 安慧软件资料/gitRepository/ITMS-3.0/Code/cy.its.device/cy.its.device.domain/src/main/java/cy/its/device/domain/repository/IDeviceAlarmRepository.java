package cy.its.device.domain.repository;

import java.util.Date;
import java.util.List;

import cy.its.device.domain.criteria.DeviceAlarmCriteria;
import cy.its.device.domain.model.DeviceAlarm;
import cy.its.device.domain.model.DeviceAlarmHandle;

public interface IDeviceAlarmRepository {

	/**
	 * 查找报警信息
	 * @param criteria
	 * @return
	 */
	List<DeviceAlarm> findDeviceAlarm(DeviceAlarmCriteria criteria);

	/**
	 * 增加报警状态记录
	 * @param alarmId
	 * @param date 
	 * @param userId 
	 */
	void saveAlarmStatus(String alarmId, String userId, Date date);

	/**
	 * 增加报警处理记录
	 * @param handle
	 */
	void saveAlarmHandle(DeviceAlarmHandle handle);

	/**
	 * 根据Id查找报警信息
	 * @param alarmId
	 * @return
	 */
	DeviceAlarm findAlarmById(String alarmId);

	/**
	 * 根据报警ID查找处置信息
	 * @param alarmId
	 * @return
	 */
	List<DeviceAlarmHandle> findHandleByAlarmId(String alarmId);
}
