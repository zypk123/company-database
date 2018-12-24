package cy.its.service.common.dataModel;

import java.math.BigDecimal;
import java.util.Date;

public class DeviceDataPath extends Model {

	/**
	 * 电子监控系统ID
	 */
	@Mapper("DEVICE_ID")
	private String deviceId;
	
	/**
	 * 组件ID
	 */
	@Mapper("SYS_COMPONENT_ID")
	private String sysComponentId;
	
	/**
	 * 机构权限代码
	 */
	@Mapper("ORG_PRIVILEGE_CODE")
	private String orgPrivilegeCode;

	/**
	 * 系统编号
	 */
	@Mapper("DEVICE_SYS_NBR")
	private String deviceSysNbr;

	/**
	 * 设备编号
	 */
	private String deviceNbr;

	/**
	 * 抓拍编号
	 */
	@Mapper("SNAP_NBR")
	private String snapNbr;

	/**
	 * 过车时间
	 */
	@Mapper("PASS_TIME")
	private Date passTime;

	/**
	 * 监控服务器处理时间
	 */
	@Mapper("SERVER_RECEIVING_TIME")
	private Date serverReceivingTime;
	
	/**
	 * 后置监控服务器处理时间
	 */
	@Mapper("AFTER_SERVER_RECEIVING_TIME")
	private Date afterserverReceivingTime;
	
	/**
	 * ICE2MQ处理时间
	 */
	@Mapper("ICE2MQ_TIME")
	private Date ice2mqTime;

	/**
	 * 前置机处理时间
	 */
	@Mapper("PRE_TIME")
	private Date preTime;

	/**
	 * 后置机处理时间
	 */
	@Mapper("AFTER_TIME")
	private Date afterTime;

	/**
	 * 稽查布控系统上传开始时间
	 */
	@Mapper("UPLOAD_TIME")
	private Date uploadTime;

	/**
	 * 稽查布控系统上传结束时间
	 */
	@Mapper("UPLOAD_END_TIME")
	private Date uploadEndTime;

	/**
	 * 总耗时，单位为秒。
	 */
	@Mapper("TOTAL_TIME")
	private BigDecimal totalTime;

	/**
	 * 唯一值
	 */
	@Mapper("DEVICE_KEY")
	private String deviceKey;

	@Mapper("SITE_CODE")
	private String siteCode;	
	
	@Mapper("ROAD_CODE")
	private String roadCode;

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

	public String getSysComponentId() {
		return sysComponentId;
	}

	public void setSysComponentId(String sysComponentId) {
		this.sysComponentId = sysComponentId;
	}

	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}

	public String getDeviceNbr() {
		return deviceNbr;
	}

	public void setDeviceNbr(String deviceNbr) {
		this.deviceNbr = deviceNbr;
	}

	public String getSnapNbr() {
		return snapNbr;
	}

	public void setSnapNbr(String snapNbr) {
		this.snapNbr = snapNbr;
	}

	public Date getPassTime() {
		return passTime;
	}

	public void setPassTime(Date passTime) {
		this.passTime = passTime;
	}

	public Date getServerReceivingTime() {
		return serverReceivingTime;
	}

	public void setServerReceivingTime(Date serverReceivingTime) {
		this.serverReceivingTime = serverReceivingTime;
	}
	
	public Date getAfterserverReceivingTime() {
		return afterserverReceivingTime;
	}

	public void setAfterserverReceivingTime(Date afterserverReceivingTime) {
		this.afterserverReceivingTime = afterserverReceivingTime;
	}

	public Date getIce2mqTime() {
		return ice2mqTime;
	}

	public void setIce2mqTime(Date ice2mqTime) {
		this.ice2mqTime = ice2mqTime;
	}

	public Date getPreTime() {
		return preTime;
	}

	public void setPreTime(Date preTime) {
		this.preTime = preTime;
	}

	public Date getAfterTime() {
		return afterTime;
	}

	public void setAfterTime(Date afterTime) {
		this.afterTime = afterTime;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Date getUploadEndTime() {
		return uploadEndTime;
	}

	public void setUploadEndTime(Date uploadEndTime) {
		this.uploadEndTime = uploadEndTime;
	}

	public BigDecimal getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(BigDecimal totalTime) {
		this.totalTime = totalTime;
	}

	public String getDeviceKey() {
		return deviceKey;
	}

	public void setDeviceKey(String deviceKey) {
		this.deviceKey = deviceKey;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getRoadCode() {
		return roadCode;
	}

	public void setRoadCode(String roadCode) {
		this.roadCode = roadCode;
	}
}
