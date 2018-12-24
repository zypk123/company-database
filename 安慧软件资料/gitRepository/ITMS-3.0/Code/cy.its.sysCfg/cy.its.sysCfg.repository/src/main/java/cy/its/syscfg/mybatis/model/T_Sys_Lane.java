package cy.its.syscfg.mybatis.model;

public class T_Sys_Lane {
    private String laneId;

    private String laneNbr;

    private String directionType;

    private String laneType;

    private String isRestrict;

    private Short limitLarge;

    private Short limitSmall;

    private Short limitLower;

    private String directionName;

    private String sectionId;

    private String siteId;

    public String getLaneId() {
        return laneId;
    }

    public void setLaneId(String laneId) {
        this.laneId = laneId;
    }

    public String getLaneNbr() {
        return laneNbr;
    }

    public void setLaneNbr(String laneNbr) {
        this.laneNbr = laneNbr;
    }

    public String getDirectionType() {
        return directionType;
    }

    public void setDirectionType(String directionType) {
        this.directionType = directionType;
    }

    public String getLaneType() {
        return laneType;
    }

    public void setLaneType(String laneType) {
        this.laneType = laneType;
    }

    public String getIsRestrict() {
        return isRestrict;
    }

    public void setIsRestrict(String isRestrict) {
        this.isRestrict = isRestrict;
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

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

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
}