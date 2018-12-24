package cy.its.vehTrack.rest.action.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import cy.its.vehTrack.domain.service.IBigDataService;
import cy.its.vehTrack.rest.action.IDataTransferMissingAction;
import cy.its.vehTrack.rest.dto.DataTransferMissingInputBean;

@RestController
@Api(value = "dataTransferMissing", description = "卡口数据传输缺失分析")
@RequestMapping(value = "vehTrack/dataMissing")
public class DataTransferMissingAction implements IDataTransferMissingAction {
	@Autowired
	IBigDataService bigDataService;

	@Override
	@RequestMapping(value = "/queryDataTransferMissing", method = RequestMethod.POST)
	@ApiOperation(value = "queryDataTransferMissing", notes = "卡口数据传输缺失分析", httpMethod = "POST")
	public JSONObject queryDataTransferMissing(@ModelAttribute("inputBean") DataTransferMissingInputBean inputBean) {
		JSONObject obj = null;
		try {
			String returnResult = this.bigDataService.loaddBigData("data_missing_service", inputBean.toString());
			obj = JSONObject.parseObject(returnResult);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	@RequestMapping(value = "/queryDataMissingByDevice", method = RequestMethod.POST)
	@ApiOperation(value = "queryDataMissingByDevice", notes = "分析单个卡口每一天的数据缺失", httpMethod = "POST")
	public JSONObject queryDataTransferMissingBySysNbr(
			@ModelAttribute("inputBean") DataTransferMissingInputBean inputBean) {
		JSONObject obj = null;
		try {
			String returnResult =this.bigDataService.loaddBigData("data_missing_deviceSysNbr_service", inputBean.toString());
			obj = JSONObject.parseObject(returnResult);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;

	}

}
