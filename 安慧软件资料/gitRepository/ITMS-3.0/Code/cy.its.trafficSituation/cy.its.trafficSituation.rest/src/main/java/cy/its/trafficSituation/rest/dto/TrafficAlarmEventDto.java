package cy.its.trafficSituation.rest.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cy.its.com.dto.BaseDto;
import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.trafficSituation.domain.model.TrafficAlarmEvent;
import cy.its.trafficSituation.domain.model.TrafficEventProcess;

public class TrafficAlarmEventDto extends BaseDto {

	private String alarmEventId;

	private String alarmValueId;

	private String orgCode;

	private String deviceSysNbr;

	private String siteCode;

	private String regionalId;

	private String sectionId;

	private String alarmEventType;

	private String subAlarmEvent;

	private String startAlarmTime;

	private String endAlarmTime;

	private String alarmGrade;

	private String alarmDesc;

	private String noFlowPeriod;

	private String regionSaturation;

	private String validity;

	private String confirmPerson;

	private String confirmTime;

	// 用于显示的名称
	private String name;

	// 查询条件中的字段
	private String startTime;
	private String endTime;
	// 点位表中的字段
	private String siteName;
	private String siteLongitude;
	private String siteLatitude;

	// 气象数据表中的字段
	private String weatherData;
	private String min;
	private String max;

	// 事件处理单中的字段
	private String eventProcessId;
	private String manualReportTraId;
	private String dealMethod;
	private String dealPerson;
	private String dealTime;
	private String dealPersonPhone;
	private String remark;
	// 断面的方向字段
	private String directionType;
	// 区间所用到的字段
	private String regionalName;
	private String distance;
	private String regionDirectionType;
	private String regionalDesignCapacity;

	/**
	 * getter method
	 * 
	 * @return the dealPerson
	 */

	public String getDealPerson() {
		return dealPerson;
	}

	/**
	 * setter method
	 * 
	 * @param dealPerson
	 *            the dealPerson to set
	 */

	public void setDealPerson(String dealPerson) {
		this.dealPerson = dealPerson;
	}

	/**
	 * getter method
	 * 
	 * @return the dealTime
	 */

	public String getDealTime() {
		return dealTime;
	}

