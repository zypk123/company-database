package cy.its.device.rest.action.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.PassSiteCriteria;
import cy.its.device.domain.criteria.SiteCriteria;
import cy.its.device.domain.criteria.SystemCriteria;
import cy.its.device.domain.model.Sys;
import cy.its.device.domain.model.SysComponent;
import cy.its.device.domain.model.site.Lane;
import cy.its.device.domain.model.site.Section;
import cy.its.device.domain.model.site.Site;
import cy.its.device.domain.service.ISiteService;
import cy.its.device.domain.service.ISystemAttachService;
import cy.its.device.domain.service.ISystemService;
import cy.its.device.rest.dto.LaneDto;
import cy.its.device.rest.dto.PassSiteQueryDto;
import cy.its.device.rest.dto.SiteDto;
import cy.its.device.rest.dto.SiteTreeDto;
import cy.its.device.rest.dto.TransectDto;
import cy.its.platform.common.exception.ItmsAppException;
import cy.its.road.domain.criteria.RoadCriteria;
import cy.its.road.domain.model.road.Road;
import cy.its.road.domain.service.IRoadService;
import cy.its.syscfg.domain.model.duty.Organization;
import cy.its.syscfg.domain.model.sysCode.Code;
import cy.its.syscfg.domain.service.IDutyService;
import cy.its.syscfg.domain.service.ISysCodeService;

@RestController
@RequestMapping("/device/site")
public class SiteAction {

	@Autowired
	ISysCodeService sysCodeService;

	@Autowired
	IDutyService dutyService;

	@Autowired
	IRoadService roadService;

	@Autowired
	ISiteService siteService;

	@Autowired
	ISystemService systemService;

	@Autowired
	ISystemAttachService systemAttachService;

	/**
	 * 查询点位形成下拉列表
	 * 
	 * @param siteDto
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/querySites")
	public List<SiteDto> querySites(@ModelAttribute(value = "siteDto") SiteDto siteDto) throws Exception{
		SiteCriteria siteCriteria = new SiteCriteria();
		List<String> orgIds = new ArrayList<String>();
		String orgId = siteDto.getOrgId();
		//根据机构ID查机构信息
		if(!StringUtil.isNullOrEmpty(orgId)){
			Organization org = dutyService.organizationOfId(orgId);
			siteCriteria.orgPrvCode = org.orgPrivilegeCode;
		}
		String roadId = siteDto.getRoadId();
		siteCriteria.roadId = roadId;
		siteCriteria.setPageNum(1);
		siteCriteria.setPageSize(Integer.MAX_VALUE);
		// 根据机构ID添加机构权限过滤代码
		List<Site> siteList = siteService.findSites(siteCriteria);
		List<SiteDto> list = new ArrayList<SiteDto>();
		if (siteList != null && !siteList.isEmpty()) {
			siteList.stream().forEach(item -> {
				list.add(new SiteDto(item));
			});
		}
		return list;
	}

	/**
	 * 查找点位信息
	 * 
	 * @return
	 */
	@RequestMapping("/findSite")
	public Map<String, Object> findSite(@ModelAttribute(value = "siteDto") SiteDto siteDto,
			HttpServletRequest request) {
		SiteCriteria siteCriteria = siteDto.convertToCriteria();
		List<Site> siteList = siteService.findSites(siteCriteria);
		List<SiteDto> resultList = new ArrayList<SiteDto>();
		if (siteList != null && !siteList.isEmpty()) {
			siteList.stream().forEach(item -> {
				resultList.add(new SiteDto(item));
			});
		}
		return returnResult(resultList, siteCriteria.getTotal());
	}

	/**
	 * 删除点位
	 * 
	 * @param ids
	 *            点位Id串
	 * @return
	 */
	@RequestMapping("/delete")
	public String goDeleteSites(@RequestParam(value = "siteId") String ids) {
		deleteSite(ids);
		siteService.siteChanged();
		return "success";
	}

	/**
	 * 批量删除点位
	 * 
	 * @param ids
	 */
	private void deleteSite(String ids) {
		String[] deleteIds = ids.split(",");
		for (String id : deleteIds) {
			siteService.deleteSite(id);
		}
	}

