package cy.its.video.domain.model;

import java.util.Date;

public class TrafficPlanLog {
	private String videoPlanLogId;

	private String videoPlanId;

	private String videoPlanNbr;

	private Date videoPlanStartTime;

	private Date videoPlanEndTime;

	private String videoPlanPerson;

	private String computerIp;

	private String orgId;

	private String videoPlanName;

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

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getVideoPlanName() {
		return videoPlanName;
	}

	public void setVideoPlanName(String videoPlanName) {
		this.videoPlanName = videoPlanName;
	}

}