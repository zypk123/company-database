package cy.its.service.analysisAlarm.domain;

import java.util.Date;

/**
* @Title: RoadSensor.java 
* @Package cy.its.service.weather.domain 
* @Description:路感接收数据信息
* @author lil@cychina.cn
* @date 2015年11月13日 下午3:09:02 
* @version V1.0   
 */
public class RoadSensor {
	/**
	 *系统编号
	 */
	private String  deviceSysNbr;
	/**
	 *机构代码
	 */
	private String  orgCode;
	/**
	 *点位代码
	 */
	private String  siteCode;
	/**
	 *路面温度
	 */
	private double  roadTemperature;
	/**
	 *路基温度
	 */
	private double  roadbedTemperature;
	/**
	 *水膜厚度
	 */
	private double  waterFileHeigh;
	/**
	 *含盐量
	 */
	private double  salinity;
	/**
	 *结冰点温度
	 */
	private double  freezingTemperature;
	
	/**
	 * 道路类型
	 */
	private String roadType;
	/**
	 *道路状况
	 */
	private String  roadCondition;
	/**
	 *记录时间
	 */
	private Date  recordTime;
	/**
	 *机构权限过滤代码
	 */
	private String  orgPrivilegeCode;
	
	/**
	 *设备ID
	 */
	private String  deviceId="";
	
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}
	public void setDeviceSysNbr(String deviceSysNbr) {
		this.deviceSysNbr = deviceSysNbr;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public double getRoadTemperature() {
		return roadTemperature;
	}
	public void setRoadTemperature(double roadTemperature) {
		this.roadTemperature = roadTemperature;
	}
	public double getRoadbedTemperature() {
		return roadbedTemperature;
	}
	public void setRoadbedTemperature(double roadbedTemperature) {
		this.roadbedTemperature = roadbedTemperature;
	}
	public double getWaterFileHeigh() {
		return waterFileHeigh;
	}
	public void setWaterFileHeigh(double waterFileHeigh) {
		this.waterFileHeigh = waterFileHeigh;
	}
	public double getSalinity() {
		return salinity;
	}
	public void setSalinity(double salinity) {
		this.salinity = salinity;
	}
	public double getFreezingTemperature() {
		return freezingTemperature;
	}
	public void setFreezingTemperature(double freezingTemperature) {
		this.freezingTemperature = freezingTemperature;
	}
	public String getRoadType() {
		return roadType;
	}
	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}
	public String getRoadCondition() {
		return roadCondition;
	}
	public void setRoadCondition(String roadCondition) {
		this.roadCondition = roadCondition;
	}
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}
	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}
}
