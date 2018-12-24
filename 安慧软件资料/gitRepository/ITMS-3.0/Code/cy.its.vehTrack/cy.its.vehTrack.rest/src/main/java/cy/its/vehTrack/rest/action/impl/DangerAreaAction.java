package cy.its.vehTrack.rest.action.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import cy.its.com.util.StringUtil;
import cy.its.device.domain.model.site.Site;
import cy.its.device.domain.service.ISiteService;
import cy.its.vehTrack.domain.service.IDangerAreaService;
import cy.its.vehTrack.rest.action.IDangerAreaAction;
import cy.its.vehTrack.rest.dto.DangerAreaDetailBean;
import cy.its.vehTrack.rest.dto.DangerAreaInputBean;
import cy.its.vehTrack.rest.dto.DangerAreaStatBean;

@RestController
@RequestMapping(value = "/vehTrack/dangerArea")
@Api(value = "DangerAreaAction", description = "高危地域车辆分析")
public class DangerAreaAction implements IDangerAreaAction {
	@Autowired
	IDangerAreaService service;
	@Autowired
	ISiteService siteService;

	@Override
	@RequestMapping(value = "findDangerAreaCarList", method = RequestMethod.POST)
	@ApiOperation(value = "findDangerAreaCarList", httpMethod = "POST", notes = "高危地域车辆分析")
	public Map<String, Object> findDangerAreaCarList(DangerAreaInputBean bean) throws Exception {
		bean.setOrgAuthorityCode(bean.getCurrentOrgPrivilegeCode());
		JSONObject obj = this.service.findDangerAreaCarList(bean.toString());
		String rows = obj.getJSONObject("result").getString("rows");
		List<DangerAreaStatBean> dangerAreaBeanList = new ArrayList<DangerAreaStatBean>();
		List<DangerAreaDetailBean> detailBeanList = new ArrayList<DangerAreaDetailBean>();
		if (!StringUtil.isNullOrEmpty(rows)){
			 dangerAreaBeanList = JSONObject.parseArray(rows, DangerAreaStatBean.class);
			for (DangerAreaStatBean dangerAreaBean : dangerAreaBeanList) {
				for (DangerAreaDetailBean detailBean : dangerAreaBean.getDetailList()) {
					detailBean.setPlate_nbr(dangerAreaBean.getPlate_nbr());
					detailBean.setPlate_type(dangerAreaBean.getPlate_type());
					detailBean.setPlateCount(dangerAreaBean.getPlatecount());
					detailBean.setVehicle_brand(dangerAreaBean.getVehicle_brand());
					detailBean.setVehicle_color(dangerAreaBean.getVehicle_color());
					Site site = siteService.siteOfCode(detailBean.getSite_code());
					if (site != null) {
						detailBean.setSite_name(site.getSiteName());
					}
					detailBeanList.add(detailBean);
				}
			}
		}
		return returnResult(detailBeanList, obj.getJSONObject("result").getIntValue("total"));
	}

	/**
	 * 
	 * returnResult 封装返回参数 @Title: returnResult @Description:
	 * 封装返回参数 @param @param dataList 数据 @param @param total 总数 @param @return
	 * 设定文件 @return Map<String,Object> 返回类型 @throws
	 */
	private Map<String, Object> returnResult(List dataList, int total) {
		// 封装返回集
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("error", "");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", total);
		resultMap.put("rows", dataList);
		returnMap.put("result", resultMap);
		return returnMap;
	}

}
