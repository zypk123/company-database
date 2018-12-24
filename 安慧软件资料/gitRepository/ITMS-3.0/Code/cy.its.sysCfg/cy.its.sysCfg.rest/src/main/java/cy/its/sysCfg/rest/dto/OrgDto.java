package cy.its.sysCfg.rest.dto;

public class OrgDto {
	//机构代码
	private String orgCode;
	//旧机构代码
	private String oldOrgCode;
	// 机构唯一标识
	private String orgId;
	//用户Id
	private String userId;
	//机构权限代码
	private String orgPrivilegeCode;
	//机构名称
	private String orgName;
	//机构级别
	private String orgLevel;
	//父机构
	private String parentOrgId;
	//父机构代码
	private String parentOrgCode;
	
	//业务指导机构
	private String parentInstructOrgId;
	//机构类型
	private String orgType;
	//机构负责人
	private String orgHeaderName;
	//负责人电话
	private String orgHeaderPhone;
	//机构联系人电话
	private String orgPhoneNbr;
	//辖区范围
	private String sponsorDistributes;
	//启用状态
	private String enableFlag;
	//是否是独立机构
	private String 	isDepartment;
	//是否是高速管理部门
	private String isHighwayOrg;
	//备注
	private String remark;
	//驻地所在城市
	private String city;
	//驻地行政区划
	private String districtCode;
	//驻地地址
	private String addressDesc;
	//经度
	private String orgLongitude;
	//纬度
	private String orgLatitude;
	// 机构信息页数
	private int pageNumber;
	// 机构信息每页数
	private int pageSize;
	
	
	public String getParentOrgCode() {
		return parentOrgCode;
	}
	public void setParentOrgCode(String parentOrgCode) {
		this.parentOrgCode = parentOrgCode;
	}
	
	
	public String getOldOrgCode() {
		return oldOrgCode;
	}
	public void setOldOrgCode(String oldOrgCode) {
		this.oldOrgCode = oldOrgCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}
	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	
	public String getOrgLevel() {
		return orgLevel;
	}
	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
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
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
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
	public String getOrgPhoneNbr() {
		return orgPhoneNbr;
	}
	public void setOrgPhoneNbr(String orgPhoneNbr) {
		this.orgPhoneNbr = orgPhoneNbr;
	}
	public String getSponsorDistributes() {
		return sponsorDistributes;
	}
	public void setSponsorDistributes(String sponsorDistributes) {
		this.sponsorDistributes = sponsorDistributes;
	}
	public String getEnableFlag() {
		return enableFlag;
	}
	public void setEnableFlag(String enableFlag) {
		this.enableFlag = enableFlag;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getAddressDesc() {
		return addressDesc;
	}
	public void setAddressDesc(String addressDesc) {
		this.addressDesc = addressDesc;
	}
	public String getOrgLongitude() {
		return orgLongitude;
	}
	public void setOrgLongitude(String orgLongitude) {
		this.orgLongitude = orgLongitude;
	}
	public String getOrgLatitude() {
		return orgLatitude;
	}
	public void setOrgLatitude(String orgLatitude) {
		this.orgLatitude = orgLatitude;
	}

	
	
	
}