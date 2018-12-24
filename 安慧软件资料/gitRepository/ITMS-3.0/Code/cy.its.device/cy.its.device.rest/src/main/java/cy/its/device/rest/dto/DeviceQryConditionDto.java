package cy.its.device.rest.dto;

import cy.its.com.dto.BaseDto;

/**
 * 设备查询条件form
 * 
 * @author chenzhiying 2015.09.21
 *
 */
public class DeviceQryConditionDto extends BaseDto{

	private String userOrgId;	//当前用户点击的机构ID
	private String siteId;		//当前用户点击的点位ID
	private String roadId;		//当前用户点击的道路ID
	private String roadType;	//当前用户点击的道路类型
	private String deviceType;
	private String onlineStatus;
	private String useStatus;
	private String deviceName;

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
	 * get设备在线状态
	 * 
	 * @return
	 */
	public String getOnlineStatus() {
		return onlineStatus;
	}

	/**
	 * set设备在线状态
	 * 
	 * @param onlineStauts
	 */
	public void setOnlineStatus(String onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	/**
	 * get设备使用状态
	 * 
	 * @return
	 */
	public String getUseStatus() {
		return useStatus;
	}

	/**
	 * set设备使用状态
	 * 
	 * @param useStatus
	 */
	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
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

	public String getUserOrgId() {
		return userOrgId;
	}

	public void setUserOrgId(String userOrgId) {
		this.userOrgId = userOrgId;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getRoadId() {
		return roadId;
	}

	public void setRoadId(String roadId) {
		this.roadId = roadId;
	}

	public String getRoadType() {
		return roadType;
	}

	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}
	
	
}
