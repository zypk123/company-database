package cy.its.device.repository.led;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cy.its.device.domain.model.led.LedPowerGroup;
import cy.its.device.domain.repository.led.ILedControlRepository;
import cy.its.service.ledClient.LedControl;
import cy.its.service.ledClient.LedManager;
import cy.its.service.ledClient.generated.DeviceInfo;
import cy.its.service.ledClient.generated.PowerGroup;
import cy.its.service.ledClient.generated.TgcsDevice;

@Service
public class LedControlRepository implements ILedControlRepository {

	@Override
	public boolean setLedPower(String serverIp, String deviceNo, boolean onOrOff) throws Exception {
		return LedControl.SetLedPower(serverIp, getDevInRemote(serverIp, deviceNo), onOrOff ? 1 : 2) == 0;
	}

	@Override
	public boolean setLedPowerTimed(String serverIp, String deviceNo, List<LedPowerGroup> lstPowerGroup)
			throws Exception {
		PowerGroup[] lstP = lstPowerGroup.stream().map(c -> new PowerGroup(c.startHour,
				c.startMinute, c.endHour, c.endMinute)).collect(Collectors.toList())
				.toArray(new PowerGroup[0]);
		int retCode = LedControl.SetLedPowerTimed(serverIp, getDevInRemote(serverIp, deviceNo), lstP) ;
		return retCode == 0;
	}

	@Override
	public boolean setLedBrightness(String serverIp, String deviceNo, int brightness) throws Exception {
		return LedControl.SetLedBrightness(serverIp, getDevInRemote(serverIp, deviceNo), brightness) == 0;
	}

	@Override
	public boolean syncLedTime(String serverIp, String deviceNo) throws Exception {
		return LedControl.SyncLedTime(serverIp, getDevInRemote(serverIp, deviceNo)) == 0;
	}

	private DeviceInfo getDevInRemote(String serverIp, String deviceNo) throws Exception {
		if (!isExistedLedInRemote(serverIp, deviceNo)) {
			throw new Exception("当前设备在LED服务器不存在,无法进行反控！");
		}

		DeviceInfo dev = new DeviceInfo();
		dev.devNo = deviceNo;
		return dev;
	}

	/**
	 * 根据设备编号获取远程服务器上的LED设备
	 * 
	 * @param ip
	 * @param deviceNo
	 * @return
	 * @throws Exception
	 */
	private boolean isExistedLedInRemote(String ip, String deviceNo) throws Exception {
		TgcsDevice[] tgcsDevArr = LedManager.GetDevices(ip);
		if (tgcsDevArr != null && tgcsDevArr.length > 0) {
			for (TgcsDevice d : tgcsDevArr) {
				if (deviceNo.equals(d.devNo)) {
					return true;
				}
			}
		}

		return false;
	}

}
