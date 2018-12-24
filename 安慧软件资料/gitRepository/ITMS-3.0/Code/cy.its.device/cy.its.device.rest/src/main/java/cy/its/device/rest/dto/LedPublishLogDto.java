package cy.its.device.rest.dto;

import cy.its.com.dto.BaseDto;
/**
 * 
 * @Title: LedPublishLogDto.java
 * @Package cy.its.device.rest.dto
 * @Description: led发布日志前台页面包装类
 * @author liug@cychina.cn
 * @date  2016年6月24日 上午9:46:44
 * @version V1.0
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2016
 */
public class LedPublishLogDto extends BaseDto {
	
	
	private String publishLogId;

    private String deviceSysNbr;

    private String programNo;

    private String beginTime;

    private String endTime;

    private String result;

    private String failureCode;

    private String failureDesc;


    private String publishUser;

    
 // 机构、
 	public String orgPrivilegeCode;
 	
 	// 道路、
 	public String roadId;
 	
 	// 设备、
 	public String deviceId;
 	
 	// 发布方式
 	public String[] publishMethodS;

 	// 信息类型、
 	public String messageType;

 	// 下发时间范围、
 	public String publishTimeFrom;

 	// 下发时间范围、
 	public String publishTimeTo;

 	// 节目
 	public String programId;

 	// 任务类型
 	public String taskType;
 	
 	// 发布结果
 	public String[] resultArrS;
 	
 	// 任务ID
 	public String taskId;

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

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getFailureCode() {
		return failureCode;
	}

	public void setFailureCode(String failureCode) {
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
    public String getRoadId() {
		return roadId;
	}

	public void setRoadId(String roadId) {
		this.roadId = roadId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public String[] getPublishMethodS() {
		return publishMethodS;
	}

	public void setPublishMethodS(String[] publishMethodS) {
		this.publishMethodS = publishMethodS;
	}

	public String[] getResultArrS() {
		return resultArrS;
	}

	public void setResultArrS(String[] resultArrS) {
		this.resultArrS = resultArrS;
	}

	public String getPublishTimeFrom() {
		return publishTimeFrom;
	}

	public void setPublishTimeFrom(String publishTimeFrom) {
		this.publishTimeFrom = publishTimeFrom;
	}

	public String getPublishTimeTo() {
		return publishTimeTo;
	}

	public void setPublishTimeTo(String publishTimeTo) {
		this.publishTimeTo = publishTimeTo;
	}

	
	private String dataIdentity;

    private String publishMethod;
	
}
