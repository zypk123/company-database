package cy.its.vehTrack.rest.dto;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.omg.CORBA.portable.CustomValue;

import cy.its.com.constant.ConstValue;
import cy.its.com.dto.BaseDto;
import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.vehTrack.domain.criteria.PassCriteria;
import cy.its.vehTrack.domain.model.PassInfo;

public class PassDto extends BaseDto{
	
	/**
	 * 转成领域模型对象
	 * @return
	 */
	public PassInfo converToModel(){
		PassInfo passInfo = new PassInfo();
		ObjectMapUtils.parseObject(passInfo, this);
		return passInfo;
	}
	
	
	/**
	 * 转化为领域查询对象
	 * @return
	 * @throws ParseException 
	 */
	public PassCriteria convertToCriteria() throws ParseException{
		PassCriteria criteria = new PassCriteria();
		ObjectMapUtils.parseObject(criteria, this);
		if(!StringUtil.isNullOrEmpty(this.distritCodes)){
			criteria.setDistritCodes(distritCodes.split(","));
		}
		if(!StringUtil.isNullOrEmpty(this.roadCodes)){
			criteria.setRoadCodes(roadCodes.split(","));
		}
		if(!StringUtil.isNullOrEmpty(this.siteCodes)){
			criteria.setSiteCodes(siteCodes.split(","));
		}
		if(!StringUtil.isNullOrEmpty(this.speedStart)){
			criteria.setSpeedStart(this.speedStart);;
		}
		if(!StringUtil.isNullOrEmpty(this.speedEnd)){
			criteria.setSpeedEnd(this.speedEnd);
		}
		//设置当前用户机构权限
		criteria.setOrgPrvCode(this.getCurrentOrgPrivilegeCode());
		criteria.setPageNum(this.getPageNumber());
		criteria.setPageSize(this.getPageSize());
		criteria.setTotal(this.getTotalCount());
		return criteria;
	}
	
	/**
	 * 默认无参构造函数
	 */
	public PassDto(){
		
	}
	
	/**
	 * 领域模型转为为DTO
	 * @param passInfo
	 */
	public PassDto(PassInfo passInfo){
		ObjectMapUtils.parseObject(this, passInfo);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.passTime = formatter.format(passInfo.getPassTime());
		this.transferDelay = String.valueOf(passInfo.getTransferDelay());
		this.vehicleLength = String.valueOf(passInfo.getVehicleLength());
	}
	
	/**
	
	  * 创建一个新的实例 PassDto. 
	  * <p>Title: </p>
	  * <p>Description: </p>
	  * @param plateNbr 车牌号
	  * @param passTimeStart 开始时间
	  * @param passTimeEnd 结束时间
	  * @param searchType 查找类型
	  */
	
	public PassDto(String plateNbr, String passTimeStart, String passTimeEnd, String searchType,
			int pageSize, int pageNumber,String plateType) {
		this.plateNbr = plateNbr;
		this.plateType = plateType;
		this.setPageSize(pageSize);
		this.setPageNumber(pageNumber);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		if(ConstValue.SEARCH_TIME_TYPE_LAST_2_HOUR.equals(searchType)){
			//最近2小时
			this.passTimeStart = formatter.format(new Date(now.getTime() - (2L*3600L*1000L)));
			this.passTimeEnd = formatter.format(now);
		}else if(ConstValue.SEARCH_TIME_TYPE_LAST_1_DAY.equals(searchType)){
			//最近一天
			this.passTimeStart = formatter.format(new Date(now.getTime() - (24L*3600L*1000L)));
			this.passTimeEnd = formatter.format(now);
		}else if(ConstValue.SEARCH_TIME_TYPE_LAST_1_WEEK.equals(searchType)){
			//最近一天
			this.passTimeStart = formatter.format(new Date(now.getTime() - (7L*24L*3600L*1000L)));
			this.passTimeEnd = formatter.format(now);
		}else{
			this.passTimeStart = passTimeStart;
			this.passTimeEnd = passTimeEnd;
		}
	}

	/**
	 * 行政区划编码
	 */
	private String distritCode;
	
	/**
	 * 行政区划编码
	 */
	private String distritCodes;

	/**
	 * 道路类型编码
	 */
	private String roadType;

	/**
	 * 道路编码
	 */
	private String roadCodes;

	/**
	 * 点位编码
	 */
	private String siteCodes;

	/**
	 * 方向
	 */
	private String direction;

