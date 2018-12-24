package cy.its.trafficSituation.rest.dto;

import cy.its.com.dto.BaseDto;

/**
 * Õ¯—≤‘§∞∏»’÷æ
 * 
 * @author Administrator
 *
 */
public class TrafficPlanLogDto extends BaseDto {
	private String videoPlanLogId;

	private String videoPlanId;

	private String videoPlanName;

	private String videoPlanNbr;

	private String videoPlanStartTime;

	private String videoPlanEndTime;

	private String videoPlanPerson;

	private String computerIp;

	public String getVideoPlanName() {
		return videoPlanName;
	}

	public void setVideoPlanName(String videoPlanName) {
		this.videoPlanName = videoPlanName;
	}

	public String getVideoPlanLogId() {
		return videoPlanLogId;
	}

	public void setVideoPlanLogId(String videoPlanLogId) {
		this.videoPlanLogId = videoPlanLogId;
	}

	public String getVideoPlanId() {
		return videoPlanId;
	}

	public void setVideoPlanId(String videoPlanId) {
		this.videoPlanId = videoPlanId;
	}

	public String getVideoPlanNbr() {
		return videoPlanNbr;
	}

	public void setVideoPlanNbr(String videoPlanNbr) {
		this.videoPlanNbr = videoPlanNbr;
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

	public String getVideoPlanPerson() {
		return videoPlanPerson;
	}

	public void setVideoPlanPerson(String videoPlanPerson) {
		this.videoPlanPerson = videoPlanPerson;
	}

	public String getComputerIp() {
		return computerIp;
	}

	public void setComputerIp(String computerIp) {
		this.computerIp = computerIp;
	}
}
