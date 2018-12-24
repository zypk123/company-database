package cy.its.device.rest.dto;

import cy.its.com.dto.BaseDto;

public class FaultAlarmDto extends BaseDto{
	
	private String faultId;			//故障信息ID
	private String maintenanceId;	//维护单ID
	private String deviceId;		//电子监控系统ID
	private String faultAlarm;		//故障或报警。1-故障；2-报警
	private String faultType;		//故障或报警类型。
	private String faultSubType;	//故障子类型
	private String startTime;		//发生时间
	private String endTime;			//结束时间
	private String collectMethod;	//采集方式。1-前端设备上传；2-系统分析；3-人工添加
	private String remark;			//描述
	private String isValidity;		//故障或报警有效性。1-有效；2-无效
	private String processState;	//处理状态。0-未处理；1-无需处理；2-未解决；3-已解决
	private String createBy;		//创建人
	private String createTime;		//创建时间
	private String processTime;		//故障处理时间
	private String sysComponentId;	//组间ID
	private String orgPrivilegeCode;//机构权限过滤代码
	
	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}
	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}
	private String startTimeFrom;		//查询条件发生时间的开始时间
	private String startTimeTo;			//查询条件发生时间的结束时间
	
	/************** 设备相关信息字段 ***************/
	private String deviceSysNbr;	//设备编号
	private String deviceName;		//设备名称
	private String siteId;			//点位ID
    private String siteName;		//点位名称
	private String orgId;			//所属机构
	private	String deviceType;		//设备类型
	private String statusType;		//设备状态
	private String contractorId;	//设备厂商ID
	private String contractorName;	//设备厂商名称
	private String roadId;			//道路ID
	
	public String getFaultId() {
		return faultId;
	}
	public void setFaultId(String faultId) {
		this.faultId = faultId;
	}
	public String getMaintenanceId() {
		return maintenanceId;
	}
	public void setMaintenanceId(String maintenanceId) {
		this.maintenanceId = maintenanceId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getFaultAlarm() {
		return faultAlarm;
	}
	public void setFaultAlarm(String faultAlarm) {
		this.faultAlarm = faultAlarm;
	}
	public String getFaultType() {
		return faultType;
	}
	public void setFaultType(String faultType) {
		this.faultType = faultType;
	}
	public String getFaultSubType() {
		return faultSubType;
	}
	public void setFaultSubType(String faultSubType) {
		this.faultSubType = faultSubType;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCollectMethod() {
		return collectMethod;
	}
	public void setCollectMethod(String collectMethod) {
		this.collectMethod = collectMethod;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsValidity() {
		return isValidity;
	}
	public void setIsValidity(String isValidity) {
		this.isValidity = isValidity;
	}
	public String getProcessState() {
		return processState;
	}
	public void setProcessState(String processState) {
		this.processState = processState;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getProcessTime() {
		return processTime;
	}
	public void setProcessTime(String processTime) {
		this.processTime = processTime;
	}
	public String getSysComponentId() {
		return sysComponentId;
	}
	public void setSysComponentId(String sysComponentId) {
		this.sysComponentId = sysComponentId;
	}
	public String getStartTimeFrom() {
		return startTimeFrom;
	}
	public void setStartTimeFrom(String startTimeFrom) {
		this.startTimeFrom = startTimeFrom;
	}
	public String getStartTimeTo() {
		return startTimeTo;
	}
	public void setStartTimeTo(String startTimeTo) {
		this.startTimeTo = startTimeTo;
	}
	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}
	public void setDeviceSysNbr(String deviceSysNbr) {
		this.deviceSysNbr = deviceSysNbr;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getStatusType() {
		return statusType;
	}
	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}
	public String getContractorId() {
		return contractorId;
	}
	public void setContractorId(String contractorId) {
		this.contractorId = contractorId;
	}
	public String getContractorName() {
		return contractorName;
	}
	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}
	public String getRoadId() {
		return roadId;
	}
	public void setRoadId(String roadId) {
		this.roadId = roadId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	
}
