/**
 * 开发公司		安徽超远信息技术有限公司
 * 系统名			its
 * 子系统名      大数据平台
 *
 * @author  Xujin.Jiao
 * @version Version 1.0
 * 
 * 更新履历
 * 2015年6月5日: 创建
 */
package cy.its.vehTrack.mybatis.model;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import cy.its.com.domain.Criteria;
/**
 * 过车查询输入条件
 */
public class PassCriteriaBD implements Serializable {

	/**
	  * @Fields serialVersionUID : TODO（用一句话描述这个变量表示什么）
	  */
	private static final long serialVersionUID = 5971737935616728060L;

	/**
	 * 号牌号码
	 */
	private String plateNbr;

	/**
	 * 过车开始日期及时间
	 */
	private String startDateTime;
	
	/**
	 * 过车截止日期及时间
	 */
	private String endDateTime;
	
	/**
	 * 最小车速
	 */
	private String speedStart;
	
	/**
	 * 最大车速
	 */
	private String speedEnd;
	
	/**
	 * 机构代码
	 */
	private String orgCodes;
	
	/**
	 * 管辖机构代码
	 */
	private String orgAuthorityCode;

	/**
	 * 方向类型
	 */
	private String directType;
	
	/**
	 * 道路编码
	 */
	private String roadCodes;
	
	/**
	 * 点位编码
	 */
	private String siteCodes;

	/**
	 *车道号
	 */
	private String driveLane;
	
	/**
	 * 行政区划代码(007)
	 */
	private String districtCodes;
	
	/**
	 * (自定义)方向名称 <br />
	 * 过车查询页面未使用该条件，但仍然可以使用restful接口使用该条件
	 */
	private String directionName;
	
	/**
	 * 路口路段代码
	 */
	private String roadSectionCodes;
	
	/**
	 * 设备系统编号
	 */
	private String deviceSysNbr;
	
	/**
	 * 号牌类型(002)
	 */
	private String plateType;
	
	/**
	 * 号牌颜色(003)
	 */
	private String plateColor;

	/**
	 *通行方式(050)
	 */
	private String driveMode;
	
	/**
	 *车辆归属地分类(084)
	 */
	private String vehicleLocalization;

	/**
	 *车辆颜色(006)
	 */
	private String vehicleColor;

	/**
	 * 道路类型
	 */
	private String roadType;

	/**
	 *车辆品牌(005) 3位代码
	 */
	private String vehicleBrand;
	
	/**
	 *车辆子品牌(005) 2位代码
	 */
	private String vehicleSubBrand;
	
	/**
	 *车辆外形(004)
	 */
	private String vehicleShape;

	/**
	 * 参考国家机动车类型代码表：ga802、ga24.4。只能识别出机动车大类的记1位大类代码（h、k、m等），只能识别出大类和规格的记2位；全识别的记3位；
	 * 只能识别出机动车规格的记2位数字，第一位用0补齐（1：大车；2：中型车；3：小型车；4：微型车）；摩托车为m1或m2
	 */
	private String vehicleType;
	
	/**
	 * ga802-2008。参照稽查布控系统编码：0：未分析（系统补充）g:租赁 h:警用 i:消防 j:救护 k:工程救险 l:营转非 m:出租转非 
	 * n:教练 o:幼儿校车 p:小学生校车 q:初中生校车 s:中小学生校车 r:危化品运输 a:非营运 b:公路客运 c:公交客运 d:出租客运 e:旅游客运 f:货运
	 */
	private String vehCharcter;

	/**
	 * 要查询的字段 
	 */
	private String selectFields = "snap_nbr,device_nbr,image_url_path,vehicle_pass_id,plate_nbr,plate_type,plate_color,org_code,district_code,road_code,road_section_code,site_code,lane,direction_type,direction_name,speed,pass_time,drive_mode,vehicle_brand,vehicle_sub_brand,vehicle_color";
	
	/**
	 * 要查询的数据库类型：elasticsearch mongodb <br />
	 * 过车查询未使用该查询条件，但仍然可以使用restful接口使用该条件
	 */
	private String dbType;

	/**
	 * 要查询的进出城标示
	 */
	private String ertranceOrExits;
	
	/**
	 * 查询结果排序方式（按时间倒序还是顺序）
	 */
	private String orderType;
	
	/**
	 * 车辆分类。1-红名单车辆；2-大车
	 */
	private String vehCategory;
	
	/**
	 * 进出高速公路卡口标识。0-非进出高速公路卡口；1-进；2-出
	 */
	private String highwayAccessFlag;
	
	/**
	 * 进出服务区卡口标识。0-非进出服务区卡口；1-进；2-出
	 */
	private String serviceAreaAccessFlag;
	
	/**
	 * 页面大小
	 */
	private int pageSize;
	
