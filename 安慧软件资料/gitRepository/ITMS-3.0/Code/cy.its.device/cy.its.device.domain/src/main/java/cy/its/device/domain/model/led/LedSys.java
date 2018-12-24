package cy.its.device.domain.model.led;

import cy.its.device.domain.model.Sys;

public class LedSys {

	private String deviceId;
	
	private Led led;
	
	private Sys sys;
	
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Led getLed() {
		return led;
	}

	public void setLed(Led led) {
		this.led = led;
	}

	public Sys getSys() {
		return sys;
	}

	public void setSys(Sys sys) {
		this.sys = sys;
	}
}
