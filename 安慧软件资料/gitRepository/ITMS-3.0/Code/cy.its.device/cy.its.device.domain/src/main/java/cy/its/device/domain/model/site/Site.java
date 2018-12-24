package cy.its.device.domain.model.site;


import java.math.BigDecimal;

import cy.its.com.domain.AggregateRoot;

public class Site extends AggregateRoot {
	
    private String siteId;

    private String roadId;

    private String crossId;

    private String roadSectionId;

    private String siteCode;

    private String siteName;

    private String siteSimpleName;
    
    private String siteAliasName;

    private String siteType;

    private Integer kilomileage;
    
    private String mileage;

    private String districtCode;

    private String orgId;
    
    private String orgPrivilegeCode;

    private BigDecimal siteLongitude;

    private BigDecimal siteLatitude;

    private String siteLandscape;

    private String remark;
    

	public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getRoadId() {
        return roadId;
    }

    public void setRoadId(String roadId) {
        this.roadId = roadId;
    }

    public String getCrossId() {
        return crossId;
    }

    public void setCrossId(String crossId) {
        this.crossId = crossId;
    }

    public String getRoadSectionId() {
        return roadSectionId;
    }

    public void setRoadSectionId(String roadSectionId) {
        this.roadSectionId = roadSectionId;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteSimpleName() {
        return siteSimpleName;
    }

    public void setSiteSimpleName(String siteSimpleName) {
        this.siteSimpleName = siteSimpleName;
    }

    public String getSiteType() {
        return siteType;
    }

    public void setSiteType(String siteType) {
        this.siteType = siteType;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
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

    public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}

	public BigDecimal getSiteLongitude() {
        return siteLongitude;
    }

    public void setSiteLongitude(BigDecimal siteLongitude) {
        this.siteLongitude = siteLongitude;
    }

    public BigDecimal getSiteLatitude() {
        return siteLatitude;
    }

    public void setSiteLatitude(BigDecimal siteLatitude) {
        this.siteLatitude = siteLatitude;
    }

    public String getSiteLandscape() {
        return siteLandscape;
    }

    public void setSiteLandscape(String siteLandscape) {
        this.siteLandscape = siteLandscape;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	@Override
	public String getIdentityId() {
		return null;
	}

	public String getSiteAliasName() {
		return siteAliasName;
	}

	public void setSiteAliasName(String siteAliasName) {
		this.siteAliasName = siteAliasName;
	}

	public Integer getKilomileage() {
		return kilomileage;
	}

	public void setKilomileage(Integer kilomileage) {
		this.kilomileage = kilomileage;
	}
}