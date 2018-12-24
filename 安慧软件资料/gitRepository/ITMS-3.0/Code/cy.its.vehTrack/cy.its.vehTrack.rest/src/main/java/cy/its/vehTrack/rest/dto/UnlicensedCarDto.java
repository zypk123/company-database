package cy.its.vehTrack.rest.dto;

import java.lang.reflect.Field;

import com.wordnik.swagger.annotations.ApiParam;

import cy.its.com.dto.BaseDto;

public class UnlicensedCarDto extends BaseDto {
	
	/**
	 * 无牌车辆分析输入inputBean
	 */

	/*开始时间*/
	@ApiParam(value=" 过车开始时间 格式：yyyy-MM-dd HH:mm:ss",required=true)
	private String beginTime;
	
	/*截止时间*/
	@ApiParam(value=" 过车截止时间 格式：yyyy-MM-dd HH:mm:ss",required=true)
	private String endTime;
	
	/*点位列表*/
	private String siteCode;
	
	/*车辆品牌*/
	private String vehBrand;
	
	/*车辆外形*/
	private String vehShape;
	
	/*车辆类型*/
	private String vehType;
	
	/*车辆用途*/
	private String vehCharacter;
	
	/*号牌类型*/
	private String plateType;
	
	/*号牌颜色*/
	private String plateColor;
	
	/*归属地*/
	private String vehLocation;
	
	/*查询字段*/
	private String qryFields;
	
	
	// 当前页数
	private int pageNumber;
		// 分页大小
	private int pageSize;
	
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

	/**
     * 总记录数
     */
    private String totalCount;

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getSiteCode() {
		return this.siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getVehBrand() {
		return vehBrand;
	}

	public void setVehBrand(String vehBrand) {
		this.vehBrand = vehBrand;
	}

	public String getVehShape() {
		return vehShape;
	}

	public void setVehShape(String vehShape) {
		this.vehShape = vehShape;
	}

	public String getVehType() {
		return vehType;
	}

	public void setVehType(String vehType) {
		this.vehType = vehType;
	}

	public String getVehCharacter() {
		return vehCharacter;
	}

	public void setVehCharacter(String vehCharacter) {
		this.vehCharacter = vehCharacter;
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

	public String getVehLocation() {
		return vehLocation;
	}

	public void setVehLocation(String vehLocation) {
		this.vehLocation = vehLocation;
	}

	public String getQryFields() {
		return qryFields;
	}

	public void setQryFields(String qryFields) {
		this.qryFields = qryFields;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
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
