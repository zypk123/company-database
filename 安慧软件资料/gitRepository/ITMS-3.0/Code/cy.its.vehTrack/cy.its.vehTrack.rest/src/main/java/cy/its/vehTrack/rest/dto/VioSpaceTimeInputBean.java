package cy.its.vehTrack.rest.dto;

import java.lang.reflect.Field;

import com.wordnik.swagger.annotations.ApiParam;

import cy.its.com.dto.BaseDto;

public class VioSpaceTimeInputBean  extends BaseDto{
	
	@ApiParam(value="组织机构")
	private String orgCodes;
	@ApiParam(value="开始时间")
	private String startDateTime;
	@ApiParam(value="结束时间")
	private String endDateTime;
	@ApiParam(value="违法类型，多个用逗号隔开")
	private String violationTypes;
	@ApiParam(value="号牌类型")
	private String plateType;
	@ApiParam(value="道路，多个用逗号隔开")
	private String roadCodes;
	@ApiParam(value="统计类型： district、org、road、roadSection、site")
	private String statBy ;
	
	// 机构权限编码
	private String orgAuthorityCode;
	// 当前页数
	private int pageNumber;
	// 分页大小
	private int pageSize;
	// 总数
	private int totalCount;
	
	private String isPagination;
	public String getOrgCodes() {
		return orgCodes;
	}
	public void setOrgCodes(String orgCodes) {
		this.orgCodes = orgCodes;
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
	public String getViolationTypes() {
		return violationTypes;
	}
	public void setViolationTypes(String violationTypes) {
		this.violationTypes = violationTypes;
	}
	public String getPlateType() {
		return plateType;
	}
	public void setPlateType(String plateType) {
		this.plateType = plateType;
	}
	public String getRoadCodes() {
		return roadCodes;
	}
	public void setRoadCodes(String roadCodes) {
		this.roadCodes = roadCodes;
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
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public String getStatBy() {
		return statBy;
	}
	public void setStatBy(String statBy) {
		this.statBy = statBy;
	}
	public String getOrgAuthorityCode() {
		return orgAuthorityCode;
	}
	public void setOrgAuthorityCode(String orgAuthorityCode) {
		this.orgAuthorityCode = orgAuthorityCode;
	}
	
	public String getIsPagination() {
		return isPagination;
	}
	public void setIsPagination(String isPagination) {
		this.isPagination = isPagination;
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
