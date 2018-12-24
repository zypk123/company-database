package cy.its.service.data2db.SingleData;

import java.util.List;

import cy.its.service.common.ConstValue;
import cy.its.service.common.dataModel.DeviceAlarm;
import cy.its.service.common.ioc.Export;

@Export
public class DeviceAlarmSingleMaker extends SingleMaker<DeviceAlarm> {

	public DeviceAlarmSingleMaker() {
		super(DeviceAlarm.class, "T_DEVICE_ALARM", "Data2DB_DeviceAlarm",
				ConstValue.ROUTE_KEY_ALARM_DEVICE_BEFORE_TODB,
				ConstValue.ROUTE_KEY_ALARM_DEVICE, "设备服务器报警");
	}

	@Override
	void extendCols(List<String[]> lst) {
		lst.add(new String[] { "CREATE_TIME", "SYSDATE" });  // 创建时间		
	}
}
