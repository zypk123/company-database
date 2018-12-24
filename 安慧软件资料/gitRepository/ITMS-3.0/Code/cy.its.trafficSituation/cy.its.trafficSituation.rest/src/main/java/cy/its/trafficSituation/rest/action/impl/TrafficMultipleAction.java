/**
 * @Title: TrafficMultipleAction.java
 * @Package cy.its.trafficSituation.rest.action.impl
 * @Description: 综合rest实现类
 * @author gyf guanyf@cychina.cn
 * @date 2015年10月29日 下午4:08:59
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.rest.action.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cy.its.com.constant.SysCodeConstant;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.MeteorologicCriteria;
import cy.its.device.domain.criteria.SiteCriteria;
import cy.its.device.domain.criteria.SystemCriteria;
import cy.its.device.domain.model.MeteorologicSys;
import cy.its.device.domain.model.Sys;
import cy.its.device.domain.model.site.Section;
import cy.its.device.domain.model.site.Site;
import cy.its.device.domain.service.ISiteService;
import cy.its.device.domain.service.ISystemService;
import cy.its.platform.common.utils.RedisPoolUtil;
import cy.its.road.domain.model.region.Region;
import cy.its.road.domain.service.IRegionService;
import cy.its.syscfg.domain.model.duty.Organization;
import cy.its.syscfg.domain.model.traffic.EnforceStation;
import cy.its.syscfg.domain.model.traffic.OverRunCheckPoint;
import cy.its.syscfg.domain.model.traffic.PolicePost;
import cy.its.syscfg.domain.service.IDutyService;
import cy.its.syscfg.domain.service.IEnforceStationService;
import cy.its.syscfg.domain.service.IOverRunCheckPointService;
import cy.its.syscfg.domain.service.IPolicePostService;
import cy.its.trafficSituation.domain.model.MapRoad;
import cy.its.trafficSituation.domain.model.TrafficControl;
import cy.its.trafficSituation.domain.model.TrafficRegionFlow;
import cy.its.trafficSituation.domain.model.TrafficRoadsensor;
import cy.its.trafficSituation.domain.model.TrafficSectionFlow;
import cy.its.trafficSituation.domain.model.TrafficVisibility;
import cy.its.trafficSituation.domain.model.TrafficWeather;
import cy.its.trafficSituation.domain.service.IMapRoadService;
import cy.its.trafficSituation.domain.service.ITrafficControlService;
import cy.its.trafficSituation.domain.service.ITrafficMultipleService;
import cy.its.trafficSituation.rest.action.ITrafficMultipleAction;
import cy.its.trafficSituation.rest.dto.DeviceDto;
import cy.its.trafficSituation.rest.dto.EnforceStationDto;
import cy.its.trafficSituation.rest.dto.MapRoadDto;
import cy.its.trafficSituation.rest.dto.OverRunCheckPointDto;
import cy.its.trafficSituation.rest.dto.PolicePostDto;
import cy.its.trafficSituation.rest.dto.RegionFlowDto;
import cy.its.trafficSituation.rest.dto.RoadStateDto;
import cy.its.trafficSituation.rest.dto.RoadsensorDeviceDto;
import cy.its.trafficSituation.rest.dto.RoadsensorDto;
import cy.its.trafficSituation.rest.dto.SectionFlowDto;
import cy.its.trafficSituation.rest.dto.SiteSectonFlowDto;
import cy.its.trafficSituation.rest.dto.TrafficControlDto;
import cy.its.trafficSituation.rest.dto.VisibilityDeviceDto;
import cy.its.trafficSituation.rest.dto.VisibilityDto;
import cy.its.trafficSituation.rest.dto.WeatherDeviceDto;
import cy.its.trafficSituation.rest.dto.WeatherDto;

@RestController
@RequestMapping("/trafficSituation/trafficState/multiple")
public class TrafficMultipleAction implements ITrafficMultipleAction {

	private static final String DEVICE_STATUS_REDIS_KEY = "deviceStatus";
	@Autowired
	ITrafficMultipleService trafficMultipleService;
	@Autowired
	ISiteService siteService;
	@Autowired
	ISystemService systemService;
	@Autowired
	IMapRoadService mapRoadService;
	@Autowired
	IRegionService RegionService;
	@Autowired
	ITrafficControlService trafficControlService;
	@Autowired
	IDutyService dutyService;

	@Autowired
	IPolicePostService policePostService;
	@Autowired
	IOverRunCheckPointService overRunCheckPointService;
	@Autowired
	IEnforceStationService enforceStationService;

	@RequestMapping("/initQryTrafficSituationData")
	public Map initQryTrafficSituationData(String currentOrgPrivilegeCode) throws Exception {
		Map resMp = new HashMap();
		// 获取地图上的道路区间
		resMp.put("mapRoad", selectRoadByOrgPriCode(currentOrgPrivilegeCode));
		// 获取断面
		resMp.put("siteSection", selectSiteSectionsByOrgPriCode(currentOrgPrivilegeCode));
		// 获取管制
		resMp.put("trafficCtrl", selectValidTrafficCtrl(currentOrgPrivilegeCode));
		// 获取各种设备类型：视频、事件检测器、气象
		resMp.put("videoDevice", selectDeviceByType(SysCodeConstant.DEVICE_TYPE_VIDEO, currentOrgPrivilegeCode));
		resMp.put("eventDevice", selectDeviceByType(SysCodeConstant.DEVICE_TYPE_EVENT, currentOrgPrivilegeCode));
		resMp.put("meteorDevice", qryMeteorDevice(currentOrgPrivilegeCode));
		// 获取各种资源：岗亭、超限检查站、执法服务站、
		resMp.put("policePost", selectPolicePost());
		resMp.put("overRunCheckPoint", selectOverRunCheckPoint());
		resMp.put("enforceStation", selectEnforceStation());
		return resMp;
	}

	@RequestMapping("/selectWeathersForGrid")
	public JSONObject selectWeathersForGrid(String currentOrgPrivilegeCode) throws Exception {
		List<DeviceDto> dtos = new ArrayList<DeviceDto>();
		List<MeteorologicSys> devLst = getMeteorDeviceSys(currentOrgPrivilegeCode);
		if (devLst == null || devLst.size() == 0) {
			retObject(dtos);
		}
		// 过滤只支持气象仪的气象设备
		List<MeteorologicSys> weatherDevLst = devLst.stream()
				.filter(p -> p.getIsWeatherSupport() != null && p.getIsWeatherSupport().equals("1"))
				.collect(Collectors.toList());
		if (weatherDevLst == null || weatherDevLst.size() == 0) {
			retObject(dtos);
		}
		Map<String, String> cacheSys = getAllCacheDeviceStatus();
		for (MeteorologicSys meteorologic : weatherDevLst) {
			DeviceDto dto = new DeviceDto();
			String cacheDev = getCacheDevById(cacheSys, meteorologic.getDeviceId());
			// 需要把缓存的设备状态传递进去进行转换
			dto = convertToWeatherDeviceDto(meteorologic, cacheDev);
			// 没有经纬度的不返回
			if (dto.getLonLat() != null) {
				dtos.add(dto);
			}
		}
		return retObject(dtos);
	}

	// 查询所有气象仪设备
	@RequestMapping("/selectWeatherDev")
	public List<MeteorologicSys> selectWeatherDev(String currentOrgPrivilegeCode) {
		List<MeteorologicSys> list = new ArrayList<MeteorologicSys>();
		List<MeteorologicSys> devLst = getMeteorDeviceSys(currentOrgPrivilegeCode);
		if (devLst == null || devLst.size() == 0) {
			return list;
		}
		// 过滤只支持气象仪的气象设备
		list = devLst.stream().filter(p -> p.getIsWeatherSupport() != null && p.getIsWeatherSupport().equals("1"))
				.collect(Collectors.toList());
		return list;
	}

	@RequestMapping("/selectRoadsensorsForGrid")
	public JSONObject selectRoadSensorsForGrid(String currentOrgPrivilegeCode) throws Exception {
		List<DeviceDto> dtos = new ArrayList<DeviceDto>();
		List<MeteorologicSys> devLst = getMeteorDeviceSys(currentOrgPrivilegeCode);
		if (devLst == null || devLst.size() == 0) {
			retObject(dtos);
		}
		// 过滤只支持路感的气象设备
		List<MeteorologicSys> roadsensorDevLst = devLst.stream()
				.filter(p -> p.getIsRoadsensorSupport() != null && p.getIsRoadsensorSupport().equals("1"))
				.collect(Collectors.toList());
		if (roadsensorDevLst == null || roadsensorDevLst.size() == 0) {
			retObject(dtos);
		}
		Map<String, String> cacheSys = getAllCacheDeviceStatus();
		for (MeteorologicSys meteorologic : roadsensorDevLst) {
			DeviceDto dto = new DeviceDto();
			String cacheDev = getCacheDevById(cacheSys, meteorologic.getDeviceId());
			// 需要把缓存的设备状态传递进去进行转换
			dto = convertToRoadsensorDeviceDto(meteorologic, cacheDev);
			// 没有经纬度的不返回
			if (dto.getLonLat() != null) {
				dtos.add(dto);
			}
		}
		return retObject(dtos);
	}

	// 查询所有路感设备
	@RequestMapping("/selectRoadSensorDev")
	public List<MeteorologicSys> selectRoadSensorDev(String currentOrgPrivilegeCode) {
		List<MeteorologicSys> list = new ArrayList<MeteorologicSys>();
		List<MeteorologicSys> devLst = getMeteorDeviceSys(currentOrgPrivilegeCode);
		if (devLst == null || devLst.size() == 0) {
			return list;
		}
		// 过滤只支持路感的气象设备
		list = devLst.stream().filter(p -> p.getIsRoadsensorSupport() != null && p.getIsRoadsensorSupport().equals("1"))
				.collect(Collectors.toList());
		return list;
	}

	@RequestMapping("/selectVisibilitysForGrid")
	public JSONObject selectVisibilitysForGrid(String currentOrgPrivilegeCode) throws Exception {
		List<DeviceDto> dtos = new ArrayList<DeviceDto>();
		List<MeteorologicSys> devLst = getMeteorDeviceSys(currentOrgPrivilegeCode);
		if (devLst == null || devLst.size() == 0) {
			retObject(dtos);
		}
		// 过滤只支持能见度仪的气象设备
		List<MeteorologicSys> visibilityDevLst = devLst.stream()
				.filter(p -> p.getIsVisibilitySupport() != null && p.getIsVisibilitySupport().equals("1"))
				.collect(Collectors.toList());
		if (visibilityDevLst == null || visibilityDevLst.size() == 0) {
			retObject(dtos);
		}
		Map<String, String> cacheSys = getAllCacheDeviceStatus();
		for (MeteorologicSys meteorologic : visibilityDevLst) {
			DeviceDto dto = new DeviceDto();
			String cacheDev = getCacheDevById(cacheSys, meteorologic.getDeviceId());
			// 需要把缓存的设备状态传递进去进行转换
			dto = convertToRoadsensorDeviceDto(meteorologic, cacheDev);
			// 没有经纬度的不返回
			if (dto.getLonLat() != null) {
				dtos.add(dto);
			}
		}
		return retObject(dtos);
	}

	// 查询所有能见度仪设备
	@RequestMapping("/selectVisibilityDev")
	public List<MeteorologicSys> selectVisibilityDev(String currentOrgPrivilegeCode) {
		List<MeteorologicSys> list = new ArrayList<MeteorologicSys>();
		List<MeteorologicSys> devLst = getMeteorDeviceSys(currentOrgPrivilegeCode);
		if (devLst == null || devLst.size() == 0) {
			return list;
		}
		// 过滤只支持能见度仪的气象设备
		list = devLst.stream().filter(p -> p.getIsVisibilitySupport() != null && p.getIsVisibilitySupport().equals("1"))
				.collect(Collectors.toList());
		return list;
	}

	/**
	 * 组装grid要求的格式
	 * 
	 * @param list
	 * @return
	 */
	private JSONObject retObject(List list) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("error", "");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("total", list.size());
		jsonObject.put("rows", list);
		jsonObj.put("result", jsonObject);
		return jsonObj;
	}

	/**
	 * 查询当前用户下的道路区间
	 * 
	 * @param orgPrivilegeCode
	 * @return
	 * @throws Exception
	 */
	private List<MapRoadDto> selectRoadByOrgPriCode(String orgPrivilegeCode) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		Map<String, String> mapObject = RedisPoolUtil.hgetAll("tf:region");
		List<MapRoadDto> dtos = new ArrayList<MapRoadDto>();
		Map paramMp = new HashMap();
		paramMp.put("orgPrivilegeCode", orgPrivilegeCode);
		List<MapRoad> mapRoads = mapRoadService.selectAll(paramMp);
		for (MapRoad mapRoad : mapRoads) {
			MapRoadDto dto = new MapRoadDto(mapRoad);
			String jsonStr = mapObject.get(mapRoad.getRegionalId());
			if (!StringUtil.isNullOrEmpty(jsonStr)) {
				JSONObject obj = JSONObject.parseObject(jsonStr);
				dto.setTrafficState(obj.getString("trafficState"));
				dto.setAvgSpeed(obj.getString("avgSpeed"));
				if (obj.getDate("updateTime") != null) {
					dto.setUpdateTime(df.format(obj.getDate("updateTime")));
				}
			}
			String manualState = RedisPoolUtil.get("ms:" + mapRoad.getRegionalId());
			if (!StringUtil.isNullOrEmpty(manualState)) {
				JSONObject manualObj = JSONObject.parseObject(manualState);
				// 从redis里面取人工干预的状态
				dto.setTrafficState(manualObj.getString("trafficState"));
			}
			dtos.add(dto);
		}
		return dtos;
	}

	/**
	 * 查询断面，以点位为记录返回
	 * 
	 * @param currentOrgPrivilegeCode
	 * @return
	 * @throws Exception
	 */
	private List<SiteSectonFlowDto> selectSiteSectionsByOrgPriCode(String currentOrgPrivilegeCode) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		List<Site> sites = getSupportFlowSite(currentOrgPrivilegeCode);
		List<Section> sections = siteService
				.sectionsOfSites(sites.stream().map(s -> s.getSiteId()).collect(Collectors.toList()));

		Map<String, List<Section>> mapSiteSection = sections.stream()
				.collect(Collectors.groupingBy(Section::getSiteId));
		// 从redis缓存中取数据
		Map<String, String> mapObject = getCacheSection();
		List<SiteSectonFlowDto> siteSectonFlowDtos = sites.stream().map(s -> {
			// if (s != null && s.getSiteLongitude() != null &&
			// s.getSiteLatitude() != null) {
			List<Section> scs = mapSiteSection.get(s.getSiteId());
			if (scs != null && scs.size() > 0) {
				List<SectionFlowDto> sectionFlowDtos = scs.stream().map(sc -> {
					SectionFlowDto sectionFlowDto = new SectionFlowDto();
					sectionFlowDto.setSectionId(sc.getSectionId());
					sectionFlowDto.setDirectionType(sc.getDirectionType());
					sectionFlowDto.setDirectionName(sc.getDirectionName());
					// 加载缓存数据
					String jsonStr = mapObject.get(sc.getSectionId());
					setRealTimeParamForSectionFlow(sectionFlowDto, jsonStr);
					return sectionFlowDto;

				}).collect(Collectors.toList());

				SiteSectonFlowDto dto = new SiteSectonFlowDto(s);
				dto.setSections(sectionFlowDtos);
				return dto;
			}
			// }
			return null;

		}).filter(d -> d != null).collect(Collectors.toList());
		return siteSectonFlowDtos;
	}

	private List<Site> getSupportFlowSite(String currentOrgPrivilegeCode) throws Exception {
		// 绿灯抓拍功能及流量检测功能
		SystemCriteria criteria = new SystemCriteria();
		criteria.isSupportFlowOrNot = "1";
		criteria.orgPrivilegeCode = currentOrgPrivilegeCode;
		criteria.useStatusFlag = SysCodeConstant.USE_STATUS_USE;
		List<Sys> devLst = systemService.findSys(criteria);
		List<Site> sites = new ArrayList<Site>();
		for (Sys sys : devLst) {
			Site site = siteService.siteOfId(sys.getSiteId());
			if (!sites.contains(site) && site != null) {
				sites.add(site);
			}
		}
		return sites;
	}

	private void setRealTimeParamForSectionFlow(SectionFlowDto sectionFlowDto, String jsonStr) {
		// 加载缓存数据
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (!StringUtil.isNullOrEmpty(jsonStr)) {
			JSONObject obj = JSONObject.parseObject(jsonStr);
			sectionFlowDto.setAvgSpeed(obj.getString("avgSpeed"));
			sectionFlowDto.setLargeNum(obj.getString("largeNum"));
			sectionFlowDto.setOtherNum(obj.getString("otherNum"));
			sectionFlowDto.setSmallNum(obj.getString("smallNum"));
			sectionFlowDto.setTotalNum(obj.getString("totalNum"));
			sectionFlowDto.setVehTailSpace(obj.getString("vehTailSpace"));
			sectionFlowDto.setUpdateTime(df.format(obj.getDate("updateTime")));
		}
	}

	// 获取所有的断面，并从redis获取缓存数据，返回到datagrid中
	@RequestMapping("/selectSiteSectionsByOrgPriCodeForGrid")
	public Object selectSiteSectionsByOrgPriCodeForGrid(String currentOrgPrivilegeCode) throws Exception {
		List<Site> sites = getSupportFlowSite(currentOrgPrivilegeCode);
		// 从redis缓存中取数据
		Map<String, String> mapObject = getCacheSection();

		Map<String, Site> siteMap = sites.stream().filter(s -> s != null)
				.collect(Collectors.toMap(Site::getSiteId, (s) -> s));

		List<Section> sections = siteService.sectionsOfSites(new ArrayList<String>(siteMap.keySet()));

		List<SectionFlowDto> sectionFlowDtos = sections.stream().map(sc -> {
			SectionFlowDto sectionFlowDto = new SectionFlowDto();
			Site site = siteMap.get(sc.getSiteId());
			sectionFlowDto.setSectionId(sc.getSectionId());
			sectionFlowDto.setDirectionType(sc.getDirectionType());
			sectionFlowDto.setDirectionName(sc.getDirectionName());
			sectionFlowDto.setSiteName(site.getSiteName());
			sectionFlowDto.setSiteId(site.getSiteId());
			sectionFlowDto.setRoadId(site.getRoadId());
			if (site.getSiteLongitude() != null && site.getSiteLatitude() != null) {
				sectionFlowDto.setLonLat("POINT(" + site.getSiteLongitude() + " " + site.getSiteLatitude() + ")");
			}
			// 加载缓存数据
			String jsonStr = mapObject.get(sc.getSectionId());
			setRealTimeParamForSectionFlow(sectionFlowDto, jsonStr);
			return sectionFlowDto;
		}).collect(Collectors.toList());

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("error", "");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("total", sectionFlowDtos.size());
		jsonObject.put("rows", sectionFlowDtos);
		jsonObj.put("result", jsonObject);
		return jsonObj;
	}

	/**
	 * 从redis中获取最新的断面信息
	 * 
	 * @return
	 */
	public Map<String, String> getCacheSection() {
		Map<String, String> mapObject = new HashMap<String, String>();
		synchronized (TrafficMultipleAction.class) {
			mapObject = RedisPoolUtil.hgetAll("tf:section");
		}
		return mapObject;
	}

	/**
	 * 获取交通管制信息
	 * 
	 * @param currentOrgPrivilegeCode
	 * @return
	 */
	private List<TrafficControlDto> selectValidTrafficCtrl(String currentOrgPrivilegeCode) {
		List<TrafficControlDto> pDtos = new ArrayList<TrafficControlDto>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgPrivilegeCode", currentOrgPrivilegeCode);
		List<TrafficControl> trafficControls = trafficControlService.selectValid(map);
		for (TrafficControl trafficControl : trafficControls) {
			TrafficControlDto dto = new TrafficControlDto(trafficControl);
			// if (dto.getLonLat() != null) {
			// pDtos.add(dto);
			// }
		}
		return pDtos;
	}

	private List<MeteorologicSys> getMeteorDeviceSys(String currentOrgPrivilegeCode) {
		MeteorologicCriteria criteria = new MeteorologicCriteria();
		criteria.useStatusFlag = SysCodeConstant.USE_STATUS_USE;
		criteria.orgPrivilegeCode = currentOrgPrivilegeCode;
		List<MeteorologicSys> devLst = systemService.findMeteorologicSys(criteria);
		return devLst;
	}

	private List<DeviceDto> qryMeteorDevice(String currentOrgPrivilegeCode) throws Exception {
		List<DeviceDto> dtos = new ArrayList<DeviceDto>();
		List<MeteorologicSys> devLst = getMeteorDeviceSys(currentOrgPrivilegeCode);
		if (devLst == null || devLst.size() == 0) {
			return dtos;
		}
		Map<String, String> cacheSys = getAllCacheDeviceStatus();
		for (MeteorologicSys meteorologic : devLst) {
			DeviceDto dto = new DeviceDto();
			String cacheDev = getCacheDevById(cacheSys, meteorologic.getDeviceId());
			// 需要把缓存的设备状态传递进去进行转换
			if (meteorologic.getIsWeatherSupport() != null && meteorologic.getIsWeatherSupport().equals("1")) {
				dto = convertToWeatherDeviceDto(meteorologic, cacheDev);
			} else if (meteorologic.getIsVisibilitySupport() != null
					&& meteorologic.getIsVisibilitySupport().equals("1")) {
				dto = convertToVisibilityDeviceDto(meteorologic, cacheDev);
			} else if (meteorologic.getIsRoadsensorSupport() != null
					&& meteorologic.getIsRoadsensorSupport().equals("1")) {
				dto = convertToRoadsensorDeviceDto(meteorologic, cacheDev);
			}
			// // 没有经纬度的不返回
			// if (dto.getLonLat() != null) {
			// dtos.add(dto);
			// }
			// 对于气象设备经纬度空判断在前端剔除
			dtos.add(dto);
		}
		return dtos;
	}

	/**
	 * 根据设备类型查询设备
	 * 
	 * @param deviceType
	 * @param currentOrgPrivilegeCode
	 * @return
	 * @throws Exception
	 */
	private List<DeviceDto> selectDeviceByType(String deviceType, String currentOrgPrivilegeCode) throws Exception {
		List<DeviceDto> dtos = new ArrayList<DeviceDto>();
		SystemCriteria criteria = new SystemCriteria();
		criteria.deviceType = deviceType;
		criteria.useStatusFlag = SysCodeConstant.USE_STATUS_USE;
		criteria.orgPrivilegeCode = currentOrgPrivilegeCode;
		List<Sys> devLst = systemService.findSys(criteria);
		if (devLst == null || devLst.size() == 0) {
			return dtos;
		}
		Map<String, String> cacheSys = getAllCacheDeviceStatus();
		for (Sys sys : devLst) {
			String cacheDev = getCacheDevById(cacheSys, sys.getDeviceId());
			// 需要把缓存的设备状态传递进去进行转换
			DeviceDto dto = convertToDeviceDto(sys, cacheDev);
			// 没有经纬度的不返回
			if (dto.getLonLat() != null) {
				dtos.add(dto);
			}
		}
		return dtos;
	}

	/**
	 * 根据机构权限代码查询其管辖的设备
	 * 
	 * @param currentOrgPrivilegeCode
	 * @return
	 */
	private List<Sys> findDevSysByOrgPrivilegeCode(String currentOrgPrivilegeCode) {
		SystemCriteria criteria = new SystemCriteria();
		criteria.useStatusFlag = SysCodeConstant.USE_STATUS_USE;
		criteria.orgPrivilegeCode = currentOrgPrivilegeCode;
		return systemService.findSys(criteria);
	}

	private Sys getSysByNbr(List<Sys> sysLst, String devNbr) {
		if (sysLst == null || sysLst.size() == 0) {
			return null;
		}
		List<Sys> devs = sysLst.stream().filter(p -> p.getDeviceSysNbr().equals(devNbr)).collect(Collectors.toList());
		if (devs == null || devs.size() == 0) {
			return null;
		} else {
			return devs.get(0);
		}
	}

	private List<Site> findSiteByOrgPrivilegeCode(String currentOrgPrivilegeCode) {
		SiteCriteria criteria = new SiteCriteria();
		criteria.orgPrvCode = currentOrgPrivilegeCode;
		return siteService.findSites(criteria);
	}

	private Site getSiteByCode(List<Site> siteLst, String siteCode) {
		if (siteLst == null || siteLst.size() == 0) {
			return null;
		}
		List<Site> sites = siteLst.stream().filter(p -> p.getSiteCode().equals(siteCode)).collect(Collectors.toList());
		if (sites == null || sites.size() == 0) {
			return null;
		} else {
			return sites.get(0);
		}
	}

	public Map<String, String> getAllCacheDeviceStatus() {
		Map<String, String> cacheSys = null;
		synchronized (TrafficMultipleAction.class) {
			cacheSys = RedisPoolUtil.hgetAll(DEVICE_STATUS_REDIS_KEY);
		}
		return cacheSys;
	}

	private String getCacheDevById(Map<String, String> cacheSys, String devId) {
		if (cacheSys == null || cacheSys.size() == 0) {
			return null;
		}
		return cacheSys.get(devId);
	}

	public Map<String, String> getRedisCache(String key) {
		Map<String, String> cache = null;
		synchronized (TrafficMultipleAction.class) {
			cache = RedisPoolUtil.hgetAll(key);
		}
		return cache;
	}

	// sys转化为DeviceDto
	private DeviceDto convertToDeviceDto(Sys s, String cacheDevStr) throws Exception {
		DeviceDto deviceDto = new DeviceDto();
		deviceDto.setDeviceSysNbr(s.getDeviceSysNbr());
		deviceDto.setDeviceName(s.getDeviceName());
		deviceDto.setDeviceStatus(s.getStatusType());// 设备状态
		deviceDto.setDeviceType(s.getDeviceType());
		deviceDto.setDeviceId(s.getDeviceId());

		JSONObject jsonObject = null;
		if (!StringUtil.isNullOrEmpty(cacheDevStr)) {
			jsonObject = JSONObject.parseObject(cacheDevStr);
		}
		if (jsonObject != null) {
			deviceDto.setDeviceStatus(jsonObject.getInteger("statusType").toString());
		}
		Site sysSite = siteService.siteOfId(s.getSiteId());
		Double siteLon = (double) 0;
		Double siteLat = (double) 0;
		String siteName = "";
		if (sysSite != null) {
			siteLon = sysSite.getSiteLongitude() == null ? 0 : sysSite.getSiteLongitude().doubleValue();
			siteLat = sysSite.getSiteLatitude() == null ? 0 : sysSite.getSiteLatitude().doubleValue();
			siteName = sysSite.getSiteName();
		}
		deviceDto.setSiteId(s.getSiteId());
		deviceDto.setSiteName(siteName);
		deviceDto.setLatitude(siteLat);
		deviceDto.setLongitude(siteLon);
		if (siteLon != 0 && siteLat != 0) {
			deviceDto.setLonLat("POINT(" + siteLon + " " + siteLat + ")");
		}
		Organization org = dutyService.organizationOfId(s.getOrgId());
		if (org != null) {
			deviceDto.setOrgName(org.orgName);
			deviceDto.setOrgCode(org.orgCode);
		}
		return deviceDto;
	}

	private void convert2DeviceDto(DeviceDto deviceDto, MeteorologicSys meteorSys, String cacheDevStatusStr)
			throws Exception {
		deviceDto.setDeviceId(meteorSys.getDeviceId());
		deviceDto.setDeviceSysNbr(meteorSys.getDeviceSysNbr());
		deviceDto.setDeviceName(meteorSys.getDeviceName());
		deviceDto.setDeviceStatus(meteorSys.getStatusType());// 设备状态
		JSONObject jsonObject = null;
		if (!StringUtil.isNullOrEmpty(cacheDevStatusStr)) {
			jsonObject = JSONObject.parseObject(cacheDevStatusStr);
		}
		if (jsonObject != null) {
			deviceDto.setDeviceStatus(jsonObject.getInteger("statusType").toString());
		}
		deviceDto.setDeviceType(meteorSys.getDeviceType());

		Site sysSite = siteService.siteOfId(meteorSys.getSiteId());
		Double siteLon = (double) 0;
		Double siteLat = (double) 0;
		String siteName = "";
		if (sysSite != null) {
			siteLon = sysSite.getSiteLongitude() == null ? 0 : sysSite.getSiteLongitude().doubleValue();
			siteLat = sysSite.getSiteLatitude() == null ? 0 : sysSite.getSiteLatitude().doubleValue();
			siteName = sysSite.getSiteName();

		}
		deviceDto.setSiteId(meteorSys.getSiteId());
		deviceDto.setSiteName(siteName);
		deviceDto.setLatitude(siteLat);
		deviceDto.setLongitude(siteLon);
		if (siteLon != 0 && siteLat != 0) {
			deviceDto.setLonLat("POINT(" + siteLon + " " + siteLat + ")");
		}
		Organization org = dutyService.organizationOfId(meteorSys.getOrgId());
		if (org != null) {
			deviceDto.setOrgName(org.orgName);
			deviceDto.setOrgCode(org.orgCode);
		}
	}

	// 气象仪设备转化为DeviceDto
	private WeatherDeviceDto convertToWeatherDeviceDto(MeteorologicSys meteorSys, String cacheDevStatusStr)
			throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		Map<String, String> mapObject = getRedisCache("tf:weather");
		WeatherDeviceDto deviceDto = new WeatherDeviceDto();
		deviceDto.setWeatherType("weather");
		// 加载缓存数据
		String jsonStr = mapObject.get(meteorSys.getDeviceSysNbr());
		if (!StringUtil.isNullOrEmpty(jsonStr)) {
			JSONObject obj = JSONObject.parseObject(jsonStr);
			deviceDto.setAirPressure(obj.getString("airPressure"));
			deviceDto.setRainStrong(obj.getString("rainStrong"));
			deviceDto.setRecordTime(df.format(obj.getDate("recordTime")));
			deviceDto.setRelativeHumidity(obj.getString("relativeHumidity"));
			deviceDto.setWaterFilmHeight(obj.getString("waterFilmHeight"));
			deviceDto.setWaterType(obj.getString("waterType"));
			deviceDto.setWeatherTemperature(obj.getString("weatherTemperature"));
			deviceDto.setWindDirection(obj.getString("windDirection"));
			deviceDto.setWindSpeed(obj.getString("windSpeed"));
		}
		convert2DeviceDto(deviceDto, meteorSys, cacheDevStatusStr);
		return deviceDto;
	}

	// 能见度仪设备转化为DTO
	private VisibilityDeviceDto convertToVisibilityDeviceDto(MeteorologicSys meteorSys, String cacheDevStatusStr)
			throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		Map<String, String> mapObject = getRedisCache("tf:visibility");
		VisibilityDeviceDto deviceDto = new VisibilityDeviceDto();
		deviceDto.setWeatherType("visibility");
		// 加载缓存数据
		String jsonStr = mapObject.get(meteorSys.getDeviceSysNbr());
		if (!StringUtil.isNullOrEmpty(jsonStr)) {
			JSONObject obj = JSONObject.parseObject(jsonStr);
			deviceDto.setCaseTemperature(obj.getString("caseTemperature"));
			deviceDto.setCleanDegre(obj.getString("cleanDegre"));
			deviceDto.setRecordTime(df.format(obj.getDate("recordTime")));
			deviceDto.setOneMinuteVisibility(obj.getString("oneMinuteVisibility"));
			deviceDto.setPowerVolatage(obj.getString("powerVolatage"));
			deviceDto.setTenMinuteVisibility(obj.getString("tenMinuteVisibility"));
		}
		convert2DeviceDto(deviceDto, meteorSys, cacheDevStatusStr);
		return deviceDto;
	}

	// 路感设备转化为DTO
	private RoadsensorDeviceDto convertToRoadsensorDeviceDto(MeteorologicSys meteorSys, String cacheDevStatusStr)
			throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		Map<String, String> mapObject = getRedisCache("tf:roadsensor");
		RoadsensorDeviceDto deviceDto = new RoadsensorDeviceDto();
		deviceDto.setWeatherType("roadsensor");
		// 加载缓存数据
		String jsonStr = mapObject.get(meteorSys.getDeviceSysNbr());
		if (!StringUtil.isNullOrEmpty(jsonStr)) {
			JSONObject obj = JSONObject.parseObject(jsonStr);
			deviceDto.setFreezingTemperature(obj.getString("freezingTemperature"));
			deviceDto.setRoadbedTemperature(obj.getString("roadbedTemperature"));
			deviceDto.setRecordTime(df.format(obj.getDate("recordTime")));
			deviceDto.setRoadCondition(obj.getString("roadCondition"));
			deviceDto.setRoadTemperature(obj.getString("roadTemperature"));
			deviceDto.setSalinity(obj.getString("salinity"));
			deviceDto.setWaterFileHeigh(obj.getString("waterFileHeigh"));
		}
		convert2DeviceDto(deviceDto, meteorSys, cacheDevStatusStr);
		return deviceDto;
	}

	/**
	 * 查询交警岗亭
	 * 
	 * @return
	 */
	private List<PolicePostDto> selectPolicePost() {
		List<PolicePostDto> policePostDtos = new ArrayList<PolicePostDto>();
		List<PolicePost> policePosts = policePostService.selectAll(new HashMap());
		for (PolicePost policePost : policePosts) {
			PolicePostDto dto = new PolicePostDto(policePost);
			if (dto.getLonLat() != null) {
				policePostDtos.add(dto);
			}
		}
		return policePostDtos;
	}

	/**
	 * 查询超限检查站
	 * 
	 * @return
	 */
	private List<OverRunCheckPointDto> selectOverRunCheckPoint() {
		List<OverRunCheckPointDto> pDtos = new ArrayList<OverRunCheckPointDto>();
		List<OverRunCheckPoint> overRunCheckPoints = overRunCheckPointService.selectAll(new HashMap());
		for (OverRunCheckPoint overRunCheckPoint : overRunCheckPoints) {
			OverRunCheckPointDto dto = new OverRunCheckPointDto(overRunCheckPoint);
			if (dto.getLonLat() != null) {
				pDtos.add(dto);
			}
		}
		return pDtos;
	}

	/**
	 * 查询执法服务站
	 * 
	 * @return
	 */
	private List<EnforceStationDto> selectEnforceStation() {
		List<EnforceStationDto> pDtos = new ArrayList<EnforceStationDto>();
		List<EnforceStation> enforceStations = enforceStationService.selectAll(new HashMap());
		for (EnforceStation enforceStation : enforceStations) {
			EnforceStationDto dto = new EnforceStationDto(enforceStation);
			if (dto.getLonLat() != null) {
				pDtos.add(dto);
			}
		}
		return pDtos;
	}

	/***************************************************************************************/

	/*
	 * <p>Title: selectRegionFlows</p> <p>Description:按条件查询区间流量列表 </p>
	 * 
	 * @param map
	 * 
	 * @return
	 * 
	 * @see cy.its.trafficSituation.rest.action.ITrafficMultipleAction#
	 * selectRegionFlows(java.util.Map)
	 */
	@Override
	@RequestMapping("/searchRegions")
	public Object selectRegionFlows(RegionFlowDto regionFlowDto) throws Exception {
		Integer pageNow = Integer.valueOf(regionFlowDto.getPageNumber());
		Integer pageSize = Integer.valueOf(regionFlowDto.getPageSize());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtil.isNullOrEmpty(regionFlowDto.getStartTime())) {
			map.put("startTime", sdf.parse(regionFlowDto.getStartTime()));
		}
		if (!StringUtil.isNullOrEmpty(regionFlowDto.getEndTime())) {
			map.put("endTime", sdf.parse(regionFlowDto.getEndTime()));
		}
		if (!StringUtil.isNullOrEmpty(regionFlowDto.getRoadId())) {
			map.put("roadId", regionFlowDto.getRoadId());
		}
		map.put("orgPrivilegeCode", regionFlowDto.getCurrentOrgPrivilegeCode());

		PageHelper.startPage(pageNow, pageSize);
		Page pageRs = (Page) trafficMultipleService.selectRegionFlows(map);
		List<RegionFlowDto> pDtos = new ArrayList<RegionFlowDto>();
		List<TrafficRegionFlow> regions = pageRs.getResult();
		for (TrafficRegionFlow regionFlow : regions) {
			RegionFlowDto dto = new RegionFlowDto(regionFlow);

			// Region region =
			// RegionService.regionOfId(regionFlow.getRegionalId());
			// if (region != null) {
			// if (region.getDirectionType() != null) {
			// dto.setDirectionType(region.getDirectionType());
			// }
			if (dto.getEntrySiteId() != null) {
				Site siteS = siteService.siteOfId(dto.getEntrySiteId());
				if (siteS != null) {
					dto.setLonlatStart("POINT(" + siteS.getSiteLongitude() + " " + siteS.getSiteLatitude() + ")");
				}
			}
			if (dto.getExitSiteId() != null) {
				Site siteE = siteService.siteOfId(dto.getExitSiteId());
				if (siteE != null) {
					dto.setLonlatEnd("POINT(" + siteE.getSiteLongitude() + " " + siteE.getSiteLatitude() + ")");
				}
			}
			pDtos.add(dto);
			// }
		}
		return parseToJson(pageRs, pDtos);
	}

	@RequestMapping("/searchRegionsByTime")
	public List<TrafficRegionFlow> selectRegionFlowsByTime(String regionalId, String updateTime) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date filterTime = new Date();
		if (!StringUtil.isNullOrEmpty(updateTime)) {
			filterTime = sdf.parse(updateTime);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("regionalId", regionalId);
		map.put("updateTime", filterTime);
		List<TrafficRegionFlow> regionFlows = trafficMultipleService.select2HourRegionFlow(map);
		return regionFlows;
	}

	/*
	 * <p>Title: selectSectionFlows</p> <p>Description:按条件查询断面流量列表 （保留）</p>
	 * 
	 * @param sectionFlowDto
	 * 
	 * @return
	 * 
	 * @throws ParseException
	 * 
	 * @throws Exception
	 * 
	 * @see cy.its.trafficSituation.rest.action.ITrafficMultipleAction#
	 * selectSectionFlows(cy.its.trafficSituation.rest.dto.SectionFlowDto)
	 */
	@Override
	@RequestMapping("/searchSections")
	public Object selectSectionFlows(SectionFlowDto sectionFlowDto) throws ParseException, Exception {
		Integer pageNow = Integer.valueOf(sectionFlowDto.getPageNumber());
		Integer pageSize = Integer.valueOf(sectionFlowDto.getPageSize());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtil.isNullOrEmpty(sectionFlowDto.getStartTime())) {
			map.put("startTime", sdf.parse(sectionFlowDto.getStartTime()));
		}
		if (!StringUtil.isNullOrEmpty(sectionFlowDto.getEndTime())) {
			map.put("endTime", sdf.parse(sectionFlowDto.getEndTime()));
		}
		if (!StringUtil.isNullOrEmpty(sectionFlowDto.getRoadId())) {
			map.put("roadId", sectionFlowDto.getRoadId());
		}
		map.put("orgPrivilegeCode", sectionFlowDto.getCurrentOrgPrivilegeCode());

		PageHelper.startPage(pageNow, pageSize);
		Page pageRs = (Page) trafficMultipleService.selectSectionFlows(map);
		List<SectionFlowDto> pDtos = new ArrayList<SectionFlowDto>();
		List<TrafficSectionFlow> sectionFlows = pageRs.getResult();
		for (TrafficSectionFlow sectionFlow : sectionFlows) {
			SectionFlowDto dto = new SectionFlowDto(sectionFlow);
			if (dto.getSiteId() != null) {
				Site siteS = siteService.siteOfId(dto.getSiteId());
				if (siteS != null) {
					if (siteS.getSiteLongitude() != null && siteS.getSiteLatitude() != null) {
						dto.setLonLat("POINT(" + siteS.getSiteLongitude().toString() + " "
								+ siteS.getSiteLatitude().toString() + ")");
					}
				}
			}
			pDtos.add(dto);
		}
		return parseToJson(pageRs, pDtos);
	}

	@RequestMapping("/searchSectionsByTime")
	public List<TrafficSectionFlow> selectSectionFlowsByTime(String sectionId, String updateTime)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date filterTime = new Date();
		if (!StringUtil.isNullOrEmpty(updateTime)) {
			filterTime = sdf.parse(updateTime);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sectionId", sectionId);
		map.put("updateTime", filterTime);
		List<TrafficSectionFlow> sectionFlows = trafficMultipleService.select2HourSectionFlow(map);// trafficMultipleService.selectSectionFlows(map);
		return sectionFlows;
	}

	/*
	 * <p>Title: selectWeathers</p> <p>Description: 按条件查询气象数据列表（保留）</p>
	 * 
	 * @param weatherDto
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 * @see cy.its.trafficSituation.rest.action.ITrafficMultipleAction#
	 * selectWeathers(cy.its.trafficSituation.rest.dto.WeatherDto)
	 */

	@Override
	@RequestMapping("/searchWeathers")
	public Object selectWeathers(WeatherDto weatherDto) throws Exception {
		Integer pageNow = Integer.valueOf(weatherDto.getPageNumber());
		Integer pageSize = Integer.valueOf(weatherDto.getPageSize());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtil.isNullOrEmpty(weatherDto.getStartTime())) {
			map.put("startTime", sdf.parse(weatherDto.getStartTime()));
		}
		if (!StringUtil.isNullOrEmpty(weatherDto.getEndTime())) {
			map.put("endTime", sdf.parse(weatherDto.getEndTime()));
		}
		if (!StringUtil.isNullOrEmpty(weatherDto.getDeviceSysNbr())) {
			map.put("deviceSysNbr", weatherDto.getDeviceSysNbr());
		}
		map.put("orgPrivilegeCode", weatherDto.getCurrentOrgPrivilegeCode());
		PageHelper.startPage(pageNow, pageSize);
		Page pageRs = (Page) trafficMultipleService.selectWeathers(map);
		List<WeatherDto> pDtos = new ArrayList<WeatherDto>();
		List<TrafficWeather> weathers = pageRs.getResult();

		List<Sys> devLst = findDevSysByOrgPrivilegeCode(weatherDto.getCurrentOrgPrivilegeCode());
		List<Site> siteLst = findSiteByOrgPrivilegeCode(weatherDto.getCurrentOrgPrivilegeCode());

		for (TrafficWeather weather : weathers) {
			WeatherDto dto = new WeatherDto(weather);
			String nbr = dto.getDeviceSysNbr();
			if (!StringUtil.isNullOrEmpty(nbr)) {
				Sys device = getSysByNbr(devLst, nbr);
				if (device != null) {
					if (device.getDeviceName() != null) {
						dto.setDeviceName(device.getDeviceName());
					}
				}
			}
			String siteCode = dto.getSiteCode();
			if (!StringUtil.isNullOrEmpty(siteCode)) {
				Site site = getSiteByCode(siteLst, siteCode);
				if (site != null) {
					dto.setSiteName(site.getSiteName());
					if (site.getSiteLongitude() != null && site.getSiteLatitude() != null) {
						String wkt = "POINT(" + site.getSiteLongitude().toString() + " "
								+ site.getSiteLatitude().toString() + ")";
						dto.setLonLat(wkt);
					}
				}
			}
			pDtos.add(dto);
		}
		return parseToJson(pageRs, pDtos);
	}

	@RequestMapping("/searchWeathersByTime")
	public List<TrafficWeather> selectWeathersByTime(String deviceSysNbr, String recordTime) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date filterTime = new Date();
		if (!StringUtil.isNullOrEmpty(recordTime)) {
			filterTime = sdf.parse(recordTime);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceSysNbr", deviceSysNbr);
		map.put("recordTime", filterTime);
		List<TrafficWeather> weathers = trafficMultipleService.select2HourWeathers(map);
		return weathers;
	}

	/**
	 * 
	 * @Title: parseToJson @Description: 转为Json @param @param
	 *         pageRs @param @param obj @param @return 设定文件 @return JSONObject
	 *         返回类型 @throws
	 */
	private JSONObject parseToJson(Page pageRs, Object obj) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("error", "");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("total", pageRs.getTotal());
		jsonObject.put("rows", obj);
		jsonObj.put("result", jsonObject);
		return jsonObj;
	}

	/*
	 * <p>Title: selectVisibilitys</p> <p>Description:按条件查询能见度数据列表 （保留）</p>
	 * 
	 * @param visibilityDto
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 * @see cy.its.trafficSituation.rest.action.ITrafficMultipleAction#
	 * selectVisibilitys(cy.its.trafficSituation.rest.dto.VisibilityDto)
	 */
	@Override
	@RequestMapping("/searchVisibilitys")
	public Object selectVisibilitys(VisibilityDto visibilityDto) throws Exception {
		Integer pageNow = Integer.valueOf(visibilityDto.getPageNumber());
		Integer pageSize = Integer.valueOf(visibilityDto.getPageSize());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtil.isNullOrEmpty(visibilityDto.getStartTime())) {
			map.put("startTime", sdf.parse(visibilityDto.getStartTime()));
		}
		if (!StringUtil.isNullOrEmpty(visibilityDto.getEndTime())) {
			map.put("endTime", sdf.parse(visibilityDto.getEndTime()));
		}
		if (!StringUtil.isNullOrEmpty(visibilityDto.getDeviceSysNbr())) {
			map.put("deviceSysNbr", visibilityDto.getDeviceSysNbr());
		}
		map.put("orgPrivilegeCode", visibilityDto.getCurrentOrgPrivilegeCode());
		PageHelper.startPage(pageNow, pageSize);
		Page pageRs = (Page) trafficMultipleService.selectVisibilitys(map);
		List<VisibilityDto> pDtos = new ArrayList<VisibilityDto>();
		List<TrafficVisibility> visibilities = pageRs.getResult();

		List<Sys> devLst = findDevSysByOrgPrivilegeCode(visibilityDto.getCurrentOrgPrivilegeCode());
		List<Site> siteLst = findSiteByOrgPrivilegeCode(visibilityDto.getCurrentOrgPrivilegeCode());

		for (TrafficVisibility trafficVisibility : visibilities) {
			VisibilityDto dto = new VisibilityDto(trafficVisibility);
			String nbr = dto.getDeviceSysNbr();
			if (!StringUtil.isNullOrEmpty(nbr)) {
				Sys device = getSysByNbr(devLst, nbr);
				if (device != null) {
					if (device.getDeviceName() != null) {
						dto.setDeviceName(device.getDeviceName());
					}
				}
			}
			String siteCode = dto.getSiteCode();
			if (!StringUtil.isNullOrEmpty(siteCode)) {
				Site site = getSiteByCode(siteLst, siteCode);
				if (site != null) {
					dto.setSiteName(site.getSiteName());
					if (site.getSiteLongitude() != null && site.getSiteLatitude() != null) {
						String wkt = "POINT(" + site.getSiteLongitude().toString() + " "
								+ site.getSiteLatitude().toString() + ")";
						dto.setLonLat(wkt);
					}
				}
			}
			pDtos.add(dto);
		}
		return parseToJson(pageRs, pDtos);
	}

	@RequestMapping("/searchVisibilitysByTime")
	public List<TrafficVisibility> selectVisibilitysByTime(String deviceSysNbr, String recordTime) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date filterTime = new Date();
		if (!StringUtil.isNullOrEmpty(recordTime)) {
			filterTime = sdf.parse(recordTime);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceSysNbr", deviceSysNbr);
		map.put("recordTime", filterTime);
		List<TrafficVisibility> visibilitys = trafficMultipleService.select2HourVisibilitys(map);
		return visibilitys;
	}

	/*
	 * <p>Title: selectRoadsensors</p> <p>Description:按条件查询路感数据列表 （保留）</p>
	 * 
	 * @param roadsensorDto
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 * @see cy.its.trafficSituation.rest.action.ITrafficMultipleAction#
	 * selectRoadsensors(cy.its.trafficSituation.rest.dto.RoadsensorDto)
	 */
	@Override
	@RequestMapping("/searchRoadsensors")
	public Object selectRoadsensors(RoadsensorDto roadsensorDto) throws Exception {
		Integer pageNow = Integer.valueOf(roadsensorDto.getPageNumber());
		Integer pageSize = Integer.valueOf(roadsensorDto.getPageSize());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtil.isNullOrEmpty(roadsensorDto.getStartTime())) {
			map.put("startTime", sdf.parse(roadsensorDto.getStartTime()));
		}
		if (!StringUtil.isNullOrEmpty(roadsensorDto.getEndTime())) {
			map.put("endTime", sdf.parse(roadsensorDto.getEndTime()));
		}
		if (!StringUtil.isNullOrEmpty(roadsensorDto.getDeviceSysNbr())) {
			map.put("deviceSysNbr", roadsensorDto.getDeviceSysNbr());
		}
		map.put("orgPrivilegeCode", roadsensorDto.getCurrentOrgPrivilegeCode());
		PageHelper.startPage(pageNow, pageSize);
		Page pageRs = (Page) trafficMultipleService.selectRoadsensors(map);
		List<RoadsensorDto> pDtos = new ArrayList<RoadsensorDto>();
		List<TrafficRoadsensor> roadsensors = pageRs.getResult();

		List<Sys> devLst = findDevSysByOrgPrivilegeCode(roadsensorDto.getCurrentOrgPrivilegeCode());
		List<Site> siteLst = findSiteByOrgPrivilegeCode(roadsensorDto.getCurrentOrgPrivilegeCode());

		for (TrafficRoadsensor roadsensor : roadsensors) {
			RoadsensorDto dto = new RoadsensorDto(roadsensor);
			String nbr = dto.getDeviceSysNbr();
			if (!StringUtil.isNullOrEmpty(nbr)) {
				Sys device = getSysByNbr(devLst, nbr);
				if (device != null) {
					if (device.getDeviceName() != null) {
						dto.setDeviceName(device.getDeviceName());
					}
				}
			}
			String siteCode = dto.getSiteCode();
			if (!StringUtil.isNullOrEmpty(siteCode)) {
				Site site = getSiteByCode(siteLst, siteCode);
				if (site != null) {
					dto.setSiteName(site.getSiteName());
					if (site.getSiteLongitude() != null && site.getSiteLatitude() != null) {
						String wkt = "POINT(" + site.getSiteLongitude().toString() + " "
								+ site.getSiteLatitude().toString() + ")";
						dto.setLonLat(wkt);
					}
				}
			}
			pDtos.add(dto);
		}
		return parseToJson(pageRs, pDtos);
	}

	@RequestMapping("/searchRoadsensorsByTime")
	public List<TrafficRoadsensor> selectRoadsensorsByTime(String deviceSysNbr, String recordTime) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date filterTime = new Date();
		if (!StringUtil.isNullOrEmpty(recordTime)) {
			filterTime = sdf.parse(recordTime);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceSysNbr", deviceSysNbr);
		map.put("recordTime", filterTime);
		List<TrafficRoadsensor> roadsensors = trafficMultipleService.select2HourRoadsensors(map);
		return roadsensors;
	}

	/*
	 * <p>Title: selectRoadStates</p> <p>Description:查询历史路况数据 </p>
	 * 
	 * @param roadStateDto
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 * @see cy.its.trafficSituation.rest.action.ITrafficMultipleAction#
	 * selectRoadStates(cy.its.trafficSituation.rest.dto.RoadStateDto)
	 */

	@Override
	@RequestMapping("/selectRoadStates")
	public Object selectRoadStates(RoadStateDto roadStateDto) throws Exception {
		Integer pageNow = Integer.valueOf(roadStateDto.getPageNumber());
		Integer pageSize = Integer.valueOf(roadStateDto.getPageSize());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtil.isNullOrEmpty(roadStateDto.getStartTime())) {
			map.put("startTime", sdf.parse(roadStateDto.getStartTime()));
		}
		if (!StringUtil.isNullOrEmpty(roadStateDto.getEndTime())) {
			map.put("endTime", sdf.parse(roadStateDto.getEndTime()));
		}
		if (!StringUtil.isNullOrEmpty(roadStateDto.getRoadId())) {
			map.put("roadId", roadStateDto.getRoadId());
		}
		if (!StringUtil.isNullOrEmpty(roadStateDto.getTrafficState())) {
			map.put("trafficState", roadStateDto.getTrafficState());
		}
		map.put("orgPrivilegeCode", roadStateDto.getCurrentOrgPrivilegeCode());

		PageHelper.startPage(pageNow, pageSize);
		Page pageRs = (Page) trafficMultipleService.selectRegionFlows(map);
		List<RoadStateDto> pDtos = new ArrayList<RoadStateDto>();

		List<TrafficRegionFlow> regions = pageRs.getResult();

		List<MapRoad> mapRoads = mapRoadService.selectAll(map);
		for (TrafficRegionFlow region : regions) {
			if (region.getRegionalId() != null) {
				MapRoad road = null;
				if (mapRoads != null && mapRoads.size() > 0) {
					List<MapRoad> mapRoadsTem = mapRoads.stream()
							.filter(p -> p.getRegionalId().equals(region.getRegionalId())).collect(Collectors.toList());
					if (mapRoadsTem != null && mapRoadsTem.size() > 0) {
						road = mapRoadsTem.get(0);
					}
				}
				RoadStateDto dto = new RoadStateDto(region, road);
				pDtos.add(dto);
			}
		}
		return parseToJson(pageRs, pDtos);
	}

	/*
	 * <p>Title: selectSiteSections</p> <p>Description:获取点位（断面及流量） </p>
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 * @see cy.its.trafficSituation.rest.action.ITrafficMultipleAction#
	 * selectSiteSections()
	 */

	@Override
	@RequestMapping("/selectSiteSections")
	public List<SiteSectonFlowDto> selectSiteSections(String currentOrgPrivilegeCode) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式

		// 绿灯抓拍功能及流量检测功能
		SystemCriteria criteria = new SystemCriteria();
		// todo 代码调整，临时注释
		// criteria.lstFuncCodeList = Arrays.asList("1011", "1012");
		criteria.orgPrivilegeCode = currentOrgPrivilegeCode;
		List<Sys> devLst = systemService.findSystems(criteria);

		List<Site> sites = new ArrayList<Site>();
		for (Sys sys : devLst) {
			if (!StringUtil.isNullOrEmpty(sys.getUseStatusFlag())) {
				if (sys.getUseStatusFlag().equals("1") || sys.getUseStatusFlag().equals("2")) {
					Site site = siteService.siteOfId(sys.getSiteId());
					if (!sites.contains(site) && site != null) {
						sites.add(site);
					}
				}
			}
		}
		// siteService.findSectionsAndSetIntoSiteList(sites);
		//
		// // 从redis缓存中取数据
		// Map<String, String> mapObject = RedisPoolUtil.hgetAll("tf:section");
		//
		// for (Site site : sites) {
		// if (site != null) {
		// if (site.getSiteLongitude() != null && site.getSiteLatitude() !=
		// null) {
		// SiteSectonFlowDto dto = new SiteSectonFlowDto(site);
		// List<SectionFlowDto> sectionFlowDtos = new
		// ArrayList<SectionFlowDto>();
		// for (Section section : site.getSections()) {
		// SectionFlowDto sectionFlowDto = new SectionFlowDto();
		// sectionFlowDto.setSectionId(section.getSectionId());
		// sectionFlowDto.setDirectionType(section.getDirectionType());
		// sectionFlowDto.setDirectionName(section.getDirectionName());
		// // 加载缓存数据
		// String jsonStr = mapObject.get(section.getSectionId());
		// if (!StringUtil.isNullOrEmpty(jsonStr)) {
		// JSONObject obj = JSONObject.parseObject(jsonStr);
		// sectionFlowDto.setAvgSpeed(obj.getString("avgSpeed"));
		// sectionFlowDto.setLargeNum(obj.getString("largeNum"));
		// sectionFlowDto.setOtherNum(obj.getString("otherNum"));
		// sectionFlowDto.setSmallNum(obj.getString("smallNum"));
		// sectionFlowDto.setTotalNum(obj.getString("totalNum"));
		// sectionFlowDto.setVehTailSpace(obj.getString("vehTailSpace"));
		// sectionFlowDto.setUpdateTime(df.format(obj.getDate("updateTime")));
		// }
		// sectionFlowDtos.add(sectionFlowDto);
		// }
		// if (sectionFlowDtos.size() > 0) {
		// dto.setSections(sectionFlowDtos);
		// }
		// if (dto.getSections().size() > 0) {
		// siteSectonFlowDtos.add(dto);
		// }
		// }
		// }
		// }

		List<Section> sections = siteService
				.sectionsOfSites(sites.stream().map(s -> s.getSiteId()).collect(Collectors.toList()));

		Map<String, List<Section>> mapSiteSection = sections.stream()
				.collect(Collectors.groupingBy(Section::getSiteId));

		// 从redis缓存中取数据
		Map<String, String> mapObject = RedisPoolUtil.hgetAll("tf:section");

		List<SiteSectonFlowDto> siteSectonFlowDtos = sites.stream().map(s -> {
			if (s != null && s.getSiteLongitude() != null && s.getSiteLatitude() != null) {
				List<Section> scs = mapSiteSection.get(s.getSiteId());
				if (scs != null && scs.size() > 0) {
					List<SectionFlowDto> sectionFlowDtos = scs.stream().map(sc -> {
						SectionFlowDto sectionFlowDto = new SectionFlowDto();
						sectionFlowDto.setSectionId(sc.getSectionId());
						sectionFlowDto.setDirectionType(sc.getDirectionType());
						sectionFlowDto.setDirectionName(sc.getDirectionName());
						// 加载缓存数据
						String jsonStr = mapObject.get(sc.getSectionId());
						if (!StringUtil.isNullOrEmpty(jsonStr)) {
							JSONObject obj = JSONObject.parseObject(jsonStr);
							sectionFlowDto.setAvgSpeed(obj.getString("avgSpeed"));
							sectionFlowDto.setLargeNum(obj.getString("largeNum"));
							sectionFlowDto.setOtherNum(obj.getString("otherNum"));
							sectionFlowDto.setSmallNum(obj.getString("smallNum"));
							sectionFlowDto.setTotalNum(obj.getString("totalNum"));
							sectionFlowDto.setVehTailSpace(obj.getString("vehTailSpace"));
							sectionFlowDto.setUpdateTime(df.format(obj.getDate("updateTime")));
						}
						return sectionFlowDto;

					}).collect(Collectors.toList());

					SiteSectonFlowDto dto = new SiteSectonFlowDto(s);
					dto.setSections(sectionFlowDtos);
					return dto;
				}
			}

			return null;

		}).filter(d -> d != null).collect(Collectors.toList());

		return siteSectonFlowDtos;
	}

	// 获取所有的断面，并从redis获取缓存数据，返回到datagrid中
	@RequestMapping("/selectSiteSectionsForGrid")
	public Object selectSiteSectionsForGrid(String currentOrgPrivilegeCode) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式

		// 绿灯抓拍功能及流量检测功能
		SystemCriteria criteria = new SystemCriteria();
		// todo 代码调整，临时注释
		// criteria.lstFuncCodeList = Arrays.asList("1011", "1012");
		criteria.orgPrivilegeCode = currentOrgPrivilegeCode;
		List<Sys> devLst = systemService.findSystems(criteria);

		List<Site> sites = new ArrayList<Site>();
		for (Sys sys : devLst) {
			if (!StringUtil.isNullOrEmpty(sys.getUseStatusFlag())) {
				if (sys.getUseStatusFlag().equals("1") || sys.getUseStatusFlag().equals("2")) {
					Site site = siteService.siteOfId(sys.getSiteId());
					if (!sites.contains(site) && site != null) {
						sites.add(site);
					}
				}
			}
		}
		// 给点位添加断面
		// siteService.findSectionsAndSetIntoSiteList(sites);
		//
		// // 从redis缓存中取数据
		// Map<String, String> mapObject = RedisPoolUtil.hgetAll("tf:section");
		// List<SectionFlowDto> sectionFlowDtos = new
		// ArrayList<SectionFlowDto>();
		// for (Site site : sites) {
		// if (site != null) {
		// // if (site.getSiteLongitude() != null && site.getSiteLatitude()
		// // != null) {
		// for (Section section : site.getSections()) {
		// SectionFlowDto sectionFlowDto = new SectionFlowDto();
		// sectionFlowDto.setSectionId(section.getSectionId());
		// sectionFlowDto.setDirectionType(section.getDirectionType());
		// sectionFlowDto.setDirectionName(section.getDirectionName());
		// sectionFlowDto.setSiteName(site.getSiteName());
		// sectionFlowDto.setSiteId(site.getSiteId());
		// sectionFlowDto.setRoadId(site.getRoadId());
		//
		// if (site.getSiteLongitude() != null && site.getSiteLatitude() !=
		// null) {
		// sectionFlowDto
		// .setLonLat("POINT(" + site.getSiteLongitude() + " " +
		// site.getSiteLatitude() + ")");
		// }
		// // 加载缓存数据
		// String jsonStr = mapObject.get(section.getSectionId());
		// if (!StringUtil.isNullOrEmpty(jsonStr)) {
		// JSONObject obj = JSONObject.parseObject(jsonStr);
		// sectionFlowDto.setAvgSpeed(obj.getString("avgSpeed"));
		// sectionFlowDto.setLargeNum(obj.getString("largeNum"));
		// sectionFlowDto.setOtherNum(obj.getString("otherNum"));
		// sectionFlowDto.setSmallNum(obj.getString("smallNum"));
		// sectionFlowDto.setTotalNum(obj.getString("totalNum"));
		// sectionFlowDto.setVehTailSpace(obj.getString("vehTailSpace"));
		// sectionFlowDto.setUpdateTime(df.format(obj.getDate("updateTime")));
		// }
		// sectionFlowDtos.add(sectionFlowDto);
		// }
		// // }
		// }
		// }

		// 从redis缓存中取数据
		Map<String, String> mapObject = RedisPoolUtil.hgetAll("tf:section");
		Map<String, Site> siteMap = sites.stream().filter(s -> s != null)
				.collect(Collectors.toMap(Site::getSiteId, (s) -> s));

		List<Section> sections = siteService.sectionsOfSites(new ArrayList<String>(siteMap.keySet()));

		List<SectionFlowDto> sectionFlowDtos = sections.stream().map(sc -> {
			SectionFlowDto sectionFlowDto = new SectionFlowDto();
			Site site = siteMap.get(sc.getSiteId());
			sectionFlowDto.setSectionId(sc.getSectionId());
			sectionFlowDto.setDirectionType(sc.getDirectionType());
			sectionFlowDto.setDirectionName(sc.getDirectionName());
			sectionFlowDto.setSiteName(site.getSiteName());
			sectionFlowDto.setSiteId(site.getSiteId());
			sectionFlowDto.setRoadId(site.getRoadId());

			if (site.getSiteLongitude() != null && site.getSiteLatitude() != null) {
				sectionFlowDto.setLonLat("POINT(" + site.getSiteLongitude() + " " + site.getSiteLatitude() + ")");
			}
			// 加载缓存数据
			String jsonStr = mapObject.get(sc.getSectionId());
			if (!StringUtil.isNullOrEmpty(jsonStr)) {
				JSONObject obj = JSONObject.parseObject(jsonStr);
				sectionFlowDto.setAvgSpeed(obj.getString("avgSpeed"));
				sectionFlowDto.setLargeNum(obj.getString("largeNum"));
				sectionFlowDto.setOtherNum(obj.getString("otherNum"));
				sectionFlowDto.setSmallNum(obj.getString("smallNum"));
				sectionFlowDto.setTotalNum(obj.getString("totalNum"));
				sectionFlowDto.setVehTailSpace(obj.getString("vehTailSpace"));
				sectionFlowDto.setUpdateTime(df.format(obj.getDate("updateTime")));
			}

			return sectionFlowDto;

		}).collect(Collectors.toList());

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("error", "");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("total", sectionFlowDtos.size());
		jsonObject.put("rows", sectionFlowDtos);
		jsonObj.put("result", jsonObject);
		return jsonObj;
	}

	// 获取所有的断面，并从redis获取缓存数据，返回到datagrid中
	@RequestMapping("/selectSecCount")
	public int selectSecCount(String currentOrgPrivilegeCode) throws Exception {
		int count = 0;
		// 绿灯抓拍功能及流量检测功能
		SystemCriteria criteria = new SystemCriteria();
		// todo 代码调整，临时注释
		// criteria.lstFuncCodeList = Arrays.asList("1011", "1012");
		criteria.orgPrivilegeCode = currentOrgPrivilegeCode;
		List<Sys> devLst = systemService.findSystems(criteria);

		List<Site> sites = new ArrayList<Site>();
		for (Sys sys : devLst) {
			if (!StringUtil.isNullOrEmpty(sys.getUseStatusFlag())) {
				if (sys.getUseStatusFlag().equals("1") || sys.getUseStatusFlag().equals("2")) {
					Site site = siteService.siteOfId(sys.getSiteId());
					if (!sites.contains(site) && site != null) {
						sites.add(site);
					}
				}
			}
		}
		// // 给点位添加断面
		// siteService.findSectionsAndSetIntoSiteList(sites);
		// for (Site site : sites) {
		// if (site != null) {
		// if (site.getSiteLongitude() != null && site.getSiteLatitude() !=
		// null) {
		// if (site.getSections().size() > 0) {
		// count += site.getSections().size();
		// }
		// }
		// }
		// }
		//
		// return count;

		List<String> siteIds = sites.stream().filter(s -> s.getSiteLongitude() != null && s.getSiteLatitude() != null)
				.map(s -> s.getSiteId()).collect(Collectors.toList());

		return siteService.sectionCountOfSites(siteIds);
	}

}
