package cy.its.device.domain.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.LedContentLibCriteria;
import cy.its.device.domain.criteria.LedProgTemplateCriteria;
import cy.its.device.domain.criteria.LedPublishLogCriteria;
import cy.its.device.domain.criteria.LedSpecCriteria;
import cy.its.device.domain.criteria.LedTaskCriteria;
import cy.its.device.domain.model.Sys;
import cy.its.device.domain.model.led.LedContentLib;
import cy.its.device.domain.model.led.LedControlParam;
import cy.its.device.domain.model.led.LedField;
import cy.its.device.domain.model.led.LedSpec;
import cy.its.device.domain.model.led.LedTask;
import cy.its.device.domain.model.led.LedTaskAuto;
import cy.its.device.domain.model.led.LedTaskManual;
import cy.its.device.domain.model.led.ReponseResult;
import cy.its.device.domain.model.led.LedPowerGroup;
import cy.its.device.domain.model.led.LedProg;
import cy.its.device.domain.model.led.LedProgNoChange;
import cy.its.device.domain.model.led.LedProgTemplate;
import cy.its.device.domain.model.led.LedPublishLog;
import cy.its.device.domain.repository.ISysRepository;
import cy.its.device.domain.repository.led.IContentLibRepository;
import cy.its.device.domain.repository.led.ILedControlRepository;
import cy.its.device.domain.repository.led.ILedOtherRepository;
import cy.its.device.domain.repository.led.ILedProgControlRepository;
import cy.its.device.domain.repository.led.ILedProgRepository;
import cy.its.device.domain.repository.led.ILedProgTempRepository;
import cy.its.device.domain.repository.led.ILedRemoteManageRepository;
import cy.its.device.domain.repository.led.ILedSpecRepository;
import cy.its.device.domain.repository.led.ILedTaskRepository;
import cy.its.device.domain.service.ILedService;
import cy.its.device.domain.service.ISurveySystemService;

@Service
public class LedService implements ILedService {

	@Autowired
	IContentLibRepository contentLibRepository;

	@Autowired
	ISurveySystemService surveySystemService;

	@Autowired
	ILedRemoteManageRepository ledRemoteManageRepository;

	@Autowired
	ILedSpecRepository ledSpecRepository;

	@Autowired
	ILedProgRepository ledProgRepository;

	@Autowired
	ILedProgTempRepository ledProgTempRepository;

	@Autowired
	ISysRepository sysRepository;

	@Autowired
	ILedControlRepository ledControlRepository;

	@Autowired
	ILedOtherRepository ledOtherRepository;

	@Autowired
	ILedTaskRepository ledTaskRepository;

	@Autowired
	ILedProgControlRepository ledProgControlRepository;

	/**
	 * 同一设备下节目移动
	 * 
	 * @param deviceId
	 * @param taskId1
	 * @param taskId2
	 * @throws Exception
	 */
	public void swapTask(String deviceId, String taskId1, String taskId2) throws Exception {
		LedTaskCriteria c = new LedTaskCriteria();
		c.taskIdArr = new String[] { taskId1, taskId2 };
		List<LedTask> taskLst = ledTaskRepository.findLedTask(c);

		if (taskLst.size() == 2) {
			LedProg ledProg1 = taskLst.get(0).ledProgOfTask();
			LedProg ledProg2 = taskLst.get(1).ledProgOfTask();
			String progId1 = ledProg1.getProgramId();
			String progNo1 = ledProg1.getProgramNo();
			String progId2 = ledProg2.getProgramId();
			String progNo2 = ledProg2.getProgramNo();
			LedControlParam param = getLedControlParams(deviceId);
			ledProgRepository.updateProgNo(progId1, progNo1, progNo2);
			ledProgRepository.updateProgNo(progId2, progNo2, progNo1);

			ledProgControlRepository.swapProgram(param.getLedSvrIp(), param.getDeviceSysNbr(), progNo1, progNo2);
		} else {
			throw new Exception("任务数不匹配");
		}

	}

	/**
	 * 创建自动任务
	 * 
	 * @param ledTask
	 * @throws Exception
	 */
	public void createAutoTask(LedTaskAuto ledAutoTask) throws Exception {
		ledAutoTask.check(sysRepository);
		ledAutoTask.generate(ledTaskRepository, ledProgRepository);
		ledTaskRepository.save(ledAutoTask);
	}

