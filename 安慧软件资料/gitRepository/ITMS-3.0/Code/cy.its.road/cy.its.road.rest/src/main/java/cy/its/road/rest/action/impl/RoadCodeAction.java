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
import org.springframework.web.bind.annotation.ModelAttribute;
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
import cy.its.road.domain.criteria.CrossCriteria;
import cy.its.road.domain.criteria.RegionCriteria;
import cy.its.road.domain.criteria.RoadCriteria;
import cy.its.road.domain.criteria.RoadSectionCriteria;
import cy.its.road.domain.model.region.Region;
import cy.its.road.domain.model.road.Cross;
import cy.its.road.domain.model.road.Road;
import cy.its.road.domain.model.road.RoadSection;
import cy.its.road.domain.service.IRegionService;
import cy.its.road.domain.service.IRoadService;
import cy.its.road.rest.action.IRoadCodeAction;
import cy.its.road.rest.dto.RoadDto;
import cy.its.road.rest.dto.RoadTreeDto;
import cy.its.syscfg.domain.model.duty.Organization;
import cy.its.syscfg.domain.model.sysCode.Code;
import cy.its.syscfg.domain.service.IDutyService;
import cy.its.syscfg.domain.service.ISysCodeService;

@RestController
@RequestMapping("/road/roadCode")
public class RoadCodeAction implements IRoadCodeAction {

	@Autowired
	IRoadService roadService;
	
	@Autowired
	IRegionService RegionService;
	
	@Autowired
	ISysCodeService sysCodeService;

	@Autowired
	IDutyService dutyService;
	
	@Autowired
	ISiteService siteService;

	@Autowired
	ISystemService systemService; 
	
