package cy.its.service.device.statusChecker.receiver;

import cy.its.service.common.rabbitmqClient.IReceiver;
import cy.its.service.common.rabbitmqClient.QueueHandler;

public interface StatusReceiver extends IReceiver {
	QueueHandler queueHandler();
}
