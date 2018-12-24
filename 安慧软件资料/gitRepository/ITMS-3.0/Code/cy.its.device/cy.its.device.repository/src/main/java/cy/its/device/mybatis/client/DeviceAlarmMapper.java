package cy.its.device.mybatis.client;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cy.its.device.domain.model.DeviceAlarm;
import cy.its.device.domain.model.DeviceAlarmHandle;

public interface DeviceAlarmMapper {

	/**
	 * 查找报警信息
	 * @param parseParams
	 * @return
	 */
	List<DeviceAlarm> findDeviceAlarm(Map<String, Object> parseParams);
	
	/**
	 * 查找服务器状态报警信息
	 * @param parseParams
	 * @return
	 */
	List<DeviceAlarm> findServerStatusAlarm(Map<String, Object> parseParams);

	/**
	 * 查找报警状态
	 * @param alarmId
	 * @return
	 */
	List<DeviceAlarm> findDeviceAlarmStatusByAlarmId(@Param("alarmId")String alarmId);

	/**
	 * 更新签收状态
	 * @param alarmId
	 */
	void updateAlarmStatusToSign(Map params);

	/**
	 * 保存报警签收状态
	 * @param alarmId
	 */
	void saveAlarmStatus(Map params);

	/**
	 * 更新处理状态
	 * @param parseParams
	 */
	void updateAlarmStatusToHandle(Map<String, Object> parseParams);
	
	/**
	 * 保存处理信息
	 * @param parseParams
	 */
	void saveAlarmHandle(Map<String, Object> parseParams);

	/**
	 * 根据ID查找报警信息
	 * @param alarmId
	 * @return
	 */
	DeviceAlarm findAlarmById(@Param("alarmId")String alarmId);

	/**
	 * 根据ID查找处理列表
	 * @param alarmId
	 * @return
	 */
	List<DeviceAlarmHandle> findHandleByAlarmId(@Param("alarmId")String alarmId);

}
