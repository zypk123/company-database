package cy.its.device.domain.model;

import java.util.Date;

public class Equipment {
    private String equipmentId;

    private String equipmentNbr;

    private String integratePlatformNbr;

    private String equipmentType;

    private String equipmentName;

    private String orgId;

    private String orgPrivilegeCode;

    private String contractId;

    private String contractorId;

    private String deviceSysModelId;

    private String deviceBrand;

    private String specification;

    private String manufactureSn;

    private String softwareVersion;

    private Date installDate;

    private String useStatusFlag;

    private Date enableUpdateDate;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;
    //检定标识
    private String verificationMark;
    
    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentNbr() {
        return equipmentNbr;
    }

    public void setEquipmentNbr(String equipmentNbr) {
        this.equipmentNbr = equipmentNbr;
    }

    public String getIntegratePlatformNbr() {
        return integratePlatformNbr;
    }

    public void setIntegratePlatformNbr(String integratePlatformNbr) {
        this.integratePlatformNbr = integratePlatformNbr;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
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

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractorId() {
        return contractorId;
    }

    public void setContractorId(String contractorId) {
        this.contractorId = contractorId;
    }

    public String getDeviceSysModelId() {
        return deviceSysModelId;
    }

    public void setDeviceSysModelId(String deviceSysModelId) {
        this.deviceSysModelId = deviceSysModelId;
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getManufactureSn() {
        return manufactureSn;
    }

    public void setManufactureSn(String manufactureSn) {
        this.manufactureSn = manufactureSn;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public Date getInstallDate() {
        return installDate;
    }

    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }

    public String getUseStatusFlag() {
        return useStatusFlag;
    }

    public void setUseStatusFlag(String useStatusFlag) {
        this.useStatusFlag = useStatusFlag;
    }

    public Date getEnableUpdateDate() {
        return enableUpdateDate;
    }

    public void setEnableUpdateDate(Date enableUpdateDate) {
        this.enableUpdateDate = enableUpdateDate;
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

	public String getVerificationMark() {
		return verificationMark;
	}

	public void setVerificationMark(String verificationMark) {
		this.verificationMark = verificationMark;
	}
    
}