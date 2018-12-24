package cy.its.service.device.statusChecker.receiver;

import cy.its.service.common.ConstValue;
import cy.its.service.common.JsonUtil;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataModel.SurveyUpgrade_DeviceStatus;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.common.log.LogManager;
import cy.its.service.common.rabbitmqClient.QueueHandler;
import cy.its.service.device.statusChecker.util.ISysManager;

@Export
public class DeviceHeartReceiver implements StatusReceiver {

	@Import
	ISysManager sysManager;
	Class<SurveyUpgrade_DeviceStatus> clazz = SurveyUpgrade_DeviceStatus.class;
	String fmt = "处理设备状态失败,接收数据:%s,失败原因: %s";
	
	@Override
	public Boolean receive(String routingKey, String message) {
		try {
			SurveyUpgrade_DeviceStatus status = JsonUtil.parseObject(message, clazz);
			if (status != null && !StringUtil.isNullOrEmpty(status.getDeviceSysNbr())
					) {
				// && !StatusUtil.isOldData(status.getDeviceTime())
				sysManager.handleStatus(status);
			}
			status = null;
		} catch (Throwable e) {
			LogManager.error(String.format(fmt, message, StringUtil.getErrorDetail(e)));
		}

		return ConstValue.BOOL_TRUE;
	}

	@Override
	public QueueHandler queueHandler() {
		return new QueueHandler("StatusChecker_Status", false, true,
				new String[] { ConstValue.ROUTE_KEY_STANDARD_STATUS_FOR_UPGRADE }, this);
	}
}
