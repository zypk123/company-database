package cy.its.service.common.dataModel;

import java.util.Date;
import java.util.Map;

/**
 * @Title: DeviceStatus.java
 * @Package cy.its.service.common.dataModel
 * @Description: 规范化的设备心跳
 * @author STJ lijun@cychina.cn
 * @date 2015年11月8日 下午9:32:49
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */
public class DeviceStatus extends Model {
	
	private String deviceSysNbr;
	private String deviceNbr;
	private Date statusTime;
	private Date startTime;
//	private Date deviceTime;
	private int status;
	private String faultDetail;
	private String gpsLocation;
	private Map<String, Integer> statsInfo;
	private Map<String, String> otherStatusInfo;

	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}

	public void setDeviceSysNbr(String deviceSysNbr) {
		this.deviceSysNbr = deviceSysNbr;
	}

	public String getDeviceNbr() {
		return deviceNbr;
	}

	public void setDeviceNbr(String deviceNbr) {
		this.deviceNbr = deviceNbr;
	}

	public Date getStatusTime() {
		return statusTime;
	}

	public void setStatusTime(Date statusTime) {
		this.statusTime = statusTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

//	public Date getDeviceTime() {
//		return deviceTime;
//	}
//
//	public void setDeviceTime(Date deviceTime) {
//		this.deviceTime = deviceTime;
//	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFaultDetail() {
		return faultDetail;
	}

	public void setFaultDetail(String faultDetail) {
		this.faultDetail = faultDetail;
	}

	public String getGpsLocation() {
		return gpsLocation;
	}

	public void setGpsLocation(String gpsLocation) {
		this.gpsLocation = gpsLocation;
	}

	public Map<String, Integer> getStatsInfo() {
		return statsInfo;
	}

	public void setStatsInfo(Map<String, Integer> statsInfo) {
		this.statsInfo = statsInfo;
	}

	public Map<String, String> getOtherStatusInfo() {
		return otherStatusInfo;
	}

	public void setOtherStatusInfo(Map<String, String> otherStatusInfo) {
		this.otherStatusInfo = otherStatusInfo;
	}

}
