package cy.its.video.domain.model;

import java.util.Date;

public class TrafficVideoLog {
	private String videoLogId;

	private String videoPlanLogId;

	private String deviceId;

	private String questionFind;

	private String resultDesc;

	private Date videoPlanStartTime;

	private Date videoPlanEndTime;

	private String place;

	public String getVideoLogId() {
		return videoLogId;
	}

	public void setVideoLogId(String videoLogId) {
		this.videoLogId = videoLogId;
	}

	public String getVideoPlanLogId() {
		return videoPlanLogId;
	}

	public void setVideoPlanLogId(String videoPlanLogId) {
		this.videoPlanLogId = videoPlanLogId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getQuestionFind() {
		return questionFind;
	}

	public void setQuestionFind(String questionFind) {
		this.questionFind = questionFind;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public Date getVideoPlanStartTime() {
		return videoPlanStartTime;
	}

	public void setVideoPlanStartTime(Date videoPlanStartTime) {
		this.videoPlanStartTime = videoPlanStartTime;
	}

	public Date getVideoPlanEndTime() {
		return videoPlanEndTime;
	}

	public void setVideoPlanEndTime(Date videoPlanEndTime) {
		this.videoPlanEndTime = videoPlanEndTime;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
}