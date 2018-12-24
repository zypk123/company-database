package cy.its.service.data2db;

import cy.its.service.common.rabbitmqClient.IReceiver;
import cy.its.service.common.rabbitmqClient.QueueHandler;

public interface IMaker extends IReceiver {
	QueueHandler getQueueHandler();
	void  close();
}
