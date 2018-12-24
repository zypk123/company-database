package cy.its.violation.domain.model.violation;

import java.util.Date;

import cy.its.com.domain.AggregateRoot;
import cy.its.violation.domain.criteria.ConstantCode;

/**
 * @author Administrator
 *
 */
public class Violation extends AggregateRoot {

	/**
	 * 违法证据ID
	 */
	public String violationId;

	/**
	 * 车辆信息表ID
	 */
	public String vehicleRegId;

	/**
	 * 设备/区间编号
	 */
	public String deviceSysNbr;

	/**
	 * 抓拍编号
	 */
	public String snapNbr;

	/**
	*
	*/
	private String orgCode;

	/**
	 * 1：闯红灯设备 2：公路卡口设备 3：测速设备 4：闭路电视 5：移动摄像 6：警务通 7：区间测速 8：卫星定位装置 9：其它电子设备
	 */
	private String collectionType;

	/**
	*
	*/
	public String collectionPolice;

	/**
	*
	*/
	private String districtCode;

	/**
	*
	*/
	private String vioSiteCode;

	/**
	 * 违法地点
	 */
	private String addressDesc;

	/**
	*
	*/
	private String roadCode;

	/**
	*
	*/
	private String roadSectionCode;

	/**
	*
	*/
	public Short mileage;

	/**
	 * 1：上行；2：下行。
	 */
	public String directionType;

	/**
	*
	*/
	public String directionName;

	/**
	*
	*/
	public String laneNbr;

	/**
	 * 号牌号码
	 */
	private String plateNbr;

	/**
	 * 号牌类型(002)
	 */
	private String plateType;

	/**
	 * 号牌颜色(003)
	 */
	private String plateColor;

	/**
	 * 违法时间
	 */
	private Date violationTime;

	/**
	 * 违法类型(030)： 0：大车占道 1：超高速 2：超低速 3：逆行 4：闯红灯 5：压黄线 6：违章停车 7：区间超速 8：禁行 9：其他
	 * a：违法占道 b：遮挡号牌 c：不按导向行驶 d：压线 e：未系安全带
	 */
	private String violationType;

	/**
	 * 违法代码
	 */
	private String violationCode;

	/**
	 * 违法描述
	 */
	private String violationDesc;

	/**
	 * 车速
	 */
	public Integer speed;

	/**
	*
	*/
	public Long limitLarge;

	/**
	*
	*/
	public Long limitSmall;

	/**
	*
	*/
	public Integer limitLower;

	/**
	 * 超速比
	 */
	public Integer overSpeedPercent;

	/**
	 * 入口点位代码
	 */
	public String entrySiteCode;

	/**
	 * 区间入口时间
	 */
	public Date regionEntryTime;

	/**
	 * 区间距离
	 */
	public Long regionDistance;

	/**
	 * 红灯开始时间
	 */
	public Date redLightBeginTime;

	/**
	 * 红灯结束时间
	 */
	public Date redLightEndTime;

	/**
	 * 1 移动测速 2 区间测速 3 固定点测速 4 卡口线圈测速 5 卡口雷达测速
	 */
	public String speedingType;

	/**
	*
	*/
	public String abnormalDataType;

	/**
	 * 特殊车辆类型 1、套牌车 2、假牌车 3、白名单车 4、军警车 5、农用车 6、摩托车
	 */
	public String specialVehType;

	/**
	 * 废弃原因(312) 01 异常数据 02 特殊车辆 21套牌车 22 假牌车 23 白名单车 24 军警车 25 农用车 26 摩托车 03
	 * 重复记录 04 无效图像 05 号牌不全 06 无号牌 07 超时 08 其他
	 */
	public String discardedReason;

	/**
	 * 废弃类别(306), 1 单张废弃 2 自动废弃 3 批量废弃
	 */
	public String discardedType;

	/**
	 * 本地处罚标记（313）,0：未处罚；1：已处罚。
	 */
	private String localPunishFlag;

	/**
	 * 导出标识(314),0：未导出；1：已导出。
	 */
	private String exportFlag;

	/**
	 * 导出时间
	 */
	public Date exportTime;

	/**
	 * 锁定标识,0：未锁定；1：已锁定。
	 */
	private String lockFlag;

	/**
	 * 锁定人
	 */
	public String lockUser;

	/**
	 * 锁定时间
	 */
	public Date lockTime;

	/**
	 * 记录状态（301）,0：新记录；1：已录入；2：废弃记录
	 */
	private String statusFlag;

	/**
	 * 录入人
	 */
	public String entryBy;

	/**
	 * 录入时间
	 */
	public Date entryTime;

	/**
	 * 上传状态（305） 0 未上传 1 待上传 2 已上传 3 上传失败
	 */
	public String uploadFlag;

	/**
	 * 上传人
	 */
	public String uploadBy;

	/**
	 * 上传时间
	 */
	public Date uploadTime;

	/**
	*
	*/
	public Date createTime;

