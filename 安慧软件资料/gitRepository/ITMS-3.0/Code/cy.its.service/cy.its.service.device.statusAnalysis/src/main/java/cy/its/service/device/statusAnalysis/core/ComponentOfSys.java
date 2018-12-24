package cy.its.service.device.statusAnalysis.core;

import java.util.Date;
import java.util.List;

import cy.its.service.common.ConstValue;
import cy.its.service.common.dataModel.ComponentStatusResult;
import cy.its.service.device.statusAnalysis.data.SurveyData;
import cy.its.service.device.statusAnalysis.model.DeviceCfg;
import cy.its.service.device.statusAnalysis.util.StatusUtil;

class ComponentOfSys {
	/**
	 * 最新的心跳上传时间
	 */
	private long lastBeatTime;

	/**
	 * 最新的心跳代码
	 */
	private int lastBeatCode;

	/**
	 * 无监测数据检测周期
	 */
	private int nodataPeriod;

	private String sysComponentId;

	private String deviceNbr;

	private int offLinePeriod;

	private long statusUpdateTime;

	private int statusType;

	private List<Integer> statusDetails;

	private int vehicleTotal;

	/**
	 * 最近上传时间
	 */
	private long surveyDataUploadTime;

	/**
	 * 构造函数
	 * @param cfg
	 */
	public ComponentOfSys(DeviceCfg cfg) {
		this.initConfig(cfg);
		long now = System.currentTimeMillis();
		this.lastBeatCode = ConstValue.INT_0;
		this.lastBeatTime = now;
		this.surveyDataUploadTime = now;
		this.setStatus(now, ConstValue.DEV_STATUS_TYPE_NORMAL, ConstValue.INT_0, this.lastBeatCode, ConstValue.BOOL_TRUE);
	}

	/**
	 * 初始化配置
	 * @param cfg
	 */
	public void initConfig(DeviceCfg cfg) {
		this.offLinePeriod = 600000;
		this.nodataPeriod = 3600000;
		this.sysComponentId = cfg.getSysComponentId();
		this.deviceNbr = cfg.getDeviceNbr();
	}

	/**
	 * 接收心跳数据
	 * @param statusCode
	 * @param statusTime
	 * @param vehicleTotal
	 * @return
	 */
	public boolean receive(int statusCode, long statusTime, int vehicleTotal) {
		// 保存心跳时间
		this.lastBeatTime = statusTime;
		// 保存心跳代码
		this.lastBeatCode = StatusUtil.formatStatus(statusCode);
		// 确定当前是否异常
		int statusType = StatusUtil.offLineOrAbnormal(statusTime, this.lastBeatTime, this.surveyDataUploadTime,
				this.offLinePeriod, this.nodataPeriod);

		if (statusType == Integer.MIN_VALUE) {
			// 未检测到异常
			statusType = StatusUtil.getStatusType(this.lastBeatCode);
		}

		return this.setStatus(statusTime, statusType, vehicleTotal, this.lastBeatCode, ConstValue.BOOL_FALSE);
	}

	/**
	 * 接收监测数据
	 * @param data
	 */
	public void receive(SurveyData data) {
		this.surveyDataUploadTime = System.currentTimeMillis();
	}

	/**
	 * 自定义周期检查
	 * @param statusTime
	 * @return
	 */
	public boolean customCheck(long statusTime) {
		int statusType = StatusUtil.offLineOrAbnormal(statusTime, this.lastBeatTime, this.surveyDataUploadTime,
				this.offLinePeriod, this.nodataPeriod);

		if (statusType != Integer.MIN_VALUE) {
			return this.setStatus(statusTime, statusType, ConstValue.INT_0, ConstValue.INT_1_MINUS, ConstValue.BOOL_FALSE);
		} else {
			// 未检测到异常或离线
			if (this.statusType == ConstValue.DEV_STATUS_TYPE_ERROR ||
				this.statusType == ConstValue.DEV_STATUS_TYPE_OFFLINE) {
				// 设备当前处于异常或离线中
				// 恢复设备状态
				return this.setStatus(statusTime, StatusUtil.getStatusType(this.lastBeatCode), ConstValue.INT_0,
						this.lastBeatCode, ConstValue.BOOL_FALSE);
			}
		}

		return ConstValue.BOOL_FALSE;
	}

	/**
	 * 设置状态类型
	 * @param newStatusTime
	 * @param newStatusType
	 * @param vehicleTotal
	 * @param statusCode
	 * @param ignore
	 * @return
	 */
	private boolean setStatus(long newStatusTime, int newStatusType, int vehicleTotal, int statusCode, boolean ignore) {
		// 判断状态类型是否发生变化
		if (this.statusType != newStatusType) {
			this.statusType = newStatusType;
		} else {
			// 状态类型不变
			// 检查数据发送是否频繁
			if (!ignore && newStatusTime - this.statusUpdateTime < ConstValue.INT_60000) {
				// 连续发送的相同状态且间隔时间较短
				return ConstValue.BOOL_FALSE;
			}
		}

		this.statusUpdateTime = newStatusTime;
		this.statusDetails = StatusUtil.splitStatus(statusCode);
		this.vehicleTotal = vehicleTotal;

		return ConstValue.BOOL_TRUE;
	}

	public int getStatusType() {
		return statusType;
	}

	public int getVehicleTotal() {
		return vehicleTotal;
	}

	public ComponentStatusResult result() {
		return new ComponentStatusResult(sysComponentId, deviceNbr,
				new Date(statusUpdateTime), statusType,
				lastBeatCode, statusDetails, vehicleTotal);
	}
}
