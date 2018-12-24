package cy.its.service.device.statusChecker.model;

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
 *      Company: 安徽超远信息技术有限公司 Copyright: Copyright (c) 2015
 */
public class DeviceCfg {

	@MapColumn
	private String deviceSysId;

	@MapColumn
	private String deviceSysNbr;

	@MapColumn
	private String architecture;

	@MapColumn
	private String orgCode;

	@MapColumn
	private String orgPrivilegeCode;

	@MapColumn
	private String deviceType;

	@MapColumn
	private String siteCode;

	@MapColumn
	private String cameraId;

	@MapColumn
	private String cameraNbr;

	@MapColumn
	private String cameraKey;

	private String statusType;

	private Date startTime;

	private Date endTime;

	public String getDeviceSysId() {
		return deviceSysId;
	}

	public void setDeviceSysId(String deviceSysId) {
		this.deviceSysId = deviceSysId;
	}

	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}

	public void setDeviceSysNbr(String deviceSysNbr) {
		this.deviceSysNbr = deviceSysNbr;
	}
	
	public String getArchitecture() {
		return architecture;
	}

	public void setArchitecture(String architecture) {
		this.architecture = architecture;
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

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getCameraId() {
		return cameraId;
	}

	public void setCameraId(String cameraId) {
		this.cameraId = cameraId;
	}

	public String getCameraNbr() {
		return cameraNbr;
	}

	public void setCameraNbr(String cameraNbr) {
		this.cameraNbr = cameraNbr;
	}

	public String getCameraKey() {
		return cameraKey;
	}

	public void setCameraKey(String cameraKey) {
		this.cameraKey = cameraKey;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
