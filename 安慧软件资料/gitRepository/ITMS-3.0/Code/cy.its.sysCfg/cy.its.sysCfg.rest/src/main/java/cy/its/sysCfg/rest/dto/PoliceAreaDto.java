package cy.its.sysCfg.rest.dto;

import cy.its.syscfg.domain.model.traffic.PoliceArea;

public class PoliceAreaDto {
		private String policeAreaId;

	    private String orgId;
	    private String lonLat;

	    public String getLonLat() {
			return lonLat;
		}

		public void setLonLat(String lonLat) {
			this.lonLat = lonLat;
		}

		public String getPoliceAreaId() {
	        return policeAreaId;
	    }

	    public void setPoliceAreaId(String policeAreaId) {
	        this.policeAreaId = policeAreaId;
	    }

	    public String getOrgId() {
	        return orgId;
	    }

	    public void setOrgId(String orgId) {
	        this.orgId = orgId;
	    }
	    public PoliceAreaDto(){
	    	
	    }
	    public PoliceAreaDto(PoliceArea policeArea){
	    	this.setOrgId(policeArea.getOrgId());
	    	this.setLonLat(policeArea.getLonLat());
	    	this.setPoliceAreaId(policeArea.getPoliceAreaId());
	    }
	    public PoliceArea parseToPoliceArea(){
	    	PoliceArea pArea=new PoliceArea();
	    	pArea.setLonLat(this.getLonLat());
	    	pArea.setOrgId(this.getOrgId());
	    	pArea.setPoliceAreaId(this.getPoliceAreaId());
	    	return pArea;
	    }
}
