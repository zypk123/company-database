/**
 * @Title: CrossDto.java
 * @Package cy.its.road.rest.dto
 * @Description: TODO(这里要填写用途)
 * @author wangyf wangyf@cychina.cn
 * @date 2015年11月24日 下午1:54:57
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.road.rest.dto;

import cy.its.com.dto.BaseDto;

/**
  * @ClassName: CrossDto
  * @Description: TODO(这里要填写用途)
  * @author wangyf wangyf@cychina.cn
  * @date 2015年11月24日 下午1:54:57
  *
  */

public class CrossDto extends BaseDto{
	//路口Id
	private String crossId;
	//路口代码
	private String crossCode;
	//机构Id
	private String orgId;
	//机构名称
	private String orgName;
	//行政区划
	private String districtCode;
	//路口名称
	private String crossName;
	//路口类型
	private String crossType;
	//路口经度
	private String crossLongitude;
	//路口纬度
	private String crossLatitude;
	//路口所属主干路
	private String roadId;
	//路口断面数
	private String sectionNum;
	//权限过滤代码
    private String orgPrivilegeCode;
    
	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}
	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}
	public String getCrossId() {
		return crossId;
	}
	public void setCrossId(String crossId) {
		this.crossId = crossId;
	}
	public String getCrossCode() {
		return crossCode;
	}
	public void setCrossCode(String crossCode) {
		this.crossCode = crossCode;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getCrossName() {
		return crossName;
	}
	public void setCrossName(String crossName) {
		this.crossName = crossName;
	}
	public String getCrossType() {
		return crossType;
	}
	public void setCrossType(String crossType) {
		this.crossType = crossType;
	}
	
	public String getRoadId() {
		return roadId;
	}
	public void setRoadId(String roadId) {
		this.roadId = roadId;
	}
	public String getCrossLongitude() {
		return crossLongitude;
	}
	public void setCrossLongitude(String crossLongitude) {
		this.crossLongitude = crossLongitude;
	}
	
	public String getCrossLatitude() {
		return crossLatitude;
	}
	public void setCrossLatitude(String crossLatitude) {
		this.crossLatitude = crossLatitude;
	}
	
	public String getSectionNum() {
		return sectionNum;
	}
	public void setSectionNum(String sectionNum) {
		this.sectionNum = sectionNum;
	}
	
}
