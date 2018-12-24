package cy.its.trafficSituation.domain.model;

public class MapRoad {
	private String name;

	private String kind;

	private String popname;

	private Short direction;

	private String district;

	private Short type;

	private Short length;

	private Short lanes;

	private Short formofway;

	private Short updatetime;

	private String cyid;

	private String policearea;

	private String wkt;
	
	private String roadCode;
	private String regionalId;
	private String fRoadName;
	private String bRoadName;
	
	private String regionalName;
	private String regionalDesignCapacity;
	private String directionType;
	private String orgId;
	private String directionName;

	

	/**
	 * getter method
	 * @return the fRoadName
	 */
	
	public String getfRoadName() {
		return fRoadName;
	}

	/**
	 * setter method
	 * @param fRoadName the fRoadName to set
	 */
	
	public void setfRoadName(String fRoadName) {
		this.fRoadName = fRoadName;
	}

	/**
	 * getter method
	 * @return the bRoadName
	 */
	
	public String getbRoadName() {
		return bRoadName;
	}

	/**
	 * setter method
	 * @param bRoadName the bRoadName to set
	 */
	
	public void setbRoadName(String bRoadName) {
		this.bRoadName = bRoadName;
	}

	public String getWkt() {
		return wkt;
	}

	public void setWkt(String wkt) {
		this.wkt = wkt;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getPopname() {
		return popname;
	}

	public void setPopname(String popname) {
		this.popname = popname;
	}

	public Short getDirection() {
		return direction;
	}

	public void setDirection(Short direction) {
		this.direction = direction;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Short getLength() {
		return length;
	}

	public void setLength(Short length) {
		this.length = length;
	}

	public Short getLanes() {
		return lanes;
	}

	public void setLanes(Short lanes) {
		this.lanes = lanes;
	}

	public Short getFormofway() {
		return formofway;
	}

	public void setFormofway(Short formofway) {
		this.formofway = formofway;
	}

	public Short getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Short updatetime) {
		this.updatetime = updatetime;
	}

	public String getCyid() {
		return cyid;
	}

	public void setCyid(String cyid) {
		this.cyid = cyid;
	}

	public String getPolicearea() {
		return policearea;
	}

	public void setPolicearea(String policearea) {
		this.policearea = policearea;
	}

	/**
	 * getter method
	 * @return the roadCode
	 */
	
	public String getRoadCode() {
		return roadCode;
	}

	/**
	 * setter method
	 * @param roadCode the roadCode to set
	 */
	
	public void setRoadCode(String roadCode) {
		this.roadCode = roadCode;
	}

	/**
	 * getter method
	 * @return the regionalId
	 */
	
	public String getRegionalId() {
		return regionalId;
	}

	/**
	 * setter method
	 * @param regionalId the regionalId to set
	 */
	
	public void setRegionalId(String regionalId) {
		this.regionalId = regionalId;
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