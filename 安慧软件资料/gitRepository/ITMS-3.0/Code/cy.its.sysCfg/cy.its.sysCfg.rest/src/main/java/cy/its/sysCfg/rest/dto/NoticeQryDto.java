package cy.its.sysCfg.rest.dto;

import java.util.Date;

import cy.its.com.dto.BaseDto;

public class NoticeQryDto extends BaseDto{
	String title;
	String createTimeFr;
	String createTimeTo;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
