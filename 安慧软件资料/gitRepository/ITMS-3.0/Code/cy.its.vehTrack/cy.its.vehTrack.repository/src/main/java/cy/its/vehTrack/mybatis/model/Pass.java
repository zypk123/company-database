package cy.its.vehTrack.mybatis.model;

import java.math.BigDecimal;
import java.util.Date;

import cy.its.com.util.ObjectMapUtils;
import cy.its.vehTrack.domain.model.PassInfo;

public class Pass {
	//过车Id
	private String vehiclePassId;

	//抓拍编号
    private String snapNbr;
    
    //设备系统编号
    private String deviceSysNbr;

    //设备编号
    private String deviceNbr;

    //行政区划代码
    private String districtCode;

    //点位代码
    private String siteCode;

    //道路代码
    private String roadCode;

    //道路类型
    private String roadType;

    //路段代码
    private String roadSectionCode;

    //方向名称
    private String directionName;

    //方向类型
    private String directionType;

    //车道号
    private String lane;

    //号牌号码
    private String plateNbr;

    //号牌颜色
    private String plateColor;

    //号牌类型
    private String plateType;

    //尾牌号码
    private String tailPlateNbr;

    //尾牌颜色
    private String tailPlateColor;

    //车辆品牌
    private String vehicleBrand;

    //车辆子品牌
    private String vehicleSubBrand;

    //车辆类型
    private String vehicleType;

    //车辆使用类型
    private String vehCharcter;

    //车辆形状
    private String vehicleShape;

    //车长
    private BigDecimal vehicleLength;

    //车辆颜色
    private String vehicleColor;

    //过车时间
    private Date passTime;

    //速度
    private Integer speed;

    //通行方式
    private String driveMode;

    //归属地
    private String vehLocalization;

    //车辆分类
    private String vehCategory;

    //机构代码
    private String orgCode;

    //机构权限代码
    private String orgAuthorityCode;

    //图片存储路径
    private String imageUrlPath;

    //车尾图片存储路径
    private String imageUrl1;

    //车辆特征图片路径
    private String imageUrl2;

    //人脸图像存储路径
    private String imageUrl3;

    //特征图片位置
    private String vehiclePlatePlace;

    //人脸图像位置
    private String facePlace;

    //传输延迟
    private Long transferDelay;

    //创建时间
    private Date createTime;

    //进出高速公路卡口标识
    private String highwayAccessFlag;

    //进出服务器卡口标识
    private String serviceAreaAccessFlag;

    //进出城方向标记
    private String ertranceOrExit;
    
    public PassInfo convertToModel(){
    	PassInfo passInfo = new PassInfo();
    	ObjectMapUtils.parseObject(passInfo, this);
    	return passInfo;
    }
    
    /**
	 * getter method
	 * @return the deviceSysNbr
	 */
	
	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}

	/**
	 * setter method
	 * @param deviceSysNbr the deviceSysNbr to set
	 */
	
	public void setDeviceSysNbr(String deviceSysNbr) {
		this.deviceSysNbr = deviceSysNbr;
	}

	public String getVehiclePassId() {
        return vehiclePassId;
    }

    public void setVehiclePassId(String vehiclePassId) {
        this.vehiclePassId = vehiclePassId;
    }

    public String getSnapNbr() {
        return snapNbr;
    }

    public void setSnapNbr(String snapNbr) {
        this.snapNbr = snapNbr;
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

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
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

    public Long getTransferDelay() {
        return transferDelay;
    }

    public void setTransferDelay(Long transferDelay) {
        this.transferDelay = transferDelay;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

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
}