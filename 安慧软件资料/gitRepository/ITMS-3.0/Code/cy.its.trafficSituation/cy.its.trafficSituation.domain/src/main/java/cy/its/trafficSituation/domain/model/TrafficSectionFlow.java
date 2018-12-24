package cy.its.trafficSituation.domain.model;

import java.math.BigDecimal;
import java.util.Date;

public class TrafficSectionFlow {
	
    private String sectionStateId;

    private String sectionId;

    private Integer totalNum;

    private Integer largeNum;

    private Integer smallNum;

    private Integer otherNum;

    private BigDecimal avgSpeed;

    private BigDecimal vehTailSpace;

    private Date updateTime;
    
    private String roadId;
    private String directionType;
    private String siteId;
    private String siteName;
    private String orgPrivilegeCode;
    
    private String directionName;

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

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getLargeNum() {
        return largeNum;
    }

    public void setLargeNum(Integer largeNum) {
        this.largeNum = largeNum;
    }

    public Integer getSmallNum() {
        return smallNum;
    }

    public void setSmallNum(Integer smallNum) {
        this.smallNum = smallNum;
    }

    public Integer getOtherNum() {
        return otherNum;
    }

    public void setOtherNum(Integer otherNum) {
        this.otherNum = otherNum;
    }

    public BigDecimal getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(BigDecimal avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public BigDecimal getVehTailSpace() {
        return vehTailSpace;
    }

    public void setVehTailSpace(BigDecimal vehTailSpace) {
        this.vehTailSpace = vehTailSpace;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
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
}