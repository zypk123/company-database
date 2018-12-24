package cy.its.device.rest.action.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cy.its.com.util.DateUtil;
import cy.its.com.util.ObjectMapUtils;
import cy.its.device.domain.criteria.LedCriteria;
import cy.its.device.domain.criteria.LedTaskCriteria;
import cy.its.device.domain.model.Sys;
import cy.its.device.domain.model.led.LedSys;
import cy.its.device.domain.model.led.LedTask;
import cy.its.device.domain.model.led.LedTaskAuto;
import cy.its.device.domain.model.led.LedTaskManual;
import cy.its.device.domain.service.ILedService;
import cy.its.device.domain.service.impl.SystemService;
import cy.its.device.rest.action.IReleaseTaskManagerAction;
import cy.its.device.rest.dto.LedTaskDto;
import cy.its.device.rest.dto.LedTaskSearchDto;
import cy.its.device.rest.dto.TreeDto;
import cy.its.service.common.StringUtil;

@RequestMapping("/device/IReleaseTaskManager")
@RestController
public class ReleaseTaskManagerAction implements IReleaseTaskManagerAction {

	@Autowired
	private ILedService ledService;
	@Autowired
	private SystemService systemService;

	@RequestMapping("/findReleaseTaskList")
	@Override
	public Map<String, Object> findReleaseTaskList(LedTaskSearchDto form) throws Exception {
		LedTaskCriteria ledTaskcriteria = new LedTaskCriteria();
		// 设置当前页数
		ledTaskcriteria.setPageNum(form.getPageNumber());
		// 设置每页最大显示个数
		ledTaskcriteria.setPageSize(form.getPageSize());
		// 是否需要统计总数
		ledTaskcriteria.setNeedTotal(true);

		if (!StringUtil.isNullOrEmpty(form.getCurrentOrgPrivilegeCode())) {
			ledTaskcriteria.orgPrivilegeCode = form.getCurrentOrgPrivilegeCode();
		}
		if (!StringUtil.isNullOrEmpty(form.getDeviceId())) {
			ledTaskcriteria.deviceId = form.getDeviceId();
		}
		if (!StringUtil.isNullOrEmpty(form.getRoadId())) {
			ledTaskcriteria.roadId = form.getRoadId();
		}
		if (form.getIssueStatusArrs() != null && form.getIssueStatusArrs() !="") {
			ledTaskcriteria.issueStatusArr = form.getIssueStatusArrs().split(",");
		}

		if (!StringUtil.isNullOrEmpty(form.getMessageType())) {
			ledTaskcriteria.messageType = form.getMessageType();
		}
		if (!StringUtil.isNullOrEmpty(form.getCreateTimeFrom())) {
			ledTaskcriteria.createTimeFrom = DateUtil.parseDate(form.getCreateTimeFrom());
		}

		if (!StringUtil.isNullOrEmpty(form.getCreateTimeTo())) {
			ledTaskcriteria.createTimeTo = DateUtil.parseDate(form.getCreateTimeTo());
		}

		if (!StringUtil.isNullOrEmpty(form.getTaskType())) {
			ledTaskcriteria.taskType = form.getTaskType();
		}
		List<LedTask> ledTaskList = ledService.findLedTask(ledTaskcriteria);
		Map<String, Sys> sysMap = getSysMap();// TODO
		// 建立接收数据集合的前台包装集合
		ArrayList<LedTaskDto> ltDtoList = new ArrayList<LedTaskDto>();
		Sys sys;
		for (LedTask lt : ledTaskList) {
			LedTaskDto ldDto = new LedTaskDto();
			ObjectMapUtils.parseObject(ldDto, lt);
			ldDto.deviceId = lt.ledProgOfTask().getDeviceId();
			ldDto.timeString = lt.getTimeString();
			sys = sysMap.get(ldDto.deviceId);
			ldDto.createTime = DateUtil.formatDate(lt.getCreateTime());
			ldDto.deviceName = (sys != null ? sys.getDeviceName() : null);
			if (lt instanceof LedTaskAuto) {// 如果当前为自动发布任务，取自动发布任务的内容
				LedTaskAuto ledTaskAuto = (LedTaskAuto) lt;
				ldDto.enableFlag = ledTaskAuto.getEnableFlag();
				// 获得消息类型
				if (ledTaskAuto.getLedProgGrad() != null) {
					ldDto.messageType = (ledTaskAuto.getLedProgGrad().getMessageType());
				}
			} else if (lt instanceof LedTaskManual) {// 当前为手动发布
				LedTaskManual ledTaskManual = (LedTaskManual) lt;
				// ldDto.createTimeFrom=ledTaskManual.getIssueStatus();
				// 获得消息类型
				if (ledTaskManual.getLedProg() != null) {
					ldDto.messageType = (ledTaskManual.getLedProg().getMessageType());
				}
			}

			ltDtoList.add(ldDto);
		}
		List<LedSys> lst = systemService.findLedSyses(new LedCriteria());

		List<TreeDto> ydpList = new ArrayList<TreeDto>();
		TreeDto treeDto = new TreeDto();
		treeDto.setId("0");
		treeDto.setText("请选择");
		ydpList.add(treeDto);

		for (LedSys leds : lst) {
			treeDto = new TreeDto();
			treeDto.setId(leds.getLed().getDeviceId());
			treeDto.setText(leds.getSys().getDeviceName());
			ydpList.add(treeDto);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ydpData", ydpList);
		map.put("error", "");
		Map<String, Serializable> maprow = new HashMap<String, Serializable>();
		maprow.put("rows", ltDtoList);
		maprow.put("total", ledTaskcriteria.getTotal());
		map.put("result", maprow);
		return map;
	}

	@RequestMapping("/enableAutoTask")
	@Override
	public int enableAutoTask(LedTaskSearchDto form) throws Exception {
		if (StringUtil.isNullOrEmpty(form.getTaskId())) {
			throw new Exception("要启用的信息不存在");
		}
		ledService.enableAutoTask(form.getTaskId());
		return 0;
	}

	@RequestMapping("/disableAutoTask")
	@Override
	public int disableAutoTask(LedTaskSearchDto form) throws Exception {
		if (StringUtil.isNullOrEmpty(form.getTaskId())) {
			throw new Exception("要停用的信息不存在");
		}
		ledService.disableAutoTask(form.getTaskId());
		return 0;
	}

	private Map<String, Sys> getSysMap() {
		List<LedSys> lst = systemService.findLedSyses(new LedCriteria());
		return lst.stream().map(s -> s.getSys()).collect(Collectors.toMap(Sys::getDeviceId, s -> s));
	}

	@RequestMapping("/findReleaseTaskDetatil")
	@Override
	public LedTask findReleaseTaskDetatil(LedTaskSearchDto form) throws Exception {
		if (StringUtil.isNullOrEmpty(form.getTaskId())) {
			throw new Exception("要展示的信息不存在");
		}
		LedTask ledTask = ledService.ledTaskOfId(form.getTaskId());
		return ledTask;
	}
	//重新下发任务
	@RequestMapping("/repubManualTask")
	@Override
	public int repubManualTask(LedTaskSearchDto form) throws Exception {
		String taskId  = form.getTaskId();
		if(StringUtil.isNullOrEmpty(taskId)){
			throw new Exception("该任务不存在");
		}
		ledService.repubManualTask(taskId);
		return 0;
	}

	/**
	 * 删除任务
	 */
	@RequestMapping("/removeLedTask")
	@Override
	public int removeLedTask(LedTaskSearchDto form) throws Exception {
		String taskId  = form.getTaskId();
		if(StringUtil.isNullOrEmpty(taskId)){
			throw new Exception("该任务不存在");
		}
		ledService.removeLedTask(taskId);
		return 0;
		
	}
}
