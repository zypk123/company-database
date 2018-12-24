package cy.its.service.standardization.dataMaker;

import cy.its.service.common.ConstValue;
import cy.its.service.common.DateUtil;
import cy.its.service.common.dataModel.DeviceStatus;
import cy.its.service.standardization.dataMaker.originalModel.SurveyDeviceStatus;
import cy.its.service.standardization.dictionary.DeviceCache;
import cy.its.service.standardization.dictionary.model.DeviceInfo;
import cy.its.service.standardization.validator.BaseValidator;

//@Export
public class DeviceStatusMaker extends DataMaker<SurveyDeviceStatus, DeviceStatus> {

//	@Import
	DeviceCache deviceCache;

	public DeviceStatusMaker() {
		super("Éè±¸ÐÄÌø", SurveyDeviceStatus.class, DeviceStatus.class, ConstValue.ROUTE_KEY_ORIGINAL_STATUS,
				ConstValue.ROUTE_KEY_STANDARD_STATUS, "original_device_status", false);
	}

	@Override
	DeviceStatus translate(SurveyDeviceStatus input) {
		DeviceInfo dInfo = deviceCache.get(input.getDeviceNo());
		if (dInfo != null) {
			DeviceStatus status = new DeviceStatus();
			status.setDeviceSysNbr(dInfo.getSysNbr());
			status.setDeviceNbr(dInfo.getDeviceNbr());
			status.setStartTime(DateUtil.parseDate(input.getStartTime()));
//			status.setDeviceTime(DateUtil.parseDate(input.getDeviceTime()).getTime());
			status.setStatusTime(status.getStatusTime());
			status.setOtherStatusInfo(input.getOtherStatusInfo());
			status.setStatsInfo(input.getStatsInfo());
			status.setFaultDetail(input.getFaultDetail());
			status.setStatus(input.getStatus());
			input = null;
			dInfo = null;
			return status;
		}
		return null;
	}

	@Override
	BaseValidator<DeviceStatus> validator() {
		return null;
	}
}
