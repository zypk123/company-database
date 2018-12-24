package cy.its.service.data2db;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cy.its.service.common.config.ITSConfig;
import cy.its.service.common.ice.grid.AppService;
import cy.its.service.common.ioc.AppContext;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.common.log.LogManager;
import cy.its.service.common.rabbitmqClient.BindRelation;
import cy.its.service.common.rabbitmqClient.ExchangeInfo;
import cy.its.service.common.rabbitmqClient.ExchangeType;
import cy.its.service.common.rabbitmqClient.MQAddress;
import cy.its.service.common.rabbitmqClient.MQGateWay;
import cy.its.service.common.ConstValue;

@Export
public class MainApp extends AppService {

	@Import
	List<IMaker> makers;

	/**
	 * 程序入口
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LogManager.createDefaultLogger("data2db", "data2db");
			AppContext.init("cy.its.service.data2db");
			Run(AppContext.getBean(MainApp.class), args);
		} catch (Throwable e) {
			LogManager.error(e);
		}
	}

	@Override
	public void Start(String[] args) throws Exception {
		// 初始化MQ
		String mqIp = ITSConfig.findValue("rabbitMqIp");
		MQAddress mqAddress = new MQAddress(mqIp, "/", "guest", "guest");
		List<BindRelation> bindRelations = Arrays.asList(new BindRelation(new ExchangeInfo(ConstValue.EXCHANGE, true, false, ExchangeType.TOPIC),
				makers.stream().map(m -> m.getQueueHandler()).collect(Collectors.toList())));

		MQGateWay.init(mqAddress, bindRelations, s -> {
			LogManager.info(s);
		} , s -> {
			LogManager.error(s);
		});

		LogManager.info("启动入库程序");
	}

	@Override
	public void Stop() throws Exception {
		LogManager.info("开始停止入库程序");
		if (makers != null) {
			for (IMaker iMaker : makers) {
				iMaker.close();
			}
		}
		LogManager.info("完成停止入库程序");
	}

	@Override
	public String getAppName() {
		return "data2db";
	}

}
