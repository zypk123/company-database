package cy.its.device.domain.model;

import java.util.Date;

public class VTollgate {
    private String deviceId;

    private String deviceSysNbr;

    private String tollgateType;

    private String orgId;

    private String ownership;

    private String roadId;

    private String siteId;

    private String mountingFacilityType;

    private String sectionIdList;

    private String isConnectTrackSys;

    private String isVioSupport;

    private Date enableUpdateDate;
    
    private String useStatusFlag;
    
    private String trackSysTollgateNbr;
    
    private String integratePlatformNbr;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceSysNbr() {
        return deviceSysNbr;
    }

    public void setDeviceSysNbr(String deviceSysNbr) {
        this.deviceSysNbr = deviceSysNbr;
    }

    public String getTollgateType() {
        return tollgateType;
    }

    public void setTollgateType(String tollgateType) {
        this.tollgateType = tollgateType;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getRoadId() {
        return roadId;
    }

    public void setRoadId(String roadId) {
        this.roadId = roadId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getMountingFacilityType() {
        return mountingFacilityType;
    }

    public void setMountingFacilityType(String mountingFacilityType) {
        this.mountingFacilityType = mountingFacilityType;
    }

    public String getSectionIdList() {
        return sectionIdList;
    }

    public void setSectionIdList(String sectionIdList) {
        this.sectionIdList = sectionIdList;
    }

    public String getIsConnectTrackSys() {
        return isConnectTrackSys;
    }

    public void setIsConnectTrackSys(String isConnectTrackSys) {
        this.isConnectTrackSys = isConnectTrackSys;
    }

    public String getIsVioSupport() {
        return isVioSupport;
    }

    public void setIsVioSupport(String isVioSupport) {
        this.isVioSupport = isVioSupport;
    }

    public Date getEnableUpdateDate() {
        return enableUpdateDate;
    }

    public void setEnableUpdateDate(Date enableUpdateDate) {
        this.enableUpdateDate = enableUpdateDate;
    }

	public String getUseStatusFlag() {
		return useStatusFlag;
	}

	public void setUseStatusFlag(String useStatusFlag) {
		this.useStatusFlag = useStatusFlag;
	}

	public String getTrackSysTollgateNbr() {
		return trackSysTollgateNbr;
	}

	public void setTrackSysTollgateNbr(String trackSysTollgateNbr) {
		this.trackSysTollgateNbr = trackSysTollgateNbr;
	}

	public String getIntegratePlatformNbr() {
		return integratePlatformNbr;
	}

	public void setIntegratePlatformNbr(String integratePlatformNbr) {
		this.integratePlatformNbr = integratePlatformNbr;
	}
}