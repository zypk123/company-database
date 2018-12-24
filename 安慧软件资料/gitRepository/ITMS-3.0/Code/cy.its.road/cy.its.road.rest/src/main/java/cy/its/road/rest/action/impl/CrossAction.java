package cy.its.road.rest.action.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Title: CrossAction.java
 * @Package cy.its.road.rest.action.impl
 * @Description: TODO 路口管理rest服务
 * @author zuop zuop@cychina.cn
 * @date 2015年11月14日 下午1:49:47
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.road.domain.criteria.CrossCriteria;
import cy.its.road.domain.model.road.Cross;
import cy.its.road.domain.service.IRoadService;
import cy.its.road.rest.action.ICrossAction;
import cy.its.road.rest.dto.CrossDto;
import cy.its.road.rest.dto.CrossTreeDto;
import cy.its.syscfg.domain.model.duty.Organization;
import cy.its.syscfg.domain.service.IDutyService;

@RestController
@RequestMapping("/road/cross")
public class CrossAction implements ICrossAction {

	@Autowired
	IRoadService roadService;

	@Autowired
	IDutyService dutyService;

	/**
	 * @throws Exception
	 * 
	 * findCrossByOrgId 根据机构Id查找路口信息 @Title:
	 * findCrossByOrgId @Description: TODO @param @param
	 * orgId @param @return 设定文件 @return List 返回类型 @throws
	 */
	@RequestMapping("findCrossByOrgId")
	public List<CrossTreeDto> findCrossByOrgId(String id, String rootId) throws Exception {
		List<CrossTreeDto> returnList = new ArrayList<CrossTreeDto>();
		// 初始化加载根节点和下级节点
		if (id == null || "".equals(id)) {
			Organization rootOrg = dutyService.organizationOfId(rootId);
			List<Organization> children = dutyService.findOrgsOfParent(rootId);
			CrossTreeDto orgTreeDto = new CrossTreeDto(rootOrg);
			orgTreeDto.setState("open");
			for (Organization org : children) {
				orgTreeDto.getChildren().add(new CrossTreeDto(org));
			}
			returnList.add(orgTreeDto);
		} else {
			// 加载子节点
			// 找到路口信息
			CrossCriteria criteria = new CrossCriteria();
			criteria.setNoPage();
			criteria.orgId = id;
			List<Cross> crossList = roadService.findCrosses(criteria);
			for (Cross cross : crossList) {
				returnList.add(new CrossTreeDto(cross));
			}
			// 找到子机构信息
			List<Organization> orgList = dutyService.findOrgsOfParent(id);
			for (Organization org : orgList) {
				returnList.add(new CrossTreeDto(org));
			}
		}
		return returnList;
	}

	/**
	 * 
	 * findCorssInfo 查找路口 @Title: findCorssInfo @Description: TODO @param @param
	 * searchIndex @param @return 设定文件 @return List 返回类型 @throws
	 */
	@RequestMapping("/findCrossInfo")
	public Map<String, Object> findCorssInfo(String searchIndex) {
		CrossCriteria criteria = new CrossCriteria();
		criteria.setNoPage();
		criteria.crossName = searchIndex;
		List<Cross> crossList = roadService.findCrosses(criteria);
		return returnResult(crossList, crossList.size());
	}

	/**
	 * 封装页面返回数据
	 * 
	 * @param dataList
	 * @param total
	 * @return
	 */
	private Map<String, Object> returnResult(List<Cross> dataList, int total) {
		// 封装返回集
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("error", "");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", total);
		resultMap.put("rows", dataList);
		returnMap.put("result", resultMap);
		return returnMap;
	}

	/*
	 * <p>Title: createCross</p> <p>Description: 创建路口信息</p>
	 * 
	 * @param crossDto
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.road.rest.action.ICrossAction#createCross(cy.its.road.rest.dto.
	 * CrossDto)
	 */

	@RequestMapping("/saveCross")
	@Override
	public String createCross(CrossDto crossDto) throws Exception {

		Cross cross = new Cross();
		if (!StringUtil.isNullOrEmpty(crossDto.getCrossLatitude())) {
			cross.setCrossLatitude(new BigDecimal(crossDto.getCrossLatitude()));
		}
		if (!StringUtil.isNullOrEmpty(crossDto.getCrossLongitude())) {
			cross.setCrossLongitude(new BigDecimal(crossDto.getCrossLongitude()));
		}
		// 根据机构Id获得机构权限代码转给领域Dto
		Organization org = dutyService.organizationOfId(crossDto.getOrgId());
		if (org != null) {
			cross.setOrgPrivilegeCode(org.orgPrivilegeCode);
		}
				
		// 将dto转化成领域对象
		ObjectMapUtils.parseObject(cross, crossDto);
		roadService.saveCross(cross);
		return "success";
	}

