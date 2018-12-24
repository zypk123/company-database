package cy.its.vehTrack.rest.dto;

import java.lang.reflect.Field;

import cy.its.com.dto.BaseDto;

public class CollAnalyzeInputBean extends BaseDto{

	/* 查询开始时间 */
	private String beginTime1;
	private String beginTime2;
	private String beginTime3;
	private String beginTime4;
	private String beginTime5;

	/* 查询截止时间 */
	private String endTime1;
	private String endTime2;
	private String endTime3;
	private String endTime4;
	private String endTime5;

	/* 点位 */
	private String siteCode1;
	private String siteCode2;
	private String siteCode3;
	private String siteCode4;
	private String siteCode5;

	private String qryFields;

	private String plateType;

	private String vehicleType;
	/**
	 * 是否有号牌 值：ture false
	 */
	private String havePlate;

	/*
	 * 是否假套牌 值：ture false
	 */
	private String isFlasePlate;

	/* 前台输入参数个数 */
	private int qryConditionNum;
	
	private String vehicleBrand;
	
	private String vehicleSubBrand;
	
	private String orgAuthorityCode;
	
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

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getBeginTime1() {
		return beginTime1;
	}

	public void setBeginTime1(String beginTime1) {
		this.beginTime1 = beginTime1;
	}

	public String getBeginTime2() {
		return beginTime2;
	}

	public void setBeginTime2(String beginTime2) {
		this.beginTime2 = beginTime2;
	}

	public String getBeginTime3() {
		return beginTime3;
	}

	public void setBeginTime3(String beginTime3) {
		this.beginTime3 = beginTime3;
	}

	public String getBeginTime4() {
		return beginTime4;
	}

	public void setBeginTime4(String beginTime4) {
		this.beginTime4 = beginTime4;
	}

	public String getBeginTime5() {
		return beginTime5;
	}

	public void setBeginTime5(String beginTime5) {
		this.beginTime5 = beginTime5;
	}

	public String getEndTime1() {
		return endTime1;
	}

	public void setEndTime1(String endTime1) {
		this.endTime1 = endTime1;
	}

	public String getEndTime2() {
		return endTime2;
	}

	public void setEndTime2(String endTime2) {
		this.endTime2 = endTime2;
	}

	public String getEndTime3() {
		return endTime3;
	}

	public void setEndTime3(String endTime3) {
		this.endTime3 = endTime3;
	}

	public String getEndTime4() {
		return endTime4;
	}

	public void setEndTime4(String endTime4) {
		this.endTime4 = endTime4;
	}

	public String getEndTime5() {
		return endTime5;
	}

	public void setEndTime5(String endTime5) {
		this.endTime5 = endTime5;
	}

	public String getSiteCode1() {
		return siteCode1;
	}

	public void setSiteCode1(String siteCode1) {
		this.siteCode1 = siteCode1;
	}

	public String getSiteCode2() {
		return siteCode2;
	}

	public void setSiteCode2(String siteCode2) {
		this.siteCode2 = siteCode2;
	}

	public String getSiteCode3() {
		return siteCode3;
	}

	public void setSiteCode3(String siteCode3) {
		this.siteCode3 = siteCode3;
	}

	public String getSiteCode4() {
		return siteCode4;
	}

	public void setSiteCode4(String siteCode4) {
		this.siteCode4 = siteCode4;
	}

	public String getSiteCode5() {
		return siteCode5;
	}

	public void setSiteCode5(String siteCode5) {
		this.siteCode5 = siteCode5;
	}

	public String getQryFields() {
		return qryFields;
	}

	public void setQryFields(String qryFields) {
		this.qryFields = qryFields;
	}

	public int getQryConditionNum() {
		return qryConditionNum;
	}

	public void setQryConditionNum(int qryConditionNum) {
		this.qryConditionNum = qryConditionNum;
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

	public String getHavePlate() {
		return havePlate;
	}

	public void setHavePlate(String havePlate) {
		this.havePlate = havePlate;
	}

	public String getIsFlasePlate() {
		return isFlasePlate;
	}

	public void setIsFlasePlate(String isFlasePlate) {
		this.isFlasePlate = isFlasePlate;
	}

	public String getPlateType() {
		return plateType;
	}

	public void setPlateType(String plateType) {
		this.plateType = plateType;
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

	public String getOrgAuthorityCode() {
		return orgAuthorityCode;
	}

	public void setOrgAuthorityCode(String orgAuthorityCode) {
		this.orgAuthorityCode = orgAuthorityCode;
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
