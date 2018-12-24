package cy.its.trafficSituation.rest.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cy.its.com.dto.BaseDto;
import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.trafficSituation.domain.model.TrafficControl;

public class TrafficControlDto extends BaseDto {

	private String trafficControlId;

	private String trafficControlName;

	private String startSite;

	private String endSite;

	private String controlReason;

	private String startTime;

	private String endTime;

	private String controlMeasures;

	private String influenceRange;

	private String orgId;

	private String phone;

	private String createBy;

	private String createTime;

	private String roadId;

	private String directionType;

	private String directionName;

	private String controlLane;

	private String lonLat;

	private String bypassLine;

	private String siteIds;

	private String lon;
	private String lat;
	
	private String orgPrivilegeCode;
	
	private String realEndTime;

	/**
	 * getter method
	 * 
	 * @return the lon
	 */

	public String getLon() {
		return lon;
	}

	/**
	 * setter method
	 * 
	 * @param lon
	 *            the lon to set
	 */

	public void setLon(String lon) {
		this.lon = lon;
	}

	/**
	 * getter method
	 * 
	 * @return the lat
	 */

	public String getLat() {
		return lat;
	}

	/**
	 * setter method
	 * 
	 * @param lat
	 *            the lat to set
	 */

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getTrafficControlId() {
		return trafficControlId;
	}

	public void setTrafficControlId(String trafficControlId) {
		this.trafficControlId = trafficControlId;
	}

	public String getTrafficControlName() {
		return trafficControlName;
	}

	public void setTrafficControlName(String trafficControlName) {
		this.trafficControlName = trafficControlName;
	}

	public String getStartSite() {
		return startSite;
	}

	public void setStartSite(String startSite) {
		this.startSite = startSite;
	}

	public String getEndSite() {
		return endSite;
	}

	public void setEndSite(String endSite) {
		this.endSite = endSite;
	}

	public String getControlReason() {
		return controlReason;
	}

	public void setControlReason(String controlReason) {
		this.controlReason = controlReason;
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

	public String getControlMeasures() {
		return controlMeasures;
	}

	public void setControlMeasures(String controlMeasures) {
		this.controlMeasures = controlMeasures;
	}

	public String getInfluenceRange() {
		return influenceRange;
	}

	public void setInfluenceRange(String influenceRange) {
		this.influenceRange = influenceRange;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getRoadId() {
		return roadId;
	}

	public void setRoadId(String roadId) {
		this.roadId = roadId;
	}

	public String getDirectionType() {
		return directionType;
	}

	public void setDirectionType(String directionType) {
		this.directionType = directionType;
	}

	public String getDirectionName() {
		return directionName;
	}

	public void setDirectionName(String directionName) {
		this.directionName = directionName;
	}

	public String getControlLane() {
		return controlLane;
	}

	public void setControlLane(String controlLane) {
		this.controlLane = controlLane;
	}

	public String getLonLat() {
		return lonLat;
	}

	public void setLonLat(String lonLat) {
		this.lonLat = lonLat;
	}

	public String getBypassLine() {
		return bypassLine;
	}

	public void setBypassLine(String bypassLine) {
		this.bypassLine = bypassLine;
	}

	public String getSiteIds() {
		return siteIds;
	}

	public void setSiteIds(String siteIds) {
		this.siteIds = siteIds;
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
	 * @return the realEndTime
	 */
	
	public String getRealEndTime() {
		return realEndTime;
	}

	/**
	 * setter method
	 * @param realEndTime the realEndTime to set
	 */
	
	public void setRealEndTime(String realEndTime) {
		this.realEndTime = realEndTime;
	}

	public TrafficControlDto() {
	}

	public TrafficControlDto(TrafficControl trafficControl) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ObjectMapUtils.parseObject(this, trafficControl);
		if(trafficControl.getCreateTime()!=null){
			this.setCreateTime(sdf.format(trafficControl.getCreateTime()));
		}
		if(trafficControl.getStartTime()!=null){
			this.setStartTime(sdf.format(trafficControl.getStartTime()));
		}
		if(trafficControl.getEndTime()!=null){
			this.setEndTime(sdf.format(trafficControl.getEndTime()));
		}
		if(trafficControl.getRealEndTime()!=null){
			this.setRealEndTime(sdf.format(trafficControl.getRealEndTime()));
		}

		if (trafficControl.getLonLat()!= null) {
			String tempStr = trafficControl.getLonLat().trim();
			String[] lonlat = tempStr.substring(tempStr.indexOf("(") + 1, tempStr.indexOf(")")).split(" ");
			if (lonlat.length > 1) {
				this.setLon(lonlat[0]);
				this.setLat(lonlat[1]);
			}
		}

	}

	public TrafficControl parseToTrafficControl() throws ParseException {
		TrafficControl trafficControl = new TrafficControl();
		ObjectMapUtils.parseObject(trafficControl, this);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (this.getStartTime() != null) {
			trafficControl.setStartTime(sdf.parse(this.getStartTime()));
		}
		if (this.getEndTime() != null) {
			trafficControl.setEndTime(sdf.parse(this.getEndTime()));
		}
		if(!StringUtil.isNullOrEmpty(this.getRealEndTime())){
			trafficControl.setRealEndTime(sdf.parse(this.getRealEndTime()));
		}
		else {
			trafficControl.setRealEndTime(null);
		}
		if (this.getLon()!= null && this.getLat()!= null) {
			trafficControl.setLonLat("POINT(" + this.getLon().trim() + " " + this.getLat().trim() + ")");
		}
		else{
			trafficControl.setLonLat(null);
		}
		trafficControl.setCreateTime(new Date());
		trafficControl.setCreateBy(this.getCurrentUserName());
		return trafficControl;
	}
}
