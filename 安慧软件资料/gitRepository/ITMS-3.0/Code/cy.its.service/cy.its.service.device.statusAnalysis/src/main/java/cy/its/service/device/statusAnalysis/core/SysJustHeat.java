package cy.its.service.device.statusAnalysis.core;

import java.util.List;

import cy.its.service.common.ConstValue;
import cy.its.service.common.dataModel.ComponentStatusResult;
import cy.its.service.common.dataModel.DeviceStatus;
import cy.its.service.device.statusAnalysis.data.SurveyData;
import cy.its.service.device.statusAnalysis.model.DeviceCfg;
import cy.its.service.device.statusAnalysis.util.StatusUtil;

/**
 * 对仅发送心跳的设备进行状态分析
 * @author lijun
 *
 */
class SysJustHeat extends Sys {
	
	/**
	 * 构造函数
	 * @param cfg
	 */
	public SysJustHeat(List<DeviceCfg> cfgs){
		super(cfgs);
	}
	
	/**
	 * 定制化配置
	 */
	@Override
	void customConfig(List<DeviceCfg> cfgs) {
		this.offLinePeriod = 30 * 60 * 1000;
	}	

	/**
	 * 设备系统的最新数据上传时间
	 * 
	 * @param data
	 */
	@Override
	synchronized void receive(SurveyData data) {
		return;
	}

	/**
	 * 接收前端传送的心跳
	 * 
	 * @param status
	 */
	@Override
	synchronized void receive(DeviceStatus status) {
		int statusCode = StatusUtil.formatStatus(status.getStatus());
		int statusType = StatusUtil.getStatusType(statusCode);
		this.setStatus(System.currentTimeMillis(), statusType,
				StatusUtil.getVehicleTotal(status), statusCode, ConstValue.BOOL_FALSE);
	}

	/**
	 * 离线检测
	 */
	@Override
	synchronized void customCheck() {
		long now = System.currentTimeMillis();
		if (now - this.statusTypeUpdateTime > this.offLinePeriod) {
			this.setStatus(now, ConstValue.DEV_STATUS_TYPE_OFFLINE,
					ConstValue.INT_0, ConstValue.INT_1_MINUS, ConstValue.BOOL_FALSE);
		}
	}

	/**
	 * 返回部件
	 */
	@Override
	List<ComponentStatusResult> componentStatus() {
		// 当前设备无部件
		return null;
	}

}
