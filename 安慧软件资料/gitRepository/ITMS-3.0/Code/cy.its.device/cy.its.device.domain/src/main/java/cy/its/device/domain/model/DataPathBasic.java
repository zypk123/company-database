package cy.its.device.domain.model;

public class DataPathBasic {
    private String deviceSysNbr;
    
    private String isConnectTrackSys;

    private String roadName;

    private String siteName;

    private String direction;

    private String orgName;

    private Integer uploadTotal;

    private Integer avgDelay;

    public String getDeviceSysNbr() {
        return deviceSysNbr;
    }

    public void setDeviceSysNbr(String deviceSysNbr) {
        this.deviceSysNbr = deviceSysNbr;
    }

    public String getIsConnectTrackSys() {
		return isConnectTrackSys;
	}

	public void setIsConnectTrackSys(String isConnectTrackSys) {
		this.isConnectTrackSys = isConnectTrackSys;
	}

	public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getUploadTotal() {
        return uploadTotal;
    }

    public void setUploadTotal(Integer uploadTotal) {
        this.uploadTotal = uploadTotal;
    }

    public Integer getAvgDelay() {
        return avgDelay;
    }

    public void setAvgDelay(Integer avgDelay) {
        this.avgDelay = avgDelay;
    }
}