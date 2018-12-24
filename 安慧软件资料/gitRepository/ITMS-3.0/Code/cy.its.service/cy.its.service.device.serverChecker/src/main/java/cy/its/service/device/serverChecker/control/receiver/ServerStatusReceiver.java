package cy.its.service.device.serverChecker.control.receiver;

import cy.its.service.common.ConstValue;
import cy.its.service.common.JsonUtil;
import cy.its.service.common.dataModel.SurveyUpgrade_ServerStatus;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.common.rabbitmqClient.QueueHandler;
import cy.its.service.device.serverChecker.ifs.IServerManager;
import cy.its.service.device.serverChecker.ifs.IServerReceiver;

@Export
public class ServerStatusReceiver implements IServerReceiver {

	@Import
	IServerManager serverManager;

	Class<SurveyUpgrade_ServerStatus> clazz = SurveyUpgrade_ServerStatus.class;

	@Override
	public Boolean receive(String routingKey, String message) {
		if (ConstValue.ROUTE_KEY_STANDARD_SERVER_STATUS_FOR_UPGRADE.equals(routingKey)) {
			serverManager.receiveServerStatus(JsonUtil.parseObject(message, clazz));
		}

		return ConstValue.BOOL_TRUE;
	}

	@Override
	public QueueHandler queueHandler() {
		return new QueueHandler("ServerChecker_Server_Status", false, true,
				new String[] { ConstValue.ROUTE_KEY_STANDARD_SERVER_STATUS_FOR_UPGRADE },
				this);
	}

}
