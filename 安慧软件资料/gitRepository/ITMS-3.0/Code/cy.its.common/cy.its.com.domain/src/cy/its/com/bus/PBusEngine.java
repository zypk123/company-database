package cy.its.com.bus;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cy.its.com.constant.ConstValue;
import cy.its.com.util.Config;
import cy.its.com.util.StringUtil;
import cy.its.service.common.rabbitmqClient.BindRelation;
import cy.its.service.common.rabbitmqClient.ExchangeInfo;
import cy.its.service.common.rabbitmqClient.ExchangeType;
import cy.its.service.common.rabbitmqClient.IReceiver;
import cy.its.service.common.rabbitmqClient.MQAddress;
import cy.its.service.common.rabbitmqClient.MQGateWay;
import cy.its.service.common.rabbitmqClient.QueueHandler;

class PBusEngine implements IBusEngine, IReceiver {
	Receiver EventAction;

	ExecutorService threadPool = Executors.newCachedThreadPool();

	String identity = StringUtil.generateUUID();

	@Override
	public void register(Receiver eventAction) {
		EventAction = eventAction;
	}

	@Override
	public void send(Event event) {
		this.receive(event);
		MQGateWay.publish(event.getTopic(), this.identity);
	}

	@Override
	public void receive(Event event) {
		EventAction.accept(event);
	}

	@Override
	public Boolean receive(String routingKey, String message) {
		if (!this.identity.equals(message)) {
			receive(new Event(routingKey, ""));
		}

		return true;
	}

	@Override
	public void start() {
		try {
			String mqIp = Config.value("rabbitMqIp");
			String vHost = "/";
			String user = "guest";
			String passWord = "guest";

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
			MQGateWay.init(new MQAddress(mqIp, vHost, user, passWord), Arrays.asList(
					new BindRelation(new ExchangeInfo(ConstValue.EXCHANGE, true, false, ExchangeType.TOPIC), Arrays.asList(
							new QueueHandler("Domain_" + sdf.format(new Date()), false, true, "CacheChange.*", this)))));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop() {
		try {
			MQGateWay.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}