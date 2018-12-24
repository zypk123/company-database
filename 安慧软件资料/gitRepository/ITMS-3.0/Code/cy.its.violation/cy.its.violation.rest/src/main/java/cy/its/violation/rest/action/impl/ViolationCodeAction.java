/**
 * @Title: ViolationCodeAction.java
 * @Package cy.its.violation.rest.action.impl
 * @Description: TODO(这里要填写用途)
 * @author wangyf wangyf@cychina.cn
 * @date 2015年11月13日 下午4:52:13
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.violation.rest.action.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cy.its.com.util.ObjectMapUtils;
import cy.its.violation.domain.criteria.VioActionCriteria;
import cy.its.violation.domain.model.config.VioActionCode;
import cy.its.violation.domain.model.config.VioAssoAction;
import cy.its.violation.domain.service.IVioActionService;
import cy.its.violation.domain.service.IVioAssoActionService;
import cy.its.violation.rest.action.IViolationCodeAction;
import cy.its.violation.rest.dto.ViolationCodeDto;

/**
 * @ClassName: ViolationCodeAction
 * @Description: TODO(这里要填写用途)
 * @author wangyf wangyf@cychina.cn
 * @date 2015年11月13日 下午4:52:13
 *
 */
@RestController
@RequestMapping("/violationCodeAction")
public class ViolationCodeAction implements IViolationCodeAction {

	@Autowired
	IVioActionService vioActionService;

	@Autowired
	IVioAssoActionService vioAssoActionService;
	/*
	 * <p>Title: findViolationCode</p> <p>Description: </p>
	 * 
	 * @param vioationCodeDto
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.violation.rest.action.IViolationCodeAction#findViolationCode(cy.
	 * its.violation.rest.dto.ViolationCodeDto)
	 */

	@RequestMapping("/findViolationCode")
	@Override
	public Map findViolationCode(ViolationCodeDto violationCodeDto) throws Exception {
		// 查询条件
		VioActionCriteria vioCrieria = new VioActionCriteria();
		vioCrieria.violationCode = violationCodeDto.getVioActionCode();
		vioCrieria.violationType = violationCodeDto.getVioType();
		vioCrieria.fuzzySummary = violationCodeDto.getVioSummary();

		// 返回页面的list集合
		ArrayList<ViolationCodeDto> lstView = new ArrayList<ViolationCodeDto>();
		// 获得违法代码表数据
		List<VioActionCode> lstCode = vioActionService.findVioAction(vioCrieria);
		//

		for (VioActionCode vioActionCode : lstCode) {

			ViolationCodeDto vioCodeDto = new ViolationCodeDto();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			vioCodeDto.setIssueDay(sdf.format(vioActionCode.getIssueDay()));
			vioCodeDto.setIssueEndDay(sdf.format(vioActionCode.getIssueEndDay()));
			if (vioActionCode.fineDefault != null) {
				vioCodeDto.setFineDefault(vioActionCode.fineDefault.toString());
			}

			if (vioActionCode.fineMax != null) {
				vioCodeDto.setFineMax(vioActionCode.fineMax.toString());
			}
			if (vioActionCode.fineMin != null) {
				vioCodeDto.setFineMin(vioActionCode.fineMin.toString());
			}
			if (vioActionCode.fineScore != null) {
				vioCodeDto.setFineScore(vioActionCode.fineScore.toString());
			}
			if (vioActionCode.suspendMonthMax != null) {
				vioCodeDto.setSuspendMonthMax(vioActionCode.suspendMonthMax.toString());
			}
			if (vioActionCode.suspendMonthMin != null) {
				vioCodeDto.setSuspendMonthMin(vioActionCode.suspendMonthMin.toString());
			}
			if (vioActionCode.detainDaysMax != null) {
				vioCodeDto.setDetainDaysMax(vioActionCode.detainDaysMax.toString());
			}
			if (vioActionCode.detainDaysMin != null) {
				vioCodeDto.setDetainDaysMin(vioActionCode.detainDaysMin.toString());
			}

			ObjectMapUtils.parseObject(vioCodeDto, vioActionCode);

			List<VioAssoAction> vioAssoLst = vioAssoActionService
					.SelectByViolationCode(vioActionCode.getVioActionCode());
			for (VioAssoAction vioAsso : vioAssoLst) {

				if (vioAsso.getLimitSpeed() != null) {
					vioCodeDto.setLimitSpeed(vioAsso.getLimitSpeed().toString());
				}
				if (vioAsso.getMaxRatio() != null) {
					vioCodeDto.setMaxRatio(vioAsso.getMaxRatio().toString());
				}
				if (vioAsso.getMinRatio() != null) {
					vioCodeDto.setMinRatio(vioAsso.getMinRatio().toString());
				}

				ObjectMapUtils.parseObject(vioCodeDto, vioAsso);

			}
			lstView.add(vioCodeDto);
		}

		Map map = new HashMap();
		map.put("error", "");
		Map maprows = new HashMap();
		maprows.put("rows", lstView);
		map.put("result", maprows);
		return map;
	}

