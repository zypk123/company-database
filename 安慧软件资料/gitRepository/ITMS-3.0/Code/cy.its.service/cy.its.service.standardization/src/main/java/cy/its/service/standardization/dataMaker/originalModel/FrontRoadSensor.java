package cy.its.service.standardization.dataMaker.originalModel;

import java.math.BigDecimal;

public class FrontRoadSensor extends BaseOrginalModel{
	private String devNbr;  //设备编号
    private BigDecimal roanTemperature;//路面温度
    private BigDecimal temperature;//路基温度
    private BigDecimal waterFilmHeigh; //水膜厚度
    private BigDecimal salinity; //盐度
    private BigDecimal freezingTemperature;//融冰点
    private String roadCondition;//路面状况 0	干燥1	微湿2	潮湿3	Lyc  ?4	冰/雪5	盐浓度6	黑冰7	Critical ？90	未知
    private String recordTime;//记录时间
    private String remark;
    
	public String getDevNbr() {
		return devNbr;
	}
	public void setDevNbr(String devNbr) {
		this.devNbr = devNbr;
	}
	public BigDecimal getRoanTemperature() {
		return roanTemperature;
	}
	public void setRoanTemperature(BigDecimal roanTemperature) {
		this.roanTemperature = roanTemperature;
	}
	public BigDecimal getTemperature() {
		return temperature;
	}
	public void setTemperature(BigDecimal temperature) {
		this.temperature = temperature;
	}
	public BigDecimal getWaterFilmHeigh() {
		return waterFilmHeigh;
	}
	public void setWaterFilmHeigh(BigDecimal waterFilmHeigh) {
		this.waterFilmHeigh = waterFilmHeigh;
	}
	public BigDecimal getSalinity() {
		return salinity;
	}
	public void setSalinity(BigDecimal salinity) {
		this.salinity = salinity;
	}
	public BigDecimal getFreezingTemperature() {
		return freezingTemperature;
	}
	public void setFreezingTemperature(BigDecimal freezingTemperature) {
		this.freezingTemperature = freezingTemperature;
	}
	public String getRoadCondition() {
		return roadCondition;
	}
	public void setRoadCondition(String roadCondition) {
		this.roadCondition = roadCondition;
	}
	public String getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
