package cy.its.device.rest.dto;


import cy.its.com.dto.BaseDto;
/**
 *
 * @Title: InfoReleaseManagerDto.java
 * @Package cy.its.device.rest.dto
 * @Description: 发布记录管理页面属性包装类
 * @author liug@cychina.cn
 * @date  2016年6月20日 上午10:12:22
 * @version V1.0
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2016
 */
 public class ReleaseTaskManagerDto extends BaseDto {
	 /**
	  * 放置所有表主键，供控制层增删改使用
	  */
	 private String specId;//T_DEVICE_LED_SPEC
	 
	 private String deviceId;//T_DEVICE_LED
	 private String programId;//T_DEVICE_LED_PROG
	 private String autoTaskId;//T_DEVICE_LED_TASK_AUTO
	 private String fieldId;//T_DEVICE_LED_FIELD
	 private String progTemplateId;//T_DEVICE_LED_PROG_TEMPLATE
	 private String mediaId;//T_DEVICE_LED_MEDIA
	 private String publishLogId;//T_DEVICE_LED_PUBLISH_LOG
	 private String textId;//T_DEVICE_LED_TEXT
	 private String contentId;//T_DEVICE_LED_CONTENT_LIB
	 
	 
	 
	 public String getSpecId() {
		return specId;
	}
	public void setSpecId(String specId) {
		this.specId = specId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getAutoTaskId() {
		return autoTaskId;
	}
	public void setAutoTaskId(String autoTaskId) {
		this.autoTaskId = autoTaskId;
	}
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	public String getProgTemplateId() {
		return progTemplateId;
	}
	public void setProgTemplateId(String progTemplateId) {
		this.progTemplateId = progTemplateId;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getPublishLogId() {
		return publishLogId;
	}
	public void setPublishLogId(String publishLogId) {
		this.publishLogId = publishLogId;
	}
	public String getTextId() {
		return textId;
	}
	public void setTextId(String textId) {
		this.textId = textId;
	}
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	//TODO 注释部分未处理！！！！！！！！
	 //private String ledShape;//所属机构
	 //private String ledShape;//所属道路
	 private String ledShape;//诱导屏设备
	 private String taskType;//任务类型
	 private String messageType;//消息类型
	 private String issueStatus;//执行状态
	 private String manualTaskId;//任务流水号
	 private String textXml;//文本内容
	// private String ledShape;//执行开始时间
	// private String ledShape;//执行结束时间
	 private String createTime;//创建时间 
	public String getLedShape() {
		return ledShape;
	}
	public void setLedShape(String ledShape) {
		this.ledShape = ledShape;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getIssueStatus() {
		return issueStatus;
	}
	public void setIssueStatus(String issueStatus) {
		this.issueStatus = issueStatus;
	}
	public String getManualTaskId() {
		return manualTaskId;
	}
	public void setManualTaskId(String manualTaskId) {
		this.manualTaskId = manualTaskId;
	}
	public String getTextXml() {
		return textXml;
	}
	public void setTextXml(String textXml) {
		this.textXml = textXml;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
 }
