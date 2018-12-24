package cy.its.syscfg.mybatis.model;

import java.util.Date;

public class T_Sys_Operation_Log {
    private String opeLogId;

    private String sysFunctionCode;

    private String opeUserName;

	private Date operateTime;

    private String operateRecordCounts;

    private String operateResult;

    private Double consumeSeconds;

    private String errorDesc;

    private String computerIp;

    private String rebackable;

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
    
    public String getOpeUserName() {
		return opeUserName;
	}

	public void setOpeUserName(String opeUserName) {
		this.opeUserName = opeUserName;
	}
    
}