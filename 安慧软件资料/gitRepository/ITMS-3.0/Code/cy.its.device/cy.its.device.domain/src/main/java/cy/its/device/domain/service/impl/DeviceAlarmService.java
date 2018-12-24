package cy.its.device.domain.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.device.domain.criteria.DeviceAlarmCriteria;
import cy.its.device.domain.model.DeviceAlarm;
import cy.its.device.domain.model.DeviceAlarmHandle;
import cy.its.device.domain.repository.IDeviceAlarmRepository;
import cy.its.device.domain.service.IDeviceAlarmService;

@Service
public class DeviceAlarmService implements IDeviceAlarmService {

	@Autowired
	private IDeviceAlarmRepository deviceAlarmRepository;
	
	/**
	 * 查找报警信息
	 */
	@Override
	public List<DeviceAlarm> findDeviceAlarm(DeviceAlarmCriteria criteria) {
		return deviceAlarmRepository.findDeviceAlarm(criteria);
	}

	/**
	 * 签收报警
	 */
	@Override
	public void signAlarm(String alarmId, String userId) {
		//增加报警状态记录
		deviceAlarmRepository.saveAlarmStatus(alarmId,userId,new Date());
		//发送mq，更新报警状态

	}

	@Override
	public DeviceAlarmHandle handleAlarm(DeviceAlarmHandle handle) {
		//增加报警处理记录
		deviceAlarmRepository.saveAlarmHandle(handle);
		//发送mq，更新报警状态
		return handle;
	}

	@Override
	public DeviceAlarm deviceAlarmOfId(String alarmId) {
		return deviceAlarmRepository.findAlarmById(alarmId);
	}

	@Override
	public List<DeviceAlarmHandle> findHandleByAlarmId(String alarmId) {
		return deviceAlarmRepository.findHandleByAlarmId(alarmId);
	}

}
