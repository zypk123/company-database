package cy.its.service.device.statusAnalysis.receiver;

import cy.its.service.common.ConstValue;
import cy.its.service.common.JsonUtil;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataModel.DeviceStatus;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.common.log.LogManager;
import cy.its.service.common.rabbitmqClient.QueueHandler;
import cy.its.service.device.statusAnalysis.util.ISysManager;

@Export
public class DeviceHeartReceiver implements StatusReceiver {

	@Import
	ISysManager sysManager;

	Class<DeviceStatus> clazz = DeviceStatus.class;

	@Override
	public Boolean receive(String routingKey, String message) {
		try {
			DeviceStatus status = JsonUtil.parseObject(message, clazz);
			if (status != null && !StringUtil.isNullOrEmpty(status.getDeviceSysNbr())) {
				sysManager.handleStatus(status);
			}
			status = null;
		} catch (Throwable e) {
			LogManager.error(e);
		}

		return ConstValue.BOOL_TRUE;
	}

	@Override
	public QueueHandler queueHandler() {
		return new QueueHandler("StatusAnalysis_Heart", false, true,
				new String[] { ConstValue.ROUTE_KEY_STANDARD_STATUS }, this);
	}
}
