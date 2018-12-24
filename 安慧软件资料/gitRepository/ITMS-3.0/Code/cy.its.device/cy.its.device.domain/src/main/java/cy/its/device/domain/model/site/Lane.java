package cy.its.device.domain.model.site;


public class Lane {
	
    private String laneId;

    private String sectionId;

    private String laneNbr;

    private String laneType;

    private String isRestrict;

    private Short limitLarge;

    private Short limitSmall;

    private Short limitLower;
    
    private Short limitOthers;

	public String getLaneId() {
		return laneId;
	}

	public void setLaneId(String laneId) {
		this.laneId = laneId;
	}

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getLaneNbr() {
		return laneNbr;
	}

	public void setLaneNbr(String laneNbr) {
		this.laneNbr = laneNbr;
	}

	public String getLaneType() {
		return laneType;
	}

	public void setLaneType(String laneType) {
		this.laneType = laneType;
	}

	public String getIsRestrict() {
		return isRestrict;
	}

	public void setIsRestrict(String isRestrict) {
		this.isRestrict = isRestrict;
	}

	public Short getLimitLarge() {
		return limitLarge;
	}

	public void setLimitLarge(Short limitLarge) {
		this.limitLarge = limitLarge;
	}

	public Short getLimitSmall() {
		return limitSmall;
	}

	public void setLimitSmall(Short limitSmall) {
		this.limitSmall = limitSmall;
	}

	public Short getLimitLower() {
		return limitLower;
	}

	public void setLimitLower(Short limitLower) {
		this.limitLower = limitLower;
	}

	public Short getLimitOthers() {
		return limitOthers;
	}

	public void setLimitOthers(Short limitOthers) {
		this.limitOthers = limitOthers;
	}

    
}