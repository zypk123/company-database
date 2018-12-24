package cy.its.road.domain.model.road;

import java.util.Date;

import cy.its.com.domain.AggregateRoot;

public class RoadSection  extends AggregateRoot {
    private String roadSectionId;

    private String roadId;

    private String roadSectionCode;

    private String districtCode;

    private String orgId;

    private String roadSectionName;

    private String roadSectionType;

    private String isOneDirection;
    //道路起点名称
  	private String roadBeginName;
  	//道路终点名称
  	private String roadEndName;
   
    private String roadStructure;

    private String roadLinear;

    private String roadLandscape;

    private String centralIsolation;

    private String roadIsolation;

    private String protectFacilities;

    private String beginMeter;

    private String endMeter;

    private Date updateTime;

    private String orgPrivilegeCode;
    
    public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}

	public String getRoadBeginName() {
		return roadBeginName;
	}

	public void setRoadBeginName(String roadBeginName) {
		this.roadBeginName = roadBeginName;
	}

	public String getRoadEndName() {
		return roadEndName;
	}

	public void setRoadEndName(String roadEndName) {
		this.roadEndName = roadEndName;
	}

	public String getRoadSectionId() {
        return roadSectionId;
    }

    public void setRoadSectionId(String roadSectionId) {
        this.roadSectionId = roadSectionId;
    }

    public String getRoadId() {
        return roadId;
    }

    public void setRoadId(String roadId) {
        this.roadId = roadId;
    }

    public String getRoadSectionCode() {
        return roadSectionCode;
    }

    public void setRoadSectionCode(String roadSectionCode) {
        this.roadSectionCode = roadSectionCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getRoadSectionName() {
        return roadSectionName;
    }

    public void setRoadSectionName(String roadSectionName) {
        this.roadSectionName = roadSectionName;
    }

    public String getRoadSectionType() {
        return roadSectionType;
    }

    public void setRoadSectionType(String roadSectionType) {
        this.roadSectionType = roadSectionType;
    }

    public String getIsOneDirection() {
        return isOneDirection;
    }

    public void setIsOneDirection(String isOneDirection) {
        this.isOneDirection = isOneDirection;
    }

    public String getRoadStructure() {
        return roadStructure;
    }

    public void setRoadStructure(String roadStructure) {
        this.roadStructure = roadStructure;
    }

    public String getRoadLinear() {
        return roadLinear;
    }

    public void setRoadLinear(String roadLinear) {
        this.roadLinear = roadLinear;
    }

    public String getRoadLandscape() {
        return roadLandscape;
    }

    public void setRoadLandscape(String roadLandscape) {
        this.roadLandscape = roadLandscape;
    }

    public String getCentralIsolation() {
        return centralIsolation;
    }

    public void setCentralIsolation(String centralIsolation) {
        this.centralIsolation = centralIsolation;
    }

    public String getRoadIsolation() {
        return roadIsolation;
    }

    public void setRoadIsolation(String roadIsolation) {
        this.roadIsolation = roadIsolation;
    }

    public String getProtectFacilities() {
        return protectFacilities;
    }

    public void setProtectFacilities(String protectFacilities) {
        this.protectFacilities = protectFacilities;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    
	public String getBeginMeter() {
		return beginMeter;
	}

	public void setBeginMeter(String beginMeter) {
		this.beginMeter = beginMeter;
	}

	public String getEndMeter() {
		return endMeter;
	}

	public void setEndMeter(String endMeter) {
		this.endMeter = endMeter;
	}

	@Override
	public String getIdentityId() {
		// TODO Auto-generated method stub
		return null;
	}
}