/**
 * @Title: ReglonalDto.java
 * @Package cy.its.road.rest.dto
 * @Description: TODO(这里要填写用途)
 * @author wangyf wangyf@cychina.cn
 * @date 2015年11月21日 上午10:29:51
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
  * @ClassName: ReglonalDto
  * @Description: TODO(这里要填写用途)
  * @author wangyf wangyf@cychina.cn
  * @date 2015年11月21日 上午10:29:51
  *
  */

public class RegionalDto extends BaseDto{
	//区间Id
	private String regionalId;
	//区间编号
	private String regionalCode;
	//区间名称
	private String regionalName;
	//行政区划
	private String districtCode;
	//机构Id
	private String orgId;
	//机构名称
	private String orgName;
	//道路Id
	private String roadId;
	//道路名称
	private String roadName;
	//起点Id
	private String entrySiteId;
	//点位起点名称 
	private String entrySiteName;
	//终点Id
	private String exitSiteId;
	//点位 终点名称 
	private String exitSiteName;
	//方向类型
	private String directionType;
	//方向名称
	private String directionName;
	//车道数
	private String laneNum;
	//是否支持区间测速
	//private String hasSupportSpeed;
	//是否支持区间流量监控
	//private String hasSupportVehicleFlow;
	//区间历史高峰车辆数
	private String hisToryMaxVehicleNum;
	//高峰时间
	private String hisToryMaxFlowTime;
	//是否支持测速区间
	private String hasSupportSpeed;
	//区间设计容量
	private String  regionalDesignCapacity;
	//大车限高速
	private String limitLarge;
	//小车限高速
	private String limitSmall;
	//其它车限高速
	//private String limitOthers;
	//限低速
	private String limitLower;
	//大车限速容许值 
	//小车限速容许值
	//启用标识
	private String enableFlag;
	//区间距离
    private  String distance;
    //权限过滤代码
    private String orgPrivilegeCode;
	
	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}
	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getRegionalId() {
		return regionalId;
	}
	public void setRegionalId(String regionalId) {
		this.regionalId = regionalId;
	}
	public String getRegionalCode() {
		return regionalCode;
	}
	public void setRegionalCode(String regionalCode) {
		this.regionalCode = regionalCode;
	}
	public String getRegionalName() {
		return regionalName;
	}
	public void setRegionalName(String regionalName) {
		this.regionalName = regionalName;
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
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getRoadId() {
		return roadId;
	}
	public void setRoadId(String roadId) {
		this.roadId = roadId;
	}
	
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
		
	public String getEntrySiteId() {
		return entrySiteId;
	}
	public void setEntrySiteId(String entrySiteId) {
		this.entrySiteId = entrySiteId;
	}
	public String getExitSiteId() {
		return exitSiteId;
	}
	public void setExitSiteId(String exitSiteId) {
		this.exitSiteId = exitSiteId;
	}
	public String getEntrySiteName() {
		return entrySiteName;
	}
	public void setEntrySiteName(String entrySiteName) {
		this.entrySiteName = entrySiteName;
	}
	public String getExitSiteName() {
		return exitSiteName;
	}
	public void setExitSiteName(String exitSiteName) {
		this.exitSiteName = exitSiteName;
	}
	public String getDirectionType() {
		return directionType;
	}
	public void setDirectionType(String directionType) {
		this.directionType = directionType;
	}
	public String getDirectionName() {
		return directionName;
	}
	public void setDirectionName(String directionName) {
		this.directionName = directionName;
	}
	public String getLaneNum() {
		return laneNum;
	}
	public void setLaneNum(String laneNum) {
		this.laneNum = laneNum;
	}
//	public String getHasSupportSpeed() {
//		return hasSupportSpeed;
//	}
//	public void setHasSupportSpeed(String hasSupportSpeed) {
//		this.hasSupportSpeed = hasSupportSpeed;
//	}
//	public String getHasSupportVehicleFlow() {
//		return hasSupportVehicleFlow;
//	}
//	public void setHasSupportVehicleFlow(String hasSupportVehicleFlow) {
//		this.hasSupportVehicleFlow = hasSupportVehicleFlow;
//	}
	public String getHisToryMaxVehicleNum() {
		return hisToryMaxVehicleNum;
	}
	public void setHisToryMaxVehicleNum(String hisToryMaxVehicleNum) {
		this.hisToryMaxVehicleNum = hisToryMaxVehicleNum;
	}
	public String getHisToryMaxFlowTime() {
		return hisToryMaxFlowTime;
	}
	public void setHisToryMaxFlowTime(String hisToryMaxFlowTime) {
		this.hisToryMaxFlowTime = hisToryMaxFlowTime;
	}
	public String getRegionalDesignCapacity() {
		return regionalDesignCapacity;
	}
	public void setRegionalDesignCapacity(String regionalDesignCapacity) {
		this.regionalDesignCapacity = regionalDesignCapacity;
	}
	public String getLimitLarge() {
		return limitLarge;
	}
	public void setLimitLarge(String limitLarge) {
		this.limitLarge = limitLarge;
	}
	public String getLimitSmall() {
		return limitSmall;
	}
	public void setLimitSmall(String limitSmall) {
		this.limitSmall = limitSmall;
	}
//	public String getLimitOthers() {
//		return limitOthers;
//	}
//	public void setLimitOthers(String limitOthers) {
//		this.limitOthers = limitOthers;
//	}
	public String getLimitLower() {
		return limitLower;
	}
	public void setLimitLower(String limitLower) {
		this.limitLower = limitLower;
	}
	public String getEnableFlag() {
		return enableFlag;
	}
	public void setEnableFlag(String enableFlag) {
		this.enableFlag = enableFlag;
	}
	public String getHasSupportSpeed() {
		return hasSupportSpeed;
	}
	public void setHasSupportSpeed(String hasSupportSpeed) {
		this.hasSupportSpeed = hasSupportSpeed;
	}
		
}
