package ah.its.wrokflow.dto;


/**
 * 接收类
 * @Title: SystemApproveDto.java
 * @Package ah.its.wrokflow.dto
 * @Description: TODO(这里要填写用途)
 * @author chengf chengf@cychina.cn
 * @date 2016年6月13日 下午5:36:26
 * @version V1.0
 */
public class SystemApproveDto {

	private String  groupId;
	private String  systemName;
	private String  startTime;
	private String  endTime;
	private String  startPage;
	private String  pageSize;
	private String  status;
	private String  systemApproveId;
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
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
	public String getStartPage() {
		return startPage;
	}
	public void setStartPage(String startPage) {
		this.startPage = startPage;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getSystemApproveId() {
		return systemApproveId;
	}
	public void setSystemApproveId(String systemApproveId) {
		this.systemApproveId = systemApproveId;
	}
	
}
