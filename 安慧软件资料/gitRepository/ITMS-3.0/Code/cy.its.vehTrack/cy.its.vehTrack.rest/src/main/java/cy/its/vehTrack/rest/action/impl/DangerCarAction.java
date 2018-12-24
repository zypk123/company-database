/**
 *name :危险驾驶车辆分析
 *author:wangyunqi
 *date:2016/03/14
 * 
 */
package cy.its.vehTrack.rest.action.impl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import cy.its.com.util.StringUtil;
import cy.its.road.domain.criteria.RoadSectionCriteria;
import cy.its.road.domain.model.road.RoadSection;
import cy.its.road.domain.service.IRoadService;
import cy.its.vehTrack.domain.service.IDangerCarService;
import cy.its.vehTrack.rest.action.IDangerCarAction;
import cy.its.vehTrack.rest.dto.DangerCarDto;
import cy.its.vehTrack.rest.dto.DangerCarTopN;
import cy.its.vehTrack.rest.dto.DangerVehWholeOutbean;

@RestController
@Api(value="DangerCarAction",description="危险驾驶车辆分析")
@RequestMapping(value = "/vehTrack/danger")
public class DangerCarAction implements IDangerCarAction {
	
	@Autowired
	IDangerCarService dangerCarService;
	@Autowired
	IRoadService roadService;

	@Override
	@RequestMapping(value="/findDangerCarTopN",method=RequestMethod.POST)
	@ApiOperation(value="findDangerCarTopN",notes="危险驾驶车辆分析TOPN",httpMethod="POST")
	public Map<String, Object> findDangerCarTopN(DangerCarDto dangerDto) throws Exception {
		/*String params = dangerDto.toString();
		return (JSONObject) dangerCarService.findDangerCarTopN(params).get("result");*/
		// 调用大数据平台请求数据
		//dangerDto.setDistrictCode("53");
		String query = "sync=true&creator.id=%s&job.name=%s&job.alias=%s&job.params={\"fromDate\":\"%s\", \"endDate\":\"%s\", \"districtCode\":\"%s\",\"topNum\":\"%s\"}";
		query = String.format(query, dangerDto.getCurrentUserName(), "TopnVioVehAnalysis","TopN危险车辆分析",
				dangerDto.getStartTime().replaceAll("-", ""), dangerDto.getEndTime().replaceAll("-", ""),
				dangerDto.getDistrictCode(),dangerDto.getTopN());
		String result = this.dangerCarService.findDangerCarTopN(query);
		List<DangerCarTopN> list = JSONObject.parseArray(result, DangerCarTopN.class);
		//将数据包装
		return returnResult(list,list.size());
				
	}

	@Override
	@RequestMapping(value="/dangerCarWholeAnylsis",method=RequestMethod.POST)
	@ApiOperation(value="dangerCarWholeAnylsis",notes="危险驾驶车辆整体分析",httpMethod="POST")
	public Map<String, Object> findDangerCarWholeAnylsis(DangerCarDto dangerDto) throws Exception {
		//String params = dangerDto.toString();
		//return (JSONObject) dangerCarService.findDangerCarWholeAnylsis(params).get("result");
		//dangerDto.setDistrictCode("53");
		String query = "sync=true&creator.id=%s&job.name=%s&job.alias=%s&job.params={\"fromDate\":\"%s\", \"endDate\":\"%s\", \"districtCode\":\"%s\",\"minuteInterval\":\"60\"}";
		query = String.format(query, dangerDto.getCurrentUserName(), "RoadSectAnalysis","危险驾驶整体分析",
				dangerDto.getStartTime().replaceAll("-", ""), dangerDto.getEndTime().replaceAll("-", ""),
				dangerDto.getDistrictCode());
		String result = this.dangerCarService.findDangerCarWholeAnylsis(query);
		// 当job完成后且进程状态位正常结束才进行查询
		List<DangerVehWholeOutbean> resultList = new ArrayList<DangerVehWholeOutbean>();
		resultList = JSONObject.parseArray(result, DangerVehWholeOutbean.class);
		resultList = convert(resultList,true);
		//将数据包装
		return returnResult(resultList,resultList.size());
	}

	@Override
	@RequestMapping(value="/dangerCarSimpleAnylsis",method=RequestMethod.POST)
	@ApiOperation(value="dangerCarSimpleAnylsis",notes="危险驾驶车辆单项分析",httpMethod="POST")
	public Map<String, Object> findDangerCarSimpleAnylsis(DangerCarDto dangerDto) throws Exception {
		String query = "sync=true&creator.id=%s&job.name=%s&job.alias=%s&job.params={\"fromDate\":\"%s\", \"endDate\":\"%s\", \"districtCode\":\"%s\",\"minuteInterval\":\"60\",\"vioType\":\"%s\"}";
		query = String.format(query, dangerDto.getCurrentUserName(), "RoadSectAnalysis","危险驾驶联项分析",
				dangerDto.getStartTime().replaceAll("-", ""), dangerDto.getEndTime().replaceAll("-", ""),
				dangerDto.getDistrictCode(),dangerDto.getVioType());
		String result = this.dangerCarService.findDangerCarMulitAnylsis(query);
		// 当job完成后且进程状态位正常结束才进行查询
		List<DangerVehWholeOutbean> resultList = new ArrayList<DangerVehWholeOutbean>();
		resultList = JSONObject.parseArray(result, DangerVehWholeOutbean.class);
		resultList = convert(resultList,true);
		//将数据包装
		return returnResult(resultList,resultList.size());
	}

