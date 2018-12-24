package cy.its.service.common.dataModel;

public class SurveyUpgrade_ServiceStatus extends Model {
	
	private String hostIp;

	private int processID;

	private String processName;

	private String processDesc;

	private long processStartTime;

	private String userName;

	private int expires;

	private int messageType;

	private String softVersion;

	private long statusGenTime;

	private int processStatus;

	private int processMemory;

	private int processCpu;

	private int handleNum;

	private String doRecordNum;

	private java.util.Map<java.lang.String, java.lang.String> typeNums;

	private long countStartTime;

	private int countCycle;
	
	private int reportCycle;

	public int getReportCycle() {
		return reportCycle;
	}

	public void setReportCycle(int reportCycle) {
		this.reportCycle = reportCycle;
	}

	private java.util.Map<java.lang.String, java.lang.String> extendedProperties;

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public int getProcessID() {
		return processID;
	}

	public void setProcessID(int processID) {
		this.processID = processID;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getProcessDesc() {
		return processDesc;
	}

	public void setProcessDesc(String processDesc) {
		this.processDesc = processDesc;
	}

	public long getProcessStartTime() {
		return processStartTime;
	}

	public void setProcessStartTime(long processStartTime) {
		this.processStartTime = processStartTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getExpires() {
		return expires;
	}

	public void setExpires(int expires) {
		this.expires = expires;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public String getSoftVersion() {
		return softVersion;
	}

	public void setSoftVersion(String softVersion) {
		this.softVersion = softVersion;
	}

	public long getStatusGenTime() {
		return statusGenTime;
	}

	public void setStatusGenTime(long statusGenTime) {
		this.statusGenTime = statusGenTime;
	}

	public int getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(int processStatus) {
		this.processStatus = processStatus;
	}

	public int getProcessMemory() {
		return processMemory;
	}

	public void setProcessMemory(int processMemory) {
		this.processMemory = processMemory;
	}

	public int getProcessCpu() {
		return processCpu;
	}

	public void setProcessCpu(int processCpu) {
		this.processCpu = processCpu;
	}

	public int getHandleNum() {
		return handleNum;
	}

	public void setHandleNum(int handleNum) {
		this.handleNum = handleNum;
	}

	public String getDoRecordNum() {
		return doRecordNum;
	}

	public void setDoRecordNum(String doRecordNum) {
		this.doRecordNum = doRecordNum;
	}

	public java.util.Map<java.lang.String, java.lang.String> getTypeNums() {
		return typeNums;
	}

	public void setTypeNums(java.util.Map<java.lang.String, java.lang.String> typeNums) {
		this.typeNums = typeNums;
	}

	public long getCountStartTime() {
		return countStartTime;
	}

	public void setCountStartTime(long countStartTime) {
		this.countStartTime = countStartTime;
	}

	public int getCountCycle() {
		return countCycle;
	}

	public void setCountCycle(int countCycle) {
		this.countCycle = countCycle;
	}

	public java.util.Map<java.lang.String, java.lang.String> getExtendedProperties() {
		return extendedProperties;
	}

	public void setExtendedProperties(java.util.Map<java.lang.String, java.lang.String> extendedProperties) {
		this.extendedProperties = extendedProperties;
	}
}
