package cy.its.device.domain.model.led;

import java.util.Date;

/**
 * LED发布日志
 * @author STJ
 *
 */
public class LedPublishLog {
	
	private String publishLogId;

    private String deviceSysNbr;

    private String programId;

    private String programNo;

    private Date beginTime;

    private Date endTime;

    private String result;

    private Short failureCode;

    private String failureDesc;

    private String messageType;

    private String publishUser;

    private String orgPrivilegeCode;

    private String taskId;

    private String taskType;

    private String dataIdentity;

    private String publishMethod;

    private String progContent;

    public String getPublishLogId() {
        return publishLogId;
    }

    public void setPublishLogId(String publishLogId) {
        this.publishLogId = publishLogId;
    }

    public String getDeviceSysNbr() {
        return deviceSysNbr;
    }

    public void setDeviceSysNbr(String deviceSysNbr) {
        this.deviceSysNbr = deviceSysNbr;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getProgramNo() {
        return programNo;
    }

    public void setProgramNo(String programNo) {
        this.programNo = programNo;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Short getFailureCode() {
        return failureCode;
    }

    public void setFailureCode(Short failureCode) {
        this.failureCode = failureCode;
    }

    public String getFailureDesc() {
        return failureDesc;
    }

    public void setFailureDesc(String failureDesc) {
        this.failureDesc = failureDesc;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getPublishUser() {
        return publishUser;
    }

    public void setPublishUser(String publishUser) {
        this.publishUser = publishUser;
    }

    public String getOrgPrivilegeCode() {
        return orgPrivilegeCode;
    }

    public void setOrgPrivilegeCode(String orgPrivilegeCode) {
        this.orgPrivilegeCode = orgPrivilegeCode;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getDataIdentity() {
        return dataIdentity;
    }

    public void setDataIdentity(String dataIdentity) {
        this.dataIdentity = dataIdentity;
    }

    public String getPublishMethod() {
        return publishMethod;
    }

    public void setPublishMethod(String publishMethod) {
        this.publishMethod = publishMethod;
    }

    public String getProgContent() {
        return progContent;
    }

    public void setProgContent(String progContent) {
        this.progContent = progContent;
    }
    
}