	/*
	 * <p>Title: createVioationCode</p> <p>Description:创建违法代码 </p>
	 * 
	 * @param vioationCodeDto
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.violation.rest.action.IViolationCodeAction#createVioationCode(cy.
	 * its.violation.rest.dto.ViolationCodeDto)
	 */

	@RequestMapping("/createVioationCode")
	@Override
	public String createVioationCode(@ModelAttribute("vioActionDto") ViolationCodeDto vioActionDto)
			throws ParseException, Exception {

		VioActionCode vioCode = new VioActionCode();
		// 将客户端日期String类型转换为领域Date类型
		DateFormat dfmat = new SimpleDateFormat("yyyy-MM-dd");
		vioCode.setIssueDay(dfmat.parse(vioActionDto.getIssueDay()));
		vioCode.setIssueEndDay(dfmat.parse(vioActionDto.getIssueEndDay()));
		if (vioActionDto.getFineScore() != "") {
			vioCode.fineScore = Integer.valueOf(vioActionDto.getFineScore());

		}

		if (vioActionDto.getFineMax() != "") {
			vioCode.fineMax = Integer.valueOf(vioActionDto.getFineMax());
		}

		if (vioActionDto.getFineMin() != "") {
			vioCode.fineMin = Integer.valueOf(vioActionDto.getFineMin());
		}
		if (vioActionDto.getFineDefault() != "") {
			vioCode.fineDefault = Integer.valueOf(vioActionDto.getFineDefault());
		}
		if (vioActionDto.getSuspendMonthMax() != "") {
			vioCode.suspendMonthMax = Integer.valueOf(vioActionDto.getSuspendMonthMax());
		}
		if (vioActionDto.getSuspendMonthMin() != "") {
			vioCode.suspendMonthMin = Integer.valueOf(vioActionDto.getSuspendMonthMin());
		}
		if (vioActionDto.getDetainDaysMax() != "") {
			vioCode.detainDaysMax = Integer.valueOf(vioActionDto.getDetainDaysMax());
		}
		if (vioActionDto.getDetainDaysMin() != "") {
			vioCode.detainDaysMin = Integer.valueOf(vioActionDto.getDetainDaysMin());
		}

		ObjectMapUtils.parseObject(vioCode, vioActionDto);

		VioAssoAction vioAsso = new VioAssoAction();
		if (vioActionDto.getLimitSpeed() != "") {
			vioAsso.setLimitSpeed(Integer.valueOf((vioActionDto.getLimitSpeed())));
		}
		if (vioActionDto.getMaxRatio() != "") {
			vioAsso.setMaxRatio(Integer.valueOf(vioActionDto.getMaxRatio()));
		}
		if (vioActionDto.getMinRatio() != "") {
			vioAsso.setMinRatio(Integer.valueOf(vioActionDto.getMinRatio()));
		}
		if (vioActionDto.getVioActionCode() != "") {
			vioAsso.setViolationCode(vioActionDto.getVioActionCode());
		}
		if (vioActionDto.getVioConetent() != "") {
			vioAsso.setViolationDesc(vioActionDto.getVioConetent());
		}

		ObjectMapUtils.parseObject(vioAsso, vioActionDto);

		vioActionService.saveActionCode(vioCode);
		if (vioAsso.getRoadType() != null && vioAsso.getLimitSpeed() != null && vioAsso.getMaxRatio() != null
				&& vioAsso.getMinRatio() != null && vioAsso.getViolationType() != null) {
			vioAssoActionService.saveVioAssoAction(vioAsso);
		}

		return "success";
	}

	/*
	 * <p>Title: updateVioationCode</p> <p>Description:更新违法代码 </p>
	 * 
	 * @param vioationCodeDto
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.violation.rest.action.IViolationCodeAction#updateVioationCode(cy.
	 * its.violation.rest.dto.ViolationCodeDto)
	 */

