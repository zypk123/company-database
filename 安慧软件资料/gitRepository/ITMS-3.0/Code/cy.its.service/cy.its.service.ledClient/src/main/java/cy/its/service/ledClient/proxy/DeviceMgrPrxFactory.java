package cy.its.service.ledClient.proxy;

import Ice.ObjectPrx;
import cy.its.service.common.StringUtil;
import cy.its.service.ledClient.generated.TgcsDeviceMgrPrx;
import cy.its.service.ledClient.generated.TgcsDeviceMgrPrxHelper;

public class DeviceMgrPrxFactory extends ProxyFactory<TgcsDeviceMgrPrx> {

	private static ProxyFactory<TgcsDeviceMgrPrx> proxy = new DeviceMgrPrxFactory();

	private DeviceMgrPrxFactory() {
	}

	@Override
	protected TgcsDeviceMgrPrx createPrx(ObjectPrx objectPrx) {
		return TgcsDeviceMgrPrxHelper.uncheckedCast(objectPrx);
	}

	@Override
	String identity() {
		return "DeviceManagement";
	}

	@Override
	int port() {
		return 35201;
	}

	public static TgcsDeviceMgrPrx getProxy(String server) throws Exception {
		if (StringUtil.isNullOrEmpty(server)) {
			throw new Exception("服务器地址不能为空");
		}
		
		return proxy.getPrx(server);
	}

}
