/**
 * @Title: ExportFileAction.java
 * @Package cy.its.sysCfg.rest.aciton.impl
 * @Description: TODO(这里要填写用途)
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月27日 上午9:31:59
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.sysCfg.rest.action.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cy.its.com.util.StringUtil;
import cy.its.common.offLine.domain.OffLineExport;
import cy.its.common.offLine.service.IOffLineExportService;
import cy.its.sysCfg.rest.action.IExportFileAction;
import cy.its.sysCfg.rest.dto.ExportDto;

@RestController
@RequestMapping("/sysCfg/exportFile")
public class ExportFileAction implements IExportFileAction {
	@Autowired
	IOffLineExportService offLineExportService;
	/*
	  * <p>Title: selectAll</p>
	  * <p>Description: </p>
	  * @param exportDto
	  * @return
	  * @see cy.its.sysCfg.rest.acitonr.IExportFileAction#selectAll(cy.its.sysCfg.rest.dto.ExportDto)
	  */

	@Override
	@RequestMapping("/selectAll")
	public Object selectAll(ExportDto exportDto) throws ParseException {
		Integer pageNow = Integer.valueOf(exportDto.getPageNumber());
		Integer pageSize = Integer.valueOf(exportDto.getPageSize());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> map=new HashMap<>();		
		map.put("loginName",exportDto.getCurrentUserLoginName());
		map.put("fileName", exportDto.getFileName());
		if(!StringUtil.isNullOrEmpty(exportDto.getStartTime())){
			map.put("startTime",sdf.parse(exportDto.getStartTime()));
		}
		if(!StringUtil.isNullOrEmpty(exportDto.getEndTime())){
			map.put("endTime", sdf.parse(exportDto.getEndTime()));
		}
		if(!StringUtil.isNullOrEmpty(exportDto.getStatus())){
			map.put("status", exportDto.getStatus());
		}
		PageHelper.startPage(pageNow, pageSize);
		Page pageRs = (Page) offLineExportService.selectAllList(map);
		List<ExportDto> pDtos=new ArrayList<ExportDto>();
		List<OffLineExport> offLineExports=pageRs.getResult();
		for (OffLineExport offLineExport : offLineExports) {
			ExportDto dto=new ExportDto(offLineExport);
			pDtos.add(dto);
		}
		return parseToJson(pageRs,pDtos);
	}
	private JSONObject parseToJson(Page pageRs, Object obj) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("error", "");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("total", pageRs.getTotal());
		jsonObject.put("rows", obj);
		jsonObj.put("result", jsonObject);
		return jsonObj;
	}
	/*
	  * <p>Title: delete</p>
	  * <p>Description: </p>
	  * @param id
	  * @return
	  * @see cy.its.sysCfg.rest.aciton.IExportFileAction#delete(java.lang.String)
	  */
	
	
	@Override
	@RequestMapping("/deleteById")
	public int delete(String id) {
		return offLineExportService.delete(id);
	}
	

}
