package cy.its.video.domain.model;

public class TrafficPlanVideo {
	private String relatedVideoId;

	private String videoPlanId;

	private String deviceId;

	private Short sortIndex;

	private String presetList;

	private Short cruiseTime;

	private String sysComponentId;

	public String getRelatedVideoId() {
		return relatedVideoId;
	}

	public void setRelatedVideoId(String relatedVideoId) {
		this.relatedVideoId = relatedVideoId;
	}

	public String getVideoPlanId() {
		return videoPlanId;
	}

	public void setVideoPlanId(String videoPlanId) {
		this.videoPlanId = videoPlanId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Short getSortIndex() {
		return sortIndex;
	}

	public void setSortIndex(Short sortIndex) {
		this.sortIndex = sortIndex;
	}

	public String getPresetList() {
		return presetList;
	}

	public void setPresetList(String presetList) {
		this.presetList = presetList;
	}

	public Short getCruiseTime() {
		return cruiseTime;
	}

	public void setCruiseTime(Short cruiseTime) {
		this.cruiseTime = cruiseTime;
	}

	public String getSysComponentId() {
		return sysComponentId;
	}

	public void setSysComponentId(String sysComponentId) {
		this.sysComponentId = sysComponentId;
	}

}