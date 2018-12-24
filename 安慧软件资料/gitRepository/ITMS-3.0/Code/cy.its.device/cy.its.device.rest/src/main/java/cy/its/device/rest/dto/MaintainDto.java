package cy.its.device.rest.dto;

import java.util.List;

import cy.its.com.dto.BaseDto;

public class MaintainDto extends BaseDto {
	/****************** 维护单表 ******************/
	private String maintenanceId;			//维护单ID
	private String maintenanceNbr;			//维护单编号
	private String maintenanceCompany;		//维护厂家ID
	private String maintenanceCompanyName;	//维护厂家名称
	private String contactPerson;			//联系人
	private String phoneNbr;				//联系方式
	private String finishTime;				//要求完成时间
	private String assignBy;				//分派人
	private String assignTime;				//分派时间
	private String maintendanceResult;		//维修结论
	private String solution;				//故障解决方法
	private String solutionTime;			//故障解决时间
	private String remark;					//备注
	private String maintenanceStatus;		//维护单状态
	private String maintenancePhoto;		//图片
	private String orgPrivilegeCode;		//机构权限过滤代码
	private String orgId;					//机构ID
	private String orgName;					//机构名称
	
	private String maintenanceIdStr;		//多个维护单编号字符串
	private String faultIdStr;				//多个故障ID字符串
	
	/**************** 维护单故障关系表 *****************/
	private String faultMaintenanceId;		//故障与维护单关系ID
	private String faultId;					//故障信息ID
	
	private String createTimeFrom;			//查询条件分派时间的开始时间
	private String createTimeTo;			//查询条件分派时间的结束时间
	private List<FaultAlarmDto> deviceFaults; //维护单中的故障
	
	 
	
	
	public String getMaintenanceId() {
		return maintenanceId;
	}
	public void setMaintenanceId(String maintenanceId) {
		this.maintenanceId = maintenanceId;
	}
	public String getMaintenanceNbr() {
		return maintenanceNbr;
	}
	public void setMaintenanceNbr(String maintenanceNbr) {
		this.maintenanceNbr = maintenanceNbr;
	}
	public String getMaintenanceCompany() {
		return maintenanceCompany;
	}
	public void setMaintenanceCompany(String maintenanceCompany) {
		this.maintenanceCompany = maintenanceCompany;
	}
	public String getMaintenanceCompanyName() {
		return maintenanceCompanyName;
	}
	public void setMaintenanceCompanyName(String maintenanceCompanyName) {
		this.maintenanceCompanyName = maintenanceCompanyName;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getPhoneNbr() {
		return phoneNbr;
	}
	public void setPhoneNbr(String phoneNbr) {
		this.phoneNbr = phoneNbr;
	}
	public String getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	public String getAssignBy() {
		return assignBy;
	}
	public void setAssignBy(String assignBy) {
		this.assignBy = assignBy;
	}
	public String getAssignTime() {
		return assignTime;
	}
	public void setAssignTime(String assignTime) {
		this.assignTime = assignTime;
	}
	public String getMaintendanceResult() {
		return maintendanceResult;
	}
	public void setMaintendanceResult(String maintendanceResult) {
		this.maintendanceResult = maintendanceResult;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public String getSolutionTime() {
		return solutionTime;
	}
	public void setSolutionTime(String solutionTime) {
		this.solutionTime = solutionTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMaintenanceStatus() {
		return maintenanceStatus;
	}
	public void setMaintenanceStatus(String maintenanceStatus) {
		this.maintenanceStatus = maintenanceStatus;
	}
	public String getMaintenancePhoto() {
		return maintenancePhoto;
	}
	public void setMaintenancePhoto(String maintenancePhoto) {
		this.maintenancePhoto = maintenancePhoto;
	}
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
	public String getMaintenanceIdStr() {
		return maintenanceIdStr;
	}
	public void setMaintenanceIdStr(String maintenanceIdStr) {
		this.maintenanceIdStr = maintenanceIdStr;
	}
	public String getFaultIdStr() {
		return faultIdStr;
	}
	public void setFaultIdStr(String faultIdStr) {
		this.faultIdStr = faultIdStr;
	}
	public String getFaultMaintenanceId() {
		return faultMaintenanceId;
	}
	public void setFaultMaintenanceId(String faultMaintenanceId) {
		this.faultMaintenanceId = faultMaintenanceId;
	}
	public String getFaultId() {
		return faultId;
	}
	public void setFaultId(String faultId) {
		this.faultId = faultId;
	}
	public String getCreateTimeFrom() {
		return createTimeFrom;
	}
	public void setCreateTimeFrom(String createTimeFrom) {
		this.createTimeFrom = createTimeFrom;
	}
	public String getCreateTimeTo() {
		return createTimeTo;
	}
	public void setCreateTimeTo(String createTimeTo) {
		this.createTimeTo = createTimeTo;
	}
	public List<FaultAlarmDto> getDeviceFaults() {
		return deviceFaults;
	}
	public void setDeviceFaults(List<FaultAlarmDto> deviceFaults) {
		this.deviceFaults = deviceFaults;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	
	
}
