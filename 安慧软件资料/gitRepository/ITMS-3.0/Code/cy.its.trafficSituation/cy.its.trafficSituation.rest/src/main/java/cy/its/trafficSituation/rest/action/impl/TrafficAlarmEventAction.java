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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cy.its.com.constant.SysCodeConstant;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.SiteCriteria;
import cy.its.device.domain.criteria.SystemCriteria;
import cy.its.device.domain.model.Sys;
import cy.its.device.domain.model.site.Section;
import cy.its.device.domain.model.site.Site;
import cy.its.device.domain.service.ISiteService;
import cy.its.device.domain.service.ISystemService;
import cy.its.road.domain.model.region.Region;
import cy.its.road.domain.service.IRegionService;
import cy.its.trafficSituation.domain.model.TrafficAlarmEvent;
import cy.its.trafficSituation.domain.model.TrafficEventProcess;
import cy.its.trafficSituation.domain.service.ITrafficAlarmEventService;
import cy.its.trafficSituation.domain.service.ITrafficControlService;
import cy.its.trafficSituation.domain.service.ITrafficEventProcessService;
import cy.its.trafficSituation.rest.action.ITrafficAlarmEventAction;
import cy.its.trafficSituation.rest.dto.TrafficAlarmEventDto;

/**
 * 
 * @ClassName: TrafficAlarmEventAction
 * @Description: 交通预警事件rest
 * @author gyf guanyf@cychina.cn
 * @date 2015年10月27日 下午3:24:05
 *
 */
@RestController
@RequestMapping("/trafficSituation/alarmEvent")
public class TrafficAlarmEventAction implements ITrafficAlarmEventAction {

	@Autowired
	ITrafficAlarmEventService trafficAlarmEventService;
	@Autowired
	ITrafficEventProcessService processService;
	@Autowired
	ISiteService siteService;
	@Autowired
	ISystemService systemService;
	@Autowired
	IRegionService regionService;
	@Autowired
	ITrafficControlService trafficControlService;

	@RequestMapping("/update")
	public int update(TrafficAlarmEventDto trafficAlarmEventDto) throws ParseException {
		String id = trafficAlarmEventDto.getEventProcessId();// trafficAlarmEventDto.parseToEventProcess().getEventProcessId();
		int count = 0;
		if (trafficAlarmEventDto.getValidity().equals("1")) {// 如果有效，进一步判断EventProcessId是否为空来确定是新增还是更新记录
			if (StringUtil.isNullOrEmpty(id)) {
				TrafficEventProcess pro = trafficAlarmEventDto.parseToEventProcess();
				pro.setDealPerson(trafficAlarmEventDto.getCurrentUserName());
				pro.setDealTime(new Date());
				String processId = processService.save(pro);
				if (!StringUtil.isNullOrEmpty(processId)) {
					count++;
				}
			} else {
				TrafficEventProcess pro = trafficAlarmEventDto.parseToEventProcess();
				pro.setDealPerson(trafficAlarmEventDto.getCurrentUserName());
				pro.setDealTime(new Date());
				count += processService.update(pro);
			}
		} else {// 如果无效，另外EventProcessId也不为空，则删除这条处理记录
			if (!StringUtil.isNullOrEmpty(id)) {
				count += processService.delete(id);
			}
		}
		// 保存validity、confirmPeason、confirmTime
		TrafficAlarmEvent event = trafficAlarmEventDto.parseToTrafficAlarmEvent();
		event.setConfirmPerson(trafficAlarmEventDto.getCurrentUserName());
		event.setConfirmTime(new Date());
		count += trafficAlarmEventService.update(event);
		return count;
	}

