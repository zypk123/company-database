package cy.its.service.device.serverChecker.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cy.its.service.common.ConstValue;
import cy.its.service.common.JsonUtil;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataModel.SurveyUpgrade_ServiceStatus;
import cy.its.service.common.log.LogManager;
import cy.its.service.common.rabbitmqClient.MQGateWay;
import cy.its.service.common.dataModel.DeviceAlarm;
import cy.its.service.common.dataModel.SurveyUpgrade_ServerStatus;
import cy.its.service.common.dataModel.SurveyUpgrade_ServerStatusResult;
import cy.its.service.device.serverChecker.model.ServerInfo;

public class Server {
	/**
	 * 服务列表: key 服务进程名
	 */
	Map<String, Service> service;
	String surveySystemId;
	String surveySystemName;
	String orgPrivilegeCode;
	String serverId;
	String serverIP;

	String hostName; // 主机名
	String softVersion; // 软件版本
	Long hostStartTime; // 服务器启动时间,yyyy-MM-dd HH:mm:ss
	Long hostCurrTime; // 服务器当前时间,yyyy-MM-dd HH:mm:ss
	Integer networkUsage; // 网络利用率,%
	Long memoryTotal; // 总内存，kb
	Long memoryUsage; // 已用内存,kb
	Integer cpuUsage; // cpu利用率,%
	Integer inputPerSec; // IOPS-Input
	Integer outputPerSec; // IOPS-Output
	Long updateTime;

	Integer msgType;
	Integer reportCycle;
	Integer timeResult;
    

	public Server(ServerInfo serverInfo) {
		this.config(serverInfo);
		this.setServerStatus();
	}

	public synchronized void config(ServerInfo serverInfo) {
		this.surveySystemId = serverInfo.surveySystemId;
		this.surveySystemName = serverInfo.surveySystemName;
		this.orgPrivilegeCode = serverInfo.orgPrivilegeCode;
		this.serverId = serverInfo.serverId;
		this.serverIP = serverInfo.serverIp;
	}

	public synchronized boolean receiveServerStatus(SurveyUpgrade_ServerStatus status) {
		if (this.hostCurrTime != null && this.hostCurrTime >= status.getHostCurrTime()) {
			return ConstValue.BOOL_FALSE;
		}

		this.hostName = status.getHostName();
		this.softVersion = status.getSoftVersion();
		this.hostStartTime = status.getHostStartTime();
		this.hostCurrTime = status.getHostCurrTime();
		this.networkUsage = status.getNetworkUsage();
		this.memoryTotal = status.getMemoryTotal();
		this.memoryUsage = status.getMemoryUsage();
		this.cpuUsage = status.getCpuUsage();
		this.inputPerSec = status.getInputPerSec();
		this.outputPerSec = status.getOutputPerSec();
		this.msgType  = status.getMsgType();
		this.reportCycle = status.getReportCycle();
		this.timeResult = status.getTimeResult();
		this.updateTime = System.currentTimeMillis();
		this.setServerStatus();
		
		publishResult();
		return ConstValue.BOOL_TRUE;
	}

	public synchronized boolean receiveProcessStatus(SurveyUpgrade_ServiceStatus status) {
		if (service == null) {
			service = new HashMap<String, Service>();
		}

		if (service.containsKey(status.getProcessName())) {
			service.get(status.getProcessName()).receiveStatus(status);
		} else {
			service.put(status.getProcessName(), new Service(status));
		}

		this.setServerStatus();
		
		publishResult();
		return ConstValue.BOOL_TRUE;
	}

	public synchronized SurveyUpgrade_ServerStatusResult generateResult() {
		this.setServerStatus();
		return getResult();
	}
	
	public synchronized void periodCheck() {
		if(this.updateTime != null) {
			ServerStatus s = this.analyServerStatus();			
			if(s.getStatus() == this.status && 
			   s.getStatusDescription().equals(this.statusDescription)){
				return;
			} else {
				this.status = s.getStatus();
				this.statusDescription = s.getStatusDescription();				
				this.publishResult();
			}
		}
	}
	
	private void publishResult() {
		String msg = JsonUtil.serialize(getResult());
		LogManager.debug("发布服务器和服务状态: " + msg);
		MQGateWay.publish(ConstValue.ROUTE_KEY_SERVER_STATUS_RESULT, msg);
	}

