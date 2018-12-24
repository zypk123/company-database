package cy.its.device.rest.dto;


import java.util.Date;

import cy.its.com.dto.BaseDto;

/**
 * 设备查询列表DTO
 * 
 * @author chenzhiying 2015.09.21
 *
 */
public class DeviceConfigQryDto extends BaseDto {

	private String deviceId;
	private String deviceSysNbr;
	private String deviceName;
	private String deviceType;
	private String orgId;
	private String orgName;
	private String deviceIp;
	private String roadId;
	private String roadName;
	private String siteId;
	private String siteName;
	private String mountingFacilityType;
	private String useStatusFlag;
	private String expireDate;  //证书有效期止
	private String onlineStatus;
	private String latestHeartTime;
	private String bug;
	private int snapTotal;
	private int backlog;
	private String latestUploadTime;
	private String correctionTime;
	private String imgUrl;			//实景图片地址
	
	private String statusType;		//设备状态
	private String timeDiff;		//校时时间
	
	
	private String recordType;	//记录类型，1表示电子监控设备，0表示装备
	

	private String userOrgId; 

	private String remark; 
	

	/**
	 * get设备ID
	 * 
	 * @return
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * set设备Id
	 * 
	 * @param deviceId
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * get设备编号
	 * 
	 * @return
	 */
	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}

	/**
	 * set设备编号
	 * 
	 * @param deviceSysNbr
	 */
	public void setDeviceSysNbr(String deviceSysNbr) {
		this.deviceSysNbr = deviceSysNbr;
	}

	/**
	 * get设备名称
	 * 
	 * @return
	 */
	public String getDeviceName() {
		return deviceName;
	}

	/**
	 * set设备名称
	 * 
	 * @param deviceName
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	/**
	 * get设备类型
	 * 
	 * @return
	 */
	public String getDeviceType() {
		return deviceType;
	}

	/**
	 * set设备类型
	 * 
	 * @param deviceType
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	/**
	 * get机构ID
	 * 
	 * @return
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * set机构ID
	 * 
	 * @param orgId
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * get机构名称
	 * 
	 * @return
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * set机构名称
	 * 
	 * @param orgName
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * get设备IP
	 * 
	 * @return
	 */
	public String getDeviceIp() {
		return deviceIp;
	}

	/**
	 * set设备IP
	 * 
	 * @param deviceIp
	 */
	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	/**
	 * get道路ID
	 * 
	 * @return
	 */
	public String getRoadId() {
		return roadId;
	}

	/**
	 * set道路ID
	 * 
	 * @param roadId
	 */
	public void setRoadId(String roadId) {
		this.roadId = roadId;
	}

	/**
	 * get道路名称
	 * 
	 * @return
	 */
	public String getRoadName() {
		return roadName;
	}

	/**
	 * set道路名称
	 * 
	 * @param roadName
	 */
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	/**
	 * get点位ID
	 * 
	 * @return
	 */
	public String getSiteId() {
		return siteId;
	}

	/**
	 * set点位ID
	 * 
	 * @param siteId
	 */
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	/**
	 * get点位名称
	 * 
	 * @return
	 */
	public String getSiteName() {
		return siteName;
	}

	/**
	 * set点位名称
	 * 
	 * @param siteName
	 */
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	/**
	 * get安装方式
	 * 
	 * @return
	 */
	public String getMountingFacilityType() {
		return mountingFacilityType;
	}

	/**
	 * set安装方式
	 * 
	 * @param mountType
	 */
	public void setMountingFacilityType(String mountingFacilityType) {
		this.mountingFacilityType = mountingFacilityType;
	}

	/**
	 * get使用状态
	 * 
	 * @return
	 */
	public String getUseStatusFlag() {
		return useStatusFlag;
	}

	/**
	 * set使用状态
	 * 
	 * @param useStatus
	 */
	public void setUseStatusFlag(String useStatusFlag) {
		this.useStatusFlag = useStatusFlag;
	}

	/**
	 * get年检时间
	 * 
	 * @return
	 */
	public String getExpireDate() {
		return expireDate;
	}

	/**
	 * set年检时间
	 * 
	 * @param expireDate
	 */
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	/**
	 * get在线状态
	 * 
	 * @return
	 */
	public String getOnlineStatus() {
		return onlineStatus;
	}

	/**
	 * set在线状态
	 * 
	 * @param onlineStatus
	 */
	public void setOnlineStatus(String onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	/**
	 * get最后心跳时间
	 * 
	 * @return
	 */
	public String getLatestHeartTime() {
		return latestHeartTime;
	}

	/**
	 * set最后心跳时间
	 * 
	 * @param latestHeartTime
	 */
	public void setLatestHeartTime(String latestHeartTime) {
		this.latestHeartTime = latestHeartTime;
	}

	/**
	 * get故障情况
	 * 
	 * @return
	 */
	public String getBug() {
		return bug;
	}

	/**
	 * set故障情况
	 * 
	 * @param bug
	 */
	public void setBug(String bug) {
		this.bug = bug;
	}

	/**
	 * get抓拍总数
	 * 
	 * @return
	 */
	public int getSnapTotal() {
		return snapTotal;
	}

	/**
	 * set抓拍总数
	 * 
	 * @param snapTotal
	 */
	public void setSnapTotal(int snapTotal) {
		this.snapTotal = snapTotal;
	}

	/**
	 * get积压数量
	 * 
	 * @return
	 */
	public int getBacklog() {
		return backlog;
	}

	/**
	 * set积压数量
	 * 
	 * @param backlog
	 */
	public void setBacklog(int backlog) {
		this.backlog = backlog;
	}

	/**
	 * get最后数据上传时间
	 * 
	 * @return
	 */
	public String getLatestUploadTime() {
		return latestUploadTime;
	}

	/**
	 * set最后数据上传时间
	 * 
	 * @param latestUploadTime
	 */
	public void setLatestUploadTime(String latestUploadTime) {
		this.latestUploadTime = latestUploadTime;
	}

	/**
	 * get最后校时时间
	 * 
	 * @return
	 */
	public String getCorrectionTime() {
		return correctionTime;
	}

	/**
	 * set最后校时时间
	 * 
	 * @param correctionTime
	 */
	public void setCorrectionTime(String correctionTime) {
		this.correctionTime = correctionTime;
	}
	
	
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getUserOrgId() {
		return userOrgId;
	}

	public void setUserOrgId(String userOrgId) {
		this.userOrgId = userOrgId;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public String getTimeDiff() {
		return timeDiff;
	}

	public void setTimeDiff(String timeDiff) {
		this.timeDiff = timeDiff;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
