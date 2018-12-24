package cy.its.video.domain.model;

import java.util.Date;

public class TrafficPlan {
	private String videoPlanId;

	private String orgId;

	private String videoPlanName;

	private String enableFlag;

	private String createBy;

	private Date createTime;

	private String updateBy;

	private Date updateTime;

	private Long defaultCruiseTimes;

	private Long totalCruiseTimes;

	private Long planExecuteTimes;

	public String getVideoPlanId() {
		return videoPlanId;
	}

	public void setVideoPlanId(String videoPlanId) {
		this.videoPlanId = videoPlanId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getVideoPlanName() {
		return videoPlanName;
	}

	public void setVideoPlanName(String videoPlanName) {
		this.videoPlanName = videoPlanName;
	}

	public String getEnableFlag() {
		return enableFlag;
	}

	public void setEnableFlag(String enableFlag) {
		this.enableFlag = enableFlag;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getDefaultCruiseTimes() {
		return defaultCruiseTimes;
	}

	public void setDefaultCruiseTimes(Long defaultCruiseTimes) {
		this.defaultCruiseTimes = defaultCruiseTimes;
	}

	public Long getTotalCruiseTimes() {
		return totalCruiseTimes;
	}

	public void setTotalCruiseTimes(Long totalCruiseTimes) {
		this.totalCruiseTimes = totalCruiseTimes;
	}

	public Long getPlanExecuteTimes() {
		return planExecuteTimes;
	}

	public void setPlanExecuteTimes(Long planExecuteTimes) {
		this.planExecuteTimes = planExecuteTimes;
	}

}