	/**
	 * 车道
	 */
	private String lane;

	/**
	 * 过车时间开始
	 */
	private String passTimeStart;

	/**
	 * 过车时间结束
	 */
	private String passTimeEnd;

	/**
	 * 车牌号码
	 */
	private String plateNbr;

	/**
	 * 车牌颜色
	 */
	private String plateColor;

	/**
	 * 车辆类型
	 */
	private String vehicleType;

	/**
	 * 车身颜色
	 */
	private String vehicleColor;

	/**
	 * 车辆品牌
	 */
	private String vehicleBrand;

	/**
	 * 车辆归属地
	 */
	private String vehLocalization;

	/**
	 * 最小速度
	 */
	private String speedStart;
	/**
	 * 最大速度
	 */
	private String speedEnd;
	// 过车信息ID，唯一主键
	private String vehiclePassId;

	// 抓拍编号
	private String snapNbr;

	// 设备编号
	private String deviceNbr;

	// 行政区划编码
	private String districtCode;

	// 点位编码
	private String siteCode;

	// 道路编码
	private String roadCode;

	// 路口\路段代码
	private String junctionCode;

	// 方向名称
	private String directionName;

	// 方向类型
	private String directionType;

	// 车牌种类
	private String plateType;

	// 尾牌号码
	private String tailPlateNbr;

	// 尾牌颜色
	private String tailPlateColor;

	// 车牌子品牌
	private String vehicleSubBrand;

	// 车辆外形
	private String vehCharcter;

	// 车辆形状
	private String vehicleShape;

	// 车辆长度
	private String vehicleLength;

	// 过车时间
	private String passTime;

	// 速度
	private Integer speed;

	// 车型方式
	private String driveMode;

	// 车辆类别 1红名单2大车
	private String vehCategory;

	// 所属机构编码
	private String orgCode;

	// 机构权限编码
	private String orgAuthorityCode;

	// 过车图片路径
	private String imageUrlPath;

	// 车尾图片路径
	private String imageUrl1;

	// 车辆特征图片路径
	private String imageUrl2;

	// 人脸特征图片路径
	private String imageUrl3;

	// 特征图片位置
	private String vehiclePlatePlace;

	// 人脸图片位置
	private String facePlace;

	// 数据传输延迟
	private String transferDelay;

	// 创建时间
	private String createTime;

	// 进出高速公路卡口标识
	private String highwayAccessFlag;

	// 进出服务区卡口标识
	private String serviceAreaAccessFlag;

	// 进城或出城
	private String ertranceOrExit;
	
	// 点位名称
	private String siteName;
	
	// 点位经度
	private BigDecimal siteLon;
	
	// 点位纬度
	private BigDecimal siteLat;
	
	//是否仅仅搜索无牌车（号牌号码长度小于7） 值：true 或 false，默认为false
	private String justSearchNullPlate;
	
	//外地车查询标识 值：true 或 false，默认为false。当为true时，车牌号牌参数应为本地车号牌前缀，如：皖A* 将查询号牌不是皖A开头车辆的过车记录。
	private String justNonlocalVeh;
	//首次出现车辆查询标识 值：true 或 false，默认为false。当为true时，仅查询那些在指定行政区划内首次出现车辆的过车记录（[一星期]以前从来没在指定行政区划出现过）。仅Impala支持。
	private String justFirstApear;
	
	//首次出现车辆查询的新近时间界定值，当首次出现时间不小于该时间时，认为新近出现。 格式：yyyy-MM-dd HH:mm:ss
	private String firstTime;
	//首次出现车辆查询的“首现行政区划”代码，比如：要查询的查辆为在合肥市首次出现的车辆，但是本次过车查询的范围却限定在蜀山区。此值填合肥市的行政区划代码：340100
	private String firstAppearDistrictCodes;
	//排序字段及排序方式组合，如：plate_type desc, vehicle_brand, vehicle_color desc, pass_time asc 若该值不为空，[orderType]条件将失效。
	private String orderFields;
	//查询结果排序方式（按过车时间倒序还是顺序，值"asc"或"desc"）
	private String orderType;
	
	private String dbType;
	
	private long totalCount;
	
	
	//活动规律flag
	private boolean activeFlag;

	public BigDecimal getSiteLon() {
		return siteLon;
	}


	public void setSiteLon(BigDecimal siteLon) {
		this.siteLon = siteLon;
	}


