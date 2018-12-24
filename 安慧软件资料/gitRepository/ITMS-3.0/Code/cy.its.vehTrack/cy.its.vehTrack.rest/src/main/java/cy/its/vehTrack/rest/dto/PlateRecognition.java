package cy.its.vehTrack.rest.dto;

import java.lang.reflect.Field;

import com.wordnik.swagger.annotations.ApiParam;

import cy.its.com.dto.BaseDto;

public class PlateRecognition extends BaseDto {



	/**
	 * 过车开始日期及时间
	 */
	@ApiParam(value=" 过车开始时间 格式：yyyy-MM-dd HH:mm:ss",required=true)
	private String startDateTime;
	
	/**
	 * 过车截止日期及时间
	 */
	@ApiParam(value=" 过车截止时间 格式：yyyy-MM-dd HH:mm:ss",required=true)
	private String endDateTime;

	/**
	 * 机构代码（多个用半角逗号隔开）
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
	 * 道路编码（多个用半角逗号隔开）
	 */
	private String roadCodes;
	
	/**
	 * 点位编码（多个用半角逗号隔开）
	 */
	private String siteCodes;

	/**
	 *车道号
	 */
	private String driveLane;
	
	/**
	 * 行政区划代码(007)（多个用半角逗号隔开）
	 */
	private String districtCodes;
	
	/**
	 * (自定义)方向名称 <br />
	 */
	private String directionName;
	
	/**
	 * 路口路段代码（多个用半角逗号隔开）
	 */
	private String roadSectionCodes;
	
	/**
	 * 设备系统编号
	 */
	private String deviceSysNbr;
	
	/**
	 * 设备编号
	 */
	private String deviceNbr;

	/**
	 * 道路类型
	 */
	private String roadType;

	/**
	 * 要查询的进出城标示（多个用半角逗号隔开）
	 */
	private String entranceOrExits;
	
	/**
	 * 进出高速公路卡口标识。0-非进出高速公路卡口；1-进；2-出
	 */
	private String highwayAccessFlag;
	
	/**
	 * 进出服务区卡口标识。0-非进出服务区卡口；1-进；2-出
	 */
	private String serviceAreaAccessFlag;
	
	/**
	 * 查询结果排序方式（倒序还是顺序ASC DESC）
	 */
	private String orderType;
	
	/**
	 * 查询结果排序字段名称deviceNbr day hour recogRate totalCount invalidCount <br />
	 * Constants.PlateRecogOrderName
	 */
	private String orderTypeName;
	
	/**
	 * 按什么统计(deviceNbr:按设备，day:按日期天， hour:按日期小时)
	 */
	private String statBy;
	
	private String tollgateType;
	
	private String isConnectTrackSys;
	/**
     * 总记录数
     */
    private String totalCount;
    
 // 当前页数
 	private int pageNumber;
 	// 分页大小
 	private int pageSize;
    
	public String getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}
	public String getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}
	public String getOrgCodes() {
		return orgCodes;
	}
	public void setOrgCodes(String orgCodes) {
		this.orgCodes = orgCodes;
	}
	public String getOrgAuthorityCode() {
		return orgAuthorityCode;
	}
	public void setOrgAuthorityCode(String orgAuthorityCode) {
		this.orgAuthorityCode = orgAuthorityCode;
	}
	public String getDirectType() {
		return directType;
	}
	public void setDirectType(String directType) {
		this.directType = directType;
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
	public String getDriveLane() {
		return driveLane;
	}
	public void setDriveLane(String driveLane) {
		this.driveLane = driveLane;
	}
	public String getDistrictCodes() {
		return districtCodes;
	}
	public void setDistrictCodes(String districtCodes) {
		this.districtCodes = districtCodes;
	}
	public String getDirectionName() {
		return directionName;
	}
	public void setDirectionName(String directionName) {
		this.directionName = directionName;
	}
	public String getRoadSectionCodes() {
		return roadSectionCodes;
	}
	public void setRoadSectionCodes(String roadSectionCodes) {
		this.roadSectionCodes = roadSectionCodes;
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
	public String getRoadType() {
		return roadType;
	}
	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}
	public String getEntranceOrExits() {
		return entranceOrExits;
	}
	public void setEntranceOrExits(String entranceOrExits) {
		this.entranceOrExits = entranceOrExits;
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
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getOrderTypeName() {
		return orderTypeName;
	}
	public void setOrderTypeName(String orderTypeName) {
		this.orderTypeName = orderTypeName;
	}
	public String getStatBy() {
		return statBy;
	}
	public void setStatBy(String statBy) {
		this.statBy = statBy;
	}
	
	public String getTollgateType() {
		return tollgateType;
	}
	public void setTollgateType(String tollgateType) {
		this.tollgateType = tollgateType;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	
	
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public String getIsConnectTrackSys() {
		return isConnectTrackSys;
	}
	public void setIsConnectTrackSys(String isConnectTrackSys) {
		this.isConnectTrackSys = isConnectTrackSys;
	}
	@Override
	public String toString(){
		StringBuilder params = new StringBuilder();
		Field[] fields = this.getClass().getDeclaredFields();
		for(Field field : fields){
			try {
				params.append("&").append(field.getName()).append("=");
				Object value = field.get(this);
				if(value != null){
					params.append(value);
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return params.substring(1);
	}
}
