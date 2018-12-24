package cy.its.trafficSituation.rest.dto;

import java.math.BigDecimal;

import cy.its.com.util.ObjectMapUtils;
import cy.its.trafficSituation.domain.model.TrafficAlarmConfig;

public class TrafficAlarmConfigDto {
	 private String alarmValueId;

	    private String alarmValueType;

	    private String roadType;

	    private String min;

	    private String max;

	    private String alarmGrade;

	    private BigDecimal limitSpeed;

	    private String diffusionContent;

	    private String remark;

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

	    public String getRoadType() {
	        return roadType;
	    }

	    public void setRoadType(String roadType) {
	        this.roadType = roadType;
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
	    public TrafficAlarmConfigDto(){
	    	
	    }
	    public TrafficAlarmConfigDto(TrafficAlarmConfig trafficAlarmConfig){
	    	ObjectMapUtils.parseObject(this, trafficAlarmConfig);
	    }
	    public TrafficAlarmConfig parseToTrafficAlarmConfig(){
	    	TrafficAlarmConfig trafficAlarmConfig=new TrafficAlarmConfig();
	    	ObjectMapUtils.parseObject(trafficAlarmConfig, this);
	    	return trafficAlarmConfig;
	    }
}