	public BigDecimal getSiteLat() {
		return siteLat;
	}


	public void setSiteLat(BigDecimal siteLat) {
		this.siteLat = siteLat;
	}


	/**
	 * getter method
	 * @return the siteName
	 */
	
	public String getSiteName() {
		return siteName;
	}


	/**
	 * setter method
	 * @param siteName the siteName to set
	 */
	
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}


	public String getDistritCodes() {
		return distritCodes;
	}




	public void setDistritCodes(String distritCodes) {
		this.distritCodes = distritCodes;
	}


	public String getDistritCode() {
		return distritCode;
	}

	public void setDistritCode(String distritCode) {
		this.distritCode = distritCode;
	}

	public String getRoadType() {
		return roadType;
	}

	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}

	public String getRoadCodes() {
		return roadCodes;
	}

	public void setRoadCodes(String roadCodes) {
		this.roadCodes = roadCodes;
	}

	public String getSiteCodes() {
		return siteCodes;
	}

	public void setSiteCodes(String siteCodes) {
		this.siteCodes = siteCodes;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getLane() {
		return lane;
	}

	public void setLane(String lane) {
		this.lane = lane;
	}

	public String getPassTimeStart() {
		return passTimeStart;
	}

	public void setPassTimeStart(String passTimeStart) {
		this.passTimeStart = passTimeStart;
	}

	public String getPassTimeEnd() {
		return passTimeEnd;
	}

	public void setPassTimeEnd(String passTimeEnd) {
		this.passTimeEnd = passTimeEnd;
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

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVehicleColor() {
		return vehicleColor;
	}

	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}

	public String getVehicleBrand() {
		return vehicleBrand;
	}

	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}

	public String getVehLocalization() {
		return vehLocalization;
	}

	public void setVehLocalization(String vehLocalization) {
		this.vehLocalization = vehLocalization;
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

	public String getJunctionCode() {
		return junctionCode;
	}

	public void setJunctionCode(String junctionCode) {
		this.junctionCode = junctionCode;
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

	public String getVehicleSubBrand() {
		return vehicleSubBrand;
	}

	public void setVehicleSubBrand(String vehicleSubBrand) {
		this.vehicleSubBrand = vehicleSubBrand;
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

	public String getVehicleLength() {
		return vehicleLength;
	}

	public void setVehicleLength(String vehicleLength) {
		this.vehicleLength = vehicleLength;
	}

	public String getPassTime() {
		return passTime;
	}

	public void setPassTime(String passTime) {
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

	public String getTransferDelay() {
		return transferDelay;
	}

	public void setTransferDelay(String transferDelay) {
		this.transferDelay = transferDelay;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
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


	public boolean isActiveFlag() {
		return activeFlag;
	}


	public void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
	}


	public String getJustSearchNullPlate() {
		return justSearchNullPlate;
	}


	public void setJustSearchNullPlate(String justSearchNullPlate) {
		this.justSearchNullPlate = justSearchNullPlate;
	}


	public String getJustNonlocalVeh() {
		return justNonlocalVeh;
	}


	public void setJustNonlocalVeh(String justNonlocalVeh) {
		this.justNonlocalVeh = justNonlocalVeh;
	}


	public String getJustFirstApear() {
		return justFirstApear;
	}


	public void setJustFirstApear(String justFirstApear) {
		this.justFirstApear = justFirstApear;
	}


	public String getFirstTime() {
		return firstTime;
	}


	public void setFirstTime(String firstTime) {
		this.firstTime = firstTime;
	}


	public String getFirstAppearDistrictCodes() {
		return firstAppearDistrictCodes;
	}


	public void setFirstAppearDistrictCodes(String firstAppearDistrictCodes) {
		this.firstAppearDistrictCodes = firstAppearDistrictCodes;
	}


	public String getOrderFields() {
		return orderFields;
	}


	public void setOrderFields(String orderFields) {
		this.orderFields = orderFields;
	}


	public String getDbType() {
		return dbType;
	}


	public void setDbType(String dbType) {
		this.dbType = dbType;
	}


	public String getSpeedStart() {
		return speedStart;
	}


	public void setSpeedStart(String speedStart) {
		this.speedStart = speedStart;
	}


	public String getSpeedEnd() {
		return speedEnd;
	}


	public void setSpeedEnd(String speedEnd) {
		this.speedEnd = speedEnd;
	}


	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	
	
}
