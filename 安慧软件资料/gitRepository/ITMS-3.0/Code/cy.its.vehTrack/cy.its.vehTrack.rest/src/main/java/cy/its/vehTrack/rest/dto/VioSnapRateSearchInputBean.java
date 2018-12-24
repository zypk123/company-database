package cy.its.vehTrack.rest.dto;


import java.lang.reflect.Field;

import com.wordnik.swagger.annotations.ApiParam;

import cy.its.com.dto.BaseDto;

/**
 * @Description: 违法设备抓捕率分析查询条件实体
 *
 */
public class VioSnapRateSearchInputBean extends BaseDto {

	/**
	 * 机构代码（多个用半角逗号隔开）
	 */
	@ApiParam(value = "机构代码（多个用半角逗号隔开）")
	private String orgCodes;

	/**
	 * 行政区划代码(007)（多个用半角逗号隔开）
	 */
	@ApiParam(value = "行政区划代码（多个用半角逗号隔开）")
	private String districtCodes;

	/**
	 * 道路编码（多个用半角逗号隔开）
	 */
	@ApiParam(value = "道路代码（多个用半角逗号隔开）")
	private String roadCodes;

	/**
	 * 点位编码（多个用半角逗号隔开）
	 */
	@ApiParam(value = "点位编码（多个用半角逗号隔开）")
	private String siteCodes;

	/**
	 * (自定义)方向名称 <br />
	 * 过车查询页面未使用该条件，但仍然可以使用restful接口使用该条件
	 */
	@ApiParam(value = "(自定义)方向名称，精确匹配。")
	private String directionName;

	/**
	 * 查询开始日期
	 */
	@ApiParam(value = "查询开始日期 格式：yyyy-MM-dd", required = true)
	private String startDate;

	/**
	 * 查询结束日期
	 */
	@ApiParam(value = "查询结束日期  格式：yyyy-MM-dd", required = true)
	private String endDate;

	/**
	 * 违法类型
	 */
	@ApiParam(value = "违法类型代码单选", required = true)
	private String violationType;

	/**
	 * 卡口承建商id(t_device_sys.contractor_id)
	 */
	@ApiParam(value = "卡口承建商id")
	private String contractorId;

	/**
	 * 管辖机构代码
	 */
	@ApiParam(value = "管辖机构代码：建议不要使用该参数")
	private String orgAuthorityCode;

	/**
	 * 排序名称
	 */
	@ApiParam(value = "排序名称 格式：contractor[按厂商+抓捕率排序] 或 vioRate[按抓捕率排序]")
	private String orderName;

	/**
	 * 排序方式
	 */
	@ApiParam(value = "查询结果排序方式（按倒序还是顺序，值\"asc\"或\"desc\"）")
	private String orderType;

	// 当前页数
	@ApiParam(value = "当前页数")
	private int pageNumber;
	// 分页大小
	@ApiParam(value = "分页大小")
	private int pageSize;

	@ApiParam(value = "总数")
	private int totalCount;

	public String getOrgCodes() {
		return orgCodes;
	}

	public void setOrgCodes(String orgCodes) {
		this.orgCodes = orgCodes;
	}

	public String getDistrictCodes() {
		return districtCodes;
	}

	public void setDistrictCodes(String districtCodes) {
		this.districtCodes = districtCodes;
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

	public String getDirectionName() {
		return directionName;
	}

	public void setDirectionName(String directionName) {
		this.directionName = directionName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getViolationType() {
		return violationType;
	}

	public void setViolationType(String violationType) {
		this.violationType = violationType;
	}

	public String getContractorId() {
		return contractorId;
	}

	public void setContractorId(String contractorId) {
		this.contractorId = contractorId;
	}

	public String getOrgAuthorityCode() {
		return orgAuthorityCode;
	}

	public void setOrgAuthorityCode(String orgAuthorityCode) {
		this.orgAuthorityCode = orgAuthorityCode;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
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
