package cy.its.device.rest.dto;

import java.util.List;

import cy.its.com.dto.BaseDto;

public class DeviceInfoDto extends BaseDto {

	/** 电子监控系统表 **/
	private String deviceId; // 电子监控系统ID
	private String deviceSysModelId; // 系统型号ID
	private String contractId; // 合同ID
	private String deviceSysNbr; // 系统编号
	private String deviceName; // 系统名称
	private String orgId; // 机构ID
	private String deviceType; // 系统类型
	private String deviceTypeName; // 系统类型名称
	private String deviceBrand; // 品牌
	private String manufactureSn; // 出厂序列号
	private String softwareVersion; // 软件版本
	private String networkType; // 前端设备网络类型
	private String safeConnectMeans; // 安全接入方式
	private String deviceIp; // 设备IP
	private String devicePort; // 设备端口
	private String userName; // 用户名
	private String password; // 设备口令(密码)
	private String simNbr; // SIM卡号
	private String installDate; // 安装日期
	private String installBy; // 安装人
	private String mountingFacilityType;// 安装方式
	private String enableUpdateDate; // 使用状态更新时间
	private String useStatusFlag; // 使用状态标识
	private String longitude; // 经度
	private String latitude; // 纬度
	private String statusType; // 设备状态
	private String statusUpdateTime; // 设备状态更新时间
	private String syncStatus; // 同步标识
	private String createBy; // 创建人Id
	private String createByName; // 创建人
	private String createTime; // 创建时间
	private String updateBy; // 更新人员
	private String updateTime; // 更新时间
	private String remark; // 备注
	private String powerType; // 供电类型
	private String transmissionMode; // 传输方式
	private String bandwidth; // 网络带宽
	private String ownership; // 建设归属
	private String siteId; // 点位ID
	private String siteName; // 点位名称
	private String sectionIdList; // 监控断面列表
	private String serverGroupTypeId; // 服务器类型组ID
	private String collectionType; // 违法采集方式、
	private String contractorId; // 厂商ID
	private String roadId; // 道路ID
	private String orgPrivilegeCode; // 机构权限过滤代码
	private String serverPlatId;//服务器平台ID
	private String verificationMark;//
	
	private String deviceKey;			//唯一性标识
	private String oldDeviceKey;		//旧的唯一性标识（用于验证判断唯一性）
	private String oldDeviceSysNbr; // 编辑设备基本信息时，原来的设备编号（设备编号未改动）

	/** 卡口系统参数表 **/
	private String trackSysTollgateNbr; // 稽查布控系统卡口编号
	private String tollgateType; // 卡口类型
	private String isConnectTrackSys; // 是否接入稽查布控系统
	private String interceptConditions; // 支持拦截条件
	private String lawEnforceStationCode; // 执法服务站编号
	private String upRelatedVideoList; // 上行关联视频编号列表
	private String downRelatedVideoList; // 下行关联视频编号列表
	private String matchTypeList; // 支持的比对类型列表
	private String highwayEntranceExit; // 进出高速公路标识
	private String serviceEntranceExit; // 进出服务区标识
	private String photoModel; // 抓拍模式
	private Short largeCarSnapImages; // 大车图片抓拍张数
	private String detectionMode; // 检测方式
	private String isFlowSupport; // 是否支持流量监测
	private String isVioSupport;	//是否支持违法取证
	
	/** 取证系统参数表 **/
	private String integratePlatformNbr; // 综合平台设备登记编号
	private String collcetionOrg; // 采集单位
	private String collectionPerson; // 采集人
	private String validityDate; // 有效期止
	private String limitLarge; // 大车限高速
	private String limitSmall; // 小车限高速
	private String limitOthers; // 其他车限高速
	private String limitLower; // 限低速
	private String limitLargeMargin; // 大车限速容许值
	private String limitSmallMargin; // 小车限速容许值
	private String vioCollectionType; // 违法行为采集类型列表
	private String relatedVideoList; // 关联视频编号列表
	private String relatedVariableSpeed; // 关联可变限速牌编号列表

	/** 视频监控系统参数表 **/
	private String domeGunlock; // 球机或枪机
	private String videoResolution; // 视频分辨率
	private String videoSuperviseType; // 视频监控类型
	private String dailyPreset; // 日常监控预置位
	private String alarmPreset; // 点位告警紧急录像预置位
	private String isBackstageRecovery; // 是否需要后台控制恢复预置位
	private String preViewParam;// 预览参数
	private String playBackParam;// 回放参数
	private String cameraSn;//视频编号（视频对接时的编号）
	private String videoAccessMode;//视频介入方式
	
