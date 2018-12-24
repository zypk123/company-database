package ah.its.wrokflow.dto;



public class DeviceApproveDto {

	private String  groupId;
	private String  deviceName;
	private String  startTime;
	private String  endTime;
	private String  startPage;
	private String  pageSize;
	private String  status;
	private String  deviceApproveId;
	
	public String getDeviceApproveId() {
		return deviceApproveId;
	}
	public void setDeviceApproveId(String deviceApproveId) {
		this.deviceApproveId = deviceApproveId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
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
	
	
}
