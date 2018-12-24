/**
 * @Title: InfoTreeAction.java
 * @Package cy.its.sysCfg.rest.aciton.impl
 * @Description: TODO(这里要填写用途)
 * @author gyf guanyf@cychina.cn
 * @date 2015年10月30日 下午3:36:21
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.sysCfg.rest.action.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cy.its.sysCfg.rest.action.IInfoTreeAction;
import cy.its.sysCfg.rest.dto.EnforceStationDto;
import cy.its.sysCfg.rest.dto.InfoTreeDto;
import cy.its.sysCfg.rest.dto.OverRunCheckPointDto;
import cy.its.sysCfg.rest.dto.PolicePostDto;
import cy.its.syscfg.domain.model.duty.Organization;
import cy.its.syscfg.domain.model.traffic.EnforceStation;
import cy.its.syscfg.domain.model.traffic.OverRunCheckPoint;
import cy.its.syscfg.domain.model.traffic.PoliceArea;
import cy.its.syscfg.domain.model.traffic.PolicePost;
import cy.its.syscfg.domain.service.IDutyService;
import cy.its.syscfg.domain.service.IEnforceStationService;
import cy.its.syscfg.domain.service.IOverRunCheckPointService;
import cy.its.syscfg.domain.service.IPoliceAreaService;
import cy.its.syscfg.domain.service.IPolicePostService;

@RestController
@RequestMapping("/sysCfg/traffic")
public class InfoTreeAction implements IInfoTreeAction {

	@Autowired
	IEnforceStationService enforceStationService;
	@Autowired
	IPoliceAreaService policeAreaService;
	@Autowired
	IPolicePostService policePostService;
	@Autowired
	IOverRunCheckPointService overRunCheckPointService;
	@Autowired
	IDutyService dutyService;
	
	/*
	 * <p>Title: getInfoTree</p> <p>Description: </p>
	 * @return
	 * @throws Exception
	 * @see cy.its.sysCfg.rest.aciton.IInfoTreeAction#getInfoTree()
	 */
	@Override
	@RequestMapping("/getTree")
	public List<InfoTreeDto> getInfoTree() throws Exception {
		List<InfoTreeDto> infoRoots = new ArrayList<InfoTreeDto>();
		InfoTreeDto treeNode1 = new InfoTreeDto();
		treeNode1.setId("1");
		treeNode1.setText("执法服务站");
		if(enforceParseToTree().size()>0){
			treeNode1.setChildren(enforceParseToTree());
		}
		else{
			treeNode1.setChildren(null);
		}
		
		infoRoots.add(treeNode1);
		
		InfoTreeDto treeNode2 = new InfoTreeDto();
		treeNode2.setId("2");
		treeNode2.setText("交警岗亭");
		if(policePostParseToTree().size()>0){
			treeNode2.setChildren(policePostParseToTree());
		}
		else{
			treeNode2.setChildren(null);
		}
		infoRoots.add(treeNode2);

		InfoTreeDto treeNode3 = new InfoTreeDto();
		treeNode3.setId("3");
		treeNode3.setText("超限检查站");
		if(overRunParseToTree().size()>0){
			treeNode3.setChildren(overRunParseToTree());
		}
		else{
			treeNode3.setChildren(null);
		}
		infoRoots.add(treeNode3);
		
//		InfoTreeDto treeNode4 = new InfoTreeDto();
//		treeNode4.setId("4");
//		treeNode4.setText("交警辖区");
//		treeNode4.setChildren(areaParseToTree());
//		infoRoots.add(treeNode4);
		
		
		// 添加一个统一的根节点
		List<InfoTreeDto> roots = new ArrayList<InfoTreeDto>();
		InfoTreeDto root = new InfoTreeDto();
		root.setId("0");
		root.setText("交警机关");
		root.setChildren(infoRoots);
		roots.add(root);
		return infoRoots;
	}
	
	
	private List<InfoTreeDto> policePostParseToTree(){
		List<InfoTreeDto> children=new ArrayList<InfoTreeDto>();
		List<PolicePost> posts= policePostService.selectAll(new HashMap());
		for (PolicePost item : posts) {
			InfoTreeDto child=new InfoTreeDto();
			child.setId(item.getPolicePostId());
			child.setText(item.getPostName());
			child.setAttribute(new PolicePostDto(item));
			children.add(child);
		}		
		return children;
	}
	
	private List<InfoTreeDto> enforceParseToTree(){
		List<InfoTreeDto> children=new ArrayList<InfoTreeDto>();
		List<EnforceStation> enforces= enforceStationService.selectAll(new HashMap());
		for (EnforceStation item : enforces) {
			InfoTreeDto child=new InfoTreeDto();
			child.setId(item.getLawEnforceStationId());
			child.setText(item.getLawEnforceStationName());	
			child.setAttribute(new EnforceStationDto(item));
			children.add(child);
		}		
		return children;
	}
	
	
	private List<InfoTreeDto> overRunParseToTree(){
		List<InfoTreeDto> children=new ArrayList<InfoTreeDto>();
		List<OverRunCheckPoint> posts= overRunCheckPointService.selectAll(new HashMap());
		for (OverRunCheckPoint item : posts) {
			InfoTreeDto child=new InfoTreeDto();
			child.setId(item.getCheckpointId());
			child.setText(item.getCheckpointName());
			child.setAttribute(new OverRunCheckPointDto(item));
			children.add(child);
		}		
		return children;
	}
	
	private List<InfoTreeDto> areaParseToTree() throws Exception{
		List<InfoTreeDto> children=new ArrayList<InfoTreeDto>();
		List<PoliceArea> posts= policeAreaService.selectAll(new HashMap());
		for (PoliceArea item : posts) {
			InfoTreeDto child=new InfoTreeDto();
			child.setId(item.getPoliceAreaId());
			if(item.getOrgId()!=null){
			 Organization org=	dutyService.organizationOfId(item.getOrgId());
			 child.setText(org.orgName);
			}						
			child.setAttribute(item);
			children.add(child);
		}
		
		return children;
	}

	

	/*
	 * <p>Title: createTree</p> <p>Description: </p>
	 * @return
	 * @see cy.its.sysCfg.rest.aciton.IInfoTreeAction#createTree()
	 */

	@Override
	public String createTree() {
		return null;
	}

}
