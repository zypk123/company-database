package cy.its.device.domain.model;

public class BaseStation extends SysParam {

	private String deviceId;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public void attatchSys(String deviceId) {
		this.setDeviceId(deviceId);
	}

}
