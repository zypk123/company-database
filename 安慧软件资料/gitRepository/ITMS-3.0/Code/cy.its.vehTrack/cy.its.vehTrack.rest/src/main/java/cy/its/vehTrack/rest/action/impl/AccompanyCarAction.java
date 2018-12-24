package cy.its.vehTrack.rest.action.impl;

import java.util.ArrayList;
import java.util.Date;
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
import cy.its.vehTrack.domain.service.IAccompanyCarService;
import cy.its.vehTrack.rest.action.IAccompanyCarAction;
import cy.its.vehTrack.rest.dto.AccompanyDetailBean;
import cy.its.vehTrack.rest.dto.AccompanySearchInputBean;
import cy.its.vehTrack.rest.dto.AccompanyStatBean;


@RestController
@RequestMapping(value= "/vehTrack/accompanyCar")
@Api(description="伴随车分析", value = "AccompanyCarAction")
public class AccompanyCarAction implements IAccompanyCarAction {

	@Autowired
	IAccompanyCarService service;
	@Autowired
	ISiteService siteService;
	
	@Override
	@RequestMapping(value="findAccompanyCarList",method=RequestMethod.POST)
	@ApiOperation(value="findCollAnalyResList",notes="伴随车分析",httpMethod="POST")
	public Map<String, Object> findAccompanyCarList(AccompanySearchInputBean bean) throws Exception {
		JSONObject obj = this.service.findAccompanyCarList(bean.toString());
		String rows = obj.getJSONObject("result").getString("rows");
		List<AccompanyStatBean> accompanyAreaBeanList = new ArrayList<AccompanyStatBean>();
		if (!StringUtil.isNullOrEmpty(rows)){
		accompanyAreaBeanList = JSONObject.parseArray(rows, AccompanyStatBean.class);
		for (AccompanyStatBean accompanyAreaBean : accompanyAreaBeanList) {
			for (AccompanyDetailBean detailBean : accompanyAreaBean.getDetailList()) {
				Site site = siteService.siteOfCode(detailBean.getSite_code());
				if (site != null) {
					detailBean.setSite_name(site.getSiteName());
				}
			}
		}
		}
		return returnResult(accompanyAreaBeanList, obj.getJSONObject("result").getIntValue("total"));
		/*List<AccompanyStatBean> dangerAreaBeanList = new ArrayList<AccompanyStatBean>();
		List<AccompanyDetailBean> dangerAreaDetaiList = new ArrayList<AccompanyDetailBean>();
		AccompanyStatBean dangerBean = new AccompanyStatBean();
		dangerBean.setPlate_nbr("云A124444");
		dangerBean.setPlate_type("a");
		dangerBean.setAcccount(5);
		dangerBean.setAccplatenbr("云B2222");
		
		AccompanyDetailBean detaiBean = new AccompanyDetailBean();
		detaiBean.setPlate_nbr("云A12345");
		detaiBean.setPlate_type("a");
		detaiBean.setSite_name("高速路口1");
		detaiBean.setAcccount(2);
		detaiBean.setAccpasstime(new Date());
		detaiBean.setImgSource("aaa.jpg");
		detaiBean.setAccImgSource("bb.jpg");
		detaiBean.setAccplatenbr("云B2222");
		dangerAreaDetaiList.add(detaiBean);
		dangerAreaDetaiList.add(detaiBean);
		dangerBean.setDetailList(dangerAreaDetaiList);
		dangerAreaBeanList.add(dangerBean);
		
	return returnResult(dangerAreaBeanList,1);*/
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
