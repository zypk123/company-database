package cy.its.device.rest.dto;


import cy.its.com.dto.BaseDto;
/**
 * 
 * @Title: LedTaskRelaseSearchDto.java
 * @Package cy.its.device.rest.dto
 * @Description: 信息发布前台包装类
 * @author liug@cychina.cn
 * @date  2016年6月29日 下午12:43:58
 * @version V1.0
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2016
 */
public class LedTaskRelaseSearchDto extends BaseDto {

	private String specId;

	private String templateName;

	private String templateDesc;
	
	private String taskType;
	//任务下发状态: 0 未下发; 1 下发成功; 2 下发失败
	private String issueStatus;
	//审核时间
	private String approveTime;
	//审核时间
	private String createTime;
	//任务执行计数
	private String issueCount;
	//数据来源区间ID
	private String regionalId;
	/**
	 * 自定义过滤条件 使用预定义的字段名、运算符拼装的表达式;
 字段名：       在动态字段配置表中定义;
 常用运算符： &&:并且; ||:或者; ==:等于; >:大于; >=大于等于; <:小于; <=:小于等于;
 表达式示例1：
     direction == "玉溪方向" && speed > 100
     含义：符合 方向为玉溪方向并且速度大于100 的数据可以用于发布;direction 和 speed 为预定义属性名;
	 */
	private String taskFilter;
	//启用标记 0:停用; 1:启用;
	private String enableFlag;
	
	
	private String programId;

	/**
	 * 节目编号
	 */
	private String programNo;

	/**
	 * 节目名称
	 */
	private String perName;

	/**
	 * 显示模式  0:非排他显示;1:排他显示;
	 */
	private String showMode;

	/**
	 * 节目优先级  0:通知;1:警告;2:关键的;3:紧急的;4:计数作用;
	 */
	private String programPriority;

	/**
	 * 信息类型  1 宣传文字、 2 违法警示、3 交通管制、4 交通路况、5 交通事故、6 交通法规、7 安全提示、8 气象信息
	 */
	private String messageType;

	//节目内容（文字 图片）
	private String programContent;
	
	//任务主键
	private String taskId;
	//播放时长
	private String playTime;
	//播放次数
	private String playTimes;
	//播放延迟
	private String playDelay;
	
	// 开始日期-年/月/日
	private String startDate;

	//结束日期-年/月/日
	private String endDate;

	//开始时间: 时/分/秒
	private String startTime;

	//结束时间-时/分/秒
	private String endTime;

	/**
	 * 星期参数: 周一~周日, 0-不播放,1-播放 如:1,1,1,1,1,1,1 表示周一至周日都播放
	 */
	private String week;
	//定时播放 1 ：确定定时 
	private String timedPlay;
	//取证设备id
	private String deviceId;
	//发布方式
	private String publishMethod;
	
	//每周几（逗号分隔）
	private String daysOfWeek;

	//模板主键
	private String progTemplateId;
	//开关键
	private String onOrOff;
	
	public String getPlayTime() {
		return playTime;
	}
	public void setPlayTime(String playTime) {
		this.playTime = playTime;
	}
	public String getPlayTimes() {
		return playTimes;
	}
	public void setPlayTimes(String playTimes) {
		this.playTimes = playTimes;
	}
	public String getPlayDelay() {
		return playDelay;
	}
	public void setPlayDelay(String playDelay) {
		this.playDelay = playDelay;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getTimedPlay() {
		return timedPlay;
	}
	public void setTimedPlay(String timedPlay) {
		this.timedPlay = timedPlay;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getPublishMethod() {
		return publishMethod;
	}
	public void setPublishMethod(String publishMethod) {
		this.publishMethod = publishMethod;
	}
	public String getDaysOfWeek() {
		return daysOfWeek;
	}
	public void setDaysOfWeek(String daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}
	public String getProgTemplateId() {
		return progTemplateId;
	}
	public void setProgTemplateId(String progTemplateId) {
		this.progTemplateId = progTemplateId;
	}
	public String getOnOrOff() {
		return onOrOff;
	}
	public void setOnOrOff(String onOrOff) {
		this.onOrOff = onOrOff;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
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
	public String getPerName() {
		return perName;
	}
	public void setPerName(String perName) {
		this.perName = perName;
	}
	public String getShowMode() {
		return showMode;
	}
	public void setShowMode(String showMode) {
		this.showMode = showMode;
	}
	public String getProgramPriority() {
		return programPriority;
	}
	public void setProgramPriority(String programPriority) {
		this.programPriority = programPriority;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getIssueStatus() {
		return issueStatus;
	}
	public void setIssueStatus(String issueStatus) {
		this.issueStatus = issueStatus;
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
	public String getEnableFlag() {
		return enableFlag;
	}
	public void setEnableFlag(String enableFlag) {
		this.enableFlag = enableFlag;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getSpecId() {
		return specId;
	}
	public void setSpecId(String specId) {
		this.specId = specId;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getTemplateDesc() {
		return templateDesc;
	}
	public void setTemplateDesc(String templateDesc) {
		this.templateDesc = templateDesc;
	}
	public String getProgramContent() {
		return programContent;
	}
	public void setProgramContent(String programContent) {
		this.programContent = programContent;
	}
}
