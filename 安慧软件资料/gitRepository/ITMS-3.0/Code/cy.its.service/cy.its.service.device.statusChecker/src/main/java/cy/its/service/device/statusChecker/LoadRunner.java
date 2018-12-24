package cy.its.service.device.statusChecker;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import cy.its.service.common.ConstValue;
import cy.its.service.common.StringUtil;
import cy.its.service.common.config.ITSConfig;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.common.log.LogManager;
import cy.its.service.common.rabbitmqClient.BindRelation;
import cy.its.service.common.rabbitmqClient.ExchangeInfo;
import cy.its.service.common.rabbitmqClient.ExchangeType;
import cy.its.service.common.rabbitmqClient.MQAddress;
import cy.its.service.common.rabbitmqClient.MQGateWay;
import cy.its.service.device.statusChecker.data.DeviceDAO;
import cy.its.service.device.statusChecker.model.DeviceCfg;
import cy.its.service.device.statusChecker.model.StatusLog;
import cy.its.service.device.statusChecker.receiver.StatusReceiver;
import cy.its.service.device.statusChecker.util.ISysManager;

/**
 * @Title: LoadRunner.java
 * @Package cy.its.service.device.statusAnalysis
 * @Description: 状态分析服务运行管理类
 * @author STJ lijun@cychina.cn
 * @date 2015年11月4日 下午3:21:01
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 *      Company: 安徽超远信息技术有限公司 Copyright: Copyright (c) 2015
 */
@Export
public class LoadRunner {
	@Import
	ISysManager sysManager;

	@Import
	List<StatusReceiver> receivers;
	
	Boolean isStop = false;
	
	ExecutorService excutor;

	
	public void initialize() {
		try {
			String[] devTypes = sysManager.focusDeviceTypes();
			boolean loaded = false;
			while (!isStop) {
				try {
					loadDevice(devTypes);
					loaded = true;
					break;
				} catch (Throwable e) {
					LogManager.error("系统状态分析检测初始化加载失败, 稍后重新加载, 错误:" + StringUtil.getErrorDetail(e));
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}

			if (loaded && !isStop) {
				this.initMQ();
				sysManager.publishAllStatus();
			}
		} catch (Throwable e) {
			LogManager.error(e);
		}
	}

	
	void start() {
		excutor = Executors.newFixedThreadPool(1);
		excutor.submit(()->initialize());
		LogManager.info("启动状态分析服务");
	}

	void stop() {		
        LogManager.info("停止状态分析服务 开始");		
		try {
			isStop = true;
			excutor.shutdown();
			excutor.awaitTermination(Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
		}
		
		try {
			MQGateWay.stop();
		} catch (Exception e1) {
		}
		
		sysManager.stop();
		
		LogManager.info("停止状态分析服务 结束");	
	}

	private void loadDevice(String[] devTypes) throws Exception {
		List<DeviceCfg> lstDevCfg = DeviceDAO.getDeviceCfg(devTypes);
		Map<String, StatusLog> m = DeviceDAO.getDeviceStatusLog().stream()
				.filter(l -> l != null && !StringUtil.isNullOrEmpty(l.deviceId))
				.collect(Collectors.toMap(StatusLog::getDeviceId, s -> s));
		lstDevCfg.forEach(c->{
			StatusLog l = m.get(c.getDeviceSysId());
			if(l != null){
				c.setStatusType(l.statusType);
				c.setStartTime(l.startTime);
				c.setEndTime(l.endTime);
			}
		});
		
		sysManager.start(lstDevCfg);
	}
	
	void initMQ() {
		// 初始化MQ
		String mqIp = ITSConfig.findValue("rabbitMqIp");
		MQAddress mqAddress = new MQAddress(mqIp, "/", "guest", "guest");

		List<BindRelation> bindRelations = Arrays
				.asList(new BindRelation(new ExchangeInfo(ConstValue.EXCHANGE, true, false, ExchangeType.TOPIC),
						receivers.stream().map(c -> c.queueHandler()).collect(Collectors.toList())));

		try {
			MQGateWay.synInit(mqAddress, bindRelations, s -> {
				LogManager.info(s);
			} , s -> {
				LogManager.error(s);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
