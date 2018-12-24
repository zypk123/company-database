/**
 *name :无牌车车辆分析
 *author:wangyunqi
 *date:2016/03/14
 * 
 */
package cy.its.vehTrack.rest.action.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wordnik.swagger.annotations.ApiOperation;

import cy.its.vehTrack.domain.service.IUnlicensedCarsService;
import cy.its.vehTrack.rest.action.IUnlicensedCarsAction;
import cy.its.vehTrack.rest.dto.UnlicensedCarDto;

@RequestMapping(value="/vehTrack/nullplate")
@RestController
public class UnlicensedCarsAction implements IUnlicensedCarsAction {
	@Autowired
	IUnlicensedCarsService service;

	@Override
	@RequestMapping(value="/findUnlicensedCars",method=RequestMethod.POST)
	@ApiOperation(value="findUnlicensedCars",httpMethod="POST",notes="无牌车车辆分析")
	public JSONObject findUnlicensedCarsInfo(UnlicensedCarDto carDto) throws Exception {
		carDto.setTotalCount("0");
		// TODO Auto-generated method stub
		String query = carDto.toString();
		JSONObject obj = (JSONObject)service.findUnlicensedCarsInfo(query).get("result");
		//System.out.println("......");
		return obj;
	}

}
