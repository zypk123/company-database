package cy.its.service.device.statusAnalysis.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import cy.its.service.common.ConstValue;
import cy.its.service.common.JsonUtil;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataModel.DeviceStatus;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.log.LogManager;
import cy.its.service.common.rabbitmqClient.MQGateWay;
import cy.its.service.device.statusAnalysis.data.DeviceDAO;
import cy.its.service.device.statusAnalysis.data.StatusLogWriter;
import cy.its.service.device.statusAnalysis.data.StatusUpdater;
import cy.its.service.device.statusAnalysis.data.SurveyData;
import cy.its.service.device.statusAnalysis.model.DeviceCfg;
import cy.its.service.device.statusAnalysis.util.ISysManager;
import cy.its.service.device.statusAnalysis.util.StatusUtil;
import cy.its.service.device.statusAnalysis.util.Timer;

/**
 * @Title: SysManager.java
 * @Package cy.its.service.device.statusAnalysis.core
 * @Description: 监控系统管理类
 * @author STJ lijun@cychina.cn
 * @date 2015年11月4日 下午3:19:41
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 *      Company: 安徽超远信息技术有限公司 Copyright: Copyright (c) 2015
 */
@Export
public class SysManager implements ISysManager {

	static Map<String, Class<?>> deviceType;

	static {
		deviceType = new HashMap<String, Class<?>>();
		// 既发心跳又发监测数据的系统类型
		Class<SysDevice> sysDev = SysDevice.class;
		// 只发心跳的系统类型
		Class<SysJustHeat> sysJustHeat = SysJustHeat.class;

		// 定义设备类型与状态分析方式的对应关系
		deviceType.put("01", sysDev); // 卡口
		deviceType.put("02", sysDev); // 电警
		deviceType.put("03", sysJustHeat);// 视频
		deviceType.put("04", sysDev); // 测速设备
		deviceType.put("05", sysJustHeat);// 气象设备
		deviceType.put("06", sysJustHeat); // 可变限速牌
		deviceType.put("07", sysJustHeat);// 诱导屏
		deviceType.put("08", sysJustHeat);// 事件检测
		deviceType.put("09", sysDev); // 流量检测
		deviceType.put("10", sysJustHeat);// 短信基站
		deviceType.put("11", sysJustHeat);// 违停
		deviceType.put("12", sysJustHeat);// 大车占道
		deviceType.put("13", sysJustHeat);// 逆行
		deviceType.put("14", sysJustHeat);// 信号机
		deviceType.put("15", sysJustHeat);// 匝道口信号机
		deviceType.put("16", sysJustHeat); // 车载

	}

	Map<String, Sys> sysMap;
	Timer customChecker;
	Object sync = new Object();

	/**
	 * 启动处理
	 */
	@Override
	public void start(List<DeviceCfg> lstDevCfg) {
		Map<String, List<DeviceCfg>> map = lstDevCfg.stream()
				.collect(Collectors.groupingBy(DeviceCfg::getDeviceSysNbr));

		try {
			sysMap = new HashMap<String, Sys>(map.size());
			for (Entry<String, List<DeviceCfg>> kv : map.entrySet()) {
				createSys(kv.getKey(), kv.getValue());
			}
		} catch (Throwable e) {
			LogManager.error(e);
		}

		if (customChecker == null) {
			customChecker = new Timer(() -> {
				this.customCheck();
			} , 60000, 5000);

			customChecker.start();
		}
	}

	/**
	 * 停止处理
	 */
	@Override
	public void stop() {
		customChecker.stop();
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
	 * 接收心跳以外的监测数据
	 */
	@Override
	public void handleSurveyData(SurveyData data) {
		Sys sys = getSys(data.getDeviceSysNbr());
		if (sys != null) {
			sys.receive(data);
		}
	}

	/**
	 * 接收心跳状态处理
	 */
	@Override
	public void handleStatus(DeviceStatus heart) {
		Sys sys = getSys(heart.getDeviceSysNbr());
		if (sys != null) {
			sys.receive(heart);
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
					.collect(Collectors.groupingBy(DeviceCfg::getDeviceSysNbr));
			synchronized (sysMap) {
				Set<String> leftSysNbrs = StatusUtil.intersect(map.keySet(), sysMap.keySet());
				Set<String> newSysNbrs = StatusUtil.sutract(map.keySet(), leftSysNbrs);
				Set<String> deleteSysNbrs = StatusUtil.sutract(sysMap.keySet(), leftSysNbrs);
				for (String s : deleteSysNbrs) {
					sysMap.get(s).stop();
					sysMap.remove(s);
				}

				for (String s : newSysNbrs) {
					this.createSys(s, map.get(s));
				}

				for (String s : leftSysNbrs) {
					sysMap.get(s).change(map.get(s));
				}
			}

		} catch (Throwable e) {
			LogManager.error(e);
		}
	}

	/**
	 * 自定义周期检测
	 */
	private void customCheck() {
		try {
			Sys[] lstSys = null;
			synchronized (sysMap) {
				if (sysMap.size() > ConstValue.INT_0) {
					lstSys = sysMap.values().toArray(new Sys[ConstValue.INT_0]);
				}
			}

			if (lstSys != null) {
				for (Sys sys : lstSys) {
					sys.customCheck();
				}
			}
		} catch (Throwable e) {
			LogManager.error(StringUtil.getErrorDetail(e));
		}
	}

	/**
	 * 获取指定的系统
	 * 
	 * @param sysNbr
	 * @return
	 */
	private Sys getSys(String sysNbr) {
		synchronized (sysMap) {
			return sysMap.get(sysNbr);
		}
	}

	/**
	 * 选择性创建系统
	 * 
	 * @param deviceSysNbr
	 * @param lstCfg
	 */
	private void createSys(String deviceSysNbr, List<DeviceCfg> lstCfg) {
		if (lstCfg.size() > ConstValue.INT_1) {
			// 带组件的系统
			sysMap.put(deviceSysNbr, new SysComponents(lstCfg));
		} else if (lstCfg.size() == ConstValue.INT_1) {
			DeviceCfg cfg = lstCfg.get(ConstValue.INT_0);
			if (!StringUtil.isNullOrEmpty(cfg.getDeviceNbr())) {
				// 带组件的系统
				sysMap.put(deviceSysNbr, new SysComponents(lstCfg));
			} else {
				Class<?> clz = deviceType.get(cfg.getDeviceType());
				if (clz != null) {
					if (clz == SysDevice.class) {
						sysMap.put(deviceSysNbr, new SysDevice(lstCfg));
					} else if (clz == SysJustHeat.class) {
						sysMap.put(deviceSysNbr, new SysJustHeat(lstCfg));
					}
				}
			}
		}
	}

}
