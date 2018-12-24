package cy.its.service.device.statusAnalysis.model;

import java.util.Date;

public class Period {

	private Date beginTime;
	
	private Date endTime;

	public Period(Date beginTime, Date endTime) {
		this.beginTime = beginTime;
		this.endTime = endTime;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}
	
	
}
