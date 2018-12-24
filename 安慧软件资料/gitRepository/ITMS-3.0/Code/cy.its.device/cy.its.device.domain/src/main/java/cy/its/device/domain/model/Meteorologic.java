package cy.its.device.domain.model;

public class Meteorologic extends SysParam  {
    private String deviceId;

    private String relatedVideoId;

    private String relatedLedId;

    private String relatedSpeedDeviceId;

    private String isWeatherSupport;

    private String isVisibilitySupport;

    private String isRoadsensorSupport;
    
    public String relatedVariableSpeed;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getRelatedVideoId() {
        return relatedVideoId;
    }

    public void setRelatedVideoId(String relatedVideoId) {
        this.relatedVideoId = relatedVideoId;
    }

    public String getRelatedLedId() {
        return relatedLedId;
    }

    public void setRelatedLedId(String relatedLedId) {
        this.relatedLedId = relatedLedId;
    }

    public String getRelatedSpeedDeviceId() {
        return relatedSpeedDeviceId;
    }

    public void setRelatedSpeedDeviceId(String relatedSpeedDeviceId) {
        this.relatedSpeedDeviceId = relatedSpeedDeviceId;
    }

    public String getIsWeatherSupport() {
        return isWeatherSupport;
    }

    public void setIsWeatherSupport(String isWeatherSupport) {
        this.isWeatherSupport = isWeatherSupport;
    }

    public String getIsVisibilitySupport() {
        return isVisibilitySupport;
    }

    public void setIsVisibilitySupport(String isVisibilitySupport) {
        this.isVisibilitySupport = isVisibilitySupport;
    }

    public String getIsRoadsensorSupport() {
        return isRoadsensorSupport;
    }

    public void setIsRoadsensorSupport(String isRoadsensorSupport) {
        this.isRoadsensorSupport = isRoadsensorSupport;
    }
	
    
	public String getRelatedVariableSpeed() {
		return relatedVariableSpeed;
	}

	public void setRelatedVariableSpeed(String relatedVariableSpeed) {
		this.relatedVariableSpeed = relatedVariableSpeed;
	}

		@Override
	public void attatchSys(String deviceId) {
		this.setDeviceId(deviceId);
	}

}