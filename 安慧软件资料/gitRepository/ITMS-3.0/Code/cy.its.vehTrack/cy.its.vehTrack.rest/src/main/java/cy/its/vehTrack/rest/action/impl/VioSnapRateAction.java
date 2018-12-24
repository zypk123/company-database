package cy.its.vehTrack.rest.action.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import cy.its.device.domain.model.site.Site;
import cy.its.device.domain.service.ISiteService;
import cy.its.vehTrack.domain.service.IBigDataService;
import cy.its.vehTrack.rest.action.IVioSnapRateAction;
import cy.its.vehTrack.rest.dto.VioSnapRateSearchInputBean;

@RestController
@Api(value = "vioSnapRate", description = "违法设备抓拍率分析")
@RequestMapping(value = "vehTrack/vioSnapRate")
public class VioSnapRateAction implements IVioSnapRateAction {
	@Autowired
	IBigDataService bigdataService;
	
	@Autowired
	ISiteService siteService;

	/**
	 * 违法设备的抓拍率
	 * 
	 * @param inputBean
	 * @return JSONObject
	 */
	@Override
	@RequestMapping(value = "/queryVioSnapRate", method = RequestMethod.POST)
	@ApiOperation(value = "queryVioSnapRate", notes = "违法设备抓拍率分析", httpMethod = "POST")
	public JSONObject queryVioSnapRate(@ModelAttribute("inputBean") VioSnapRateSearchInputBean inputBean) {
		// TODO Auto-generated method stub
		inputBean.setOrgAuthorityCode(inputBean.getCurrentOrgPrivilegeCode());
		JSONObject obj = null;
		try {
			String result = this.bigdataService.loaddBigData("vio_snap_rate_service", inputBean.toString());
			obj = JSONObject.parseObject(result);
			
			JSONArray array = obj.getJSONObject("result").getJSONArray("rows");
			JSONObject item;
			Site site;
			for(int i =0;i<array.size();i++){
				item = array.getJSONObject(i);
				site = this.siteService.siteOfCode(item.getString("site_code"));
				if(site !=null){
					item.put("site_name", site.getSiteName());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

}
