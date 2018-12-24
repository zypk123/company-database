/**
 * @Title: SectionDto.java
 * @Package cy.its.road.rest.dto
 * @Description: TODO(这里要填写用途)
 * @author wangyf wangyf@cychina.cn
 * @date 2015年10月29日 下午4:15:59
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
 * @ClassName: SectionDto
 * @Description: TODO(sectionDto对应页面与领域Dto交互数据)
 * @author wangyf wangyf@cychina.cn
 * @date 2015年10月29日 下午4:15:59
 *
 */

public class RoadSectionDto extends BaseDto {
	
	// 路段Id
	private String roadSectionId;
	// 道路Id
	private String roadId;
	// 旧道路Id
	private String oldRoadId;
	//所属道路
	private String roadCode;
	// 路段代码
	private String roadSectionCode;
	// 旧路段代码
	private String oldRoadSectionCode;
	// 行政区划
	private String districtCode;
	// 机构Id
	private String orgId;
	// 路段名称
	private String roadSectionName;
	// 路段类型
	private String roadSectionType;
	// 是否单行通过
	private String isOneDirection;
	// 道路结构
	private String roadStructure;
	// 道路线型
	private String roadLinear;
	// 道路地形
	private String roadLandscape;
	// 中央隔离设施
	private String centralIsolation;
	// 防护设施类型
	private String protectFacilities;
	//道路起点名称
	private String roadBeginName;
	//道路终点名称
	private String roadEndName;
	// 起始里程
	private String beginMeterG;
	//起始米数
	private String beginMeterM;
	//结束里程
	private String endMeterG;
	//结束米数
	private String endMeterM;
	
	private String orgPrivilegeCode;
	
	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}

	public String getRoadBeginName() {
		return roadBeginName;
	}

	public void setRoadBeginName(String roadBeginName) {
		this.roadBeginName = roadBeginName;
	}

	public String getRoadEndName() {
		return roadEndName;
	}

	public void setRoadEndName(String roadEndName) {
		this.roadEndName = roadEndName;
	}

	public String getRoadSectionId() {
		return roadSectionId;
	}

	public void setRoadSectionId(String roadSectionId) {
		this.roadSectionId = roadSectionId;
	}

	public String getRoadId() {
		return roadId;
	}

	public void setRoadId(String roadId) {
		this.roadId = roadId;
	}

	public String getOldRoadId() {
		return oldRoadId;
	}

	public void setOldRoadId(String oldRoadId) {
		this.oldRoadId = oldRoadId;
	}

	public String getRoadSectionCode() {
		return roadSectionCode;
	}

	public void setRoadSectionCode(String roadSectionCode) {
		this.roadSectionCode = roadSectionCode;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	

	public String getRoadCode() {
		return roadCode;
	}

	public void setRoadCode(String roadCode) {
		this.roadCode = roadCode;
	}

	public String getOldRoadSectionCode() {
		return oldRoadSectionCode;
	}

	public void setOldRoadSectionCode(String oldRoadSectionCode) {
		this.oldRoadSectionCode = oldRoadSectionCode;
	}

	public String getRoadSectionName() {
		return roadSectionName;
	}

	public void setRoadSectionName(String roadSectionName) {
		this.roadSectionName = roadSectionName;
	}

	public String getRoadSectionType() {
		return roadSectionType;
	}

	public void setRoadSectionType(String roadSectionType) {
		this.roadSectionType = roadSectionType;
	}

	public String getIsOneDirection() {
		return isOneDirection;
	}

	public void setIsOneDirection(String isOneDirection) {
		this.isOneDirection = isOneDirection;
	}

	public String getRoadStructure() {
		return roadStructure;
	}

	public void setRoadStructure(String roadStructure) {
		this.roadStructure = roadStructure;
	}

	public String getRoadLinear() {
		return roadLinear;
	}

	public void setRoadLinear(String roadLinear) {
		this.roadLinear = roadLinear;
	}

	public String getRoadLandscape() {
		return roadLandscape;
	}

	public void setRoadLandscape(String roadLandscape) {
		this.roadLandscape = roadLandscape;
	}

	public String getCentralIsolation() {
		return centralIsolation;
	}

	public void setCentralIsolation(String centralIsolation) {
		this.centralIsolation = centralIsolation;
	}

	public String getProtectFacilities() {
		return protectFacilities;
	}

	public void setProtectFacilities(String protectFacilities) {
		this.protectFacilities = protectFacilities;
	}

	
	public String getBeginMeterM() {
		return beginMeterM;
	}

	public void setBeginMeterM(String beginMeterM) {
		this.beginMeterM = beginMeterM;
	}

	
	public String getBeginMeterG() {
		return beginMeterG;
	}

	public void setBeginMeterG(String beginMeterG) {
		this.beginMeterG = beginMeterG;
	}

	public String getEndMeterG() {
		return endMeterG;
	}

	public void setEndMeterG(String endMeterG) {
		this.endMeterG = endMeterG;
	}

	public String getEndMeterM() {
		return endMeterM;
	}

	public void setEndMeterM(String endMeterM) {
		this.endMeterM = endMeterM;
	}

	

}
