package cy.its.device.domain.model;

public class DeviceSysCapability {
	/**
	 * 系统ID
	 */
    private String deviceId;

    /**
     * 当前系统的参数类型:
     * TollgateSys     卡口
     * EventDetection  事件检测
     * VioDevice       违法取证
     * Video           视频
     * Meteorologic    气象类
     * NoParam         系统无参数类型
     */
    private String paramtype;

    /**
     * 是否支持事件检测:  0  不支持; 1  支持;
     */
    private String isEventSupport;

    /**
     * 是否支持卡口过车:  0  不支持; 1  支持;
     */
    private String isTollgateSupport;

    /**
     * 是否支持视频:  0  不支持; 1  支持;
     */
    private String isVideoSupport;

    /**
     * 是否支持违法取证:  0  不支持; 1  支持;
     */
    private String isVioSupport;
    
    /**
     * 是否支持流量功能:  0  不支持; 1  支持;
     */
    private String isFlowSupport;

    /**
     * 是否支持气象检测:  0  不支持; 1  支持;
     */
    private String isWeatherSupport;

    /**
     * 是否支持能见度:  0  不支持; 1  支持;
     */
    private String isVisibilitySupport;

    /**
     * 是否支持路感:  0  不支持; 1  支持;
     */
    private String isRoadsensorSupport;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getParamtype() {
        return paramtype;
    }

    public void setParamtype(String paramtype) {
        this.paramtype = paramtype;
    }

    public String getIsEventSupport() {
        return isEventSupport;
    }

    public void setIsEventSupport(String isEventSupport) {
        this.isEventSupport = isEventSupport;
    }

    public String getIsTollgateSupport() {
        return isTollgateSupport;
    }

    public void setIsTollgateSupport(String isTollgateSupport) {
        this.isTollgateSupport = isTollgateSupport;
    }

    public String getIsVideoSupport() {
        return isVideoSupport;
    }

    public void setIsVideoSupport(String isVideoSupport) {
        this.isVideoSupport = isVideoSupport;
    }

    public String getIsVioSupport() {
        return isVioSupport;
    }

    public void setIsVioSupport(String isVioSupport) {
        this.isVioSupport = isVioSupport;
    }

    public String getIsWeatherSupport() {
        return isWeatherSupport;
    }

    public void setIsWeatherSupport(String isWeatherSupport) {
        this.isWeatherSupport = isWeatherSupport;
    }

    public String getIsVisibilitySupport() {
        return isVisibilitySupport;
    }

    public void setIsVisibilitySupport(String isVisibilitySupport) {
        this.isVisibilitySupport = isVisibilitySupport;
    }

    public String getIsRoadsensorSupport() {
        return isRoadsensorSupport;
    }

    public void setIsRoadsensorSupport(String isRoadsensorSupport) {
        this.isRoadsensorSupport = isRoadsensorSupport;
    }

	public String getIsFlowSupport() {
		return isFlowSupport;
	}

	public void setIsFlowSupport(String isFlowSupport) {
		this.isFlowSupport = isFlowSupport;
	}
}