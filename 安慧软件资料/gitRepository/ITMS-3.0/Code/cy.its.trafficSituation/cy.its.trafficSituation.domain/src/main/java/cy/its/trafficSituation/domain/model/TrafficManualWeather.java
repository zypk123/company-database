package cy.its.trafficSituation.domain.model;

import java.util.Date;

public class TrafficManualWeather {
    private String badWeatherId;

    private String meteorologyType;

    private String roadId;

    private String roadSectionId;

    private String directionType;

    private String directionName;

    private String lonLat;

    private String eventDesc;

    private String reportBy;

    private Date reportTime;

    private String eventReleaseType;

    private String orgId;

    private String orgPrivilegeCode;

    private String remark;

    public String getBadWeatherId() {
        return badWeatherId;
    }

    public void setBadWeatherId(String badWeatherId) {
        this.badWeatherId = badWeatherId;
    }

    public String getMeteorologyType() {
        return meteorologyType;
    }

    public void setMeteorologyType(String meteorologyType) {
        this.meteorologyType = meteorologyType;
    }

    public String getRoadId() {
        return roadId;
    }

    public void setRoadId(String roadId) {
        this.roadId = roadId;
    }

    public String getRoadSectionId() {
        return roadSectionId;
    }

    public void setRoadSectionId(String roadSectionId) {
        this.roadSectionId = roadSectionId;
    }

    public String getDirectionType() {
        return directionType;
    }

    public void setDirectionType(String directionType) {
        this.directionType = directionType;
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    public String getLonLat() {
        return lonLat;
    }

    public void setLonLat(String lonLat) {
        this.lonLat = lonLat;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getReportBy() {
        return reportBy;
    }

    public void setReportBy(String reportBy) {
        this.reportBy = reportBy;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public String getEventReleaseType() {
        return eventReleaseType;
    }

    public void setEventReleaseType(String eventReleaseType) {
        this.eventReleaseType = eventReleaseType;
    }


    /**
	 * getter method
	 * @return the orgId
	 */
	
	public String getOrgId() {
		return orgId;
	}

	/**
	 * setter method
	 * @param orgId the orgId to set
	 */
	
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgPrivilegeCode() {
        return orgPrivilegeCode;
    }

    public void setOrgPrivilegeCode(String orgPrivilegeCode) {
        this.orgPrivilegeCode = orgPrivilegeCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}