package cy.its.device.rest.dto;


import com.wordnik.swagger.annotations.ApiParam;

import cy.its.com.dto.BaseDto;

public class BayonetDeviceDto extends BaseDto{
	
	/*****卡口基本信息*********/
	
	@ApiParam(name="deviceId",required = true,value="电子监控系统ID")
	private String deviceId;
	
	@ApiParam(name="deviceSysModelId",value="系统型号ID")
	private String deviceSysModelId;

	@ApiParam(name="contractId",value="合同ID")
	private String contractId;

	@ApiParam(name="deviceSysNbr",required = true,value="系统编号")
	private String deviceSysNbr;

	@ApiParam(name="deviceName",required = true,value="系统名称")
	private String deviceName;

	@ApiParam(name="orgId",required = true,value="机构ID")
	private String orgId;

	@ApiParam(name="deviceType",required = true,value="系统类型")
	private String deviceType;

	@ApiParam(name="deviceBrand",value="品牌")
	private String deviceBrand;

	@ApiParam(name="manufactureSn",value="出厂序列号")
	private String manufactureSn;

	@ApiParam(name="softwareVersion",value="软件版本")
	private String softwareVersion;

	@ApiParam(name="networkType",value="前端设备网络类型")
	private String networkType;

	@ApiParam(name="safeConnectMeans",required = true,value="安全接入方式")
	private String safeConnectMeans;

	@ApiParam(name="deviceIp",value="设备IP")
	private String deviceIp;

	@ApiParam(name="devicePort",value="设备端口")
	private String devicePort;

	@ApiParam(name="userName",value="用户名")
	private String userName;

	@ApiParam(name="password",value="设备口令")
	private String password;

	@ApiParam(name="simNbr",value="SIM卡号")
	private String simNbr;

	@ApiParam(name="installDate",value="安装日期")
	private String installDate;

	@ApiParam(name="installBy",value="安装人")
	private String installBy;

	@ApiParam(name="mountingFacilityType",value="安装方式")
	private String mountingFacilityType;

	@ApiParam(name="enableUpdateDate",value="使用状态更新时间")
	private String enableUpdateDate;

	@ApiParam(name="useStatusFlag",required = true,value="使用状态标识")
    private String useStatusFlag;

	@ApiParam(name="longitude",value="经度")
	private String longitude;

	@ApiParam(name="latitude",value="纬度")
	private String latitude;

	@ApiParam(name="statusType",required = true,value="设备状态")
	private String statusType;

	@ApiParam(name="statusUpdateTime",value="设备状态更新时间")
	private String statusUpdateTime;

	@ApiParam(name="syncStatus",value="同步标识")
	private String syncStatus;

	@ApiParam(name="createBy",value="创建人")
	private String createBy;

	@ApiParam(name="createTime",required = true,value="创建时间")
	private String createTime;

	@ApiParam(name="updateBy",value="更新人员")
	private String updateBy;

	@ApiParam(name="updateTime",value="更新时间")
	private String updateTime;

	@ApiParam(name="remark",value="备注")
	private String remark;

	@ApiParam(name="powerType",value="供电类型")
	private String powerType;

	@ApiParam(name="transmissionMode",value="传输方式")
	private String transmissionMode;

	@ApiParam(name="bandwidth",value="网络带宽")
	private String bandwidth;

	@ApiParam(name="ownership",value="建设归属")
	private String ownership;

	@ApiParam(name="siteId",value="点位ID")
	private String siteId;

	@ApiParam(name="sectionIdList",value="监控断面列表")
    private String sectionIdList;
	
	@ApiParam(name="collectionType",value="违法采集方式")
    private String collectionType;

	@ApiParam(name="orgPrivilegeCode",value="机构权限过滤代码")
    private String orgPrivilegeCode;

	@ApiParam(name="contractorId",value="厂商ID")
    private String contractorId;

	@ApiParam(name="architecture",required = true,value="网络体系结构")
    private String architecture;

	@ApiParam(name="serverPlatId",value="接入平台ID")
    private String serverPlatId;
	
	@ApiParam(name="isIndustrialControl",value="是否是工控式卡口")
	private String isIndustrialControl;
	
	@ApiParam(name="collcetionOrg",value="采集单位")
	private String collcetionOrg;
	 
	@ApiParam(name="collectionPerson",value="采集人")
	private String collectionPerson;
	
	@ApiParam(name="roadId",value="道路ID")
	private String roadId;
	  
	@ApiParam(name="verificationMark",value="检定标识")
	private String verificationMark;
	
	/**************卡口参数**************/
	@ApiParam(name="trackSysTollgateNbr",value="稽查布控系统卡口编号")
	private String trackSysTollgateNbr;

	@ApiParam(name="tollgateType",required = true,value="卡口类型")
	private String tollgateType;

	@ApiParam(name="isConnectTrackSys",required = true,value="是否接入稽查布控系统")
	private String isConnectTrackSys;

	@ApiParam(name="interceptConditions",value="拦截条件")
	private String interceptConditions;

	@ApiParam(name="lawEnforceStationCode",value="执法服务站编号")
	private String lawEnforceStationCode;

