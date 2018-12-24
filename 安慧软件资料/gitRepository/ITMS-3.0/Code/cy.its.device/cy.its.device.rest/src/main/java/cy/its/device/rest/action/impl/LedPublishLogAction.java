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
import cy.its.device.domain.criteria.LedPublishLogCriteria;
import cy.its.device.domain.model.Sys;
import cy.its.device.domain.model.led.LedPublishLog;
import cy.its.device.domain.model.led.LedSys;
import cy.its.device.domain.service.ILedService;
import cy.its.device.domain.service.ISystemService;
import cy.its.device.rest.action.ILedPublishLogAction;
import cy.its.device.rest.dto.LedPublishLogDto;
import cy.its.device.rest.dto.TreeDto;

@RequestMapping("/device/ledPublishLogAction")
@RestController
public class LedPublishLogAction implements ILedPublishLogAction {
	
	@Autowired
	private ILedService ledservice;
	
	@Autowired
	ISystemService systemService;
 
	@RequestMapping("/findLedPublishLogList")
	@Override
	public Map<String, Object> findLedPublishLogList(LedPublishLogDto form) throws Exception {
		LedPublishLogCriteria criteria = new LedPublishLogCriteria();
		// 设置当前页数
		criteria.setPageNum(form.getPageNumber());
		// 设置每页最大显示个数
		criteria.setPageSize(form.getPageSize());
		// 是否需要统计总数
		criteria.setNeedTotal(true);
		criteria.setOrderName(form.getOrderName());
		criteria.setOrderType(form.getOrderType());				
		if(form.orgPrivilegeCode !=""){
			criteria.orgPrivilegeCode = form.orgPrivilegeCode;
		}
		if(form.deviceId !=""){
			criteria.deviceId = form.deviceId;
		}
		if(form.roadId !=""){
			criteria.roadId = form.roadId;
		}
		if(form.publishMethodS !=null){
			criteria.publishMethodArr = form.publishMethodS;
		}
		
		if(form.messageType !=""){
			criteria.messageType = form.messageType;
		}
		if(form.publishTimeFrom !=""){
			criteria.publishTimeFrom =DateUtil.parseDate(form.publishTimeFrom);
		}
		
		if(form.publishTimeTo !=""){
			criteria.publishTimeTo = DateUtil.parseDate(form.publishTimeTo);
		}
		
		if(form.programId !=""){
			criteria.programId = form.programId;
		}
		
		if(form.programId !=""){
			criteria.programId = form.programId;
		}
		if(form.taskType !=""){
			criteria.taskType = form.taskType;
		}
		if(form.resultArrS !=null){
			criteria.resultArr = form.resultArrS;
		}
		if(form.taskId !=""){
			criteria.taskId = form.taskId;
		}

		List<LedPublishLog> logList = ledservice.findLedPublishLogs(criteria);
		
		Map<String, Sys> sysMap = getSysMap();
		// 返回页面的结果集
		ArrayList<LedPublishLogDto> listView = new ArrayList<>();
		for(LedPublishLog log:logList){
			LedPublishLogDto logDto = new LedPublishLogDto();
			if(log.getBeginTime() !=null){
				logDto.setBeginTime(DateUtil.formatDate(log.getBeginTime()));
			}
			if(log.getEndTime() !=null){
				logDto.setEndTime(DateUtil.formatDate(log.getEndTime()));
			}
			Sys s = sysMap.get(log.getDeviceSysNbr());
			logDto.setDeviceSysNbr(s!=null?s.getDeviceName():null);
			ObjectMapUtils.parseObject(logDto, log);
			listView.add(logDto);
		}
		List<LedSys> lst = systemService.findLedSyses(new LedCriteria());
		List<TreeDto> ydpList = new ArrayList<TreeDto>();
		TreeDto treefirst = new TreeDto();
		treefirst.setId("0");
		treefirst.setText("请选择");
		ydpList.add(treefirst);
		for (int i = 0; i < lst.size(); i++) {
			TreeDto tree = new TreeDto();
			tree.setId(lst.get(i).getSys().getDeviceId());
			tree.setText(lst.get(i).getSys().getDeviceName());
			ydpList.add(tree);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ydpData", ydpList);
		map.put("error", "");
		Map<String, Serializable> maprow = new HashMap<String, Serializable>();
		maprow.put("rows", listView);
		maprow.put("total", criteria.getTotal());
		map.put("result", maprow);
		return map;
	}
		
	private Map<String, Sys> getSysMap() {
		List<LedSys> lst = systemService.findLedSyses(new LedCriteria());
		return  lst.stream().map(s->s.getSys()).
		collect(Collectors.toMap(Sys::getDeviceSysNbr, s->s));
	}

	
}
