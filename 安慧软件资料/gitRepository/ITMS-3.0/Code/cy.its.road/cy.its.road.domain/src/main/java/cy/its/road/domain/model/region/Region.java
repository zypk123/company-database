package cy.its.road.domain.model.region;

import java.util.Date;

public class Region {
    private String regionalId;

    private String regionalCode;

    private String regionalName;

    private String districtCode;

    private String orgId;

    private String roadId;

    private String entrySiteId;

    private String exitSiteId;
    //区间距离
    private Double distance;

    private String travelTimeRange;

    private String directionType;

    private String directionName;

    private String laneNum;

//    private String hasSupportSpeed;

//    private String hasSupportVehicleFlow;

    private String historyMaxVehicleNum;

    private Date historyMaxFlowTime;

    private String regionalDesignCapacity;

    private Short limitLarge;

    private Short limitSmall;

//    private Long limitOthers;

    private Short limitLower;

//    private Integer limitLargeMargin;

//    private Integer limitSmallMargin;

//    private String relatedVariableSpeed;

    private String enableFlag;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private String remark;
    //权限过滤代码
    private String orgPrivilegeCode;
    
    public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
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

    public String getRoadId() {
        return roadId;
    }

    public void setRoadId(String roadId) {
        this.roadId = roadId;
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

 
    public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public String getTravelTimeRange() {
        return travelTimeRange;
    }

    public void setTravelTimeRange(String travelTimeRange) {
        this.travelTimeRange = travelTimeRange;
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

//    public String getHasSupportSpeed() {
//        return hasSupportSpeed;
//    }
//
//    public void setHasSupportSpeed(String hasSupportSpeed) {
//        this.hasSupportSpeed = hasSupportSpeed;
//    }
//
//    public String getHasSupportVehicleFlow() {
//        return hasSupportVehicleFlow;
//    }
//
//    public void setHasSupportVehicleFlow(String hasSupportVehicleFlow) {
//        this.hasSupportVehicleFlow = hasSupportVehicleFlow;
//    }

    public String getHistoryMaxVehicleNum() {
        return historyMaxVehicleNum;
    }

    public void setHistoryMaxVehicleNum(String historyMaxVehicleNum) {
        this.historyMaxVehicleNum = historyMaxVehicleNum;
    }

    public Date getHistoryMaxFlowTime() {
        return historyMaxFlowTime;
    }

    public void setHistoryMaxFlowTime(Date historyMaxFlowTime) {
        this.historyMaxFlowTime = historyMaxFlowTime;
    }

    public String getRegionalDesignCapacity() {
        return regionalDesignCapacity;
    }

    public void setRegionalDesignCapacity(String regionalDesignCapacity) {
        this.regionalDesignCapacity = regionalDesignCapacity;
    }

    public Short getLimitLarge() {
        return limitLarge;
    }

    public void setLimitLarge(Short limitLarge) {
        this.limitLarge = limitLarge;
    }

    public Short getLimitSmall() {
        return limitSmall;
    }

    public void setLimitSmall(Short limitSmall) {
        this.limitSmall = limitSmall;
    }

//    public Long getLimitOthers() {
//        return limitOthers;
//    }
//
//    public void setLimitOthers(Long limitOthers) {
//        this.limitOthers = limitOthers;
//    }

    public Short getLimitLower() {
        return limitLower;
    }

    public void setLimitLower(Short limitLower) {
        this.limitLower = limitLower;
    }

//    public Integer getLimitLargeMargin() {
//        return limitLargeMargin;
//    }
//
//    public void setLimitLargeMargin(Integer limitLargeMargin) {
//        this.limitLargeMargin = limitLargeMargin;
//    }
//
//    public Integer getLimitSmallMargin() {
//        return limitSmallMargin;
//    }
//
//    public void setLimitSmallMargin(Integer limitSmallMargin) {
//        this.limitSmallMargin = limitSmallMargin;
//    }
//
//    public String getRelatedVariableSpeed() {
//        return relatedVariableSpeed;
//    }
//
//    public void setRelatedVariableSpeed(String relatedVariableSpeed) {
//        this.relatedVariableSpeed = relatedVariableSpeed;
//    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}