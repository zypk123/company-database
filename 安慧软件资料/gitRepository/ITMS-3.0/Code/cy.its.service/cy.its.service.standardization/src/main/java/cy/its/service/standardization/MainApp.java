package cy.its.service.standardization;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cy.its.service.common.config.ITSConfig;
import cy.its.service.common.ice.grid.AppService;
import cy.its.service.common.ioc.AppContext;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.common.log.ILog;
import cy.its.service.common.log.LogManager;
import cy.its.service.common.rabbitmqClient.BindRelation;
import cy.its.service.common.rabbitmqClient.ExchangeInfo;
import cy.its.service.common.rabbitmqClient.ExchangeType;
import cy.its.service.common.rabbitmqClient.MQAddress;
import cy.its.service.common.rabbitmqClient.MQGateWay;
import cy.its.service.common.ConstValue;
import cy.its.service.standardization.dictionary.CacheManager;

/**
 * @Title: MainApp.java
 * @Package cy.its.service.standardization
 * @Description: TODO
 * @author STJ lijun@cychina.cn
 * @date 2015年11月3日 上午10:59:13
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */
@Export
public class MainApp extends AppService {

	
	@Import
	List<IMaker> baseMakers;

	@Import
	CacheManager cacheManager;

	static final String LogName = "MainApp";

	static ILog log;

	/**
	 * 程序入口
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LogManager.createDefaultLogger("standard~", "standardization");
			AppContext.init("cy.its.service.standardization");
			Run(AppContext.getBean(MainApp.class), args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * 服务启动处理
	 */
	public void Start(String[] args) throws Exception {
		// 初始化缓存
		cacheManager.initialize();
		// 初始化MQ
		initRabbitMQ();
		LogManager.info("启动规范化服务");
	}

	@Override
	/**
	 * 服务停止处理
	 */
	public void Stop() throws Exception {
		LogManager.info("开始停止规范化服务");
		MQGateWay.stop();
		cacheManager.close();
		LogManager.info("完成停止规范化服务");
	}

	@Override
	public String getAppName() {
		return "standardization";
	}

	/**
	 * 初始化与MQ的连接
	 * 
	 * @throws Exception
	 */
	private void initRabbitMQ() throws Exception {
		// 初始化MQ
		String mqIp = ITSConfig.findValue("rabbitMqIp");
		MQAddress mqAddress = new MQAddress(mqIp, "/", "guest", "guest");
		List<BindRelation> bindRelations = new ArrayList<BindRelation>();
		bindRelations.add(new BindRelation(new ExchangeInfo(ConstValue.EXCHANGE, true, false, ExchangeType.TOPIC),
				baseMakers.stream().map(m -> m.getQueueHandler()).collect(Collectors.toList())));

		MQGateWay.init(mqAddress, bindRelations, s -> {
			LogManager.info(s);
		} , s -> {
			LogManager.error(s);
		});
	}

//	Timer timer = new Timer();

//	private void testPublish() {
//		timer.schedule(new TimerTask() {
//
//			@Override
//			public void run() {
//				PassingVehicle pv = new PassingVehicle();
//				pv.setCaptureTime(DateUtil.formatDate(new Date()));
//				pv.setDeviceNo("530326003");
//				pv.setSnapNbr(DateUtil.formatDate(new Date()));
//				pv.setPlateNbr("皖A12345");
//				pv.setPlateColor("2");
//				pv.setLane(1);
//				pv.setDriveDirection("向西方向");
//				pv.setDriveDirection("向东方向");
//
//				boolean r = MQGateWay.publish(ConstValue.ROUTE_KEY_ORIGINAL_PASS, JsonUtil.serialize(pv));
//
//				System.out.println(r ? ("过车测试发布成功一条:" + JsonUtil.serialize(pv)) : "过车测试发布失败");
//			}
//		}, 1000, 10000);

//		pubPass();
//
//		pubVio();

//		pubFlow();
//	}

//	private void pubFlow() {
//		timer.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				TrafficStats stats = new TrafficStats();
//				 
//				Date d = new Date();
//				if (d.getSeconds() % 2 == 0) {
//					stats.setDeviceNo("WS2100202184");
//				} else {
//					stats.setDeviceNo("530326003");
//				}
//				
//				stats.setStatsTime(DateUtil.formatDate(d));
//				stats.setDriveLane(1);
//				stats.setVehicleTotal(100);
//				
//				boolean r = MQGateWay.publish(ConstValue.ROUTE_KEY_ORIGINAL_FLOW, JsonUtil.serialize(stats));
//
//				System.out.println(r ? ("流量测试发布成功一条:" + JsonUtil.serialize(stats)) : "流量测试发布失败");
//			}
//		}, 1000, 12233);
//	}
//
//	private void pubVio() {
//		timer.schedule(new TimerTask() {
//
//			@Override
//			public void run() {
//				ViolationVehicle pv = new ViolationVehicle();
//				pv.setCaptureTime(DateUtil.formatDate(new Date()));
//				pv.setDeviceNo("530326003");
//				pv.setSnapNbr(DateUtil.formatDate(new Date()));
//				pv.setPlateNbr("皖A12345");
//				pv.setPlateColor("2");
//				pv.setLane(1);
//				pv.setDriveDirection("向西方向");
//				pv.setDriveDirection("向东方向");
//				// SpeedLimit[] limits = new SpeedLimit[1];
//				SpeedLimit limit = new SpeedLimit();
//				limit.setLane(1);
//				limit.setVehicleType("01");
//				limit.setRoadOverSpeedLimit(120);
//				pv.setRoadSpeedLimits(new SpeedLimit[] { limit });
//				pv.setViolationBehaviors(new String[] { "56024" });
//
//				boolean r = MQGateWay.publish(ConstValue.ROUTE_KEY_ORIGINAL_VIO, JsonUtil.serialize(pv));
//
//				System.out.println(r ? ("违法测试发布成功一条:" + JsonUtil.serialize(pv)) : "违法测试发布失败");
//			}
//		}, 1000, 10000);
//	}
//
//	private void pubPass() {
//		timer.schedule(new TimerTask() {
//
//			@Override
//			public void run() {
//			
//				PassingVehicle pv = new PassingVehicle();
//				pv.setCaptureTime(DateUtil.formatDate(new Date()));
//				pv.setDeviceNo("530326003");
//				pv.setSnapNbr(DateUtil.formatDate(new Date()));
//				pv.setPlateNbr("皖A12345");
//				pv.setPlateColor("2");
//				pv.setLane(1);
//				pv.setDriveDirection("向西方向");
//				pv.setDriveDirection("向东方向");
//				
//				boolean r = MQGateWay.publish(ConstValue.ROUTE_KEY_ORIGINAL_PASS, 
//						JsonUtil.serialize(pv));
//				
//				System.out.println(r?("过车测试发布成功一条:"+JsonUtil.serialize(pv)):"过车测试发布失败");
//			
//				
//			}
//		}, 1000, 13123);
//	}
}
