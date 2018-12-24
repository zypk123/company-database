package cy.its.violation.mybatis.model;

import java.util.Date;

public class T_Vio_Violation {
	private String violationId;

	private String vehicleRegId;

	private String deviceSysNbr;

	private String snapNbr;

	private String orgCode;

	private String collectionType;

	private String collectionPolice;

	private String districtCode;

	private String vioSiteCode;

	private String addressDesc;

	private String roadCode;

	private String roadSectionCode;

	private Short mileage;

	private String directionType;

	private String directionName;

	private String laneNbr;

	private String plateNbr;

	private String plateType;

	private String plateColor;

	private Date violationTime;

	private String violationType;

	private String violationCode;

	private String violationDesc;

	private Integer speed;

	private Long limitLarge;

	private Long limitSmall;

	private Integer limitLower;

	private Integer overSpeedPercent;

	private String entrySiteCode;

	private Date regionEntryTime;

	private Long regionDistance;

	private Date redLightBeginTime;

	private Date redLightEndTime;

	private String speedingType;

	private String specialVehType;

	private String discardedReason;

	private String discardedType;

	private String localPunishFlag;

	private String exportFlag;

	private Date exportTime;

	private String lockFlag;

	private String lockUser;

	private Date lockTime;

	private String statusFlag;

	private String entryBy;

	private Date entryTime;

	private String uploadFlag;

	private String uploadBy;

	private Date uploadTime;

	private Date createTime;

	private Date updateTime;

	private String image;

	private String video;

	private String remark;

	private String vehLocalization;

	private String discardedBy;

	private Date discardedTime;

	private String deviceNBr;

	private String orgAuthorityCode;

	private String crossCode;

	public String getViolationId() {
		return violationId;
	}

