package cy.its.device.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.DeviceAlarmCriteria;
import cy.its.device.domain.model.DeviceAlarm;
import cy.its.device.domain.model.DeviceAlarmHandle;
import cy.its.device.domain.repository.IDeviceAlarmRepository;
import cy.its.device.mybatis.client.DeviceAlarmMapper;
import cy.its.platform.common.utils.SqlHelper;

@Service
public class DeviceAlarmRepository implements IDeviceAlarmRepository {

	@Autowired
	DeviceAlarmMapper deviceAlarmMapper;

	@Override
	public List<DeviceAlarm> findDeviceAlarm(DeviceAlarmCriteria criteria) {
		// 分页查找
		PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
		PageHelper.orderBy(" start_time desc ");
		Page<DeviceAlarm> page = null;
		if("1".equals(criteria.getAlarmType())){
			page = (Page<DeviceAlarm>) deviceAlarmMapper.findDeviceAlarm(ParamUtil.parseParams(criteria));
		}else if("2".equals(criteria.getAlarmType())){
			page = (Page<DeviceAlarm>) deviceAlarmMapper.findServerStatusAlarm(ParamUtil.parseParams(criteria));
		}
		criteria.setTotal(page.getTotal());
		return page.getResult();
	}

	@Override
	public void saveAlarmStatus(String alarmId, String userId, Date handleDate) {
		Map paramMap = new HashMap();
		paramMap.put("alarmId", alarmId);
		paramMap.put("userId", userId);
		paramMap.put("acceptDate", handleDate);
		// 查找记录是否存在
		List<DeviceAlarm> alarmStatus = deviceAlarmMapper.findDeviceAlarmStatusByAlarmId(alarmId);
		if (alarmStatus.size() > 0) {
			// 状态记录已经存在，更新状态记录
			deviceAlarmMapper.updateAlarmStatusToSign(paramMap);
		} else {
			// 保存签收状态
			deviceAlarmMapper.saveAlarmStatus(paramMap);
		}
	}

	@Override
	public void saveAlarmHandle(DeviceAlarmHandle handle) {
		// 查找记录是否存在
		List<DeviceAlarm> alarmStatus = deviceAlarmMapper.findDeviceAlarmStatusByAlarmId(handle.getAlarmId());
		if (alarmStatus.size() == 0) {
			Map paramMap = new HashMap();
			paramMap.put("alarmId", handle.getAlarmId());
			paramMap.put("userId", handle.getHandlePerson());
			paramMap.put("acceptDate", handle.getHandleTime());
			// 状态记录不存在，说明未签收，先签收
			deviceAlarmMapper.saveAlarmStatus(paramMap);
		}
		// 状态记录已经存在，更新状态记录
		deviceAlarmMapper.updateAlarmStatusToHandle(ParamUtil.parseParams(handle));
		// 添加处理信息
		deviceAlarmMapper.saveAlarmHandle(ParamUtil.parseParams(handle));
	}

	@Override
	public DeviceAlarm findAlarmById(String alarmId) {
		return deviceAlarmMapper.findAlarmById(alarmId);
	}

	@Override
	public List<DeviceAlarmHandle> findHandleByAlarmId(String alarmId) {
		return deviceAlarmMapper.findHandleByAlarmId(alarmId);
	}

}