	/**
	 * 当前页号
	 */
	private int pageNumber;
	
	
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * getter method
	 * @return the orderType
	 */
	public String getOrderType() {
		return orderType;
	}

	/**
	 * setter method
	 * @param orderType the orderType to set
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	/**
	 * @return the ertranceOrExits
	 */
	public String getErtranceOrExits() {
		return ertranceOrExits;
	}

	/**
	 * @param ertranceOrExits the ertranceOrExits to set
	 */
	public void setErtranceOrExits(String ertranceOrExits) {
		this.ertranceOrExits = ertranceOrExits;
	}

	/**
	 * @return the selectFields
	 */
	public String getSelectFields() {
		return selectFields;
	}

	/**
	 * @param selectFields the selectFields to set
	 */
	public void setSelectFields(String selectFields) {
		this.selectFields = selectFields;
	}

	/**
	 * @return the plateNbr
	 */
	public String getPlateNbr() {
		return plateNbr;
	}

	/**
	 * @param plateNbr the plateNbr to set
	 */
	public void setPlateNbr(String plateNbr) {
		this.plateNbr = plateNbr;
	}

	/**
	 * @return the startDateTime
	 */
	public String getStartDateTime() {
		return startDateTime;
	}

	/**
	 * @param startDateTime the startDateTime to set
	 */
	public void setStartDateTime(String startDateTime) {
		if (!StringUtils.isEmpty(startDateTime)) {
			switch(startDateTime.length())
			{
				case 10:
					startDateTime += " 00:00:00";
					break;
				case 13:
					startDateTime += " :00:00";
					break;
				case 16:
					startDateTime += " :00";
					break;
			}
		}
		this.startDateTime = startDateTime;
	}

	/**
	 * @return the endDateTime
	 */
	public String getEndDateTime() {
		return endDateTime;
	}

	/**
	 * @param endDateTime the endDateTime to set
	 */
	public void setEndDateTime(String endDateTime) {
		if (!StringUtils.isEmpty(endDateTime)) {
			switch(endDateTime.length())
			{
				case 10:
					endDateTime += " 23:59:59";
					break;
				case 13:
					endDateTime += " :59:59";
					break;
				case 16:
					endDateTime += " :59";
					break;
			}
		}
		this.endDateTime = endDateTime;
	}

	/**
	 * @return the speedStart
	 */
	public String getSpeedStart() {
		return speedStart;
	}

	/**
	 * @param speedStart the speedStart to set
	 */
	public void setSpeedStart(String speedStart) {
		this.speedStart = speedStart;
	}

	/**
	 * @return the speedEnd
	 */
	public String getSpeedEnd() {
		return speedEnd;
	}

	/**
	 * @param speedEnd the speedEnd to set
	 */
	public void setSpeedEnd(String speedEnd) {
		this.speedEnd = speedEnd;
	}

	/**
	 * @return the directType
	 */
	public String getDirectType() {
		return directType;
	}

	/**
	 * @param directType the directType to set
	 */
	public void setDirectType(String directType) {
		this.directType = directType;
	}

	/**
	 * @return the driveLane
	 */
	public String getDriveLane() {
		return driveLane;
	}

	/**
	 * @param driveLane the driveLane to set
	 */
	public void setDriveLane(String driveLane) {
		this.driveLane = driveLane;
	}

	/**
	 * @return the orgCodes
	 */
	public String getOrgCodes() {
		return orgCodes;
	}

	/**
	 * @param orgCodes the orgCodes to set
	 */
	public void setOrgCodes(String orgCodes) {
		this.orgCodes = orgCodes;
	}

	/**
	 * getter method
	 * @return the orgAuthorityCode
	 */
	public String getOrgAuthorityCode() {
		return orgAuthorityCode;
	}

	/**
	 * setter method
	 * @param orgAuthorityCode the orgAuthorityCode to set
	 */
	public void setOrgAuthorityCode(String orgAuthorityCode) {
		this.orgAuthorityCode = orgAuthorityCode;
	}

	/**
	 * @return the roadCodes
	 */
	public String getRoadCodes() {
		return roadCodes;
	}

	/**
	 * @param roadCodes the roadCodes to set
	 */
	public void setRoadCodes(String roadCodes) {
		this.roadCodes = roadCodes;
	}

	/**
	 * @return the siteCodes
	 */
	public String getSiteCodes() {
		
		return siteCodes;
	}

	/**
	 * @param siteCodes the siteCodes to set
	 */
	public void setSiteCodes(String siteCodes) {
		this.siteCodes = siteCodes;
	}

	/**
	 * @return the districtCodes
	 */
	public String getDistrictCodes() {
		return districtCodes;
	}

	/**
	 * @param districtCodes the districtCodes to set
	 */
	public void setDistrictCodes(String districtCodes) {
		this.districtCodes = districtCodes;
	}

