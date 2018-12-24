package cy.its.device.domain.model;

import java.util.Date;

public class SysUseStatus {
    private String sysUseStatusId;

    private String deviceId;

    private String originalStatus;

    private String updateStatus;

    private String updateReason;

    private String updateBy;

    private Date updateTime;

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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}