	/**
	 * 获取道路树数据
	 * 
	 * @return 道路树对象
	 * @throws Exception
	 */
	@RequestMapping("/getRoadCodeTree")
	public List<RoadTreeDto> getRoadCodeTree(String currentOrgPrivilegeCode) throws Exception {
		// 查找所有的道路信息
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
	private List<RoadTreeDto> convertToRoadTree(List<Code> roadTypeList, List<Road> roadList) {
		List<RoadTreeDto> types = new ArrayList<RoadTreeDto>();
		// 首先初始化道路编号
		roadTypeList.stream().forEach(item -> {
			RoadTreeDto treeRoot = new RoadTreeDto();
			treeRoot.setId(item.codeNo);
			treeRoot.setText(item.codeName);
			treeRoot.setChildren(new ArrayList<RoadTreeDto>());
			Map<String, String> attribute = new HashMap<String, String>();
			attribute.put("nodeType", "raodType");
			treeRoot.setAttribute(attribute);
			types.add(treeRoot);
		});
		if(roadList != null && !roadList.isEmpty()){
			// 将道路数据添加到对应的道路类型节点下
			roadList.stream().forEach(item -> {
				List<RoadTreeDto> children = findChildrenByRoadType(types, item.getRoadType());
				if (children != null) {
					RoadTreeDto treeNode = new RoadTreeDto();
					treeNode.setId(item.getRoadId());
					treeNode.setText(item.getRoadName());
					Map<String, String> attribute = new HashMap<String, String>();
					attribute.put("nodeType", "road");
					attribute.put("code", item.getRoadCode());//编码
					attribute.put("orgId", item.getOrgId());//机构ID
					attribute.put("orgPrivCode", item.getOrgPrivilegeCode());//机构权限编码
					attribute.put("districtCode", item.getDistrictCodeList());//行政区划编码
					treeNode.setAttribute(attribute);
					children.add(treeNode);
				}
			});
		}
		// 添加一个统一的根节点
		List<RoadTreeDto> roots = new ArrayList<RoadTreeDto>();
		RoadTreeDto root = new RoadTreeDto();
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
	private List<RoadTreeDto> findChildrenByRoadType(List<RoadTreeDto> roots, String roadType) {
		Iterator<RoadTreeDto> iter = roots.iterator();
		while (iter.hasNext()) {
			RoadTreeDto item = iter.next();
			if (item.getId().equals(roadType)) {
				return item.getChildren();
			}
		}
		return null;
	}

	/**
	 * 新增道路信息
	 */
	@RequestMapping("/saveRoad")
	@Override
	public String createRoad(RoadDto roadDto) throws Exception {
		String flag="errorRoadCode";
		RoadCriteria roadCriteria = new RoadCriteria();
		roadCriteria.roadCode = roadDto.getRoadCode();
		List<Road> list = roadService.findRoads(roadCriteria);
		if (list == null || list.size() == 0) {
			flag = addRoad(roadDto);
		}
		return flag;
	}
		// 创建道路对象
		private String addRoad(RoadDto roadDto) throws Exception {
		// TODO Auto-generated method stub
			Road road = new Road();
			if ((roadDto.getRoadType()).equals("5") || roadDto.getRoadType().equals("6")
					|| roadDto.getRoadType().equals("7") || roadDto.getRoadType().equals("8")) {

				road.setDirectionType(roadDto.getDirectionTypeCity());
			} else if (roadDto.getNoDirectionTypeCity().equals("0")) {
				road.setDirectionType("1" + "," + "2");
			} else {
				road.setDirectionType(roadDto.getNoDirectionTypeCity());
			}

			if (!StringUtil.isNullOrEmpty(roadDto.getRoadLength())) {
				road.setRoadLength(Double.parseDouble(roadDto.getRoadLength()));
			}
			// 根据机构Id获得机构权限代码转给领域Dto
			if (!StringUtil.isNullOrEmpty(roadDto.getOrgId())) {
				String[] orgId = roadDto.getOrgId().split(",");
				StringBuilder strBuilder = new StringBuilder();
				for (int i = 0; i < orgId.length; i++) {
					Organization org = dutyService.organizationOfId(orgId[i]);
					if (org != null) {
						strBuilder.append(org.orgPrivilegeCode).append(",");
					}
				}

				road.setOrgPrivilegeCode(strBuilder.substring(0, strBuilder.length() - 1));
			}
			// 客户端Dto转换领域Dto
			ObjectMapUtils.parseObject(road, roadDto);
			roadService.roadChanged();
			return saveRoad(road);
		}
	/**
	 * saveRoad(新增道路信息) TODO(这里描述这个方法适用条件 – 可选) TODO(这里描述这个方法的执行流程 –可选)
	 * TODO(这里描述这个方法的使用方法 – 可选) TODO(这里描述这个方法的注意事项 – 可选) @Title:
	 * saveRoad @Description: TODO @param @param road 设定文件 @return void
	 * 返回类型 @throws
	 */
	private String saveRoad(Road road) {
		return roadService.saveRoad(road);

	}

	/*
	 * <p>Title: searchRoad</p> <p>Description:查询功能 </p>
	 * 
	 * @param roadDto
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.road.rest.action.IRoadCodeAction#searchRoad(cy.its.road.rest.dto.
	 * RoadDto)
	 */

	@RequestMapping("/searchRoadList")
	@Override
	public Map<String, Object> searchRoad(RoadDto roadDto) throws Exception {

		RoadCriteria roadCriteria = new RoadCriteria();
		// 设置查询页数
		roadCriteria.setPageNum(roadDto.getPageNumber());
		// 设置每页的最大记录数
		roadCriteria.setPageSize(roadDto.getPageSize());
		// 设置是否需要统计总数: 是
		roadCriteria.setNeedTotal(true);
		// 获取和设置UI端的输入条件
		roadCriteria.roadCode = roadDto.getRoadCode();
		roadCriteria.roadName=roadDto.getRoadName();
		roadCriteria.roadType = roadDto.getRoadType();
		roadCriteria.districtCode = roadDto.getDistrictCode();
		roadCriteria.orgPrivilegeCode = roadDto.getCurrentOrgPrivilegeCode();
		roadCriteria.setOrderName(roadDto.getOrderName());
		roadCriteria.setOrderType(roadDto.getOrderType());
		// 返回页面lstView集合
		ArrayList<RoadDto> lstView = new ArrayList<RoadDto>();
		// 获得list集合
		List<Road> list = roadService.findRoads(roadCriteria);

		if (list != null) {
			for (Road road : list) {
				RoadDto roadDo = new RoadDto();
				// 根据roadType判断;给方向类型("070","072")赋值
				if (road.getRoadType().equals("5") || road.getRoadType().equals("6") || road.getRoadType().equals("7")
						|| road.getRoadType().equals("8")) {

					roadDo.setDirectionTypeCity(road.getDirectionType());

				} else {
					if (!StringUtil.isNullOrEmpty(road.getDirectionType())) {
						if (road.getDirectionType().equals("1,2")) {

							roadDo.setNoDirectionTypeCity("0");

						} else {
							roadDo.setNoDirectionTypeCity(road.getDirectionType());
						}
					}
				}
				if (road.getRoadLength() != null) {
					roadDo.setRoadLength(road.getRoadLength().toString());
				}
				if (!StringUtil.isNullOrEmpty(road.getRoadCode())) {
					roadDo.setRoadCode(road.getRoadCode());// 道路编号ID
				}
				if (!StringUtil.isNullOrEmpty(road.getRoadName())) {
					roadDo.setRoadName(road.getRoadName());// 道路名称
				}
				if (!StringUtil.isNullOrEmpty(road.getRoadType())) {
					roadDo.setRoadType(road.getRoadType());// 道路类型
				}
				if (!StringUtil.isNullOrEmpty(road.getDistrictCodeList())) {
					roadDo.setDistrictCode(road.getDistrictCodeList());// 行政区划
				}
				if (!StringUtil.isNullOrEmpty(road.getOrgId())) {
					String[] orgId = road.getOrgId().split(",");
					for (int i = 0; i < orgId.length; i++) {
						roadDo.setOrgPrivilegeCode(orgId[i]);
					}
				}
				roadDo.setOldRoadCode(road.getRoadCode());
				ObjectMapUtils.parseObject(roadDo, road);
				// 把每一次遍历Road数据添加到lstView
				lstView.add(roadDo);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", "");
		Map<String, Serializable> maprow = new HashMap<String, Serializable>();
		maprow.put("rows", lstView);
		maprow.put("total", roadCriteria.getTotal());
		map.put("result", maprow);

		return map;
	}

	/*
	 * <p>Title: updateRoad</p> <p>Description:更新道路信息 </p>
	 * 
	 * @param road
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.road.rest.action.IRoadCodeAction#updateRoad(cy.its.road.rest.dto.
	 * RoadDto)
	 */
	@RequestMapping("/updateRoad")
	@Override
	public int goUpdateRoad(@ModelAttribute(value = "roadId") RoadDto roadDto) throws Exception {
		int flag=0;
		if(roadDto.getRoadCode().equals(roadDto.getOldRoadCode())){
			flag = updateRoadMessager(roadDto);
		}else{
			RoadCriteria roadCriteria = new RoadCriteria();
			roadCriteria.roadCode = roadDto.getRoadCode();
			List<Road> list = roadService.findRoads(roadCriteria);
			if (list == null || list.size() == 0) {
				flag = updateRoadMessager(roadDto);
			}else{
				return flag;
			}
		}
		return flag;
	}
		
		private int updateRoadMessager(RoadDto roadDto) throws Exception {
			// TODO Auto-generated method stub
			// 创建道路对象
			Road road = new Road();
			// 设置更新时间
			road.setUpdateTime(new Date());
			// 客户端Dto转换领域 Dto
			// 根据道路类型判断，分别赋值给方向类型
			if (roadDto.getRoadType().equals("5") || roadDto.getRoadType().equals("6") || roadDto.getRoadType().equals("7")
					|| roadDto.getRoadType().equals("8")) {

				road.setDirectionType(roadDto.getDirectionTypeCity());
			} else if (roadDto.getNoDirectionTypeCity().equals("0")) {
				road.setDirectionType("1" + "," + "2");
			} else {
				road.setDirectionType(roadDto.getNoDirectionTypeCity());
			}
			if (!StringUtil.isNullOrEmpty(roadDto.getRoadLength())) {
				road.setRoadLength(Double.parseDouble(roadDto.getRoadLength()));
			}
			// 根据机构Id获得机构权限代码转给领域Dto
			if (!StringUtil.isNullOrEmpty(roadDto.getOrgId())) {
				String[] orgId = roadDto.getOrgId().split(",");
				StringBuilder strBuilder = new StringBuilder();
				for (int i = 0; i < orgId.length; i++) {
					Organization org = dutyService.organizationOfId(orgId[i]);
					if (org != null) {
						strBuilder.append(org.orgPrivilegeCode).append(",");
					}
				}
				road.setOrgPrivilegeCode(strBuilder.substring(0, strBuilder.length() - 1));
			}
			ObjectMapUtils.parseObject(road, roadDto);

			updateRoad(road);// 调用更新方法

			roadService.roadChanged();// 变更通知

			return 1;

		}
		/**
		 * updateRoad(更新道路信息) TODO(这里描述这个方法适用条件 – 可选) TODO(这里描述这个方法的执行流程 – 可选)
		 * TODO(这里描述这个方法的使用方法 – 可选) TODO(这里描述这个方法的注意事项 – 可选) @Title:
		 * updateRoad @Description: TODO @param @param road 设定文件 @return void
		 * 返回类型 @throws
		 */

		private void updateRoad(Road road) {
			roadService.updateRoad(road);
		}
	
			
	/*
	 * <p>Title: deleteRoad</p> <p>Description: 单个删除</p>
	 * 
	 * @param roadIdStr
	 * 
	 * @return
	 * 
	 * @see cy.its.road.rest.action.IRoadCodeAction#deleteRoad(java.lang.String)
	 */
	@RequestMapping("/goDeleteRoad")
	@Override
	public String goDeleteRoad(String roadId) {

			// 删除道路信息
			roadService.deleteRoad(roadId);
			// 变更通知
			roadService.roadChanged();

		return "success";
	}

	/*
	 * <p>Title: removeRoad</p> <p>Description: 批量删除</p>
	 * 
	 * @param roadId
	 * 
	 * @return
	 * 
	 * @see cy.its.road.rest.action.IRoadCodeAction#removeRoad(java.lang.String)
	 */
	@RequestMapping("/goRemoveRoad")
	public  String goRemoveRoad(String ids) {
		  
		String[] idarr = ids.split(",");

		//通过Arrays.asList将字符串数组转化为List<String>集合对象

		List<String> idList = Arrays.asList(idarr);

		Map<String,Object> map = new HashMap<String,Object>();

		//将集合对象放置到HashMap中

		map.put("idList", idList);

		//调用mysqlbatis删除方法

		roadService.removeRoad(map);
		// 变更通知
		roadService.roadChanged();
		
		return "success";

	}

	/**
	 * 根据道路ID查询该道路是否单向通行来添加断面
	 * 
	 * @throws Exception
	 */
	@Override
	@RequestMapping("/querySectionByRoadId")
	public int querySectionByRoadId(@RequestParam("roadId") String roadId) throws Exception {
		// TODO Auto-generated method stub
		int sectionNum = 0;// 该道路下的断面数
		Road road = roadService.roadOfId(roadId);
		String isOneDirection = road.getIsOneDirection();
		if ("1".equals(isOneDirection)) {
			sectionNum = 1;
		} else if ("0".equals(isOneDirection)) {
			sectionNum = 2;
		} else {
			sectionNum = 2;
		}
		return sectionNum;
	}
	
	/**
	 * 根据roadId和directionType查询方向类型
	 * @param roadId
	 * @param directionType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryDirectionNameByRoadId")
	public String queryDirectionNameByRoadId(String roadId,String directionType) throws Exception{
		String directionName = null;
		Road road = roadService.roadOfId(roadId);
		
		if(roadId != null && directionType !=null){
			if("1".equals(directionType)){
				directionName = road.getUpDirection();
			}else{
				directionName = road.getUpDirection();
			}
		}
		System.out.println(directionName);
		return directionName;
	}
	
	/**
	 * 根据道路ID查询设备，看此道路上的点位是否已绑定设备，或者已绑定路段
	 * @param roadId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryByRoadId")
	public int queryByRoadId(@RequestParam("roadId") String roadId){
		int flag = 0;
		String[] rId=roadId.split(","); 
		for(String id : rId){
			//根据道路查询点位信息
			SiteCriteria siteCriteria = new SiteCriteria();
			siteCriteria.roadId = id;
			List<Site> site = siteService.findSites(siteCriteria);
			if (site != null) {
				if (site != null) {
					flag = flag + site.size();
					if (flag > 0) {
						return 1;
					}
				}
			}
			//判断路段是否绑定道路
			RoadSectionCriteria criteria = new RoadSectionCriteria();
			criteria.roadId = id;
			List<RoadSection> roadSectionList = roadService.findRoadSectiones(criteria);
			if (roadSectionList != null) {
				flag = flag + roadSectionList.size();
				if (flag > 0) {
					return 2;
				}
			}
		}
		return flag;
	}
	
	/**
	 * 根据roadId获取道路信息
	 */
	@RequestMapping("/queryRoadInfoById")
	public RoadDto queryRoadInfoById(@RequestParam("roadId") String roadId) throws Exception{
		Road road = roadService.roadOfId(roadId);
		RoadDto roadDto = new RoadDto();
		ObjectMapUtils.parseObject(roadDto, road);
		return roadDto;
	}

}
