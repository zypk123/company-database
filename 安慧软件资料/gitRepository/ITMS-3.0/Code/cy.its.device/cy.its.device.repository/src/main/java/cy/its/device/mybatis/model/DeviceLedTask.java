package cy.its.device.mybatis.model;

import java.util.Date;

public class DeviceLedTask {

	private String taskId;

	private String publishType;
	
	private String deviceId;

	private String regionalId;

	private String programId;

	private String taskName;

	private String taskType;

	private String taskFilter;

	private String enableFlag;

	private Short issueCount;

	private Short timeMode;

	private String startDate;

	private String endDate;

	private String startTime;

	private String endTime;

	private String week;

	private String creator;

	private Date createTime;

	private String approver;

	private Date approveTime;

	private String issueStatus;

	private TDeviceLedProg tDeviceLedProg;

//	private List<LedProgContentText> lstLedText;
//
//	private List<LedProgContentMedia> lstLedMedia;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	public String getPublishType() {
		return publishType;
	}

	public void setPublishType(String publishType) {
		this.publishType = publishType;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getRegionalId() {
		return regionalId;
	}

	public void setRegionalId(String regionalId) {
		this.regionalId = regionalId;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
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

	public Short getIssueCount() {
		return issueCount;
	}

	public void setIssueCount(Short issueCount) {
		this.issueCount = issueCount;
	}

	public Short getTimeMode() {
		return timeMode;
	}

	public void setTimeMode(Short timeMode) {
		this.timeMode = timeMode;
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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	public String getIssueStatus() {
		return issueStatus;
	}

	public void setIssueStatus(String issueStatus) {
		this.issueStatus = issueStatus;
	}

	public TDeviceLedProg gettDeviceLedProg() {
		return tDeviceLedProg;
	}

	public void settDeviceLedProg(TDeviceLedProg tDeviceLedProg) {
		this.tDeviceLedProg = tDeviceLedProg;
	}
}
