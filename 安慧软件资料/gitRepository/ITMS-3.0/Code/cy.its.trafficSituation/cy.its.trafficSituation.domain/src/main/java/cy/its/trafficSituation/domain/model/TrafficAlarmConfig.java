package cy.its.trafficSituation.domain.model;

import java.math.BigDecimal;

public class TrafficAlarmConfig {
    private String alarmValueId;

    private String alarmValueType;

    private String roadConditionType;

    private String min;

    private String max;

    private String alarmGrade;

    private BigDecimal limitSpeed;

    private String diffusionContent;

    private String remark;
    
    private String roadType;

    public String getAlarmValueId() {
        return alarmValueId;
    }

    public void setAlarmValueId(String alarmValueId) {
        this.alarmValueId = alarmValueId;
    }

    public String getAlarmValueType() {
        return alarmValueType;
    }

    public void setAlarmValueType(String alarmValueType) {
        this.alarmValueType = alarmValueType;
    }

    public String getRoadConditionType() {
        return roadConditionType;
    }

    public void setRoadConditionType(String roadConditionType) {
        this.roadConditionType = roadConditionType;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getAlarmGrade() {
        return alarmGrade;
    }

    public void setAlarmGrade(String alarmGrade) {
        this.alarmGrade = alarmGrade;
    }

    public BigDecimal getLimitSpeed() {
        return limitSpeed;
    }

    public void setLimitSpeed(BigDecimal limitSpeed) {
        this.limitSpeed = limitSpeed;
    }

    public String getDiffusionContent() {
        return diffusionContent;
    }

    public void setDiffusionContent(String diffusionContent) {
        this.diffusionContent = diffusionContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	/**
	 * getter method
	 * @return the roadType
	 */
	
	public String getRoadType() {
		return roadType;
	}

	/**
	 * setter method
	 * @param roadType the roadType to set
	 */
	
	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}
}