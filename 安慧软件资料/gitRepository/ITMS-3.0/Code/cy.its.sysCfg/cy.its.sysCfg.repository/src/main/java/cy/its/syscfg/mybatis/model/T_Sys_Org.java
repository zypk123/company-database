package cy.its.syscfg.mybatis.model;

import java.math.BigDecimal;
import java.util.Date;

public class T_Sys_Org {
	
    private String orgId;

    private String orgCode;

    private String orgName;

    private String parentOrgId;

    private String parentInstructOrgId;

//    private String orgClassification;

    private String orgType;

    private String isDepartment;

    private String isHighwayOrg;

    private String orgLevel;

    private String orgPhoneNbr;

    private String orgHeaderName;

    private String orgHeaderPhone;

    private String trackFlag;

    private String enforcementFlag;

    private String sponsorDistributes;

    private String addressDesc;

    private String districtCode;

    private String city;

    private BigDecimal orgLongitude;

    private BigDecimal orgLatitude;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;
    
    private String orgPrivilegeCode;
    
	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}

	public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getParentOrgId() {
        return parentOrgId;
    }

    public void setParentOrgId(String parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    public String getParentInstructOrgId() {
        return parentInstructOrgId;
    }

    public void setParentInstructOrgId(String parentInstructOrgId) {
        this.parentInstructOrgId = parentInstructOrgId;
    }

//    public String getOrgClassification() {
//        return orgClassification;
//    }
//
//    public void setOrgClassification(String orgClassification) {
//        this.orgClassification = orgClassification;
//    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getIsDepartment() {
        return isDepartment;
    }

    public void setIsDepartment(String isDepartment) {
        this.isDepartment = isDepartment;
    }

    public String getIsHighwayOrg() {
        return isHighwayOrg;
    }

    public void setIsHighwayOrg(String isHighwayOrg) {
        this.isHighwayOrg = isHighwayOrg;
    }

    public String getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(String orgLevel) {
        this.orgLevel = orgLevel;
    }

    public String getOrgPhoneNbr() {
        return orgPhoneNbr;
    }

    public void setOrgPhoneNbr(String orgPhoneNbr) {
        this.orgPhoneNbr = orgPhoneNbr;
    }

    public String getOrgHeaderName() {
        return orgHeaderName;
    }

    public void setOrgHeaderName(String orgHeaderName) {
        this.orgHeaderName = orgHeaderName;
    }

    public String getOrgHeaderPhone() {
        return orgHeaderPhone;
    }

    public void setOrgHeaderPhone(String orgHeaderPhone) {
        this.orgHeaderPhone = orgHeaderPhone;
    }

    public String getTrackFlag() {
        return trackFlag;
    }

    public void setTrackFlag(String trackFlag) {
        this.trackFlag = trackFlag;
    }

    public String getEnforcementFlag() {
        return enforcementFlag;
    }

    public void setEnforcementFlag(String enforcementFlag) {
        this.enforcementFlag = enforcementFlag;
    }

    public String getSponsorDistributes() {
        return sponsorDistributes;
    }

    public void setSponsorDistributes(String sponsorDistributes) {
        this.sponsorDistributes = sponsorDistributes;
    }

    public String getAddressDesc() {
        return addressDesc;
    }

    public void setAddressDesc(String addressDesc) {
        this.addressDesc = addressDesc;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BigDecimal getOrgLongitude() {
        return orgLongitude;
    }

    public void setOrgLongitude(BigDecimal orgLongitude) {
        this.orgLongitude = orgLongitude;
    }

    public BigDecimal getOrgLatitude() {
        return orgLatitude;
    }

    public void setOrgLatitude(BigDecimal orgLatitude) {
        this.orgLatitude = orgLatitude;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}