/**
 *name :Œ£œ’º› ª≥µ¡æ∑÷Œˆaction
 *author:wangyunqi
 *date:2016/03/14
 * 
 */
package cy.its.vehTrack.rest.action.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import cy.its.vehTrack.domain.service.IBigDataService;
import cy.its.vehTrack.rest.action.IViolationCarAction;
import cy.its.vehTrack.rest.dto.ViolationCar;
import cy.its.vehTrack.rest.dto.ViolationDetailCar;

@RestController
@RequestMapping("/vehTrack/violation")
@Api(value="ViolationCarAction",description="Œ•∑®≥µ¡æ∑÷Œˆ")
public class ViolationCarAction implements IViolationCarAction {
	
	@Autowired
	IBigDataService bigdataService;


	@Override
	@RequestMapping(value = "/fingViolationCarInfo" ,method=RequestMethod.POST)
	@ApiOperation(value="fingViolationCarInfo",notes="Œ£œ’º› ª≥µ¡æ∑÷Œˆ",httpMethod="POST")
	public JSONObject fingViolationCarInfo(ViolationCar dto) throws Exception {
		dto.setOrgAuthorityCode(dto.getCurrentOrgPrivilegeCode());
		dto.setStartDateTime(dto.getStartDateTime() + ":00");
		dto.setEndDateTime(dto.getEndDateTime() + ":59");
		dto.setTotalCount("0");
		String query= dto.toString();
		String result = this.bigdataService.loaddBigData("vio_car_service", query);
		return JSON.parseObject(result);
	}
	
	
	@Override
	@RequestMapping(value = "/queryViolationDetails" ,method=RequestMethod.POST)
	@ApiOperation(value="queryViolationDetails",notes="Œ£œ’º› ª≥µ¡æ√˜œ∏≤È—Ø",httpMethod="POST")
	public JSONObject queryViolationDetails(ViolationDetailCar dto) throws Exception{
		dto.setOrgAuthorityCode(dto.getCurrentOrgPrivilegeCode());
		dto.setStartDateTime(dto.getStartDateTime() + ":00");
		dto.setEndDateTime(dto.getEndDateTime() + ":59");
		dto.setTotalCount("0");
		String query= dto.toString();
		String result = this.bigdataService.loaddBigData("vioCar_detail_service", query);
		return JSON.parseObject(result);
	}

}
