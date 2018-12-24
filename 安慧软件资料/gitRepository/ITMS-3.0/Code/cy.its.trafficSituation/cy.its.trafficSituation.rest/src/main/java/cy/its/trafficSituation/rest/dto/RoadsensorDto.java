/**
 * @Title: RoadsensorDto.java
 * @Package cy.its.trafficSituation.rest.dto
 * @Description: 路感Dto
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月7日 下午3:00:59
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.rest.dto;

import java.text.SimpleDateFormat;

import cy.its.com.dto.BaseDto;
import cy.its.com.util.ObjectMapUtils;
import cy.its.trafficSituation.domain.model.TrafficRoadsensor;

public class RoadsensorDto extends BaseDto {
	private String roadsensorDataId;

	private String deviceSysNbr;

	private String orgCode;

	private String siteCode;

	private String roadTemperature;

	private String roadbedTemperature;

	private String waterFileHeigh;

	private String salinity;

	private String freezingTemperature;

	private String roadCondition;

	private String recordTime;

	// 关联字段
	private String deviceName;
	private String siteName;
	private String lonLat;

	// 查询条件中的字段
	private String startTime;
	private String endTime;
	
	/**
	 * getter method
	 * @return the roadsensorDataId
	 */
	
	public String getRoadsensorDataId() {
		return roadsensorDataId;
	}
	/**
	 * setter method
	 * @param roadsensorDataId the roadsensorDataId to set
	 */
	
	public void setRoadsensorDataId(String roadsensorDataId) {
		this.roadsensorDataId = roadsensorDataId;
	}
	/**
	 * getter method
	 * @return the deviceSysNbr
	 */
	
	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}
	/**
	 * setter method
	 * @param deviceSysNbr the deviceSysNbr to set
	 */
	
	public void setDeviceSysNbr(String deviceSysNbr) {
		this.deviceSysNbr = deviceSysNbr;
	}
	/**
	 * getter method
	 * @return the orgCode
	 */
	
	public String getOrgCode() {
		return orgCode;
	}
	/**
	 * setter method
	 * @param orgCode the orgCode to set
	 */
	
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	/**
	 * getter method
	 * @return the siteCode
	 */
	
	public String getSiteCode() {
		return siteCode;
	}
	/**
	 * setter method
	 * @param siteCode the siteCode to set
	 */
	
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	/**
	 * getter method
	 * @return the roadTemperature
	 */
	
	public String getRoadTemperature() {
		return roadTemperature;
	}
	/**
	 * setter method
	 * @param roadTemperature the roadTemperature to set
	 */
	
	public void setRoadTemperature(String roadTemperature) {
		this.roadTemperature = roadTemperature;
	}
	/**
	 * getter method
	 * @return the roadbedTemperature
	 */
	
	public String getRoadbedTemperature() {
		return roadbedTemperature;
	}
	/**
	 * setter method
	 * @param roadbedTemperature the roadbedTemperature to set
	 */
	
	public void setRoadbedTemperature(String roadbedTemperature) {
		this.roadbedTemperature = roadbedTemperature;
	}
	/**
	 * getter method
	 * @return the waterFileHeigh
	 */
	
	public String getWaterFileHeigh() {
		return waterFileHeigh;
	}
	/**
	 * setter method
	 * @param waterFileHeigh the waterFileHeigh to set
	 */
	
	public void setWaterFileHeigh(String waterFileHeigh) {
		this.waterFileHeigh = waterFileHeigh;
	}
	/**
	 * getter method
	 * @return the salinity
	 */
	
	public String getSalinity() {
		return salinity;
	}
	/**
	 * setter method
	 * @param salinity the salinity to set
	 */
	
	public void setSalinity(String salinity) {
		this.salinity = salinity;
	}
	/**
	 * getter method
	 * @return the freezingTemperature
	 */
	
	public String getFreezingTemperature() {
		return freezingTemperature;
	}
	/**
	 * setter method
	 * @param freezingTemperature the freezingTemperature to set
	 */
	
	public void setFreezingTemperature(String freezingTemperature) {
		this.freezingTemperature = freezingTemperature;
	}
	/**
	 * getter method
	 * @return the roadCondition
	 */
	
	public String getRoadCondition() {
		return roadCondition;
	}
	/**
	 * setter method
	 * @param roadCondition the roadCondition to set
	 */
	
	public void setRoadCondition(String roadCondition) {
		this.roadCondition = roadCondition;
	}
	/**
	 * getter method
	 * @return the recordTime
	 */
	
	public String getRecordTime() {
		return recordTime;
	}
	/**
	 * setter method
	 * @param recordTime the recordTime to set
	 */
	
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	/**
	 * getter method
	 * @return the deviceName
	 */
	
	public String getDeviceName() {
		return deviceName;
	}
	/**
	 * setter method
	 * @param deviceName the deviceName to set
	 */
	
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	/**
	 * getter method
	 * @return the siteName
	 */
	
	public String getSiteName() {
		return siteName;
	}
	/**
	 * setter method
	 * @param siteName the siteName to set
	 */
	
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	/**
	 * getter method
	 * @return the lonLat
	 */
	
	public String getLonLat() {
		return lonLat;
	}
	/**
	 * setter method
	 * @param lonLat the lonLat to set
	 */
	
	public void setLonLat(String lonLat) {
		this.lonLat = lonLat;
	}
	/**
	 * getter method
	 * @return the startTime
	 */
	
	public String getStartTime() {
		return startTime;
	}
	/**
	 * setter method
	 * @param startTime the startTime to set
	 */
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * getter method
	 * @return the endTime
	 */
	
	public String getEndTime() {
		return endTime;
	}
	/**
	 * setter method
	 * @param endTime the endTime to set
	 */
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public RoadsensorDto(){
		
	}
	public RoadsensorDto(TrafficRoadsensor tRoadsensor){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ObjectMapUtils.parseObject(this, tRoadsensor);
		if(tRoadsensor.getRecordTime() != null){
			this.setRecordTime(sdf.format(tRoadsensor.getRecordTime()));
		}
		if(tRoadsensor.getRoadbedTemperature() != null){
			this.setRoadbedTemperature(tRoadsensor.getRoadbedTemperature().toString());
		}
		if(tRoadsensor.getRoadTemperature() != null){
			this.setRoadTemperature(tRoadsensor.getRoadTemperature().toString());
		}
		if(tRoadsensor.getFreezingTemperature() != null){
			this.setFreezingTemperature(tRoadsensor.getFreezingTemperature().toString());
		}
		if(tRoadsensor.getWaterFileHeigh() != null){
			this.setWaterFileHeigh(tRoadsensor.getWaterFileHeigh().toString());
		}
		if(tRoadsensor.getSalinity() != null){
			this.setSalinity(tRoadsensor.getSalinity().toString());
		}
	}
}
