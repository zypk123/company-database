package cy.its.vehTrack.rest.dto;

import java.util.List;

public class DataLatencyConvertBean {
	
	//设备编号
	private String deviceNbr;
	private int hour;
	private int step;
	//总延迟时间
	private double tatolCost;
	//x轴
	private List<Integer> xAxis;
	//y轴
	private List<Double> yAxis;
	
    private String site_id;
    private String site_name;
    //卡口类型
    private String tollgateType;
    
	public String getTollgateType() {
		return tollgateType;
	}
	public void setTollgateType(String tollgateType) {
		this.tollgateType = tollgateType;
	}
	public String getDeviceNbr() {
		return deviceNbr;
	}
	public void setDeviceNbr(String deviceNbr) {
		this.deviceNbr = deviceNbr;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public double getTatolCost() {
		return tatolCost;
	}
	public String getSite_name() {
		return site_name;
	}
	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}
	public void setTatolCost(double tatolCost) {
		this.tatolCost = tatolCost;
	}
	public List<Integer> getxAxis() {
		return xAxis;
	}
	public void setxAxis(List<Integer> xAxis) {
		this.xAxis = xAxis;
	}
	public List<Double> getyAxis() {
		return yAxis;
	}
	public void setyAxis(List<Double> yAxis) {
		this.yAxis = yAxis;
	}
	public String getSite_id() {
		return site_id;
	}
	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}
	
}
