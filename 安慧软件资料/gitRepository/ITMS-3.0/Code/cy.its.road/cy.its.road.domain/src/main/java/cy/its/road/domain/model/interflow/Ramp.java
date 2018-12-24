package cy.its.road.domain.model.interflow;

public class Ramp {
    private String rampId;

    private String interflowId;

    private String rampType;

    private String rampLength;

    private String rampLimitSpeed;

    public String getRampId() {
        return rampId;
    }

    public void setRampId(String rampId) {
        this.rampId = rampId;
    }

    public String getInterflowId() {
        return interflowId;
    }

    public void setInterflowId(String interflowId) {
        this.interflowId = interflowId;
    }

    public String getRampType() {
        return rampType;
    }

    public void setRampType(String rampType) {
        this.rampType = rampType;
    }

    public String getRampLength() {
        return rampLength;
    }

    public void setRampLength(String rampLength) {
        this.rampLength = rampLength;
    }

    public String getRampLimitSpeed() {
        return rampLimitSpeed;
    }

    public void setRampLimitSpeed(String rampLimitSpeed) {
        this.rampLimitSpeed = rampLimitSpeed;
    }
}