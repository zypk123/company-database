package cy.its.trafficSituation.domain.model;

import java.math.BigDecimal;
import java.util.Date;

public class TrafficRoadsensor {
    private String roadsensorDataId;

    private String deviceSysNbr;

    private String orgCode;

    private String siteCode;

    private BigDecimal roadTemperature;

    private BigDecimal roadbedTemperature;

    private BigDecimal waterFileHeigh;

    private BigDecimal salinity;

    private BigDecimal freezingTemperature;

    private String roadCondition;

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

    public String getRoadsensorDataId() {
        return roadsensorDataId;
    }

    public void setRoadsensorDataId(String roadsensorDataId) {
        this.roadsensorDataId = roadsensorDataId;
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

    public BigDecimal getRoadTemperature() {
        return roadTemperature;
    }

    public void setRoadTemperature(BigDecimal roadTemperature) {
        this.roadTemperature = roadTemperature;
    }

    public BigDecimal getRoadbedTemperature() {
        return roadbedTemperature;
    }

    public void setRoadbedTemperature(BigDecimal roadbedTemperature) {
        this.roadbedTemperature = roadbedTemperature;
    }

    public BigDecimal getWaterFileHeigh() {
        return waterFileHeigh;
    }

    public void setWaterFileHeigh(BigDecimal waterFileHeigh) {
        this.waterFileHeigh = waterFileHeigh;
    }

    public BigDecimal getSalinity() {
        return salinity;
    }

    public void setSalinity(BigDecimal salinity) {
        this.salinity = salinity;
    }

    public BigDecimal getFreezingTemperature() {
        return freezingTemperature;
    }

    public void setFreezingTemperature(BigDecimal freezingTemperature) {
        this.freezingTemperature = freezingTemperature;
    }

    public String getRoadCondition() {
        return roadCondition;
    }

    public void setRoadCondition(String roadCondition) {
        this.roadCondition = roadCondition;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
}