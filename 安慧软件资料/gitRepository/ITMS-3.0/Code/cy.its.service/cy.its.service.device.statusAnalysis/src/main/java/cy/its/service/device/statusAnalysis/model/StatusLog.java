package cy.its.service.device.statusAnalysis.model;

import java.util.Date;

import cy.its.service.common.dataAccess.MapColumn;

public class StatusLog {

	/**
	 * 电子监控系统ID
	 */
	@MapColumn
	public String deviceId;

	/**
	 * 设备状态。1：正常；2：脱机；3：故障
	 */
	@MapColumn
	public String statusType;

	/**
	 * 开始时间
	 */
	@MapColumn
	public Date startTime;

	/**
	 * 结束时间
	 */
	@MapColumn
	public Date endTime;

	public StatusLog(){}
	
	// /**
	// * 持续时长
	// */
	// public Long durationSecs;
	// , Long durationSecs
	public StatusLog(String deviceId, String statusType, Date startTime, Date endTime) {
		this.deviceId = deviceId;
		this.statusType = statusType;
		this.startTime = startTime;
		this.endTime = endTime;
		// this.durationSecs = durationSecs;
	}
	
	public String getDeviceId() {
		return this.deviceId;
	}
}
