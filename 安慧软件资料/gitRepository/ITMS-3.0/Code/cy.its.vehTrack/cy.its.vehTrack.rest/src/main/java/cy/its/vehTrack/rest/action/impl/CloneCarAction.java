/**
 *name :套牌车分析查询action
 *author:wangyunqi
 *date:2016/03/11
 * 
 */
package cy.its.vehTrack.rest.action.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wordnik.swagger.annotations.ApiOperation;

import cy.its.com.dto.BaseDto;
import cy.its.device.domain.model.site.Site;
import cy.its.device.domain.service.ISiteService;
import cy.its.vehTrack.domain.model.CloneCarDB;
import cy.its.vehTrack.domain.service.ICloneCarService;
import cy.its.vehTrack.rest.action.ICloneCarAction;
import cy.its.vehTrack.rest.dto.CloneCarDto;

@RequestMapping(value = "/vehTrack/clone")
@RestController
public class CloneCarAction implements ICloneCarAction {

	@Autowired
	ICloneCarService cloneCarService;
	
	@Autowired
	ISiteService siteService;

	/*
	 * 套牌车分析查询 (non-Javadoc)
	 * 
	 * @see cy.its.vehTrack.rest.action.ICloneCarAction#findCloneCarInfo(cy.its.
	 * vehTrack.rest.dto.CloneCarDto)
	 */
	@Override
	@RequestMapping(value = "/findCloneCar", method = RequestMethod.POST)
	@ApiOperation(value = "findCloneCar", notes = "套牌车分析查询", httpMethod = "POST")
	public Map<String, Object> findCloneCarInfo(CloneCarDto cloneCarDto) throws Exception {
		cloneCarDto.setTotalCount("0");
		String queryDB = JSON.toJSONString(cloneCarDto);
		Map<String, Object> map = this.cloneCarService.findCloneCarInfo(cloneCarDto.toString(), queryDB);
		Map<String, Object> map2 = (Map<String, Object>) map.get("result");
		List<CloneCarDB> cloneCarList = (List<CloneCarDB>) map2.get("rows");
		for(CloneCarDB bean:cloneCarList){
			Site site1= this.siteService.siteOfCode(bean.getSite_code1());
			bean.setSite_name1(site1.getSiteName());
			bean.setSiteLatitude1(site1.getSiteLatitude());
			bean.setSiteLatitude1(site1.getSiteLatitude());
			Site site2= this.siteService.siteOfCode(bean.getSite_code2());
			bean.setSite_name1(site2.getSiteName());
			bean.setSiteLatitude1(site2.getSiteLatitude());
			bean.setSiteLatitude1(site2.getSiteLatitude());
		}
		map2.put("rows", cloneCarList);
		return map;
	}

	/**
	 * 更新确认状态标识
	 * 
	 * @param clone_vehicle_id
	 * @param clone_flag
	 * @throws Exception 
	 */
	@RequestMapping(value = "/updateConfirmFlag", method = RequestMethod.POST)
	@ApiOperation(value = "updateConfirmFlag", notes = "更新确认状态标识", httpMethod = "POST")
	public String updateConfirmFlag(@RequestParam("clone_vehicle_id") String clone_vehicle_id,
			@RequestParam("clone_flag") String clone_flag,@RequestParam("confirm_desc") String confirm_desc) throws Exception {
		
		this.cloneCarService.updateConfirmFlag(clone_vehicle_id, clone_flag,confirm_desc);
		return "success";
	}
}
