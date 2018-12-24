/**
 * @Title: DeviceRunMonitorDto.java
 * @Package cy.its.device.rest.dto
 * @Description: TODO(这里要填写用途)
 * @author Administrator Administrator@cychina.cn
 * @date 2016年1月19日 下午5:00:56
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2016 
 */

package cy.its.device.rest.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
  * @ClassName: DeviceRunMonitorDto
  * @Description: TODO(这里要填写用途)
  * @author Administrator Administrator@cychina.cn
  * @date 2016年1月19日 下午5:00:56
  *
  */

public class DeviceRunMonitorDto {
	
	private String deviceId;
	private String deviceKey;
	private String deviceSysNbr;
	private String deviceType;
	private String deviceName;
	private String address;
	private String correctTime;
	/**
	 * getter method
	 * @return the bugType
	 */
	
	public List<String> getBugType() {
		return bugType;
	}

	/**
	 * setter method
	 * @param bugType the bugType to set
	 */
	
	public void setBugType(List<String> bugType) {
		this.bugType = bugType;
	}

	private String correctTimeFlag;
	private String netStatus;
	private String netStatusFlag;
	private String elcStatus;
	private String elcStatusFlag;
	private String devBug;
	private String devBugFlag;
	
	private List<String> bugType;
	/**
	 * getter method
	 * @return the correctTimeFlag
	 */
	
	public String getCorrectTimeFlag() {
		return correctTimeFlag;
	}

	/**
	 * setter method
	 * @param correctTimeFlag the correctTimeFlag to set
	 */
	
	public void setCorrectTimeFlag(String correctTimeFlag) {
		this.correctTimeFlag = correctTimeFlag;
	}

	/**
	 * getter method
	 * @return the netStatusFlag
	 */
	
	public String getNetStatusFlag() {
		return netStatusFlag;
	}

	/**
	 * setter method
	 * @param netStatusFlag the netStatusFlag to set
	 */
	
	public void setNetStatusFlag(String netStatusFlag) {
		this.netStatusFlag = netStatusFlag;
	}

	/**
	 * getter method
	 * @return the elcStatusFlag
	 */
	
	public String getElcStatusFlag() {
		return elcStatusFlag;
	}

	/**
	 * setter method
	 * @param elcStatusFlag the elcStatusFlag to set
	 */
	
	public void setElcStatusFlag(String elcStatusFlag) {
		this.elcStatusFlag = elcStatusFlag;
	}

	/**
	 * getter method
	 * @return the devBugFlag
	 */
	
	public String getDevBugFlag() {
		return devBugFlag;
	}

	/**
	 * setter method
	 * @param devBugFlag the devBugFlag to set
	 */
	
	public void setDevBugFlag(String devBugFlag) {
		this.devBugFlag = devBugFlag;
	}

	/**
	 * getter method
	 * @return the mantainceFlag
	 */
	
	public String getMantainceFlag() {
		return mantainceFlag;
	}

	/**
	 * setter method
	 * @param mantainceFlag the mantainceFlag to set
	 */
	
	public void setMantainceFlag(String mantainceFlag) {
		this.mantainceFlag = mantainceFlag;
	}

	private String mantainceFlag;
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

	/**
	 * getter method
	 * @return the deviceKey
	 */
	
	public String getDeviceKey() {
		return deviceKey;
	}

	/**
	 * setter method
	 * @param deviceKey the deviceKey to set
	 */
	
	public void setDeviceKey(String deviceKey) {
		this.deviceKey = deviceKey;
	}

	/**
	 * getter method
	 * @return the deviceSysNbr
	 */
	
	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}

	/**
	 * setter method
	 * @param deviceSysNbr the deviceSysNbr to set
	 */
	
	public void setDeviceSysNbr(String deviceSysNbr) {
		this.deviceSysNbr = deviceSysNbr;
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
	 * @return the address
	 */
	
	public String getAddress() {
		return address;
	}

	/**
	 * setter method
	 * @param address the address to set
	 */
	
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * getter method
	 * @return the correctTime
	 */
	
	public String getCorrectTime() {
		return correctTime;
	}

	/**
	 * setter method
	 * @param correctTime the correctTime to set
	 */
	
	public void setCorrectTime(String correctTime) {
		this.correctTime = correctTime;
	}

	/**
	 * getter method
	 * @return the netStatus
	 */
	
	public String getNetStatus() {
		return netStatus;
	}

	/**
	 * setter method
	 * @param netStatus the netStatus to set
	 */
	
	public void setNetStatus(String netStatus) {
		this.netStatus = netStatus;
	}

	/**
	 * getter method
	 * @return the elcStatus
	 */
	
	public String getElcStatus() {
		return elcStatus;
	}

	/**
	 * setter method
	 * @param elcStatus the elcStatus to set
	 */
	
	public void setElcStatus(String elcStatus) {
		this.elcStatus = elcStatus;
	}

	/**
	 * getter method
	 * @return the devBug
	 */
	
	public String getDevBug() {
		return devBug;
	}

	/**
	 * setter method
	 * @param devBug the devBug to set
	 */
	
	public void setDevBug(String devBug) {
		this.devBug = devBug;
	}

	/**
	 * getter method
	 * @return the latestHeartTime
	 */
	
	public Date getLatestHeartTime() {
		return latestHeartTime;
	}

	/**
	 * setter method
	 * @param latestHeartTime the latestHeartTime to set
	 */
	
	public void setLatestHeartTime(Date latestHeartTime) {
		this.latestHeartTime = latestHeartTime;
	}

	/**
	 * getter method
	 * @return the latestUploadTime
	 */
	
	public Date getLatestUploadTime() {
		return latestUploadTime;
	}

	/**
	 * setter method
	 * @param latestUploadTime the latestUploadTime to set
	 */
	
	public void setLatestUploadTime(Date latestUploadTime) {
		this.latestUploadTime = latestUploadTime;
	}

	/**
	 * getter method
	 * @return the mantaince
	 */
	
	public Map<String, String> getMantaince() {
		return mantaince;
	}

	/**
	 * setter method
	 * @param mantaince the mantaince to set
	 */
	
	public void setMantaince(Map<String, String> mantaince) {
		this.mantaince = mantaince;
	}

	private Date latestHeartTime;
	private Date latestUploadTime;
	
	private Map<String, String> mantaince;
	
	private String latestUploadTimeFormat;
	
	public String getLatestUploadTimeFormat() {
		return latestUploadTimeFormat;
	}

	/**
	 * setter method
	 * @param lastUploadTimeFormat the lastUploadTimeFormat to set
	 */
	
	public void setLatestUploadTimeFormat(String latestUploadTimeFormat) {
		this.latestUploadTimeFormat = latestUploadTimeFormat;
	}
	
	private String deviceTypeName;
	/**
	 * getter method
	 * @return the deviceTypeName
	 */
	
	public String getDeviceTypeName() {
		return deviceTypeName;
	}

	/**
	 * setter method
	 * @param deviceTypeName the deviceTypeName to set
	 */
	
	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}
	/**
	 * 
	 */
	private String matainceInfo;
	/**
	 * getter method
	 * @return the matainceInfo
	 */
	
	public String getMatainceInfo() {
		return matainceInfo;
	}

	/**
	 * setter method
	 * @param matainceInfo the matainceInfo to set
	 */
	
	public void setMatainceInfo(String matainceInfo) {
		this.matainceInfo = matainceInfo;
	}
}
