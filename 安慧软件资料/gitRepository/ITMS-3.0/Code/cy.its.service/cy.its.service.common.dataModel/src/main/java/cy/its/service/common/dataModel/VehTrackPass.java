package cy.its.service.common.dataModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Title: VehTrackPass.java
 * @Package cy.its.service.common.dataModel
 * @Description: 规范化后的过车
 * @author STJ lijun@cychina.cn
 * @date 2015年11月8日 下午9:33:34
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */
public class VehTrackPass extends Model {

	// /**
	// * 过车ID
	// */
	// private String vehiclePassId;	
	

	/**
	 * 抓拍编号
	 */
	@Mapper("SNAP_NBR")
	private String snapNbr;

	/**
	 * 系统编号
	 */
	@Mapper("DEVICE_SYS_NBR")
	private String deviceSysNbr;

	/**
	 * 行政区划(007)
	 */
	@Mapper("DISTRICT_CODE")
	private String districtCode;

	/**
	 * 点位代码
	 */
	@Mapper("SITE_CODE")
	private String siteCode;

	/**
	 * 道路代码
	 */
	@Mapper("ROAD_CODE")
	private String roadCode;

	/**
	 * 道路类型
	 */
	@Mapper("ROAD_TYPE")
	private String roadType;

	/**
	 * 路段代码
	 */
	@Mapper("ROAD_SECTION_CODE")
	private String roadSectionCode;

	/**
	 * 前端设备上传的自定义方向名称
	 */
	@Mapper("DIRECTION_NAME")
	private String directionName;

	/**
	 * 方向类型（070）
	 */
	@Mapper("DIRECTION_TYPE")
	private String directionType;

	/**
	 * 车道号
	 */
	@Mapper("LANE")
	private String lane;

	/**
	 * 号牌号码
	 */
	@Mapper("PLATE_NBR")
	private String plateNbr;

	/**
	 * 号牌颜色(003)
	 */
	@Mapper("PLATE_COLOR")
	private String plateColor;

	/**
	 * 号牌类型(002)
	 */
	@Mapper("PLATE_TYPE")
	private String plateType;

	/**
	 * 尾牌号码
	 */
	@Mapper("TAIL_PLATE_NBR")
	private String tailPlateNbr;

	/**
	 * 尾牌颜色(003)
	 */
	@Mapper("TAIL_PLATE_COLOR")
	private String tailPlateColor;

	/**
	 * 车辆品牌
	 */
	@Mapper("VEHICLE_BRAND")
	private String vehicleBrand;

	/**
	 * 车辆子品牌
	 */
	@Mapper("VEHICLE_SUB_BRAND")
	private String vehicleSubBrand;

	/**
	 * 车辆类型。参考国家机动车类型代码表：GA802、GA24.4。只能识别出机动车大类的记1位大类代码（H、K、M等），只能识别出大类和规格的记2位；
	 * 全识别的记3位；只能识别出机动车规格的记2位数字，第一位用0补齐（1：大车；2：中型车；3：小型车；4：微型车）；摩托车为M1或M2
	 */
	@Mapper("VEHICLE_TYPE")
	private String vehicleType;

	/**
	 * 车辆使用性质。GA802-2008。参照稽查布控系统编码： 0：未分析（系统补充） G 租赁 H 警用 I 消防 J 救护 K 工程救险 L
	 * 营转非 M 出租转非 N 教练 O 幼儿校车 P 小学生校车 Q 初中生校车 S 中小学生校车 R 危化品运输 A 非营运 B 公路客运 C
	 * 公交客运 D 出租客运 E 旅游客运 F 货运
	 */
	@Mapper("VEH_CHARCTER")
	private String vehCharcter;

	/**
	 * 车辆外形
	 */
	@Mapper("VEHICLE_SHAPE")
	private String vehicleShape;

	/**
	 * 车长
	 */
	@Mapper("VEHICLE_LENGTH")
	private BigDecimal vehicleLength;

	/**
	 * 车身颜色(006)
	 */
	@Mapper("VEHICLE_COLOR")
	private String vehicleColor;

	/**
	 * 过车时间
	 */
	@Mapper("PASS_TIME")
	private Date passTime;

