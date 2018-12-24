package cy.its.sysCfg.rest.dto;

import cy.its.com.dto.BaseDto;

public class DownloadQryDto extends BaseDto {
	private String downloadTitle;
	String createTimeFr;
	String createTimeTo;

	public String getDownloadTitle() {
		return downloadTitle;
	}

	public void setDownloadTitle(String downloadTitle) {
		this.downloadTitle = downloadTitle;
	}

	public String getCreateTimeFr() {
		return createTimeFr;
	}

	public void setCreateTimeFr(String createTimeFr) {
		this.createTimeFr = createTimeFr;
	}

	public String getCreateTimeTo() {
		return createTimeTo;
	}

	public void setCreateTimeTo(String createTimeTo) {
		this.createTimeTo = createTimeTo;
	}
}
