package cy.its.device.domain.model;

public class EventDetection extends SysParam {
    private String deviceId;

    private String eventDetectionList;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getEventDetectionList() {
        return eventDetectionList;
    }

    public void setEventDetectionList(String eventDetectionList) {
        this.eventDetectionList = eventDetectionList;
    }

	@Override
	public void attatchSys(String deviceId) {
		this.setDeviceId(deviceId);
	}
}