package cy.its.violation.mybatis.model;

public class T_Vio_Asso_Action {
    private String vioActionMatchId;

    private String roadType;

    private String violationType;

    private String violationDesc;

    private String violationCode;

    private Integer limitSpeed;

    private Integer maxRatio;

    private Integer minRatio;

    private String vehicleType;

    public String getVioActionMatchId() {
        return vioActionMatchId;
    }

    public void setVioActionMatchId(String vioActionMatchId) {
        this.vioActionMatchId = vioActionMatchId;
    }

    public String getRoadType() {
        return roadType;
    }

    public void setRoadType(String roadType) {
        this.roadType = roadType;
    }

    public String getViolationType() {
        return violationType;
    }

    public void setViolationType(String violationType) {
        this.violationType = violationType;
    }

    public String getViolationDesc() {
        return violationDesc;
    }

    public void setViolationDesc(String violationDesc) {
        this.violationDesc = violationDesc;
    }

    public String getViolationCode() {
        return violationCode;
    }

    public void setViolationCode(String violationCode) {
        this.violationCode = violationCode;
    }

    public Integer getLimitSpeed() {
        return limitSpeed;
    }

    public void setLimitSpeed(Integer limitSpeed) {
        this.limitSpeed = limitSpeed;
    }

    public Integer getMaxRatio() {
        return maxRatio;
    }

    public void setMaxRatio(Integer maxRatio) {
        this.maxRatio = maxRatio;
    }

    public Integer getMinRatio() {
        return minRatio;
    }

    public void setMinRatio(Integer minRatio) {
        this.minRatio = minRatio;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}