package cy.its.service.common.dataModel;

public class SurveyUpgrade_ServerStatus extends Model{
	
	private String hostIP;

    private String hostName;

    private String softVersion;

    private Long hostStartTime;

    private Long hostCurrTime;

    private Integer networkUsage;

    private Long memoryTotal;

    private Long memoryUsage;

    private Integer cpuUsage;

    private Integer inputPerSec;

    private Integer outputPerSec;

    private java.util.Map<String, String> extendedProperties;
        
    private Integer msgType;
    
    private Integer reportCycle;
    
    private Integer timeResult;

	public String getHostIP() {
		return hostIP;
	}

	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getSoftVersion() {
		return softVersion;
	}

	public void setSoftVersion(String softVersion) {
		this.softVersion = softVersion;
	}

	public Long getHostStartTime() {
		return hostStartTime;
	}

	public void setHostStartTime(Long hostStartTime) {
		this.hostStartTime = hostStartTime;
	}

	public Long getHostCurrTime() {
		return hostCurrTime;
	}

	public void setHostCurrTime(Long hostCurrTime) {
		this.hostCurrTime = hostCurrTime;
	}

	public Integer getNetworkUsage() {
		return networkUsage;
	}

	public void setNetworkUsage(Integer networkUsage) {
		this.networkUsage = networkUsage;
	}

	public Long getMemoryTotal() {
		return memoryTotal;
	}

	public void setMemoryTotal(Long memoryTotal) {
		this.memoryTotal = memoryTotal;
	}

	public Long getMemoryUsage() {
		return memoryUsage;
	}

	public void setMemoryUsage(Long memoryUsage) {
		this.memoryUsage = memoryUsage;
	}

	public Integer getCpuUsage() {
		return cpuUsage;
	}

	public void setCpuUsage(Integer cpuUsage) {
		this.cpuUsage = cpuUsage;
	}

	public Integer getInputPerSec() {
		return inputPerSec;
	}

	public void setInputPerSec(Integer inputPerSec) {
		this.inputPerSec = inputPerSec;
	}

	public Integer getOutputPerSec() {
		return outputPerSec;
	}

	public void setOutputPerSec(Integer outputPerSec) {
		this.outputPerSec = outputPerSec;
	}

	public java.util.Map<String, String> getExtendedProperties() {
		return extendedProperties;
	}

	public void setExtendedProperties(java.util.Map<String, String> extendedProperties) {
		this.extendedProperties = extendedProperties;
	}

	public Integer getMsgType() {
		return msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

	public Integer getReportCycle() {
		return reportCycle;
	}

	public void setReportCycle(Integer reportCycle) {
		this.reportCycle = reportCycle;
	}

	public Integer getTimeResult() {
		return timeResult;
	}

	public void setTimeResult(Integer timeResult) {
		this.timeResult = timeResult;
	}
}
