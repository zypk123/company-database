package cy.its.device.rest.dto;

import com.wordnik.swagger.annotations.ApiParam;

import cy.its.com.dto.BaseDto;

public class TollgateQryDto extends BaseDto{
	
	@ApiParam(name="userOrgId",value="当前用户点击的机构ID")
	private String userOrgId;	
	
	@ApiParam(name="siteId",value="当前用户点击的点位ID")
	private String siteId;		
	
	@ApiParam(name="roadId",value="当前用户点击的道路ID")
	private String roadId;	
	
	@ApiParam(name="roadType",value="当前用户点击的道路类型")
	private String roadType;	
	
	@ApiParam(name="tollgateType",value="卡口类型")
	private String tollgateType;
	
	@ApiParam(name="contractorId",value="厂商ID")
	private String contractorId;
	
	@ApiParam(name="useStatusFlagArray",value="使用状态数组（查询条件使用状态可以是几种状态的组合）")
	private String useStatusFlagArray;
	
	@ApiParam(name="orgPrivilageCode",value="机构权限过滤代码")
	private String orgPrivilageCode;
	
	@ApiParam(name="deviceName",value="设备名称")
	private String deviceName;
	
	@ApiParam(name="deviceId",value="设备ID")
	private String deviceId;
	
	@ApiParam(name="updateStartTime",value="查询修改设备使用状态的开始时间")
	private String updateStartTime;
	
	@ApiParam(name="updateEndTime",value="查询修改设备使用状态的结束时间")
	private String updateEndTime;
	/**
	 * get卡口设备类型
	 * 
	 * @return
	 */
	public String getTollgateType() {
		return tollgateType;
	}

	/**
	 * set卡口设备类型
	 * 
	 * @param tollgateType
	 */
	public void setTollgateType(String tollgateType) {
		this.tollgateType = tollgateType;
	}

	/**
	 * get厂商
	 * 
	 * @return
	 */
	public String getContractorId() {
		return contractorId;
	}

	/**
	 * set厂商
	 * 
	 * @param contractorId
	 */
	public void setContractorId(String contractorId) {
		this.contractorId = contractorId;
	}

	/**
	 * get设备使用状态
	 * 
	 * @return
	 */
	public String getUseStatusFlagArray() {
		return useStatusFlagArray;
	}

	/**
	 * set设备使用状态
	 * 
	 * @param useStatusFlagArray
	 */
	public void setUseStatusFlagArray(String useStatusFlagArray) {
		this.useStatusFlagArray = useStatusFlagArray;
	}

	/**
	 * get设备机构权限编码
	 * 
	 * @return
	 */
	public String getOrgPrivalageCode() {
		return orgPrivilageCode;
	}

	/**
	 * set设备机构权限编码
	 * 
	 * @param orgPrivilageCode
	 */
	public void setOrgPrivilageCode(String orgPrivilageCode) {
		this.orgPrivilageCode = orgPrivilageCode;
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
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getUpdateStartTime() {
		return updateStartTime;
	}

	public void setUpdateStartTime(String updateStartTime) {
		this.updateStartTime = updateStartTime;
	}

	public String getUpdateEndTime() {
		return updateEndTime;
	}

	public void setUpdateEndTime(String updateEndTime) {
		this.updateEndTime = updateEndTime;
	}
}
