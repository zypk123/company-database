package cy.its.device.domain.repository.led;

import java.util.List;

import cy.its.device.domain.model.led.LedPowerGroup;

public interface ILedControlRepository {

	boolean setLedPower(String serverIp, String deviceNo, boolean onOrOff) throws Exception;

	boolean setLedPowerTimed(String serverIp, String deviceNo, List<LedPowerGroup> lstPowerGroup) throws Exception;

	boolean setLedBrightness(String serverIp, String deviceNo, int brightness) throws Exception;

	boolean syncLedTime(String serverIp, String deviceNo) throws Exception;

}
