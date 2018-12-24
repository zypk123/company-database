package cy.its.device.domain.model;

public class FlowSys extends SysParam {
	
	private String deviceId;

    private String relatedLedId;
    
	@Override
	public void attatchSys(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getRelatedLedId() {
		return relatedLedId;
	}

	public void setRelatedLedId(String relatedLedId) {
		this.relatedLedId = relatedLedId;
	}
}
