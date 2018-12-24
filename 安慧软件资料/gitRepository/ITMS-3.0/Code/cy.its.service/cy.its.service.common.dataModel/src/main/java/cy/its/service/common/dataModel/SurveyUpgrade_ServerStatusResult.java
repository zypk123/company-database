package cy.its.service.common.dataModel;

import java.util.Date;
import java.util.List;

public class SurveyUpgrade_ServerStatusResult {
	String surveySystemId;
	String surveySystemName;
	String orgPrivilegeCode;
	String serverId;
	String serverIP;
	
	/**
	 * 主机名
	 */
	String hostName;
	
	/**
	 * 软件版本
	 */
	String softVersion;
	
	/**
	 * 服务器启动时间,yyyy-MM-dd HH:mm:ss
	 * 从未收到时为空
	 */
	Date hostStartTime;
	
	/**
	 * 服务器当前时间, yyyy-MM-dd HH:mm:ss
	 * 从未收到时为空
	 */
	Date hostCurrTime;
	
	/**
	 * 网络利用率,%
	 */
	Integer networkUsage;
	
	/**
	 * 总内存，kb
	 */
	Long memoryTotal;
	
	/**
	 * 已用内存,  %
	 */
	Long memoryUsage;

	/**
	 * cpu利用率,%
	 */
	Integer cpuUsage;
	
	/**
	 * IOPS-Input
	 */
	Integer inputPerSec;
	
	/**
	 * IOPS-Output
	 */
	Integer outputPerSec;
	
	/**
	 * 服务器状态更新时间
	 * 服务器状态从未收到时为空
	 */
	Date updateTime;
	
	Integer msgType;
	Integer reportCycle;
	Integer timeResult;
    
    /**
     * 服务器状态   0 正常   1 异常   2 未收到
     */
    Integer status;
    
    /**
     * 服务器状态描述
     */
    String statusDescription;
    
    /**
     * 服务器上的服务状态
     * 服务状态从未收到时，以下列表为空
     */
	List<SurveyUpgrade_ServiceStatusResult> lstServiceStatus;

	public String getSurveySystemId() {
		return surveySystemId;
	}

	public void setSurveySystemId(String surveySystemId) {
		this.surveySystemId = surveySystemId;
	}

	public String getSurveySystemName() {
		return surveySystemName;
	}

	public void setSurveySystemName(String surveySystemName) {
		this.surveySystemName = surveySystemName;
	}

	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
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

	public Date getHostStartTime() {
		return hostStartTime;
	}

	public void setHostStartTime(Date hostStartTime) {
		this.hostStartTime = hostStartTime;
	}

	public Date getHostCurrTime() {
		return hostCurrTime;
	}

	public void setHostCurrTime(Date hostCurrTime) {
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public List<SurveyUpgrade_ServiceStatusResult> getLstServiceStatus() {
		return lstServiceStatus;
	}

	public void setLstServiceStatus(List<SurveyUpgrade_ServiceStatusResult> lstServiceStatus) {
		this.lstServiceStatus = lstServiceStatus;
	}
}
