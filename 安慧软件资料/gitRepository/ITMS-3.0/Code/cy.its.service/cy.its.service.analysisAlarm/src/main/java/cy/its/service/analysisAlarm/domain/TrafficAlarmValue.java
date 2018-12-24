package cy.its.service.analysisAlarm.domain;

/**
* @Title: TrafficAlertValue.java 
* @Package cy.its.service.section.domain 
* @Description: 阈值信息 
* @author lil@cychina.cn
* @date 2015年11月5日 下午3:35:47 
* @version V1.0   
 */
public class TrafficAlarmValue {
	/**
	 *预警类型
	 */
    private String  alarmValueType;
	/**
	 *道路类型
	 */
    private String  roadType;
	/**
	 *最小值
	 */
    private String  min;
	/**
	 *最大值
	 */
    private String  max;
	/**
	 *预警级别
	 */
    private String  alermGrade;
    

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

	public double getMin() {
		return Double.valueOf(min);
	}

	public void setMin(String min) {
		this.min = min;
	}

	public double getMax() {
		return Double.valueOf(max);
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getAlermGrade() {
		return alermGrade;
	}

	public void setAlermGrade(String alermGrade) {
		this.alermGrade = alermGrade;
	}
}
