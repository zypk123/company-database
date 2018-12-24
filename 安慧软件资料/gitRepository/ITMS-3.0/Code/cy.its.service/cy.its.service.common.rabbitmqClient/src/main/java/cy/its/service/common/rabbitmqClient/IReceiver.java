package cy.its.service.common.rabbitmqClient;

@FunctionalInterface
public interface IReceiver {

	/**
	 * 订阅接收MQ发送的消息
	 * @param routingKey 队列关键字
	 * @param message    消息
	 * @return 是否确认收到消息, true:是;false:否;
	 */
	Boolean receive(String routingKey, String message);
	
//	Boolean receive(String queue, String routingKey, String message);

}
