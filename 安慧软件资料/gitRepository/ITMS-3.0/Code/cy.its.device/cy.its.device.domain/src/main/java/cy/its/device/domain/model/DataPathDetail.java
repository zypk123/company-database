package cy.its.device.domain.model;

public class DataPathDetail {
    private String deviceSysNbr;

    /**
     * 设备到前置监控服务器时间延迟
     */
    private Short dev2svrDelay;

    /**
     * 前置监控服务器到后置监控服务器时间延迟
     */
    private Short svr2afsvrDelay;
    
    /**
     * 后置监控服务器到ICE2MQ时间延迟
     */
    private Short afsvr2imDelay;

    /**
     * ICE2MQ到监控服务器前置机时间延迟
     */
    private Short im2preDelay;

    /**
     * 前置机到后置服务器时间延迟
     */
    private Short pre2afDelay;

    /**
     * 后置服务器到上传服务器时间延迟
     */
    private Short af2updDelay;

    /**
     * 上传开始到上传结束时间延迟
     */
    private Short upd2upd0Delay;

    private Short avgDelay;

    private String deviceName;

	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}

	public void setDeviceSysNbr(String deviceSysNbr) {
		this.deviceSysNbr = deviceSysNbr;
	}

	public Short getDev2svrDelay() {
		return dev2svrDelay;
	}

	public void setDev2svrDelay(Short dev2svrDelay) {
		this.dev2svrDelay = dev2svrDelay;
	}

	public Short getSvr2afsvrDelay() {
		return svr2afsvrDelay;
	}

	public void setSvr2afsvrDelay(Short svr2afsvrDelay) {
		this.svr2afsvrDelay = svr2afsvrDelay;
	}

	public Short getAfsvr2imDelay() {
		return afsvr2imDelay;
	}

	public void setAfsvr2imDelay(Short afsvr2imDelay) {
		this.afsvr2imDelay = afsvr2imDelay;
	}

	public Short getIm2preDelay() {
		return im2preDelay;
	}

	public void setIm2preDelay(Short im2preDelay) {
		this.im2preDelay = im2preDelay;
	}

	public Short getPre2afDelay() {
		return pre2afDelay;
	}

	public void setPre2afDelay(Short pre2afDelay) {
		this.pre2afDelay = pre2afDelay;
	}

	public Short getAf2updDelay() {
		return af2updDelay;
	}

	public void setAf2updDelay(Short af2updDelay) {
		this.af2updDelay = af2updDelay;
	}

	public Short getUpd2upd0Delay() {
		return upd2upd0Delay;
	}

	public void setUpd2upd0Delay(Short upd2upd0Delay) {
		this.upd2upd0Delay = upd2upd0Delay;
	}

	public Short getAvgDelay() {
		return avgDelay;
	}

	public void setAvgDelay(Short avgDelay) {
		this.avgDelay = avgDelay;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

    
}