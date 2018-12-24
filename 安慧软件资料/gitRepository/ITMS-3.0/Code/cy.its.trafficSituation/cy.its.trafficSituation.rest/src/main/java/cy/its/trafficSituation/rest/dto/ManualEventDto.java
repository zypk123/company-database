/**
 * @Title: manualEventDto.java
 * @Package cy.its.trafficSituation.rest.dto
 * @Description: 人工登记事件dto
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月10日 下午3:35:48
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.rest.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cy.its.com.dto.BaseDto;
import cy.its.com.util.ObjectMapUtils;
import cy.its.trafficSituation.domain.model.TrafficManualEvent;

public class ManualEventDto extends BaseDto {
	private String roadEventId;

	private String eventType;

	private String roadId;

	private String roadSectionId;

	private String directionType;

	private String directionName;

	private String lonLat;

	private String eventDesc;

	private String reportBy;

	private String reportTime;

	private String eventReleaseType;

	private String orgId;

	private String orgPrivilegeCode;

	private String remark;

	// 查询条件中的字段
	private String startTime;
	private String endTime;
	private String lon;
	private String lat;
	/**
	 * getter method
	 * @return the roadEventId
	 */
	
	public String getRoadEventId() {
		return roadEventId;
	}
	/**
	 * setter method
	 * @param roadEventId the roadEventId to set
	 */
	
	public void setRoadEventId(String roadEventId) {
		this.roadEventId = roadEventId;
	}
	/**
	 * getter method
	 * @return the eventType
	 */
	
	public String getEventType() {
		return eventType;
	}
	/**
	 * setter method
	 * @param eventType the eventType to set
	 */
	
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	/**
	 * getter method
	 * @return the roadId
	 */
	
	public String getRoadId() {
		return roadId;
	}
	/**
	 * setter method
	 * @param roadId the roadId to set
	 */
	
	public void setRoadId(String roadId) {
		this.roadId = roadId;
	}
	/**
	 * getter method
	 * @return the roadSectionId
	 */
	
	public String getRoadSectionId() {
		return roadSectionId;
	}
	/**
	 * setter method
	 * @param roadSectionId the roadSectionId to set
	 */
	
	public void setRoadSectionId(String roadSectionId) {
		this.roadSectionId = roadSectionId;
	}
	/**
	 * getter method
	 * @return the directionType
	 */
	
	public String getDirectionType() {
		return directionType;
	}
	/**
	 * setter method
	 * @param directionType the directionType to set
	 */
	
	public void setDirectionType(String directionType) {
		this.directionType = directionType;
	}
	/**
	 * getter method
	 * @return the directionName
	 */
	
	public String getDirectionName() {
		return directionName;
	}
	/**
	 * setter method
	 * @param directionName the directionName to set
	 */
	
	public void setDirectionName(String directionName) {
		this.directionName = directionName;
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
	 * @return the eventDesc
	 */
	
	public String getEventDesc() {
		return eventDesc;
	}
	/**
	 * setter method
	 * @param eventDesc the eventDesc to set
	 */
	
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
	/**
	 * getter method
	 * @return the reportBy
	 */
	
	public String getReportBy() {
		return reportBy;
	}
	/**
	 * setter method
	 * @param reportBy the reportBy to set
	 */
	
	public void setReportBy(String reportBy) {
		this.reportBy = reportBy;
	}
	/**
	 * getter method
	 * @return the reportTime
	 */
	
	public String getReportTime() {
		return reportTime;
	}
	/**
	 * setter method
	 * @param reportTime the reportTime to set
	 */
	
	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}
	/**
	 * getter method
	 * @return the eventReleaseType
	 */
	
	public String getEventReleaseType() {
		return eventReleaseType;
	}
	/**
	 * setter method
	 * @param eventReleaseType the eventReleaseType to set
	 */
	
	public void setEventReleaseType(String eventReleaseType) {
		this.eventReleaseType = eventReleaseType;
	}
	/**
	 * getter method
	 * @return the orgId
	 */
	
	public String getOrgId() {
		return orgId;
	}
	/**
	 * setter method
	 * @param orgId the orgId to set
	 */
	
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	/**
	 * getter method
	 * @return the orgPrivilegeCode
	 */
	
	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}
	/**
	 * setter method
	 * @param orgPrivilegeCode the orgPrivilegeCode to set
	 */
	
	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}
	/**
	 * getter method
	 * @return the remark
	 */
	
	public String getRemark() {
		return remark;
	}
	/**
	 * setter method
	 * @param remark the remark to set
	 */
	
	public void setRemark(String remark) {
		this.remark = remark;
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
	/**
	 * getter method
	 * @return the lon
	 */
	
	public String getLon() {
		return lon;
	}
	/**
	 * setter method
	 * @param lon the lon to set
	 */
	
	public void setLon(String lon) {
		this.lon = lon;
	}
	/**
	 * getter method
	 * @return the lat
	 */
	
	public String getLat() {
		return lat;
	}
	/**
	 * setter method
	 * @param lat the lat to set
	 */
	
	public void setLat(String lat) {
		this.lat = lat;
	}
	
	public ManualEventDto(){
		
	}
	
	public ManualEventDto(TrafficManualEvent trafficManualEvent){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ObjectMapUtils.parseObject(this, trafficManualEvent);
		if(trafficManualEvent.getReportTime() != null){
			this.setReportTime(sdf.format(trafficManualEvent.getReportTime()));
		}
		if(trafficManualEvent.getLonLat() != null){
			String tempStr=trafficManualEvent.getLonLat().trim();
			if(tempStr!=null){
				String[] lonlat=tempStr.substring(tempStr.indexOf("(")+1, tempStr.indexOf(")")).split(" ");
				if(lonlat.length>1){
					this.setLon(lonlat[0]);
					this.setLat(lonlat[1]);
				}
			}
		}
	}
	public TrafficManualEvent parseToTrafficManualEvent() throws ParseException{
		TrafficManualEvent trafficManualEvent=new TrafficManualEvent();
		ObjectMapUtils.parseObject(trafficManualEvent,this);
		if(this.getLon()!=null && this.getLat()!=null){
			trafficManualEvent.setLonLat("POINT("+this.getLon().trim()+" "+this.getLat().trim()+")");
		}
		else{
			trafficManualEvent.setLonLat(null);
		}
		trafficManualEvent.setReportTime(new Date());
		trafficManualEvent.setReportBy(this.getCurrentUserName());
		return trafficManualEvent;
	}
}
