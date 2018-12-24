package cy.its.syscfg.mybatis.model;

public class T_Sys_Branch {
    private String roadBranchId;

    private String junctionId;

    private String centralLineAngle;

    private String canalization;

    private String relatedCrossCode;

    private String laneNum;

    public String getRoadBranchId() {
        return roadBranchId;
    }

    public void setRoadBranchId(String roadBranchId) {
        this.roadBranchId = roadBranchId;
    }

    public String getJunctionId() {
        return junctionId;
    }

    public void setJunctionId(String junctionId) {
        this.junctionId = junctionId;
    }

    public String getCentralLineAngle() {
        return centralLineAngle;
    }

    public void setCentralLineAngle(String centralLineAngle) {
        this.centralLineAngle = centralLineAngle;
    }

    public String getCanalization() {
        return canalization;
    }

    public void setCanalization(String canalization) {
        this.canalization = canalization;
    }

    public String getRelatedCrossCode() {
        return relatedCrossCode;
    }

    public void setRelatedCrossCode(String relatedCrossCode) {
        this.relatedCrossCode = relatedCrossCode;
    }

    public String getLaneNum() {
        return laneNum;
    }

    public void setLaneNum(String laneNum) {
        this.laneNum = laneNum;
    }
}