package cy.its.service.device.serverChecker.core;

import java.util.Date;
import java.util.Map;

import cy.its.service.common.ConstValue;
import cy.its.service.common.dataModel.SurveyUpgrade_ServiceStatus;
import cy.its.service.common.dataModel.SurveyUpgrade_ServiceStatusResult;

public class Service {
	String processName; // 进程名
	int processID; // 进程ID
	String processDesc; // 进程描述
	long processStartTime; // 进程启动时间 yyyy-MM-dd HH:mm:ss
	String userName; // 进程的用户名
	int expires; // 注册有效期,units(minutes)
	int messageType; // 0: 注册消息，1：状态消息
	String softVersion; // 软件版本
	long statusGenTime; // 进程状态产生时间,yyyy-MM-dd HH:mm:ss
	int processStatus; // 进程状态，取值见定义
	int processMemory; // 进程占用内存，单位K
	int processCpu; // 进程占用cpu，%
	int handleNum; // 进程占用句柄数
	String doRecordNum; // 进程处理记录总数
	Map<String, String> typeNums; // 分类的处理个数，目前key的值有:
									// passingVehicleNum,violationVehicleNum
	long countStartTime; // 进程记录统计开始时间
	int countCycle; // 统计周期
	int reportCycle;

	long updateTime;

	public Service(SurveyUpgrade_ServiceStatus status) {
		this.set(status);
	}

	public boolean receiveStatus(SurveyUpgrade_ServiceStatus status) {
		return this.set(status);
	}

	public SurveyUpgrade_ServiceStatusResult generateResult() {
		SurveyUpgrade_ServiceStatusResult rslt = new SurveyUpgrade_ServiceStatusResult();
		rslt.setProcessName(this.processName);
		rslt.setProcessID(this.processID);
		rslt.setProcessDesc(this.processDesc);
		rslt.setProcessStartTime(new Date(this.processStartTime));
		rslt.setSoftVersion(this.softVersion);
		rslt.setStatusGenTime(new Date(this.statusGenTime));
		rslt.setProcessStatus(this.processStatus);
		rslt.setProcessMemory(this.processMemory);
		rslt.setProcessCpu(this.processCpu);
		rslt.setHandleNum(this.handleNum);
		rslt.setDoRecordNum(this.doRecordNum);
		rslt.setReportCycle(this.reportCycle);
		rslt.setTypeNums(this.typeNums);
		
		return rslt;
	}

	private boolean set(SurveyUpgrade_ServiceStatus status) {
		if (this.statusGenTime >= status.getStatusGenTime()) {
			return ConstValue.BOOL_FALSE;
		}

		this.processName = status.getProcessName();
		this.processID = status.getProcessID();
		this.processDesc = status.getProcessDesc();
		this.processStartTime = status.getProcessStartTime();
		this.userName = status.getUserName();
		this.expires = status.getExpires();
		this.messageType = status.getMessageType();
		this.softVersion = status.getSoftVersion();
		this.statusGenTime = status.getStatusGenTime();
		this.processStatus = status.getProcessStatus();
		this.processMemory = status.getProcessMemory();
		this.processCpu = status.getProcessCpu();
		this.handleNum = status.getHandleNum();
		this.doRecordNum = status.getDoRecordNum();
		this.typeNums = status.getTypeNums();
		this.countStartTime = status.getCountStartTime();
		this.countCycle = status.getCountCycle();
		this.reportCycle = status.getReportCycle();

		this.updateTime = System.currentTimeMillis();

		return ConstValue.BOOL_TRUE;

	}

}
