package cy.its.trafficSituation.domain.model;

import java.util.Date;

public class TrafficManualState {
    private String stateControlId;

    private String roadId;
    
    private String regionalId;

    private String controlledState;

    private String controlledBy;

    private Date controlTime;

    private String orgPrivilegeCode;

    private Date startTime;

    private Date endTime;

    private String reason;

    public String getStateControlId() {
        return stateControlId;
    }

    public void setStateControlId(String stateControlId) {
        this.stateControlId = stateControlId;
    }

    public String getRoadId() {
        return roadId;
    }

    public void setRoadId(String roadId) {
        this.roadId = roadId;
    }

    /**
	 * getter method
	 * @return the regionalId
	 */
	
	public String getRegionalId() {
		return regionalId;
	}

	/**
	 * setter method
	 * @param regionalId the regionalId to set
	 */
	
	public void setRegionalId(String regionalId) {
		this.regionalId = regionalId;
	}

	public String getControlledState() {
        return controlledState;
    }

    public void setControlledState(String controlledState) {
        this.controlledState = controlledState;
    }

    public String getControlledBy() {
        return controlledBy;
    }

    public void setControlledBy(String controlledBy) {
        this.controlledBy = controlledBy;
    }

    public Date getControlTime() {
        return controlTime;
    }

    public void setControlTime(Date controlTime) {
        this.controlTime = controlTime;
    }

    public String getOrgPrivilegeCode() {
        return orgPrivilegeCode;
    }

    public void setOrgPrivilegeCode(String orgPrivilegeCode) {
        this.orgPrivilegeCode = orgPrivilegeCode;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}