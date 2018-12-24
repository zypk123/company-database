package cy.its.service.standardization.dictionary.model;

import cy.its.service.common.dataAccess.MapColumn;

public class VioAction {

	@MapColumn
	private String vioActionCode;
	
	@MapColumn
	private String vioType;
	
	@MapColumn
	private String vioSummary;
	
	public String getVioActionCode() {
		return vioActionCode;
	}
	public void setVioActionCode(String vioActionCode) {
		this.vioActionCode = vioActionCode;
	}
	public String getVioType() {
		return vioType;
	}
	public void setVioType(String vioType) {
		this.vioType = vioType;
	}
	public String getVioSummary() {
		return vioSummary;
	}
	public void setVioSummary(String vioSummary) {
		this.vioSummary = vioSummary;
	}
	
	
}
