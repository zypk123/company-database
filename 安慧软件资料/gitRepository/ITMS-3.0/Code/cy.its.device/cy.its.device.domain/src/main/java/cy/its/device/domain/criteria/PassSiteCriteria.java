package cy.its.device.domain.criteria;

import java.util.List;

/**
 * 卡口点位查找类 
 * @author dell
 *
 */
public class PassSiteCriteria {
	private List<String> roadIds;
	
	private String directionType;
	
	private String siteName;
	
	private String roadType;
	
	private String orgPrivCode;
	
	private String orgId;
	
	private List<String> districtCodes;

	public List<String> getRoadIds() {
		return roadIds;
	}

	public void setRoadIds(List<String> roadIds) {
		this.roadIds = roadIds;
	}

	public String getDirectionType() {
		return directionType;
	}

	public void setDirectionType(String directionType) {
		this.directionType = directionType;
	}

	public String getRoadType() {
		return roadType;
	}

	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}

	public String getOrgPrivCode() {
		return orgPrivCode;
	}

	public void setOrgPrivCode(String orgPrivCode) {
		this.orgPrivCode = orgPrivCode;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public List<String> getDistrictCodes() {
		return districtCodes;
	}

	public void setDistrictCodes(List<String> districtCodes) {
		this.districtCodes = districtCodes;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	
	
}