	private String oldCameraSn;//存储编辑时原先的视频编号（编辑时用于判断重复）

	/** 事件检测系统参数表 **/
	private String eventDetectionList; // 支持的事件检测种类
	private String eventDetectionListStr;
	/** 气象监测系统参数表 **/
	private String relatedVideoId; // 关联视频列表(也是LED的参数)
	private String relatedLedId; // 关联LED列表
	private String relatedSpeedDeviceId; // 关联测速设备列表
	private String isWeatherSupport;//是否支持气象采集     1 支持； 0 不支持
	private String isVisibilitySupport;//是否支持能见度    1 支持； 0 不支持
	private String isRoadsensorSupport;//是否支持路感    1 支持； 0 不支持
	/** LED系统参数表 **/
	private String specId;    //规格ID
	private String ledLevel;  //诱导屏等级
	
	/** 设备状态更改表 **/
	private String reason; // 更改原因

	/** 服务器类型与系统设备关联表 **/
	private String serverTypeId;

	

	public String getEventDetectionListStr() {
		return eventDetectionListStr;
	}

	public void setEventDetectionListStr(String eventDetectionListStr) {
		this.eventDetectionListStr = eventDetectionListStr;
	}

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

	public String getDeviceTypeName() {
		return deviceTypeName;
	}

	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
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

	public String getServerGroupTypeId() {
		return serverGroupTypeId;
	}

	public void setServerGroupTypeId(String serverGroupTypeId) {
		this.serverGroupTypeId = serverGroupTypeId;
	}

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

	

	public String getIsFlowSupport() {
		return isFlowSupport;
	}

	public void setIsFlowSupport(String isFlowSupport) {
		this.isFlowSupport = isFlowSupport;
	}

	public String getIntegratePlatformNbr() {
		return integratePlatformNbr;
	}