	/**
	 * 创建手动任务 √
	 * 
	 * @param ledTask
	 * @throws Exception
	 */
	public void createManualTask(LedTaskManual ledManualTask) throws Exception {
		ledManualTask.check(sysRepository);
		ledManualTask.generate(ledTaskRepository, ledProgRepository);

		ledTaskRepository.save(ledManualTask);

		// 发布节目至前端
		Date beginTime = new Date();
		LedControlParam params = getLedControlParams(ledManualTask.getLedProg().getDeviceId());
		ReponseResult respRslt = ledProgControlRepository.issueProgram(params.getLedSvrIp(), params.getDeviceSysNbr(),
				ledManualTask.getLedProg());
		if (respRslt.getSuccess()) {
			ledOtherRepository.savePublishLog(ledManualTask, params, respRslt, beginTime, new Date());
		} else {
			throw new Exception("节目下发失败," + respRslt.getResponseString());
		}
	}

	/**
	 * todo: 编辑任务
	 * 
	 * @param ledTask
	 * @throws Exception
	 */
	public void updateLedTask(LedTask ledTask) throws Exception {
		// todo: 注意手动任务和自动任务的区别
		ledTask.check(sysRepository);
//		ledTask.ledProgOfTask().setContentNo();
		ledTaskRepository.update(ledTask);

		if (ledTask instanceof LedTaskManual) {
			// 同步新节目至前端
			Date beginTime = new Date();
			LedControlParam params = getLedControlParams(ledTask.ledProgOfTask().getDeviceId());
			ReponseResult respRslt = ledProgControlRepository.modifyProgramInRemote(params.getLedSvrIp(),
					params.getDeviceSysNbr(), ledTask.ledProgOfTask());
			if (respRslt.getSuccess()) {
				ledOtherRepository.savePublishLog(ledTask, params, respRslt, beginTime, new Date());
			} else {
				throw new Exception("节目编辑失败," + respRslt.getResponseString());
			}
		}
	}

	/**
	 * 删除任务  √
	 * 
	 * @param ledTask
	 * @throws Exception
	 */
	public void removeLedTask(String taskId) throws Exception {
		// todo: 注意手动任务和自动任务的区别
		LedTask ledTask = ledTaskRepository.aggregateOfId(taskId);

		if (ledTask == null) {
			throw new Exception("任务不存在");
		}

		if (ledTask instanceof LedTaskManual) {
			ledTaskRepository.delete(taskId);
			removeProgInRemote(ledTask.ledProgOfTask());
		} else {
			LedTaskAuto autoTask = (LedTaskAuto) ledTask;
			if (autoTask.isInEnabledStatus()) {
				throw new Exception("自动任务处于启用中, 禁止删除");
			}

			ledTaskRepository.delete(taskId);
			if (autoTask.getIssueCount() > 0) {
				removeProgInRemote(ledTask.ledProgOfTask());
			}
		}
	}

	//public void 
	
	private void removeProgInRemote(LedProg ledProg) throws Exception {
		LedControlParam p = this.getLedControlParams(ledProg.getDeviceId());
		// 当前任务的节目曾经下发到前端上
		ReponseResult respRslt = ledProgControlRepository.removeProgramInRemote(p.getLedSvrIp(), p.getDeviceSysNbr(),
				Integer.parseInt(ledProg.getProgramNo()));

		if (!respRslt.getSuccess()) {
			throw new Exception("无法删除任务,因为清除其在前端的节目失败," + respRslt.getResponseString());
		}
	}

	/**
	 * 查找符合条件的任务（含自动和人工）  √
	 * 
	 * @param criteria
	 * @return
	 */
	public List<LedTask> findLedTask(LedTaskCriteria criteria) {
		if(criteria.getNeedTotal()){
			criteria.setTotal(ledTaskRepository.countLedTask(criteria));
		}
		return ledTaskRepository.findLedTask(criteria);
	}