	/**
	 * setter method
	 * 
	 * @param dealTime
	 *            the dealTime to set
	 */

	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}

	/**
	 * getter method
	 * 
	 * @return the dealPersonPhone
	 */

	public String getDealPersonPhone() {
		return dealPersonPhone;
	}

	/**
	 * setter method
	 * 
	 * @param dealPersonPhone
	 *            the dealPersonPhone to set
	 */

	public void setDealPersonPhone(String dealPersonPhone) {
		this.dealPersonPhone = dealPersonPhone;
	}

	/**
	 * getter method
	 * 
	 * @return the name
	 */

	public String getName() {
		return name;
	}

	/**
	 * setter method
	 * 
	 * @param name
	 *            the name to set
	 */

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getter method
	 * 
	 * @return the regionalName
	 */

	public String getRegionalName() {
		return regionalName;
	}

	/**
	 * setter method
	 * 
	 * @param regionalName
	 *            the regionalName to set
	 */

	public void setRegionalName(String regionalName) {
		this.regionalName = regionalName;
	}

	/**
	 * getter method
	 * 
	 * @return the distance
	 */

	public String getDistance() {
		return distance;
	}

	/**
	 * setter method
	 * 
	 * @param distance
	 *            the distance to set
	 */

	public void setDistance(String distance) {
		this.distance = distance;
	}

	/**
	 * getter method
	 * 
	 * @return the regionDirectionType
	 */

	public String getRegionDirectionType() {
		return regionDirectionType;
	}

	/**
	 * setter method
	 * 
	 * @param regionDirectionType
	 *            the regionDirectionType to set
	 */

	public void setRegionDirectionType(String regionDirectionType) {
		this.regionDirectionType = regionDirectionType;
	}

	/**
	 * getter method
	 * 
	 * @return the regionalDesignCapacity
	 */

	public String getRegionalDesignCapacity() {
		return regionalDesignCapacity;
	}

	/**
	 * setter method
	 * 
	 * @param regionalDesignCapacity
	 *            the regionalDesignCapacity to set
	 */

	public void setRegionalDesignCapacity(String regionalDesignCapacity) {
		this.regionalDesignCapacity = regionalDesignCapacity;
	}

	/**
	 * getter method
	 * 
	 * @return the directionType
	 */

	public String getDirectionType() {
		return directionType;
	}

	/**
	 * setter method
	 * 
	 * @param directionType
	 *            the directionType to set
	 */

	public void setDirectionType(String directionType) {
		this.directionType = directionType;
	}

	// 设备名称
	private String deviceName;

	/**
	 * getter method
	 * 
	 * @return the deviceName
	 */

	public String getDeviceName() {
		return deviceName;
	}

	/**
	 * setter method
	 * 
	 * @param deviceName
	 *            the deviceName to set
	 */

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getWeatherData() {
		return weatherData;
	}

	public void setWeatherData(String weatherData) {
		this.weatherData = weatherData;
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

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteLongitude() {
		return siteLongitude;
	}

	public void setSiteLongitude(String siteLongitude) {
		this.siteLongitude = siteLongitude;
	}

	public String getSiteLatitude() {
		return siteLatitude;
	}

	public void setSiteLatitude(String siteLatitude) {
		this.siteLatitude = siteLatitude;
	}

	public String getAlarmEventId() {
		return alarmEventId;
	}

	public void setAlarmEventId(String alarmEventId) {
		this.alarmEventId = alarmEventId;
	}

	public String getAlarmValueId() {
		return alarmValueId;
	}

	public void setAlarmValueId(String alarmValueId) {
		this.alarmValueId = alarmValueId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}

	public void setDeviceSysNbr(String deviceSysNbr) {
		this.deviceSysNbr = deviceSysNbr;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
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

	public String getAlarmGrade() {
		return alarmGrade;
	}

	public void setAlarmGrade(String alarmGrade) {
		this.alarmGrade = alarmGrade;
	}

	public String getAlarmDesc() {
		return alarmDesc;
	}

	public void setAlarmDesc(String alarmDesc) {
		this.alarmDesc = alarmDesc;
	}

	public String getNoFlowPeriod() {
		return noFlowPeriod;
	}

	public void setNoFlowPeriod(String noFlowPeriod) {
		this.noFlowPeriod = noFlowPeriod;
	}

	public String getRegionSaturation() {
		return regionSaturation;
	}

	public void setRegionSaturation(String regionSaturation) {
		this.regionSaturation = regionSaturation;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public String getConfirmPerson() {
		return confirmPerson;
	}

	public void setConfirmPerson(String confirmPerson) {
		this.confirmPerson = confirmPerson;
	}

	public String getStartAlarmTime() {
		return startAlarmTime;
	}

	public void setStartAlarmTime(String startAlarmTime) {
		this.startAlarmTime = startAlarmTime;
	}

	public String getEndAlarmTime() {
		return endAlarmTime;
	}

	public void setEndAlarmTime(String endAlarmTime) {
		this.endAlarmTime = endAlarmTime;
	}

	public String getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEventProcessId() {
		return eventProcessId;
	}

	public void setEventProcessId(String eventProcessId) {
		this.eventProcessId = eventProcessId;
	}

	public String getManualReportTraId() {
		return manualReportTraId;
	}

	public void setManualReportTraId(String manualReportTraId) {
		this.manualReportTraId = manualReportTraId;
	}

	public String getDealMethod() {
		return dealMethod;
	}

	public void setDealMethod(String dealMethod) {
		this.dealMethod = dealMethod;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public TrafficAlarmEventDto() {

	}

	public TrafficAlarmEventDto(TrafficAlarmEvent trafficAlarmEvent) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ObjectMapUtils.parseObject(this, trafficAlarmEvent);
		if (trafficAlarmEvent.getConfirmTime() != null) {
			this.setConfirmTime(sdf.format(trafficAlarmEvent.getConfirmTime()));
		}
		if (trafficAlarmEvent.getEndAlarmTime() != null) {
			this.setEndAlarmTime(sdf.format(trafficAlarmEvent.getEndAlarmTime()));
		}
		if (trafficAlarmEvent.getStartAlarmTime() != null) {
			this.setStartAlarmTime(sdf.format(trafficAlarmEvent.getStartAlarmTime()));
		}
		if(trafficAlarmEvent.getAlarmValueId()!=null){
			String[] arr=trafficAlarmEvent.getAlarmValueId().split(",");
			if(arr.length==2){
				this.setMin(arr[0]);
				this.setMax(arr[1]);
			}
		}
	}

	public TrafficAlarmEventDto(TrafficAlarmEvent trafficAlarmEvent, TrafficEventProcess trafficEventProcess) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		ObjectMapUtils.parseObject(this, trafficAlarmEvent);
		ObjectMapUtils.parseObject(this, trafficEventProcess);
		if (trafficAlarmEvent.getEndAlarmTime() != null) {
			this.setEndAlarmTime(sdf.format(trafficAlarmEvent.getEndAlarmTime()));
		}
		if (trafficAlarmEvent.getStartAlarmTime() != null) {
			this.setStartAlarmTime(sdf.format(trafficAlarmEvent.getStartAlarmTime()));
		}
		if (trafficAlarmEvent.getConfirmTime() != null) {
			this.setConfirmTime(sdf.format(trafficAlarmEvent.getConfirmTime()));
		} else {
			this.setConfirmTime(null);
		}
		if (trafficEventProcess.getDealTime() != null) {
			this.setConfirmTime(sdf.format(trafficEventProcess.getDealTime()));
		} 
		//下面的没看明白？？？
		if(trafficAlarmEvent.getAlarmValueId()!=null){
			String[] arr=trafficAlarmEvent.getAlarmValueId().split(",");
			if(arr.length==2){
				this.setMin(arr[0]);
				this.setMax(arr[1]);
			}
		}
		 
	}

	public TrafficAlarmEvent parseToTrafficAlarmEvent() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TrafficAlarmEvent trafficAlarmEvent = new TrafficAlarmEvent();
		ObjectMapUtils.parseObject(trafficAlarmEvent, this);

		if (!StringUtil.isNullOrEmpty(this.getConfirmTime())) {
			trafficAlarmEvent.setConfirmTime(sdf.parse(this.getConfirmTime()));
		}
		if (!StringUtil.isNullOrEmpty(this.getEndAlarmTime())) {
			trafficAlarmEvent.setEndAlarmTime(sdf.parse(this.getEndAlarmTime()));
		}
		if (!StringUtil.isNullOrEmpty(this.getStartAlarmTime())) {
			trafficAlarmEvent.setStartAlarmTime(sdf.parse(this.getStartAlarmTime()));
		}
		return trafficAlarmEvent;
	}

	public TrafficEventProcess parseToEventProcess() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TrafficEventProcess trafficEventProcess = new TrafficEventProcess();
		ObjectMapUtils.parseObject(trafficEventProcess, this);

		if (!StringUtil.isNullOrEmpty(this.getDealTime())) {
			trafficEventProcess.setDealTime(sdf.parse(this.getDealTime()));
		}
		return trafficEventProcess;
	}

}
