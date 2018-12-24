package sync.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_sys_org")
public class Org {
	
    private String orgId;

    private String orgCode;

    private String orgName;

    private String parentOrgId;

    private String parentInstructOrgId;

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
    
    
    @Column(name="org_privilege_code")
	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}

	@Id
	@Column(name="org_id")
	public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Column(name="org_code")
    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    @Column(name="org_name")
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Column(name="parent_org_id")
    public String getParentOrgId() {
        return parentOrgId;
    }

    public void setParentOrgId(String parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    @Column(name="parent_instruct_org_id")
    public String getParentInstructOrgId() {
        return parentInstructOrgId;
    }

    public void setParentInstructOrgId(String parentInstructOrgId) {
        this.parentInstructOrgId = parentInstructOrgId;
    }

    @Column(name="org_type")
    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    @Column(name="is_department")
    public String getIsDepartment() {
        return isDepartment;
    }

    public void setIsDepartment(String isDepartment) {
        this.isDepartment = isDepartment;
    }

    @Column(name="is_highway_org")
    public String getIsHighwayOrg() {
        return isHighwayOrg;
    }

    public void setIsHighwayOrg(String isHighwayOrg) {
        this.isHighwayOrg = isHighwayOrg;
    }

    @Column(name="org_level")
    public String getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(String orgLevel) {
        this.orgLevel = orgLevel;
    }

    @Column(name="org_phone_nbr")
    public String getOrgPhoneNbr() {
        return orgPhoneNbr;
    }

    public void setOrgPhoneNbr(String orgPhoneNbr) {
        this.orgPhoneNbr = orgPhoneNbr;
    }

    @Column(name="org_header_name")
    public String getOrgHeaderName() {
        return orgHeaderName;
    }

    public void setOrgHeaderName(String orgHeaderName) {
        this.orgHeaderName = orgHeaderName;
    }

    @Column(name="org_header_phone")
    public String getOrgHeaderPhone() {
        return orgHeaderPhone;
    }

    public void setOrgHeaderPhone(String orgHeaderPhone) {
        this.orgHeaderPhone = orgHeaderPhone;
    }

    @Column(name="track_flag")
    public String getTrackFlag() {
        return trackFlag;
    }

    public void setTrackFlag(String trackFlag) {
        this.trackFlag = trackFlag;
    }

    @Column(name="enforcement_flag")
    public String getEnforcementFlag() {
        return enforcementFlag;
    }

    public void setEnforcementFlag(String enforcementFlag) {
        this.enforcementFlag = enforcementFlag;
    }

    @Column(name="sponsor_distributes")
    public String getSponsorDistributes() {
        return sponsorDistributes;
    }

    public void setSponsorDistributes(String sponsorDistributes) {
        this.sponsorDistributes = sponsorDistributes;
    }

    @Column(name="address_desc")
    public String getAddressDesc() {
        return addressDesc;
    }

    public void setAddressDesc(String addressDesc) {
        this.addressDesc = addressDesc;
    }

    @Column(name="district_code")
    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    @Column(name="city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name="org_longitude")
    public BigDecimal getOrgLongitude() {
        return orgLongitude;
    }

    public void setOrgLongitude(BigDecimal orgLongitude) {
        this.orgLongitude = orgLongitude;
    }
    
    @Column(name="org_latitude")
    public BigDecimal getOrgLatitude() {
        return orgLatitude;
    }

    public void setOrgLatitude(BigDecimal orgLatitude) {
        this.orgLatitude = orgLatitude;
    }

    @Column(name="create_by")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Column(name="create_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name="update_by")
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Column(name="update_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name="remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}