package cy.its.road.domain.model.road;

import java.math.BigDecimal;
import java.util.Date;

import cy.its.com.domain.AggregateRoot;

public class Cross extends AggregateRoot {
	
    private String crossId;
    
    private String roadId;

    private String crossCode;

    private String districtCode;

    private String orgId;

    private String crossName;

    private String crossType;

//    private String crossRoadId;

//    private String mainRoadId;
    
    private String sectionNum;
    
    private BigDecimal crossLongitude;

    private BigDecimal crossLatitude;
  
    private Date updateTime;
    
    private String orgPrivilegeCode;
    
    public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}

	public String getCrossId() {
        return crossId;
    }

    public void setCrossId(String crossId) {
        this.crossId = crossId;
    }

    public String getCrossCode() {
        return crossCode;
    }

    public void setCrossCode(String crossCode) {
        this.crossCode = crossCode;
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

    public String getCrossName() {
        return crossName;
    }

    public void setCrossName(String crossName) {
        this.crossName = crossName;
    }

    public String getCrossType() {
        return crossType;
    }

    public void setCrossType(String crossType) {
        this.crossType = crossType;
    }

//    public String getCrossRoadId() {
//        return crossRoadId;
//    }
//
//    public void setCrossRoadId(String crossRoadId) {
//        this.crossRoadId = crossRoadId;
//    }

//    public String getMainRoadId() {
//		return mainRoadId;
//	}
//
//	public void setMainRoadId(String mainRoadId) {
//		this.mainRoadId = mainRoadId;
//	}

	public String getSectionNum() {
		return sectionNum;
	}

	public void setSectionNum(String sectionNum) {
		this.sectionNum = sectionNum;
	}

	public BigDecimal getCrossLongitude() {
        return crossLongitude;
    }

    public void setCrossLongitude(BigDecimal crossLongitude) {
        this.crossLongitude = crossLongitude;
    }

    public BigDecimal getCrossLatitude() {
        return crossLatitude;
    }

    public void setCrossLatitude(BigDecimal crossLatitude) {
        this.crossLatitude = crossLatitude;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	@Override
	public String getIdentityId() {
		return crossId;
	}

	public String getRoadId() {
		return roadId;
	}

	public void setRoadId(String roadId) {
		this.roadId = roadId;
	}
}