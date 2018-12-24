package cy.its.vehTrack.rest.dto;

import com.wordnik.swagger.annotations.ApiParam;

import cy.its.com.dto.BaseDto;

public class DataLatencyAnalysisInputBean  extends BaseDto{
	
	@ApiParam(name="分析开始时间",value="fromDate",required=true)
	private String fromDate;
	@ApiParam(name="分析结束时间",value="endDate",required=true)
	private String endDate;
	
	/**
	 * 行政区划编码
	 */
	@ApiParam(name="行政区划编码",value="distritCode")
	private String distritCode;
	
	@ApiParam(name="组织机构编码",value="orgId")
	private String orgId;
	
	
	@ApiParam(name="卡口设备编号",value="deviceSysNbr")
	private String deviceSysNbr;
	
	@ApiParam(name="监控中心或者接入平台Id",value="serverId")
	private String serverId;
	
	@ApiParam(name="卡口等级(类型)",value="deviceType")
	private String deviceType;
	
	@ApiParam(name="开始时间段",value="startTime")
	private String startTime;
	
	@ApiParam(name="结束时间段",value="结束时间段")
	private String endTime;

	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}

	public void setDeviceSysNbr(String deviceSysNbr) {
		this.deviceSysNbr = deviceSysNbr;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDistritCode() {
		return distritCode;
	}

	public void setDistritCode(String distritCode) {
		this.distritCode = distritCode;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
