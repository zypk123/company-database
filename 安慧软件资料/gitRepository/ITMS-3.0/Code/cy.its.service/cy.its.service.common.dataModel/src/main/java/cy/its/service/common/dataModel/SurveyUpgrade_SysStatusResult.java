package cy.its.service.common.dataModel;

import java.util.Date;
import java.util.List;

public class SurveyUpgrade_SysStatusResult extends Model {	

	/**
	 * 系统ID
	 */
	private String deviceId;

	/**
	 * 系统编号
	 */
	private String deviceSysNbr;

//	/**
//	 * 系统编号
//	 */
//	private String deviceKey;
	
	/**
	 * 设备类型
	 */
	private String deviceType;

	/**
	 * 系统所属机构权限代码
	 */
	private String orgPrivilegeCode;
	
	/**
	 * 点位代码
	 */
	private String siteCode;
	
	/**
	 * 状态更新时间
	 */
	private Date statusUpdateTime;

	/**
	 * 状态类型
	 */
	private int statusType;

	/**
	 * 详细故障码列表 注:当系统为工控类 无组件系统时, 且设备为无故障时本列表有值;
	 */
	private List<String> faultDetails;

	/**
	 * 部件状态列表 注: 当系统为工控类 无组件系统时, 本列表空;
	 */
	private List<SurveyUpgrade_ComponentStatusResult> componentStatusResults;
	
	/**
	 * 设备当前时间
	 * 注: 当系统包含组件时, 本值为时间差绝对值最大的那个组件的设备当前时间
	 */
	private Date deviceCurTime;
	
	/**
	 * 时间差   单位:秒
	 * 正数表示监控服务器时间比设备(相机)当前时间大(晚)
	 * 负值表示监控服务器时间比设备(相机)当前时间小(早)
	 * 注: 当系统包含组件时, 本值为时间差绝对值最大的那个组件的差值
	 */
	private Integer timeDiff;
	
	/**
	 * 最近数据上传时间
	 */
	private Date lastUploadTime;
	
	/**
	 * 最近设备产生的最新数据时间
	 */
	private Date latestDataTime;
	
	public SurveyUpgrade_SysStatusResult() {
	}

	public SurveyUpgrade_SysStatusResult(String deviceId, String deviceSysNbr, String deviceType,
			String orgPrivilegeCode, String siteCode, Date statusUpdateTime, int statusType, List<String> faultDetails,
			List<SurveyUpgrade_ComponentStatusResult> componentStatusResults, Date deviceCurTime, Integer timeDiff, Date lastUploadTime, Date latestDataTime) {
		super();
		this.deviceId = deviceId;
		this.deviceSysNbr = deviceSysNbr;
//		this.deviceKey = deviceKey;
		this.deviceType = deviceType;
		this.orgPrivilegeCode = orgPrivilegeCode;
		this.siteCode = siteCode;
		this.statusUpdateTime = statusUpdateTime;
		this.statusType = statusType;
		this.faultDetails = faultDetails;
		this.componentStatusResults = componentStatusResults;
		this.deviceCurTime = deviceCurTime;
		this.timeDiff = timeDiff;
		this.lastUploadTime = lastUploadTime;
		this.latestDataTime = latestDataTime;
	}

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

//	public String getDeviceKey() {
//		return deviceKey;
//	}
//
//	public void setDeviceKey(String deviceKey) {
//		this.deviceKey = deviceKey;
//	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public Date getStatusUpdateTime() {
		return statusUpdateTime;
	}

	public void setStatusUpdateTime(Date statusUpdateTime) {
		this.statusUpdateTime = statusUpdateTime;
	}

	public int getStatusType() {
		return statusType;
	}

	public void setStatusType(int statusType) {
		this.statusType = statusType;
	}

	public List<String> getFaultDetails() {
		return faultDetails;
	}

	public void setFaultDetails(List<String> faultDetails) {
		this.faultDetails = faultDetails;
	}

	public List<SurveyUpgrade_ComponentStatusResult> getComponentStatusResults() {
		return componentStatusResults;
	}

	public void setComponentStatusResults(List<SurveyUpgrade_ComponentStatusResult> componentStatusResults) {
		this.componentStatusResults = componentStatusResults;
	}
	
	public Date getDeviceCurTime() {
		return deviceCurTime;
	}

	public void setDeviceCurTime(Date deviceCurTime) {
		this.deviceCurTime = deviceCurTime;
	}

	public Integer getTimeDiff() {
		return timeDiff;
	}

	public void setTimeDiff(Integer timeDiff) {
		this.timeDiff = timeDiff;
	}

	public Date getLastUploadTime() {
		return lastUploadTime;
	}

	public void setLastUploadTime(Date lastUploadTime) {
		this.lastUploadTime = lastUploadTime;
	}

	public Date getLatestDataTime() {
		return latestDataTime;
	}

	public void setLatestDataTime(Date latestDataTime) {
		this.latestDataTime = latestDataTime;
	}
}
