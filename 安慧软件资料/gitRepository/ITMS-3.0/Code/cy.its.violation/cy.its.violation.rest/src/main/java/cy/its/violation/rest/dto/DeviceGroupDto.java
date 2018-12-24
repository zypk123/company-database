/**
 * @Title: DeviceGroupDto.java
 * @Package cy.its.violation.rest.dto
 * @Description: TODO(这里要填写用途)
 * @author wangyf wangyf@cychina.cn
 * @date 2015年11月9日 下午7:17:17
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.violation.rest.dto;

import java.util.List;

/**
  * @ClassName: DeviceGroupDto
  * @Description: TODO(这里要填写用途)
  * @author wangyf wangyf@cychina.cn
  * @date 2015年11月9日 下午7:17:17
  *
  */

public class DeviceGroupDto {
	//分配用户设备Id
	private String assoUserDeviceId;
	//机构Id
	private String orgId;
	//用户Id
	private String userId;
	//用户名
	private String userName;
	//设备编号
	private String deviceSysNbr;
	private String deviceName;
	//设备名字
	String  deviceNames;
	//设备Ids
	String deviceSysNbrs;
	
	public String getAssoUserDeviceId() {
		return assoUserDeviceId;
	}
	public void setAssoUserDeviceId(String assoUserDeviceId) {
		this.assoUserDeviceId = assoUserDeviceId;
	}
	
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	  
	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}
	public void setDeviceSysNbr(String deviceSysNbr) {
		this.deviceSysNbr = deviceSysNbr;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceNames() {
		return deviceNames;
	}
	public void setDeviceNames(String deviceNames) {
		this.deviceNames = deviceNames;
	}
	public String getDeviceSysNbrs() {
		return deviceSysNbrs;
	}
	public void setDeviceSysNbrs(String deviceSysNbrs) {
		this.deviceSysNbrs = deviceSysNbrs;
	}
	
}
