package cy.its.road.rest.action.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.SiteCriteria;
import cy.its.device.domain.criteria.SystemCriteria;
import cy.its.device.domain.model.Sys;
import cy.its.device.domain.model.site.Site;
import cy.its.device.domain.service.ISiteService;
import cy.its.device.domain.service.ISystemService;
import cy.its.road.domain.criteria.RoadCriteria;
import cy.its.road.domain.criteria.RoadSectionCriteria;
import cy.its.road.domain.model.road.Road;
import cy.its.road.domain.model.road.RoadSection;
import cy.its.road.domain.service.IRoadService;

import cy.its.road.rest.dto.RoadSectionTreeDto;
import cy.its.road.rest.action.IRoadSectionAction;
import cy.its.road.rest.dto.RoadSectionDto;
import cy.its.syscfg.domain.model.duty.Organization;
import cy.its.syscfg.domain.model.sysCode.Code;
import cy.its.syscfg.domain.service.IDutyService;
import cy.its.syscfg.domain.service.ISysCodeService;

/**
 * @Title: RoadSectionAction.java
 * @Package cy.its.road.rest.action.impl
 * @Description: 路段rest服务
 * @author zuop zuop@cychina.cn
 * @date 2015年11月16日 下午2:12:15
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 *      Company: 安徽超远信息技术有限公司 Copyright: Copyright (c) 2015
 */
@RestController
@RequestMapping("/road/roadSection")
public class RoadSectionAction implements IRoadSectionAction {

	@Autowired
	IRoadService roadService;

	@Autowired
	ISysCodeService sysCodeService;

	@Autowired
	IDutyService dutyService;
	
	@Autowired
	ISiteService siteService;

	@Autowired
	ISystemService systemService; 
	/**
	 * 获得路段树 getRoadSectionTree(这里用一句话描述这个方法的作用) @Title:
	 * getRoadSectionTree @Description: TODO @param @return 设定文件 @return List
	 * <RoadSectionTreeDto> 返回类型 @throws
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/getRoadSectionTree")
	public List<RoadSectionTreeDto> getRoadSectionTree(String id) throws Exception {
		if (id == null || "".equals(id)) {
			// 加载道路信息
			return getRoadTree();
		} else {
			List<RoadSectionTreeDto> returnList = new ArrayList<RoadSectionTreeDto>();
			// 加载路段信息
			RoadSectionCriteria criteria = new RoadSectionCriteria();
			criteria.roadId = id;
			criteria.setNoPage();
			List<RoadSection> roadSectionList = roadService.findRoadSectiones(criteria);
			roadSectionList.forEach(item -> {
				returnList.add(new RoadSectionTreeDto(item));
			});
			return returnList;
		}
	}

	/**
	 * 
	 * findRoadSection 查找路段 @Title: findRoadSection @Description:
	 * TODO @param @param searchIndex @param @return 设定文件 @return Map
	 * 返回类型 @throws
	 */
	@RequestMapping("/findRoadSection")
	public Map<String, Object> findRoadSection(String searchIndex) {
		RoadSectionCriteria criteria = new RoadSectionCriteria();
		criteria.roadSectionName = searchIndex;
		criteria.setNoPage();
		List<RoadSection> roadSectionList = roadService.findRoadSectiones(criteria);
		return returnResult(roadSectionList, roadSectionList.size());
	}

