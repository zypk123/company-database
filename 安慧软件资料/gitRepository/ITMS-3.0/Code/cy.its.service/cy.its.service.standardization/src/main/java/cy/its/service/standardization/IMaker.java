package cy.its.service.standardization;

import cy.its.service.common.rabbitmqClient.IReceiver;
import cy.its.service.common.rabbitmqClient.QueueHandler;

public interface IMaker extends IReceiver {
	/**
	 * @return
	 */
	QueueHandler getQueueHandler();
}

