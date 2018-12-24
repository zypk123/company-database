package cy.its.service.device.statusAnalysis.core;

import java.util.Date;
import java.util.List;

import cy.its.service.common.ConstValue;
import cy.its.service.common.JsonUtil;
import cy.its.service.common.dataModel.ComponentStatusResult;
import cy.its.service.common.dataModel.DeviceStatus;
import cy.its.service.common.dataModel.SysStatusResult;
import cy.its.service.common.log.LogManager;
import cy.its.service.common.rabbitmqClient.MQGateWay;
import cy.its.service.device.statusAnalysis.data.StatusLogWriter;
import cy.its.service.device.statusAnalysis.data.StatusUpdater;
import cy.its.service.device.statusAnalysis.data.SurveyData;
import cy.its.service.device.statusAnalysis.model.DeviceCfg;
import cy.its.service.device.statusAnalysis.model.Period;
import cy.its.service.device.statusAnalysis.model.StatusLog;
import cy.its.service.device.statusAnalysis.util.StatusUtil;

abstract class Sys {
	protected String deviceId;
	protected String deviceSysNbr;
	protected String orgPrivilegeCode;
	protected String siteCode;
	protected String deviceType;
	protected int statusType;
	protected long surveyDataUploadTime;
	protected long statusTypeUpdateTime;
	protected long statusTypeBeginTime;
	protected List<Integer> statusDetails;
	protected int vehicleTotal;
	protected int offLinePeriod;

	protected Sys(List<DeviceCfg> cfgs){
		this.config(cfgs.get(ConstValue.INT_0));
		this.customConfig(cfgs);
		this.initStatus(cfgs.get(ConstValue.INT_0));
	}
	
	/**
	 * 设备系统的最新数据上传时间
	 * 
	 * @param data
	 */
	abstract void receive(SurveyData data);

	/**
	 * 接收前端传送的心跳
	 * 
	 * @param status
	 */
	abstract void receive(DeviceStatus status);

	/**
	 * 自定义检测,如离线、异常等检测
	 */
	abstract void customCheck();

	/**
	 * 获取组件状态
	 * 
	 * @return
	 */
	abstract List<ComponentStatusResult> componentStatus();
	
	/**
	 * 定制化配置
	 */
	abstract void customConfig(List<DeviceCfg> cfgs);

	/**
	 * 改变系统信息
	 * 
	 * @param cfgs
	 */
	synchronized void change(List<DeviceCfg> cfgs) {
		this.config(cfgs.get(ConstValue.INT_0));
		this.customConfig(cfgs);
	}
	
	/**
	 * 停止系统的状态分析
	 */
	public synchronized void stop() {
		this.setStatus(System.currentTimeMillis(), this.statusType, this.vehicleTotal,
				StatusUtil.sumListStatus(this.statusDetails), ConstValue.BOOL_TRUE);
	}

	/**
	 * 配置基本信息
	 * 
	 * @param cfg
	 */
	protected void config(DeviceCfg cfg) {
		this.deviceId = cfg.getDeviceId();
		this.deviceSysNbr = cfg.getDeviceSysNbr();
		this.orgPrivilegeCode = cfg.getOrgPrivilegeCode();
		this.siteCode = cfg.getSiteCode();
		this.deviceType = cfg.getDeviceType();
	}

	/**
	 * 初始化系统状态
	 * 
	 * @param cfg
	 */
	protected void initStatus(DeviceCfg cfg) {
		// 默认设置状态为正常
		this.statusType = ConstValue.DEV_STATUS_TYPE_NORMAL;
		this.vehicleTotal = ConstValue.INT_0;
		this.statusDetails = null;

		// 计算服务停止期间的状态
		if (cfg.getStartTime() != null && cfg.getEndTime() != null) {
			this.statusTypeBeginTime = this.statusType == Integer.valueOf(cfg.getStatusType())
					? cfg.getStartTime().getTime() : cfg.getEndTime().getTime();
			this.setStatus(System.currentTimeMillis(), ConstValue.DEV_STATUS_TYPE_NORMAL, vehicleTotal,
					ConstValue.INT_0, ConstValue.BOOL_TRUE);
		} else {
			// 更新系统表
			this.statusTypeUpdateTime = System.currentTimeMillis();
			this.statusTypeBeginTime = this.statusTypeUpdateTime;
			StatusUpdater.update(this.deviceId, this.statusType, new Date(this.statusTypeUpdateTime), null, null);
		}
	}

	/**
	 * 处理状态
	 * 
	 * @param newStatusTime
	 * @param status1
	 * @param vehicleTotal
	 */
	protected void setStatus(long newStatusTime, int newStatusType, int vehicleTotal, int statusCode, boolean ignore) {
		// 判断状态类型是否发生变化
		if (this.statusType != newStatusType) {
			// 状态类型发生变化
			// 写状态日志表
			this.statusTypeBeginTime = this.writeStatusLog(this.deviceId, this.statusType, this.statusTypeBeginTime,
					newStatusTime);
			// 更新系统表
			StatusUpdater.update(this.deviceId, newStatusType, new Date(newStatusTime), null, null);
			this.statusTypeBeginTime = newStatusTime;
		} else {
			// 状态类型不变
				// 检查数据发送是否频繁
			if (!ignore && newStatusTime - this.statusTypeUpdateTime < ConstValue.INT_60000) {
				// 连续发送的相同状态且间隔时间较短
				return;
			}

			// 写状态日志表
			this.statusTypeBeginTime = this.writeStatusLog(this.deviceId, this.statusType, this.statusTypeBeginTime,
					newStatusTime);

			// 更新系统表
			StatusUpdater.update(this.deviceId, newStatusType, new Date(newStatusTime),
					new Date(this.statusTypeBeginTime), new Date(newStatusTime));
		}

		this.statusType = newStatusType;
		this.statusTypeUpdateTime = newStatusTime;
		this.statusDetails = StatusUtil.splitStatus(statusCode);
		this.vehicleTotal = vehicleTotal;

		// 发布状态分析结果
		this.publishResult();
	}

	/**
	 * 发布状态分析结果
	 */
	void publishResult() {
		String json = JsonUtil.serialize(getResult());
		LogManager.debug(String.format(fmt, JsonUtil.serialize(json)));
		MQGateWay.publish(ConstValue.ROUTE_KEY_STATUS_RESULT, json);
	}

	synchronized SysStatusResult generateResult() {
		return getResult();
	}
	
	private SysStatusResult getResult(){
		return new SysStatusResult(this.deviceId, this.deviceSysNbr, this.orgPrivilegeCode,
				this.siteCode, this.surveyDataUploadTime, this.statusTypeUpdateTime, this.statusType, this.statusDetails,
				this.vehicleTotal, this.componentStatus(), this.deviceType);
	}
	
	static String fmt = "发布状态分析结果:%s";

	/**
	 * 写状态日志
	 * 
	 * @param devId
	 * @param statusType
	 * @param begin
	 * @param end
	 * @return
	 */
	protected long writeStatusLog(String devId, int statusType, long begin, long end) {
		Date newBegin = null;
		List<Period> periods = StatusUtil.splitDateByDay(new Date(begin), new Date(end));
		for (Period period : periods) {
			StatusLogWriter.insert(
					new StatusLog(devId, String.valueOf(statusType), period.getBeginTime(), period.getEndTime()));
			newBegin = period.getBeginTime();
		}

		return newBegin.getTime();
	}

}
