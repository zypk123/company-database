package cy.its.trafficSituation.rest.dto;

import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.syscfg.domain.model.traffic.EnforceStation;

public class EnforceStationDto {
	private String lawEnforceStationId;

    private String lawEnforceStationCode;

    private String lawEnforceStationName;

    private String orgId;

    private String dutyModel;

    private Short policeCounts;

    private String stationRating;

    private String contactTel;

    private String serviceStationHeader;

    private String headerTel;

    private String entranceTollgateId;

    private String exitTollgateId;

    private String lawEnforceVideoId;

    private String indoorVideoId;

    private String storagePeriod;

    private String interceptionLedId;

    private String isConnectPoliceNet;

    private String importantCheckingCase;

    private String lonLat;
    private String lon;
    private String lat;

    /**
	 * getter method
	 * @return the lon
	 */
	
	public String getLon() {
		return lon;
	}

	/**
	 * setter method
	 * @param lon the lon to set
	 */
	
	public void setLon(String lon) {
		this.lon = lon;
	}

	/**
	 * getter method
	 * @return the lat
	 */
	
	public String getLat() {
		return lat;
	}

	/**
	 * setter method
	 * @param lat the lat to set
	 */
	
	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLawEnforceStationId() {
        return lawEnforceStationId;
    }

    public void setLawEnforceStationId(String lawEnforceStationId) {
        this.lawEnforceStationId = lawEnforceStationId;
    }

    public String getLawEnforceStationCode() {
        return lawEnforceStationCode;
    }

    public void setLawEnforceStationCode(String lawEnforceStationCode) {
        this.lawEnforceStationCode = lawEnforceStationCode;
    }

    public String getLawEnforceStationName() {
        return lawEnforceStationName;
    }

    public void setLawEnforceStationName(String lawEnforceStationName) {
        this.lawEnforceStationName = lawEnforceStationName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getDutyModel() {
        return dutyModel;
    }

    public void setDutyModel(String dutyModel) {
        this.dutyModel = dutyModel;
    }

    public Short getPoliceCounts() {
        return policeCounts;
    }

    public void setPoliceCounts(Short policeCounts) {
        this.policeCounts = policeCounts;
    }

    public String getStationRating() {
        return stationRating;
    }

    public void setStationRating(String stationRating) {
        this.stationRating = stationRating;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getServiceStationHeader() {
        return serviceStationHeader;
    }

    public void setServiceStationHeader(String serviceStationHeader) {
        this.serviceStationHeader = serviceStationHeader;
    }

    public String getHeaderTel() {
        return headerTel;
    }

    public void setHeaderTel(String headerTel) {
        this.headerTel = headerTel;
    }

    public String getEntranceTollgateId() {
        return entranceTollgateId;
    }

    public void setEntranceTollgateId(String entranceTollgateId) {
        this.entranceTollgateId = entranceTollgateId;
    }

    public String getExitTollgateId() {
        return exitTollgateId;
    }

    public void setExitTollgateId(String exitTollgateId) {
        this.exitTollgateId = exitTollgateId;
    }

    public String getLawEnforceVideoId() {
        return lawEnforceVideoId;
    }

    public void setLawEnforceVideoId(String lawEnforceVideoId) {
        this.lawEnforceVideoId = lawEnforceVideoId;
    }

    public String getIndoorVideoId() {
        return indoorVideoId;
    }

    public void setIndoorVideoId(String indoorVideoId) {
        this.indoorVideoId = indoorVideoId;
    }

    public String getStoragePeriod() {
        return storagePeriod;
    }

    public void setStoragePeriod(String storagePeriod) {
        this.storagePeriod = storagePeriod;
    }

    public String getInterceptionLedId() {
        return interceptionLedId;
    }

    public void setInterceptionLedId(String interceptionLedId) {
        this.interceptionLedId = interceptionLedId;
    }

    public String getIsConnectPoliceNet() {
        return isConnectPoliceNet;
    }

    public void setIsConnectPoliceNet(String isConnectPoliceNet) {
        this.isConnectPoliceNet = isConnectPoliceNet;
    }

    public String getImportantCheckingCase() {
        return importantCheckingCase;
    }

    public void setImportantCheckingCase(String importantCheckingCase) {
        this.importantCheckingCase = importantCheckingCase;
    }

    public String getLonLat() {
        return lonLat;
    }

    public void setLonLat(String lonLat) {
        this.lonLat = lonLat;
    }
    public EnforceStationDto(){
    	
    }
    public EnforceStationDto(EnforceStation enforceStation){
    	ObjectMapUtils.parseObject(this, enforceStation);
    	if (!StringUtil.isNullOrEmpty(enforceStation.getLonLat())) {
			String tempStr = enforceStation.getLonLat().trim();
			String[] lonlat = tempStr.substring(tempStr.indexOf("(") + 1, tempStr.indexOf(")")).split(" ");
			if (lonlat.length > 1) {
				this.setLon(lonlat[0]);
				this.setLat(lonlat[1]);
			}
		}
    }
}
