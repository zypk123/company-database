package cy.its.trafficSituation.domain.model;

import java.util.Date;

public class TrafficEventImage {
    private String eventImageId;

    private String alarmEventId;

    private String imageUrl;

    private String recordId;

    private String videoCode;

    private Date startTime;

    private Date endTime;

    private String remark;

    public String getEventImageId() {
        return eventImageId;
    }

    public void setEventImageId(String eventImageId) {
        this.eventImageId = eventImageId;
    }

    public String getAlarmEventId() {
        return alarmEventId;
    }

    public void setAlarmEventId(String alarmEventId) {
        this.alarmEventId = alarmEventId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getVideoCode() {
        return videoCode;
    }

    public void setVideoCode(String videoCode) {
        this.videoCode = videoCode;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}