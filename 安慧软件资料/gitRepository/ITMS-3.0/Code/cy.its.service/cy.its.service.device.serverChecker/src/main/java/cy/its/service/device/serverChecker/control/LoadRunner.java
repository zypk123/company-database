package cy.its.service.device.serverChecker.control;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
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
import cy.its.service.device.serverChecker.ifs.ILoadRunner;
import cy.its.service.device.serverChecker.ifs.IServerLoader;
import cy.its.service.device.serverChecker.ifs.IServerReceiver;

@Export
public class LoadRunner implements ILoadRunner {

	@Import
	IServerLoader serverLoader;

	@Import
	List<IServerReceiver> receivers;

	ExecutorService excutor;
	boolean isStop = false;
	ScheduledExecutorService service;

	@Override
	public void Start() {
		excutor = Executors.newFixedThreadPool(1);
		excutor.submit(() -> initialize());
		LogManager.info("启动状态分析服务");
	}

	@Override
	public void Stop() {
		isStop = true;
		try {
			excutor.shutdown();
			excutor.awaitTermination(Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (service != null) {
			try {
				service.shutdown();
				service.awaitTermination(Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void initMQ() {
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

	private void initialize() {
		try {
			if (loadOnAndOn() && !isStop) {
				this.initMQ();
				serverLoader.publishAllStatus();
				service = Executors.newScheduledThreadPool(1);
				service.scheduleWithFixedDelay(() -> {
					serverLoader.periodCheckStatus();
				} , 10000, 5000, TimeUnit.MILLISECONDS);
			}
		} catch (Throwable e) {
			LogManager.error(e);
		}
	}

	private boolean loadOnAndOn() {
		while (!isStop) {
			try {
				serverLoader.loadServer();
				return true;
			} catch (Throwable e) {
				LogManager.error("系统状态分析检测初始化加载失败, 稍后重新加载, 错误:" + StringUtil.getErrorDetail(e));
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					LogManager.error(e1);
				}
			}
		}

		return false;
	}
}