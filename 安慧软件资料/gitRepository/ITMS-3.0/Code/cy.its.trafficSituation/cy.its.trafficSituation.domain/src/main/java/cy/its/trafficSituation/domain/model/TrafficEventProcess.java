package cy.its.trafficSituation.domain.model;

import java.util.Date;

public class TrafficEventProcess {
    private String eventProcessId;

    private String alarmEventId;

    private String manualReportTraId;

    private String dealMethod;

    private String dealPerson;

    private Date dealTime;

    private String dealPersonPhone;

    private String remark;

    public String getEventProcessId() {
        return eventProcessId;
    }

    public void setEventProcessId(String eventProcessId) {
        this.eventProcessId = eventProcessId;
    }

    public String getAlarmEventId() {
        return alarmEventId;
    }

    public void setAlarmEventId(String alarmEventId) {
        this.alarmEventId = alarmEventId;
    }

    public String getManualReportTraId() {
        return manualReportTraId;
    }

    public void setManualReportTraId(String manualReportTraId) {
        this.manualReportTraId = manualReportTraId;
    }

    public String getDealMethod() {
        return dealMethod;
    }

    public void setDealMethod(String dealMethod) {
        this.dealMethod = dealMethod;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	/**
	 * getter method
	 * @return the dealPersonPhone
	 */
	
	public String getDealPersonPhone() {
		return dealPersonPhone;
	}

	/**
	 * setter method
	 * @param dealPersonPhone the dealPersonPhone to set
	 */
	
	public void setDealPersonPhone(String dealPersonPhone) {
		this.dealPersonPhone = dealPersonPhone;
	}

	/**
	 * getter method
	 * @return the dealTime
	 */
	
	public Date getDealTime() {
		return dealTime;
	}

	/**
	 * setter method
	 * @param dealTime the dealTime to set
	 */
	
	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	/**
	 * getter method
	 * @return the dealPerson
	 */
	
	public String getDealPerson() {
		return dealPerson;
	}

	/**
	 * setter method
	 * @param dealPerson the dealPerson to set
	 */
	
	public void setDealPerson(String dealPerson) {
		this.dealPerson = dealPerson;
	}
}