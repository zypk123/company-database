/**
 * @Title: CrossTreeDto.java
 * @Package cy.its.road.rest.dto
 * @Description: TODO(这里要填写用途)
 * @author zuop zuop@cychina.cn
 * @date 2015年11月16日 上午10:19:48
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.road.rest.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cy.its.road.domain.model.road.Road;
import cy.its.road.domain.model.road.RoadSection;
import cy.its.syscfg.domain.model.sysCode.Code;

/**
  * @ClassName: CrossTreeDto
  * @Description: 封装路段树的dto
  * @author zuop zuop@cychina.cn
  * @date 2015年11月16日 上午10:19:48
  *
  */
public class RoadSectionTreeDto {
	private String id;
	
	private String text;
	
	private String state;
	
	private List<RoadSectionTreeDto> children = new ArrayList<RoadSectionTreeDto>();
	
	private Map<String,Object> attribute = new HashMap<String,Object>();

	/**
	 * 
	
	  * 创建一个新的实例 CrossTreeDto.  空构造方法 
	 */
	public RoadSectionTreeDto(){
		
	}
	
	/**
	  * 创建一个新的实例 roadSection. 使用路段领域对象创建树节点
	 */
	public RoadSectionTreeDto(RoadSection roadSection){
		this.id = roadSection.getRoadSectionId();
		this.text = roadSection.getRoadSectionName();
		this.attribute.put("code", roadSection.getRoadSectionCode());
		this.attribute.put("orgId", roadSection.getOrgId());
		this.attribute.put("orgPrivCode", roadSection.getOrgPrivilegeCode());
		this.attribute.put("roadId", roadSection.getRoadId());
		this.attribute.put("nodeType", "roadSection");
	}
	
	/**
	  * 创建一个新的实例 roadType. 使用道路类型领域对象创建节点
	 */
	public RoadSectionTreeDto(Code roadType){
		this.id = roadType.codeNo;
		this.text = roadType.codeName;
		this.state = "closed";
		this.attribute.put("nodeType", "roadType");
	}
	
	/**
	  * 创建一个新的实例 RoadSectionTreeDto.  使用道路领域对象创建节点
	 */
	public RoadSectionTreeDto(Road road){
		this.id = road.getRoadId();
		this.text = road.getRoadName();
		this.state = "closed";
		this.attribute.put("code", road.getRoadCode());
		this.attribute.put("orgId", road.getOrgId());
		this.attribute.put("orgPrivCode", road.getOrgPrivilegeCode());
		this.attribute.put("nodeType", "road");
	}
	
	/**
	 * getter method
	 * @return the id
	 */
	
	public String getId() {
		return id;
	}

	/**
	 * setter method
	 * @param id the id to set
	 */
	
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * getter method
	 * @return the text
	 */
	
	public String getText() {
		return text;
	}

	/**
	 * setter method
	 * @param text the text to set
	 */
	
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * getter method
	 * @return the children
	 */
	
	public List<RoadSectionTreeDto> getChildren() {
		return children;
	}

	/**
	 * setter method
	 * @param children the children to set
	 */
	
	public void setChildren(List<RoadSectionTreeDto> children) {
		this.children = children;
	}

	/**
	 * getter method
	 * @return the attribute
	 */
	
	public Map<String, Object> getAttribute() {
		return attribute;
	}

	/**
	 * setter method
	 * @param attribute the attribute to set
	 */
	
	public void setAttribute(Map<String, Object> attribute) {
		this.attribute = attribute;
	}

	/**
	 * getter method
	 * @return the state
	 */
	
	public String getState() {
		return state;
	}

	/**
	 * setter method
	 * @param state the state to set 
	 */
	
	public void setState(String state) {
		this.state = state;
	}
	
	
}
