package cy.its.service.device.statusAnalysis.model;

import java.util.Date;

public class SysStatus {

	public String deviceId;
	public String statusType;
	public Date statusUpdateTime;
	public Date startTime;
	public Date endTime;

	public SysStatus(){}
	public SysStatus(String deviceId, String statusType, Date statusUpdateTime, Date startTime, Date endTime) {
		this.deviceId = deviceId;
		this.statusType = statusType;
		this.statusUpdateTime = statusUpdateTime;
		this.startTime = startTime;
		this.endTime = endTime;
	}
}