	@SuppressWarnings("unused")
	@RequestMapping("/updateVioationCode")
	@Override
	public String updateVioationCode(ViolationCodeDto vioationCodeDto) throws ParseException, Exception {
		VioActionCode vioCode = new VioActionCode();
		// 将客户端日期String类型转换为领域Date类型
		DateFormat dfmat = new SimpleDateFormat("yyyy-MM-dd");
		if (vioationCodeDto.getIssueDay() != null) {
			vioCode.setIssueDay(dfmat.parse(vioationCodeDto.getIssueDay()));
		}
		if (vioationCodeDto.getIssueDay() != null) {
			vioCode.setIssueEndDay(dfmat.parse(vioationCodeDto.getIssueDay()));
		}
		if (vioationCodeDto.getFineScore() != "") {
			vioCode.fineScore = Integer.valueOf(vioationCodeDto.getFineScore());

		}

		if (vioationCodeDto.getFineMax() != "") {
			vioCode.fineMax = Integer.valueOf(vioationCodeDto.getFineMax());
		}

		if (vioationCodeDto.getFineMin() != "") {
			vioCode.fineMin = Integer.valueOf(vioationCodeDto.getFineMin());
		}
		if (vioationCodeDto.getFineDefault() != "") {
			vioCode.fineDefault = Integer.valueOf(vioationCodeDto.getFineDefault());
		}
		if (vioationCodeDto.getSuspendMonthMax() != "") {
			vioCode.suspendMonthMax = Integer.valueOf(vioationCodeDto.getSuspendMonthMax());
		}
		if (vioationCodeDto.getSuspendMonthMin() != "") {
			vioCode.suspendMonthMin = Integer.valueOf(vioationCodeDto.getSuspendMonthMin());
		}
		if (vioationCodeDto.getDetainDaysMax() != "") {
			vioCode.detainDaysMax = Integer.valueOf(vioationCodeDto.getDetainDaysMax());
		}
		if (vioationCodeDto.getDetainDaysMin() != "") {
			vioCode.detainDaysMin = Integer.valueOf(vioationCodeDto.getDetainDaysMin());
		}
		ObjectMapUtils.parseObject(vioCode, vioationCodeDto);

		VioAssoAction vioAsso = new VioAssoAction();

		if (vioationCodeDto.getLimitSpeed() != "") {
			vioAsso.setLimitSpeed(Integer.valueOf((vioationCodeDto.getLimitSpeed())));
		}
		if (vioationCodeDto.getMaxRatio() != "") {
			vioAsso.setMaxRatio(Integer.valueOf(vioationCodeDto.getMaxRatio()));
		}
		if (vioationCodeDto.getMinRatio() != "") {
			vioAsso.setMinRatio(Integer.valueOf(vioationCodeDto.getMinRatio()));
		}
		if (vioationCodeDto.getVioActionCode() != "") {
			vioAsso.setViolationCode(vioationCodeDto.getVioActionCode());
		}
		if (vioationCodeDto.getVioConetent() != "") {
			vioAsso.setViolationDesc(vioationCodeDto.getVioConetent());
		}

		ObjectMapUtils.parseObject(vioAsso, vioationCodeDto);
		int resultVioCode = vioActionService.updateActionCode(vioCode);
		int resultVioAsso = 0;
		String vioAssResult = "";
		if (vioAsso.getVioActionMatchId() != null && vioAsso.getVioActionMatchId() != "") {
			resultVioAsso = vioAssoActionService.updateVioAssoAction(vioAsso);
		} else if (vioAsso.getRoadType() != null || vioAsso.getLimitSpeed() != null || vioAsso.getMaxRatio() != null
				|| vioAsso.getMinRatio() != null || vioAsso.getViolationType() != null) {
			vioAssResult = vioAssoActionService.saveVioAssoAction(vioAsso);

		}

		if (resultVioCode == 1 || resultVioAsso == 1 || vioAssResult.equals(vioAssResult)) {
			return "success";
		} else {
			return "fial";
		}

	}

	/*
	 * <p>Title: deleteVioationCode</p> <p>Description: </p>
	 * 
	 * @param vioationCodeStrId
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.violation.rest.action.IViolationCodeAction#deleteVioationCode(java
	 * .lang.String)
	 */
	@RequestMapping("/deleteVioationCode")
	@Override
	public String deleteVioationCode(String vioActionCodeStr, String vioActionMatchIdStr) {
		String[] vioActionCodes = vioActionCodeStr.split(",");
		String[] vioActionMatchIds = vioActionMatchIdStr.split(",");

		int resultVioActionCode = 0;
		for (int i = 0; i < vioActionCodes.length; i++) {
			resultVioActionCode += vioActionService.removeCode(vioActionCodes[i]);
		}
		int resultVioActionMatchId = 0;
		if (vioActionMatchIds != null && vioActionMatchIds.length > 0) {
			for (int i = 0; i < vioActionMatchIds.length; i++) {
				resultVioActionMatchId += vioAssoActionService.removeCode(vioActionMatchIds[i]);
			}
		}

		if (resultVioActionCode == 1 || resultVioActionMatchId == 1) {
			return "success";
		} else {
			return "false";
		}
	}

	/*
	 * <p>Title: removeVioationCode</p> <p>Description: </p>
	 * 
	 * @param vioationCodeId
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.violation.rest.action.IViolationCodeAction#removeVioationCode(java
	 * .lang.String)
	 */
	@RequestMapping("/removeVioationCode")
	@Override
	public String removeVioationCode(String vioActionCode, String vioActionMatchId) {
		int resultVioActionCode = vioActionService.removeCode(vioActionCode);
		int resultVioActionMatchId = 0;
		if (vioActionMatchId != null && vioActionMatchId != "") {
			resultVioActionMatchId = vioAssoActionService.removeCode(vioActionMatchId);
		}
		if (resultVioActionCode == 1 || resultVioActionMatchId == 1) {
			return "success";
		} else {
			return "false";
		}
	}

}
