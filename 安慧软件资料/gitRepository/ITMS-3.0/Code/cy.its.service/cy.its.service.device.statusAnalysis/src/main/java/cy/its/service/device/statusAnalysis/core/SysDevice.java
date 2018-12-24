package cy.its.service.device.statusAnalysis.core;

import java.util.List;

import cy.its.service.common.ConstValue;
import cy.its.service.common.dataModel.ComponentStatusResult;
import cy.its.service.common.dataModel.DeviceStatus;
import cy.its.service.device.statusAnalysis.data.SurveyData;
import cy.its.service.device.statusAnalysis.model.DeviceCfg;
import cy.its.service.device.statusAnalysis.util.StatusUtil;

/**
 * 对既有心跳又有其它检测数据的且无组件的设备系统进行状态分析
 * 
 * @author STJ
 *
 */
public class SysDevice extends Sys {

	/**
	 * 最新的心跳上传时间
	 */
	private long lastBeatTime;

	/**
	 * 最新的心跳代码
	 */
	private int lastBeatCode;

	/**
	 * 无检测数据检测周期
	 */
	private int nodataPeriod;

	public SysDevice(List<DeviceCfg> cfgs) {
		super(cfgs);
	}

	@Override
	void customConfig(List<DeviceCfg> cfgs) {
		this.offLinePeriod = 5 * 60 * 1000;
		this.nodataPeriod = 30 * 60 * 1000;
		this.lastBeatTime = System.currentTimeMillis();
		this.surveyDataUploadTime = this.lastBeatTime;
	}

	/**
	 * 设备系统的最新数据上传时间
	 * 
	 * @param data
	 */
	@Override
	synchronized void receive(SurveyData data) {
		this.surveyDataUploadTime = System.currentTimeMillis();
	}

	/**
	 * 接收前端传送的心跳
	 * 
	 * @param status
	 */
	@Override
	synchronized void receive(DeviceStatus status) {
		long now = System.currentTimeMillis();
		// 保存心跳时间
		this.lastBeatTime = now;
		// 保存心跳代码
		this.lastBeatCode = StatusUtil.formatStatus(status.getStatus());
		// 确定当前是否异常
		int statusType = StatusUtil.offLineOrAbnormal(now, this.lastBeatTime, this.surveyDataUploadTime,
				this.offLinePeriod, this.nodataPeriod);

		if (statusType == Integer.MIN_VALUE) {
			// 未检测到异常
			statusType = StatusUtil.getStatusType(this.lastBeatCode);
		}

		this.setStatus(now, statusType, StatusUtil.getVehicleTotal(status), this.lastBeatCode, ConstValue.BOOL_FALSE);
	}

	/**
	 * 离线检测
	 */
	@Override
	synchronized void customCheck() {
		long now = System.currentTimeMillis();
		// 确定当前是否异常
		int statusType = StatusUtil.offLineOrAbnormal(now, 
				this.lastBeatTime, this.surveyDataUploadTime,
				this.offLinePeriod, this.nodataPeriod);

		if (statusType != Integer.MIN_VALUE) {
			// 检测到异常或离线
			this.setStatus(now, statusType, ConstValue.INT_0, ConstValue.INT_1_MINUS, ConstValue.BOOL_FALSE);
		} else {
			// 未检测到异常或离线
			if (this.statusType == ConstValue.DEV_STATUS_TYPE_ERROR
					|| this.statusType == ConstValue.DEV_STATUS_TYPE_OFFLINE) {
				// 设备当前处于异常或离线中
				// 恢复设备状态
				this.setStatus(now, StatusUtil.getStatusType(this.lastBeatCode), 
						ConstValue.INT_0, this.lastBeatCode, ConstValue.BOOL_FALSE);
			}
		}
	}

	@Override
	List<ComponentStatusResult> componentStatus() {
		return null;
	}
}
