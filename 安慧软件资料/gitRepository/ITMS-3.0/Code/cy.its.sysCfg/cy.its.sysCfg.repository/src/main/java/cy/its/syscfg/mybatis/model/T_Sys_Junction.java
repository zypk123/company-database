package cy.its.syscfg.mybatis.model;

import java.util.Date;

public class T_Sys_Junction {
    private String junctionId;

    private String roadId;

    private String junctionCode;

    private String districtCode;

    private String junctionName;

    private String junctionType;

    private String isCross;

    private String crossRoadId;

    private String roadBranchNum;

    private String roadStructure;

    private String roadLinear;

    private String roadIsolation;

    private String protectFacilities;

    private Integer juncBeginMeter;

    private Integer juncEndMeter;

    private Date updateTime;

    private String orgId;

    public String getJunctionId() {
        return junctionId;
    }

    public void setJunctionId(String junctionId) {
        this.junctionId = junctionId;
    }

    public String getRoadId() {
        return roadId;
    }

    public void setRoadId(String roadId) {
        this.roadId = roadId;
    }

    public String getJunctionCode() {
        return junctionCode;
    }

    public void setJunctionCode(String junctionCode) {
        this.junctionCode = junctionCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getJunctionName() {
        return junctionName;
    }

    public void setJunctionName(String junctionName) {
        this.junctionName = junctionName;
    }

    public String getJunctionType() {
        return junctionType;
    }

    public void setJunctionType(String junctionType) {
        this.junctionType = junctionType;
    }

    public String getIsCross() {
        return isCross;
    }

    public void setIsCross(String isCross) {
        this.isCross = isCross;
    }

    public String getCrossRoadId() {
        return crossRoadId;
    }

    public void setCrossRoadId(String crossRoadId) {
        this.crossRoadId = crossRoadId;
    }

    public String getRoadBranchNum() {
        return roadBranchNum;
    }

    public void setRoadBranchNum(String roadBranchNum) {
        this.roadBranchNum = roadBranchNum;
    }

    public String getRoadStructure() {
        return roadStructure;
    }

    public void setRoadStructure(String roadStructure) {
        this.roadStructure = roadStructure;
    }

    public String getRoadLinear() {
        return roadLinear;
    }

    public void setRoadLinear(String roadLinear) {
        this.roadLinear = roadLinear;
    }

    public String getRoadIsolation() {
        return roadIsolation;
    }

    public void setRoadIsolation(String roadIsolation) {
        this.roadIsolation = roadIsolation;
    }

    public String getProtectFacilities() {
        return protectFacilities;
    }

    public void setProtectFacilities(String protectFacilities) {
        this.protectFacilities = protectFacilities;
    }

    public Integer getJuncBeginMeter() {
        return juncBeginMeter;
    }

    public void setJuncBeginMeter(Integer juncBeginMeter) {
        this.juncBeginMeter = juncBeginMeter;
    }

    public Integer getJuncEndMeter() {
        return juncEndMeter;
    }

    public void setJuncEndMeter(Integer juncEndMeter) {
        this.juncEndMeter = juncEndMeter;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}