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

import cy.its.road.domain.model.road.Cross;
import cy.its.syscfg.domain.model.duty.Organization;

/**
  * @ClassName: CrossTreeDto
  * @Description: 封装路口树的dto
  * @author zuop zuop@cychina.cn
  * @date 2015年11月16日 上午10:19:48
  *
  */
public class CrossTreeDto {
	private String id;
	
	private String text;
	
	private String state;
	
	private List<CrossTreeDto> children = new ArrayList<CrossTreeDto>();
	
	private Map<String,Object> attribute = new HashMap<String,Object>();

	/**
	 * 
	
	  * 创建一个新的实例 CrossTreeDto.  空构造方法 
	 */
	public CrossTreeDto(){
		
	}
	
	/**
	  * 创建一个新的实例 CrossTreeDto. 使用机构领域对象创建树节点
	 */
	public CrossTreeDto(Organization org){
		this.id = org.getIdentityId();
		this.text = org.orgName;
		this.state = "closed";
		this.attribute.put("code", org.orgCode);
		this.attribute.put("orgPrivCode", org.orgPrivilegeCode);
		this.attribute.put("nodeType", "org");
	}
	
	/**
	  * 创建一个新的实例 CrossTreeDto. 使用路口领域对象创建节点
	 */
	public CrossTreeDto(Cross cross){
		this.id = cross.getIdentityId();
		this.text = cross.getCrossName();
		this.attribute.put("code", cross.getCrossCode());
		this.attribute.put("orgId", cross.getOrgId());
		//this.attribute.put("roadId", cross.getCrossRoadId());
		this.attribute.put("orgPrivCode", cross.getOrgPrivilegeCode());
		this.attribute.put("nodeType", "cross");
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
	
	public List<CrossTreeDto> getChildren() {
		return children;
	}

	/**
	 * setter method
	 * @param children the children to set
	 */
	
	public void setChildren(List<CrossTreeDto> children) {
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
