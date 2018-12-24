package cy.its.device.rest.dto;

import cy.its.com.dto.BaseDto;

public class LedTaskDto extends BaseDto {
	//发布任务ID 流水号
	public String taskId;
	//数据来源设备ID
	public String deviceId;
	//任务名称
	public String taskName;
	//创建人
	public String creator;
	//创建时间
	public String createTime;
	//审核人
	public String approver;
	//审核时间
	public String approveTime;
	//任务执行计数+
	public String issueCount;
	//设备名称
	public String deviceName;
	//任务执行时间 	
	public String timeString;

	/**
	 * 任务类型 取值为: 
	 * 10 日常任务 11 定时任务 12 插播任务 13 紧急任务;
	 * 20 违法自动发布;21 流量自动发布; 22 气象自动发布; 23 限速自动发布;
	 */
	public String taskType;
	
	// 信息类型、
	public String messageType;
	
	// 执行状态
	public String issueStatus;

	// 执行状态
	public String issueStatusArrs;

	// 任务标识/流水号
	public String taskIdArr;

	// 节目编号列表
	public String programNoArr;
	
	//启用标记
	public String enableFlag;
	//数据来源区间ID
	public String regionalId;
//	自定义过滤条件 使用预定义的字段名、运算符拼装的表达式;
//	 字段名：       在动态字段配置表中定义;
//	 常用运算符： &&:并且; ||:或者; ==:等于; >:大于; >=大于等于; <:小于; <=:小于等于;
//	 表达式示例1：
//	     direction == "玉溪方向" && speed > 100
//	     含义：符合 方向为玉溪方向并且速度大于100 的数据可以用于发布;direction 和 speed 为预定义属性名;
	public String taskFilter;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(String approveTime) {
		this.approveTime = approveTime;
	}

	public String getIssueCount() {
		return issueCount;
	}

	public void setIssueCount(String issueCount) {
		this.issueCount = issueCount;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getTimeString() {
		return timeString;
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
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

	public String getIssueStatusArrs() {
		return issueStatusArrs;
	}

	public void setIssueStatusArrs(String issueStatusArrs) {
		this.issueStatusArrs = issueStatusArrs;
	}

	public String getTaskIdArr() {
		return taskIdArr;
	}

	public void setTaskIdArr(String taskIdArr) {
		this.taskIdArr = taskIdArr;
	}

	public String getProgramNoArr() {
		return programNoArr;
	}

	public void setProgramNoArr(String programNoArr) {
		this.programNoArr = programNoArr;
	}

	public String getEnableFlag() {
		return enableFlag;
	}

	public void setEnableFlag(String enableFlag) {
		this.enableFlag = enableFlag;
	}

	public String getRegionalId() {
		return regionalId;
	}

	public void setRegionalId(String regionalId) {
		this.regionalId = regionalId;
	}

	public String getTaskFilter() {
		return taskFilter;
	}

	public void setTaskFilter(String taskFilter) {
		this.taskFilter = taskFilter;
	}
	
}
