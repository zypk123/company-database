package cy.its.trafficSituation.domain.model;

import java.util.Date;

public class TrafficState {
    private String roadStateId;

    private String roadSectionId;

    private String setionId;

    private String regionId;

    private String trafficState;

    private String stateDuration;

    private Date updateTime;

    public String getRoadStateId() {
        return roadStateId;
    }

    public void setRoadStateId(String roadStateId) {
        this.roadStateId = roadStateId;
    }

    public String getRoadSectionId() {
        return roadSectionId;
    }

    public void setRoadSectionId(String roadSectionId) {
        this.roadSectionId = roadSectionId;
    }

    public String getSetionId() {
        return setionId;
    }

    public void setSetionId(String setionId) {
        this.setionId = setionId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getTrafficState() {
        return trafficState;
    }

    public void setTrafficState(String trafficState) {
        this.trafficState = trafficState;
    }

    public String getStateDuration() {
        return stateDuration;
    }

    public void setStateDuration(String stateDuration) {
        this.stateDuration = stateDuration;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}