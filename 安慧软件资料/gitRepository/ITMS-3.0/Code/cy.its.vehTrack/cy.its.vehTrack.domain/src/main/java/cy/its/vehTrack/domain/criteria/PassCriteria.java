package cy.its.vehTrack.domain.criteria;

import cy.its.com.domain.Criteria;

public class PassCriteria extends Criteria{
	/**
	 * 行政区划编码
	 */
	private String[] distritCodes;
	
	/**
	 * 道路类型编码
	 */
	private String roadType;
	
	/**
	 * 道路编码
	 */
	private String[] roadCodes;
	
	/**
	 * 点位编码
	 */
	private String[] siteCodes;
	
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
	 * 机构权限代码
	 */
	private String orgPrvCode;
	
	/**
	 * 最小速度
	 */
	private String speedStart;
	
	/**
	 * 最大速度
	 */
	private String  speedEnd;
	
	/**
	 * 排序规则，asc或desc
	 */
	private String sort;
	
	/**
	 * 是否仅仅搜索无牌车（号牌号码长度小于7） 值：true 或 false，默认为false
	 */
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
	
	private String plateType;


	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	/**
	 * getter method
	 * @return the orgPrvCode
	 */
	
	public String getOrgPrvCode() {
		return orgPrvCode;
	}

	/**
	 * setter method
	 * @param orgPrvCode the orgPrvCode to set
	 */
	
	public void setOrgPrvCode(String orgPrvCode) {
		this.orgPrvCode = orgPrvCode;
	}

	public String getRoadType() {
		return roadType;
	}

	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}


	public String[] getDistritCodes() {
		return distritCodes;
	}

	public void setDistritCodes(String[] distritCodes) {
		this.distritCodes = distritCodes;
	}

	public String[] getRoadCodes() {
		return roadCodes;
	}

	public void setRoadCodes(String[] roadCodes) {
		this.roadCodes = roadCodes;
	}

	public String[] getSiteCodes() {
		return siteCodes;
	}

	public void setSiteCodes(String[] siteCodes) {
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

	

	/**
	 * getter method
	 * @return the passTimeStart
	 */
	
	public String getPassTimeStart() {
		return passTimeStart;
	}

	/**
	 * setter method
	 * @param passTimeStart the passTimeStart to set
	 */
	
	public void setPassTimeStart(String passTimeStart) {
		this.passTimeStart = passTimeStart;
	}

	/**
	 * getter method
	 * @return the passTimeEnd
	 */
	
	public String getPassTimeEnd() {
		return passTimeEnd;
	}

	/**
	 * setter method
	 * @param passTimeEnd the passTimeEnd to set
	 */
	
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

	/**
	 * getter method
	 * @return the sort
	 */
	
	public String getSort() {
		return sort;
	}

	/**
	 * setter method
	 * @param sort the sort to set
	 */
	
	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getJustNonlocalVeh() {
		return justNonlocalVeh;
	}

	public void setJustNonlocalVeh(String justNonlocalVeh) {
		this.justNonlocalVeh = justNonlocalVeh;
	}

	public String getJustSearchNullPlate() {
		return justSearchNullPlate;
	}

	public void setJustSearchNullPlate(String justSearchNullPlate) {
		this.justSearchNullPlate = justSearchNullPlate;
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

	public String getPlateType() {
		return plateType;
	}

	public void setPlateType(String plateType) {
		this.plateType = plateType;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
}
