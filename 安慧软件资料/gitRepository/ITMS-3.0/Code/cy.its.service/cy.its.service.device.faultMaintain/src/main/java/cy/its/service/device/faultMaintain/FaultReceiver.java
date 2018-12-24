package cy.its.service.device.faultMaintain;

import cy.its.service.common.JsonUtil;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataModel.DeviceFault;
import cy.its.service.common.log.LogManager;
import cy.its.service.common.rabbitmqClient.IReceiver;

public class FaultReceiver implements IReceiver {

	Class<DeviceFault> clazz = DeviceFault.class;
	String msg = "接收到故障:%s"; 

	@Override
	public Boolean receive(String routingKey, String message) {
		try {
			LogManager.debug(String.format(msg, message));
			return FaultWriter.write(JsonUtil.parseObject(message, clazz));
		} catch (Throwable e) {
			LogManager.error("故障处理失败:" + StringUtil.getErrorDetail(e));
			return true;
		}
	}

}