	/**
	*
	*/
	public Date updateTime;

	/**
	 * 图片存储路径
	 */
	public String image;

	/**
	 * 视频存储路径
	 */
	public String video;

	/**
	*
	*/
	public String remark;

	private String discardedBy;

	private Date discardedTime;

	private String getDeviceNBr() {
		return deviceNBr;
	}

	private void setDeviceNBr(String deviceNBr) {
		this.deviceNBr = deviceNBr;
	}

	private String getOrgAuthorityCode() {
		return orgAuthorityCode;
	}

	private void setOrgAuthorityCode(String orgAuthorityCode) {
		this.orgAuthorityCode = orgAuthorityCode;
	}

	private String getCrossCode() {
		return crossCode;
	}

	private void setCrossCode(String crossCode) {
		this.crossCode = crossCode;
	}

	private String deviceNBr;

	private String orgAuthorityCode;

	private String crossCode;

	/**
	 * 违法录入
	 * 
	 * @param input
	 * @throws Exception
	 */
	public void excuteInput(ViolationInput input) throws Exception {
		input.Excute(this);
	}

	public String getViolationId() {
		return violationId;
	}

	public void setViolationId(String violationId) {
		this.violationId = violationId;
	}

	public String getDeviceNbr() {
		return deviceSysNbr;
	}

