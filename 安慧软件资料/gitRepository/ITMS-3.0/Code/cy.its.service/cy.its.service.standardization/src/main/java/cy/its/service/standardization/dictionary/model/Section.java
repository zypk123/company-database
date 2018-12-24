package cy.its.service.standardization.dictionary.model;

import cy.its.service.common.dataAccess.MapColumn;

public class Section {
	
	@MapColumn
    private String sectionId;

    @MapColumn
    private String siteId;

    @MapColumn
    private String laneNum;

//    private String hasEmergencyLane;
//
//    private String hasBicycleLane;

//    private String hasPavement;

    @MapColumn
    private String directionType;
    
    @MapColumn
    private String directionName;

//    private String enterOrExit;

//    private String sectionMaxFlow;

//    private Date maxFlowTime;

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }


    public String getLaneNum() {
        return laneNum;
    }

    public void setLaneNum(String laneNum) {
        this.laneNum = laneNum;
    }

//    public String getHasEmergencyLane() {
//        return hasEmergencyLane;
//    }
//
//    public void setHasEmergencyLane(String hasEmergencyLane) {
//        this.hasEmergencyLane = hasEmergencyLane;
//    }

//    public String getHasBicycleLane() {
//        return hasBicycleLane;
//    }
//
//    public void setHasBicycleLane(String hasBicycleLane) {
//        this.hasBicycleLane = hasBicycleLane;
//    }
//
//    public String getHasPavement() {
//        return hasPavement;
//    }
//
//    public void setHasPavement(String hasPavement) {
//        this.hasPavement = hasPavement;
//    }

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

//    public String getEnterOrExit() {
//        return enterOrExit;
//    }
//
//    public void setEnterOrExit(String enterOrExit) {
//        this.enterOrExit = enterOrExit;
//    }
//
//    public String getSectionMaxFlow() {
//        return sectionMaxFlow;
//    }
//
//    public void setSectionMaxFlow(String sectionMaxFlow) {
//        this.sectionMaxFlow = sectionMaxFlow;
//    }
//
//    public Date getMaxFlowTime() {
//        return maxFlowTime;
//    }
//
//    public void setMaxFlowTime(Date maxFlowTime) {
//        this.maxFlowTime = maxFlowTime;
//    }
}