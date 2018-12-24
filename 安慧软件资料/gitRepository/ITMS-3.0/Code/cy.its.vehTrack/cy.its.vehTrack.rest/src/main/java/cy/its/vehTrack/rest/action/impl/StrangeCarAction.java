package cy.its.vehTrack.rest.action.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import cy.its.com.util.StringUtil;
import cy.its.device.domain.model.site.Site;
import cy.its.device.domain.service.ISiteService;
import cy.its.vehTrack.domain.service.IBigDataService;
import cy.its.vehTrack.rest.action.IStrangeCarAction;
import cy.its.vehTrack.rest.dto.StrangeCarDto;

@RestController
@Api(value="strangeCarAction",description="新近车辆查询")
@RequestMapping(value="vehTrack/strange")
public class StrangeCarAction implements IStrangeCarAction {
	@Autowired
	IBigDataService service;
	@Autowired
	ISiteService siteService;

	@Override
	@RequestMapping(value = "/queryStrangeCars", method = RequestMethod.POST)
	@ApiOperation(value = "queryStrangeCars", notes = "套牌车分析查询", httpMethod = "POST")
	public JSONObject queryStrangeCarList(@ModelAttribute("dto") StrangeCarDto dto) {
		
		String query = dto.toString();
		JSONObject obj = null;
		try {
			String result = this.service.loaddBigData("strange_car_service", query);
			obj= JSON.parseObject(result);
			JSONArray rows = obj.getJSONObject("result").getJSONArray("rows");
			JSONObject jsonObj;
			Site site;
			String siteCode;
			if(StringUtil.isNullOrEmpty(rows)){
				return obj;
			}
			for(int i =0;i<rows.size();i++){
				jsonObj = rows.getJSONObject(i);
				siteCode = jsonObj.getString("site_code");
				if(StringUtil.isNullOrEmpty(siteCode)){
					continue;
				}
				//获得点位名称
				site =this.siteService.siteOfCode(siteCode);
				if(site !=null){
					jsonObj.put("site_name", site.getSiteName());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return obj;
	}

}
