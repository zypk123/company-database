package cy.its.service.standardization.dataMaker.originalModel;

public class SurveyUpgrade_HostStatusInfo extends BaseOrginalModel{
	
	private String hostIP;

    private String hostName;

    private String softVersion;

    private String hostStartTime;

    private String hostCurrTime;

    private int networkUsage;

    private long memoryTotal;

    private long memoryUsage;

    private int cpuUsage;

    private int inputPerSec;

    private int outputPerSec;

    private java.util.Map<java.lang.String, java.lang.String> extendedProperties;
    
    private int msgType;
    
    private int reportCycle;
    
    private int timeResult;
    

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

	public String getHostStartTime() {
		return hostStartTime;
	}

	public void setHostStartTime(String hostStartTime) {
		this.hostStartTime = hostStartTime;
	}

	public String getHostCurrTime() {
		return hostCurrTime;
	}

	public void setHostCurrTime(String hostCurrTime) {
		this.hostCurrTime = hostCurrTime;
	}

	public int getNetworkUsage() {
		return networkUsage;
	}

	public void setNetworkUsage(int networkUsage) {
		this.networkUsage = networkUsage;
	}

	public long getMemoryTotal() {
		return memoryTotal;
	}

	public void setMemoryTotal(long memoryTotal) {
		this.memoryTotal = memoryTotal;
	}

	public long getMemoryUsage() {
		return memoryUsage;
	}

	public void setMemoryUsage(long memoryUsage) {
		this.memoryUsage = memoryUsage;
	}

	public int getCpuUsage() {
		return cpuUsage;
	}

	public void setCpuUsage(int cpuUsage) {
		this.cpuUsage = cpuUsage;
	}

	public int getInputPerSec() {
		return inputPerSec;
	}

	public void setInputPerSec(int inputPerSec) {
		this.inputPerSec = inputPerSec;
	}

	public int getOutputPerSec() {
		return outputPerSec;
	}

	public void setOutputPerSec(int outputPerSec) {
		this.outputPerSec = outputPerSec;
	}

	public java.util.Map<java.lang.String, java.lang.String> getExtendedProperties() {
		return extendedProperties;
	}

	public void setExtendedProperties(java.util.Map<java.lang.String, java.lang.String> extendedProperties) {
		this.extendedProperties = extendedProperties;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public int getReportCycle() {
		return reportCycle;
	}

	public void setReportCycle(int reportCycle) {
		this.reportCycle = reportCycle;
	}

	public int getTimeResult() {
		return timeResult;
	}

	public void setTimeResult(int timeResult) {
		this.timeResult = timeResult;
	}
}
