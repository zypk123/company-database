package cy.its.trafficSituation.domain.model;

import java.util.Date;

public class TrafficManualReport {
    private String manualReportTraId;

    private String reportType;

    private String roadId;

    private String roadSectionId;

    private String startMileage;

    private String endMileage;

    private String directionType;

    private String directionName;

    private String trafficState;

    private String meteorologyType;

    private String eventType;

    private String reportBy;

    private Date reportTime;

    private String eventReleaseType;

    private String remark;

    public String getManualReportTraId() {
        return manualReportTraId;
    }

    public void setManualReportTraId(String manualReportTraId) {
        this.manualReportTraId = manualReportTraId;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
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

    public String getStartMileage() {
        return startMileage;
    }

    public void setStartMileage(String startMileage) {
        this.startMileage = startMileage;
    }

    public String getEndMileage() {
        return endMileage;
    }

    public void setEndMileage(String endMileage) {
        this.endMileage = endMileage;
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

    public String getTrafficState() {
        return trafficState;
    }

    public void setTrafficState(String trafficState) {
        this.trafficState = trafficState;
    }

    public String getMeteorologyType() {
        return meteorologyType;
    }

    public void setMeteorologyType(String meteorologyType) {
        this.meteorologyType = meteorologyType;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}