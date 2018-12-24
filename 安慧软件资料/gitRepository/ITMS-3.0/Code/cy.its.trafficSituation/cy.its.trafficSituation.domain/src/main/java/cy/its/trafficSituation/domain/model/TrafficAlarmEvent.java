package cy.its.trafficSituation.domain.model;

import java.util.Date;

public class TrafficAlarmEvent {  
	private String alarmEventId;

    private String alarmValueId;

    private String orgCode;

    private String deviceSysNbr;

    private String siteCode;

    private String regionalId;

    private String sectionId;

    private String alarmEventType;

    private String subAlarmEvent;

    private Date startAlarmTime;

    private Date endAlarmTime;

    private String alarmGrade;

    private String alarmDesc;

    private String noFlowPeriod;

    private String regionSaturation;

    private String validity;

    private String confirmPerson;

    private Date confirmTime;
    
    private String siteName;
    
    private String weatherData;
    private String min;
    private String max;
    
    private String orgPrivilegeCode;
	
	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}
   
	public String getWeatherData() {
		return weatherData;
	}

	public void setWeatherData(String weatherData) {
		this.weatherData = weatherData;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}
    
   
    public String getAlarmEventId() {
        return alarmEventId;
    }

    public void setAlarmEventId(String alarmEventId) {
        this.alarmEventId = alarmEventId;
    }

    public String getAlarmValueId() {
        return alarmValueId;
    }

    public void setAlarmValueId(String alarmValueId) {
        this.alarmValueId = alarmValueId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getDeviceSysNbr() {
        return deviceSysNbr;
    }

    public void setDeviceSysNbr(String deviceSysNbr) {
        this.deviceSysNbr = deviceSysNbr;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getRegionalId() {
        return regionalId;
    }

    public void setRegionalId(String regionalId) {
        this.regionalId = regionalId;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getAlarmEventType() {
        return alarmEventType;
    }

    public void setAlarmEventType(String alarmEventType) {
        this.alarmEventType = alarmEventType;
    }

    public String getSubAlarmEvent() {
        return subAlarmEvent;
    }

    public void setSubAlarmEvent(String subAlarmEvent) {
        this.subAlarmEvent = subAlarmEvent;
    }

    public Date getStartAlarmTime() {
        return startAlarmTime;
    }

    public void setStartAlarmTime(Date startAlarmTime) {
        this.startAlarmTime = startAlarmTime;
    }

    public Date getEndAlarmTime() {
        return endAlarmTime;
    }

    public void setEndAlarmTime(Date endAlarmTime) {
        this.endAlarmTime = endAlarmTime;
    }

    public String getAlarmGrade() {
        return alarmGrade;
    }

    public void setAlarmGrade(String alarmGrade) {
        this.alarmGrade = alarmGrade;
    }

    public String getAlarmDesc() {
        return alarmDesc;
    }

    public void setAlarmDesc(String alarmDesc) {
        this.alarmDesc = alarmDesc;
    }

    public String getNoFlowPeriod() {
        return noFlowPeriod;
    }

    public void setNoFlowPeriod(String noFlowPeriod) {
        this.noFlowPeriod = noFlowPeriod;
    }

    public String getRegionSaturation() {
        return regionSaturation;
    }

    public void setRegionSaturation(String regionSaturation) {
        this.regionSaturation = regionSaturation;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getConfirmPerson() {
        return confirmPerson;
    }

    public void setConfirmPerson(String confirmPerson) {
        this.confirmPerson = confirmPerson;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
    
    
}