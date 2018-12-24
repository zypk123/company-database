
package cy.its.sysCfg.rest.dto;


import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.syscfg.domain.model.traffic.PolicePost;


public class PolicePostDto {
	private String policePostId;
	private String postName;
	private String manageOrg;
	private String address;
	private String lonLat;
	private String dutyModel;
	private String headerName;
	private String headerPhone;
	private String lon;
	private String lat;
	
	public PolicePostDto(){
		
	}
	
	public PolicePostDto(PolicePost policePost){
		ObjectMapUtils.parseObject(this, policePost);
    	if (!StringUtil.isNullOrEmpty(policePost.getLonLat())) {
			String tempStr = policePost.getLonLat().trim();
			String[] lonlat = tempStr.substring(tempStr.indexOf("(") + 1, tempStr.indexOf(")")).split(" ");
			if (lonlat.length > 1) {
				this.setLon(lonlat[0]);
				this.setLat(lonlat[1]);
			}
		}
	}	
	public PolicePost parseToPolicePost(){
		PolicePost policePost=new PolicePost();
    	ObjectMapUtils.parseObject(policePost, this);
    	if (!StringUtil.isNullOrEmpty(this.getLon()) && !StringUtil.isNullOrEmpty(this.getLat())) {
    		policePost.setLonLat("POINT(" + this.getLon().trim() + " " + this.getLat().trim() + ")");
		}
    	else{
    		policePost.setLonLat(null);
    	}
		return policePost;
	}
	
	/**
	 * getter method
	 * @return the lon
	 */
	
	public String getLon() {
		return lon;
	}

	/**
	 * setter method
	 * @param lon the lon to set
	 */
	
	public void setLon(String lon) {
		this.lon = lon;
	}

	/**
	 * getter method
	 * @return the lat
	 */
	
	public String getLat() {
		return lat;
	}

	/**
	 * setter method
	 * @param lat the lat to set
	 */
	
	public void setLat(String lat) {
		this.lat = lat;
	}

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
	public void getDtoFromDomain(PolicePost policePost){
		this.policePostId=policePost.getPolicePostId();
		this.postName=policePost.getPostName();
	}
}
