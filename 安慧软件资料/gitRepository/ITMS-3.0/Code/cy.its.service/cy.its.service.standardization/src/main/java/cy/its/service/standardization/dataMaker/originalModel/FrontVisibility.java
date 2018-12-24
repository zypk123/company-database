package cy.its.service.standardization.dataMaker.originalModel;

import java.util.Map;

public class FrontVisibility extends BaseOrginalModel{
	
	private String devNbr;

	private String meterNbr;

	private String commandCode;

	private String recordTime;

	private String dataQualityCode;

	private int oneMinuteVisibilityValue;

	private int tenMinuteVisibilityValue;

	private int cleanDegree;

	private int caseTemperature;

	private String powerVolatage;

	private Map<String, String> extendedProperties;

	public String getDevNbr() {
		return devNbr;
	}

	public void setDevNbr(String devNbr) {
		this.devNbr = devNbr;
	}

	public String getMeterNbr() {
		return meterNbr;
	}

	public void setMeterNbr(String meterNbr) {
		this.meterNbr = meterNbr;
	}

	public String getCommandCode() {
		return commandCode;
	}

	public void setCommandCode(String commandCode) {
		this.commandCode = commandCode;
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public String getDataQualityCode() {
		return dataQualityCode;
	}

	public void setDataQualityCode(String dataQualityCode) {
		this.dataQualityCode = dataQualityCode;
	}

	public int getOneMinuteVisibilityValue() {
		return oneMinuteVisibilityValue;
	}

	public void setOneMinuteVisibilityValue(int oneMinuteVisibilityValue) {
		this.oneMinuteVisibilityValue = oneMinuteVisibilityValue;
	}

	public int getTenMinuteVisibilityValue() {
		return tenMinuteVisibilityValue;
	}

	public void setTenMinuteVisibilityValue(int tenMinuteVisibilityValue) {
		this.tenMinuteVisibilityValue = tenMinuteVisibilityValue;
	}

	public int getCleanDegree() {
		return cleanDegree;
	}

	public void setCleanDegree(int cleanDegree) {
		this.cleanDegree = cleanDegree;
	}

	public int getCaseTemperature() {
		return caseTemperature;
	}

	public void setCaseTemperature(int caseTemperature) {
		this.caseTemperature = caseTemperature;
	}

	public String getPowerVolatage() {
		return powerVolatage;
	}

	public void setPowerVolatage(String powerVolatage) {
		this.powerVolatage = powerVolatage;
	}

	public Map<String, String> getExtendedProperties() {
		return extendedProperties;
	}

	public void setExtendedProperties(Map<String, String> extendedProperties) {
		this.extendedProperties = extendedProperties;
	}
}
