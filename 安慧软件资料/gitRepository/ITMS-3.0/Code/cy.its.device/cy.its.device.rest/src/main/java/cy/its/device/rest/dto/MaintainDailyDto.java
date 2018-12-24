package cy.its.device.rest.dto;

import cy.its.com.dto.BaseDto;

public class MaintainDailyDto extends BaseDto{
	private String dailyMaintenanceId;				//维护记录ID
	private String deviceId;						//设备ID
	private String maintenanceLevel;				//维护级别
	private String deviceAppearance;				//设备外观
	private String deviceBar;						//设备杆件
	private String protectionComponents;			//防护部件
	private String power;							//设备电源
	private String cable;							//设备电缆
	private String lightingProtection;				//避雷设施
	private String groundConnection;				//设备接地
	private String communicationDevice;				//通信设备
	private String deviceTime;						//校准设备时钟
	private String cleanProtectiveCover;			//清理防护罩
	private String cleanCameraAsh;					//清理镜头积灰
	private String installation;					//安装情况
	private String trafficSign;						//标志标线
	private String vehiclePassInfo;					//车辆通行信息
	private String vehicleViolation;				//车辆违法记录信息
	private String meteorologyData;					//气象数据
	private String recentlyUploadTime;				//最近上传时间/
	private String videoQuality;					//视频质量
	private String ptzControl;						//视频云台控制
	private String selfCheck;						//LED自检情况
	private String outControlPoint;					//LED屏失控点
	private String trafficLight;					//信号灯工作情况
	private String signalDevice;					//信号机工作情况
	private String maintenanceDate;					//维护时间/
	private String maintenanceCycle;				//维护周期
	private String maintenanceAdvice;				//维护意见
	private String nextMaintenanceDate;				//下次维护时间/
	private String maintainPerson;					//维护人
	private String phone;							//联系方式
	private String createBy;						//创建人
	private String createTime;						//创建时间/
	private String remark;							//备注
	private String trafficDataFlow;					//交通流数据
	
	/*************查询条件字段***************/
	private String orgId;							//机构ID
	private String deviceType;						//设备类型		
	private String roadId;							//道路ID
	private String siteId;							//点位ID
	private String contractorId;					//厂商ID
	private String maintenanceDateFrom;				//维护时间开始时间
	private String maintenanceDateTo;				//维护时间结束时间
	private String orgPrivilegeCode;				//机构权限过滤代码
	
	/**************查询列表字段*****************/
	private String deviceSysNbr;					//设备编号
	private String deviceName;						//设备名称
	private String roadName;						//道路名称
	private String siteName;						//点位名称
	
	
	
