/**
 * @Title: ManualEventAction.java
 * @Package cy.its.trafficSituation.rest.action.impl
 * @Description: 人工登记事件rest实现类
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月10日 下午4:04:19
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.rest.action.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cy.its.com.util.StringUtil;
import cy.its.syscfg.domain.model.duty.Organization;
import cy.its.syscfg.domain.service.IDutyService;
import cy.its.trafficSituation.domain.model.TrafficManualEvent;
import cy.its.trafficSituation.domain.service.IManualEventService;
import cy.its.trafficSituation.rest.action.IManualEventAction;
import cy.its.trafficSituation.rest.dto.ManualEventDto;

@RestController
@RequestMapping("/trafficSituation/manualEvent")
public class ManualEventAction implements IManualEventAction {

	@Autowired
	IManualEventService manualEventService;
	@Autowired
	IDutyService dutyService;
	
	/*
	 * <p>Title: add</p> <p>Description: </p>
	 * 
	 * @param dto
	 * 
	 * @return
	 * 
	 * @see cy.its.trafficSituation.rest.action.IManualEventAction#add(cy.its.
	 * trafficSituation.rest.dto.ManualEventDto)
	 */

	@Override
	@RequestMapping("/add")
	public String add(ManualEventDto dto) throws Exception {
		if(!StringUtil.isNullOrEmpty(dto.getOrgId())){
			Organization org=dutyService.organizationOfId(dto.getOrgId());
			dto.setOrgPrivilegeCode(org.orgPrivilegeCode);
		}
		return manualEventService.save(dto.parseToTrafficManualEvent());
	}

	/*
	 * <p>Title: delete</p> <p>Description: </p>
	 * 
	 * @param id
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.trafficSituation.rest.action.IManualEventAction#delete(java.lang.
	 * String)
	 */

	@Override
	@RequestMapping("/delete")
	public int delete(String id) {
		return manualEventService.delete(id);
	}

	/*
	 * <p>Title: deleteMultiple</p> <p>Description: </p>
	 * 
	 * @param ids
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.trafficSituation.rest.action.IManualEventAction#deleteMultiple(
	 * java.lang.String)
	 */

	@Override
	@RequestMapping("/deleteMultiple")
	public int deleteMultiple(String ids) {
		int count = 0;
		String[] arr = ids.split(",");
		for (String str : arr) {
			count += manualEventService.delete(str);
		}
		return count;
	}

	/*
	 * <p>Title: update</p> <p>Description: </p>
	 * 
	 * @param dto
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.trafficSituation.rest.action.IManualEventAction#update(cy.its.
	 * trafficSituation.rest.dto.ManualEventDto)
	 */

	@Override
	@RequestMapping("/update")
	public int update(ManualEventDto dto) throws Exception {
		if(!StringUtil.isNullOrEmpty(dto.getOrgId())){
			Organization org=dutyService.organizationOfId(dto.getOrgId());
			dto.setOrgPrivilegeCode(org.orgPrivilegeCode);
		}
		return manualEventService.update(dto.parseToTrafficManualEvent());
	}

	/*
	 * <p>Title: selectAll</p> <p>Description: </p>
	 * 
	 * @param manualEventDto
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.trafficSituation.rest.action.IManualEventAction#selectAll(cy.its.
	 * trafficSituation.rest.dto.ManualEventDto)
	 */

	@Override
	@RequestMapping("/selectAll")
	public Object selectAll(ManualEventDto manualEventDto) throws Exception {
		Integer pageNow = Integer.valueOf(manualEventDto.getPageNumber());
		Integer pageSize = Integer.valueOf(manualEventDto.getPageSize());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(manualEventDto.getStartTime())) {
			map.put("startTime", sdf.parse(manualEventDto.getStartTime()));
		}
		if (!StringUtils.isEmpty(manualEventDto.getEndTime())) {
			map.put("endTime", sdf.parse(manualEventDto.getEndTime()));
		}
		if (!StringUtils.isEmpty(manualEventDto.getRoadId())) {
			map.put("roadId", manualEventDto.getRoadId());
		}
		map.put("orgPrivilegeCode", manualEventDto.getCurrentOrgPrivilegeCode());
		PageHelper.startPage(pageNow, pageSize);
		Page pageRs = (Page) manualEventService.selectAll(map);
		List<ManualEventDto> pDtos = new ArrayList<ManualEventDto>();
		List<TrafficManualEvent> manualEvents = pageRs.getResult();
		for (TrafficManualEvent trafficManualEvent : manualEvents) {
			ManualEventDto dto = new ManualEventDto(trafficManualEvent);
			pDtos.add(dto);
		}
		return parseToJson(pageRs, pDtos);
	}

	/**
	 * @Title: parseToJson @Description: 转为Json @param @param
	 * pageRs @param @param obj @param @return 设定文件 @return JSONObject
	 * 返回类型 @throws
	 */
	private JSONObject parseToJson(Page pageRs, Object obj) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("error", "");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("total", pageRs.getTotal());
		jsonObject.put("rows", obj);
		jsonObj.put("result", jsonObject);
		return jsonObj;
	}

}
