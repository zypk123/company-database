package cy.its.service.ledClient;

import cy.its.service.ledClient.generated.TgcsDevice;
import cy.its.service.ledClient.proxy.DeviceMgrPrxFactory;

public class LedManager {
	
	public static int AddDevice(String server, TgcsDevice[] listDev) throws Exception {
		return DeviceMgrPrxFactory.getProxy(server).AddDevice(listDev);
	}
	
	public static int ModifyDevice(String server,TgcsDevice[] listDev) throws Exception {
		return DeviceMgrPrxFactory.getProxy(server).ModifyDevice(listDev);
	}
	
	public static int DeleteDevice(String server,TgcsDevice[] listDev) throws Exception {
		return DeviceMgrPrxFactory.getProxy(server).DeleteDevice(listDev);
	}
	
	public static TgcsDevice[] GetDevices(String server) throws Exception {
		return DeviceMgrPrxFactory.getProxy(server).GetDevices();
	}
}