	private SurveyUpgrade_ServerStatusResult getResult() {
		SurveyUpgrade_ServerStatusResult rslt = new SurveyUpgrade_ServerStatusResult();
		rslt.setSurveySystemId(this.surveySystemId);
		rslt.setSurveySystemName(this.surveySystemName);
		rslt.setOrgPrivilegeCode(this.orgPrivilegeCode);
		rslt.setServerId(this.serverId);
		rslt.setServerIP(this.serverIP);
		rslt.setHostName(this.hostName);
		rslt.setSoftVersion(this.softVersion);
		rslt.setHostStartTime(this.hostStartTime != null ? new Date(this.hostStartTime) : null);
		rslt.setHostCurrTime(this.hostCurrTime != null ? new Date(this.hostCurrTime) : null);
		rslt.setNetworkUsage(this.networkUsage);
		rslt.setMemoryTotal(this.memoryTotal);
		rslt.setMemoryUsage(this.memoryUsage);
		rslt.setCpuUsage(this.cpuUsage);
		rslt.setInputPerSec(this.inputPerSec);
		rslt.setOutputPerSec(this.outputPerSec);
		rslt.setUpdateTime(this.updateTime != null ? new Date(this.updateTime) : null);
		rslt.setMsgType(this.msgType);
		rslt.setReportCycle(this.reportCycle);
		rslt.setTimeResult(this.timeResult);		
		rslt.setStatus(this.status);
		rslt.setStatusDescription(this.statusDescription);
		
		if (service != null) {
			rslt.setLstServiceStatus(this.service.entrySet().stream().map(c -> c.getValue().generateResult())
					.collect(Collectors.toList()));
		}

		return rslt;
	}

	static long ErrSpan = 350000;
	static long INT_90 = 90;
	static String NORMAL = "正常";
	static String ERROR = "异常:";
	static String UN_RECV_MSG = "未收到";
	static String ERROR_CPU = "CPU占用超过90%";
	static String ERROR_MEMORY = "内存占用超过90%";
	static String ERROR_SPAN = "状态时间偏差过大";

	int status;
	String statusDescription;
	
	private void setServerStatus() {
		ServerStatus s = analyServerStatus();		
		this.status = s.getStatus();
		this.statusDescription = s.getStatusDescription();		
		s = null;
	}
	
	private ServerStatus analyServerStatus() {
		long currentTimeMillis = System.currentTimeMillis();
		if (this.updateTime == null) {
			return new ServerStatus(UN_RECV_MSG, ConstValue.INT_2);
		} else if ((this.cpuUsage != null && this.cpuUsage > INT_90)
				|| (this.memoryUsage != null && this.memoryUsage > INT_90)
				|| (this.hostCurrTime != null && Math.abs(this.hostCurrTime - currentTimeMillis) > ErrSpan)) {
			List<String> msg = new ArrayList<String>(ConstValue.INT_3);
			if (this.cpuUsage != null && this.cpuUsage > INT_90) {
				msg.add(ERROR_CPU);
			}
			if (this.memoryUsage != null && this.memoryUsage > INT_90) {
				msg.add(ERROR_MEMORY);
			}

			if (this.hostCurrTime != null && Math.abs(this.hostCurrTime - currentTimeMillis) > ErrSpan) {
				msg.add(ERROR_SPAN);
			}

			ServerStatus status = new ServerStatus(String.join(ConstValue.SEMICOLON, msg), ConstValue.INT_1);
			
			if(ConstValue.INT_1 != this.status ||
			   !status.getStatusDescription().equals(this.statusDescription)) {
				publishAlarm(status, currentTimeMillis);
			}
			
			return status;
		} else {
			return new ServerStatus(NORMAL, ConstValue.INT_0);
		}
	}
	
	void publishAlarm(ServerStatus status, long currentTimeMillis) {
		DeviceAlarm alarm = new DeviceAlarm();
		alarm.setAlarmId(StringUtil.generateUUID());
		alarm.setAlarmType("2");
		alarm.setAlarmSubType(String.valueOf(status.getStatus()));
		alarm.setAlarmDeviceId(this.serverId);
		alarm.setStartTime(new Date(currentTimeMillis));
//		alarm.setEndTime();
		alarm.setCollectWay("2");
		alarm.setAlarmDisc(status.getStatusDescription());
		alarm.setOrgPrivilegeCode(this.orgPrivilegeCode);
		String msg = JsonUtil.serialize(alarm);
		LogManager.debug("发布服务器异常报警: " + msg);
		MQGateWay.publish(ConstValue.ROUTE_KEY_ALARM_DEVICE_BEFORE_TODB, msg);
	}
}


class ServerStatus {
	
	private String statusDescription;
	
	private int status;
	
	public ServerStatus(String statusDescription, int status) {
		this.statusDescription = statusDescription;
		this.status = status;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
