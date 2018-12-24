package cy.its.vehTrack.rest.dto;

public class PlateRecognitionOutBean {

	private String deviceNbr;
	private String dateStr;
	private Integer invalidCount;
	private Integer totalCount;
	private String recogRate;
	private Integer dayTotalCount;
	private Integer dayInvalidCount;
	private String dayRecogRate;
	private Integer nightTotalCount;
	private Integer nightInvalidCount;
	private String nightRecogRate;
	private String siteName;
	private String tollgateType;
	private String orgName;
	private String deviceName;

	public String getDeviceNbr() {
		return deviceNbr;
	}

	public void setDeviceNbr(String deviceNbr) {
		this.deviceNbr = deviceNbr;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public Integer getInvalidCount() {
		return invalidCount;
	}

	public void setInvalidCount(Integer invalidCount) {
		this.invalidCount = invalidCount;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public String getRecogRate() {
		return recogRate;
	}

	public void setRecogRate(String recogRate) {
		this.recogRate = recogRate;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getTollgateType() {
		return tollgateType;
	}

	public void setTollgateType(String tollgateType) {
		this.tollgateType = tollgateType;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public Integer getDayTotalCount() {
		return dayTotalCount;
	}

	public void setDayTotalCount(Integer dayTotalCount) {
		this.dayTotalCount = dayTotalCount;
	}

	public Integer getDayInvalidCount() {
		return dayInvalidCount;
	}

	public void setDayInvalidCount(Integer dayInvalidCount) {
		this.dayInvalidCount = dayInvalidCount;
	}

	public String getDayRecogRate() {
		return dayRecogRate;
	}

	public void setDayRecogRate(String dayRecogRate) {
		this.dayRecogRate = dayRecogRate;
	}

	public Integer getNightTotalCount() {
		return nightTotalCount;
	}

	public void setNightTotalCount(Integer nightTotalCount) {
		this.nightTotalCount = nightTotalCount;
	}

	public Integer getNightInvalidCount() {
		return nightInvalidCount;
	}

	public void setNightInvalidCount(Integer nightInvalidCount) {
		this.nightInvalidCount = nightInvalidCount;
	}

	public String getNightRecogRate() {
		return nightRecogRate;
	}

	public void setNightRecogRate(String nightRecogRate) {
		this.nightRecogRate = nightRecogRate;
	}

}
