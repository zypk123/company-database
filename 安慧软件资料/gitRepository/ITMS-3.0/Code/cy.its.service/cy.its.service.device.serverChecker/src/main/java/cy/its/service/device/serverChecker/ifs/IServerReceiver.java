package cy.its.service.device.serverChecker.ifs;

import cy.its.service.common.rabbitmqClient.IReceiver;
import cy.its.service.common.rabbitmqClient.QueueHandler;

public interface IServerReceiver extends IReceiver {
	QueueHandler queueHandler();
}
