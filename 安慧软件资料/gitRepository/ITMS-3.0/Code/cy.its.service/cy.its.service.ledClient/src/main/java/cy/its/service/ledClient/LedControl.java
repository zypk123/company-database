package cy.its.service.ledClient;

import cy.its.service.ledClient.generated.BrightnessGroup;
import cy.its.service.ledClient.generated.DeviceInfo;
import cy.its.service.ledClient.generated.LedErrorResponse;
import cy.its.service.ledClient.generated.OnlineStatusResponse;
import cy.its.service.ledClient.generated.PowerGroup;
import cy.its.service.ledClient.generated.ScreenStatusResponse;
import cy.its.service.ledClient.proxy.TgcsInterfacePrxFactory;

public class LedControl {
	
	public static OnlineStatusResponse GetOnlineStatus(String server, DeviceInfo devInfo) throws Exception {
		return TgcsInterfacePrxFactory.getProxy(server).GetOnlineStatus(devInfo);
	}

	public static ScreenStatusResponse GetScreenStatus(String server, DeviceInfo devInfo) throws Exception {
		return TgcsInterfacePrxFactory.getProxy(server).GetScreenStatus(devInfo);
	}

	public static LedErrorResponse GetLedError(String server, DeviceInfo devInfo) throws Exception {
		return TgcsInterfacePrxFactory.getProxy(server).GetLedError(devInfo);
	}

	public static int SetLedPower(String server, DeviceInfo devInfo, int power) throws Exception {
		return TgcsInterfacePrxFactory.getProxy(server).SetLedPower(devInfo, power);
	}

	public static int SetLedPowerTimed(String server, DeviceInfo devInfo, PowerGroup[] listPower) throws Exception {
		return TgcsInterfacePrxFactory.getProxy(server).SetLedPowerTimed(devInfo, listPower);
	}

	public static int SetLedBrightness(String server, DeviceInfo devInfo, int brightness) throws Exception {
		return TgcsInterfacePrxFactory.getProxy(server).SetLedBrightness(devInfo, brightness);
	}

	public static int SetLedBrightnessTimed(String server, DeviceInfo devInfo, BrightnessGroup[] listBrightness)
			throws Exception {
		return TgcsInterfacePrxFactory.getProxy(server).SetLedBrightnessTimed(devInfo, listBrightness);
	}

	public static int SyncLedTime(String server, DeviceInfo devInfo) throws Exception {
		return TgcsInterfacePrxFactory.getProxy(server).SyncLedTime(devInfo);
	}

}
