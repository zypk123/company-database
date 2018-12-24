package cy.its.vehTrack.rest.dto;

import java.lang.reflect.Field;

import com.wordnik.swagger.annotations.ApiParam;

import cy.its.com.dto.BaseDto;

public class ViolationCar extends BaseDto {

	/**
	 * 违法类型
	 */
	private String violationTypes;

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

	/**
	 * 机构代码（多个用半角逗号隔开）
	 */
	private String orgCodes;

	/**
	 * 管辖机构代码
	 */
	private String orgAuthorityCode;

	/**
	 * 行政区划代码(007)（多个用半角逗号隔开）
	 */
	private String districtCodes;
	
	/**
	 * 高速、非高速标识 true:仅查询高速 false:查询非高速
	 */
	private String isHighway; 
	
	/**
	 * 若有值不Count不分页，仅查询TopN
	 */
	private String topN;
	
	/**
	 * 统计方式，1：按违法罚分统计 0或空：按违法次数统计
	 */
	private String statBy;

	/**
	 * 总记录数
	 */
	private String totalCount;

	// 当前页数
	private int pageNumber;
	// 分页大小
	private int pageSize;
	
	private String plateType;

	public String getViolationTypes() {
		return violationTypes;
	}

	public void setViolationTypes(String violationTypes) {
		this.violationTypes = violationTypes;
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

	public String getDistrictCodes() {
		return districtCodes;
	}

	public void setDistrictCodes(String districtCodes) {
		this.districtCodes = districtCodes;
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

	public String getStatBy() {
		return statBy;
	}

	public void setStatBy(String statBy) {
		this.statBy = statBy;
	}
	

	public String getPlateType() {
		return plateType;
	}

	public void setPlateType(String plateType) {
		this.plateType = plateType;
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
