package cy.its.syscfg.domain.model.traffic;


public class PolicePost {

	private String policePostId;
	private String postName;
	private String manageOrg;
	private String address;	
	private String dutyModel;
	private String headerName;
	private String headerPhone;
	private String lonLat;

	public String getLonLat() {
		return lonLat;
	}

	public void setLonLat(String lonLat) {
		this.lonLat = lonLat;
	}

	public String getPolicePostId() {
		return policePostId;
	}

	public void setPolicePostId(String policePostId) {
		this.policePostId = policePostId;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getManageOrg() {
		return manageOrg;
	}

	public void setManageOrg(String manageOrg) {
		this.manageOrg = manageOrg;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getDutyModel() {
		return dutyModel;
	}

	public void setDutyModel(String dutyModel) {
		this.dutyModel = dutyModel;
	}

	public String getHeaderName() {
		return headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	public String getHeaderPhone() {
		return headerPhone;
	}

	public void setHeaderPhone(String headerPhone) {
		this.headerPhone = headerPhone;
	}

	
}
