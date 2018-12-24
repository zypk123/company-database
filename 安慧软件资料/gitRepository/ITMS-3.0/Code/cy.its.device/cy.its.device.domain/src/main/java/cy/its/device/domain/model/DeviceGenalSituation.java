/**
 * @Title: DeviceGenalSituation.java
 * @Package cy.its.device.domain.model
 * @Description: TODO(这里要填写用途)
 * @author Administrator Administrator@cychina.cn
 * @date 2015年12月18日 上午9:58:08
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.device.domain.model;

import java.util.Date;

/**
  * @ClassName: DeviceGenalSituation
  * @Description: TODO(这里要填写用途)
  * @author Administrator Administrator@cychina.cn
  * @date 2015年12月18日 上午9:58:08
  *
  */

public class DeviceGenalSituation {
	private String deviceId;
	private String orgId;
	private String deviceType;
	private String statusType;
	private Date statusUpdateTime;
	private String orgPrivilegeCode;
	private String zhiduiPriviCode;
	private String daduiPriviCode;
	private String deviceKey;
	/**
	 * getter method
	 * @return the zhiduiPriviCode
	 */
	
	public String getZhiduiPriviCode() {
		return zhiduiPriviCode;
	}
	/**
	 * setter method
	 * @param zhiduiPriviCode the zhiduiPriviCode to set
	 */
	
	public void setZhiduiPriviCode(String zhiduiPriviCode) {
		this.zhiduiPriviCode = zhiduiPriviCode;
	}
	/**
	 * getter method
	 * @return the daduiPriviCode
	 */
	
	public String getDaduiPriviCode() {
		return daduiPriviCode;
	}
	/**
	 * setter method
	 * @param daduiPriviCode the daduiPriviCode to set
	 */
	
	public void setDaduiPriviCode(String daduiPriviCode) {
		this.daduiPriviCode = daduiPriviCode;
	}
	private String isCertification;
	private Date expireDate;
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
	 * @return the statusType
	 */
	
	public String getStatusType() {
		return statusType;
	}
	/**
	 * setter method
	 * @param statusType the statusType to set
	 */
	
	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}
	/**
	 * getter method
	 * @return the statusUpdateTime
	 */
	
	public Date getStatusUpdateTime() {
		return statusUpdateTime;
	}
	
	/**
	 * setter method
	 * @param statusUpdateTime the statusUpdateTime to set
	 */
	
	public void setStatusUpdateTime(Date statusUpdateTime) {
		this.statusUpdateTime = statusUpdateTime;
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
	 * @return the isCertification
	 */
	
	public String getIsCertification() {
		return isCertification;
	}
	/**
	 * setter method
	 * @param isCertification the isCertification to set
	 */
	
	public void setIsCertification(String isCertification) {
		this.isCertification = isCertification;
	}
	/**
	 * getter method
	 * @return the expireDate
	 */
	
	public Date getExpireDate() {
		return expireDate;
	}
	/**
	 * setter method
	 * @param expireDate the expireDate to set
	 */
	
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
}
