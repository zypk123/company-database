package cy.its.service.analysisAlarm.domain;

import java.util.Date;

import cy.its.service.util.TableColumn;

/**
* @Title: WeatherAlarm.java 
* @Package cy.its.service.weather.domain 
* @Description:预警表插入信息
* @author lil@cychina.cn
* @date 2015年12月15日 下午6:16:36 
* @version V1.0   
 */
public class WeatherAlarm {
	
	/**
	 *预警编码
	 */
	@TableColumn(name="ALARM_EVENT_ID")
	private  String  alarmEventId="";
	/**
	 * 设备系统编码
	 */
	@TableColumn(name="DEVICE_SYS_NBR")
	private  String  deviceSysNbr="";
	
	/**
	* 机构代码
	*/
	@TableColumn(name="ORG_CODE")
	private String orgCode="";
	
	/*
	 *区间ID
	 */
	@TableColumn(name="REGIONAL_ID")
	private String regionalId="";
	
	/*
	 *断面ID
	 */
	@TableColumn(name="SECTION_ID") 
	private String sectionId="";
	
	/**
	* 管辖机构权限代码
	*/
	@TableColumn(name="ORG_PRIVILEGE_CODE")
	private String orgPrivilegeCode="";
	
	/**
	* 点位编码
	*/
	@TableColumn(name="SITE_CODE") 
	private String siteCode;
	
	
	/**
	 * 状态
	 */
	@TableColumn(name="ALARM_GRADE")
	private  String alarmGrade;
	
	
	/**
	 * 预警类型
	 */
	@TableColumn(name="ALARM_EVENT_TYPE")
	private  String  alarmEventType;
	

	/**
	 * 预警子类型
	 */
	@TableColumn(name="SUB_ALARM_EVENT")
	private  String  subAlarmEvent;
	
	/**
	 * 预警时间
	 */
	@TableColumn(name="START_ALARM_TIME")
	private  Date    startAlarmTime;
	
	/**
	 * 预警时间
	 */
	@TableColumn(name="END_ALARM_TIME")
	private  Date    endAlarmTime;
	
	/**
	 * 预警时间
	 */
	@TableColumn(name="ALARM_DESC")
	private  String    alarmDesc="";
	
	/**
	 * 预警值
	 */
	@TableColumn(name="WEATHER_DATA")
	private String weatherData="0.00";
	
	/*
	 * 阈值范围
	 */
	@TableColumn(name="ALARM_VALUE_ID")
	private String  alarmValueId="";
	
	
	private String  deviceId="";
	
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getWeatherData() {
		return weatherData;
	}

	public void setWeatherData(String weatherData) {
		this.weatherData = weatherData;
	}
	
	public String getAlarmValueId() {
		return alarmValueId;
	}

	public void setAlarmValueId(String alarmValueId) {
		this.alarmValueId = alarmValueId;
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

	public String getAlarmGrade() {
		return alarmGrade;
	}

	public void setAlarmGrade(String alarmGrade) {
		this.alarmGrade = alarmGrade;
	}

	public String getAlarmEventType() {
		return alarmEventType;
	}

	public void setAlarmEventType(String alarmEventType) {
		this.alarmEventType = alarmEventType;
	}

	public String getSubAlarmEvent() {
		return subAlarmEvent;
	}

	public void setSubAlarmEvent(String subAlarmEvent) {
		this.subAlarmEvent = subAlarmEvent;
	}

	public Date getStartAlarmTime() {
		return startAlarmTime;
	}

	public void setStartAlarmTime(Date startAlarmTime) {
		this.startAlarmTime = startAlarmTime;
	}

	public Date getEndAlarmTime() {
		return endAlarmTime;
	}

	public void setEndAlarmTime(Date endAlarmTime) {
		this.endAlarmTime = endAlarmTime;
	}

	public String getRegionalId() {
		return regionalId;
	}

	public void setRegionalId(String regionalId) {
		this.regionalId = regionalId;
	}

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getAlarmDesc() {
		return alarmDesc;
	}

	public void setAlarmDesc(String alarmDesc) {
		this.alarmDesc = alarmDesc;
	}

	public String getAlarmEventId() {
		return alarmEventId;
	}

	public void setAlarmEventId(String alarmEventId) {
		this.alarmEventId = alarmEventId;
	}
	
	
	
	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}
	
	
}
