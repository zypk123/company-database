package cy.its.device.domain.model;

import java.util.Date;

public class FaultLog {
    private String faultEventId;

    private String faultId;

    private String deviceNbr;

    private Date faultEventTime;

    private String faultAlarm;

    private String faultType;

    private String faultSubType;

    private String collectMethod;

    private String remark;

    public String getFaultEventId() {
        return faultEventId;
    }

    public void setFaultEventId(String faultEventId) {
        this.faultEventId = faultEventId;
    }

    public String getFaultId() {
        return faultId;
    }

    public void setFaultId(String faultId) {
        this.faultId = faultId;
    }

    public String getDeviceNbr() {
        return deviceNbr;
    }

    public void setDeviceNbr(String deviceNbr) {
        this.deviceNbr = deviceNbr;
    }

    public Date getFaultEventTime() {
        return faultEventTime;
    }

    public void setFaultEventTime(Date faultEventTime) {
        this.faultEventTime = faultEventTime;
    }

    public String getFaultAlarm() {
        return faultAlarm;
    }

    public void setFaultAlarm(String faultAlarm) {
        this.faultAlarm = faultAlarm;
    }

    public String getFaultType() {
        return faultType;
    }

    public void setFaultType(String faultType) {
        this.faultType = faultType;
    }

    public String getFaultSubType() {
        return faultSubType;
    }

    public void setFaultSubType(String faultSubType) {
        this.faultSubType = faultSubType;
    }

    public String getCollectMethod() {
        return collectMethod;
    }

    public void setCollectMethod(String collectMethod) {
        this.collectMethod = collectMethod;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}