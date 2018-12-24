package cy.its.syscfg.mybatis.model;

import java.math.BigDecimal;

public class T_Sys_Site {
    private String siteId;

    private String roadId;

    private String siteCode;

    private String siteName;

    private String siteSimpleName;

    private String junctionId;

    private String mileage;

    private String districtCode;

    private BigDecimal siteLongitude;

    private BigDecimal siteLatitude;

    private String siteLandscape;

    private String remark;

    private String orgId;

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

    public String getJunctionId() {
        return junctionId;
    }

    public void setJunctionId(String junctionId) {
        this.junctionId = junctionId;
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

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}