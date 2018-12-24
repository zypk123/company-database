package cy.its.device.rest.dto;

import java.util.List;

/**
 * 地图上显示设备DTO
 * @author chenzhiying 2015.10.13
 *
 */
public class DeviceForMapDto {
	private String devId;	
	private String devNbr;
	private String devName;
	private String devType;
	private String devStatus;	
	private String siteId;//
	private String siteName;
	private String siteCode;
	private double longitude;
	private double latitude;
	private String orgId;//
	private String orgName;//
	private String directionName;//
	private String latestHeartTime;
	private String latestCalibrationTime;
	private String timeDiff;
	public String getTimeDiff() {
		return timeDiff;
	}
	public void setTimeDiff(String timeDiff) {
		this.timeDiff = timeDiff;
	}
	private String latestUpDataTime;
	private String snapTotal;
	private String snapBackLogPercent;
	private String certificationExpireTime;//
	private String devImgUrl;//
	
	public String getDevId() {
		return devId;
	}
	public void setDevId(String devId) {
		this.devId = devId;
	}
	private String ownership;
	
	public String getOwnership() {
		return ownership;
	}
	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}	
	
	/**
	 * getter method
	 * @return the devImgUrl
	 */
	
	public String getDevImgUrl() {
		return devImgUrl;
	}
	/**
	 * setter method
	 * @param devImgUrl the devImgUrl to set
	 */
	
	public void setDevImgUrl(String devImgUrl) {
		this.devImgUrl = devImgUrl;
	}
	/**
	 * getter method
	 * @return the directionName
	 */
	
	public String getDirectionName() {
		return directionName;
	}
	/**
	 * setter method
	 * @param directionName the directionName to set
	 */
	
	public void setDirectionName(String directionName) {
		this.directionName = directionName;
	}
	/**
	 * getter method
	 * @return the latestHeartTime
	 */
	
	public String getLatestHeartTime() {
		return latestHeartTime;
	}
	/**
	 * setter method
	 * @param latestHeartTime the latestHeartTime to set
	 */
	
	public void setLatestHeartTime(String latestHeartTime) {
		this.latestHeartTime = latestHeartTime;
	}
	/**
	 * getter method
	 * @return the latestCalibrationTime
	 */
	
	public String getLatestCalibrationTime() {
		return latestCalibrationTime;
	}
	/**
	 * setter method
	 * @param latestCalibrationTime the latestCalibrationTime to set
	 */
	
	public void setLatestCalibrationTime(String latestCalibrationTime) {
		this.latestCalibrationTime = latestCalibrationTime;
	}
	/**
	 * getter method
	 * @return the latestUpDataTime
	 */
	
	public String getLatestUpDataTime() {
		return latestUpDataTime;
	}
	/**
	 * setter method
	 * @param latestUpDataTime the latestUpDataTime to set
	 */
	
	public void setLatestUpDataTime(String latestUpDataTime) {
		this.latestUpDataTime = latestUpDataTime;
	}
	/**
	 * getter method
	 * @return the snapTotal
	 */
	
	public String getSnapTotal() {
		return snapTotal;
	}
	/**
	 * setter method
	 * @param snapTotal the snapTotal to set
	 */
	
	public void setSnapTotal(String snapTotal) {
		this.snapTotal = snapTotal;
	}
	/**
	 * getter method
	 * @return the snapBackLogPercent
	 */
	
	public String getSnapBackLogPercent() {
		return snapBackLogPercent;
	}
	/**
	 * setter method
	 * @param snapBackLogPercent the snapBackLogPercent to set
	 */
	
	public void setSnapBackLogPercent(String snapBackLogPercent) {
		this.snapBackLogPercent = snapBackLogPercent;
	}
	/**
	 * getter method
	 * @return the certificationExpireTime
	 */
	
	public String getCertificationExpireTime() {
		return certificationExpireTime;
	}
	/**
	 * setter method
	 * @param certificationExpireTime the certificationExpireTime to set
	 */
	
	public void setCertificationExpireTime(String certificationExpireTime) {
		this.certificationExpireTime = certificationExpireTime;
	}
	/**
	 * getter method
	 * @return the devName
	 */
	
	public String getDevName() {
		return devName;
	}
	/**
	 * setter method
	 * @param devName the devName to set
	 */
	
	public void setDevName(String devName) {
		this.devName = devName;
	}
	/**
	 * getter method
	 * @return the orgId
	 */
	
	public String getOrgId() {
		return orgId;
	}
	/**
	 * setter method
	 * @param orgId the orgId to set
	 */
	
	public void setOrgId(String orgId) {
		this.orgId = orgId;
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
	public String getDevNbr() {
		return devNbr;
	}
	public void setDevNbr(String devNbr) {
		this.devNbr = devNbr;
	}
	public String getDevType() {
		return devType;
	}
	public void setDevType(String devType) {
		this.devType = devType;
	}
	public String getDevStatus() {
		return devStatus;
	}
	public void setDevStatus(String devStatus) {
		this.devStatus = devStatus;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
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
}
