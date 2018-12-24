package cy.its.syscfg.mybatis.model;

import java.util.Date;

public class T_Sys_Section {
    private String sectionId;

    private String laneNum;

    private String hasEmergencyLane;

    private String hasBicycleLane;

    private String hasPavement;

    private String sectionMaxFlow;

    private Date maxFlowTime;

    private String roadId;

    private String siteId;

    private String directionType;

    private String enterOrExit;

    private Short limitLarge;

    private Short limitSmall;

    private Short limitLower;

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getLaneNum() {
        return laneNum;
    }

    public void setLaneNum(String laneNum) {
        this.laneNum = laneNum;
    }

    public String getHasEmergencyLane() {
        return hasEmergencyLane;
    }

    public void setHasEmergencyLane(String hasEmergencyLane) {
        this.hasEmergencyLane = hasEmergencyLane;
    }

    public String getHasBicycleLane() {
        return hasBicycleLane;
    }

    public void setHasBicycleLane(String hasBicycleLane) {
        this.hasBicycleLane = hasBicycleLane;
    }

    public String getHasPavement() {
        return hasPavement;
    }

    public void setHasPavement(String hasPavement) {
        this.hasPavement = hasPavement;
    }

    public String getSectionMaxFlow() {
        return sectionMaxFlow;
    }

    public void setSectionMaxFlow(String sectionMaxFlow) {
        this.sectionMaxFlow = sectionMaxFlow;
    }

    public Date getMaxFlowTime() {
        return maxFlowTime;
    }

    public void setMaxFlowTime(Date maxFlowTime) {
        this.maxFlowTime = maxFlowTime;
    }

    public String getRoadId() {
        return roadId;
    }

    public void setRoadId(String roadId) {
        this.roadId = roadId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getDirectionType() {
        return directionType;
    }

    public void setDirectionType(String directionType) {
        this.directionType = directionType;
    }

    public String getEnterOrExit() {
        return enterOrExit;
    }

    public void setEnterOrExit(String enterOrExit) {
        this.enterOrExit = enterOrExit;
    }

    public Short getLimitLarge() {
        return limitLarge;
    }

    public void setLimitLarge(Short limitLarge) {
        this.limitLarge = limitLarge;
    }

    public Short getLimitSmall() {
        return limitSmall;
    }

    public void setLimitSmall(Short limitSmall) {
        this.limitSmall = limitSmall;
    }

    public Short getLimitLower() {
        return limitLower;
    }

    public void setLimitLower(Short limitLower) {
        this.limitLower = limitLower;
    }
}