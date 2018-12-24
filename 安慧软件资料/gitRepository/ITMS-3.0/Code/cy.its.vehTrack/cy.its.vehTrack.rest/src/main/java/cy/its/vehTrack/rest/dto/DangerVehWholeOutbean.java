package cy.its.vehTrack.rest.dto;

/**
 * @Title: DangerVehWholeOutbean.java
 * @Package com.cychina.bigdata.its.entity
 * @author Yunqi wang
 * @date 2016 0401
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 合肥安慧软件公司
 * Copyright: Copyright (c) 2016 
 */

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class DangerVehWholeOutbean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6552680105539579361L;
	
	//roadCode":30999,"roadSectionCode":55,"hour":12,"minuteStep":1,"total":86
	
	private String roadCode;
	private String roadSectionCode;
	private String hour;
	private String minuteStep;
	private Long total;
	private List<String> xAxis;
	private List<Long> yAxis;
	/*违法Code*/
	private String violationCode;
	private Map<String,List<Long>> map;
	private Long trafficCount;
	private Long variance;
	
	/*
	 *违法类型
	 */
	private String vioType;
	
	/*路段名称*/
	private String roadSectionName;
	
	public Long getTrafficCount() {
		return trafficCount;
	}
	public void setTrafficCount(Long trafficCount) {
		this.trafficCount = trafficCount;
	}
	public Map<String, List<Long>> getMap() {
		return map;
	}
	public void setMap(Map<String, List<Long>> map) {
		this.map = map;
	}
	public String getRoadCode() {
		return roadCode;
	}
	public void setRoadCode(String roadCode) {
		this.roadCode = roadCode;
	}
	public String getRoadSectionCode() {
		return roadSectionCode;
	}
	public void setRoadSectionCode(String roadSectionCode) {
		this.roadSectionCode = roadSectionCode;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<String> getxAxis() {
		return xAxis;
	}
	public void setxAxis(List<String> xAxis) {
		this.xAxis = xAxis;
	}
	public List<Long> getyAxis() {
		return yAxis;
	}
	public void setyAxis(List<Long> yAxis) {
		this.yAxis = yAxis;
	}
	public Long getVariance() {
		return variance;
	}
	public void setVariance(Long variance) {
		this.variance = variance;
	}
	public String getMinuteStep() {
		return minuteStep;
	}
	public void setMinuteStep(String minuteStep) {
		this.minuteStep = minuteStep;
	}
	public String getViolationCode() {
		return violationCode;
	}
	public void setViolationCode(String violationCode) {
		this.violationCode = violationCode;
	}
	public String getRoadSectionName() {
		return roadSectionName;
	}
	public void setRoadSectionName(String roadSectionName) {
		this.roadSectionName = roadSectionName;
	}
	public String getVioType() {
		return vioType;
	}
	public void setVioType(String vioType) {
		this.vioType = vioType;
	}
}
