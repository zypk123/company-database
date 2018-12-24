package cy.its.device.rest.dto;

import cy.its.com.dto.BaseDto;

public class DeviceEquipmentDto extends BaseDto{
	private String equipmentId;			    //装备ID
	private String equipmentNbr;			//装备编号
	private String oldEquipmentNbr;			//装备编号
	private String integratePlatformNbr;	//综合平台登记编号
	private String equipmentType;			//装备类型：1 酒检 2 执法记录仪 3 GPS
	private String equipmentName;			//装备名称
	private String orgId;					//机构ID
	private String orgPrivilegeCode;		//机构权限过滤代码
	private String contractId;				//合同ID
	private String contractorId;			//承建商ID
	private String deviceSysModelId;		//系统型号ID
	private String deviceBrand;				//品牌
	private String specification;			//规格
	private String manufactureSn;           //出厂序列号
	private String softwareVersion;			//软件版本
	private String installDate;				//安装日期
	private String useStatusFlag;			//使用状态标识：0 启用 1 停用 2 报废
	private String enableUpdateDate;		//使用状态更新时间
	private String createBy;				//创建人ID
	private String createByName;			//创建人名称
	private String createTime;				//创建时间
	private String updateBy;				//更新人员
	private String updateTime;				//更新时间
	private String remark;					//备注
	
	
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
	
	public String getOldEquipmentNbr() {
		return oldEquipmentNbr;
	}
	public void setOldEquipmentNbr(String oldEquipmentNbr) {
		this.oldEquipmentNbr = oldEquipmentNbr;
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
	public String getInstallDate() {
		return installDate;
	}
	public void setInstallDate(String installDate) {
		this.installDate = installDate;
	}
	public String getUseStatusFlag() {
		return useStatusFlag;
	}
	public void setUseStatusFlag(String useStatusFlag) {
		this.useStatusFlag = useStatusFlag;
	}
	public String getEnableUpdateDate() {
		return enableUpdateDate;
	}
	public void setEnableUpdateDate(String enableUpdateDate) {
		this.enableUpdateDate = enableUpdateDate;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateByName() {
		return createByName;
	}
	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
