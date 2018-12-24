package cy.its.service.device.statusAnalysis.model;

import java.util.Date;

import cy.its.service.common.dataAccess.MapColumn;


/**
 * @Title: DeviceCfg.java
 * @Package cy.its.service.device.statusAnalysis.model
 * @Description: 监控系统配置信息
 * @author STJ lijun@cychina.cn
 * @date 2015年11月4日 下午3:20:40
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */
public class DeviceCfg {
	
	@MapColumn
	private String deviceId;
	
	@MapColumn
	private String deviceSysNbr;
	
	@MapColumn
	private String orgCode;
	
	@MapColumn
	private String orgPrivilegeCode;
	
	@MapColumn
	private String deviceType;
		
	private String statusType;
	
	@MapColumn
	private Date statusUpdateTime;
	
	@MapColumn
	private String siteCode;
	
	@MapColumn
	private String sysComponentId;
	
	@MapColumn
	private String deviceNbr;
	
//	@MapColumn
//	private String disableFlag;
		
	private Date startTime;
	
	private Date endTime;
	

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}

	public void setDeviceSysNbr(String deviceSysNbr) {
		this.deviceSysNbr = deviceSysNbr;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public Date getStatusUpdateTime() {
		return statusUpdateTime;
	}

	public void setStatusUpdateTime(Date statusUpdateTime) {
		this.statusUpdateTime = statusUpdateTime;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getSysComponentId() {
		return sysComponentId;
	}

	public void setSysComponentId(String sysComponentId) {
		this.sysComponentId = sysComponentId;
	}

	public String getDeviceNbr() {
		return deviceNbr;
	}

	public void setDeviceNbr(String deviceNbr) {
		this.deviceNbr = deviceNbr;
	}

//	public String getDisableFlag() {
//		return disableFlag;
//	}
//
//	public void setDisableFlag(String disableFlag) {
//		this.disableFlag = disableFlag;
//	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date beginTime) {
		this.startTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	
}
