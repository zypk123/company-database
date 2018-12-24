package cy.its.trafficSituation.domain.model;

import java.util.Date;

public class TrafficVisibility {
    private String visibilityDataId;

    private String deviceSysNbr;

    private String orgCode;

    private String siteCode;

    private Long oneMinuteVisibility;

    private Long tenMinuteVisibility;

    private Long cleanDegre;

    private Long caseTemperature;

    private String powerVolatage;

    private Date recordTime;
    
    private String orgPrivilegeCode;

    /**
	 * getter method
	 * @return the orgPrivilegeCode
	 */
	
	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	/**
	 * setter method
	 * @param orgPrivilegeCode the orgPrivilegeCode to set
	 */
	
	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}

    public String getVisibilityDataId() {
        return visibilityDataId;
    }

    public void setVisibilityDataId(String visibilityDataId) {
        this.visibilityDataId = visibilityDataId;
    }

    public String getDeviceSysNbr() {
        return deviceSysNbr;
    }

    public void setDeviceSysNbr(String deviceSysNbr) {
        this.deviceSysNbr = deviceSysNbr;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public Long getOneMinuteVisibility() {
        return oneMinuteVisibility;
    }

    public void setOneMinuteVisibility(Long oneMinuteVisibility) {
        this.oneMinuteVisibility = oneMinuteVisibility;
    }

    public Long getTenMinuteVisibility() {
        return tenMinuteVisibility;
    }

    public void setTenMinuteVisibility(Long tenMinuteVisibility) {
        this.tenMinuteVisibility = tenMinuteVisibility;
    }

    public Long getCleanDegre() {
        return cleanDegre;
    }

    public void setCleanDegre(Long cleanDegre) {
        this.cleanDegre = cleanDegre;
    }

    public Long getCaseTemperature() {
        return caseTemperature;
    }

    public void setCaseTemperature(Long caseTemperature) {
        this.caseTemperature = caseTemperature;
    }

    public String getPowerVolatage() {
        return powerVolatage;
    }

    public void setPowerVolatage(String powerVolatage) {
        this.powerVolatage = powerVolatage;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
}