package cy.its.device.domain.repository.led;

import cy.its.device.domain.model.Sys;
import cy.its.device.domain.model.led.Led;
import cy.its.device.domain.model.led.LedSpec;

public interface ILedRemoteManageRepository {

	boolean addLedDevice(String ip, Sys sys, Led led, LedSpec spec) throws Exception;
	void modifyLedDevice(String ip, Sys sys, Led led, LedSpec spec) throws Exception;
	boolean deleteLedDevice(String ip, String deviceSysNbr) throws Exception;

}