	public void setViolationId(String violationId) {
		this.violationId = violationId;
	}

	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}

	public void setDeviceSysNbr(String deviceSysNbr) {
		this.deviceSysNbr = deviceSysNbr;
	}

	public String getDeviceNBr() {
		return deviceNBr;
	}

	public void setDeviceNBr(String deviceNBr) {
		this.deviceNBr = deviceNBr;
	}

	public String getOrgAuthorityCode() {
		return orgAuthorityCode;
	}

	public void setOrgAuthorityCode(String orgAuthorityCode) {
		this.orgAuthorityCode = orgAuthorityCode;
	}

	public String getCrossCode() {
		return crossCode;
	}

	public void setCrossCode(String crossCode) {
		this.crossCode = crossCode;
	}

	public String getVehicleRegId() {
		return vehicleRegId;
	}

	public void setVehicleRegId(String vehicleRegId) {
		this.vehicleRegId = vehicleRegId;
	}

	public String getDeviceNbr() {
		return deviceSysNbr;
	}

	public void setDeviceNbr(String deviceNbr) {
		this.deviceSysNbr = deviceNbr;
	}

	public String getSnapNbr() {
		return snapNbr;
	}

	public void setSnapNbr(String snapNbr) {
		this.snapNbr = snapNbr;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getCollectionType() {
		return collectionType;
	}

	public void setCollectionType(String collectionType) {
		this.collectionType = collectionType;
	}

	public String getCollectionPolice() {
		return collectionPolice;
	}

	public void setCollectionPolice(String collectionPolice) {
		this.collectionPolice = collectionPolice;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getVioSiteCode() {
		return vioSiteCode;
	}

	public void setVioSiteCode(String vioSiteCode) {
		this.vioSiteCode = vioSiteCode;
	}

	public String getAddressDesc() {
		return addressDesc;
	}

	public void setAddressDesc(String addressDesc) {
		this.addressDesc = addressDesc;
	}

	public String getRoadCode() {
		return roadCode;
	}

	public void setRoadCode(String roadCode) {
		this.roadCode = roadCode;
	}

	public String getRoadSectionCode() {
		return roadSectionCode;
	}

	public void setRoadSectionCode(String roadSectionCode) {
		this.roadSectionCode = roadSectionCode;
	}

	public Short getMileage() {
		return mileage;
	}

	public void setMileage(Short mileage) {
		this.mileage = mileage;
	}

	public String getDirectionType() {
		return directionType;
	}

	public void setDirectionType(String directionType) {
		this.directionType = directionType;
	}

	public String getDirectionName() {
		return directionName;
	}

	public void setDirectionName(String directionName) {
		this.directionName = directionName;
	}

	public String getLaneNbr() {
		return laneNbr;
	}

	public void setLaneNbr(String laneNbr) {
		this.laneNbr = laneNbr;
	}

	public String getPlateNbr() {
		return plateNbr;
	}

	public void setPlateNbr(String plateNbr) {
		this.plateNbr = plateNbr;
	}

	public String getPlateType() {
		return plateType;
	}

	public void setPlateType(String plateType) {
		this.plateType = plateType;
	}

	public String getPlateColor() {
		return plateColor;
	}

	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}

	public Date getViolationTime() {
		return violationTime;
	}

	public void setViolationTime(Date violationTime) {
		this.violationTime = violationTime;
	}

	public String getViolationType() {
		return violationType;
	}

	public void setViolationType(String violationType) {
		this.violationType = violationType;
	}

	public String getViolationCode() {
		return violationCode;
	}

	public void setViolationCode(String violationCode) {
		this.violationCode = violationCode;
	}

	public String getViolationDesc() {
		return violationDesc;
	}

	public void setViolationDesc(String violationDesc) {
		this.violationDesc = violationDesc;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public Long getLimitLarge() {
		return limitLarge;
	}

	public void setLimitLarge(Long limitLarge) {
		this.limitLarge = limitLarge;
	}

	public Long getLimitSmall() {
		return limitSmall;
	}

	public void setLimitSmall(Long limitSmall) {
		this.limitSmall = limitSmall;
	}

	public Integer getLimitLower() {
		return limitLower;
	}

	public void setLimitLower(Integer limitLower) {
		this.limitLower = limitLower;
	}

	public Integer getOverSpeedPercent() {
		return overSpeedPercent;
	}

	public void setOverSpeedPercent(Integer overSpeedPercent) {
		this.overSpeedPercent = overSpeedPercent;
	}

	public String getEntrySiteCode() {
		return entrySiteCode;
	}

	public void setEntrySiteCode(String entrySiteCode) {
		this.entrySiteCode = entrySiteCode;
	}

	public Date getRegionEntryTime() {
		return regionEntryTime;
	}

	public void setRegionEntryTime(Date regionEntryTime) {
		this.regionEntryTime = regionEntryTime;
	}

	public Long getRegionDistance() {
		return regionDistance;
	}

	public void setRegionDistance(Long regionDistance) {
		this.regionDistance = regionDistance;
	}

	public Date getRedLightBeginTime() {
		return redLightBeginTime;
	}

	public void setRedLightBeginTime(Date redLightBeginTime) {
		this.redLightBeginTime = redLightBeginTime;
	}

	public Date getRedLightEndTime() {
		return redLightEndTime;
	}

	public void setRedLightEndTime(Date redLightEndTime) {
		this.redLightEndTime = redLightEndTime;
	}

	public String getSpeedingType() {
		return speedingType;
	}

	public void setSpeedingType(String speedingType) {
		this.speedingType = speedingType;
	}

	public String getSpecialVehType() {
		return specialVehType;
	}

	public void setSpecialVehType(String specialVehType) {
		this.specialVehType = specialVehType;
	}

	public String getDiscardedReason() {
		return discardedReason;
	}

	public void setDiscardedReason(String discardedReason) {
		this.discardedReason = discardedReason;
	}

	public String getDiscardedType() {
		return discardedType;
	}

	public void setDiscardedType(String discardedType) {
		this.discardedType = discardedType;
	}

	public String getLocalPunishFlag() {
		return localPunishFlag;
	}

	public void setLocalPunishFlag(String localPunishFlag) {
		this.localPunishFlag = localPunishFlag;
	}

	public String getExportFlag() {
		return exportFlag;
	}

	public void setExportFlag(String exportFlag) {
		this.exportFlag = exportFlag;
	}

	public Date getExportTime() {
		return exportTime;
	}

	public void setExportTime(Date exportTime) {
		this.exportTime = exportTime;
	}

	public String getLockFlag() {
		return lockFlag;
	}

	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}

	public String getLockUser() {
		return lockUser;
	}

	public void setLockUser(String lockUser) {
		this.lockUser = lockUser;
	}

	public Date getLockTime() {
		return lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}

	public String getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}

	public String getEntryBy() {
		return entryBy;
	}

	public void setEntryBy(String entryBy) {
		this.entryBy = entryBy;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	public String getUploadFlag() {
		return uploadFlag;
	}

	public void setUploadFlag(String uploadFlag) {
		this.uploadFlag = uploadFlag;
	}

	public String getUploadBy() {
		return uploadBy;
	}

	public void setUploadBy(String uploadBy) {
		this.uploadBy = uploadBy;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getVehLocalization() {
		return vehLocalization;
	}

	public void setVehLocalization(String vehLocalization) {
		this.vehLocalization = vehLocalization;
	}

	public String getDiscardedBy() {
		return discardedBy;
	}

	public void setDiscardedBy(String discardedBy) {
		this.discardedBy = discardedBy;
	}

	public Date getDiscardedTime() {
		return discardedTime;
	}

	public void setDiscardedTime(Date discardedTime) {
		this.discardedTime = discardedTime;
	}
}