	@ApiParam(name="upRelatedVideoList",value="上行关联视频编号列表")
	private String upRelatedVideoList;

	@ApiParam(name="downRelatedVideoList",value="下行关联视频编号列表")
	private String downRelatedVideoList;

	@ApiParam(name="matchTypeList",value="支持的比对类型列表")
	private String matchTypeList;

	@ApiParam(name="highwayEntranceExit",value="进出高速公路标识")
	private String highwayEntranceExit;

	@ApiParam(name="serviceEntranceExit",value="进出服务区标识")
	private String serviceEntranceExit;

	@ApiParam(name="photoModel",value="抓拍模式")
	private String photoModel;

	@ApiParam(name="largeCarSnapImages",value="大车图片抓拍张数")
	private Short largeCarSnapImages;

	@ApiParam(name="detectionMode",value="检测方式")
    private String detectionMode;

	@ApiParam(name="isVioSupport",value="是否支持违法取证")
    private String isVioSupport;
	
	@ApiParam(name="isFlowSupport",value="是否支持流量采集")
    private String isFlowSupport;

	@ApiParam(name="integratePlatformNbr",value="综合平台设备登记编号")
    private String integratePlatformNbr;
	
	/**************业务处理辅助字段**************/
	@ApiParam(name="oldDeviceSysNbr",value="编辑前的设备编号")
	private String oldDeviceSysNbr;
	
	@ApiParam(name="cameraInfoList",value="存放相机信息的集合")
	private String cameraInfoList;
	
	@ApiParam(name="serviceEntranceExitUp",value="上行进出辖区（城）标识")
	private String serviceEntranceExitUp;
	
	@ApiParam(name="serviceEntranceExitDown",value="下行进出辖区（城）标识")
	private String serviceEntranceExitDown;
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceSysModelId() {
		return deviceSysModelId;
	}

