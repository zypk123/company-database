package cy.its.service.common.dataModel;

import java.util.Date;
import java.util.List;

public class SurveyUpgrade_ComponentStatusResult extends Model {
	/**
	 * 部件id
	 */
	private String sysComponentId;

	/**
	 * 组件设备编号
	 */
	private String deviceNbr;
	
	/**
	 * 组件设备编号
	 */
	private String deviceKey;

	/**
	 * 组件状态更新时间
	 */
	private Date statusUpdateTime;

	/**
	 * 组件状态类型
	 */
	private int statusType;

	/**
	 * 故障码列表
	 */
	private List<String> faultDetails;
	
	/**
	 * 相机当前时间
	 */
	private Date deviceCurTime;
	
	/**
	 * 时间差   单位:秒
	 * 正数表示监控服务器时间比设备(相机)当前时间大(晚)
	 * 负值表示监控服务器时间比设备(相机)当前时间小(早)
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

	public SurveyUpgrade_ComponentStatusResult(String sysComponentId, String deviceNbr, String deviceKey,
			Date statusUpdateTime, int statusType, List<String> faultDetails, Date deviceCurTime, Integer timeDiff, Date lastUploadTime, Date latestDataTime) {
		this.sysComponentId = sysComponentId;
		this.deviceNbr = deviceNbr;
		this.deviceKey = deviceKey;
		this.statusUpdateTime = statusUpdateTime;
		this.statusType = statusType;
		this.faultDetails = faultDetails;
		this.deviceCurTime = deviceCurTime;
		this.timeDiff = timeDiff;
		this.lastUploadTime = lastUploadTime;
		this.latestDataTime = latestDataTime;
	}

	public String getSysComponentId() {
		return sysComponentId;
	}

	public void setSysComponentId(String sysComponentId) {
		this.sysComponentId = sysComponentId;
	}

	public String getDeviceNbr() {
		return deviceNbr;
	}

	public void setDeviceNbr(String deviceNbr) {
		this.deviceNbr = deviceNbr;
	}

	public String getDeviceKey() {
		return deviceKey;
	}

	public void setDeviceKey(String deviceKey) {
		this.deviceKey = deviceKey;
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