	/*
	 * <p>Title: findCross</p> <p>Description: 查询路口信息</p>
	 * 
	 * @param crossDto
	 * 
	 * @return
	 * 
	 * @see cy.its.road.rest.action.ICrossAction#findCross(cy.its.road.rest.dto.
	 * CrossDto)
	 */

	@RequestMapping("/findCross")
	@Override
	public Map<String, Object> findCross(CrossDto crossDto) throws Exception {
		/**
		 * 构建查询条件
		 */
		CrossCriteria crossCriteria = new CrossCriteria();
		crossCriteria.crossCode = crossDto.getCrossCode();
		crossCriteria.crossName = crossDto.getCrossName();
		crossCriteria.roadId = crossDto.getRoadId();
		crossCriteria.crossType = crossDto.getCrossType();
		crossCriteria.setNeedTotal(true);
		crossCriteria.setPageNum(crossDto.getPageNumber());
		crossCriteria.setPageSize(crossDto.getPageSize());
		ArrayList<CrossDto> lstView = new ArrayList<CrossDto>();
		// 获得路口信息List集合
		List<Cross> crossLst = roadService.findCrosses(crossCriteria);
		for (Cross cross : crossLst) {
			CrossDto Dto = new CrossDto();
			if (cross.getCrossLatitude() != null) {
				Dto.setCrossLatitude(cross.getCrossLatitude().toString());
			}
			if (cross.getCrossLongitude() != null) {
				Dto.setCrossLongitude(cross.getCrossLongitude().toString());
			}
			// 将领域对象转化为Dto
			ObjectMapUtils.parseObject(Dto, cross);
			lstView.add(Dto);
		}
		// 封装返回结果集
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", "");
		Map<String, Object> rows = new HashMap<String, Object>();
		rows.put("total", crossCriteria.getTotal());
		rows.put("rows", lstView);
		map.put("result", rows);
		return map;
	}

	/*
	 * <p>Title: updateCross</p> <p>Description:更新路口信息 </p>
	 * 
	 * @param crossDto
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.road.rest.action.ICrossAction#updateCross(cy.its.road.rest.dto.
	 * CrossDto)
	 */

	@RequestMapping("/updateCross")
	@Override
	public String updateCross(CrossDto crossDto) throws Exception {

		Cross cross = new Cross();

		if (!StringUtil.isNullOrEmpty(crossDto.getCrossLatitude())) {
			cross.setCrossLatitude(new BigDecimal(crossDto.getCrossLatitude()));
		}
		if (!StringUtil.isNullOrEmpty(crossDto.getCrossLongitude())) {
			cross.setCrossLongitude(new BigDecimal(crossDto.getCrossLongitude()));
		}
		// 根据机构Id获得机构权限代码转给领域Dto
		Organization org = dutyService.organizationOfId(crossDto.getOrgId());
		if (org != null) {
			cross.setOrgPrivilegeCode(org.orgPrivilegeCode);
		}
		// 将dto转化成领域对象
		ObjectMapUtils.parseObject(cross, crossDto);

		roadService.updateCross(cross);

		return "success";

	}

	/*
	 * <p>Title: deleteCrossIds</p> <p>Description: 批量删除CrossIds</p>
	 * 
	 * @param crossIdStr
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.road.rest.action.ICrossAction#deleteCrossIds(java.lang.String)
	 */

	@RequestMapping("removeCrossIds")
	@Override
	public String removeCrossIds(String crossIdStr) {
		// TODO Auto-generated method stub
		String[] idarr = crossIdStr.split(",");

		// 通过Arrays.asList将字符串数组转化为List<String>集合对象

		List<String> idList = Arrays.asList(idarr);

		Map<String, Object> map = new HashMap<String, Object>();

		// 将集合对象放置到HashMap中

		map.put("idList", idList);

		// 调用mysqlbatis删除方法

		roadService.removeCross(map);
		// 变更通知
		roadService.roadChanged();
		return "success";
	}

	/*
	 * <p>Title: removeCrossId</p> <p>Description: 单个删除CrossId</p>
	 * 
	 * @param crossId
	 * 
	 * @return
	 * 
	 * @see cy.its.road.rest.action.ICrossAction#removeCrossId(java.lang.String)
	 */

	@RequestMapping("/deleteCrossId")
	@Override
	public String deleteCrossId(String crossId) {
		// TODO Auto-generated method stub
		roadService.deleteCross(crossId);
		return "success";
	}
}
