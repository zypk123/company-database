package cy.its.vehTrack.rest.dto;

import java.lang.reflect.Field;

import com.wordnik.swagger.annotations.ApiParam;

import cy.its.com.dto.BaseDto;

public class DataTransferMissingInputBean extends BaseDto {

	/***** 查询的条件字段 ********/
	// 道路代码
	@ApiParam(value = "道路代码")
	private String roadCode;

	// 设备编号
	@ApiParam(value = "设备编号")
	private String deviceSysNbr;

	// 点位代码
	@ApiParam(value = "点位代码")
	private String siteCode;

	// 起始过车时间
	@ApiParam(value = "起始分析时间")
	private String passTimeFrom;

	// 结束过车时间
	@ApiParam(value = "结束分析时间")
	private String passTimeTo;

	// 机构权限代码
	@ApiParam(value = "机构权限代码")
	private String orgPrivilegeCode;

	// 道路类型
	@ApiParam(value = "道路类型")
	private String roadType;

	// 道路ID
	@ApiParam(value = "道路ID")
	private String roadId;

	/**
	 * 是否接入稽查布控系统 0 : 否 1 : 是 2 : 全
	 */
	@ApiParam(value = "是否接入稽查布控系统")
	private String isConnectTrackSys;

	// 当前页数
	@ApiParam(value = "当前页数")
	private int pageNumber;
	// 分页大小
	@ApiParam(value = "分页大小")
	private int pageSize;

	@ApiParam(value = "总数")
	private int totalCount;

	@ApiParam(value = "排序名称")
	private String orderName;
	@ApiParam(value = "排序方式")
	private String orderType;

	@ApiParam(value = "承建厂商id")
	private String contractorId;

	public String getRoadCode() {
		return roadCode;
	}

	public void setRoadCode(String roadCode) {
		this.roadCode = roadCode;
	}

	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}

	public void setDeviceSysNbr(String deviceSysNbr) {
		this.deviceSysNbr = deviceSysNbr;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getPassTimeFrom() {
		return passTimeFrom;
	}

	public void setPassTimeFrom(String passTimeFrom) {
		this.passTimeFrom = passTimeFrom;
	}

	public String getPassTimeTo() {
		return passTimeTo;
	}

	public void setPassTimeTo(String passTimeTo) {
		this.passTimeTo = passTimeTo;
	}

	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}

	public String getRoadType() {
		return roadType;
	}

	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}

	public String getRoadId() {
		return roadId;
	}

	public void setRoadId(String roadId) {
		this.roadId = roadId;
	}

	public String getIsConnectTrackSys() {
		return isConnectTrackSys;
	}

	public void setIsConnectTrackSys(String isConnectTrackSys) {
		this.isConnectTrackSys = isConnectTrackSys;
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

	public String getContractorId() {
		return contractorId;
	}

	public void setContractorId(String contractorId) {
		this.contractorId = contractorId;
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