	/**
	 * todo:获取指定设备正在播放的任务（含自动和人工） 
	 * 
	 * @param deviceId
	 * @return
	 * @throws Exception
	 */
	public List<LedTask> playingTasksOfLed(String deviceId) throws Exception {
		LedControlParam p = getLedControlParams(deviceId);
		String[] progNoArr = ledProgControlRepository.getPlayingProgNoArr(p.getLedSvrIp(), p.getDeviceSysNbr());
		if(progNoArr == null) {
			return null;
		}
		LedTaskCriteria c = new LedTaskCriteria();
		c.deviceId = deviceId;
		c.programNoArr = progNoArr;
		return ledTaskRepository.findLedTask(c);
	}

	
	public void repubManualTask(String taskId) throws Exception{
		LedTask task = ledTaskRepository.aggregateOfId(taskId);
		if(task instanceof LedTaskAuto){
			throw new Exception("自动任务无法进行重发操作");
		}
		
		LedTaskManual ledManualTask = (LedTaskManual)task;
		
		// 发布节目至前端
		Date beginTime = new Date();
		LedControlParam params = getLedControlParams(ledManualTask.getLedProg().getDeviceId());
		ReponseResult respRslt = ledProgControlRepository.modifyProgramInRemote(params.getLedSvrIp(), params.getDeviceSysNbr(),
				ledManualTask.getLedProg());
		if (respRslt.getSuccess()) {
			ledOtherRepository.savePublishLog(ledManualTask, params, respRslt, beginTime, new Date());
		} else {
			throw new Exception("节目下发失败," + respRslt.getResponseString());
		}
	}
	
	/**
	 * 启用自动任务  √
	 * 
	 * @param taskId
	 * @throws Exception
	 */
	public void enableAutoTask(String taskId) throws Exception {
		LedTask task = ledTaskRepository.aggregateOfId(taskId);
		if (task instanceof LedTaskManual) {
			throw new Exception("人工任务无法进行启用操作");
		}

		LedTaskAuto auto = (LedTaskAuto) task;
		// 启用
		auto.enable();
		ledTaskRepository.update(auto);
	}

	/**
	 * 停用自动任务  √
	 * 
	 * @param taskId
	 * @throws Exception
	 */
	public void disableAutoTask(String taskId) throws Exception {
		LedTask task = ledTaskRepository.aggregateOfId(taskId);
		if (task instanceof LedTaskManual) {
			throw new Exception("人工任务无法进行停用操作");
		}

		LedTaskAuto auto = (LedTaskAuto) task;
		// 停用
		auto.disable();
		ledTaskRepository.update(auto);
	}

	/**
	 * 获取指定的任务明细  √
	 * 
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	public LedTask ledTaskOfId(String taskId) throws Exception {
		return ledTaskRepository.aggregateOfId(taskId);
	}

	/**
	 * 创建节目模板  √
	 * 
	 * @param ledProgTemplate
	 * @throws Exception
	 */
	public void createProgTemplate(LedProgTemplate ledProgTemplate) throws Exception {
		ledProgTemplate.checkRepeatName(ledProgTempRepository);
		ledProgTemplate.setProgTemplateId(StringUtil.generateUUID());
		ledProgTempRepository.save(ledProgTemplate);
	}

	/**
	 * 查询节目模板列表（条件： 规格、无条件等） √
	 * 
	 * @param ledProgTemplate
	 * @return
	 */
	public List<LedProgTemplate> findProgTemplates(LedProgTemplateCriteria criteria) {
		return ledProgTempRepository.findLedProgTemplates(criteria);
	}

	/**
	 * 编辑节目模板  √
	 * 
	 * @param ledProgTemplate
	 * @throws Exception
	 */
	public void updateProgTemplate(LedProgTemplate ledProgTemplate) throws Exception {
		ledProgTemplate.checkRepeatName(ledProgTempRepository);
		ledProgTempRepository.update(ledProgTemplate);
	}

	/**
	 * 删除节目模板  √
	 * 
	 * @param progTemplateId
	 */
	public void removeProgTemplate(String progTemplateId) {
		ledProgTempRepository.delete(progTemplateId);
	}

	/**
	 * 获取所有动态字段配置
	 * 
	 * @return
	 */
	public List<LedField> allLedFields() {
		return ledOtherRepository.allLedFields();
	}

	/**
	 * 查询发布日志记录  √
	 * 
	 * @param criteria
	 * @return
	 */
	public List<LedPublishLog> findLedPublishLogs(LedPublishLogCriteria criteria) {
		if (criteria.getNeedTotal()) {
			criteria.setTotal(ledOtherRepository.countLedPublishLogs(criteria));
		}

		return ledOtherRepository.findLedPublishLogs(criteria);
	}

	/**
	 * 创建发布日志记录  √
	 * 
	 * @param ledPublishLog
	 */
	public void saveLedPublishLog(LedPublishLog ledPublishLog) {
		ledOtherRepository.createLedPublishLog(ledPublishLog);
	}

