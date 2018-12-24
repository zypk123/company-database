package cy.its.service.common.dataModel;

import java.util.Date;

/**
 * @Title: Violation.java
 * @Package cy.its.service.common.dataModel
 * @Description: 规范化后的违法
 * @author STJ lijun@cychina.cn
 * @date 2015年11月8日 下午9:34:04
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */
public class Violation extends Model {
	/**
	 * 车辆信息ID
	 */
	@Mapper("VEHICLE_REG_ID")
	private String vehicleRegId;

	/**
	 * 系统编号
	 */
	@Mapper("DEVICE_SYS_NBR")
	private String deviceSysNbr;
	
	/**
	 * 设备编号
	 */
	@Mapper("DEVICE_NBR")
	private String deviceNbr;

	/**
	 * 抓拍编号
	 */
	@Mapper("SNAP_NBR")
	private String snapNbr;

	/**
	 * 采集机关编号
	 */
	@Mapper("ORG_CODE")
	private String orgCode;
	
	@Mapper("ORG_AUTHORITY_CODE")
	private String orgAuthorityCode;

	/**
	 * 采集方式（013）。1：闯红灯设备 2：公路卡口设备 3：测速设备 4：闭路电视 5：移动摄像 6：警务通 7：区间测速 8：卫星定位装置
	 * 9：其它电子设备
	 */
	@Mapper("COLLECTION_TYPE")
	private String collectionType;

	/**
	 * 行政区划
	 */
	@Mapper("DISTRICT_CODE")
	private String districtCode;

	/**
	 * 违法地点代码
	 */
	@Mapper("VIO_SITE_CODE")
	private String vioSiteCode;

	/**
	 * 违法地点描述
	 */
	@Mapper("ADDRESS_DESC")
	private String addressDesc;

	/**
	 * 道路代码
	 */
	@Mapper("ROAD_CODE")
	private String roadCode;
	
	/**
	 * 路段代码
	 */
	@Mapper("ROAD_SECTION_CODE")
	private String roadSectionCode;
	
	/**
	 * 路口代码
	 */
	@Mapper("CROSS_CODE")
	private String crossCode;


	/**
	 * 所在路段米数
	 */
	@Mapper("MILEAGE")
	private int mileage;

	/**
	 * 方向类型（070）
	 */
	@Mapper("DIRECTION_TYPE")
	private String directionType;

	/**
	 * 方向名称
	 */
	@Mapper("DIRECTION_NAME")
	private String directionName;

	/**
	 * 车道编号
	 */
	@Mapper("LANE_NBR")
	private String laneNbr;

	/**
	 * 号牌号码
	 */
	@Mapper("PLATE_NBR")
	private String plateNbr;

	/**
	 * 号牌类型(002)
	 */
	@Mapper("PLATE_TYPE")
	private String plateType;

	/**
	 * 号牌颜色(003)
	 */
	@Mapper("PLATE_COLOR")
	private String plateColor;

	/**
	 * 违法时间
	 */
	@Mapper("VIOLATION_TIME")
	private Date violationTime;

	/**
	 * 违法类型(011)
	 */
	@Mapper("VIOLATION_TYPE")
	private String violationType;

	/**
	 * 违法行为代码
	 */
	@Mapper("VIOLATION_CODE")
	private String violationCode;

	/**
	 * 违法行为描述
	 */
	@Mapper("VIOLATION_DESC")
	private String violationDesc;

	/**
	 * 车速
	 */
	@Mapper("SPEED")
	private int speed;

	/**
	 * 大车限高速
	 */
	@Mapper("LIMIT_LARGE")
	private int limitLarge;

	/**
	 * 小车限高速
	 */
	@Mapper("LIMIT_SMALL")
	private int limitSmall;

	/**
	 * 限低速
	 */
	@Mapper("LIMIT_LOWER")
	private int limitLower;

	/**
	 * 超速比
	 */
	@Mapper("OVER_SPEED_PERCENT")
	private int overSpeedPercent;

	/**
	 * 区间入口地点代码
	 */
	@Mapper("ENTRY_SITE_CODE")
	private String entrySiteCode;

	/**
	 * 区间入口时间
	 */
	@Mapper("REGION_ENTRY_TIME")
	private Date regionEntryTime;

	/**
	 * 区间距离
	 */
	@Mapper("REGION_DISTANCE")
	private Long regionDistance;

	/**
	 * 红灯开始时间
	 */
	@Mapper("RED_LIGHT_BEGIN_TIME")
	private Date redLightBeginTime;

	/**
	 * 红灯结束时间
	 */
	@Mapper("RED_LIGHT_END_TIME")
	private Date redLightEndTime;

	/**
	 * 测速类别（309）。1：移动测速；2：区间测速；3：固定点测速；4：卡口线圈测速 5：卡口雷达测速
	 */
	@Mapper("SPEEDING_TYPE")
	private String speedingType;

	/**
	 * 特殊车辆类型（311）。1：套牌车；2：假牌车；3：白名单车；4：军警车；5：农用车；6：摩托车
	 * 
	 */
	@Mapper("SPECIAL_VEH_TYPE")
	private String specialVehType;

//	/**
//	 * 创建时间
//	 */
//	@Mapper("CREATE_TIME")
//	private Date createTime;
//
//	/**
//	 * 更新时间
//	 */
//	@Mapper("UPDATE_TIME")
//	private Date updateTime;

	/**
	 * 图片存储路径
	 */
	@Mapper("IMAGE")
	private String image;

	/**
	 * 视频存储路径
	 */
	@Mapper("VIDEO")
	private String video;

	/**
	 * 备注
	 */
	@Mapper("REMARK")
	private String remark;

	/**
	 * 归属地
	 */
	@Mapper("VEH_LOCALIZATION")
	private String vehLocalization;

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

	public String getDeviceNbr() {
		return deviceNbr;
	}

	public void setDeviceNbr(String deviceNbr) {
		this.deviceNbr = deviceNbr;
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
	
	public String getOrgAuthorityCode() {
		return orgAuthorityCode;
	}

	public void setOrgAuthorityCode(String orgAuthorityCode) {
		this.orgAuthorityCode = orgAuthorityCode;
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

	public String getCrossCode() {
		return crossCode;
	}

	public void setCrossCode(String crossCode) {
		this.crossCode = crossCode;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
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

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getLimitLarge() {
		return limitLarge;
	}

	public void setLimitLarge(int limitLarge) {
		this.limitLarge = limitLarge;
	}

	public int getLimitSmall() {
		return limitSmall;
	}

	public void setLimitSmall(int limitSmall) {
		this.limitSmall = limitSmall;
	}

	public int getLimitLower() {
		return limitLower;
	}

	public void setLimitLower(int limitLower) {
		this.limitLower = limitLower;
	}

	public int getOverSpeedPercent() {
		return overSpeedPercent;
	}

	public void setOverSpeedPercent(int overSpeedPercent) {
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

//	public Date getCreateTime() {
//		return createTime;
//	}
//
//	public void setCreateTime(Date createTime) {
//		this.createTime = createTime;
//	}
//
//	public Date getUpdateTime() {
//		return updateTime;
//	}
//
//	public void setUpdateTime(Date updateTime) {
//		this.updateTime = updateTime;
//	}

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
	
private String deviceId;
	
	private String sysComponentId;
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getSysComponentId() {
		return sysComponentId;
	}

	public void setSysComponentId(String sysComponentId) {
		this.sysComponentId = sysComponentId;
	}
	
	
}
