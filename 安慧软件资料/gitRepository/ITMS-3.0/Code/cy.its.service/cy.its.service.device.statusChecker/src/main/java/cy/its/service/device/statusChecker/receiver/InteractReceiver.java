package cy.its.service.device.statusChecker.receiver;

import cy.its.service.common.ConstValue;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.common.log.LogManager;
import cy.its.service.common.rabbitmqClient.QueueHandler;
import cy.its.service.device.statusChecker.util.ISysManager;

@Export
public class InteractReceiver implements StatusReceiver {

	@Import
	ISysManager sysManger;

	@Override
	public Boolean receive(String routingKey, String message) {
		try {
			switch (routingKey) {
			case ConstValue.ROUTE_KEY_CACHECHANGE_ORG:
			case ConstValue.ROUTE_KEY_CACHECHANGE_SITE:
			case ConstValue.ROUTE_KEY_CACHECHANGE_DEVICESYS:
				sysManger.checkChangeOfSysCfg();
				sysManger.publishAllStatus();
				break;
			case ConstValue.ROUTE_KEY_QUERY_REQUEST_STATUS_RESULT:
				sysManger.publishAllStatus();
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
		return new QueueHandler("StatusChecker_Interact", false, true,
				new String[] { ConstValue.ROUTE_KEY_CACHECHANGE_ORG, ConstValue.ROUTE_KEY_CACHECHANGE_SITE,
						ConstValue.ROUTE_KEY_CACHECHANGE_DEVICESYS, ConstValue.ROUTE_KEY_QUERY_REQUEST_STATUS_RESULT },
				this);
	}

}
