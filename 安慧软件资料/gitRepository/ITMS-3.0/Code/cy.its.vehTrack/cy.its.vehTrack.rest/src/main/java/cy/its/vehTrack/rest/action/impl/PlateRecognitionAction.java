/**
 *name :号牌识别率action
 *author:wangyunqi
 *date:2016/03/14
 * 
 */

package cy.its.vehTrack.rest.action.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wordnik.swagger.annotations.ApiOperation;

import cy.its.com.util.StringUtil;
import cy.its.device.domain.model.Sys;
import cy.its.device.domain.model.SysComponent;
import cy.its.device.domain.model.TollgateSys;
import cy.its.device.domain.model.site.Site;
import cy.its.device.domain.service.ISiteService;
import cy.its.device.domain.service.ISystemAttachService;
import cy.its.device.domain.service.ISystemService;
import cy.its.syscfg.domain.model.duty.Organization;
import cy.its.syscfg.domain.service.IDutyService;
import cy.its.vehTrack.domain.service.IPlateRecognitionService;
import cy.its.vehTrack.rest.action.IPlateRecognitionAction;
import cy.its.vehTrack.rest.dto.PlateRecognition;
import cy.its.vehTrack.rest.dto.PlateRecognitionOutBean;

@RequestMapping(value="/vehTrack/plateRecog")
@RestController
public class PlateRecognitionAction implements IPlateRecognitionAction {
	@Autowired
	IPlateRecognitionService service;
	@Autowired
	ISystemService systemService;
	@Autowired
	ISiteService siteService;
	
	@Autowired
	IDutyService dutyService;	
	@Autowired
	ISystemAttachService systemAttachService;

	@Override
	@RequestMapping(value="/findPlateRecognitionRate",method=RequestMethod.POST)
	@ApiOperation(value="findPlateRecognitionRate",httpMethod="POST",notes="号牌识别率")
	public Object findPlateRecognitionRate(PlateRecognition dto) throws Exception {
		dto.setTotalCount("0");
		dto.setOrgAuthorityCode(dto.getCurrentOrgPrivilegeCode());
		String query=dto.toString();
		String result = (String) this.service.findPlateRecogRate(query).get("result");
		
		if (!"deviceNbr".equals(dto.getStatBy())){
			return JSONObject.parseObject(result);
		}
		
		JSONObject obj = JSONObject.parseObject(result).getJSONObject("result");
		String searchToken = obj.getJSONObject("searchBean").getString("searchToken");
		List<PlateRecognitionOutBean> list = JSONObject.parseArray(obj.getString("rows"), PlateRecognitionOutBean.class);
		if (list ==null){
			return null;
		}
		if ("deviceNbr".equals(dto.getStatBy())){
		dataConvert(list);
		}
		return returnResult(list,Integer.parseInt(obj.getString("total")),searchToken);
		
	}
	
	
	/**
	 * 如果选择设备，需要将点位信息带过来
	 * @param map
	 * @param list
	 */
	public void dataConvert(List<PlateRecognitionOutBean> list){
		Site site =null;
		TollgateSys tolSys =null;
		Organization org=null;
		for(PlateRecognitionOutBean outBean :list){
			if (!StringUtil.isNullOrEmpty(outBean.getDeviceNbr())){
				//Sys sysDevice = this.systemService.selectByNBR(outBean.getDeviceNbr());
				SysComponent sysComponent= this.systemAttachService.componentOfDeviceNbr(outBean.getDeviceNbr());
				if (sysComponent!=null){
					try {
						outBean.setDeviceName(sysComponent.getComponentName());
						tolSys = this.systemService.tollgateSysOfId(sysComponent.getDeviceId());
						Sys sysDevice = this.systemService.systemOfId(sysComponent.getDeviceId());
						site = this.siteService.siteOfId(sysDevice.getSiteId());
						org = this.dutyService.organizationOfId(sysDevice.getOrgId());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (site !=null){
						outBean.setSiteName(site.getSiteName());
					}
					if (tolSys !=null){
						outBean.setTollgateType(tolSys.getTollgateType());;
					}
					if (tolSys !=null){
						outBean.setOrgName(org.orgName);;
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * returnResult 封装返回参数 @Title: returnResult @Description:
	 * 封装返回参数 @param @param dataList 数据 @param @param total 总数 @param @return
	 * 设定文件 @return Map<String,Object> 返回类型 @throws
	 */
	private Map<String, Object> returnResult(List dataList, int total,String searchToken) {
		// 封装返回集
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("error", "");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("searchToken", searchToken);
		resultMap.put("total", total);
		resultMap.put("rows", dataList);
		returnMap.put("result", resultMap);
		return returnMap;
	}


}
