package cy.its.trafficSituation.rest.dto;
/**
 * 地图上显示设备DTO
 * @author gyf 2015.11.13
 *
 */
public class DeviceDto {
	private String deviceSysNbr;
	private String deviceName;
	private String deviceType;
	private String deviceStatus;
	private String siteId;
	private String siteName;
	private double longitude;
	private double latitude;
	private String orgCode;
	private String orgName;
	private String lonLat;
	private String weatherType;
	private String deviceId;
	
	/**
	 * getter method
	 * @return the weatherType
	 */
	
	public String getWeatherType() {
		return weatherType;
	}
	/**
	 * setter method
	 * @param weatherType the weatherType to set
	 */
	
	public void setWeatherType(String weatherType) {
		this.weatherType = weatherType;
	}
	/**
	 * getter method
	 * @return the lonLat
	 */
	
	public String getLonLat() {
		return lonLat;
	}
	/**
	 * setter method
	 * @param lonLat the lonLat to set
	 */
	
	public void setLonLat(String lonLat) {
		this.lonLat = lonLat;
	}
	/**
	 * getter method
	 * @return the orgName
	 */
	
	public String getOrgName() {
		return orgName;
	}
	/**
	 * setter method
	 * @param orgName the orgName to set
	 */
	
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}
	public void setDeviceSysNbr(String deviceSysNbr) {
		this.deviceSysNbr = deviceSysNbr;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	/**
	 * getter method
	 * @return the deviceName
	 */
	
	public String getDeviceName() {
		return deviceName;
	}
	/**
	 * setter method
	 * @param deviceName the deviceName to set
	 */
	
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	/**
	 * getter method
	 * @return the orgCode
	 */
	
	public String getOrgCode() {
		return orgCode;
	}
	/**
	 * setter method
	 * @param orgCode the orgCode to set
	 */
	
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	/**
	 * getter method
	 * @return the deviceStatus
	 */
	
	public String getDeviceStatus() {
		return deviceStatus;
	}
	/**
	 * setter method
	 * @param deviceStatus the deviceStatus to set
	 */
	
	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
	/**
	 * getter method
	 * @return the deviceType
	 */
	
	public String getDeviceType() {
		return deviceType;
	}
	/**
	 * setter method
	 * @param deviceType the deviceType to set
	 */
	
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	/**
	 * getter method
	 * @return the deviceId
	 */
	
	public String getDeviceId() {
		return deviceId;
	}
	/**
	 * setter method
	 * @param deviceId the deviceId to set
	 */
	
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
}
