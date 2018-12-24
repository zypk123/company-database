/**
 * @Title: DeviceRunMonitorQryDto.java
 * @Package cy.its.device.rest.dto
 * @Description: TODO(这里要填写用途)
 * @author Administrator Administrator@cychina.cn
 * @date 2016年1月19日 下午5:02:19
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2016 
 */

package cy.its.device.rest.dto;

import cy.its.com.dto.BaseDto;

/**
  * @ClassName: DeviceRunMonitorQryDto
  * @Description: TODO(这里要填写用途)
  * @author Administrator Administrator@cychina.cn
  * @date 2016年1月19日 下午5:02:19
  *
  */

public class DeviceRunMonitorQryDto extends BaseDto {
	
	/**
	 * 点击的节点类型，取值为org roadtype road site
	 */
	private String nodeType;
	private String nodeId;
	private String devType;
	private String devName;
	private String contractor;
	private String ownership;
	/**
	 * getter method
	 * @return the ownership
	 */
	
	public String getOwnership() {
		return ownership;
	}
	/**
	 * setter method
	 * @param ownership the ownership to set
	 */
	
	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}
	private String correctTime;
	/**
	 * getter method
	 * @return the nodeType
	 */
	
	public String getNodeType() {
		return nodeType;
	}
	/**
	 * setter method
	 * @param nodeType the nodeType to set
	 */
	
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	/**
	 * getter method
	 * @return the nodeId
	 */
	
	public String getNodeId() {
		return nodeId;
	}
	/**
	 * setter method
	 * @param nodeId the nodeId to set
	 */
	
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	/**
	 * getter method
	 * @return the devType
	 */
	
	public String getDevType() {
		return devType;
	}
	/**
	 * setter method
	 * @param devType the devType to set
	 */
	
	public void setDevType(String devType) {
		this.devType = devType;
	}
	/**
	 * getter method
	 * @return the devName
	 */
	
	public String getDevName() {
		return devName;
	}
	/**
	 * setter method
	 * @param devName the devName to set
	 */
	
	public void setDevName(String devName) {
		this.devName = devName;
	}
	/**
	 * getter method
	 * @return the contractor
	 */
	
	public String getContractor() {
		return contractor;
	}
	/**
	 * setter method
	 * @param contractor the contractor to set
	 */
	
	public void setContractor(String contractor) {
		this.contractor = contractor;
	}
	/**
	 * getter method
	 * @return the correctTime
	 */
	
	public String getCorrectTime() {
		return correctTime;
	}
	/**
	 * setter method
	 * @param correctTime the correctTime to set
	 */
	
	public void setCorrectTime(String correctTime) {
		this.correctTime = correctTime;
	}
	/**
	 * getter method
	 * @return the netStatus
	 */
	
	public String getNetStatus() {
		return netStatus;
	}
	/**
	 * setter method
	 * @param netStatus the netStatus to set
	 */
	
	public void setNetStatus(String netStatus) {
		this.netStatus = netStatus;
	}
	/**
	 * getter method
	 * @return the elcStatus
	 */
	
	public String getElcStatus() {
		return elcStatus;
	}
	/**
	 * setter method
	 * @param elcStatus the elcStatus to set
	 */
	
	public void setElcStatus(String elcStatus) {
		this.elcStatus = elcStatus;
	}
	/**
	 * getter method
	 * @return the devBug
	 */
	
	public String getDevBug() {
		return devBug;
	}
	/**
	 * setter method
	 * @param devBug the devBug to set
	 */
	
	public void setDevBug(String devBug) {
		this.devBug = devBug;
	}
	/**
	 * getter method
	 * @return the bugType
	 */
	
	public String getBugType() {
		return bugType;
	}
	/**
	 * getter method
	 * @return the matainceStatus
	 */
	
	public String getMatainceStatus() {
		return matainceStatus;
	}
	/**
	 * setter method
	 * @param matainceStatus the matainceStatus to set
	 */
	
	public void setMatainceStatus(String matainceStatus) {
		this.matainceStatus = matainceStatus;
	}
	/**
	 * setter method
	 * @param bugType the bugType to set
	 */
	
	public void setBugType(String bugType) {
		this.bugType = bugType;
	}
	
	private String netStatus;
	private String elcStatus;
	private String devBug;
	private String bugType;
	private String matainceStatus;

}