	/**
	 * 封装页面返回数据
	 * 
	 * @param dataList
	 * @param total
	 * @return
	 */
	private Map<String, Object> returnResult(List<RoadSection> dataList, int total) {
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
	 * getRoadTree 获得道路树组件
	 * 
	 * @Title: getRoadTree
	 * @Description: TODO
	 * @param @return
	 * 设定文件 @return List<RoadSectionTreeDto> 返回类型 @throws
	 * @throws Exception
	 */
	private List<RoadSectionTreeDto> getRoadTree() throws Exception {
		// 超找所有的道路信息
		RoadCriteria roadCriteria = new RoadCriteria();
		roadCriteria.setNoPage();
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
	private List<RoadSectionTreeDto> convertToRoadTree(List<Code> roadTypeList, List<Road> roadList) {
		List<RoadSectionTreeDto> types = new ArrayList<RoadSectionTreeDto>();
		// 首先初始化道路编号
		roadTypeList.stream().forEach(item -> {
			types.add(new RoadSectionTreeDto(item));
		});
		// 将道路数据添加到对应的道路类型节点下
		roadList.stream().forEach(item -> {
			List<RoadSectionTreeDto> children = findChildrenByRoadType(types, item.getRoadType());
			if (children != null) {
				children.add(new RoadSectionTreeDto(item));
			}
		});
		// 添加一个统一的根节点
		List<RoadSectionTreeDto> roots = new ArrayList<RoadSectionTreeDto>();
		RoadSectionTreeDto root = new RoadSectionTreeDto();
		root.setId("0");
		root.setText("道路");
		root.setChildren(types);
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
	private List<RoadSectionTreeDto> findChildrenByRoadType(List<RoadSectionTreeDto> roots, String roadType) {
		Iterator<RoadSectionTreeDto> iter = roots.iterator();
		while (iter.hasNext()) {
			RoadSectionTreeDto item = iter.next();
			if (item.getId().equals(roadType)) {
				return item.getChildren();
			}
		}
		return null;
	}

	/*
	 * <p>Title: createSection</p> <p>Description: 新增路段信息</p>
	 * 
	 * @param sectionDto
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.road.rest.action.ISectionAction#createSection(cy.its.road.rest.dto
	 * .SectionDto)
	 */

	@RequestMapping("/saveRoadSection")
	@Override
	public int createRoadSection(RoadSectionDto roadSectionDto) throws Exception {
		int flag=0;
		//根据道路Id查询该道路下的所有路段Code
		//判断新增一条路段是否已绑定道路,验证路段code唯一性标识
		RoadSectionCriteria criteria = new RoadSectionCriteria();
		criteria.roadId=roadSectionDto.getRoadId();
		List<RoadSection> roadSectionList = roadService.findRoadSectiones(criteria);
		if(roadSectionList !=null && roadSectionList.size() !=0){
			for(RoadSection roadSection : roadSectionList){
				if(roadSectionDto.getRoadSectionCode().equals(roadSection.getRoadSectionCode())){
					return flag;
				}
			}
			flag=addRoadSection(roadSectionDto);
		}else{
			flag=addRoadSection(roadSectionDto);
		}
		return flag;
	}
	//保存路段信息
	private int addRoadSection(RoadSectionDto roadSectionDto) throws Exception{

		RoadSection roadSection = new RoadSection();
		// 根据机构Id获得机构权限代码转给领域Dto
		Organization org = dutyService.organizationOfId(roadSectionDto.getOrgId());
		if (org != null) {
			roadSection.setOrgPrivilegeCode(org.orgPrivilegeCode);
		}

		String beginMeter = roadSectionDto.getBeginMeterG() + "+" + roadSectionDto.getBeginMeterM();
		if (roadSectionDto.getBeginMeterM() == null) {
			roadSection.setBeginMeter(beginMeter.substring(0, beginMeter.length() - 1));
		} else {
			roadSection.setBeginMeter(beginMeter);
		}

		String endMeter = roadSectionDto.getEndMeterG() + "+" + roadSectionDto.getEndMeterM();
		if (roadSectionDto.getEndMeterM() == null) {
			roadSection.setEndMeter(endMeter.substring(0, endMeter.length() - 1));
		} else {
			roadSection.setEndMeter(endMeter);
		}
		roadSection.setEndMeter(endMeter);
		ObjectMapUtils.parseObject(roadSection, roadSectionDto);
		saveRoadSection(roadSection);
		return 1;
	}
	/**
	 * saveRoadSection(把页面信息保存到数据库) TODO(这里描述这个方法适用条件 – 可选) TODO(这里描述这个方法的执行流程 –
	 * 可选) TODO(这里描述这个方法的使用方法 – 可选) TODO(这里描述这个方法的注意事项 – 可选)
	 *
	 * @Title: saveRoadSection @Description: TODO @param @param roadSection
	 *         设定文件 @return void 返回类型 @throws
	 */
	private void saveRoadSection(RoadSection roadSection) {

		roadService.saveRoadSection(roadSection);

	}
	/*
	 * <p>Title: searchSection</p> <p>Description:查询路段信息 </p>
	 * 
	 * @param sectionDto
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.road.rest.action.ISectionAction#searchSection(cy.its.road.rest.dto
	 * .SectionDto)
	 */

	@RequestMapping("/searchRoadSection")
	@Override
	public Map<String, Object> searchRoadSection(RoadSectionDto roadSectionDto) throws Exception {
		RoadSectionCriteria roadSection = new RoadSectionCriteria();
		// 设置查询页数
		roadSection.setPageNum(roadSectionDto.getPageNumber());
		// 设置每页的最大记录数
		roadSection.setPageSize(roadSectionDto.getPageSize());
		// 设置是否需要统计总数: 是
		roadSection.setNeedTotal(true);
		// 获取和设置UI端的输入条件
		roadSection.roadSectionCode = roadSectionDto.getRoadSectionCode();
		roadSection.roadSectionName = roadSectionDto.getRoadSectionName();
		roadSection.roadSectionType = roadSectionDto.getRoadSectionType();
		roadSection.roadId = roadSectionDto.getRoadId();
		roadSection.orgPrivilegeCode = roadSectionDto.getCurrentOrgPrivilegeCode();
		roadSection.setOrderName(roadSectionDto.getOrderName());
		roadSection.setOrderType(roadSectionDto.getOrderType());
		ArrayList<RoadSectionDto> lstView = new ArrayList<RoadSectionDto>();

		List<RoadSection> list = roadService.findRoadSectiones(roadSection);

		if (list != null) {
			for (RoadSection RSection : list) {
				RoadSectionDto sectionDo = new RoadSectionDto();
				if (!StringUtil.isNullOrEmpty(RSection.getRoadSectionCode())) {
					sectionDo.setRoadSectionCode(RSection.getRoadSectionCode());// 路段编号ID
				}
				if (!StringUtil.isNullOrEmpty(RSection.getRoadSectionName())) {
					sectionDo.setRoadSectionName(RSection.getRoadSectionName());// 路段名称
				}
				if (!StringUtil.isNullOrEmpty(RSection.getRoadSectionType())) {
					sectionDo.setRoadSectionType(RSection.getRoadSectionType());// 路段类型
				}
				if (!StringUtil.isNullOrEmpty(RSection.getRoadId())) {
					sectionDo.setRoadId(RSection.getRoadId());// 所属道路
				}
				if (RSection.getBeginMeter() != null) {
					String[] bMeter = RSection.getBeginMeter().split("\\+");
					if (bMeter.length > 0) {
						sectionDo.setBeginMeterG(bMeter[0]);
					}
					if (bMeter.length > 1) {
						sectionDo.setBeginMeterM(bMeter[1]);
					}
				}
				if (RSection.getEndMeter() != null) {
					String[] eMeter = RSection.getEndMeter().split("\\+");
					if (eMeter.length > 0) {
						sectionDo.setEndMeterG(eMeter[0]);
					}
					if (eMeter.length > 1) {
						sectionDo.setEndMeterM(eMeter[1]);
					}

				}
				sectionDo.setOldRoadSectionCode(RSection.getRoadSectionCode());
				sectionDo.setOldRoadId(RSection.getRoadId());
				ObjectMapUtils.parseObject(sectionDo, RSection);
				lstView.add(sectionDo);
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", "");
		Map<String, Serializable> maprow = new HashMap<String, Serializable>();
		maprow.put("rows", lstView);
		maprow.put("total", roadSection.getTotal());
		map.put("result", maprow);
		return map;

	}

	/*
	 * <p>Title: goUpdateSection</p> <p>Description:更新路段信息 </p>
	 * 
	 * @param sectionDto
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.road.rest.action.ISectionAction#goUpdateSection(cy.its.road.rest.
	 * dto.SectionDto)
	 */

	@RequestMapping("/goUpdateRoadSection")
	@Override
	public int goUpdateRoadSection(RoadSectionDto roadSectionDto) throws Exception {
		int flag = 0;//路段编号重复标识:"0",表示已存在;"1",表示不存在
		if (roadSectionDto.getRoadSectionCode().equals(roadSectionDto.getOldRoadSectionCode()) &&
				roadSectionDto.getRoadId().equals(roadSectionDto.getOldRoadId())) {
			flag = updateRoadSection(roadSectionDto);
		}else{
			RoadSectionCriteria criteria = new RoadSectionCriteria();
			criteria.roadId = roadSectionDto.getRoadId();
			List<RoadSection> roadSectionList = roadService.findRoadSectiones(criteria);
			if (roadSectionList != null && roadSectionList.size() != 0) {
				for (RoadSection roadSection : roadSectionList) {
					if (roadSectionDto.getRoadSectionCode().equals(roadSection.getRoadSectionCode())) {
						return flag;
					}
				}
				flag = updateRoadSection(roadSectionDto);
			} else {
				flag = updateRoadSection(roadSectionDto);
			}
		}
		return flag;
	}
	//添加一个更新路段方法
	private int updateRoadSection(RoadSectionDto roadSectionDto) throws Exception {
		RoadSection roadSection = new RoadSection();
		// 设置更新时间
		roadSection.setUpdateTime(new Date());
		// 根据机构Id获得机构权限代码转给领域Dto
		Organization org = dutyService.organizationOfId(roadSectionDto.getOrgId());
		if (org != null) {
			roadSection.setOrgPrivilegeCode(org.orgPrivilegeCode);
		}
		if (!StringUtil.isNullOrEmpty(roadSectionDto.getBeginMeterM())) {
			String beginMeter = roadSectionDto.getBeginMeterG() + "+" + roadSectionDto.getBeginMeterM();
			roadSection.setBeginMeter(beginMeter);
		} else {
			roadSection.setBeginMeter(roadSectionDto.getBeginMeterG());
		}

		if (!StringUtil.isNullOrEmpty(roadSectionDto.getEndMeterM())) {
			String endMeter = roadSectionDto.getEndMeterG() + "+" + roadSectionDto.getEndMeterM();
			roadSection.setEndMeter(endMeter);
		} else {
			roadSection.setEndMeter(roadSectionDto.getEndMeterG());
		}
		// 把客户端的Dto转换领域Dto
		ObjectMapUtils.parseObject(roadSection, roadSectionDto);
		// 更新方法
		updateRdSection(roadSection);
		return 1;
	}

	/**
	 * updateRoadSection(把更新的页面路段信息保存数据库) TODO(这里描述这个方法适用条件 – 可选)
	 * TODO(这里描述这个方法的执行流程 – 可选) TODO(这里描述这个方法的使用方法 – 可选) TODO(这里描述这个方法的注意事项 –
	 * 可选)
	 *
	 * @Title: updateRoadSection @Description: TODO
	 * @param @param
	 *            roadSection 设定文件 @return void 返回类型 @throws
	 */

	private void updateRdSection(RoadSection roadSection) {
		roadService.updateRoadSection(roadSection);
	}

	/*
	 * <p>Title: goRemoveRoadSection</p> <p>Description: 批量删除</p>
	 * 
	 * @param sectionIdStr
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.road.rest.action.ISectionAction#goDeleteSection(java.lang.String)
	 */
	@RequestMapping("/goRemoveRoadSection")
	@Override
	public String goRemoveRoadSection(@RequestParam("ids") String ids) {
		String[] idarr = ids.split(",");

		// 通过Arrays.asList将字符串数组转化为List<String>集合对象

		List<String> idList = Arrays.asList(idarr);

		Map<String, Object> map = new HashMap<String, Object>();

		// 将集合对象放置到HashMap中

		map.put("idList", idList);

		// 调用mysqlbatis删除的方法

		roadService.removeRoadSection(map);
		
		return "success";
	}

	/*
	 * <p>Title: goDeleteRoadSection</p> <p>Description: 单个删除</p>
	 * 
	 * @param sectionId
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.road.rest.action.ISectionAction#goRemoveSection(java.lang.String)
	 */
	@RequestMapping("/goDeleteRoadSection")
	@Override
	public String goDeleteRoadSection(@RequestParam("roadSectionId") String roadSectionId) {
		// 删除路段信息
		roadService.deleteRoadSection(roadSectionId);
		
		return "success";
	}
   
	// 根据路段ID查询断面信息
	@RequestMapping("/querySectionByRoadSectionId")
	@Override
	public int querySectionByRoadSectionId(String roadSectionId) throws Exception {
		// TODO Auto-generated method stub
		int sectionNum = 0;// 该路段下的断面数
		RoadSection roadSection = roadService.roadSectionOfId(roadSectionId);
		String isOneDirection = roadSection.getIsOneDirection();
		if ("1".equals(isOneDirection)) {
			sectionNum = 1;
		} else if ("0".equals(isOneDirection)) {
			sectionNum = 2;
		} else {
			sectionNum = 2;
		}
		return sectionNum;
	}
	//根据路段ID查询点位信息
	@RequestMapping("/queryBySectionRoadId")
	public int queryBySectionRoadId(String roadSectionId){
		int flag=0;
		String[] rSectionId=roadSectionId.split(",");
		for(String roadSId : rSectionId){
			SiteCriteria siteCriteria =new SiteCriteria();
			siteCriteria.roadSectionId = roadSId;
			List<Site> site= siteService.findSites(siteCriteria);
			if(site !=null){
				flag = flag + site.size();
				if(flag>0){
					return 1;
				}
			}
		}
		return flag;
	}
	
	
}
