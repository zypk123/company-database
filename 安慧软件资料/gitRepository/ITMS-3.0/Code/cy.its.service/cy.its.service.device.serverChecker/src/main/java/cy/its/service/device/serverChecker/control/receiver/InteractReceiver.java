package cy.its.service.device.serverChecker.control.receiver;

import cy.its.service.common.ConstValue;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.common.log.LogManager;
import cy.its.service.common.rabbitmqClient.QueueHandler;
import cy.its.service.device.serverChecker.ifs.IServerLoader;
import cy.its.service.device.serverChecker.ifs.IServerReceiver;

@Export
public class InteractReceiver implements IServerReceiver {

	@Import
	IServerLoader serverLoader;

	@Override
	public Boolean receive(String routingKey, String message) {
		try {
			switch (routingKey) {
			case ConstValue.ROUTE_KEY_CACHECHANGE_SERVER:
				serverLoader.loadServer();
				serverLoader.publishAllStatus();
				break;
			case ConstValue.ROUTE_KEY_QUERY_REQUEST_SERVER_STATUS_RESULT:
				serverLoader.publishAllStatus();
				break;
			default:
				break;
			}
		} catch (Throwable e) {
			LogManager.error(e);
		}

		return ConstValue.BOOL_TRUE;
	}

	@Override
	public QueueHandler queueHandler() {
		return new QueueHandler("ServerChecker_Interact", false, true, new String[] {
				ConstValue.ROUTE_KEY_CACHECHANGE_SERVER, ConstValue.ROUTE_KEY_QUERY_REQUEST_SERVER_STATUS_RESULT },
				this);
	}
}
