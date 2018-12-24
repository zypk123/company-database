/**
 * @Title: DeviceCertiStatusModel.java
 * @Package cy.its.platform.common.model
 * @Description: TODO(这里要填写用途)
 * @author Administrator Administrator@cychina.cn
 * @date 2015年12月25日 下午4:39:06
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.platform.common.model;

import java.util.Date;

/**
  * @ClassName: DeviceCertiStatusModel
  * @Description: TODO(这里要填写用途)
  * @author Administrator Administrator@cychina.cn
  * @date 2015年12月25日 下午4:39:06
  *
  */

public class DeviceCertiStatusModel {
	private String deviceId;
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
	 * @return the certiExpireDate
	 */
	
	public Date getCertiExpireDate() {
		return certiExpireDate;
	}
	/**
	 * setter method
	 * @param certiExpireDate the certiExpireDate to set
	 */
	
	public void setCertiExpireDate(Date certiExpireDate) {
		this.certiExpireDate = certiExpireDate;
	}
	private String orgPrivilegeCode;
	private Date certiExpireDate;

}
