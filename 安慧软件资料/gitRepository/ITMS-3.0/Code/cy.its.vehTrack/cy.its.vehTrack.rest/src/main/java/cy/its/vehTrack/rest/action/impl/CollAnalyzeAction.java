/**
 * date:2016/03/21
 * author:wangyunqi
 * name:区域案件对碰分析(调用大数据)
 */
package cy.its.vehTrack.rest.action.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import cy.its.vehTrack.domain.service.ICollAnalyzeService;
import cy.its.vehTrack.rest.action.ICollAnalyzeAction;
import cy.its.vehTrack.rest.dto.CollAnalyzeInputBean;

@RestController
@RequestMapping(value= "/vehTrack/areaCollision")
@Api(description="区域案件对碰分析", value = "CollAnalyzeAction")
public class CollAnalyzeAction implements ICollAnalyzeAction {
	@Autowired
	ICollAnalyzeService service;
	
	@Override
	@RequestMapping(value="areaCollisionAnaly",method=RequestMethod.POST)
	@ApiOperation(value="findCollAnalyResList",notes="区域案件对碰分析",httpMethod="POST")
	public JSONObject findCollAnalyResList(CollAnalyzeInputBean bean) throws Exception {
		bean.setOrgAuthorityCode(bean.getCurrentOrgPrivilegeCode());
		return this.service.findCollAnalyResList(bean.toString());
	}

}
