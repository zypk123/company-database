package cy.its.trafficSituation.domain.model;

import java.util.Date;

public class TrafficControl {

	private String trafficControlId;

    private String trafficControlName;

    private String startSite;

    private String endSite;

    private String controlReason;

    private Date startTime;

    private Date endTime;

    private String controlMeasures;

    private String influenceRange;

    private String orgId;

    private String phone;

    private String createBy;

    private Date createTime;

    private String roadId;

    private String directionType;

    private String directionName;

    private String controlLane;

    private String lonLat;

    private String bypassLine;

    private String siteIds;
    
    private String orgPrivilegeCode;
    
    private Date realEndTime;
    

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
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

	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}
	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}

	/**
	 * getter method
	 * @return the realEndTime
	 */
	
	public Date getRealEndTime() {
		return realEndTime;
	}

	/**
	 * setter method
	 * @param realEndTime the realEndTime to set
	 */
	
	public void setRealEndTime(Date realEndTime) {
		this.realEndTime = realEndTime;
	}
}