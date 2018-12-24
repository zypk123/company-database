package cy.its.sysLog.domain;

import java.util.Date;

public class SyOperationLog {
	
    private String opeLogId;

    private String sysFunctionCode;

    private String opeUserId;

    private Date operateTime;

    private String operateRecordCounts;

    private String operateResult;

    private Double consumeSeconds;

    private String errorDesc;

    private String computerIp;

    private String rebackable;

    private Date returnResultTime;

    private String serviceUrl;
    
    private String orgPrivilegeCode;

    public String getOpeLogId() {
        return opeLogId;
    }

    public void setOpeLogId(String opeLogId) {
        this.opeLogId = opeLogId;
    }

    public String getSysFunctionCode() {
        return sysFunctionCode;
    }

    public void setSysFunctionCode(String sysFunctionCode) {
        this.sysFunctionCode = sysFunctionCode;
    }


    public String getOpeUserId() {
		return opeUserId;
	}

	public void setOpeUserId(String opeUserId) {
		this.opeUserId = opeUserId;
	}

	public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperateRecordCounts() {
        return operateRecordCounts;
    }

    public void setOperateRecordCounts(String operateRecordCounts) {
        this.operateRecordCounts = operateRecordCounts;
    }

    public String getOperateResult() {
        return operateResult;
    }

    public void setOperateResult(String operateResult) {
        this.operateResult = operateResult;
    }

    public Double getConsumeSeconds() {
        return consumeSeconds;
    }

    public void setConsumeSeconds(Double consumeSeconds) {
        this.consumeSeconds = consumeSeconds;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getComputerIp() {
        return computerIp;
    }

    public void setComputerIp(String computerIp) {
        this.computerIp = computerIp;
    }

    public String getRebackable() {
        return rebackable;
    }

    public void setRebackable(String rebackable) {
        this.rebackable = rebackable;
    }

    public Date getReturnResultTime() {
        return returnResultTime;
    }

    public void setReturnResultTime(Date returnResultTime) {
        this.returnResultTime = returnResultTime;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}
    
}