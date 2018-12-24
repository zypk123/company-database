package cy.its.road.domain.model.road;


public class Branch  {
	
    private String roadBranchId;

    private String crossId;

    private String isOneWay;

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

    public String getCrossId() {
        return crossId;
    }

    public void setCrossId(String crossId) {
        this.crossId = crossId;
    }

    public String getIsOneWay() {
        return isOneWay;
    }

    public void setIsOneWay(String isOneWay) {
        this.isOneWay = isOneWay;
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