	public String getDailyMaintenanceId() {
		return dailyMaintenanceId;
	}
	public void setDailyMaintenanceId(String dailyMaintenanceId) {
		this.dailyMaintenanceId = dailyMaintenanceId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getMaintenanceLevel() {
		return maintenanceLevel;
	}
	public void setMaintenanceLevel(String maintenanceLevel) {
		this.maintenanceLevel = maintenanceLevel;
	}
	public String getDeviceAppearance() {
		return deviceAppearance;
	}
	public void setDeviceAppearance(String deviceAppearance) {
		this.deviceAppearance = deviceAppearance;
	}
	public String getDeviceBar() {
		return deviceBar;
	}
	public void setDeviceBar(String deviceBar) {
		this.deviceBar = deviceBar;
	}
	public String getProtectionComponents() {
		return protectionComponents;
	}
	public void setProtectionComponents(String protectionComponents) {
		this.protectionComponents = protectionComponents;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public String getCable() {
		return cable;
	}
	public void setCable(String cable) {
		this.cable = cable;
	}
	public String getLightingProtection() {
		return lightingProtection;
	}
	public void setLightingProtection(String lightingProtection) {
		this.lightingProtection = lightingProtection;
	}
	public String getGroundConnection() {
		return groundConnection;
	}
	public void setGroundConnection(String groundConnection) {
		this.groundConnection = groundConnection;
	}
	public String getCommunicationDevice() {
		return communicationDevice;
	}
	public void setCommunicationDevice(String communicationDevice) {
		this.communicationDevice = communicationDevice;
	}
	public String getDeviceTime() {
		return deviceTime;
	}
	public void setDeviceTime(String deviceTime) {
		this.deviceTime = deviceTime;
	}
	public String getCleanProtectiveCover() {
		return cleanProtectiveCover;
	}
	public void setCleanProtectiveCover(String cleanProtectiveCover) {
		this.cleanProtectiveCover = cleanProtectiveCover;
	}
	public String getCleanCameraAsh() {
		return cleanCameraAsh;
	}
	public void setCleanCameraAsh(String cleanCameraAsh) {
		this.cleanCameraAsh = cleanCameraAsh;
	}
	public String getInstallation() {
		return installation;
	}
	public void setInstallation(String installation) {
		this.installation = installation;
	}
	public String getTrafficSign() {
		return trafficSign;
	}
	public void setTrafficSign(String trafficSign) {
		this.trafficSign = trafficSign;
	}
	public String getVehiclePassInfo() {
		return vehiclePassInfo;
	}
	public void setVehiclePassInfo(String vehiclePassInfo) {
		this.vehiclePassInfo = vehiclePassInfo;
	}
	public String getVehicleViolation() {
		return vehicleViolation;
	}
	public void setVehicleViolation(String vehicleViolation) {
		this.vehicleViolation = vehicleViolation;
	}
	public String getMeteorologyData() {
		return meteorologyData;
	}
	public void setMeteorologyData(String meteorologyData) {
		this.meteorologyData = meteorologyData;
	}
	public String getRecentlyUploadTime() {
		return recentlyUploadTime;
	}
	public void setRecentlyUploadTime(String recentlyUploadTime) {
		this.recentlyUploadTime = recentlyUploadTime;
	}
	public String getVideoQuality() {
		return videoQuality;
	}
	public void setVideoQuality(String videoQuality) {
		this.videoQuality = videoQuality;
	}
	public String getPtzControl() {
		return ptzControl;
	}
	public void setPtzControl(String ptzControl) {
		this.ptzControl = ptzControl;
	}
	public String getSelfCheck() {
		return selfCheck;
	}
	public void setSelfCheck(String selfCheck) {
		this.selfCheck = selfCheck;
	}
	public String getOutControlPoint() {
		return outControlPoint;
	}
	public void setOutControlPoint(String outControlPoint) {
		this.outControlPoint = outControlPoint;
	}
	public String getTrafficLight() {
		return trafficLight;
	}
	public void setTrafficLight(String trafficLight) {
		this.trafficLight = trafficLight;
	}
	public String getSignalDevice() {
		return signalDevice;
	}
	public void setSignalDevice(String signalDevice) {
		this.signalDevice = signalDevice;
	}
	public String getMaintenanceDate() {
		return maintenanceDate;
	}
	public void setMaintenanceDate(String maintenanceDate) {
		this.maintenanceDate = maintenanceDate;
	}
	public String getMaintenanceCycle() {
		return maintenanceCycle;
	}
	public void setMaintenanceCycle(String maintenanceCycle) {
		this.maintenanceCycle = maintenanceCycle;
	}
	public String getMaintenanceAdvice() {
		return maintenanceAdvice;
	}
	public void setMaintenanceAdvice(String maintenanceAdvice) {
		this.maintenanceAdvice = maintenanceAdvice;
	}
	public String getNextMaintenanceDate() {
		return nextMaintenanceDate;
	}
	public void setNextMaintenanceDate(String nextMaintenanceDate) {
		this.nextMaintenanceDate = nextMaintenanceDate;
	}
	public String getMaintainPerson() {
		return maintainPerson;
	}
	public void setMaintainPerson(String maintainPerson) {
		this.maintainPerson = maintainPerson;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getTrafficDataFlow() {
		return trafficDataFlow;
	}
	public void setTrafficDataFlow(String trafficDataFlow) {
		this.trafficDataFlow = trafficDataFlow;
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
	public String getRoadId() {
		return roadId;
	}
	public void setRoadId(String roadId) {
		this.roadId = roadId;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getContractorId() {
		return contractorId;
	}
	public void setContractorId(String contractorId) {
		this.contractorId = contractorId;
	}
	public String getMaintenanceDateFrom() {
		return maintenanceDateFrom;
	}
	public void setMaintenanceDateFrom(String maintenanceDateFrom) {
		this.maintenanceDateFrom = maintenanceDateFrom;
	}
	public String getMaintenanceDateTo() {
		return maintenanceDateTo;
	}
	public void setMaintenanceDateTo(String maintenanceDateTo) {
		this.maintenanceDateTo = maintenanceDateTo;
	}
	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}
	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}
	
	
	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}
	public void setDeviceSysNbr(String deviceSysNbr) {
		this.deviceSysNbr = deviceSysNbr;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	
	
	
}
