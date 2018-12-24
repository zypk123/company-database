package cy.its.device.domain.model;

public class TollgateSys extends SysParam {
	
	private String deviceId;

	private String trackSysTollgateNbr;

	private String tollgateType;

	private String isConnectTrackSys;

	private String interceptConditions;

	private String lawEnforceStationCode;

	private String upRelatedVideoList;

	private String downRelatedVideoList;

	private String matchTypeList;

	private String highwayEntranceExit;

	private String serviceEntranceExit;

	private String photoModel;

	private Short largeCarSnapImages;

    private String detectionMode;

    private String isVioSupport;

    private String integratePlatformNbr;
    
    private String isFlowSupport;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getTrackSysTollgateNbr() {
		return trackSysTollgateNbr;
	}

	public void setTrackSysTollgateNbr(String trackSysTollgateNbr) {
		this.trackSysTollgateNbr = trackSysTollgateNbr;
	}

	public String getTollgateType() {
		return tollgateType;
	}

	public void setTollgateType(String tollgateType) {
		this.tollgateType = tollgateType;
	}

	public String getIsConnectTrackSys() {
		return isConnectTrackSys;
	}

	public void setIsConnectTrackSys(String isConnectTrackSys) {
		this.isConnectTrackSys = isConnectTrackSys;
	}

	public String getInterceptConditions() {
		return interceptConditions;
	}

	public void setInterceptConditions(String interceptConditions) {
		this.interceptConditions = interceptConditions;
	}

	public String getLawEnforceStationCode() {
		return lawEnforceStationCode;
	}

	public void setLawEnforceStationCode(String lawEnforceStationCode) {
		this.lawEnforceStationCode = lawEnforceStationCode;
	}

	public String getUpRelatedVideoList() {
		return upRelatedVideoList;
	}

	public void setUpRelatedVideoList(String upRelatedVideoList) {
		this.upRelatedVideoList = upRelatedVideoList;
	}

	public String getDownRelatedVideoList() {
		return downRelatedVideoList;
	}

	public void setDownRelatedVideoList(String downRelatedVideoList) {
		this.downRelatedVideoList = downRelatedVideoList;
	}

	public String getMatchTypeList() {
		return matchTypeList;
	}

	public void setMatchTypeList(String matchTypeList) {
		this.matchTypeList = matchTypeList;
	}

	public String getHighwayEntranceExit() {
		return highwayEntranceExit;
	}

	public void setHighwayEntranceExit(String highwayEntranceExit) {
		this.highwayEntranceExit = highwayEntranceExit;
	}

	public String getServiceEntranceExit() {
		return serviceEntranceExit;
	}

	public void setServiceEntranceExit(String serviceEntranceExit) {
		this.serviceEntranceExit = serviceEntranceExit;
	}

	public String getPhotoModel() {
		return photoModel;
	}

	public void setPhotoModel(String photoModel) {
		this.photoModel = photoModel;
	}

	public Short getLargeCarSnapImages() {
		return largeCarSnapImages;
	}

	public void setLargeCarSnapImages(Short largeCarSnapImages) {
		this.largeCarSnapImages = largeCarSnapImages;
	}

	public String getDetectionMode() {
		return detectionMode;
	}

	public void setDetectionMode(String detectionMode) {
		this.detectionMode = detectionMode;
	}

    public String getIsVioSupport() {
        return isVioSupport;
    }

    public void setIsVioSupport(String isVioSupport) {
        this.isVioSupport = isVioSupport;
    }

    public String getIntegratePlatformNbr() {
        return integratePlatformNbr;
    }

    public void setIntegratePlatformNbr(String integratePlatformNbr) {
        this.integratePlatformNbr = integratePlatformNbr;
    }	
    
	public String getIsFlowSupport() {
		return isFlowSupport;
	}

	public void setIsFlowSupport(String isFlowSupport) {
		this.isFlowSupport = isFlowSupport;
	}

		@Override
	public void attatchSys(String deviceId) {
		this.setDeviceId(deviceId);
	}
}