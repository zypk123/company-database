package cy.its.device.rest.action.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.types.resources.selectors.InstanceOf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cy.its.com.util.DateUtil;
import cy.its.com.util.ObjectMapUtils;
import cy.its.device.domain.criteria.LedProgTemplateCriteria;
import cy.its.device.domain.model.led.Led;
import cy.its.device.domain.model.led.LedPowerGroup;
import cy.its.device.domain.model.led.LedProgCycle;
import cy.its.device.domain.model.led.LedProgEmergency;
import cy.its.device.domain.model.led.LedProgGrab;
import cy.its.device.domain.model.led.LedProgTemplate;
import cy.its.device.domain.model.led.LedProgTimed;
import cy.its.device.domain.model.led.LedTask;
import cy.its.device.domain.model.led.LedTaskAuto;
import cy.its.device.domain.model.led.LedTaskManual;
import cy.its.device.domain.model.led.LedTimedParam;
import cy.its.device.domain.service.ILedService;
import cy.its.device.domain.service.impl.SystemService;
import cy.its.device.rest.action.ILedInfoReleaseAction;
import cy.its.device.rest.dto.LedTaskDto;
import cy.its.device.rest.dto.LedTaskRelaseSearchDto;
import cy.its.platform.common.exception.ItmsAppException;
import cy.its.service.common.StringUtil;

@RequestMapping("/device/ledInfoRelease")
@RestController
public class LedInfoReleaseAction implements ILedInfoReleaseAction {

	@Autowired
	ILedService iLedService;
	
	@Autowired
	SystemService systemService;
	/**
	 * 查询节目列表
	 */
	@RequestMapping("/playingTasksOfLed")
	@Override
	public List<LedTask> playingTasksOfLed(LedTaskRelaseSearchDto form) throws Exception {
		if(StringUtil.isNullOrEmpty(form.getDeviceId())){
			throw new ItmsAppException("要查询的设备不存在");
		}
		return iLedService.playingTasksOfLed(form.getDeviceId());
	}
	
	@RequestMapping("/setLedPower")
	@Override
	public boolean setLedPower(LedTaskRelaseSearchDto form) throws Exception {
		if(StringUtil.isNullOrEmpty(form.getDeviceId())){
			throw new ItmsAppException("设备不存在");
		}
		boolean onOrOff;
		/**
		 * 1,开屏 2,关屏
		 */
		if("1".equals(form.getOnOrOff())){
			onOrOff =true;
		}else if("2".equals(form.getOnOrOff())){
			onOrOff =false;
		}else{
			throw new ItmsAppException("请设置正确的开关键:1,开屏 2,关屏");
		}
		return iLedService.setLedPower(form.getDeviceId(),onOrOff);
	}
	@RequestMapping("/setLedPowerTimed")
	@Override
	public boolean setLedPowerTimed(String deviceId, List<LedPowerGroup> lstPowerGroup) throws Exception {
		return iLedService.setLedPowerTimed(deviceId, lstPowerGroup);
	}
//	
//	@RequestMapping("/setLedBrightness")
//	@Override
//	public boolean setLedBrightness(LedTaskRelaseSearchDto form) throws Exception {
//		if(StringUtil.isNullOrEmpty(form.getDeviceId())){
//			throw new ItmsAppException("设备不存在");
//		}
//		if(StringUtil.isNullOrEmpty(form.getLightLevel())){
//			throw new ItmsAppException("设备亮度级别不存在");
//		}
//		int lightLevel = Integer.valueOf(form.getLightLevel());
//		return iLedService.setLedBrightness(form.getDeviceId(),lightLevel);
//	}
	
	@RequestMapping("/findProgTemplates")
	@Override
	public List<LedProgTemplate> findProgTemplates(LedProgTemplateCriteria criteria) {
		return iLedService.findProgTemplates(criteria);
	}

	@RequestMapping("/createProgTemplate")
	@Override
	public void createProgTemplate(LedTaskRelaseSearchDto form) throws Exception {
		LedProgTemplate ProgTemplate  = new LedProgTemplate();
		ObjectMapUtils.parseObject(ProgTemplate, form);
		iLedService.createProgTemplate(ProgTemplate);
	}
	
	@RequestMapping("/updateProgTemplate")
	@Override
	public void updateProgTemplate(LedTaskRelaseSearchDto form) throws Exception {
		LedProgTemplate ProgTemplate  = new LedProgTemplate();
		ObjectMapUtils.parseObject(ProgTemplate, form);
		iLedService.updateProgTemplate(ProgTemplate);
		//LedProgTemplate newledProgTemplate = iLedService.fin
	}

	@RequestMapping("/removeProgTemplate")
	@Override
	public int removeProgTemplate(LedTaskRelaseSearchDto form) throws Exception {
		if(StringUtil.isNullOrEmpty(form.getProgTemplateId())){
			throw new ItmsAppException("要删除的模板不存在");
		}
		iLedService.removeProgTemplate(form.getProgTemplateId());
		return 0;
	}
	
