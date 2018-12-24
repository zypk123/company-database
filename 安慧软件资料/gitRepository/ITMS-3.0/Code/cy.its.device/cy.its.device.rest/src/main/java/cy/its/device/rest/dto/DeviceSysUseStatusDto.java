package cy.its.device.rest.dto;

import cy.its.com.dto.BaseDto;

public class DeviceSysUseStatusDto extends BaseDto{

	    private String sysUseStatusId;

	    private String deviceId;

	    private String originalStatus;

	    private String updateStatus;

	    private String updateReason;

	    private String updateBy;

	    private String updateTime;

	    public String getSysUseStatusId() {
	        return sysUseStatusId;
	    }

	    public void setSysUseStatusId(String sysUseStatusId) {
	        this.sysUseStatusId = sysUseStatusId;
	    }

	    public String getDeviceId() {
	        return deviceId;
	    }

	    public void setDeviceId(String deviceId) {
	        this.deviceId = deviceId;
	    }

	    public String getOriginalStatus() {
	        return originalStatus;
	    }

	    public void setOriginalStatus(String originalStatus) {
	        this.originalStatus = originalStatus;
	    }

	    public String getUpdateStatus() {
	        return updateStatus;
	    }

	    public void setUpdateStatus(String updateStatus) {
	        this.updateStatus = updateStatus;
	    }

	    public String getUpdateReason() {
	        return updateReason;
	    }

	    public void setUpdateReason(String updateReason) {
	        this.updateReason = updateReason;
	    }

	    public String getUpdateBy() {
	        return updateBy;
	    }

	    public void setUpdateBy(String updateBy) {
	        this.updateBy = updateBy;
	    }

	    public String getUpdateTime() {
	        return updateTime;
	    }

	    public void setUpdateTime(String updateTime) {
	        this.updateTime = updateTime;
	    }
	}
