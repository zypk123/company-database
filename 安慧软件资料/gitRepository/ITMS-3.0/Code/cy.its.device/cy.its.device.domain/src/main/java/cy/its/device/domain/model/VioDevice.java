package cy.its.device.domain.model;

import java.util.Date;

public class VioDevice extends SysParam  {
    private String deviceId;

    private String integratePlatformNbr;

    private String collcetionOrg;

    private String collectionPerson;

    private Date validityDate;

    private String vioCollectionType;

    private Long limitLarge;

    private Long limitSmall;

    private Long limitOthers;

    private Long limitLower;

    private Integer limitLargeMargin;

    private Integer limitSmallMargin;

    private String relatedVideoList;

    private String relatedVariableSpeed;

    private String isTollgateSupport;

    private String isConnectTrackSys;

    private String trackSysNbrMap;

    private String isFlowSupport;
    
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getIntegratePlatformNbr() {
        return integratePlatformNbr;
    }

    public void setIntegratePlatformNbr(String integratePlatformNbr) {
        this.integratePlatformNbr = integratePlatformNbr;
    }

    public String getCollcetionOrg() {
        return collcetionOrg;
    }

    public void setCollcetionOrg(String collcetionOrg) {
        this.collcetionOrg = collcetionOrg;
    }

    public String getCollectionPerson() {
        return collectionPerson;
    }

    public void setCollectionPerson(String collectionPerson) {
        this.collectionPerson = collectionPerson;
    }

    public Date getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }

    public String getVioCollectionType() {
        return vioCollectionType;
    }

    public void setVioCollectionType(String vioCollectionType) {
        this.vioCollectionType = vioCollectionType;
    }

    public Long getLimitLarge() {
        return limitLarge;
    }

    public void setLimitLarge(Long limitLarge) {
        this.limitLarge = limitLarge;
    }

    public Long getLimitSmall() {
        return limitSmall;
    }

    public void setLimitSmall(Long limitSmall) {
        this.limitSmall = limitSmall;
    }

    public Long getLimitOthers() {
        return limitOthers;
    }

    public void setLimitOthers(Long limitOthers) {
        this.limitOthers = limitOthers;
    }

    public Long getLimitLower() {
        return limitLower;
    }

    public void setLimitLower(Long limitLower) {
        this.limitLower = limitLower;
    }

    public Integer getLimitLargeMargin() {
        return limitLargeMargin;
    }

    public void setLimitLargeMargin(Integer limitLargeMargin) {
        this.limitLargeMargin = limitLargeMargin;
    }

    public Integer getLimitSmallMargin() {
        return limitSmallMargin;
    }

    public void setLimitSmallMargin(Integer limitSmallMargin) {
        this.limitSmallMargin = limitSmallMargin;
    }

    public String getRelatedVideoList() {
        return relatedVideoList;
    }

    public void setRelatedVideoList(String relatedVideoList) {
        this.relatedVideoList = relatedVideoList;
    }

    public String getRelatedVariableSpeed() {
        return relatedVariableSpeed;
    }

    public void setRelatedVariableSpeed(String relatedVariableSpeed) {
        this.relatedVariableSpeed = relatedVariableSpeed;
    }
    
    public String getIsTollgateSupport() {
        return isTollgateSupport;
    }

    public void setIsTollgateSupport(String isTollgateSupport) {
        this.isTollgateSupport = isTollgateSupport;
    }

    public String getIsConnectTrackSys() {
        return isConnectTrackSys;
    }

    public void setIsConnectTrackSys(String isConnectTrackSys) {
        this.isConnectTrackSys = isConnectTrackSys;
    }

    public String getTrackSysNbrMap() {
        return trackSysNbrMap;
    }

    public void setTrackSysNbrMap(String trackSysNbrMap) {
        this.trackSysNbrMap = trackSysNbrMap;
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