	/**
	 * 手动开关屏  √
	 * 
	 * @param deviceNo
	 * @param onOrOff
	 * @throws Exception
	 */
	public boolean setLedPower(String deviceId, boolean onOrOff) throws Exception {
		LedControlParam p = this.getLedControlParams(deviceId);
		return ledControlRepository.setLedPower(p.getLedSvrIp(), p.getDeviceSysNbr(), onOrOff);
	}

	/**
	 * 定时开关屏  √
	 * 
	 * @param deviceId
	 * @param lstPowerGroup
	 * @return
	 * @throws Exception
	 */
	public boolean setLedPowerTimed(String deviceId, List<LedPowerGroup> lstPowerGroup) throws Exception {
		if (lstPowerGroup == null || lstPowerGroup.size() == 0) {
			throw new Exception("开关屏定时参数不能为空");
		}

		LedControlParam p = this.getLedControlParams(deviceId);
		return ledControlRepository.setLedPowerTimed(p.getLedSvrIp(), p.getDeviceSysNbr(), lstPowerGroup);
	}

//	/**
//	 * 调节LED屏亮度
//	 * 
//	 * @param deviceId
//	 * @param brightness
//	 * @return
//	 * @throws Exception
//	 */
//	public boolean setLedBrightness(String deviceId, int brightness) throws Exception {
//		if (brightness < 0 || brightness > 16) {
//			throw new Exception("亮度值应在1-16之间");
//		}
//
//		LedControlParam p = this.getLedControlParams(deviceId);
//		return ledControlRepository.setLedBrightness(p.getLedSvrIp(), p.getDeviceSysNbr(), brightness);
//	}
//
//	/**
//	 * LED设备校时
//	 * 
//	 * @param deviceId
//	 * @return
//	 * @throws Exception
//	 */
//	public boolean syncLedTime(String deviceId) throws Exception {
//		LedControlParam p = this.getLedControlParams(deviceId);
//		return ledControlRepository.syncLedTime(p.getLedSvrIp(), p.getDeviceSysNbr());
//	}

	/**
	 * 增加诱导屏规格  √
	 * 
	 * @param ledSpec
	 * @throws Exception
	 */
	public void createLedSpec(LedSpec ledSpec) throws Exception {
		ledSpec.checkRepeatName(ledSpecRepository);
		ledSpec.setSpecId(StringUtil.generateUUID());
		ledSpecRepository.save(ledSpec);
	}

	/**
	 * 修改诱导屏规格  √
	 * 
	 * @param ledSpec
	 * @throws Exception
	 */
	public void updateLedSpec(LedSpec ledSpec) throws Exception {
		ledSpec.checkForCanChanged(sysRepository, ledProgTempRepository);
		ledSpec.checkRepeatName(ledSpecRepository);
		ledSpecRepository.update(ledSpec);
	}

	/**
	 * 删除诱导屏规格  √
	 * 
	 * @param specId
	 * @throws Exception
	 */
	public void removeLedSpec(String specId) throws Exception {
		LedSpec ledSpec = ledSpecRepository.aggregateOfId(specId);
		ledSpec.checkForCanChanged(sysRepository, ledProgTempRepository);
		ledSpec.checkRepeatName(ledSpecRepository);
		ledSpecRepository.delete(specId);
	}

	/**
	 * 查询指定的诱导屏规格  √
	 * 
	 * @param specId
	 * @return
	 * @throws Exception
	 */
	public LedSpec ledSpecOfId(String specId) throws Exception {
		return ledSpecRepository.aggregateOfId(specId);
	}

	/**
	 * 查询诱导屏规格列表 √
	 * 
	 * @param criteria
	 * @return
	 */
	public List<LedSpec> findLedSpecs(LedSpecCriteria criteria) {
		if (criteria.getNeedTotal()) {
			criteria.setTotal(ledSpecRepository.countLedSpecs(criteria));
		}

		return ledSpecRepository.findLedSpecs(criteria);
	}

	/**
	 * 创建内容库  √
	 * 
	 * @param contentlib
	 * @param creator
	 * @throws Exception
	 */
	public void createContentLib(LedContentLib contentlib, String creator) throws Exception {
		if (contentlib == null) {
			throw new Exception("节目内容空为空");
		}

		contentlib.setContentId(StringUtil.generateUUID());
		contentlib.saveCreator(creator);
		contentLibRepository.save(contentlib);
	}

