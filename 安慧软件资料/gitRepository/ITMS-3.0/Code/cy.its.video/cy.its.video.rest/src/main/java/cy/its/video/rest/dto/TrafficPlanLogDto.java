package cy.its.video.rest.dto;

import java.util.List;

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

	private String orgId;
	
	private List<TrafficVideoLogDto> eventLst;

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

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public List<TrafficVideoLogDto> getEventLst() {
		return eventLst;
	}

	public void setEventLst(List<TrafficVideoLogDto> eventLst) {
		this.eventLst = eventLst;
	}
	
	
}
