package cy.its.road.domain.model.road;

import java.util.Date;

import cy.its.com.domain.AggregateRoot;

public class Road  extends AggregateRoot{
	
    private String roadId;

    private String roadCode;

    private String roadName;
    
    private String roadAliasName;

    private String roadType;

    private String vioConfirmModel;

    private String districtCodeList;

    private String districtMileage;

    private String orgId;

    private String roadAdminGrade;

    private String isOneDirection;

    private String upDirection;

    private String downDirection;

    private String roadStructure;

    private String roadLandscape;

    private String roadLinear;

    private String roadIsolation;

    private String centralIsolation;

    private String protectFacilities;

    private String roadBeginMileage;

    private String roadEndMileage;

    private String roadRecordStatus;

    private String roadBeginName;

    private String roadEndName;
    
    private Double roadLength;
    
    private Date updateTime;
    //道路类型
    private String directionType;
    
    private String orgPrivilegeCode;
     
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

	public String getDirectionType() {
		return directionType;
	}

	public void setDirectionType(String directionType) {
		this.directionType = directionType;
	}

	public String getRoadId() {
        return roadId;
    }

    public void setRoadId(String roadId) {
        this.roadId = roadId;
    }

    public String getRoadCode() {
        return roadCode;
    }

    public void setRoadCode(String roadCode) {
        this.roadCode = roadCode;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public String getRoadType() {
        return roadType;
    }

    public void setRoadType(String roadType) {
        this.roadType = roadType;
    }

    public String getVioConfirmModel() {
        return vioConfirmModel;
    }

    public void setVioConfirmModel(String vioConfirmModel) {
        this.vioConfirmModel = vioConfirmModel;
    }

    public String getDistrictCodeList() {
        return districtCodeList;
    }

    public void setDistrictCodeList(String districtCodeList) {
        this.districtCodeList = districtCodeList;
    }

    public String getDistrictMileage() {
        return districtMileage;
    }

    public void setDistrictMileage(String districtMileage) {
        this.districtMileage = districtMileage;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getRoadAdminGrade() {
        return roadAdminGrade;
    }

    public void setRoadAdminGrade(String roadAdminGrade) {
        this.roadAdminGrade = roadAdminGrade;
    }

    public String getIsOneDirection() {
        return isOneDirection;
    }

    public void setIsOneDirection(String isOneDirection) {
        this.isOneDirection = isOneDirection;
    }

    public String getUpDirection() {
        return upDirection;
    }

    public void setUpDirection(String upDirection) {
        this.upDirection = upDirection;
    }

    public String getDownDirection() {
        return downDirection;
    }

    public void setDownDirection(String downDirection) {
        this.downDirection = downDirection;
    }

    public String getRoadStructure() {
        return roadStructure;
    }

    public void setRoadStructure(String roadStructure) {
        this.roadStructure = roadStructure;
    }

    public String getRoadLandscape() {
        return roadLandscape;
    }

    public void setRoadLandscape(String roadLandscape) {
        this.roadLandscape = roadLandscape;
    }

    public String getRoadLinear() {
        return roadLinear;
    }

    public void setRoadLinear(String roadLinear) {
        this.roadLinear = roadLinear;
    }

    public String getRoadIsolation() {
        return roadIsolation;
    }

    public void setRoadIsolation(String roadIsolation) {
        this.roadIsolation = roadIsolation;
    }

    public String getCentralIsolation() {
        return centralIsolation;
    }

    public void setCentralIsolation(String centralIsolation) {
        this.centralIsolation = centralIsolation;
    }

    public String getProtectFacilities() {
        return protectFacilities;
    }

    public void setProtectFacilities(String protectFacilities) {
        this.protectFacilities = protectFacilities;
    }

    public String getRoadBeginMileage() {
        return roadBeginMileage;
    }

    public void setRoadBeginMileage(String roadBeginMileage) {
        this.roadBeginMileage = roadBeginMileage;
    }

    public String getRoadEndMileage() {
        return roadEndMileage;
    }

    public void setRoadEndMileage(String roadEndMileage) {
        this.roadEndMileage = roadEndMileage;
    }

    public String getRoadRecordStatus() {
        return roadRecordStatus;
    }

    public void setRoadRecordStatus(String roadRecordStatus) {
        this.roadRecordStatus = roadRecordStatus;
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
 
	public Double getRoadLength() {
		return roadLength;
	}

	public void setRoadLength(Double roadLength) {
		this.roadLength = roadLength;
	}

	public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	@Override
	public String getIdentityId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRoadAliasName() {
		return roadAliasName;
	}

	public void setRoadAliasName(String roadAliasName) {
		this.roadAliasName = roadAliasName;
	}
}