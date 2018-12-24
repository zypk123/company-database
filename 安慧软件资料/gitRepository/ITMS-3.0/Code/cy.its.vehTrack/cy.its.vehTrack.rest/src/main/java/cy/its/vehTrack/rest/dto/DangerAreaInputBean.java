package cy.its.vehTrack.rest.dto;

import java.lang.reflect.Field;

import com.wordnik.swagger.annotations.ApiParam;

import cy.its.com.dto.BaseDto;

public class DangerAreaInputBean extends BaseDto {
	
	/**
	 * 管辖机构代码
	 */
	private String orgAuthorityCode;

	/**
	 * 开始日期及时间
	 */
	@ApiParam(value="开始日期及时间",required=true)
	private String startDateTime;

	/**
	 * 截止日期及时间
	 */
	@ApiParam(value="截止日期及时间",required=true)
	private String endDateTime;
	
	/**
	 * 时段开始时间
	 */
	private String startTime;

	/**
	 * 时段截止时间
	 */
	private String endTime;
	
	/**
	 * 高危区域点位列表（多个用半角逗号隔开）
	 */
	private String siteCodes;

	/**
	 * 车辆归属地
	 */
	private String vehLocalization;
			
	/**
	 * 车辆品牌
	 */
	private String vehicleBrand;
	
	/**
	 *车辆子品牌(005)
	 */
	private String vehicleSubBrand;
	
	/**
	 * 车辆类型
	 */
	private String vehicleType;
    		
	/**
	 * 车身颜色
	 */
	private String vehicleColor;
    		
	/**
	 * 号牌种类
	 */
	private String plateType;

	/**
	 * 排序方式（"asc" "desc"）
	 */
	private String orderType;

	/**
	 * 增加查询条件：过车次数范围 2011-11-13 Jiaoxujin
	 */
	private String passMinCount;

	/**
	 * 增加查询条件：过车次数范围 2011-11-13 Jiaoxujin
	 */
	private String passMaxCount;
	
	/**
     * 总记录数
     */
    private String totalCount;
	
	/**
	 * 当前页面号
	 */
	private int pageNumber;

	/**
	 * 每页最多显示的行数
	 */
	private int pageSize;

	public String getOrgAuthorityCode() {
		return orgAuthorityCode;
	}

	public void setOrgAuthorityCode(String orgAuthorityCode) {
		this.orgAuthorityCode = orgAuthorityCode;
	}

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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getSiteCodes() {
		return siteCodes;
	}

	public void setSiteCodes(String siteCodes) {
		this.siteCodes = siteCodes;
	}

	public String getVehLocalization() {
		return vehLocalization;
	}

	public void setVehLocalization(String vehLocalization) {
		this.vehLocalization = vehLocalization;
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

	public String getVehicleColor() {
		return vehicleColor;
	}

	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
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

	public String getPassMinCount() {
		return passMinCount;
	}

	public void setPassMinCount(String passMinCount) {
		this.passMinCount = passMinCount;
	}

	public String getPassMaxCount() {
		return passMaxCount;
	}

	public void setPassMaxCount(String passMaxCount) {
		this.passMaxCount = passMaxCount;
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
