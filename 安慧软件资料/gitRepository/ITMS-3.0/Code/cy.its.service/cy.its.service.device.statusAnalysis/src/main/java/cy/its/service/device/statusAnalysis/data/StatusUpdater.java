package cy.its.service.device.statusAnalysis.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cy.its.service.common.ConstValue;
import cy.its.service.device.statusAnalysis.model.SysStatus;

public class StatusUpdater extends Updater<SysStatus> {
	
	static StatusUpdater updater = new StatusUpdater();

	public static void update(String deviceId, int statusType, Date statusUpdateTime, Date startTime, Date endTime)
	{
		updater.write(new SysStatus(deviceId, String.valueOf(statusType), statusUpdateTime, startTime, endTime));
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
		List<Object> lstObj = new ArrayList<Object>(ConstValue.INT_3);
		lstObj.add(s.statusType);
		lstObj.add(s.statusUpdateTime);
		lstObj.add(s.deviceId);
		return lstObj;
	}

}
