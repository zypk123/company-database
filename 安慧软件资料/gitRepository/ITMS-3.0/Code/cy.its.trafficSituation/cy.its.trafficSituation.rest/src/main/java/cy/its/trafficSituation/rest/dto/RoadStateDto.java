/**
 * @Title: RoadStateDto.java
 * @Package cy.its.trafficSituation.rest.dto
 * @Description: 路况Dto
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月25日 上午9:31:01
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.rest.dto;

import java.text.SimpleDateFormat;

import org.springframework.expression.spel.ast.ValueRef.NullValueRef;

import cy.its.com.dto.BaseDto;
import cy.its.com.util.ObjectMapUtils;
import cy.its.trafficSituation.domain.model.MapRoad;
import cy.its.trafficSituation.domain.model.TrafficRegionFlow;

public class RoadStateDto extends BaseDto {
	// 区间流量表中的字段
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
	// 道路表中的数据
	private String name;
	private String popname;
	private String direction;
	private String district;
	private String type;
	private String length;
	private String lanes;
	private String formofway;
	private String updatetime;
	private String cyid;
	private String policearea;
	private String wkt;
	private String roadCode;
	private String fRoadName;
	private String bRoadName;

	private String startTime;
	private String endTime;

	private String roadId;

	/**
	 * getter method
	 * 
	 * @return the regionStateId
	 */

	public String getRegionStateId() {
		return regionStateId;
	}

	/**
	 * setter method
	 * 
	 * @param regionStateId
	 *            the regionStateId to set
	 */

	public void setRegionStateId(String regionStateId) {
		this.regionStateId = regionStateId;
	}

	/**
	 * getter method
	 * 
	 * @return the regionalId
	 */

	public String getRegionalId() {
		return regionalId;
	}

	/**
	 * setter method
	 * 
	 * @param regionalId
	 *            the regionalId to set
	 */

	public void setRegionalId(String regionalId) {
		this.regionalId = regionalId;
	}

	/**
	 * getter method
	 * 
	 * @return the totalNum
	 */

	public String getTotalNum() {
		return totalNum;
	}

	/**
	 * setter method
	 * 
	 * @param totalNum
	 *            the totalNum to set
	 */

	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}

	/**
	 * getter method
	 * 
	 * @return the largeNum
	 */

	public String getLargeNum() {
		return largeNum;
	}

	/**
	 * setter method
	 * 
	 * @param largeNum
	 *            the largeNum to set
	 */

	public void setLargeNum(String largeNum) {
		this.largeNum = largeNum;
	}

	/**
	 * getter method
	 * 
	 * @return the smallNum
	 */

	public String getSmallNum() {
		return smallNum;
	}

	/**
	 * setter method
	 * 
	 * @param smallNum
	 *            the smallNum to set
	 */

	public void setSmallNum(String smallNum) {
		this.smallNum = smallNum;
	}

	/**
	 * getter method
	 * 
	 * @return the otherNum
	 */

	public String getOtherNum() {
		return otherNum;
	}

	/**
	 * setter method
	 * 
	 * @param otherNum
	 *            the otherNum to set
	 */

	public void setOtherNum(String otherNum) {
		this.otherNum = otherNum;
	}

	/**
	 * getter method
	 * 
	 * @return the importantNum
	 */

	public String getImportantNum() {
		return importantNum;
	}

	/**
	 * setter method
	 * 
	 * @param importantNum
	 *            the importantNum to set
	 */

	public void setImportantNum(String importantNum) {
		this.importantNum = importantNum;
	}

	/**
	 * getter method
	 * 
	 * @return the avrSpeed
	 */

	public String getAvgSpeed() {
		return avgSpeed;
	}

	/**
	 * setter method
	 * 
	 * @param avrSpeed
	 *            the avrSpeed to set
	 */

	public void setAvgSpeed(String avgSpeed) {
		this.avgSpeed = avgSpeed;
	}

	/**
	 * getter method
	 * 
	 * @return the avrTravelTime
	 */

	public String getAvgTravelTime() {
		return avgTravelTime;
	}

	/**
	 * setter method
	 * 
	 * @param avrTravelTime
	 *            the avrTravelTime to set
	 */

	public void setAvgTravelTime(String avgTravelTime) {
		this.avgTravelTime = avgTravelTime;
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

	/**
	 * getter method
	 * 
	 * @return the updateTime
	 */

	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * setter method
	 * 
	 * @param updateTime
	 *            the updateTime to set
	 */

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
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
	 * @return the popname
	 */

	public String getPopname() {
		return popname;
	}

	/**
	 * setter method
	 * 
	 * @param popname
	 *            the popname to set
	 */

	public void setPopname(String popname) {
		this.popname = popname;
	}

	/**
	 * getter method
	 * 
	 * @return the direction
	 */

	public String getDirection() {
		return direction;
	}

	/**
	 * setter method
	 * 
	 * @param direction
	 *            the direction to set
	 */

	public void setDirection(String direction) {
		this.direction = direction;
	}

	/**
	 * getter method
	 * 
	 * @return the district
	 */

	public String getDistrict() {
		return district;
	}

	/**
	 * setter method
	 * 
	 * @param district
	 *            the district to set
	 */

	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * getter method
	 * 
	 * @return the type
	 */

	public String getType() {
		return type;
	}

	/**
	 * setter method
	 * 
	 * @param type
	 *            the type to set
	 */

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * getter method
	 * 
	 * @return the length
	 */

	public String getLength() {
		return length;
	}

	/**
	 * setter method
	 * 
	 * @param length
	 *            the length to set
	 */

	public void setLength(String length) {
		this.length = length;
	}

	/**
	 * getter method
	 * 
	 * @return the lanes
	 */

	public String getLanes() {
		return lanes;
	}

	/**
	 * setter method
	 * 
	 * @param lanes
	 *            the lanes to set
	 */

	public void setLanes(String lanes) {
		this.lanes = lanes;
	}

	/**
	 * getter method
	 * 
	 * @return the formofway
	 */

	public String getFormofway() {
		return formofway;
	}

	/**
	 * setter method
	 * 
	 * @param formofway
	 *            the formofway to set
	 */

	public void setFormofway(String formofway) {
		this.formofway = formofway;
	}

	/**
	 * getter method
	 * 
	 * @return the updatetime
	 */

	public String getUpdatetime() {
		return updatetime;
	}

	/**
	 * setter method
	 * 
	 * @param updatetime
	 *            the updatetime to set
	 */

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	/**
	 * getter method
	 * 
	 * @return the cyid
	 */

	public String getCyid() {
		return cyid;
	}

	/**
	 * setter method
	 * 
	 * @param cyid
	 *            the cyid to set
	 */

	public void setCyid(String cyid) {
		this.cyid = cyid;
	}

	/**
	 * getter method
	 * 
	 * @return the policearea
	 */

	public String getPolicearea() {
		return policearea;
	}

	/**
	 * setter method
	 * 
	 * @param policearea
	 *            the policearea to set
	 */

	public void setPolicearea(String policearea) {
		this.policearea = policearea;
	}

	/**
	 * getter method
	 * 
	 * @return the wkt
	 */

	public String getWkt() {
		return wkt;
	}

	/**
	 * setter method
	 * 
	 * @param wkt
	 *            the wkt to set
	 */

	public void setWkt(String wkt) {
		this.wkt = wkt;
	}

	/**
	 * getter method
	 * 
	 * @return the roadCode
	 */

	public String getRoadCode() {
		return roadCode;
	}

	/**
	 * setter method
	 * 
	 * @param roadCode
	 *            the roadCode to set
	 */

	public void setRoadCode(String roadCode) {
		this.roadCode = roadCode;
	}

	/**
	 * getter method
	 * 
	 * @return the fRoadName
	 */

	public String getfRoadName() {
		return fRoadName;
	}

	/**
	 * setter method
	 * 
	 * @param fRoadName
	 *            the fRoadName to set
	 */

	public void setfRoadName(String fRoadName) {
		this.fRoadName = fRoadName;
	}

	/**
	 * getter method
	 * 
	 * @return the bRoadName
	 */

	public String getbRoadName() {
		return bRoadName;
	}

	/**
	 * setter method
	 * 
	 * @param bRoadName
	 *            the bRoadName to set
	 */

	public void setbRoadName(String bRoadName) {
		this.bRoadName = bRoadName;
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

	public RoadStateDto() {

	}

	public RoadStateDto(TrafficRegionFlow regionFlow, MapRoad mapRoad) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (mapRoad != null) {
			ObjectMapUtils.parseObject(this, mapRoad);
			// 道路表
			if (mapRoad.getDirection() != null) {
				this.setDirection(mapRoad.getDirection().toString());
			}
			if (mapRoad.getFormofway() != null) {
				this.setFormofway(mapRoad.getFormofway().toString());
			}
			if (mapRoad.getLanes() != null) {
				this.setLanes(mapRoad.getLanes().toString());
			}
			if (mapRoad.getLength() != null) {
				this.setLength(mapRoad.getLength().toString());
			}
			if (mapRoad.getType() != null) {
				this.setType(mapRoad.getType().toString());
			}
		}
		ObjectMapUtils.parseObject(this, regionFlow);
		// 区间流量表
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
		if (regionFlow.getUpdateTime() != null) {
			this.setUpdateTime(sdf.format(regionFlow.getUpdateTime()));
		}

	}

}