	/**
	 * @return the directionName
	 */
	public String getDirectionName() {
		return directionName;
	}

	/**
	 * @param directionName the directionName to set
	 */
	public void setDirectionName(String directionName) {
		this.directionName = directionName;
	}

	/**
	 * getter method
	 * @return the roadSectionCodes
	 */
	public String getRoadSectionCodes() {
		return roadSectionCodes;
	}

	/**
	 * setter method
	 * @param roadSectionCodes the roadSectionCodes to set
	 */
	public void setRoadSectionCodes(String roadSectionCodes) {
		this.roadSectionCodes = roadSectionCodes;
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

	/**
	 * @return the plateType
	 */
	public String getPlateType() {
		return plateType;
	}

	/**
	 * @param plateType the plateType to set
	 */
	public void setPlateType(String plateType) {
		this.plateType = plateType;
	}

	/**
	 * @return the plateColor
	 */
	public String getPlateColor() {
		return plateColor;
	}

	/**
	 * @param plateColor the plateColor to set
	 */
	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}

	/**
	 * @return the driveMode
	 */
	public String getDriveMode() {
		return driveMode;
	}

	/**
	 * @param driveMode the driveMode to set
	 */
	public void setDriveMode(String driveMode) {
		this.driveMode = driveMode;
	}

	/**
	 * @return the vehicleLocalization
	 */
	public String getVehicleLocalization() {
		return vehicleLocalization;
	}

	/**
	 * @param vehicleLocalization the vehicleLocalization to set
	 */
	public void setVehicleLocalization(String vehicleLocalization) {
		this.vehicleLocalization = vehicleLocalization;
	}

	/**
	 * @return the vehicleColor
	 */
	public String getVehicleColor() {
		return vehicleColor;
	}

	/**
	 * @param vehicleColor the vehicleColor to set
	 */
	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}

	/**
	 * @return the roadType
	 */
	public String getRoadType() {
		return roadType;
	}

	/**
	 * @param roadType the roadType to set
	 */
	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}

	/**
	 * @return the vehicleBrand
	 */
	public String getVehicleBrand() {
		return vehicleBrand;
	}

	/**
	 * @param vehicleBrand the vehicleBrand to set
	 */
	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}

	/**
	 * @return the vehicleSubBrand
	 */
	public String getVehicleSubBrand() {
		return vehicleSubBrand;
	}

	/**
	 * @param vehicleSubBrand the vehicleSubBrand to set
	 */
	public void setVehicleSubBrand(String vehicleSubBrand) {
		this.vehicleSubBrand = vehicleSubBrand;
	}

	/**
	 * @return the vehicleShape
	 */
	public String getVehicleShape() {
		return vehicleShape;
	}

	/**
	 * @param vehicleShape the vehicleShape to set
	 */
	public void setVehicleShape(String vehicleShape) {
		this.vehicleShape = vehicleShape;
	}

	/**
	 * @return the vehicleType
	 */
	public String getVehicleType() {
		return vehicleType;
	}

	/**
	 * @param vehicleType the vehicleType to set
	 */
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	/**
	 * @return the vehCharcter
	 */
	public String getVehCharcter() {
		return vehCharcter;
	}

	/**
	 * @param vehCharcter the vehCharcter to set
	 */
	public void setVehCharcter(String vehCharcter) {
		this.vehCharcter = vehCharcter;
	}

	/**
	 * @return the dbType
	 */
	public String getDbType() {
		return dbType;
	}

	/**
	 * @param dbType the dbType to set
	 */
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	/**
	 * getter method
	 * @return the vehCategory
	 */
	public String getVehCategory() {
		return vehCategory;
	}

	/**
	 * setter method
	 * @param vehCategory the vehCategory to set
	 */
	public void setVehCategory(String vehCategory) {
		this.vehCategory = vehCategory;
	}

	/**
	 * getter method
	 * @return the highwayAccessFlag
	 */
	public String getHighwayAccessFlag() {
		return highwayAccessFlag;
	}

	/**
	 * setter method
	 * @param highwayAccessFlag the highwayAccessFlag to set
	 */
	public void setHighwayAccessFlag(String highwayAccessFlag) {
		this.highwayAccessFlag = highwayAccessFlag;
	}

	/**
	 * getter method
	 * @return the serviceAreaAccessFlag
	 */
	public String getServiceAreaAccessFlag() {
		return serviceAreaAccessFlag;
	}

	/**
	 * setter method
	 * @param serviceAreaAccessFlag the serviceAreaAccessFlag to set
	 */
	public void setServiceAreaAccessFlag(String serviceAreaAccessFlag) {
		this.serviceAreaAccessFlag = serviceAreaAccessFlag;
	}

}
