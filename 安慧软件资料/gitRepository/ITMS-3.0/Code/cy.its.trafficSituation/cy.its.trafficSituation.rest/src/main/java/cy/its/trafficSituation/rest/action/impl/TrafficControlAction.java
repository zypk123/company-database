
package cy.its.trafficSituation.rest.action.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cy.its.com.util.StringUtil;
import cy.its.syscfg.domain.model.duty.Organization;
import cy.its.syscfg.domain.service.IDutyService;
import cy.its.trafficSituation.domain.model.TrafficControl;
import cy.its.trafficSituation.domain.service.ITrafficControlService;
import cy.its.trafficSituation.rest.action.ITrafficControlAction;
import cy.its.trafficSituation.rest.dto.TrafficControlDto;

@RestController
@RequestMapping("/trafficSituation/trafficControl")
public class TrafficControlAction implements ITrafficControlAction {
	
	@Autowired
	ITrafficControlService trafficControlService;
	@Autowired
	IDutyService dutyService;
	
	@RequestMapping("/add") 
	public String add(@ModelAttribute(value="trafficControlDto")TrafficControlDto trafficControlDto) throws Exception {
		if(!StringUtil.isNullOrEmpty(trafficControlDto.getOrgId())){
			Organization org=dutyService.organizationOfId(trafficControlDto.getOrgId());
			trafficControlDto.setOrgPrivilegeCode(org.orgPrivilegeCode);
		}
		return trafficControlService.save(trafficControlDto.parseToTrafficControl());
	}
	@RequestMapping("/delete")
	public int delete(@RequestParam(value = "id")String id) {
		return trafficControlService.delete(id);
	}
	@RequestMapping("/deleteMultiple")
	public int deleteMultiple(@RequestParam(value = "ids", required = true) String ids) {
		int count = 0;
		String[] arr = ids.split(",");
		for (String id : arr) {
			count += trafficControlService.delete(id);
		}
		return count;
	}
	@RequestMapping("/update")
	public int update(TrafficControlDto trafficControlDto) throws Exception {
		if(!StringUtil.isNullOrEmpty(trafficControlDto.getOrgId())){
			Organization org=dutyService.organizationOfId(trafficControlDto.getOrgId());
			trafficControlDto.setOrgPrivilegeCode(org.orgPrivilegeCode);
		}
		return trafficControlService.update(trafficControlDto.parseToTrafficControl());
	}
	
	@RequestMapping("/updateSelective")
	public int updateSelective(String id)  {
		TrafficControl trafficControl=new TrafficControl();
		trafficControl.setTrafficControlId(id);
		trafficControl.setRealEndTime(new Date());
		return trafficControlService.updateSelective(trafficControl);
	}
	@RequestMapping("/selectAll")
	public List<TrafficControlDto> selectAll()  {
		List<TrafficControlDto> pDtos=new ArrayList<TrafficControlDto>();
		List<TrafficControl> trafficControls=trafficControlService.selectAll(new HashMap());
		for (TrafficControl trafficControl : trafficControls) {
			pDtos.add(new TrafficControlDto(trafficControl));
		}
		return pDtos;
	}
	
	@RequestMapping("/selectValid")
	public List<TrafficControlDto> selectValid(String currentOrgPrivilegeCode)  {
		List<TrafficControlDto> pDtos=new ArrayList<TrafficControlDto>();
		 Map<String, Object> map=new HashMap<String, Object>();
		 map.put("orgPrivilegeCode", currentOrgPrivilegeCode);
		List<TrafficControl> trafficControls=trafficControlService.selectValid(map);
		for (TrafficControl trafficControl : trafficControls) {
			pDtos.add(new TrafficControlDto(trafficControl));
		}
		return pDtos;
	}
	
	@RequestMapping("/selectAllToDataGrid")
	public Object selectAllToDataGrid(HttpServletRequest request) {
		Integer pageNow = Integer.valueOf(request.getParameter("pageNumber"));
		Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
		PageHelper.startPage(pageNow, pageSize);
		 Map<String, Object> map=new HashMap<String, Object>();
		 map.put("orgPrivilegeCode",request.getParameter("currentOrgPrivilegeCode") );
		 
		Page pageRs = (Page) trafficControlService.selectAll(new HashMap());
		
		List<TrafficControlDto> pDtos=new ArrayList<TrafficControlDto>();
		List<TrafficControl> trafficControls=pageRs.getResult();
		for (TrafficControl trafficControl : trafficControls) {
			pDtos.add(new TrafficControlDto(trafficControl));
		}
		return parseToJson( pageRs, pDtos);
	}
	
	@RequestMapping("/search")
	public Object search(TrafficControlDto trafficControlDto) throws Exception {
		Integer pageNow = Integer.valueOf(trafficControlDto.getPageNumber());
		Integer pageSize = Integer.valueOf(trafficControlDto.getPageSize());
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> map=new HashMap<String, Object>();
		if(!StringUtils.isEmpty(trafficControlDto.getStartTime())){
			map.put("startTime",sdf.parse(trafficControlDto.getStartTime()));
		}
		if(!StringUtils.isEmpty(trafficControlDto.getEndTime())){
			map.put("endTime", sdf.parse(trafficControlDto.getEndTime()));
		}
		if(!StringUtils.isEmpty(trafficControlDto.getRoadId())){
			map.put("roadId", trafficControlDto.getRoadId());
		}
		map.put("orgPrivilegeCode", trafficControlDto.getCurrentOrgPrivilegeCode());
		
		PageHelper.startPage(pageNow, pageSize);
		Page pageRs = (Page) trafficControlService.selectAll(map);
		
		List<TrafficControlDto> pDtos=new ArrayList<TrafficControlDto>();
		List<TrafficControl> trafficControls=pageRs.getResult();
		for (TrafficControl trafficControl : trafficControls) {
			TrafficControlDto dto=new TrafficControlDto(trafficControl);
			pDtos.add(dto);
		}
		return parseToJson( pageRs, pDtos);
	}
	
	@RequestMapping("/searchValid")
	public Object searchValid(TrafficControlDto trafficControlDto) throws Exception {
		Integer pageNow = Integer.valueOf(trafficControlDto.getPageNumber());
		Integer pageSize = Integer.valueOf(trafficControlDto.getPageSize());
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Map<String, Object> map=new HashMap<String, Object>();
		 map.put("orgPrivilegeCode", trafficControlDto.getCurrentOrgPrivilegeCode());
		PageHelper.startPage(pageNow, pageSize);
		Page pageRs = (Page) trafficControlService.selectValid(map);
		
		List<TrafficControlDto> pDtos=new ArrayList<TrafficControlDto>();
		List<TrafficControl> trafficControls=pageRs.getResult();
		for (TrafficControl trafficControl : trafficControls) {
			TrafficControlDto dto=new TrafficControlDto(trafficControl);
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
