package cy.its.service.device.statusChecker.data;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cy.its.service.device.statusChecker.model.SysStatus;

public class StatusUpdater extends Updater<SysStatus> {
	
	static StatusUpdater updater = new StatusUpdater();

	public static void update(String deviceId, int statusType, long statusUpdateTime)
	{
		updater.write(new SysStatus(deviceId, String.valueOf(statusType), statusUpdateTime));
	}

	StatusUpdater(){
		super("设备状态更新处理", "DeviceStatus", SysStatus.class);
	}
	
	public static void stop() {
		updater.close();
	}
	
	@Override
	String getSql() {
		return  "update t_device_sys           "+
				"   set status_type = ?,       "+
				"       status_update_time = ? "+
				" where device_id = ?          ";
	}

	@Override
	List<Object> getParamters(SysStatus s) {
		return Arrays.asList(s.statusType, new Date(s.statusUpdateTime), s.deviceId);
	}

}