	/**
	 * 查看视频
	 */
	@RequestMapping("/seeVoideo")
	@Override
	public String seeVoideo(LedTaskRelaseSearchDto form) throws Exception {
		if(StringUtil.isNullOrEmpty(form.getDeviceId())){
			return null;
		}
		String deviceIds[] = form.getDeviceId().split(",");
		StringBuffer sbBuffer = new StringBuffer();
		for (int i = 0; i < deviceIds.length; i++) {
			Led led =  systemService.ledOfId(deviceIds[i]);
			String deviceId =led.getRelatedVideoId();
			if(i+1 == deviceIds.length){
				sbBuffer.append(deviceId);
			}else{
				sbBuffer.append(deviceId).append(",");
			}
		}
		return sbBuffer.toString();
	}
	/**
	 * 排序节目列表 List<LedTaskDto>
	 */
	@RequestMapping("/sortTaskList")
	@Override
	public int sortTaskList(String deviceId,List<LedTaskDto> ledTaskDtoList) throws Exception {
		//List<LedTaskDto> newLedTaskDtoList = new ArrayList<LedTaskDto>();
		List<String> lstTaskId = new ArrayList<String>();
		for (LedTaskDto ledTaskDto : ledTaskDtoList) {
			//newLedTaskDtoList.add(ledTaskDto);
			lstTaskId.add(ledTaskDto.taskId);
		}
		if(StringUtil.isNullOrEmpty(deviceId)){
			throw new ItmsAppException("设备不存在");
		}
		iLedService.sortTask(deviceId, lstTaskId);
		return 0;
	}

	/**
	 * 修改节目列表
	 */
	@RequestMapping("/changeTaskList")
	@Override
	public int changeTaskList(LedTaskRelaseSearchDto form) throws Exception {
		// 添加创建人
		String taskType = form.getTaskType();
		if (StringUtil.isNullOrEmpty(taskType)) {
			throw new ItmsAppException("没有指定任务类型");
		}
		LedTask ledTask = null;
		String taskId = form.getTaskId();

		LedTaskManual ledTaskManual = null;
		LedTaskAuto ledTaskAuto = null;
		switch (taskType) {
		case "10":// 日常轮播
			ledTaskManual = new LedTaskManual();
			LedProgCycle ledProgCycle = new LedProgCycle();
			ledProgCycle.setPlayTime(Short.valueOf(form.getPlayTime()));
			ledProgCycle.setPlayTimes(Short.valueOf(form.getPlayTimes()));
			ObjectMapUtils.parseObject(ledProgCycle, form);
			ledTaskManual.setLedProg(ledProgCycle);
			ObjectMapUtils.parseObject(ledTaskManual, form);
			ledTask = ledTaskManual;
			break;
		case "11":// 定时任务
			ledTaskManual = new LedTaskManual();
			LedProgTimed ledProgTimed = new LedProgTimed();
			LedTimedParam ledTimedParam = new LedTimedParam(form.getStartDate(), form.getEndDate(), form.getStartTime(),
					form.getEndTime(), form.getWeek());
			ObjectMapUtils.parseObject(ledProgTimed, form);
			ObjectMapUtils.parseObject(ledTimedParam, form);
			ledProgTimed.setLedTimedParam(ledTimedParam);
			ledTaskManual.setLedProg(ledProgTimed);
			ledTask = ledTaskManual;
			break;
		case "12":// 插播任务
			ledTaskManual = new LedTaskManual();
			LedProgGrab ledProg = new LedProgGrab();
			ObjectMapUtils.parseObject(ledProg, form);
			ledProg.setPlayDelay(Short.valueOf(form.getPlayDelay()));
			ledProg.setPlayTime(Short.valueOf(form.getPlayTime()));
			ledProg.setPlayTimes(Short.valueOf(form.getPlayTimes()));
			ledTaskManual.setLedProg(ledProg);
			ledTask = ledTaskManual;
			break;
		case "13":// 紧急任务
			ledTaskManual = new LedTaskManual();
			LedProgEmergency ledProgEmergency = new LedProgEmergency();
			ObjectMapUtils.parseObject(ledProgEmergency, form);
			ledTaskManual.setLedProg(ledProgEmergency);
			ledTask = ledTaskManual;
			break;
		case "20":// 违法自动发布
			ledTaskAuto = new LedTaskAuto();
			ObjectMapUtils.parseObject(ledTaskAuto, form);
			LedTimedParam ledTimedParamAuto = new LedTimedParam(form.getStartDate(), form.getEndDate(),
					form.getStartTime(), form.getEndTime(), form.getWeek());
			LedProgGrab ledProgGrad = new LedProgGrab();
			ledProgGrad.setPlayDelay(Short.valueOf(form.getPlayDelay()));
			ledProgGrad.setPlayTime(Short.valueOf(form.getPlayTime()));
			ledProgGrad.setPlayTimes(Short.valueOf(form.getPlayTimes()));
			ledTaskAuto.setLedTimedParam(ledTimedParamAuto);
			ledTaskAuto.setLedProgGrad(ledProgGrad);
			ledTask = ledTaskManual;
			break;
		default:
			break;
		}

		if (ledTask != null) {
			if (StringUtil.isNullOrEmpty(taskId)) {// 创建
				ledTask.setCreator(form.getCurrentUserName());
				if (ledTask instanceof LedTaskManual) {
					iLedService.createManualTask(ledTaskManual);
				} else {
					iLedService.createAutoTask(ledTaskAuto);
				}
			} else {
				iLedService.updateLedTask(ledTask);
			}
		}

		return 0;
	}
	
	/**
	 * 删除任务列表
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/removeLedTask")
	@Override
	public int removeLedTask(LedTaskRelaseSearchDto form) throws Exception {
		String ids[] = form.getTaskId().split(",");
		for (int i = 0; i < ids.length; i++) {
			iLedService.removeLedTask(ids[i]);
		}
		return 0;
	}
}
