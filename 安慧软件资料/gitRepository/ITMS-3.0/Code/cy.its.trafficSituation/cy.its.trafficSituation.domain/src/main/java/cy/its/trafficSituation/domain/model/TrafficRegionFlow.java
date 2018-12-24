package cy.its.trafficSituation.domain.model;

import java.math.BigDecimal;
import java.util.Date;

public class TrafficRegionFlow {
    private String regionStateId;

    private String regionalId;

    private Integer totalNum;

    private Integer largeNum;

    private Integer smallNum;

    private Integer otherNum;

    private Integer importantNum;

    private BigDecimal avgSpeed;

    private BigDecimal avgTravelTime;
    
    private String trafficState;

    private Date updateTime;
    
    private String regionalName;
    private String orgId;
    private String directionType;
    private String entrySiteId;
    private String exitSiteId;
    private String roadId;
    private String regionalDesignCapacity;
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
	 * @return the regionalDesignCapacity
	 */
	
	public String getRegionalDesignCapacity() {
		return regionalDesignCapacity;
	}

	/**
	 * setter method
	 * @param regionalDesignCapacity the regionalDesignCapacity to set
	 */
	
	public void setRegionalDesignCapacity(String regionalDesignCapacity) {
		this.regionalDesignCapacity = regionalDesignCapacity;
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

    public Integer getImportantNum() {
        return importantNum;
    }

    public void setImportantNum(Integer importantNum) {
        this.importantNum = importantNum;
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

	public BigDecimal getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(BigDecimal avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public BigDecimal getAvgTravelTime() {
        return avgTravelTime;
    }

    public void setAvgTravelTime(BigDecimal avgTravelTime) {
        this.avgTravelTime = avgTravelTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	/**
	 * getter method
	 * @return the regionalName
	 */
	
	public String getRegionalName() {
		return regionalName;
	}

	/**
	 * setter method
	 * @param regionalName the regionalName to set
	 */
	
	public void setRegionalName(String regionalName) {
		this.regionalName = regionalName;
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
	 * @return the entrySiteId
	 */
	
	public String getEntrySiteId() {
		return entrySiteId;
	}

	/**
	 * setter method
	 * @param entrySiteId the entrySiteId to set
	 */
	
	public void setEntrySiteId(String entrySiteId) {
		this.entrySiteId = entrySiteId;
	}

	/**
	 * getter method
	 * @return the exitSiteId
	 */
	
	public String getExitSiteId() {
		return exitSiteId;
	}

	/**
	 * setter method
	 * @param exitSiteId the exitSiteId to set
	 */
	
	public void setExitSiteId(String exitSiteId) {
		this.exitSiteId = exitSiteId;
	}

	/**
	 * getter method
	 * @return the trafficState
	 */
	
	public String getTrafficState() {
		return trafficState;
	}

	/**
	 * setter method
	 * @param trafficState the trafficState to set
	 */
	
	public void setTrafficState(String trafficState) {
		this.trafficState = trafficState;
	}

}