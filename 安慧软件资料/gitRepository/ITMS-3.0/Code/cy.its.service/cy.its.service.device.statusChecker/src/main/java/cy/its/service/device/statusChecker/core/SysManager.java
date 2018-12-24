package cy.its.service.device.statusChecker.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import cy.its.service.common.ConstValue;
import cy.its.service.common.JsonUtil;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataModel.SurveyUpgrade_DeviceStatus;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.log.LogManager;
import cy.its.service.common.rabbitmqClient.MQGateWay;
import cy.its.service.device.statusChecker.data.DeviceDAO;
import cy.its.service.device.statusChecker.data.StatusLogWriter;
import cy.its.service.device.statusChecker.data.StatusUpdater;
import cy.its.service.device.statusChecker.model.DeviceCfg;
import cy.its.service.device.statusChecker.model.SurveyData;
import cy.its.service.device.statusChecker.util.ISysManager;

@Export
public class SysManager implements ISysManager {

	static Map<String, Class<?>> deviceType;

	static {
		deviceType = new HashMap<String, Class<?>>();

		// 只发心跳的系统类型
		Class<SysJustHeat> sysJustHeat = SysJustHeat.class;

		// 定义设备类型与状态分析方式的对应关系
		deviceType.put("01", sysJustHeat); // 卡口
		deviceType.put("02", sysJustHeat); // 电警
		deviceType.put("03", sysJustHeat);// 视频
		deviceType.put("04", sysJustHeat); // 测速设备
		deviceType.put("05", sysJustHeat);// 气象设备
		deviceType.put("06", sysJustHeat); // 可变限速牌
		deviceType.put("07", sysJustHeat);// 诱导屏
		deviceType.put("08", sysJustHeat);// 事件检测
		deviceType.put("09", sysJustHeat); // 流量检测
		deviceType.put("10", sysJustHeat);// 短信基站
		deviceType.put("11", sysJustHeat);// 违停
		deviceType.put("12", sysJustHeat);// 大车占道
		deviceType.put("13", sysJustHeat);// 逆行
		deviceType.put("14", sysJustHeat);// 信号机
		deviceType.put("15", sysJustHeat);// 匝道口信号机
		deviceType.put("16", sysJustHeat); // 车载

	}

	Map<String, Sys> sysMap;

	/**
	 * 启动处理
	 */
	@Override
	public void start(List<DeviceCfg> lstDevCfg) {
		Map<String, List<DeviceCfg>> map = lstDevCfg.stream().collect(Collectors.groupingBy(DeviceCfg::getDeviceSysId));

		try {
			sysMap = new HashMap<String, Sys>(map.size());
			for (Entry<String, List<DeviceCfg>> kv : map.entrySet()) {
				createSys(kv.getKey(), kv.getValue());
			}
		} catch (Throwable e) {
			LogManager.error(e);
		}

	}

	/**
	 * 停止处理
	 */
	@Override
	public void stop() {
		synchronized (sysMap) {
			for (Sys sys : sysMap.values()) {
				sys.stop();
			}
			sysMap.clear();
		}

		StatusUpdater.stop();
		StatusLogWriter.stop();
	}

	/**
	 * 获取关注的设备类型
	 */
	@Override
	public String[] focusDeviceTypes() {
		return deviceType.keySet().toArray(new String[ConstValue.INT_0]);
	}

	/**
	 * 接收心跳状态处理
	 * 
	 * @throws Exception
	 */
	@Override
	public void handleStatus(SurveyUpgrade_DeviceStatus heart) throws Exception {
		Sys sys = getSys(heart.getDeviceSysId());
		if (sys != null) {
			sys.receive(heart);
		}
	}
	

