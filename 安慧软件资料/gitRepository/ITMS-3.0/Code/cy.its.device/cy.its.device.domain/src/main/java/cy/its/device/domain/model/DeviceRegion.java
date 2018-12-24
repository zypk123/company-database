package cy.its.device.domain.model;

import java.util.Date;

public class DeviceRegion {
	
    private String regionalId;

    private String regionalCode;

    private String regionalName;

    private String districtCode;

    private String orgId;

    private String roadId;

    private String entrySiteId;

    private String exitSiteId;

    private Double distance;

    private String directionType;

    private String directionName;

    private Short limitLarge;

    private Short limitSmall;

//    private Long limitOthers;

    private Short limitLower;

    private Integer limitLargeMargin;

    private Integer limitSmallMargin;

    private String relatedVariableSpeed;

    private String enableFlag;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private String remark;

    private String relatedLedId;

    private String integratePlatformNbr;

    private String verificationMark;
    
    private Date enableUpdateDate;
    
    private Date expireDate;  			//证书最迟有效期止 

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

    public Integer getLimitLargeMargin() {
        return limitLargeMargin;
    }

    public void setLimitLargeMargin(Integer limitLargeMargin) {
        this.limitLargeMargin = limitLargeMargin;
    }

    public Integer getLimitSmallMargin() {
        return limitSmallMargin;
    }

    public void setLimitSmallMargin(Integer limitSmallMargin) {
        this.limitSmallMargin = limitSmallMargin;
    }

    public String getRelatedVariableSpeed() {
        return relatedVariableSpeed;
    }

    public void setRelatedVariableSpeed(String relatedVariableSpeed) {
        this.relatedVariableSpeed = relatedVariableSpeed;
    }

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

    public String getRelatedLedId() {
        return relatedLedId;
    }

    public void setRelatedLedId(String relatedLedId) {
        this.relatedLedId = relatedLedId;
    }

    public String getIntegratePlatformNbr() {
        return integratePlatformNbr;
    }

    public void setIntegratePlatformNbr(String integratePlatformNbr) {
        this.integratePlatformNbr = integratePlatformNbr;
    }
    
    
    public void setEnableFlag(DeviceRegionEnableFlag flag) throws Exception{
    	flag.setEnableFlag(this);
    }
	
	public String getVerificationMark() {
        return verificationMark;
    }

    public void setVerificationMark(String verificationMark) {
        this.verificationMark = verificationMark;
    }

	public Date getEnableUpdateDate() {
		return enableUpdateDate;
	}

	public void setEnableUpdateDate(Date enableUpdateDate) {
		this.enableUpdateDate = enableUpdateDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
}