package cy.its.service.device.faultMaintain;

import java.util.Arrays;

import cy.its.service.common.ConstValue;
import cy.its.service.common.config.ITSConfig;
import cy.its.service.common.ice.grid.AppService;
import cy.its.service.common.log.LogManager;
import cy.its.service.common.rabbitmqClient.BindRelation;
import cy.its.service.common.rabbitmqClient.ExchangeInfo;
import cy.its.service.common.rabbitmqClient.ExchangeType;
import cy.its.service.common.rabbitmqClient.MQAddress;
import cy.its.service.common.rabbitmqClient.MQGateWay;
import cy.its.service.common.rabbitmqClient.QueueHandler;

public class MainApp extends AppService {

	/**
	 * ³ÌÐòÈë¿Ú
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LogManager.createDefaultLogger("faultMaintain", "faultMaintain");
			Run(new MainApp(), args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Start(String[] args) throws Exception {
		String mqIp = ITSConfig.findValue("rabbitMqIp");
		MQGateWay.init(new MQAddress(mqIp, "/", "guest", "guest"),
				Arrays.asList(new BindRelation(new ExchangeInfo(ConstValue.EXCHANGE, true, false, ExchangeType.TOPIC),
						Arrays.asList(
								new QueueHandler("Fault_Maintain_Status", true, false,
										ConstValue.ROUTE_KEY_STANDARD_STATUS_FOR_UPGRADE, new FaultStatusReceiver())
//								new QueueHandler("Fault_Maintain", true, false,
//								ConstValue.ROUTE_KEY_STANDARD_FAULT, new FaultReceiver())
								))),
				s -> {
					LogManager.info(s);
				} , s -> {
					LogManager.error(s);
				});
	}

	@Override
	public void Stop() throws Exception {
		MQGateWay.stop();
	}

	@Override
	public String getAppName() {
		return "deviceFaultMaintain";
	}

}