	public void setDeviceSysModelId(String deviceSysModelId) {
		this.deviceSysModelId = deviceSysModelId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
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

	public String getDeviceBrand() {
		return deviceBrand;
	}

	public void setDeviceBrand(String deviceBrand) {
		this.deviceBrand = deviceBrand;
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

	public String getNetworkType() {
		return networkType;
	}

	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}

	public String getSafeConnectMeans() {
		return safeConnectMeans;
	}

	public void setSafeConnectMeans(String safeConnectMeans) {
		this.safeConnectMeans = safeConnectMeans;
	}

	public String getDeviceIp() {
		return deviceIp;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	public String getDevicePort() {
		return devicePort;
	}

	public void setDevicePort(String devicePort) {
		this.devicePort = devicePort;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSimNbr() {
		return simNbr;
	}

	public void setSimNbr(String simNbr) {
		this.simNbr = simNbr;
	}

	public String getInstallDate() {
		return installDate;
	}

	public void setInstallDate(String installDate) {
		this.installDate = installDate;
	}

	public String getInstallBy() {
		return installBy;
	}

	public void setInstallBy(String installBy) {
		this.installBy = installBy;
	}

	public String getMountingFacilityType() {
		return mountingFacilityType;
	}

	public void setMountingFacilityType(String mountingFacilityType) {
		this.mountingFacilityType = mountingFacilityType;
	}

	public String getEnableUpdateDate() {
		return enableUpdateDate;
	}

	public void setEnableUpdateDate(String enableUpdateDate) {
		this.enableUpdateDate = enableUpdateDate;
	}

	public String getUseStatusFlag() {
		return useStatusFlag;
	}

	public void setUseStatusFlag(String useStatusFlag) {
		this.useStatusFlag = useStatusFlag;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public String getStatusUpdateTime() {
		return statusUpdateTime;
	}

	public void setStatusUpdateTime(String statusUpdateTime) {
		this.statusUpdateTime = statusUpdateTime;
	}

	public String getSyncStatus() {
		return syncStatus;
	}

	public void setSyncStatus(String syncStatus) {
		this.syncStatus = syncStatus;
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

	public String getPowerType() {
		return powerType;
	}

	public void setPowerType(String powerType) {
		this.powerType = powerType;
	}

	public String getTransmissionMode() {
		return transmissionMode;
	}

	public void setTransmissionMode(String transmissionMode) {
		this.transmissionMode = transmissionMode;
	}

	public String getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(String bandwidth) {
		this.bandwidth = bandwidth;
	}

	public String getOwnership() {
		return ownership;
	}

	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getSectionIdList() {
		return sectionIdList;
	}

	public void setSectionIdList(String sectionIdList) {
		this.sectionIdList = sectionIdList;
	}

	public String getCollectionType() {
		return collectionType;
	}

	public void setCollectionType(String collectionType) {
		this.collectionType = collectionType;
	}

	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}

	public String getContractorId() {
		return contractorId;
	}

	public void setContractorId(String contractorId) {
		this.contractorId = contractorId;
	}

	public String getArchitecture() {
		return architecture;
	}

	public void setArchitecture(String architecture) {
		this.architecture = architecture;
	}

	public String getServerPlatId() {
		return serverPlatId;
	}

	public void setServerPlatId(String serverPlatId) {
		this.serverPlatId = serverPlatId;
	}
	
	public String getIsIndustrialControl() {
		return isIndustrialControl;
	}

	public void setIsIndustrialControl(String isIndustrialControl) {
		this.isIndustrialControl = isIndustrialControl;
	}
	
	public String getCollcetionOrg() {
		return collcetionOrg;
	}

	public void setCollcetionOrg(String collcetionOrg) {
		this.collcetionOrg = collcetionOrg;
	}

	public String getCollectionPerson() {
		return collectionPerson;
	}

	public void setCollectionPerson(String collectionPerson) {
		this.collectionPerson = collectionPerson;
	}
	
	public String getRoadId() {
		return roadId;
	}

	public void setRoadId(String roadId) {
		this.roadId = roadId;
	}
	
	public String getVerificationMark() {
		return verificationMark;
	}

	public void setVerificationMark(String verificationMark) {
		this.verificationMark = verificationMark;
	}

	/**************卡口参数**************/

	public String getTrackSysTollgateNbr() {
		return trackSysTollgateNbr;
	}

	public void setTrackSysTollgateNbr(String trackSysTollgateNbr) {
		this.trackSysTollgateNbr = trackSysTollgateNbr;
	}

	public String getTollgateType() {
		return tollgateType;
	}

	public void setTollgateType(String tollgateType) {
		this.tollgateType = tollgateType;
	}

	public String getIsConnectTrackSys() {
		return isConnectTrackSys;
	}

	public void setIsConnectTrackSys(String isConnectTrackSys) {
		this.isConnectTrackSys = isConnectTrackSys;
	}

	public String getInterceptConditions() {
		return interceptConditions;
	}
	
	public String getIsFlowSupport() {
		return isFlowSupport;
	}

	public void setIsFlowSupport(String isFlowSupport) {
		this.isFlowSupport = isFlowSupport;
	}

	public void setInterceptConditions(String interceptConditions) {
		this.interceptConditions = interceptConditions;
	}

	public String getLawEnforceStationCode() {
		return lawEnforceStationCode;
	}

	public void setLawEnforceStationCode(String lawEnforceStationCode) {
		this.lawEnforceStationCode = lawEnforceStationCode;
	}

	public String getUpRelatedVideoList() {
		return upRelatedVideoList;
	}

	public void setUpRelatedVideoList(String upRelatedVideoList) {
		this.upRelatedVideoList = upRelatedVideoList;
	}

	public String getDownRelatedVideoList() {
		return downRelatedVideoList;
	}

	public void setDownRelatedVideoList(String downRelatedVideoList) {
		this.downRelatedVideoList = downRelatedVideoList;
	}

	public String getMatchTypeList() {
		return matchTypeList;
	}

	public void setMatchTypeList(String matchTypeList) {
		this.matchTypeList = matchTypeList;
	}

	public String getHighwayEntranceExit() {
		return highwayEntranceExit;
	}

	public void setHighwayEntranceExit(String highwayEntranceExit) {
		this.highwayEntranceExit = highwayEntranceExit;
	}

	public String getServiceEntranceExit() {
		return serviceEntranceExit;
	}

	public void setServiceEntranceExit(String serviceEntranceExit) {
		this.serviceEntranceExit = serviceEntranceExit;
	}

	public String getPhotoModel() {
		return photoModel;
	}

	public void setPhotoModel(String photoModel) {
		this.photoModel = photoModel;
	}

	public Short getLargeCarSnapImages() {
		return largeCarSnapImages;
	}

	public void setLargeCarSnapImages(Short largeCarSnapImages) {
		this.largeCarSnapImages = largeCarSnapImages;
	}

	public String getDetectionMode() {
		return detectionMode;
	}

	public void setDetectionMode(String detectionMode) {
		this.detectionMode = detectionMode;
	}

	public String getIsVioSupport() {
		return isVioSupport;
	}

	public void setIsVioSupport(String isVioSupport) {
		this.isVioSupport = isVioSupport;
	}

	public String getIntegratePlatformNbr() {
		return integratePlatformNbr;
	}

	public void setIntegratePlatformNbr(String integratePlatformNbr) {
		this.integratePlatformNbr = integratePlatformNbr;
	}
	/**************业务处理辅助字段**************/

	public String getOldDeviceSysNbr() {
		return oldDeviceSysNbr;
	}

	public void setOldDeviceSysNbr(String oldDeviceSysNbr) {
		this.oldDeviceSysNbr = oldDeviceSysNbr;
	}

	public String getCameraInfoList() {
		return cameraInfoList;
	}

	public void setCameraInfoList(String cameraInfoList) {
		this.cameraInfoList = cameraInfoList;
	}

	public String getServiceEntranceExitUp() {
		return serviceEntranceExitUp;
	}

	public void setServiceEntranceExitUp(String serviceEntranceExitUp) {
		this.serviceEntranceExitUp = serviceEntranceExitUp;
	}

	public String getServiceEntranceExitDown() {
		return serviceEntranceExitDown;
	}

	public void setServiceEntranceExitDown(String serviceEntranceExitDown) {
		this.serviceEntranceExitDown = serviceEntranceExitDown;
	}
	
}