	/**
	 * 修改内容库  √
	 * 
	 * @param contentlib
	 * @param modifier
	 * @throws Exception
	 */
	public void updateContentLib(LedContentLib contentlib, String modifier) throws Exception {

		if (contentlib == null) {
			throw new Exception("节目内容空为空");
		}

		contentlib.modifyUser(modifier);

		contentLibRepository.update(contentlib);
	}

	/**
	 * 查询指定的内容库  √
	 * 
	 * @param contentId
	 * @return
	 * @throws Exception
	 */
	public LedContentLib contentLibOfId(String contentId) throws Exception {
		return contentLibRepository.aggregateOfId(contentId);
	}

	/**
	 * 查询符合条件的内容库  √
	 * 
	 * @param criteria
	 * @return
	 */
	public List<LedContentLib> findContentLibs(LedContentLibCriteria criteria) {
		if (criteria.getNeedTotal()) {
			criteria.setTotal(contentLibRepository.countContentLibs(criteria));
		}

		return contentLibRepository.findContentLibs(criteria);
	}

	/**
	 * 删除内容库  √
	 * 
	 * @param contentId
	 * @throws Exception
	 */
	public void removeContentLib(String contentId) throws Exception {
		if (StringUtil.isNullOrEmpty(contentId)) {
			throw new Exception("未指定删除节目内容的唯一性标识");
		}

		contentLibRepository.delete(contentId);
	}

	/**
	 * 获取LED控制参数  √
	 * 
	 * @param deviceId
	 * @return
	 * @throws Exception
	 */
	private LedControlParam getLedControlParams(String deviceId) throws Exception {
		Sys sys = sysRepository.aggregateOfId(deviceId);
		if (sys == null) {
			throw new Exception("当前LED设备不存在");
		}

		if (!sys.isInUseStatus()) {
			throw new Exception("当前LED设备未处于启用状态,禁止发送控制指令");
		}

		if (StringUtil.isNullOrEmpty(sys.getServerPlatId())) {
			throw new Exception("当前LED设备未配置接入平台");
		}

		String ledSvrIp = surveySystemService.serverIpOfPlatId(sys.getServerPlatId(), "1");
		return new LedControlParam(ledSvrIp, sys.getDeviceSysNbr(), sys.getOrgPrivilegeCode());
	}

	@Override
	public void sortTask(String deviceId, List<String> lstTaskId) throws Exception {
		// 查询旧的节目列表, 按节目序号排序
		LedTaskCriteria c = new LedTaskCriteria();
		c.taskIdArr = lstTaskId.toArray(new String[0]);
		List<LedTask> lstTask = findLedTask(c);
		if (lstTask.size() == lstTaskId.size()) {
			// 获取要排序任务的原节目序号顺序列表
			List<Integer> lstProgNoAsc = lstTask.stream().map(t -> Integer.parseInt(t.ledProgOfTask().getProgramNo()))
					.sorted().collect(Collectors.toList());
			// 任务ID_任务
			Map<String, LedTask> mapIdTask = lstTask.stream().collect(Collectors.toMap(LedTask::getTaskId, t -> t));
			// 原节目序号与新节目序号映射关系
			Map<Integer, Integer> oldNewProgNoMap = new LinkedHashMap<Integer, Integer>();
			List<LedProgNoChange> lstChange =  new ArrayList<LedProgNoChange>();
			for (int i = 0; i < lstTaskId.size(); i++) {
				Integer oldProgNo = Integer.parseInt(mapIdTask.get(lstTaskId.get(i)).ledProgOfTask().getProgramNo());
				Integer newProgNo = lstProgNoAsc.get(i);
				String progId = mapIdTask.get(lstTaskId.get(i)).ledProgOfTask().getProgramId();
				oldNewProgNoMap.put(oldProgNo, newProgNo);
				lstChange.add(new LedProgNoChange(progId, String.valueOf(oldProgNo), String.valueOf(newProgNo)));				
			}
			ledProgRepository.updateProgNoBatch(lstChange);
			LedControlParam param = this.getLedControlParams(deviceId);
			ledProgControlRepository.changeProgNoSeq(param.getLedSvrIp(), param.getDeviceSysNbr(), oldNewProgNoMap);
		} else {
			throw new Exception("任务数不匹配");
		}
	}
}


