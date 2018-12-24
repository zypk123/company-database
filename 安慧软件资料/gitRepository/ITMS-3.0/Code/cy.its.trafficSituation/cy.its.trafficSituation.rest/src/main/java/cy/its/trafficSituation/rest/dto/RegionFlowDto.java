/**
 * @Title: RegionFlowDto.java
 * @Package cy.its.trafficSituation.rest.dto
 * @Description:区间流量Dto
 * @author gyf guanyf@cychina.cn
 * @date 2015年10月29日 下午3:02:40
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.rest.dto;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import cy.its.com.dto.BaseDto;
import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.trafficSituation.domain.model.TrafficRegionFlow;

public class RegionFlowDto extends BaseDto {

	private String regionStateId;

	private String regionalId;

	private String totalNum;

	private String largeNum;

	private String smallNum;

	private String otherNum;

	private String importantNum;

	private String avgSpeed;

	private String avgTravelTime;
	private String trafficState;

	private String updateTime;

	// 区间基础信息
	private String regionalName;
	private String orgId;
	private String directionType;
	private String directionName;
	private String entrySiteId;
	private String exitSiteId;
	private String roadId;
	private String regionalDesignCapacity;

	private String lonlatStart;
	private String lonlatEnd;

	// 查询条件中的字段
	private String startTime;
	private String endTime;

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
	 * @return the lonlatStart
	 */

	public String getLonlatStart() {
		return lonlatStart;
	}

	/**
	 * setter method
	 * 
	 * @param lonlatStart
	 *            the lonlatStart to set
	 */

	public void setLonlatStart(String lonlatStart) {
		this.lonlatStart = lonlatStart;
	}

	/**
	 * getter method
	 * 
	 * @return the lonlatEnd
	 */

	public String getLonlatEnd() {
		return lonlatEnd;
	}

	/**
	 * setter method
	 * 
	 * @param lonlatEnd
	 *            the lonlatEnd to set
	 */

	public void setLonlatEnd(String lonlatEnd) {
		this.lonlatEnd = lonlatEnd;
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
	 * @return the startTime
	 */

	public String getStartTime() {
		return startTime;
	}

	/**
	 * setter method
	 * 
	 * @param startTime
	 *            the startTime to set
	 */

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * getter method
	 * 
	 * @return the endTime
	 */

	public String getEndTime() {
		return endTime;
	}

	/**
	 * setter method
	 * 
	 * @param endTime
	 *            the endTime to set
	 */

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getRegionStateId() {
		return regionStateId;
	}

	public void setRegionStateId(String regionStateId) {
		this.regionStateId = regionStateId;
	}

	public String getRegionalId() {
		return regionalId;
	}

	public void setRegionalId(String regionalId) {
		this.regionalId = regionalId;
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

	public String getImportantNum() {
		return importantNum;
	}

	public void setImportantNum(String importantNum) {
		this.importantNum = importantNum;
	}

	public String getAvgSpeed() {
		return avgSpeed;
	}

	public void setAvgSpeed(String avgSpeed) {
		this.avgSpeed = avgSpeed;
	}

	public String getAvgTravelTime() {
		return avgTravelTime;
	}

	public void setAvgTravelTime(String avgTravelTime) {
		this.avgTravelTime = avgTravelTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
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
	 * @return the orgId
	 */

	public String getOrgId() {
		return orgId;
	}

	/**
	 * setter method
	 * 
	 * @param orgId
	 *            the orgId to set
	 */

	public void setOrgId(String orgId) {
		this.orgId = orgId;
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

	/**
	 * getter method
	 * 
	 * @return the directionName
	 */

	public String getDirectionName() {
		return directionName;
	}

	/**
	 * setter method
	 * 
	 * @param directionName
	 *            the directionName to set
	 */

	public void setDirectionName(String directionName) {
		this.directionName = directionName;
	}

	/**
	 * getter method
	 * 
	 * @return the entrySiteId
	 */

	public String getEntrySiteId() {
		return entrySiteId;
	}

	/**
	 * setter method
	 * 
	 * @param entrySiteId
	 *            the entrySiteId to set
	 */

	public void setEntrySiteId(String entrySiteId) {
		this.entrySiteId = entrySiteId;
	}

	/**
	 * getter method
	 * 
	 * @return the exitSiteId
	 */

	public String getExitSiteId() {
		return exitSiteId;
	}

	/**
	 * setter method
	 * 
	 * @param exitSiteId
	 *            the exitSiteId to set
	 */

	public void setExitSiteId(String exitSiteId) {
		this.exitSiteId = exitSiteId;
	}

	public RegionFlowDto() {

	}

	public RegionFlowDto(TrafficRegionFlow regionFlow) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ObjectMapUtils.parseObject(this, regionFlow);
		if (regionFlow.getUpdateTime() != null) {
			this.setUpdateTime(sdf.format(regionFlow.getUpdateTime()));
		}
		if (regionFlow.getAvgSpeed() != null) {
			this.setAvgSpeed(regionFlow.getAvgSpeed().toString());
		}
		if (regionFlow.getAvgTravelTime() != null) {
			this.setAvgTravelTime(regionFlow.getAvgTravelTime().toString());
		}
		if (regionFlow.getImportantNum() != null) {
			this.setImportantNum(regionFlow.getImportantNum().toString());
		}
		if (regionFlow.getLargeNum() != null) {
			this.setLargeNum(regionFlow.getLargeNum().toString());
		}
		if (regionFlow.getOtherNum() != null) {
			this.setOtherNum(regionFlow.getOtherNum().toString());
		}
		if (regionFlow.getSmallNum() != null) {
			this.setSmallNum(regionFlow.getSmallNum().toString());
		}
		if (regionFlow.getTotalNum() != null) {
			this.setTotalNum(regionFlow.getTotalNum().toString());
		}
	}

	public TrafficRegionFlow parseToTrafficRegionFlow() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TrafficRegionFlow regionFlow = new TrafficRegionFlow();
		ObjectMapUtils.parseObject(regionFlow, this);
		if (StringUtil.isNullOrEmpty(this.getUpdateTime())) {
			regionFlow.setUpdateTime(sdf.parse(this.getUpdateTime()));
		}
		if (StringUtil.isNullOrEmpty(this.getAvgSpeed())) {
			regionFlow.setAvgSpeed(new BigDecimal(this.getAvgSpeed()));
		}
		return regionFlow;
	}

	/**
	 * getter method
	 * 
	 * @return the trafficState
	 */

	public String getTrafficState() {
		return trafficState;
	}

	/**
	 * setter method
	 * 
	 * @param trafficState
	 *            the trafficState to set
	 */

	public void setTrafficState(String trafficState) {
		this.trafficState = trafficState;
	}

}
