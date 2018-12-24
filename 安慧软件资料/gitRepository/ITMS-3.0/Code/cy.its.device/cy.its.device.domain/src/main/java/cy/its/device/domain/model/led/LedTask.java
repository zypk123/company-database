package cy.its.device.domain.model.led;

import java.util.Date;

import cy.its.com.util.StringUtil;
import cy.its.device.domain.repository.ISysRepository;

public abstract class LedTask {
	
	private String taskId;
	
	/**
	 * 任务类型 取值为: 
	 * 10 日常任务 11 定时任务 12 插播任务 13 紧急任务;
	 * 20 违法自动发布;21 流量自动发布; 22 气象自动发布; 23 限速自动发布;
	 */
	private String taskType;

	private String taskName;

	private String creator;

	private Date createTime;

	private String approver;

	private Date approveTime;

	private Short issueCount;

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

	public Short getIssueCount() {
		return issueCount;
	}

	public void setIssueCount(Short issueCount) {
		this.issueCount = issueCount;
	}
	
	public void checkCreator() throws Exception{
		if(StringUtil.isNullOrEmpty(this.creator)){
			throw new Exception("请指定创建者");
		}
	}
	
	public void  check(ISysRepository sysRepository) throws Exception {
		if(StringUtil.isNullOrEmpty(this.taskType)){
			throw new Exception("未指定任务类型");
		}
		
		this.checkCreator();
		this.checkPrivate(sysRepository);
	}
	
	public abstract LedProg ledProgOfTask();
	protected abstract void checkPrivate(ISysRepository sysRepository) throws Exception;
	public abstract String getTimeString();
}
