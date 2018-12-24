/**
 * @Title: VisibilityDto.java
 * @Package cy.its.trafficSituation.rest.dto
 * @Description: 能见度Dto
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月7日 下午3:00:22
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
import cy.its.trafficSituation.domain.model.TrafficVisibility;

public class VisibilityDto extends BaseDto {

	private String visibilityDataId;

	private String deviceSysNbr;

	private String orgCode;

	private String siteCode;

	private String oneMinuteVisibility;

	private String tenMinuteVisibility;

	private String cleanDegre;

	private String caseTemperature;

	private String powerVolatage;

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
	 * @return the visibilityDataId
	 */
	
	public String getVisibilityDataId() {
		return visibilityDataId;
	}
	/**
	 * setter method
	 * @param visibilityDataId the visibilityDataId to set
	 */
	
	public void setVisibilityDataId(String visibilityDataId) {
		this.visibilityDataId = visibilityDataId;
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
	public VisibilityDto(){
		
	}
	public VisibilityDto(TrafficVisibility trafficVisibility){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ObjectMapUtils.parseObject(this, trafficVisibility);
		if(trafficVisibility.getOneMinuteVisibility() != null){
			this.setOneMinuteVisibility(trafficVisibility.getOneMinuteVisibility().toString());
		}
		if(trafficVisibility.getTenMinuteVisibility() != null){
			this.setTenMinuteVisibility(trafficVisibility.getTenMinuteVisibility().toString());
		}
		if(trafficVisibility.getCleanDegre() != null){
			this.setCleanDegre(trafficVisibility.getCleanDegre().toString());
		}
		if(trafficVisibility.getCaseTemperature() != null){
			this.setCaseTemperature(trafficVisibility.getCaseTemperature().toString());
		}
		if(trafficVisibility.getRecordTime() != null){
			this.setRecordTime(sdf.format(trafficVisibility.getRecordTime()));
		}
	}

}