	public void setDeviceNbr(String deviceNbr) {
		this.deviceSysNbr = deviceNbr;
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

	public String getPlateNbr() {
		return plateNbr;
	}

	public void setPlateNbr(String plateNbr) throws Exception {
		NotNull(plateNbr, "号牌号码");
		this.plateNbr = plateNbr;
	}

	public String getPlateType() {
		return plateType;
	}

	public void setPlateType(String plateType) throws Exception {
		NotNull(plateNbr, "号牌种类");
		this.plateType = plateType;
	}

	public String getPlateColor() {
		return plateColor;
	}

	public void setPlateColor(String plateColor) throws Exception {
		NotNull(plateNbr, "号牌颜色");
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

	public void setViolationCode(String violationCode) throws Exception {
		NotNull(violationCode, "违法代码");
		this.violationCode = violationCode;
	}

	public String getViolationDesc() {
		return violationDesc;
	}

	public void setViolationDesc(String violationDesc) throws Exception {
		NotNull(violationDesc, "违法描述");
		this.violationDesc = violationDesc;
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

	public String getLockFlag() {
		return lockFlag;
	}

	public void setLockFlag(String lockFlag) {

		this.lockFlag = lockFlag;
	}

	public String getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
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

	@Override
	public String getIdentityId() {
		return this.violationId;
	}

	public String getVehicleRegId() {
		return vehicleRegId;
	}

	public void setVehicleRegId(String vehicleRegId) {
		this.vehicleRegId = vehicleRegId;
	}

	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}

	public void setDeviceSysNbr(String deviceSysNbr) {
		this.deviceSysNbr = deviceSysNbr;
	}

	public String getSnapNbr() {
		return snapNbr;
	}

	public void setSnapNbr(String snapNbr) {
		this.snapNbr = snapNbr;
	}

	public String getCollectionPolice() {
		return collectionPolice;
	}

	public void setCollectionPolice(String collectionPolice) {
		this.collectionPolice = collectionPolice;
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

	public String getAbnormalDataType() {
		return abnormalDataType;
	}

	public void setAbnormalDataType(String abnormalDataType) {
		this.abnormalDataType = abnormalDataType;
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

	public Date getExportTime() {
		return exportTime;
	}

	public void setExportTime(Date exportTime) {
		this.exportTime = exportTime;
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

	private Violation(String deviceNbr, String orgCode, String collectionType, String districtCode, String vioSiteCode,
			String addressDesc, String roadCode, String roadSectionCode, String plateNbr, String plateType,
			String plateColor, Date violationTime, String violationType, String violationCode, String violationDesc,
			String localPunishFlag, String exportFlag, String lockFlag, String statusFlag) {
		this.deviceSysNbr = deviceNbr;
		this.orgCode = orgCode;
		this.collectionType = collectionType;
		this.districtCode = districtCode;
		this.vioSiteCode = vioSiteCode;
		this.addressDesc = addressDesc;
		this.roadCode = roadCode;
		this.roadSectionCode = roadSectionCode;
		this.plateNbr = plateNbr;
		this.plateType = plateType;
		this.plateColor = plateColor;
		this.violationTime = violationTime;
		this.violationType = violationType;
		this.violationCode = violationCode;
		this.violationDesc = violationDesc;
		this.localPunishFlag = localPunishFlag;
		this.exportFlag = exportFlag;
		this.lockFlag = lockFlag;
		this.statusFlag = statusFlag;
	}

	public Violation(String deviceNbr, String orgCode, String collectionType, String districtCode, String vioSiteCode,
			String addressDesc, String roadCode, String roadSectionCode, String plateNbr, String plateType,
			String plateColor, Date violationTime, String violationType, String violationCode, String violationDesc) {

		this(deviceNbr, orgCode, collectionType, districtCode, vioSiteCode, addressDesc, roadCode, roadSectionCode,
				plateNbr, plateType, plateColor, violationTime, violationType, violationCode, violationDesc, "0", "0",
				"0", "0");
	}

	public Violation(String violationId, String deviceNbr, String orgCode, String collectionType, String districtCode,
			String vioSiteCode, String addressDesc, String roadCode, String roadSectionCode, String plateNbr,
			String plateType, String plateColor, Date violationTime, String violationType, String violationCode,
			String violationDesc, String localPunishFlag, String exportFlag, String lockFlag, String statusFlag) {
		this(deviceNbr, orgCode, collectionType, districtCode, vioSiteCode, addressDesc, roadCode, roadSectionCode,
				plateNbr, plateType, plateColor, violationTime, violationType, violationCode, violationDesc,
				localPunishFlag, exportFlag, lockFlag, statusFlag);
		this.violationId = violationId;
	}

	public Violation() {
	}

	/**
	 * 
	 * @param input
	 * @throws Exception
	 */
	public Violation(ViolationInput input) throws Exception {
		input.Excute(this);
	}

	/**
	 * 修正上传失败违法以便再次上传
	 * 
	 * @param roadCode
	 *            道路代码
	 * @param orgCode
	 *            机构代码
	 * @param police
	 *            警员
	 * @param uploadBy
	 *            上传人
	 */
	public void verifyForReadyUpload(String roadCode, String orgCode, String uploadBy) {
		this.setRoadCode(roadCode);
		this.setOrgCode(orgCode);
		this.uploadBy = uploadBy;
		this.setUploadFlag(ConstantCode.UploadFlag.WAITING);
		this.updateTime = new Date();
	}

	/**
	 * 导入
	 */
	public void Import() {

	}

	/**
	 * 修改上传状态为待上传
	 * 
	 * @param uploadBy
	 */
	public void readyUpload(String uploadBy) {
		this.setUploadFlag(ConstantCode.UploadFlag.WAITING);
		this.uploadBy = uploadBy;
		this.updateTime = new Date();
	}

	/**
	 * 修改上传状态为取消上传
	 * 
	 * @param uploadBy
	 */
	public void cancelUpload(String uploadBy) {
		this.setUploadFlag(ConstantCode.UploadFlag.CANCEL);
		this.uploadBy = "";
		this.updateTime = new Date();
	}

	/**
	 * 重录
	 * 
	 * @param opUser
	 */
	public void reInputViolation(String opUser) {
		this.setStatusFlag(ConstantCode.StatusFlag.RECONFIRM);
		this.updateTime = new Date();
	}

	/**
	 * 确认录入
	 */
	public void confirmInput() {
		this.setStatusFlag(ConstantCode.StatusFlag.CONFIRMED);
		this.setUploadFlag(ConstantCode.UploadFlag.NEW);
		this.updateTime = new Date();
	}

	/**
	 * 筛选通过
	 * 
	 * @param opUser
	 */
	public void filter(String opUser) {
		this.setStatusFlag(ConstantCode.StatusFlag.FILTERED);
		this.exportTime = new Date();
		this.updateTime = new Date();
	}

	/**
	 * 导出人
	 */
	public void export() {

		this.setExportFlag(ConstantCode.BooleanFlag.TRUE);
		this.exportTime = new Date();
		this.updateTime = new Date();
	}

	/**
	 * 确认导入
	 */
	public void confirmImport(String opUser) {
		this.setStatusFlag(ConstantCode.StatusFlag.NEW);
		this.setUploadFlag(ConstantCode.UploadFlag.NEW);
		this.createTime = new Date();
	}

	/**
	 * 确认废弃
	 */
	public void confirmAbandon() {
		this.setStatusFlag(ConstantCode.StatusFlag.DISCARDED);
		this.setUploadFlag(ConstantCode.UploadFlag.NEW);
		this.updateTime = new Date();
	}

	/**
	 * 还原废弃违法
	 */
	public void restoreAbandon() {
		this.setStatusFlag(ConstantCode.StatusFlag.NEW);
		this.setUploadFlag(ConstantCode.UploadFlag.NEW);
		this.discardedTime = new Date(0);
		this.updateTime = new Date();
	}

	/**
	 * 录入锁定
	 * 
	 * @throws Exception
	 */
	public void lockForInput(String lockUser) throws Exception {
		if ("1".equals(this.lockFlag)) {
			throw new Exception("当前违法记录已被锁定,禁止继续录入!");
		}

		// 设置已锁定
		this.setLockFlag("1");
		this.lockTime = new Date();
		this.lockUser = lockUser;
	}

	/**
	 * 解除录入锁定
	 */
	public void unlockForInput() {
		// 设置未锁定
		this.setLockFlag("0");
		this.lockTime = new Date();
		this.lockUser = "";
	}
}
