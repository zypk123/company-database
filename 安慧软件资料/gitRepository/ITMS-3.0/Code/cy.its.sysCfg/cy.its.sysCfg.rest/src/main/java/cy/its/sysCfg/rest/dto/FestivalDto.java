package cy.its.sysCfg.rest.dto;

public class FestivalDto {

	private String festivalId;// 节日Id
	private String festivalType;// 节日类型(春节，国庆节等)
	private String startDate;// 开始日期
	private String endDate;// 结束日期
	private String year;// 节假日所在年份
	private String remark;// 备注

	public String getFestivalId() {
		return festivalId;
	}

	public void setFestivalId(String festivalId) {
		this.festivalId = festivalId;
	}

	public String getFestivalType() {
		return festivalType;
	}

	public void setFestivalType(String festivalType) {
		this.festivalType = festivalType;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
