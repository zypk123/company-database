package cy.its.video.domain.criteria;

import cy.its.com.domain.Criteria;

public class TrafficPlanLogCriteria extends Criteria {
	private String cruiseUser;

	private String cruisePlanId;

	private String cruiseBeginTime;

	private String cruiseEndTime;

	private String orgId;

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

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
}