	/**
	 * 车速
	 */
	@Mapper("SPEED")
	private int speed;

	/**
	 * 通行方式(050)
	 */
	@Mapper("DRIVE_MODE")
	private String driveMode;

	/**
	 * 归属地。1-本市；2-本省；3-外省；4-军车；5-警车
	 */
	@Mapper("VEH_LOCALIZATION")
	private String vehLocalization;

	/**
	 * 车辆分类。1-红名单车辆；2-大车
	 */
	@Mapper("VEH_CATEGORY")
	private String vehCategory;

	/**
	 * 机构代码
	 */
	@Mapper("ORG_CODE")
	private String orgCode;

	/**
	 * 管辖机构权限代码
	 */
	@Mapper("ORG_AUTHORITY_CODE")
	private String orgAuthorityCode;

	/**
	 * 图片存储路径
	 */
	@Mapper("IMAGE_URL_PATH")
	private String imageUrlPath;

	/**
	 * 车尾图片存储路径
	 */
	@Mapper("IMAGE_URL1")
	private String imageUrl1;

	/**
	 * 车辆特征图片存储路径
	 */
	@Mapper("IMAGE_URL2")
	private String imageUrl2;

	/**
	 * 人脸图像存储路径
	 */
	@Mapper("IMAGE_URL3")
	private String imageUrl3;

	/**
	 * 特征图片位置
	 */
	@Mapper("VEHICLE_PLATE_PLACE")
	private String vehiclePlatePlace;

	/**
	 * 人脸图像位置
	 */
	@Mapper("FACE_PLACE")
	private String facePlace;

	/**
	 * 传输延时
	 */
	@Mapper("TRANSFER_DELAY")
	private long transferDelay;

	/**
	 * 创建时间
	 */
//	@Mapper("CREATE_TIME")
//	private Date createTime;

	/**
	 * 进出高速公路卡口标识。0-非进出高速公路卡口；1-进；2-出
	 */
	@Mapper("HIGHWAY_ACCESS_FLAG")
	private String highwayAccessFlag;

	/**
	 * 进出服务区卡口标识。0-非进出服务区卡口；1-进；2-出
	 */
	@Mapper("SERVICE_AREA_ACCESS_FLAG")
	private String serviceAreaAccessFlag;

	/**
	 * 进出城方向标记。1-进城；2-出城
	 */
	@Mapper("ENTRANCE_OR_EXIT")
	private String ertranceOrExit;

	/**
	 * 设备编号。相机等部件的编号，具有唯一性，否则无法进行监控。
	 */
	@Mapper("DEVICE_NBR")
	private String deviceNbr;

	private String deviceKey;
	// public String getVehiclePassId() {
	// return vehiclePassId;
	// }
	//
	// public void setVehiclePassId(String vehiclePassId) {
	// this.vehiclePassId = vehiclePassId;
	// }

	public String getSnapNbr() {
		return snapNbr;
	}

	public void setSnapNbr(String snapNbr) {
		this.snapNbr = snapNbr;
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

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getRoadCode() {
		return roadCode;
	}

	public void setRoadCode(String roadCode) {
		this.roadCode = roadCode;
	}

	public String getRoadType() {
		return roadType;
	}

	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}

	public String getRoadSectionCode() {
		return roadSectionCode;
	}

	public void setRoadSectionCode(String roadSectionCode) {
		this.roadSectionCode = roadSectionCode;
	}

	public String getDirectionName() {
		return directionName;
	}

	public void setDirectionName(String directionName) {
		this.directionName = directionName;
	}

	public String getDirectionType() {
		return directionType;
	}

	public void setDirectionType(String directionType) {
		this.directionType = directionType;
	}

	public String getLane() {
		return lane;
	}

	public void setLane(String lane) {
		this.lane = lane;
	}

	public String getPlateNbr() {
		return plateNbr;
	}

	public void setPlateNbr(String plateNbr) {
		this.plateNbr = plateNbr;
	}

