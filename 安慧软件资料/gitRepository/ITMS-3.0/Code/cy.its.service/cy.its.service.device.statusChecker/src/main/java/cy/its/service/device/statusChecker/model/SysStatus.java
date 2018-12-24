package cy.its.service.device.statusChecker.model;

public class SysStatus {

	public String deviceId;
	public String statusType;
	public long statusUpdateTime;

	public SysStatus(){}
	public SysStatus(String deviceId, String statusType, long statusUpdateTime) {
		this.deviceId = deviceId;
		this.statusType = statusType;
		this.statusUpdateTime = statusUpdateTime;
	}
}