	@Override
	@RequestMapping(value="/dangerCarMulitAnylsis",method=RequestMethod.POST)
	@ApiOperation(value="dangerCarMulitAnylsis",notes="危险驾驶联项车辆分析",httpMethod="POST")
	public Map<String, Object> findDangerCarMulitAnylsis(DangerCarDto dangerDto) throws Exception {
		String query = "sync=true&creator.id=%s&job.name=%s&job.alias=%s&job.params={\"fromDate\":\"%s\", \"endDate\":\"%s\", \"districtCode\":\"%s\",\"minuteInterval\":\"60\",\"vioTypes\":\"%s\"}";
		query = String.format(query, dangerDto.getCurrentUserName(), "RoadSectAnalysis","危险驾驶联项分析",
				dangerDto.getStartTime().replaceAll("-", ""), dangerDto.getEndTime().replaceAll("-", ""),
				dangerDto.getDistrictCode(),dangerDto.getVioType());
		String result = this.dangerCarService.findDangerCarMulitAnylsis(query);
		// 当job完成后且进程状态位正常结束才进行查询
		List<DangerVehWholeOutbean> resultList = new ArrayList<DangerVehWholeOutbean>();
		resultList = JSONObject.parseArray(result, DangerVehWholeOutbean.class);
		resultList = convert(resultList,false);
		//将数据包装
		return returnResult(resultList,resultList.size());
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
	
	/**
	 * 数据转换
	 * 
	 * @param resultList
	 * @return
	 */
	public  List<DangerVehWholeOutbean> convert(List<DangerVehWholeOutbean> resultList,boolean flag) {
		Map<String, List<DangerVehWholeOutbean>> map = new HashMap<String, List<DangerVehWholeOutbean>>();
		String key;
		// 以路段code位key
		for (DangerVehWholeOutbean outBean : resultList) {
			key = outBean.getRoadSectionCode();
			if (map.containsKey(key)) {
				map.get(key).add(outBean);

			} else {
				List<DangerVehWholeOutbean> list = new ArrayList<DangerVehWholeOutbean>();
				list.add(outBean);
				map.put(key, list);
			}
		}
		List<DangerVehWholeOutbean> outList = new ArrayList<DangerVehWholeOutbean>();
		Map<String, Long> outMap = new LinkedHashMap<String, Long>();
		for (Entry<String, List<DangerVehWholeOutbean>> entry : map.entrySet()) {
			Map<String, List<Long>> vicCodeMap = new LinkedHashMap<String, List<Long>>();
			List<DangerVehWholeOutbean> tempList = entry.getValue();
			Long total = (long) 0;
			// 排序
			sortByHour(tempList);
			String vicCoide;
			for (DangerVehWholeOutbean outBean : tempList) {
				total = Long.sum(total, outBean.getTotal());
				// 以一个小时为单位显示
				String hour = outBean.getHour();
				if (flag){
					//如果是违法code为KEY
				 vicCoide = outBean.getViolationCode();
				}else{
					//如果是违法类型为KEY
				 vicCoide = outBean.getVioType();
				}
				if (outMap.containsKey(hour)) {
					outMap.put(hour, Long.sum(outMap.get(hour), outBean.getTotal()));
				} else {
					outMap.put(hour, outBean.getTotal());
				}
				if (!StringUtil.isNullOrEmpty(vicCoide)) {
					if (vicCodeMap.containsKey(vicCoide)) {
						vicCodeMap.get(vicCoide).add(outBean.getTotal());
					} else {
						List<Long> list = new ArrayList<Long>();
						list.add(outBean.getTotal());
						vicCodeMap.put(vicCoide, list);
					}
				}
			}
			DangerVehWholeOutbean bean = new DangerVehWholeOutbean();
			bean.setRoadCode(tempList.get(0).getRoadCode());
			bean.setRoadSectionCode(tempList.get(0).getRoadSectionCode());
			//根据roadSection获得路段名称（提供给3.0使用）
			if(!StringUtil.isNullOrEmpty(bean.getRoadSectionCode())){
				/*RoadSectionBean roadSection = RoadSectionDictionary.getRoadSectionByCode(bean.getRoadSectionCode());
				if (roadSection!=null){
					bean.setRoadSectionName(roadSection.getRoad_section_name());
				}*/
				RoadSectionCriteria criteria = new RoadSectionCriteria();
				criteria.roadSectionCode = bean.getRoadSectionCode();
				List<RoadSection> roadSectionList= this.roadService.findRoadSectiones(criteria);
				if (roadSectionList != null && roadSectionList.size()>0){
					RoadSection roadSection =roadSectionList.get(0);
					bean.setRoadSectionName(roadSection.getRoadSectionName());
				}
			}
			bean.setxAxis(new ArrayList<String>(outMap.keySet()));
			bean.setyAxis(new ArrayList<Long>(outMap.values()));
			bean.setTotal(total);
			bean.setMap(vicCodeMap);
			outList.add(bean);
			outMap.clear();
		}

		return outList;
	}

	/**
	 * 对list进行排序desc
	 * 
	 * @param resultList
	 */
	public  void sortByHour(List<DangerVehWholeOutbean> resultList) {
		Collections.sort(resultList, new Comparator<DangerVehWholeOutbean>() {
			public int compare(DangerVehWholeOutbean arg0, DangerVehWholeOutbean arg1) {
				// return arg0.getHour().compareTo(arg1.getHour());
				return Integer.valueOf(arg0.getHour()) - Integer.valueOf(arg1.getHour());
			}
		});
	}

}
