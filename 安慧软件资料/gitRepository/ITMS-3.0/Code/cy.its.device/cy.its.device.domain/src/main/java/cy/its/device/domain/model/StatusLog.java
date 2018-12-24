package cy.its.device.domain.model;

import java.util.Date;

public class StatusLog {
    private String satusLogId;

    private String deviceId;

    private String statusType;

    private Date startTime;

    private Date endTime;

    private Long durationSecs;

    public String getSatusLogId() {
        return satusLogId;
    }

    public void setSatusLogId(String satusLogId) {
        this.satusLogId = satusLogId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getDurationSecs() {
        return durationSecs;
    }

    public void setDurationSecs(Long durationSecs) {
        this.durationSecs = durationSecs;
    }
}