package ah.its.wrokflow.repository.dao;

public class DeviceApproveResult {
	//审批单处理单位查询返回接收
	private String police_name;
	private String approval_comments;
	private String approval_result;
	private String approval_time;
	private String group_name;
	public String getPolice_name() {
		return police_name;
	}
	public void setPolice_name(String police_name) {
		this.police_name = police_name;
	}
	public String getApproval_comments() {
		return approval_comments;
	}
	public void setApproval_comments(String approval_comments) {
		this.approval_comments = approval_comments;
	}
	public String getApproval_result() {
		return approval_result;
	}
	public void setApproval_result(String approval_result) {
		this.approval_result = approval_result;
	}
	public String getApproval_time() {
		return approval_time;
	}
	public void setApproval_time(String approval_time) {
		this.approval_time = approval_time;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	
	

}
