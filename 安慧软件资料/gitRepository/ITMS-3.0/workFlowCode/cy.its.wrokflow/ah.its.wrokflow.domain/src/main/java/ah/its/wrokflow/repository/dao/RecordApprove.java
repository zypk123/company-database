package ah.its.wrokflow.repository.dao;

public class RecordApprove {
    private String recordApproveId;

    private String deviceApproveId;

    private String processId;

    private String monitorImg;

    private String limitImg;

    private String warnImg;

    private String acceptanceImg;

    private String appraisalImg;

    private String certificateImg;

    private String disabled;

    public String getRecordApproveId() {
        return recordApproveId;
    }

    public void setRecordApproveId(String recordApproveId) {
        this.recordApproveId = recordApproveId;
    }

    public String getDeviceApproveId() {
        return deviceApproveId;
    }

    public void setDeviceApproveId(String deviceApproveId) {
        this.deviceApproveId = deviceApproveId;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getMonitorImg() {
        return monitorImg;
    }

    public void setMonitorImg(String monitorImg) {
        this.monitorImg = monitorImg;
    }

    public String getLimitImg() {
        return limitImg;
    }

    public void setLimitImg(String limitImg) {
        this.limitImg = limitImg;
    }

    public String getWarnImg() {
        return warnImg;
    }

    public void setWarnImg(String warnImg) {
        this.warnImg = warnImg;
    }

    public String getAcceptanceImg() {
        return acceptanceImg;
    }

    public void setAcceptanceImg(String acceptanceImg) {
        this.acceptanceImg = acceptanceImg;
    }

    public String getAppraisalImg() {
        return appraisalImg;
    }

    public void setAppraisalImg(String appraisalImg) {
        this.appraisalImg = appraisalImg;
    }

    public String getCertificateImg() {
        return certificateImg;
    }

    public void setCertificateImg(String certificateImg) {
        this.certificateImg = certificateImg;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }
}