	@Override
	public void receiveSurveyData(SurveyData surveyData) {
		Sys sys = getSys(surveyData.getDeviceId());
		if (sys != null) {
			Long dataTime = null;
			if (surveyData.getPassTime() != null) {
				dataTime = surveyData.getPassTime().getTime();
			} else if (surveyData.getViolationTime() != null) {
				dataTime = surveyData.getViolationTime().getTime();
			} else if (surveyData.getCountTime() != null) {
				dataTime = surveyData.getCountTime().getTime();
			} else if (surveyData.getRecordTime() != null) {
				dataTime = surveyData.getRecordTime().getTime();
			}

			sys.setLastUploadTime(surveyData.getSysComponentId(), dataTime);
		}
	}

	/**
	 * 发布所有系统的状态
	 */
	@Override
	public void publishAllStatus() {
		List<Sys> lst;
		synchronized (sysMap) {
			lst = sysMap.values().stream().collect(Collectors.toList());
		}

		MQGateWay.publish(ConstValue.ROUTE_KEY_QUERY_RESULT_STATUS_RESULT,
				JsonUtil.serialize(lst.stream().map(c -> c.generateResult()).collect(Collectors.toList())));
	}

	/**
	 * 检测系统和组件的配置变更
	 */
	@Override
	public void checkChangeOfSysCfg() {
		try {
			List<DeviceCfg> lstDevCfg = DeviceDAO.getDeviceCfg(this.focusDeviceTypes());
			Map<String, List<DeviceCfg>> map = lstDevCfg.stream()
					.collect(Collectors.groupingBy(DeviceCfg::getDeviceSysId));
			synchronized (sysMap) {
				List<String> lstOldId = sysMap.keySet().stream().collect(Collectors.toList());
				for (String oldId : lstOldId) {
					if (map.containsKey(oldId)) {
						configExistDevice(map, oldId);
						// sysMap.get(oldId).change(map.remove(oldId));
					} else {
						sysMap.remove(oldId).stop();
					}
				}
				lstOldId.clear();
				for (Entry<String, List<DeviceCfg>> kv : map.entrySet()) {
					this.createSys(kv.getKey(), kv.getValue());
				}
			}

		} catch (Throwable e) {
			LogManager.error(e);
		}
	}

	/**
	 * 获取指定的系统
	 * 
	 * @param sysNbr
	 * @return
	 */
	private Sys getSys(String sysId) {
		synchronized (sysMap) {
			return sysMap.get(sysId);
		}
	}

	/**
	 * 选择性创建系统
	 * 
	 * @param deviceSysNbr
	 * @param lstCfg
	 */
	private void createSys(String deviceSysId, List<DeviceCfg> lstCfg) {
		if (lstCfg.size() > ConstValue.INT_1) {
			// 带组件的系统
			sysMap.put(deviceSysId, new SysComponents(lstCfg));
		} else if (lstCfg.size() == ConstValue.INT_1) {
			DeviceCfg cfg = lstCfg.get(ConstValue.INT_0);
			if (!StringUtil.isNullOrEmpty(cfg.getCameraId())) {
				// 带组件的系统
				sysMap.put(deviceSysId, new SysComponents(lstCfg));
			} else {
				Class<?> clz = deviceType.get(cfg.getDeviceType());
				if (clz != null && clz == SysJustHeat.class) {
					sysMap.put(deviceSysId, new SysJustHeat(lstCfg));
				}
			}
		}
	}
	
	/**
	 * 配置变更
	 * @param map
	 * @param oldId
	 */
	private void configExistDevice(Map<String, List<DeviceCfg>> map, String oldId) {
		List<DeviceCfg> newCfgs = map.remove(oldId);
		Sys sys = sysMap.get(oldId);
		if (newCfgs.size() > ConstValue.INT_1
				|| !StringUtil.isNullOrEmpty(newCfgs.get(ConstValue.INT_0).getCameraId())) {
			if (sys instanceof SysComponents) {
				sys.change(newCfgs);
			} else {
				sys.stop();
				sysMap.put(oldId, new SysComponents(newCfgs));
			}
		} else {
			if (sys instanceof SysComponents) {
				sys.stop();
				sysMap.put(oldId, new SysJustHeat(newCfgs));
			} else {
				sys.change(newCfgs);
			}
		}
	}
}