	public void setIntegratePlatformNbr(String integratePlatformNbr) {
		this.integratePlatformNbr = integratePlatformNbr;
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

	public String getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(String validityDate) {
		this.validityDate = validityDate;
	}

	public String getLimitLarge() {
		return limitLarge;
	}

	public void setLimitLarge(String limitLarge) {
		this.limitLarge = limitLarge;
	}

	public String getLimitSmall() {
		return limitSmall;
	}

	public void setLimitSmall(String limitSmall) {
		this.limitSmall = limitSmall;
	}

	public String getLimitOthers() {
		return limitOthers;
	}

	public void setLimitOthers(String limitOthers) {
		this.limitOthers = limitOthers;
	}

	public String getLimitLower() {
		return limitLower;
	}

	public void setLimitLower(String limitLower) {
		this.limitLower = limitLower;
	}

	public String getLimitLargeMargin() {
		return limitLargeMargin;
	}

	public void setLimitLargeMargin(String limitLargeMargin) {
		this.limitLargeMargin = limitLargeMargin;
	}

	public String getLimitSmallMargin() {
		return limitSmallMargin;
	}

	public void setLimitSmallMargin(String limitSmallMargin) {
		this.limitSmallMargin = limitSmallMargin;
	}

	public String getVioCollectionType() {
		return vioCollectionType;
	}

	public void setVioCollectionType(String vioCollectionType) {
		this.vioCollectionType = vioCollectionType;
	}

	public String getRelatedVideoList() {
		return relatedVideoList;
	}

	public void setRelatedVideoList(String relatedVideoList) {
		this.relatedVideoList = relatedVideoList;
	}

	public String getRelatedVariableSpeed() {
		return relatedVariableSpeed;
	}

	public void setRelatedVariableSpeed(String relatedVariableSpeed) {
		this.relatedVariableSpeed = relatedVariableSpeed;
	}

	public String getDomeGunlock() {
		return domeGunlock;
	}

	public void setDomeGunlock(String domeGunlock) {
		this.domeGunlock = domeGunlock;
	}

	public String getVideoResolution() {
		return videoResolution;
	}

	public void setVideoResolution(String videoResolution) {
		this.videoResolution = videoResolution;
	}

	public String getVideoSuperviseType() {
		return videoSuperviseType;
	}

	public void setVideoSuperviseType(String videoSuperviseType) {
		this.videoSuperviseType = videoSuperviseType;
	}

	public String getDailyPreset() {
		return dailyPreset;
	}

	public void setDailyPreset(String dailyPreset) {
		this.dailyPreset = dailyPreset;
	}

	public String getAlarmPreset() {
		return alarmPreset;
	}

	public void setAlarmPreset(String alarmPreset) {
		this.alarmPreset = alarmPreset;
	}

	public String getIsBackstageRecovery() {
		return isBackstageRecovery;
	}

	public void setIsBackstageRecovery(String isBackstageRecovery) {
		this.isBackstageRecovery = isBackstageRecovery;
	}

	public String getPreViewParam() {
		return preViewParam;
	}

	public void setPreViewParam(String preViewParam) {
		this.preViewParam = preViewParam;
	}

	public String getPlayBackParam() {
		return playBackParam;
	}

	public void setPlayBackParam(String playBackParam) {
		this.playBackParam = playBackParam;
	}

	public String getEventDetectionList() {
		return eventDetectionList;
	}

	public void setEventDetectionList(String eventDetectionList) {
		this.eventDetectionList = eventDetectionList;
	}

	public String getRelatedVideoId() {
		return relatedVideoId;
	}

	public void setRelatedVideoId(String relatedVideoId) {
		this.relatedVideoId = relatedVideoId;
	}

	public String getRelatedLedId() {
		return relatedLedId;
	}

	public void setRelatedLedId(String relatedLedId) {
		this.relatedLedId = relatedLedId;
	}

	public String getRelatedSpeedDeviceId() {
		return relatedSpeedDeviceId;
	}

	public void setRelatedSpeedDeviceId(String relatedSpeedDeviceId) {
		this.relatedSpeedDeviceId = relatedSpeedDeviceId;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCollectionType() {
		return collectionType;
	}

	public void setCollectionType(String collectionType) {
		this.collectionType = collectionType;
	}

	public String getServerTypeId() {
		return serverTypeId;
	}

	public void setServerTypeId(String serverTypeId) {
		this.serverTypeId = serverTypeId;
	}

	public String getContractorId() {
		return contractorId;
	}

	public void setContractorId(String contractorId) {
		this.contractorId = contractorId;
	}

	public String getRoadId() {
		return roadId;
	}

	public void setRoadId(String roadId) {
		this.roadId = roadId;
	}

	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}

	public String getServerPlatId() {
		return serverPlatId;
	}

	public void setServerPlatId(String serverPlatId) {
		this.serverPlatId = serverPlatId;
	}
	
	public String getOldDeviceSysNbr() {
		return oldDeviceSysNbr;
	}

	public void setOldDeviceSysNbr(String oldDeviceSysNbr) {
		this.oldDeviceSysNbr = oldDeviceSysNbr;
	}

	public String getDeviceKey() {
		return deviceKey;
	}

	public void setDeviceKey(String deviceKey) {
		this.deviceKey = deviceKey;
	}

	public String getOldDeviceKey() {
		return oldDeviceKey;
	}

	public void setOldDeviceKey(String oldDeviceKey) {
		this.oldDeviceKey = oldDeviceKey;
	}

	public String getCameraSn() {
		return cameraSn;
	}

	public void setCameraSn(String cameraSn) {
		this.cameraSn = cameraSn;
	}

	public String getOldCameraSn() {
		return oldCameraSn;
	}

	public void setOldCameraSn(String oldCameraSn) {
		this.oldCameraSn = oldCameraSn;
	}
	
	public String getIsWeatherSupport() {
		return isWeatherSupport;
	}

	public void setIsWeatherSupport(String isWeatherSupport) {
		this.isWeatherSupport = isWeatherSupport;
	}
	
	public String getIsVisibilitySupport() {
		return isVisibilitySupport;
	}

	public void setIsVisibilitySupport(String isVisibilitySupport) {
		this.isVisibilitySupport = isVisibilitySupport;
	}
	
	public String getIsRoadsensorSupport() {
		return isRoadsensorSupport;
	}

	public void setIsRoadsensorSupport(String isRoadsensorSupport) {
		this.isRoadsensorSupport = isRoadsensorSupport;
	}

	public String getVerificationMark() {
		return verificationMark;
	}

	public void setVerificationMark(String verificationMark) {
		this.verificationMark = verificationMark;
	}

	public String getVideoAccessMode() {
		return videoAccessMode;
	}

	public void setVideoAccessMode(String videoAccessMode) {
		this.videoAccessMode = videoAccessMode;
	}

	public String getIsVioSupport() {
		return isVioSupport;
	}

	public void setIsVioSupport(String isVioSupport) {
		this.isVioSupport = isVioSupport;
	}
	
	/** LED系统参数表 **/
	public String getSpecId() {
		return specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}

	public String getLedLevel() {
		return ledLevel;
	}

	public void setLedLevel(String ledLevel) {
		this.ledLevel = ledLevel;
	}
	
}
