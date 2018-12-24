package cy.its.trafficSituation.domain.model;

import java.math.BigDecimal;
import java.util.Date;

public class TrafficWeather {
    private String meteorologicDataId;

    private String deviceSysNbr;

    private String orgCode;

    private String siteCode;

    private BigDecimal weatherTemperature;

    private BigDecimal relativeHumidity;

    private BigDecimal airPressure;

    private BigDecimal windSpeed;

    private BigDecimal windDirection;

    private BigDecimal waterFilmHeight;

    private String waterType;

    private BigDecimal rainStrong;

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

    public String getMeteorologicDataId() {
        return meteorologicDataId;
    }

    public void setMeteorologicDataId(String meteorologicDataId) {
        this.meteorologicDataId = meteorologicDataId;
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

    public BigDecimal getWeatherTemperature() {
        return weatherTemperature;
    }

    public void setWeatherTemperature(BigDecimal weatherTemperature) {
        this.weatherTemperature = weatherTemperature;
    }

    public BigDecimal getRelativeHumidity() {
        return relativeHumidity;
    }

    public void setRelativeHumidity(BigDecimal relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    public BigDecimal getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(BigDecimal airPressure) {
        this.airPressure = airPressure;
    }

    public BigDecimal getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(BigDecimal windSpeed) {
        this.windSpeed = windSpeed;
    }

    public BigDecimal getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(BigDecimal windDirection) {
        this.windDirection = windDirection;
    }

    public BigDecimal getWaterFilmHeight() {
        return waterFilmHeight;
    }

    public void setWaterFilmHeight(BigDecimal waterFilmHeight) {
        this.waterFilmHeight = waterFilmHeight;
    }

    public String getWaterType() {
        return waterType;
    }

    public void setWaterType(String waterType) {
        this.waterType = waterType;
    }

    public BigDecimal getRainStrong() {
        return rainStrong;
    }

    public void setRainStrong(BigDecimal rainStrong) {
        this.rainStrong = rainStrong;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
}