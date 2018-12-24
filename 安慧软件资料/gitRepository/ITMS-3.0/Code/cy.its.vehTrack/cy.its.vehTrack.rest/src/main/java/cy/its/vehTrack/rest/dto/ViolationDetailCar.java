package cy.its.vehTrack.rest.dto;

import java.lang.reflect.Field;

import com.wordnik.swagger.annotations.ApiParam;

import cy.its.com.dto.BaseDto;

public class ViolationDetailCar extends BaseDto {
	private String orgAuthorityCode;
	private String districtCodes;
	private String orgCodes;
	private String roadCodes;
	private String roadSectionCodes;
	private String directType;
	private String siteCodes;
	/**
	 * 过车开始日期及时间
	 */
	@ApiParam(value = "过车开始日期及时间 格式：yyyy-MM-dd HH:mm:ss", required = true)
	private String startDateTime;
	/**
	 * 过车截止日期及时间
	 */
	@ApiParam(value = "过车截止日期及时间  格式：yyyy-MM-dd HH:mm:ss", required = true)
	private String endDateTime;
	@ApiParam(value = "车牌号")
	private String plateNbr;
	private String plateType;
	private String plateColor;
	private String vehicleType;
	private String roadTypes;
	private String isHighway;
	private String topN;
	private String humanConfirmed;
	private String vehicleLocalization;
	@ApiParam(value = "违法类型")
	private String violationTypes;
	private String totalCount;
	private int pageNumber;
	private int pageSize;

	public String getOrgAuthorityCode() {
		return orgAuthorityCode;
	}

	public void setOrgAuthorityCode(String orgAuthorityCode) {
		this.orgAuthorityCode = orgAuthorityCode;
	}

	public String getDistrictCodes() {
		return districtCodes;
	}

	public void setDistrictCodes(String districtCodes) {
		this.districtCodes = districtCodes;
	}

	public String getOrgCodes() {
		return orgCodes;
	}

	public void setOrgCodes(String orgCodes) {
		this.orgCodes = orgCodes;
	}

	public String getRoadCodes() {
		return roadCodes;
	}

	public void setRoadCodes(String roadCodes) {
		this.roadCodes = roadCodes;
	}

	public String getRoadSectionCodes() {
		return roadSectionCodes;
	}

	public void setRoadSectionCodes(String roadSectionCodes) {
		this.roadSectionCodes = roadSectionCodes;
	}

	public String getDirectType() {
		return directType;
	}

	public void setDirectType(String directType) {
		this.directType = directType;
	}

	public String getSiteCodes() {
		return siteCodes;
	}

	public void setSiteCodes(String siteCodes) {
		this.siteCodes = siteCodes;
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

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getRoadTypes() {
		return roadTypes;
	}

	public void setRoadTypes(String roadTypes) {
		this.roadTypes = roadTypes;
	}

	public String getIsHighway() {
		return isHighway;
	}

	public void setIsHighway(String isHighway) {
		this.isHighway = isHighway;
	}

	public String getTopN() {
		return topN;
	}

	public void setTopN(String topN) {
		this.topN = topN;
	}

	public String getHumanConfirmed() {
		return humanConfirmed;
	}

	public void setHumanConfirmed(String humanConfirmed) {
		this.humanConfirmed = humanConfirmed;
	}

	public String getVehicleLocalization() {
		return vehicleLocalization;
	}

	public void setVehicleLocalization(String vehicleLocalization) {
		this.vehicleLocalization = vehicleLocalization;
	}

	public String getViolationTypes() {
		return violationTypes;
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

	public void setViolationTypes(String violationTypes) {
		this.violationTypes = violationTypes;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
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
