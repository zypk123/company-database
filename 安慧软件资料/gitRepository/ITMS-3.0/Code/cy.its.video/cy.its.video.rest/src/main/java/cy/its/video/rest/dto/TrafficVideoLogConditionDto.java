package cy.its.video.rest.dto;

import cy.its.com.dto.BaseDto;

public class TrafficVideoLogConditionDto extends BaseDto {
	private String cruiseUser;

	private String cruisePlanId;

	private String cruiseBeginTime;

	private String cruiseEndTime;

	public String getCruiseUser() {
		return cruiseUser;
	}

	public void setCruiseUser(String cruiseUser) {
		this.cruiseUser = cruiseUser;
	}

	public String getCruisePlanId() {
		return cruisePlanId;
	}

	public void setCruisePlanId(String cruisePlanId) {
		this.cruisePlanId = cruisePlanId;
	}

	public String getCruiseBeginTime() {
		return cruiseBeginTime;
	}

	public void setCruiseBeginTime(String cruiseBeginTime) {
		this.cruiseBeginTime = cruiseBeginTime;
	}

	public String getCruiseEndTime() {
		return cruiseEndTime;
	}

	public void setCruiseEndTime(String cruiseEndTime) {
		this.cruiseEndTime = cruiseEndTime;
	}

}
