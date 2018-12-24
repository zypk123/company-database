/**
 * @Title: ManualWeatherAction.java
 * @Package cy.its.trafficSituation.rest.action.impl
 * @Description: 人工登记恶劣天气rest实现类
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月10日 下午4:05:04
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
import cy.its.trafficSituation.domain.model.TrafficManualWeather;
import cy.its.trafficSituation.domain.service.IManualWeatherService;
import cy.its.trafficSituation.rest.action.IManualWeatherAction;
import cy.its.trafficSituation.rest.dto.ManualWeatherDto;

@RestController
@RequestMapping("/trafficSituation/manualWeather")
public class ManualWeatherAction implements IManualWeatherAction {
	@Autowired
	IManualWeatherService manualWeatherService;
	@Autowired
	IDutyService dutyService;
	/*
	  * <p>Title: add</p>
	  * <p>Description: </p>
	  * @param dto
	  * @return
	  * @see cy.its.trafficSituation.rest.action.IManualWeatherAction#add(cy.its.trafficSituation.rest.dto.ManualWeatherDto)
	  */

	@Override
	@RequestMapping("/add")
	public String add(ManualWeatherDto dto) throws Exception {
		if(!StringUtil.isNullOrEmpty(dto.getOrgId())){
			Organization org=dutyService.organizationOfId(dto.getOrgId());
			dto.setOrgPrivilegeCode(org.orgPrivilegeCode);
		}
		return manualWeatherService.save(dto.parseToTrafficManualWeather());
	}

	/*
	  * <p>Title: delete</p>
	  * <p>Description: </p>
	  * @param id
	  * @return
	  * @see cy.its.trafficSituation.rest.action.IManualWeatherAction#delete(java.lang.String)
	  */

	@Override
	@RequestMapping("/delete")
	public int delete(String id) {
		return manualWeatherService.delete(id);
	}

	/*
	  * <p>Title: deleteMultiple</p>
	  * <p>Description: </p>
	  * @param ids
	  * @return
	  * @see cy.its.trafficSituation.rest.action.IManualWeatherAction#deleteMultiple(java.lang.String)
	  */

	@Override
	@RequestMapping("/deleteMultiple")
	public int deleteMultiple(String ids) {
		int count=0;
		String[] arr=ids.split(",");
		for (String str : arr) {
			count+=manualWeatherService.delete(str);
		}
		return count;
	}

	/*
	  * <p>Title: update</p>
	  * <p>Description: </p>
	  * @param dto
	  * @return
	  * @see cy.its.trafficSituation.rest.action.IManualWeatherAction#update(cy.its.trafficSituation.rest.dto.ManualWeatherDto)
	  */

	@Override
	@RequestMapping("/update")
	public int update(ManualWeatherDto dto) throws Exception {
		if(!StringUtil.isNullOrEmpty(dto.getOrgId())){
			Organization org=dutyService.organizationOfId(dto.getOrgId());
			dto.setOrgPrivilegeCode(org.orgPrivilegeCode);
		}
		return manualWeatherService.update(dto.parseToTrafficManualWeather());
	}

	/*
	  * <p>Title: selectAll</p>
	  * <p>Description: </p>
	  * @param manualWeatherDto
	  * @return
	  * @see cy.its.trafficSituation.rest.action.IManualWeatherAction#selectAll(cy.its.trafficSituation.rest.dto.ManualWeatherDto)
	  */

	@Override
	@RequestMapping("/selectAll")
	public Object selectAll(ManualWeatherDto manualWeatherDto) throws Exception {
		Integer pageNow = Integer.valueOf(manualWeatherDto.getPageNumber());
		Integer pageSize = Integer.valueOf(manualWeatherDto.getPageSize());
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> map=new HashMap<String, Object>();
		if(!StringUtils.isEmpty(manualWeatherDto.getStartTime())){
			map.put("startTime",sdf.parse(manualWeatherDto.getStartTime()));
		}
		if(!StringUtils.isEmpty(manualWeatherDto.getEndTime())){
			map.put("endTime", sdf.parse(manualWeatherDto.getEndTime()));
		}
		if(!StringUtils.isEmpty(manualWeatherDto.getRoadId())){
			map.put("roadId", manualWeatherDto.getRoadId());
		}
		map.put("orgPrivilegeCode", manualWeatherDto.getCurrentOrgPrivilegeCode());
		
		PageHelper.startPage(pageNow, pageSize);
		Page pageRs = (Page) manualWeatherService.selectAll(map);
		List<ManualWeatherDto> pDtos=new ArrayList<ManualWeatherDto>();
		List<TrafficManualWeather> manualWeathers=pageRs.getResult();
		for (TrafficManualWeather weather : manualWeathers) {
			ManualWeatherDto dto=new ManualWeatherDto(weather);
			pDtos.add(dto);
		}
		return parseToJson( pageRs, pDtos);
	}
	
	/**
	  * @Title: parseToJson
	  * @Description: 转为Json
	  * @param @param pageRs
	  * @param @param obj
	  * @param @return    设定文件
	  * @return JSONObject    返回类型
	  * @throws
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
