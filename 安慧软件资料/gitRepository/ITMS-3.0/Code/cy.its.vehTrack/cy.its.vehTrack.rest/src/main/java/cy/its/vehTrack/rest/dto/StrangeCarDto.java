package cy.its.vehTrack.rest.dto;

import java.lang.reflect.Field;

import com.wordnik.swagger.annotations.ApiParam;

import cy.its.com.dto.BaseDto;

public class StrangeCarDto extends BaseDto {

	@ApiParam(value = "号牌号码")
	private String plateNbr;
	@ApiParam(value = "外地车查询标识值")
	private String justNonlocalVeh;
	@ApiParam(value = "号牌类型")
	private String plateType;
	@ApiParam(value = "车辆类型")
	private String vehicleType;
	@ApiParam(value = "车身颜色")
	private String vehicleColor;
	@ApiParam(value = "车辆品牌")
	private String vehicleBrand;
	@ApiParam(value = "车辆子品牌")
	private String vehicleSubBrand;
	@ApiParam(value = "行政区划", required = true)
	private String districtCode;
	@ApiParam(value = "开始时间", required = true)
	private String firstTime;
	@ApiParam(value = "结束时间", required = true)
	private String endTime;
	@ApiParam(value = "出租车号牌规则正则表达式，如：^云[A-Z]T.*")
	private String texiPlateRule;
	@ApiParam(value = "是仅查询出租车还是排除出租车，值：justTexi excludeTexi")
	private String justOrExcludeTexi;
	private String totalCount;
	// 当前页数
	private int pageNumber;
	// 分页大小
	private int pageSize;

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

	public String getVehicleSubBrand() {
		return vehicleSubBrand;
	}

	public void setVehicleSubBrand(String vehicleSubBrand) {
		this.vehicleSubBrand = vehicleSubBrand;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(String firstTime) {
		this.firstTime = firstTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTexiPlateRule() {
		return texiPlateRule;
	}

	public void setTexiPlateRule(String texiPlateRule) {
		this.texiPlateRule = texiPlateRule;
	}

	public String getJustOrExcludeTexi() {
		return justOrExcludeTexi;
	}

	public void setJustOrExcludeTexi(String justOrExcludeTexi) {
		this.justOrExcludeTexi = justOrExcludeTexi;
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

	public String getJustNonlocalVeh() {
		return justNonlocalVeh;
	}

	public void setJustNonlocalVeh(String justNonlocalVeh) {
		this.justNonlocalVeh = justNonlocalVeh;
	}

	@Override
	public String toString() {
		StringBuilder params = new StringBuilder();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				params.append("&").append(field.getName()).append("=");
				Object value = field.get(this);
				if (value != null) {
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
