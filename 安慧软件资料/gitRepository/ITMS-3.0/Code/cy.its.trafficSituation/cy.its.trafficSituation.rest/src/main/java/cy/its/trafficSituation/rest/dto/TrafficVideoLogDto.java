package cy.its.trafficSituation.rest.dto;

import cy.its.com.dto.BaseDto;

/**
 * 网巡视频日志
 * 
 * @author Administrator
 *
 */
public class TrafficVideoLogDto extends BaseDto {
	private String videoLogId;

	private String videoPlanLogId;

	private String deviceId;

	private String questionFind;

	private String resultDesc;

	private String videoPlanStartTime;

	private String videoPlanEndTime;

	/**
	 * 根据deviceId生成
	 */
	private String place;

	/**
	 * 根据deviceId生成
	 */
	private String videoDeviceName;;

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

	public String getVideoPlanStartTime() {
		return videoPlanStartTime;
	}

	public void setVideoPlanStartTime(String videoPlanStartTime) {
		this.videoPlanStartTime = videoPlanStartTime;
	}

	public String getVideoPlanEndTime() {
		return videoPlanEndTime;
	}

	public void setVideoPlanEndTime(String videoPlanEndTime) {
		this.videoPlanEndTime = videoPlanEndTime;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getVideoDeviceName() {
		return videoDeviceName;
	}

	public void setVideoDeviceName(String videoDeviceName) {
		this.videoDeviceName = videoDeviceName;
	}
}
