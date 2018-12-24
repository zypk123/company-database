package cy.its.device.rest.dto;

import java.util.List;

import cy.its.com.dto.BaseDto;

public class MonitorAndServerDto extends BaseDto{
	/** 监控中心表 **/
	private String surveySystemId;		//监控中心ID
	private String surveySystemName;	//监控中心名称
	private String orgId;				//机构Id
	private String oldSurveySystemName;	//编辑时原有的监控中心名称(验证监控中心名称用)
	
	
	/** 服务器表 **/
	private String serverId;			//服务器ID
	private String serverIp;			//服务器IP
	private String serverPort;			//端口号
	private String connUserName;		//连接用户名
	private String connPassword;		//连接密码
	private String otalDiskResource;	//磁盘资源总大小
	private String oldServerIp;			//修改前服务器IP（用于服务器IP重复判断）
	private String appType;				//服务器组件类型
	private String serverAppId;			//服务器组件ID
	private String appTypeList;			//服务器下的服务器组件集合   
	
	/** 服务器监控信息 **/
	private String timeResult;			//校时结果
	private String hostStartTime;		//服务器启动时间
	private String hostCurrTime;		//服务器当前时间
	private String networkUsage;		//网络利用率%
	private String cpuUsage;			//CPU利用率
	private String memoryTotal;			//总内存kb
	private String memoryUsage;			//已用内存%
	private String serverStatus;		//服务器状态标识
	private String serverStatusDeacription;		//服务器状态描述
	private List lstServiceStatus;		//服务器中的服务集合
	
	/** 服务的相关信息 **/
	private String processName;         // 进程名
	private String processDesc;         // 进程描述
	private String processStartTime;    // 进程启动时间 yyyy-MM-dd HH:mm:ss
	private int reportCycle;         // 上报周期
	private String statusGenTime;       // 进程状态产生时间,yyyy-MM-dd HH:mm:ss 
	private String processStatus;       // 进程状态
	private String processMemory;       // 进程占用内存，单位K
	private String processCpu;          // 进程占用cpu，%
	private String handleNum;           // 进程占用句柄数 
	private String doRecordNum;         // 进程处理记录总数 
	private String typeNums;  			// 分类的处理个数
    
    
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
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	public String getServerIp() {
		return serverIp;
	}
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	public String getConnUserName() {
		return connUserName;
	}
	public void setConnUserName(String connUserName) {
		this.connUserName = connUserName;
	}
	public String getConnPassword() {
		return connPassword;
	}
	public void setConnPassword(String connPassword) {
		this.connPassword = connPassword;
	}
	public String getOtalDiskResource() {
		return otalDiskResource;
	}
	public void setOtalDiskResource(String otalDiskResource) {
		this.otalDiskResource = otalDiskResource;
	}
	public String getTimeResult() {
		return timeResult;
	}
	public void setTimeResult(String timeResult) {
		this.timeResult = timeResult;
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
	public String getNetworkUsage() {
		return networkUsage;
	}
	public void setNetworkUsage(String networkUsage) {
		this.networkUsage = networkUsage;
	}
	public String getCpuUsage() {
		return cpuUsage;
	}
	public void setCpuUsage(String cpuUsage) {
		this.cpuUsage = cpuUsage;
	}
	public String getMemoryTotal() {
		return memoryTotal;
	}
	public void setMemoryTotal(String memoryTotal) {
		this.memoryTotal = memoryTotal;
	}
	public String getMemoryUsage() {
		return memoryUsage;
	}
	public void setMemoryUsage(String memoryUsage) {
		this.memoryUsage = memoryUsage;
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
	public String getProcessStartTime() {
		return processStartTime;
	}
	public void setProcessStartTime(String processStartTime) {
		this.processStartTime = processStartTime;
	}
	public int getReportCycle() {
		return reportCycle;
	}
	public void setReportCycle(int reportCycle) {
		this.reportCycle = reportCycle;
	}
	public String getStatusGenTime() {
		return statusGenTime;
	}
	public void setStatusGenTime(String statusGenTime) {
		this.statusGenTime = statusGenTime;
	}
	public String getProcessStatus() {
		return processStatus;
	}
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
	public String getProcessMemory() {
		return processMemory;
	}
	public void setProcessMemory(String processMemory) {
		this.processMemory = processMemory;
	}
	public String getProcessCpu() {
		return processCpu;
	}
	public void setProcessCpu(String processCpu) {
		this.processCpu = processCpu;
	}
	public String getHandleNum() {
		return handleNum;
	}
	public void setHandleNum(String handleNum) {
		this.handleNum = handleNum;
	}
	public String getDoRecordNum() {
		return doRecordNum;
	}
	public void setDoRecordNum(String doRecordNum) {
		this.doRecordNum = doRecordNum;
	}
	public String getTypeNums() {
		return typeNums;
	}
	public void setTypeNums(String typeNums) {
		this.typeNums = typeNums;
	}
	public String getServerStatus() {
		return serverStatus;
	}
	public void setServerStatus(String serverStatus) {
		this.serverStatus = serverStatus;
	}
	public String getServerStatusDeacription() {
		return serverStatusDeacription;
	}
	public void setServerStatusDeacription(String serverStatusDeacription) {
		this.serverStatusDeacription = serverStatusDeacription;
	}
	public List getLstServiceStatus() {
		return lstServiceStatus;
	}
	public void setLstServiceStatus(List lstServiceStatus) {
		this.lstServiceStatus = lstServiceStatus;
	}
	public String getOldServerIp() {
		return oldServerIp;
	}
	public void setOldServerIp(String oldServerIp) {
		this.oldServerIp = oldServerIp;
	}
	public String getOldSurveySystemName() {
		return oldSurveySystemName;
	}
	public void setOldSurveySystemName(String oldSurveySystemName) {
		this.oldSurveySystemName = oldSurveySystemName;
	}
	public String getAppTypeList() {
		return appTypeList;
	}
	public void setAppTypeList(String appTypeList) {
		this.appTypeList = appTypeList;
	}
	public String getServerPort() {
		return serverPort;
	}
	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getServerAppId() {
		return serverAppId;
	}
	public void setServerAppId(String serverAppId) {
		this.serverAppId = serverAppId;
	}
	
	
//	public String getUsedDiskResource() {
//		return usedDiskResource;
//	}
//	public void setUsedDiskResource(String usedDiskResource) {
//		this.usedDiskResource = usedDiskResource;
//	}
//	public String getTotalBackupService() {
//		return totalBackupService;
//	}
//	public void setTotalBackupService(String totalBackupService) {
//		this.totalBackupService = totalBackupService;
//	}
//	public String getRunningBackupService() {
//		return runningBackupService;
//	}
//	public void setRunningBackupService(String runningBackupService) {
//		this.runningBackupService = runningBackupService;
//	}
//	public String getTotalDataSheet() {
//		return totalDataSheet;
//	}
//	public void setTotalDataSheet(String totalDataSheet) {
//		this.totalDataSheet = totalDataSheet;
//	}
//	public String getShortageDataSheet() {
//		return shortageDataSheet;
//	}
//	public void setShortageDataSheet(String shortageDataSheet) {
//		this.shortageDataSheet = shortageDataSheet;
//	}
	
}
