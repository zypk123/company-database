/**
 * @Title: DeviceGeneralStatusModel.java
 * @Package cy.its.platform.common.model
 * @Description: TODO(这里要填写用途)
 * @author Administrator Administrator@cychina.cn
 * @date 2015年12月25日 下午4:39:56
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.platform.common.model;

/**
  * @ClassName: DeviceGeneralStatusModel
  * @Description: TODO(这里要填写用途)
  * @author Administrator Administrator@cychina.cn
  * @date 2015年12月25日 下午4:39:56
  *
  */

public class DeviceGeneralStatusModel {
	private String deviceId;
	private String orgPrivilegeCode;
	private String zhiduiPrivilegeCode;
	private String daduiPrivilegeCode;
	private String deviceType;
	
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
	 * @return the orgPrivilegeCode
	 */
	
	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}
	/**
	 * setter method
	 * @param orgPrivilegeCode the orgPrivilegeCode to set
	 */
	
	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}
	/**
	 * getter method
	 * @return the zhiduiPrivilegeCode
	 */
	
	public String getZhiduiPrivilegeCode() {
		return zhiduiPrivilegeCode;
	}
	/**
	 * setter method
	 * @param zhiduiPrivilegeCode the zhiduiPrivilegeCode to set
	 */
	
	public void setZhiduiPrivilegeCode(String zhiduiPrivilegeCode) {
		this.zhiduiPrivilegeCode = zhiduiPrivilegeCode;
	}
	/**
	 * getter method
	 * @return the daduiPrivilegeCode
	 */
	
	public String getDaduiPrivilegeCode() {
		return daduiPrivilegeCode;
	}
	/**
	 * setter method
	 * @param daduiPrivilegeCode the daduiPrivilegeCode to set
	 */
	
	public void setDaduiPrivilegeCode(String daduiPrivilegeCode) {
		this.daduiPrivilegeCode = daduiPrivilegeCode;
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
		this.deviceType = deviceType;	}
	
	/**
	 * getter method
	 * @return the statusType
	 */
	
	public Integer getStatusType() {
		return statusType;
	}
	/**
	 * setter method
	 * @param statusType the statusType to set
	 */
	
	public void setStatusType(Integer statusType) {
		this.statusType = statusType;
	}
	/**
	 * getter method
	 * @return the statusBugType
	 */
	
	public String getStatusBugType() {
		return statusBugType;
	}
	/**
	 * setter method
	 * @param statusBugType the statusBugType to set
	 */
	
	public void setStatusBugType(String statusBugType) {
		this.statusBugType = statusBugType;
	}
	private Integer statusType;
	private String statusBugType;	
}
