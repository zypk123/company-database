package cy.its.syscfg.domain.model.sysLog;

import java.util.Date;

import cy.its.com.domain.AggregateRoot;

public class OperationLog extends AggregateRoot {

	/**
	 * 日志id
	 */
	private String opeLogId;

	/**
	 * 系统功能编码
	 */
	private String sysFunctionCode;

	/**
	 * 操作用户
	 */
	private String opeUserName;

	/**
	 * 操作时间
	 */
	private Date operateTime;

	/**
	*
	*/
	private String operateRecordCounts;

	/**
	 * 操作结果：1-成功；2-失败
	 */
	private String operateResult;

	/**
	 * 操作耗时
	 */
	private Double consumeSeconds;

	/**
	 * 操作失败原因
	 */
	private String errorDesc;


	/**
	 * 操作IP
	 */
	private String computerIp;

	/**
	 * 0：不支持； 1：支持，支持的时候在数据库操作内容表中记录数据内容。
	 */
	private String rebackable;

	public OperationLog(){}
	
	public OperationLog(String sysFunctionCode, String opeUserName,
			Date operateTime, String operateRecordCounts, String operateResult,
			Double consumeSeconds, String errorDesc, String computerIp,
			String rebackable) throws Exception {

		this.setOpeUserName(opeUserName);
		this.setOperateTime(operateTime);
		this.setOperateRecordCounts(operateRecordCounts);
		this.setOperateResult(operateResult);
		this.setConsumeSeconds(consumeSeconds);
		this.setRebackable(rebackable);

		this.errorDesc = errorDesc;
		this.computerIp = computerIp;
		this.sysFunctionCode = sysFunctionCode;
	}

	public OperationLog(String opeLogId, String sysFunctionCode,
			String opeUserName, Date operateTime, String operateRecordCounts,
			String operateResult,Double consumeSeconds, String errorDesc,
			String computerIp, String rebackable) throws Exception {
		this(sysFunctionCode, opeUserName, operateTime, 
				operateRecordCounts, operateResult, consumeSeconds, errorDesc, computerIp, rebackable);
		this.setOpeLogId(opeLogId);
	}

	public void setOpeLogId(String opeLogId) throws Exception {
		NotNull(opeLogId, "日志id");
		this.opeLogId = opeLogId;
	}

	private void setOpeUserName(String opeUserName) throws Exception {
//		NotNull(opeUserName, "操作用户");
		this.opeUserName = opeUserName;
	}

	public String getOpeUserName() {
		return opeUserName;
	}

	private void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	private void setOperateRecordCounts(String operateRecordCounts)
			throws Exception {
//		NotNull(operateRecordCounts, "");
		this.operateRecordCounts = operateRecordCounts;
	}

	public String getOperateRecordCounts() {
		return operateRecordCounts;
	}

	private void setOperateResult(String operateResult) throws Exception {
		NotNull(operateResult, "操作结果");
		this.operateResult = operateResult;
	}

	public String getOperateResult() {
		return operateResult;
	}

	private void setConsumeSeconds(Double consumeSeconds) throws Exception {
//		NotLessThanZero(consumeSeconds, "操作耗时");
		this.consumeSeconds = consumeSeconds;
	}

	public Double getConsumeSeconds() {
		return consumeSeconds;
	}

	private void setRebackable(String rebackable) throws Exception {
//		NotNull(rebackable, "");
		this.rebackable = rebackable;
	}

	public String getRebackable() {
		return rebackable;
	}

	@Override
	public String getIdentityId() {
		return opeLogId;
	}


	public String getSysFunctionCode() {
		return sysFunctionCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public String getComputerIp() {
		return computerIp;
	}
}
