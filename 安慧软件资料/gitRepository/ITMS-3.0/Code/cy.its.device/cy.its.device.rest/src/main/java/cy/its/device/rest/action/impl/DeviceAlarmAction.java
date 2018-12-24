package cy.its.device.rest.action.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cy.its.device.domain.criteria.DeviceAlarmCriteria;
import cy.its.device.domain.model.DeviceAlarm;
import cy.its.device.domain.service.IDeviceAlarmService;
import cy.its.device.rest.action.IDeviceAlarmAction;
import cy.its.device.rest.dto.AlarmQueryDto;

/**
 * 报警处理restAction
 * 
 * @author dell
 */
@RestController
@RequestMapping("/device/deviceAlarm")
public class DeviceAlarmAction implements IDeviceAlarmAction {

	@Autowired
	private IDeviceAlarmService deviceAlarmService;

	/**
	 * 查找设备类报警信息
	 * 
	 * @return
	 */
	@RequestMapping("/findAlarmInfo")
	@Override
	public Map findAlarmInfo(@ModelAttribute("dto") AlarmQueryDto dto) {
		DeviceAlarmCriteria criteria = dto.convertToCriteria();
		List<DeviceAlarm> alarmList = deviceAlarmService.findDeviceAlarm(criteria);
		
		Map result = new HashMap();
		Map rows = new HashMap();
		result.put("error", "");
		result.put("result", rows);
		rows.put("total", criteria.getTotal());
		rows.put("rows", alarmList);
		return result;
	}

	@Override
	public String alarmSign(String alarmId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String handle(String alarmId) {
		// TODO Auto-generated method stub
		return null;
	}
}
