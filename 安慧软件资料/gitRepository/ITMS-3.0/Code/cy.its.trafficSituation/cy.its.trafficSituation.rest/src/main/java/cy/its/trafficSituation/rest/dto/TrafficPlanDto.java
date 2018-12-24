package cy.its.trafficSituation.rest.dto;

import java.util.List;

import cy.its.com.dto.BaseDto;

/**
 * ÍøÑ²Ô¤°¸
 * 
 * @author Administrator
 *
 */
public class TrafficPlanDto extends BaseDto {
	private String videoPlanId;

	private String orgId;

	private String videoPlanName;

	private String enableFlag;

	private String createBy;

	private String createTime;

	private String updateBy;

	private String updateTime;

	private Long defaultCruiseTimes;

	private Long totalCruiseTimes;

	private Long planExecuteTimes;

	private List<TrafficPlanVideoDto> videoLst;

	public List<TrafficPlanVideoDto> getVideoLst() {
		return videoLst;
	}

	public void setVideoLst(List<TrafficPlanVideoDto> videoLst) {
		this.videoLst = videoLst;
	}

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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
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
