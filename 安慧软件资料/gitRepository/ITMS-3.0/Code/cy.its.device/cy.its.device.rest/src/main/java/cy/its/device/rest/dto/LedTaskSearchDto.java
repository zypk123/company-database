package cy.its.device.rest.dto;

import cy.its.com.dto.BaseDto;

/**
 * @Title: LedTaskSearchDto.java
 * @Package cy.its.device.rest.dto
 * @Description: 前台条件包装类
 * @author liug@cychina.cn
 * @date  2016年6月29日 上午11:41:20
 * @version V1.0
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2016 
 */

public class LedTaskSearchDto extends BaseDto {
	
	//任务id
	private String taskId;
	
	// 机构、
	private String orgPrivilegeCode;

	// 道路、
	private String roadId;

	// 设备、
	private String deviceId;

	//任务类型
	private String taskType;
	
	// 信息类型、
	private String messageType;

	// 创建时间范围、
	private String createTimeFrom;

	// 创建时间范围、
	private String createTimeTo;

	// 执行状态
	private String issueStatusArrs;


	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
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

	public String getCreateTimeFrom() {
		return createTimeFrom;
	}

	public void setCreateTimeFrom(String createTimeFrom) {
		this.createTimeFrom = createTimeFrom;
	}

	public String getCreateTimeTo() {
		return createTimeTo;
	}

	public void setCreateTimeTo(String createTimeTo) {
		this.createTimeTo = createTimeTo;
	}

	public String getIssueStatusArrs() {
		return issueStatusArrs;
	}

	public void setIssueStatusArrs(String issueStatusArrs) {
		this.issueStatusArrs = issueStatusArrs;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}	
}
