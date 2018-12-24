/**
 * @Title: VisibilityDeviceDto.java
 * @Package cy.its.trafficSituation.rest.dto
 * @Description: TODO(这里要填写用途)
 * @author gyf guanyf@cychina.cn
 * @date 2015年12月21日 下午2:49:22
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.rest.dto;

public class VisibilityDeviceDto extends DeviceDto {
	private String oneMinuteVisibility;

	private String tenMinuteVisibility;

	private String cleanDegre;

	private String caseTemperature;

	private String powerVolatage;

	private String recordTime;

	/**
	 * getter method
	 * @return the oneMinuteVisibility
	 */
	
	public String getOneMinuteVisibility() {
		return oneMinuteVisibility;
	}

	/**
	 * setter method
	 * @param oneMinuteVisibility the oneMinuteVisibility to set
	 */
	
	public void setOneMinuteVisibility(String oneMinuteVisibility) {
		this.oneMinuteVisibility = oneMinuteVisibility;
	}

	/**
	 * getter method
	 * @return the tenMinuteVisibility
	 */
	
	public String getTenMinuteVisibility() {
		return tenMinuteVisibility;
	}

	/**
	 * setter method
	 * @param tenMinuteVisibility the tenMinuteVisibility to set
	 */
	
	public void setTenMinuteVisibility(String tenMinuteVisibility) {
		this.tenMinuteVisibility = tenMinuteVisibility;
	}

	/**
	 * getter method
	 * @return the cleanDegre
	 */
	
	public String getCleanDegre() {
		return cleanDegre;
	}

	/**
	 * setter method
	 * @param cleanDegre the cleanDegre to set
	 */
	
	public void setCleanDegre(String cleanDegre) {
		this.cleanDegre = cleanDegre;
	}

	/**
	 * getter method
	 * @return the caseTemperature
	 */
	
	public String getCaseTemperature() {
		return caseTemperature;
	}

	/**
	 * setter method
	 * @param caseTemperature the caseTemperature to set
	 */
	
	public void setCaseTemperature(String caseTemperature) {
		this.caseTemperature = caseTemperature;
	}

	/**
	 * getter method
	 * @return the powerVolatage
	 */
	
	public String getPowerVolatage() {
		return powerVolatage;
	}

	/**
	 * setter method
	 * @param powerVolatage the powerVolatage to set
	 */
	
	public void setPowerVolatage(String powerVolatage) {
		this.powerVolatage = powerVolatage;
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