	public String getPlateColor() {
		return plateColor;
	}

	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}

	public String getPlateType() {
		return plateType;
	}

	public void setPlateType(String plateType) {
		this.plateType = plateType;
	}

	public String getTailPlateNbr() {
		return tailPlateNbr;
	}

	public void setTailPlateNbr(String tailPlateNbr) {
		this.tailPlateNbr = tailPlateNbr;
	}

	public String getTailPlateColor() {
		return tailPlateColor;
	}

	public void setTailPlateColor(String tailPlateColor) {
		this.tailPlateColor = tailPlateColor;
	}

	public String getVehicleBrand() {
		return vehicleBrand;
	}

	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}

	public String getVehicleSubBrand() {
		return vehicleSubBrand;
	}

	public void setVehicleSubBrand(String vehicleSubBrand) {
		this.vehicleSubBrand = vehicleSubBrand;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVehCharcter() {
		return vehCharcter;
	}

	public void setVehCharcter(String vehCharcter) {
		this.vehCharcter = vehCharcter;
	}

	public String getVehicleShape() {
		return vehicleShape;
	}

	public void setVehicleShape(String vehicleShape) {
		this.vehicleShape = vehicleShape;
	}

	public BigDecimal getVehicleLength() {
		return vehicleLength;
	}

	public void setVehicleLength(BigDecimal vehicleLength) {
		this.vehicleLength = vehicleLength;
	}

	public String getVehicleColor() {
		return vehicleColor;
	}

	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}

	public Date getPassTime() {
		return passTime;
	}

	public void setPassTime(Date passTime) {
		this.passTime = passTime;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String getDriveMode() {
		return driveMode;
	}

	public void setDriveMode(String driveMode) {
		this.driveMode = driveMode;
	}

	public String getVehLocalization() {
		return vehLocalization;
	}

	public void setVehLocalization(String vehLocalization) {
		this.vehLocalization = vehLocalization;
	}

	public String getVehCategory() {
		return vehCategory;
	}

	public void setVehCategory(String vehCategory) {
		this.vehCategory = vehCategory;
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

	public String getImageUrlPath() {
		return imageUrlPath;
	}

	public void setImageUrlPath(String imageUrlPath) {
		this.imageUrlPath = imageUrlPath;
	}

	public String getImageUrl1() {
		return imageUrl1;
	}

	public void setImageUrl1(String imageUrl1) {
		this.imageUrl1 = imageUrl1;
	}

	public String getImageUrl2() {
		return imageUrl2;
	}

	public void setImageUrl2(String imageUrl2) {
		this.imageUrl2 = imageUrl2;
	}

	public String getImageUrl3() {
		return imageUrl3;
	}

	public void setImageUrl3(String imageUrl3) {
		this.imageUrl3 = imageUrl3;
	}

	public String getVehiclePlatePlace() {
		return vehiclePlatePlace;
	}

	public void setVehiclePlatePlace(String vehiclePlatePlace) {
		this.vehiclePlatePlace = vehiclePlatePlace;
	}

	public String getFacePlace() {
		return facePlace;
	}

	public void setFacePlace(String facePlace) {
		this.facePlace = facePlace;
	}

	public long getTransferDelay() {
		return transferDelay;
	}

	public void setTransferDelay(long transferDelay) {
		this.transferDelay = transferDelay;
	}

//	public Date getCreateTime() {
//		return createTime;
//	}
//
//	public void setCreateTime(Date createTime) {
//		this.createTime = createTime;
//	}

	public String getHighwayAccessFlag() {
		return highwayAccessFlag;
	}

	public void setHighwayAccessFlag(String highwayAccessFlag) {
		this.highwayAccessFlag = highwayAccessFlag;
	}

	public String getServiceAreaAccessFlag() {
		return serviceAreaAccessFlag;
	}

	public void setServiceAreaAccessFlag(String serviceAreaAccessFlag) {
		this.serviceAreaAccessFlag = serviceAreaAccessFlag;
	}

	public String getErtranceOrExit() {
		return ertranceOrExit;
	}

	public void setErtranceOrExit(String ertranceOrExit) {
		this.ertranceOrExit = ertranceOrExit;
	}

	public String getDeviceKey() {
		return deviceKey;
	}

	public void setDeviceKey(String deviceKey) {
		this.deviceKey = deviceKey;
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