	@RequestMapping("/search")
	public Object search(TrafficAlarmEventDto trafficAlarmEventDto) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(trafficAlarmEventDto.getStartTime())) {
			map.put("startTime", sdf.parse(trafficAlarmEventDto.getStartTime()));
		}
		if (!StringUtils.isEmpty(trafficAlarmEventDto.getEndTime())) {
			map.put("endTime", sdf.parse(trafficAlarmEventDto.getEndTime()));
		}
		if (!StringUtils.isEmpty(trafficAlarmEventDto.getAlarmEventType())) {
			map.put("alarmEventType", trafficAlarmEventDto.getAlarmEventType());
		}
		map.put("orgPrivilegeCode", trafficAlarmEventDto.getCurrentOrgPrivilegeCode());

		PageHelper.startPage(trafficAlarmEventDto.getPageNumber(), trafficAlarmEventDto.getPageSize());

		Page pageRs = (Page) trafficAlarmEventService.selectAll(map);
		List<TrafficAlarmEvent> trafficAlarmEvents = pageRs.getResult();
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("error", "");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("total", pageRs.getTotal());
		jsonObject.put("rows", trafficAlarmEvents);
		jsonObj.put("result", jsonObject);
		return jsonObj;
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

	@RequestMapping("/searchEvents")
	public Object searchEvents(TrafficAlarmEventDto trafficAlarmEventDto) throws Exception {
		Integer pageNow = Integer.valueOf(trafficAlarmEventDto.getPageNumber());
		Integer pageSize = Integer.valueOf(trafficAlarmEventDto.getPageSize());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(trafficAlarmEventDto.getStartTime())) {
			map.put("startTime", sdf.parse(trafficAlarmEventDto.getStartTime()));
		}
		if (!StringUtils.isEmpty(trafficAlarmEventDto.getEndTime())) {
			map.put("endTime", sdf.parse(trafficAlarmEventDto.getEndTime()));
		}
		map.put("alarmEventType", "434");
		if (!StringUtils.isEmpty(trafficAlarmEventDto.getSubAlarmEvent())) {
			map.put("subAlarmEvent", trafficAlarmEventDto.getSubAlarmEvent());
		}
		map.put("orgPrivilegeCode", trafficAlarmEventDto.getCurrentOrgPrivilegeCode());

		PageHelper.startPage(pageNow, pageSize);
		Page pageRs = (Page) trafficAlarmEventService.selectAll(map);

		List<TrafficAlarmEventDto> pDtos = new ArrayList<TrafficAlarmEventDto>();

		List<TrafficAlarmEvent> trafficAlarmEvents = pageRs.getResult();

		List<Site> siteLst = findSiteByOrgPrivilegeCode(trafficAlarmEventDto.getCurrentOrgPrivilegeCode());
		// List<Sys> devLst =
		// findDevSysByOrgPrivilegeCode(trafficAlarmEventDto.getCurrentOrgPrivilegeCode());
		for (TrafficAlarmEvent trafficAlarmEvent : trafficAlarmEvents) {
			// TrafficEventProcess process =
			// processService.selectByEventId(trafficAlarmEvent.getAlarmEventId());
			TrafficAlarmEventDto dto = new TrafficAlarmEventDto(trafficAlarmEvent);
			// if (process == null) {
			// dto = new TrafficAlarmEventDto(trafficAlarmEvent);
			// dto.setConfirmPerson(null);
			// dto.setConfirmTime(null);
			// } else {
			// dto = new TrafficAlarmEventDto(trafficAlarmEvent, process);
			// }
			if (dto.getSiteCode() != null) {
				Site site = getSiteByCode(siteLst, dto.getSiteCode());
				if (site != null) {
					String siteName = site.getSiteName();
					if (!StringUtils.isEmpty(siteName)) {
						dto.setSiteName(siteName);
					}
					String lon = site.getSiteLongitude().toString();
					String lat = site.getSiteLatitude().toString();
					if (!StringUtils.isEmpty(lon) && !StringUtils.isEmpty(lat)) {
						dto.setSiteLongitude(lon);
						dto.setSiteLongitude(lat);
					}
				}
			}
			// if (dto.getDeviceSysNbr() != null) {
			// Sys sy = getSysByNbr(devLst,dto.getDeviceSysNbr());
			// if (sy != null) {
			// String deviceName = sy.getDeviceName();
			// if (!StringUtils.isEmpty(deviceName)) {
			// dto.setDeviceName(deviceName);
			// }
			// }
			// }
			pDtos.add(dto);
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("error", "");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("total", pageRs.getTotal());
		jsonObject.put("rows", pDtos);
		jsonObj.put("result", jsonObject);
		return jsonObj;
	}

	@RequestMapping("/getAlarmProcessByAlarmEventId")
	public Map getAlarmProcessByAlarmEventId(String alarmEventId, String currentOrgPrivilegeCode, String devSysNbr) {
		List<Sys> devLst = findDevSysByOrgPrivilegeCode(currentOrgPrivilegeCode);
		String deviceSysName = devSysNbr;
		Sys sy = getSysByNbr(devLst, devSysNbr);
		if (sy != null) {
			String deviceName = sy.getDeviceName();
			if (!StringUtils.isEmpty(deviceName)) {
				deviceSysName = deviceName;
			}
		}
		TrafficEventProcess tProcess = processService.selectByEventId(alarmEventId);
		Map res = new HashMap();
		res.put("deviceName", deviceSysName);
		res.put("tProcess", tProcess);
		// 后续还要加上事件的图片URL TODO
		return res;
	}

	// 当天的所有事件检测
	private long getEventCount(String currentOrgPrivilegeCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgPrivilegeCode", currentOrgPrivilegeCode);
		map.put("alarmEventType", "434");
		return trafficAlarmEventService.selectCountByType(map);
	}

	// 当天未处理的事件检测
	private long getEventAlarmCount(String currentOrgPrivilegeCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("validity", "1");
		map.put("orgPrivilegeCode", currentOrgPrivilegeCode);
		map.put("alarmEventType", "434");
		return trafficAlarmEventService.selectCountByType(map);
	}

	// 当天有效的交通管制
	private long getControlCount(String currentOrgPrivilegeCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgPrivilegeCode", currentOrgPrivilegeCode);
		return trafficControlService.selectCount(map);
	}

	// 返回数量集合
	@RequestMapping("/getCounts")
	public Object getCount(String currentOrgPrivilegeCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 当天所有的事件检测器检测到的事件
		map.put("eventCount", getEventCount(currentOrgPrivilegeCode));
		// 当天所有的未处理的事件
		map.put("eventAlarmCount", getEventAlarmCount(currentOrgPrivilegeCode));
		// 获取有效的交通管制
		map.put("controlCount", getControlCount(currentOrgPrivilegeCode));
		return map;
	}
}
