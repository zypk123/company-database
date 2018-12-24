/**
 * @Title: RoadsensorDeviceDto.java
 * @Package cy.its.trafficSituation.rest.dto
 * @Description: TODO(这里要填写用途)
 * @author gyf guanyf@cychina.cn
 * @date 2015年12月21日 下午2:56:32
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.rest.dto;

public class RoadsensorDeviceDto extends DeviceDto {
	private String roadTemperature;

	private String roadbedTemperature;

	private String waterFileHeigh;

	private String salinity;

	private String freezingTemperature;

	private String roadCondition;

	private String recordTime;

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
}