	/**
	 * 添加点位及其相关信息（断面、车道）
	 * 
	 * @param siteDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addSite")
	public String goAddSite(@RequestParam(value = "jsonSitelString") String jsonSitelString) throws Exception {
		Site site = null;
		String flag = "codeError";
		SiteDto siteDto = JSON.parseObject(jsonSitelString, SiteDto.class);// 将字符串转换成Dto
		String siteCode = siteDto.getSiteCode();
		if (!StringUtil.isNullOrEmpty(siteCode)) {
			site = siteService.siteOfCode(siteCode);
		}
		if (site == null) {
			addSite(jsonSitelString);
			flag = "success";
			siteService.siteChanged();
		}
		return flag;
	}

	/**
	 * 编辑点位及其相关信息（断面、车道）
	 * 
	 * @param siteDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/editSite")
	public Map goEditSite(@RequestParam(value = "jsonSitelString") String jsonSitelString) throws Exception {
		Map result = editSite(jsonSitelString);
		siteService.siteChanged();
		return result;
	}

	/**
	 * 添加点位信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void addSite(String jsonSitelString) throws Exception {
		// TODO Auto-generated method stub
		SiteDto siteDto = JSON.parseObject(jsonSitelString, SiteDto.class);// 将字符串转换成Dto
		//验证点位编号重复问题
		Site site = siteService.siteOfCode(siteDto.getSiteCode());
		if(site != null){
			throw new ItmsAppException("点位编号重复！");
		}
		
		site = new Site();
		ObjectMapUtils.parseObject(site, siteDto);// 将dto转化成领域对象
		// 根据机构ID添加机构权限过滤代码
		if (!StringUtil.isNullOrEmpty(siteDto.getOrgId())) {
			Organization org = dutyService.organizationOfId(siteDto.getOrgId());
			if (!StringUtil.isNullOrEmpty(org.orgPrivilegeCode)) {
				site.setOrgPrivilegeCode(org.orgPrivilegeCode);// 机构过滤code
			}
		}
		// 根据路口ID获取所属主干道ID（道路ID）
		// if (!StringUtil.isNullOrEmpty(siteDto.getCrossId())) {
		// Cross cross = roadService.crossOfId(siteDto.getCrossId());
		// site.setRoadId(cross.getCrossRoadId());
		// }
		// 根据路段ID获取道路ID
		// if (!StringUtil.isNullOrEmpty(siteDto.getRoadSectionId())) {
		// RoadSection roadSection =
		// roadService.roadSectionOfId(siteDto.getRoadSectionId());
		// site.setRoadId(siteDto.getRoadId());
		// }
		if (!StringUtil.isNullOrEmpty(siteDto.getSiteLatitude())) {
			site.setSiteLatitude(new BigDecimal(siteDto.getSiteLatitude()));
		}
		if (!StringUtil.isNullOrEmpty(siteDto.getSiteLongitude())) {
			site.setSiteLongitude(new BigDecimal(siteDto.getSiteLongitude()));
		}
		if (!StringUtil.isNullOrEmpty(siteDto.getKilomileage())){
			site.setKilomileage(Integer.parseInt(siteDto.getKilomileage()));
		}
		siteService.saveSite(site);// 添加点位
		String siteId = site.getSiteId();// 获取点位ID
		List<TransectDto> list = siteDto.getSections();
		for (int i = 0; i < list.size(); i++) {
			if (!StringUtil.isNullOrEmpty(list.get(i).getDirectionType())) {
				TransectDto transectDto = list.get(i);
				transectDto.setSiteId(siteId);
				addTransect(transectDto); // 添加该点位的断面信息
			}
		}
	}
	/**
	 * 添加断面
	 * 
	 * @param transectDto
	 * @throws Exception
	 */
	public String addTransect(TransectDto transectDto) throws Exception {
		Section section = new Section();
		ObjectMapUtils.parseObject(section, transectDto);
		String sectionId = siteService.createSection(section);
		if (!StringUtil.isNullOrEmpty(section.getLaneNum())) {
			List<LaneDto> list = transectDto.getLaneList();
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					LaneDto laneDto = list.get(i);
					laneDto.setSectionId(sectionId);
					addLane(laneDto);
				}
			}
		}
		return sectionId;
	}

	/**
	 * 添加车道信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addLane(LaneDto laneDto) throws Exception {
		Lane lane = new Lane();
		ObjectMapUtils.parseObject(lane, laneDto);
		if (!StringUtil.isNullOrEmpty(laneDto.getLimitLarge())) {
			lane.setLimitLarge(new Short(laneDto.getLimitLarge()));
		}
		if (!StringUtil.isNullOrEmpty(laneDto.getLimitOthers())) {
			lane.setLimitOthers(new Short(laneDto.getLimitOthers()));
		}
		if (!StringUtil.isNullOrEmpty(laneDto.getLimitSmall())) {
			lane.setLimitSmall(new Short(laneDto.getLimitSmall()));
		}
		String laneId = siteService.createLane(lane);
		return laneId;
	}

	/**
	 * 编辑点位信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> editSite(String jsonSitelString) throws Exception {
		SiteDto siteDto = JSON.parseObject(jsonSitelString, SiteDto.class);// 将字符串转换成Dto
		//验证点位编号重复问题
		Site site = siteService.siteOfId(siteDto.getSiteId());
		if(!site.getSiteCode().equals(siteDto.getSiteCode()) && siteService.siteOfCode(siteDto.getSiteCode()) != null){
			throw new ItmsAppException("点位编号重复！");
		}
		// 更新断面信息
		Map<String, Object> returnResult = updateSection(siteDto);
		if (!(boolean) returnResult.get("result")) {
			// 验证不通过,抛出异常
			throw new ItmsAppException((String) returnResult.get("message"));
		}
		site = new Site();
		ObjectMapUtils.parseObject(site, siteDto);// 将dto转化成领域对象
		// 根据机构ID添加机构权限过滤代码
		if (!StringUtil.isNullOrEmpty(siteDto.getOrgId())) {
			Organization org = dutyService.organizationOfId(siteDto.getOrgId());
			if (!StringUtil.isNullOrEmpty(org.orgPrivilegeCode)) {
				site.setOrgPrivilegeCode(org.orgPrivilegeCode);// 机构过滤code
			}
		}
		if (!StringUtil.isNullOrEmpty(siteDto.getSiteLatitude())) {
			site.setSiteLatitude(new BigDecimal(siteDto.getSiteLatitude()));
		}
		if (!StringUtil.isNullOrEmpty(siteDto.getSiteLongitude())) {
			site.setSiteLongitude(new BigDecimal(siteDto.getSiteLongitude()));
		}
		if (!StringUtil.isNullOrEmpty(siteDto.getKilomileage())){
			site.setKilomileage(Integer.parseInt(siteDto.getKilomileage()));
		}
		siteService.updateSite(site);
		// 通过验证，返回结果
		Map<String, Object> returnMap = new HashMap<String,Object>();
		returnMap.put("result", true);
		return returnMap;
	}

	/**
	 * 更新断面信息
	 * 
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> updateSection(SiteDto siteDto) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		// 已经存在的断面
		List<Section> sectionList = siteService.sectionsOfSite(siteDto.getSiteId());
		// 需要合并的断面集合
		List<TransectDto> sectionDtoList = siteDto.getSections();
		// 分离出来需要更新的断面，删除的断面，新增的断面
		for (int i = 0; i < sectionList.size(); i++) {
			for (int j = 0; j < sectionDtoList.size(); j++) {
				if (sectionDtoList.get(j).getDirectionType().equals(sectionList.get(i).getDirectionType())) {
					// 断面存在，更新断面信息和车道信息
					Map<String, Object> returnResult = updateLane(sectionDtoList.get(j));
					if ((boolean) returnResult.get("result")) {
						// 更新车道成功，更新断面信息
						Section section = sectionList.get(i);
						TransectDto dto = sectionDtoList.get(j);
						section.setDirectionName(dto.getDirectionName());
						section.setLaneNum(dto.getLaneNum());
						section.setEnterOrExit(dto.getEnterOrExit());
						siteService.updateSection(section);
					} else {
						// 更新车道信息失败，返回车道错误结果
						return returnResult;
					}
					// 删除各自队列中的元素
					sectionDtoList.remove(j--);
					sectionList.remove(i--);
					break;
				}
			}
		}
		// 删除断面
		for (Section section : sectionList) {
			SystemCriteria criteria = new SystemCriteria();
			criteria.siteId = section.getSiteId();
			List<Sys> sysList = systemService.findSystems(criteria);
			if (!sysList.isEmpty()) {
				for (Sys sys : sysList) {
					if(!StringUtil.isNullOrEmpty(sys.getSectionIdList())){
						if (sys.getSectionIdList().contains(section.getSectionId())) {
							if ("1".equals(section.getDirectionType())) {
								returnMap.put("result", false);
								returnMap.put("message", "上行方向已经配置了设备信息，请确认后再删除！");
								return returnMap;
							} else if ("2".equals(section.getDirectionType())) {
								returnMap.put("result", false);
								returnMap.put("message", "下行方向已经配置了设备信息，请确认后再删除！");
								return returnMap;
							}
						}
					}
				}
			}
			// 验证通过，删除断面
			siteService.deleteSection(section.getSectionId());
		}
		// 新增断面
		for (TransectDto sectionDto : sectionDtoList) {
			sectionDto.setSiteId(siteDto.getSiteId());
			addTransect(sectionDto);
		}
		// 通过验证，返回结果
		returnMap.put("result", true);
		return returnMap;
	}

	/**
	 * 更新断面下的车道信息
	 * 
	 * @param section
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> updateLane(TransectDto sectionDto) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		// 已存在的车道列表
		List<Lane> existLaneList = siteService.lanesOfSection(sectionDto.getSectionId());
		// 需要更新的车道信息
		List<LaneDto> laneDtoList = sectionDto.getLaneList();
		// 分离出来需要更新的车道，删除的车道，新增的车道
		for (int i = 0; i < existLaneList.size(); i++) {
			for (int j = 0; j < laneDtoList.size(); j++) {
				if (laneDtoList.get(j).getLaneNbr().equals(existLaneList.get(i).getLaneNbr())) {
					// 当前车道号已经存在，更新车道信息
					Lane lane = existLaneList.get(i);
					LaneDto dto = laneDtoList.get(j);
					lane.setIsRestrict(dto.getIsRestrict());
					lane.setLaneType(dto.getLaneType());
					lane.setLimitLarge(Short.parseShort(dto.getLimitLarge()));
					lane.setLimitOthers(Short.parseShort(dto.getLimitOthers()));
					lane.setLimitSmall(Short.parseShort(dto.getLimitSmall()));
					siteService.updateLane(lane);
					// 删除各自队列中的元素
					laneDtoList.remove(j--);
					existLaneList.remove(i--);
					break;
				}
			}
		}
		// 删除车道
		for (Lane lane : existLaneList) {
			// 查找断面下的设备信息
			SystemCriteria criteria = new SystemCriteria();
			criteria.siteId = sectionDto.getSiteId();
			List<Sys> sysList = systemService.findSystems(criteria);
			if (!sysList.isEmpty()) {
				for (Sys sys : sysList) {
					// 查找设备下的相机
					List<SysComponent> sysComponentList = systemAttachService.componentsOfSystem(sys.getDeviceId());
					for (SysComponent sysComponent : sysComponentList) {
						if (sysComponent.getDirectionType().equals(sectionDto.getDirectionType())
								&& lane.getLaneNbr().equals(sysComponent.getLaneNbr())) {
							// 该断面方向下的车道已经存在部件
							if ("1".equals(sectionDto.getDirectionType())) {
								returnMap.put("result", false);
								returnMap.put("message", "上行方向车道" + lane.getLaneNbr() + "已经配置了相机信息，请确认后再删除！");
								return returnMap;
							} else if ("2".equals(sectionDto.getDirectionType())) {
								returnMap.put("result", false);
								returnMap.put("message", "下行方向车道" + lane.getLaneNbr() + "已经配置了相机信息，请确认后再删除！");
								return returnMap;
							}
						}
					}
				}
			}
			// 验证通过，删除车道
			siteService.deleteLane(lane.getLaneId());
		}
		// 新增车道
		for (LaneDto lane : laneDtoList) {
			addLane(lane);
		}
		// 通过验证，返回结果
		returnMap.put("result", true);
		return returnMap;
	}

	/**
	 * 查询某点位的信息
	 * 
	 * @param siteId
	 *            点位ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/querySite")
	public SiteDto querySite(@RequestParam("siteId") String siteId) throws Exception {
		Site site = siteService.siteOfId(siteId);
		SiteDto siteDto = new SiteDto(site);
		// 根据点位ID查询所有断面信息添加到点位信息里
		List<Section> sectionList = siteService.sectionsOfSite(siteId);
		List<TransectDto> transectList = new ArrayList<TransectDto>();
		for (int i = 0; i < sectionList.size(); i++) {
			TransectDto transectDto = new TransectDto();
			ObjectMapUtils.parseObject(transectDto, sectionList.get(i));
			String sectionId = sectionList.get(i).getSectionId();
			// 根据断面ID查询所有车道信息添加到断面
			List<Lane> lane = siteService.lanesOfSection(sectionId);
			List<LaneDto> laneList = new ArrayList<LaneDto>();
			if (lane.size() > 0) {
				for (int j = 0; j < lane.size(); j++) {
					LaneDto laneDto = new LaneDto();
					ObjectMapUtils.parseObject(laneDto, lane.get(j));
					if (lane.get(j).getLimitLarge() != null) {
						laneDto.setLimitLarge(lane.get(j).getLimitLarge().toString());
					}
					if (lane.get(j).getLimitSmall() != null) {
						laneDto.setLimitSmall(lane.get(j).getLimitSmall().toString());
					}
					if (lane.get(j).getLimitOthers() != null) {
						laneDto.setLimitOthers(lane.get(j).getLimitOthers().toString());
					}
					laneList.add(laneDto);
				}
			}
			transectDto.setLaneList(laneList);
			transectList.add(transectDto);
		}
		siteDto.setSections(transectList);
		return siteDto;
	}

	/**
	 * 查询车道信息
	 * 
	 * @param transectDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryLane")
	public List<Lane> queryLane(@RequestParam("siteId") String siteId,
			@RequestParam("directionType") String directionType) throws Exception {
		List<Section> list = siteService.sectionsOfSite(siteId);
		String sectionId = ""; // 初始化断面Id
		for (int i = 0; i < list.size(); i++) {
			if (directionType.equals(list.get(i).getDirectionType())) {
				sectionId = list.get(i).getSectionId();
			}
		}
		List<Lane> lst = siteService.lanesOfSection(sectionId);
		return lst;
	}
	
	/**
	 * 根据断面ID查询车道信息
	 * 
	 * @param transectDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryLaneBySectionId")
	public List<TransectDto> queryLaneBySectionId(@RequestParam("sectionIdStr") String sectionIdStr) throws Exception {
		String sectionId[] = null;
		List<TransectDto> l = new ArrayList<TransectDto>();
		if(!StringUtil.isNullOrEmpty(sectionIdStr)){
			sectionId = sectionIdStr.split(",");
		}
		for (int i = 0; i < sectionId.length; i++) {
			List<LaneDto> laneList = new ArrayList<LaneDto>();
			TransectDto sectionDto = new TransectDto();
			Section section = siteService.sectionOfId(sectionId[i]);
			List<Lane> lst = siteService.lanesOfSection(sectionId[i]);
			for (int j = 0; j < lst.size(); j++) {
				LaneDto lane = new LaneDto();
				ObjectMapUtils.parseObject(lane, lst.get(j));
				laneList.add(lane);
			}
			sectionDto.setLaneList(laneList);
			sectionDto.setDirectionType(section.getDirectionType());
			sectionDto.setDirectionName(section.getDirectionName());
			l.add(sectionDto);
		}
		return l;
	}


	/**
	 * 封装页面返回数据
	 * 
	 * @param dataList
	 * @param total
	 * @return
	 */
	private Map<String, Object> returnResult(List dataList, long total) {
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
	 * 获得点位信息
	 * 
	 * @param id
	 *            根节点Id
	 * @param type
	 *            树类型
	 * @return 点位树信息
	 * @throws Exception
	 */
	@RequestMapping("/getSiteTreeByRootId")
	public List<SiteTreeDto> getSiteTreeByRootId(@RequestParam(value = "id") String id,
			@RequestParam(value = "type", required = true) String type, String currentOrgPrivilegeCode, boolean checked,
			boolean isSection,boolean isBayonet) throws Exception {
		if ("0".equals(type)) {
			// 按道路展示
			if (id == null || "".equals(id)) {
				// 查找初始化数据
				return getInitRoadData(currentOrgPrivilegeCode);
			} else {
				// 按照道路查找点位
				return getSiteInRoadByRootId(id, currentOrgPrivilegeCode, checked, isSection,isBayonet);
			}
		} else {
			// 按机构展示
			if (id == null || "".equals(id)) {
				// id为空说明是初始化操作，返回空集合
				return new ArrayList<SiteTreeDto>();
			} else {
				// 返回该机构下的所有机构和点位
				return getSiteInDistrictByRootId(id, checked, isSection,isBayonet);
			}
		}
	}

	/**
	 * 根据点位名称查找点位信息
	 * 
	 * @return 点位信息集合
	 */
	@RequestMapping("/findSiteByName")
	public Map<String, Object> findSiteByName(String siteName, String currentOrgPrivilegeCode) throws Exception {
		SiteCriteria siteCriteria = new SiteCriteria();
		siteCriteria.siteName = siteName;
		siteCriteria.orgPrvCode = currentOrgPrivilegeCode;
		siteCriteria.setNoPage();
		List<Site> siteList = siteService.findSites(siteCriteria);
		if (siteList == null) {
			siteList = new ArrayList<Site>();
		}
		return returnResult(siteList, siteList.size());
	}

	/**
	 * 按照机构方式获取
	 * 
	 * @param rootId
	 *            根节点Id
	 * @return
	 */
	private List<SiteTreeDto> getSiteInDistrictByRootId(String rootId, boolean checked, boolean isSection, boolean isBayonet)
			throws Exception {
		List<SiteTreeDto> returnList = new ArrayList<SiteTreeDto>();
		
		
		List<Site> siteList = null;
		if(isBayonet){
			//查找机构下的卡口点位
			PassSiteCriteria criteria = new PassSiteCriteria();
			criteria.setOrgId(rootId);
			siteList = siteService.findPassSites(criteria);
		}else{
			// 找到机构下所有的点位
			SiteCriteria siteCriteria = new SiteCriteria();
			siteCriteria.setNoPage();
			List<String> orgIdCondition = new ArrayList<String>();
			orgIdCondition.add(rootId);
			siteCriteria.orgIds = orgIdCondition;
			siteList = siteService.findSites(siteCriteria);
		}
		//sortSiteListBySiteName(siteList);
		List<String> siteIds = new ArrayList<String>();
		for (Site site : siteList) {
			siteIds.add(site.getSiteId());
		}
		if (siteList != null && siteList.size() > 0) {
			Map<String, List<Section>> mapSiteSections = null;
			if (isSection) {
				// 查找到所有的断面信息并添加到点位集合中
				List<Section> sections = siteService.sectionsOfSites(siteIds);
				mapSiteSections = sections.stream().collect(Collectors.groupingBy(Section::getSiteId));
			}
			for (Site item : siteList) {
				// if (item.getOrgId().equals(rootId)) {
				// 找到下级点位
				SiteTreeDto siteChild = new SiteTreeDto();
				siteChild.setId(item.getSiteId());
				siteChild.setText(item.getSiteName());
				siteChild.setChecked(checked);
				Map<String, String> map = new HashMap<String, String>();
				map.put("code", item.getSiteCode());
				map.put("orgId", item.getOrgId());
				map.put("orgPrivCode", item.getOrgPrivilegeCode());
				map.put("districtCode", item.getDistrictCode());
				map.put("roadId", item.getRoadId());
				map.put("roadSectionId", item.getRoadSectionId());
				map.put("crossId", item.getCrossId());
				map.put("nodeType", "site");
				siteChild.setAttribute(map);
				if (isSection) {
					siteChild.setState("open");
					siteChild.setChildren(new ArrayList<SiteTreeDto>());
					if (mapSiteSections.containsKey(item.getSiteId())) {
						List<Section> scs = mapSiteSections.get(item.getSiteId());
						if (scs != null && !scs.isEmpty()) {
							// 如果需要查找断面信息，则增加断面信息
							for (Section section : scs) {
								SiteTreeDto sectionNode = new SiteTreeDto();
								sectionNode.setId(item.getSiteCode() + "," + section.getDirectionType());
								sectionNode.setText(section.getDirectionName());
								sectionNode.setChecked(checked);
								sectionNode.setState(null);
								Map<String, String> sectionAttr = new HashMap<String, String>();
								sectionAttr.put("nodeType", "section");
								sectionNode.setAttribute(sectionAttr);
								siteChild.getChildren().add(sectionNode);
							}
						}
					}
				} else {
					siteChild.setState(null);
				}
				returnList.add(siteChild);
				// }
			}
			;
		}
		// 找到机构下所有下属单位
		List<Organization> orgList = dutyService.findOrgsOfParent(rootId);
		orgList.stream().forEach(item -> {
			SiteTreeDto orgChild = new SiteTreeDto();
			orgChild.setId(item.getIdentityId());
			orgChild.setText(item.orgName);
			Map<String, String> map = new HashMap<String, String>();
			map.put("code", item.orgCode);
			map.put("nodeType", "org");
			orgChild.setAttribute(map);
			orgChild.setState("closed");
			orgChild.setChecked(checked);
			returnList.add(orgChild);
		});
		return returnList;
	}

	/**
	 * 根据道路ID，获取点位信息
	 * 
	 * @param roadId
	 * @return
	 */
	private List<SiteTreeDto> getSiteInRoadByRootId(String roadId, String currentOrgPrivilegeCode, boolean checked,
			boolean isSection,boolean isBayonet) {
		List<Site> siteList = null;
		if(isBayonet){
			//查找道路下的卡口点位
			PassSiteCriteria criteria = new PassSiteCriteria();
			criteria.setOrgPrivCode(currentOrgPrivilegeCode);
			List<String> roadIds = new ArrayList<String>();
			roadIds.add(roadId);
			criteria.setRoadIds(roadIds);
			siteList = siteService.findPassSites(criteria);
		}else{
			//查询机构下的所有点位
			SiteCriteria siteCriteria = new SiteCriteria();
			siteCriteria.setNoPage();
			siteCriteria.roadId = roadId;
			siteCriteria.orgPrvCode = currentOrgPrivilegeCode;
			siteList = siteService.findSites(siteCriteria);
		}
		
		//sortSiteListBySiteName(siteList);
		List<String> siteIds = new ArrayList<String>();
		for (Site site : siteList) {
			siteIds.add(site.getSiteId());
		}
		Map<String, List<Section>> mapSiteSections = null;
		if (isSection && !siteIds.isEmpty()) {
			// 查找到所有的断面信息并添加到点位集合中
			List<Section> sections = siteService.sectionsOfSites(siteIds);
			mapSiteSections = sections.stream().collect(Collectors.groupingBy(Section::getSiteId));
		}
		if (siteList != null) {
			List<SiteTreeDto> siteTreeList = new ArrayList<SiteTreeDto>();
			for (Site item : siteList) {
				SiteTreeDto node = new SiteTreeDto();
				node.setId(item.getSiteId());
				node.setText(item.getSiteName());
				node.setChecked(checked);
				Map<String, String> attribute = new HashMap<String, String>();
				attribute.put("code", item.getSiteCode());
				attribute.put("nodeType", "site");
				attribute.put("orgId", item.getOrgId());
				attribute.put("orgPrivCode", item.getOrgPrivilegeCode());
				attribute.put("districtCode", item.getDistrictCode());
				attribute.put("roadId", item.getRoadId());
				attribute.put("roadSectionId", item.getRoadSectionId());
				attribute.put("crossId", item.getCrossId());
				node.setAttribute(attribute);
				if (isSection) {
					node.setState("open");
					node.setChildren(new ArrayList<SiteTreeDto>());
					// 如果需要查找断面信息，则增加断面信息
					if (mapSiteSections.containsKey(item.getSiteId())) {
						List<Section> scs = mapSiteSections.get(item.getSiteId());
						if (scs != null && !scs.isEmpty()) {
							// 如果需要查找断面信息，则增加断面信息
							for (Section section : scs) {
								SiteTreeDto sectionNode = new SiteTreeDto();
								sectionNode.setId(item.getSiteCode() + "," + section.getDirectionType());
								sectionNode.setText(section.getDirectionName());
								sectionNode.setChecked(checked);
								sectionNode.setState(null);
								Map<String, String> sectionAttr = new HashMap<String, String>();
								sectionAttr.put("nodeType", "section");
								sectionNode.setAttribute(sectionAttr);
								node.getChildren().add(sectionNode);
							}
						}
					}
				} else {
					node.setState(null);
				}
				siteTreeList.add(node);
			}
			return siteTreeList;
		}
		return null;
	}

	/**
	 * 初始化道路树信息
	 * 
	 * @throws Exception
	 */
	private List<SiteTreeDto> getInitRoadData(String currentOrgPrivilegeCode) throws Exception {
		// 超找所有的道路信息
		RoadCriteria roadCriteria = new RoadCriteria();
		roadCriteria.setNoPage();
		roadCriteria.orgPrivilegeCode = currentOrgPrivilegeCode;
		List<Road> roadList = roadService.findRoads(roadCriteria);
		// 获得道路类型编码
		List<Code> roadTypeList = sysCodeService.codeListOfType("016");
		return convertToRoadTree(roadTypeList, roadList);
	}

	/**
	 * 将查找到的数据转化为Tree结构
	 * 
	 * @return 道路树
	 */
	private List<SiteTreeDto> convertToRoadTree(List<Code> roadTypeList, List<Road> roadList) {
		List<SiteTreeDto> types = new ArrayList<SiteTreeDto>();
		// 首先初始化道路编号
		roadTypeList.stream().forEach(item -> {
			SiteTreeDto treeRoot = new SiteTreeDto();
			treeRoot.setId(item.codeNo);
			treeRoot.setText(item.codeName);
			Map<String, String> attribute = new HashMap<String, String>();
			attribute.put("nodeType", "roadType");
			treeRoot.setAttribute(attribute);
			treeRoot.setState("closed");
			treeRoot.setChildren(new ArrayList<SiteTreeDto>());
			types.add(treeRoot);
		});
		// 将道路数据添加到对应的道路类型节点下
		roadList.stream().forEach(item -> {
			List<SiteTreeDto> children = findChildrenByRoadType(types, item.getRoadType());
			SiteTreeDto treeNode = new SiteTreeDto();
			treeNode.setId(item.getRoadId());
			treeNode.setText(item.getRoadName());
			treeNode.setState("closed");
			Map<String, String> attribute = new HashMap<String, String>();
			attribute.put("code", item.getRoadCode());
			attribute.put("nodeType", "road");
			treeNode.setAttribute(attribute);
			children.add(treeNode);
		});
		// 添加一个统一的根节点
		List<SiteTreeDto> roots = new ArrayList<SiteTreeDto>();
		SiteTreeDto root = new SiteTreeDto();
		root.setId("0");
		root.setText("道路");
		root.setChildren(types);
		Map<String, String> attribute = new HashMap<String, String>();
		attribute.put("nodeType", "root");
		root.setAttribute(attribute);
		roots.add(root);
		return roots;
	}

	/**
	 * 根据道路类型找到该类型下的子节点
	 * 
	 * @param roots
	 *            所有道路树根节点
	 * @param roadType
	 *            道路类型
	 * @return 道路类型子节点
	 */
	private List<SiteTreeDto> findChildrenByRoadType(List<SiteTreeDto> roots, String roadType) {
		Iterator<SiteTreeDto> iter = roots.iterator();
		while (iter.hasNext()) {
			SiteTreeDto item = iter.next();
			if (item.getId().equals(roadType)) {
				return item.getChildren();
			}
		}
		return null;
	}

	/**
	 * 根据点位名称查询出点位树
	 * 
	 * @param searchText
	 *            查询条件
	 * @param searchType
	 *            查询类型，0：按道路，1：按机构
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/searchSiteTree")
	public SiteTreeDto searchSiteTree(String searchText, String searchType, String currentOrgPrivilegeCode)
			throws Exception {
		SiteCriteria siteCriteria = new SiteCriteria();
		siteCriteria.siteName = searchText;
		siteCriteria.orgPrvCode = currentOrgPrivilegeCode;
		List<Site> siteList = siteService.findSites(siteCriteria);
		if (siteList == null || siteList.size() == 0) {
			return null;
		}
		Site site = siteList.get(0);
		SiteTreeDto siteNode = new SiteTreeDto();
		siteNode.setId(site.getSiteId());
		siteNode.setText(site.getSiteName());
		if (searchType.equals("0")) {
			// 按道路查询
			// 查询所在道路
			Road road = roadService.roadOfId(site.getRoadId());
			SiteTreeDto roadNode = new SiteTreeDto();
			roadNode.setId(road.getRoadId());
			roadNode.setText(road.getRoadType());
			roadNode.setChildren(new ArrayList<SiteTreeDto>());
			roadNode.getChildren().add(siteNode);
			return roadNode;
		} else {
			// 按机构查询
			// 父机构节点
			SiteTreeDto parentOrgNode = new SiteTreeDto();
			parentOrgNode.setId(site.getOrgId());
			parentOrgNode.setChildren(new ArrayList<SiteTreeDto>());
			parentOrgNode.getChildren().add(siteNode);
			// 递归查找所有父机构并返回
			return findParentNode(parentOrgNode);
		}
	}

	/**
	 * 递归查找所有父机构并组装成节点
	 * 
	 * @param node
	 * @return
	 * @throws Exception
	 */
	private SiteTreeDto findParentNode(SiteTreeDto node) throws Exception {
		Organization org = dutyService.organizationOfId(node.getId());
		if (org == null) {
			return node.getChildren().get(0);
		} else {
			SiteTreeDto parentOrgNode = new SiteTreeDto();
			parentOrgNode.setId(org.parentOrgId);
			parentOrgNode.setChildren(new ArrayList<SiteTreeDto>());
			parentOrgNode.getChildren().add(node);
			return findParentNode(parentOrgNode);
		}
	}

	/**
	 * 查询某条道路上某个方向的具有过车性质的点位
	 * 
	 * @param roadId
	 * @param directionType
	 * @return
	 */
	@RequestMapping("/findPassSites")
	public List<Site> findPassSites(@ModelAttribute("dto") PassSiteQueryDto dto) {
		PassSiteCriteria criteria = dto.parseToCriteria();
		return siteService.findPassSites(criteria);
	}
	
	@RequestMapping("/findByonetSite")
	public Map<String, Object> findByonetSite(@ModelAttribute("dto") PassSiteQueryDto dto){
		PassSiteCriteria criteria = dto.parseToCriteria();
		List<Site> siteList = siteService.findPassSites(criteria);
		return returnResult(siteList, siteList.size());
	}
	/**
	 * 根据SiteId查找siteName
	 * @param siteId
	 * @return
	 */
	@RequestMapping("/findSiteByCode")
	public Site findSiteByCode(String siteCode){
		return siteService.siteOfCode(siteCode);
	}
	
	/**
	 * 根据点位名称顺序排序
	 * @param site
	 */
	private void sortSiteListBySiteName(List<Site> site){
		Collections.sort(site, new Comparator<Site>() {
            public int compare(Site site1, Site site2) {
            	if(site1.getKilomileage() == null && site2.getKilomileage() == null){
            		return site1.getSiteName().compareTo(site2.getSiteName());
            	}else if(site1.getKilomileage() == null && site2.getKilomileage() != null){
            		return -1;
            	}else if(site1.getKilomileage() != null && site2.getKilomileage() == null){
            		return 1;
            	}else{
            		int dists1;
					int dists2;
					try {
						dists1 = site1.getKilomileage() * 1000 + Integer.parseInt(site1.getMileage());
						dists2 = site2.getKilomileage() * 1000 + Integer.parseInt(site2.getMileage());
						return dists1 - dists2;
					} catch (NumberFormatException e) {
						return -1;
					}
            	}
            }
        });
	}
}
