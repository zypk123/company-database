/**
 * @Title: SectionFlowDto.java
 * @Package cy.its.trafficSituation.rest.dto
 * @Description: 断面流量dto
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月4日 下午5:12:33
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
import cy.its.trafficSituation.domain.model.TrafficSectionFlow;

public class SectionFlowDto extends BaseDto {

	private String sectionStateId;

	private String sectionId;

	private String totalNum;

	private String largeNum;

	private String smallNum;

	private String otherNum;

	private String avgSpeed;

	private String vehTailSpace;

	private String updateTime;

	private String roadId;
	private String directionType;
	private String siteId;
	private String siteName;
	private String lonLat;
	
	
	//查询条件中的字段
    private String startTime;
    private String endTime;
    
    private String directionName;
    
    
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
	 * @return the siteId
	 */
	
	public String getSiteId() {
		return siteId;
	}

	/**
	 * setter method
	 * @param siteId the siteId to set
	 */
	
	public void setSiteId(String siteId) {
		this.siteId = siteId;
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
	 * 
	 * @return the roadId
	 */

	public String getRoadId() {
		return roadId;
	}

	/**
	 * setter method
	 * 
	 * @param roadId
	 *            the roadId to set
	 */

	public void setRoadId(String roadId) {
		this.roadId = roadId;
	}

	/**
	 * getter method
	 * 
	 * @return the siteName
	 */

	public String getSiteName() {
		return siteName;
	}

	/**
	 * setter method
	 * 
	 * @param siteName
	 *            the siteName to set
	 */

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSectionStateId() {
		return sectionStateId;
	}

	public void setSectionStateId(String sectionStateId) {
		this.sectionStateId = sectionStateId;
	}

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}

	public String getLargeNum() {
		return largeNum;
	}

	public void setLargeNum(String largeNum) {
		this.largeNum = largeNum;
	}

	public String getSmallNum() {
		return smallNum;
	}

	public void setSmallNum(String smallNum) {
		this.smallNum = smallNum;
	}

	public String getOtherNum() {
		return otherNum;
	}

	public void setOtherNum(String otherNum) {
		this.otherNum = otherNum;
	}

	public String getAvgSpeed() {
		return avgSpeed;
	}

	public void setAvgSpeed(String avgSpeed) {
		this.avgSpeed = avgSpeed;
	}

	public String getVehTailSpace() {
		return vehTailSpace;
	}

	public void setVehTailSpace(String vehTailSpace) {
		this.vehTailSpace = vehTailSpace;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
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

	public SectionFlowDto(){
		
	}
	public SectionFlowDto(TrafficSectionFlow section){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ObjectMapUtils.parseObject(this, section);
		if(section.getUpdateTime()!=null){
			this.setUpdateTime(sdf.format(section.getUpdateTime()));
		}
		if(section.getLargeNum()!=null){
			this.setLargeNum(section.getLargeNum().toString());
		}
		if(section.getAvgSpeed() != null){
			this.setAvgSpeed(section.getAvgSpeed().toString());
		}
		if(section.getOtherNum()!=null){
			this.setOtherNum(section.getOtherNum().toString());
		}
		if(section.getSmallNum()!=null){
			this.setSmallNum(section.getSmallNum().toString());
		}
		if(section.getTotalNum()!=null){
			this.setTotalNum(section.getTotalNum().toString());
		}
		if(section.getVehTailSpace()!=null){
			this.setVehTailSpace(section.getVehTailSpace().toString());
		}
	}
	
	
	
}
