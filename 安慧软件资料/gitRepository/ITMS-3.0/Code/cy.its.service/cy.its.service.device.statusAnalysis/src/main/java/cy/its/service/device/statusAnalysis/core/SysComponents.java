package cy.its.service.device.statusAnalysis.core;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import cy.its.service.common.ConstValue;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataModel.ComponentStatusResult;
import cy.its.service.common.dataModel.DeviceStatus;
import cy.its.service.device.statusAnalysis.data.SurveyData;
import cy.its.service.device.statusAnalysis.model.DeviceCfg;
import cy.its.service.device.statusAnalysis.util.StatusUtil;

/**
 * 带组件的系统且组件接收心跳和监测数据
 * @author STJ
 *
 */
class SysComponents extends Sys {

	Map<String, ComponentOfSys> comMap;

	SysComponents(List<DeviceCfg> cfgs) {
		super(cfgs);
	}

	@Override
	void customConfig(List<DeviceCfg> cfgs) {
		this.configComponents(cfgs);
	}

	/**
	 * 接收监测数据
	 */
	@Override
	synchronized void receive(SurveyData data) {
		if (this.comMap != null) {
			ComponentOfSys com = this.comMap.get(data.getDeviceNbr());
			if (com != null) {
				com.receive(data);
			}
		}
	}

	/**
	 * 接收设备心跳
	 */
	@Override
	synchronized void receive(DeviceStatus status) {
		if (this.comMap != null) {
			// 设置带部件的系统状态
			ComponentOfSys com = this.comMap.get(status.getDeviceNbr());
			if (com != null) {
				long now = System.currentTimeMillis();
				if (com.receive(status.getStatus(), now, StatusUtil.getVehicleTotal(status))) {
					setSysStatusByComp(now);
				}
			}
		}
	}

	/**
	 * 自定义检测
	 */
	@Override
	synchronized void customCheck() {
		if (this.comMap != null) {
			// 带组件的系统
			boolean seted = ConstValue.BOOL_FALSE;
			long now = System.currentTimeMillis();
			for (Entry<String, ComponentOfSys> kv : this.comMap.entrySet()) {
				if (kv.getValue().customCheck(now)) {
					seted = ConstValue.BOOL_TRUE;
				}
			}

			if (seted) {
				setSysStatusByComp(now);
			}
		}
	}

	/**
	 * 获取组件状态列表
	 */
	@Override
	List<ComponentStatusResult> componentStatus() {
		if (this.comMap != null) {
			return this.comMap.values().stream().map(c -> c.result()).collect(Collectors.toList());
		}

		return null;
	}

	/**
	 * 配置系统组件
	 * 
	 * @param cfgs
	 * @return
	 */
	private Boolean configComponents(List<DeviceCfg> cfgs) {
		boolean hasChange = ConstValue.BOOL_FALSE;
		List<DeviceCfg> comCfgs = cfgs.stream().filter(c -> !StringUtil.isNullOrEmpty(c.getDeviceNbr()))
				.collect(Collectors.toList());
		if (comCfgs.size() > ConstValue.INT_0) {
			if (this.comMap == null) {
				hasChange = ConstValue.BOOL_TRUE;
				this.comMap = comCfgs.stream()
						.collect(Collectors.toMap(DeviceCfg::getDeviceNbr, c -> new ComponentOfSys(c)));
			} else {
				// 新的组件列表映射成map
				Map<String, DeviceCfg> mapCfg = comCfgs.stream()
						.collect(Collectors.toMap(DeviceCfg::getDeviceNbr, c -> c));

				// 获取已存在的组件列表
				String[] keys = this.comMap.keySet().toArray(new String[0]);
				for (String key : keys) {
					if (mapCfg.containsKey(key)) {
						// 仍存在的组件,修改其配置信息
						this.comMap.get(key).initConfig(mapCfg.get(key));
						mapCfg.remove(key);
					} else {
						// 清除不存在的组件
						hasChange = ConstValue.BOOL_TRUE;
						this.comMap.remove(key);
					}
				}

				// 添加新增加组件的状态分析
				for (Entry<String, DeviceCfg> kv : mapCfg.entrySet()) {
					hasChange = ConstValue.BOOL_TRUE;
					this.comMap.put(kv.getKey(), new ComponentOfSys(kv.getValue()));
				}
			}
		} else {
			// 当前系统组件已被清空
			if (this.comMap != null) {
				hasChange = ConstValue.BOOL_TRUE;
				this.comMap = null;
			}
		}
		return hasChange;
	}

	/**
	 * 根据组件的状态确定系统状态
	 * 
	 * @param statusTime
	 */
	private void setSysStatusByComp(long statusTime) {
		int statusType = ConstValue.DEV_STATUS_TYPE_NORMAL;
		int vTotal = ConstValue.INT_0;
		int normal = ConstValue.INT_0, fault = ConstValue.INT_0, offline = ConstValue.INT_0, error = ConstValue.INT_0;
		for (Entry<String, ComponentOfSys> kv : this.comMap.entrySet()) {
			vTotal += kv.getValue().getVehicleTotal();
			switch (kv.getValue().getStatusType()) {
			case ConstValue.DEV_STATUS_TYPE_NORMAL:
				normal++;
				break;
			case ConstValue.DEV_STATUS_TYPE_OFFLINE:
				offline++;
				break;
			case ConstValue.DEV_STATUS_TYPE_FAULT:
				fault++;
				break;
			case ConstValue.DEV_STATUS_TYPE_ERROR:
				error++;
				break;
			default:
				break;
			}
		}

		int csize = this.comMap.size();
		if (csize == normal) {
			statusType = ConstValue.DEV_STATUS_TYPE_NORMAL;
		} else if (csize == offline) {
			statusType = ConstValue.DEV_STATUS_TYPE_OFFLINE;
		} else if (csize == fault) {
			statusType = ConstValue.DEV_STATUS_TYPE_FAULT;
		} else if (csize == error) {
			statusType = ConstValue.DEV_STATUS_TYPE_ERROR;
		} else if (csize == offline) {
			statusType = ConstValue.DEV_STATUS_TYPE_OFFLINE;
		} else {
			statusType = ConstValue.DEV_STATUS_TYPE_FAULT;
		}

		this.setStatus(statusTime, statusType, vTotal, ConstValue.INT_1_MINUS, ConstValue.BOOL_FALSE);
	}
}
