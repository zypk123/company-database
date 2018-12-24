/**
 * @Title: opterationLogDto.java
 * @Package cy.its.sysCfg.rest.dto
 * @Description: TODO(这里要填写用途)
 * @author wangyf wangyf@cychina.cn
 * @date 2015年12月3日 上午9:12:05
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.sysCfg.rest.dto;


import cy.its.com.dto.BaseDto;

/**
  * @ClassName: opterationLogDto
  * @Description: TODO(这里要填写用途)
  * @author wangyf wangyf@cychina.cn
  * @date 2015年12月3日 上午9:12:05
  *
  */

public class OperationLogDto extends BaseDto {
	/**
	 * 日志id
	 */
	private String opeLogId;

	/**
	 * 系统功能编码
	 */
	private String functionCode;

	private String sysFunctionCode;
	/**
	 * 操作用户
	 */
	private String opeUserName;
	
	private String userId;
	
	//操作时间
	private String operateTime;
	
	/**
	 * 开始操作时间
	 */
	private	String operateBeginTime;
	/**
	 * 结束操作时间
	 */
	private	String operateEndTime;

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
	private String consumeSeconds;

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

	public String getOpeLogId() {
		return opeLogId;
	}

	public void setOpeLogId(String opeLogId) {
		this.opeLogId = opeLogId;
	}
	public String getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	public String getSysFunctionCode() {
		return sysFunctionCode;
	}

	public void setSysFunctionCode(String sysFunctionCode) {
		this.sysFunctionCode = sysFunctionCode;
	}

	public String getOpeUserName() {
		return opeUserName;
	}

	public void setOpeUserName(String opeUserName) {
		this.opeUserName = opeUserName;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperateBeginTime() {
		return operateBeginTime;
	}

	public void setOperateBeginTime(String operateBeginTime) {
		this.operateBeginTime = operateBeginTime;
	}

	public String getOperateEndTime() {
		return operateEndTime;
	}

	public void setOperateEndTime(String operateEndTime) {
		this.operateEndTime = operateEndTime;
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

	public String getConsumeSeconds() {
		return consumeSeconds;
	}

	public void setConsumeSeconds(String consumeSeconds) {
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
	
	
}
