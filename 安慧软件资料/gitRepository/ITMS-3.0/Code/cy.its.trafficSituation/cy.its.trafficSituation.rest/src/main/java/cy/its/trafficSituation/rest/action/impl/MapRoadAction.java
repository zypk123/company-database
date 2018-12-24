/**
 * @Title: MapRoadAction.java
 * @Package cy.its.trafficSituation.rest.action.impl
 * @Description: road rest
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月13日 上午10:09:05
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.rest.action.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cy.its.com.util.StringUtil;
import cy.its.platform.common.utils.RedisPoolUtil;
import cy.its.road.domain.service.IRegionService;
import cy.its.trafficSituation.domain.model.MapRoad;
import cy.its.trafficSituation.domain.service.IMapRoadService;
import cy.its.trafficSituation.domain.service.ITrafficMultipleService;
import cy.its.trafficSituation.rest.action.IMapRoadAction;
import cy.its.trafficSituation.rest.dto.MapRoadDto;

@RestController
@RequestMapping("/trafficSituation/mapRoad")
public class MapRoadAction implements IMapRoadAction {
	@Autowired
	IMapRoadService mapRoadService;
	@Autowired
	ITrafficMultipleService multipleService;
	@Autowired
	IRegionService RegionService;
	
	/*
	  * <p>Title: selectAll</p>
	  * <p>Description: </p>
	  * @return
	  * @see cy.its.trafficSituation.rest.action.IMapRoadAction#selectAll()
	  */

	@Override
	@RequestMapping("/selectAll")
	public List<MapRoadDto> selectAll() throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		//System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		Map<String,String>   mapObject= RedisPoolUtil.hgetAll("tf:region");
		List<MapRoadDto> dtos=new ArrayList<MapRoadDto>();
		
		List<MapRoad> mapRoads=mapRoadService.selectAll(new HashMap());
		for (MapRoad mapRoad : mapRoads) {
			MapRoadDto dto=new MapRoadDto(mapRoad);
			String jsonStr = mapObject.get(mapRoad.getRegionalId());
			if(!StringUtil.isNullOrEmpty(jsonStr)){
				JSONObject  obj = JSONObject.parseObject(jsonStr);
				dto.setTrafficState(obj.getString("trafficState"));
				dto.setAvgSpeed(obj.getString("avgSpeed"));
				if(obj.getDate("updateTime") != null){
					dto.setUpdateTime(df.format(obj.getDate("updateTime")));
				}
			}
			
			String  manualState = RedisPoolUtil.get("ms:"+mapRoad.getRegionalId());
			if(!StringUtil.isNullOrEmpty(manualState)){
				JSONObject manualObj=JSONObject.parseObject(manualState);
				//从redis里面取人工干预的状态
				dto.setTrafficState(manualObj.getString("trafficState"));
			}
			dtos.add(dto);
		}
		
		return dtos;
	}
	//（保留）
	/**
	 * 读取区间实时列表
	 * @param currentOrgPrivilegeCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectAllForGrid")
	public Object selectAllForGrid(String currentOrgPrivilegeCode) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Map<String,String>   mapObject= RedisPoolUtil.hgetAll("tf:region");
		List<MapRoadDto> dtos=new ArrayList<MapRoadDto>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgPrivilegeCode", currentOrgPrivilegeCode);
		
		List<MapRoad> mapRoads=mapRoadService.selectAll(map);
		for (MapRoad mapRoad : mapRoads) {
			MapRoadDto dto=new MapRoadDto(mapRoad);
			String jsonStr = mapObject.get(mapRoad.getRegionalId());
			if(!StringUtil.isNullOrEmpty(jsonStr)){
				JSONObject  obj = JSONObject.parseObject(jsonStr);
				dto.setTrafficState(obj.getString("trafficState"));
				dto.setAvgSpeed(obj.getString("avgSpeed"));
				if (obj.getDate("updateTime") != null) {
					dto.setUpdateTime(df.format(obj.getDate("updateTime")));
				}
			}			
			String  manualState = RedisPoolUtil.get("ms:"+mapRoad.getRegionalId());
			if(!StringUtil.isNullOrEmpty(manualState)){
				JSONObject manualObj=JSONObject.parseObject(manualState);
				//从redis里面取人工干预的状态
				dto.setTrafficState(manualObj.getString("trafficState"));
			}
			if(StringUtil.isNullOrEmpty(dto.getTrafficState())){
				dto.setTrafficState("0");
			}
			dtos.add(dto);
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("error", "");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("total", dtos.size());
		jsonObject.put("rows", dtos);
		jsonObj.put("result", jsonObject);
		return jsonObj;
	}
	/*
	  * <p>Title: selectById</p>
	  * <p>Description: </p>
	  * @param cyid
	  * @return
	  * @see cy.its.trafficSituation.rest.action.IMapRoadAction#selectById(java.lang.String)
	  */
	@Override
	@RequestMapping("/selectById")
	public MapRoadDto selectById(String cyid) {
		return new MapRoadDto(mapRoadService.selectById(cyid));
	}
	
	@RequestMapping("/selectRoadCount")
	public int selectRoadCount(String currentOrgPrivilegeCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgPrivilegeCode", currentOrgPrivilegeCode);
		return mapRoadService.selectRoadCount(map);
	}
}
