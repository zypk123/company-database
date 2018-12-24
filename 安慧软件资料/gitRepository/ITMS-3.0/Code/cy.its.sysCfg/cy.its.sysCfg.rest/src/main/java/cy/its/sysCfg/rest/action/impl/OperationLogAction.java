/**
 * @Title: Operation.java
 * @Package cy.its.sysCfg.rest.aciton.impl
 * @Description: TODO(这里要填写用途)
 * @author wangyf wangyf@cychina.cn
 * @date 2015年12月3日 上午9:33:36
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.sysCfg.rest.action.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cy.its.com.util.ObjectMapUtils;
import cy.its.sysCfg.rest.action.IOperationLogAction;
import cy.its.sysCfg.rest.dto.OperationLogDto;
import cy.its.syscfg.domain.criteria.OperationLogCriteria;
import cy.its.syscfg.domain.criteria.UserCriteria;
import cy.its.syscfg.domain.model.duty.User;
import cy.its.syscfg.domain.model.sysLog.OperationLog;
import cy.its.syscfg.domain.service.IDutyService;
import cy.its.syscfg.domain.service.ISysLogService;

/**
 * @ClassName: Operation
 * @Description: TODO(这里要填写用途)
 * @author wangyf wangyf@cychina.cn
 * @date 2015年12月3日 上午9:33:36
 *
 */
@RestController
@RequestMapping("/sysCfg/OperationLog")
public class OperationLogAction implements IOperationLogAction {
	@Autowired
	ISysLogService sysLogService;

	@Autowired
	IDutyService dutyService;
	/*
	 * <p>Title: findByOperationLog</p> <p>Description: 查询</p>
	 * 
	 * @param operationLogDto
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.sysCfg.rest.aciton.IOperationLogAction#findOperationLog(cy.its.
	 * sysCfg.rest.dto.OperationLogDto)
	 */

	@RequestMapping("/findByOperationLog")
	@Override
	public Map<String, Object> goFindByOperationLog(OperationLogDto operationLogDto) throws Exception {
		// TODO Auto-generated method stub
		OperationLogCriteria optLogCriteria = new OperationLogCriteria();
		// 分页信息
		optLogCriteria.setNeedTotal(true);
		optLogCriteria.setPageNum(operationLogDto.getPageNumber());
		optLogCriteria.setPageSize(operationLogDto.getPageSize());
		// 构造查询条件
		
		optLogCriteria.opeUserName = operationLogDto.getOpeUserName();
		optLogCriteria.orgPrivilegeCode=operationLogDto.getCurrentOrgPrivilegeCode();
		optLogCriteria.sysFunctionCode = operationLogDto.getFunctionCode();
		optLogCriteria.operateBeginTime = operationLogDto.getOperateBeginTime();
		optLogCriteria.operateEndTime = operationLogDto.getOperateEndTime();
		optLogCriteria.operateResult = operationLogDto.getOperateResult();
		// 返回lstView集合
		ArrayList<OperationLogDto> lstView = new ArrayList<OperationLogDto>();
		// 获得日志信息
		List<OperationLog> optLogList = sysLogService.findSysLogs(optLogCriteria);
		for (OperationLog optLog : optLogList) {
			OperationLogDto optLogDto = new OperationLogDto();
			// 把date转String
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (optLog.getOperateTime() != null) {
				optLogDto.setOperateTime(sdf.format(optLog.getOperateTime()));
			}

			if (optLog.getConsumeSeconds() != null) {
				optLogDto.setConsumeSeconds(Double.toString(optLog.getConsumeSeconds()));
			}
			// 把领域对象Dto转化为客户端Dto
			ObjectMapUtils.parseObject(optLogDto, optLog);
			lstView.add(optLogDto);
		}
		// 封装页面返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", "");
		Map<String, Object> maprows = new HashMap<String, Object>();
		maprows.put("rows", lstView);
		maprows.put("total", optLogCriteria.getTotal());
		map.put("result", maprows);
		return map;
	}

}
