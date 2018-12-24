package cy.its.service.common.dataModel;

import java.util.Date;
import java.util.Map;

public class SurveyUpgrade_ServiceStatusResult {

	private String processName; // 进程名
	private int processID; // 进程ID
	private String processDesc; // 进程描述
	private Date processStartTime; // 进程启动时间 yyyy-MM-dd HH:mm:ss
	private String softVersion; // 软件版本
	private Date statusGenTime; // 进程状态产生时间,yyyy-MM-dd HH:mm:ss
	private int processStatus; // 进程状态，取值见定义
	private int processMemory; // 进程占用内存，单位K
	private int processCpu; // 进程占用cpu，%
	private int handleNum; // 进程占用句柄数
	private String doRecordNum; // 进程处理记录总数
	private int reportCycle; // 
	private Map<String,String> typeNums;
	
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public int getProcessID() {
		return processID;
	}
	public void setProcessID(int processID) {
		this.processID = processID;
	}
	public String getProcessDesc() {
		return processDesc;
	}
	public void setProcessDesc(String processDesc) {
		this.processDesc = processDesc;
	}
	public Date getProcessStartTime() {
		return processStartTime;
	}
	public void setProcessStartTime(Date processStartTime) {
		this.processStartTime = processStartTime;
	}
	public String getSoftVersion() {
		return softVersion;
	}
	public void setSoftVersion(String softVersion) {
		this.softVersion = softVersion;
	}
	public Date getStatusGenTime() {
		return statusGenTime;
	}
	public void setStatusGenTime(Date statusGenTime) {
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
	public int getReportCycle() {
		return reportCycle;
	}
	
	public void setReportCycle(int reportCycle) {
		this.reportCycle = reportCycle;
	}
	
	public Map<String, String> getTypeNums() {
		return typeNums;
	}
	
	public void setTypeNums(Map<String, String> typeNums) {
		this.typeNums = typeNums;
	}
}
