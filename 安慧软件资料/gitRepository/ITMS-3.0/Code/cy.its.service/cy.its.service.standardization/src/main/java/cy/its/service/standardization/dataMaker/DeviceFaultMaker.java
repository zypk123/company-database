package cy.its.service.standardization.dataMaker;

import cy.its.service.common.ConstValue;
import cy.its.service.common.DateUtil;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataModel.DeviceFault;
import cy.its.service.standardization.dataMaker.originalModel.SurveyDeviceFault;
import cy.its.service.standardization.dictionary.DeviceCache;
import cy.its.service.standardization.dictionary.model.DeviceInfo;
import cy.its.service.standardization.validator.BaseValidator;
import cy.its.service.standardization.validator.DeviceFaultValidator;

//@Export
public class DeviceFaultMaker extends DataMaker<SurveyDeviceFault, DeviceFault> {

//	@Import
	DeviceCache deviceCache;

	public DeviceFaultMaker() {
		super("设备故障", SurveyDeviceFault.class, DeviceFault.class, ConstValue.ROUTE_KEY_ORIGINAL_FAULT,
				ConstValue.ROUTE_KEY_STANDARD_FAULT, "original_device_fault", false);
	}

	/**
	 * 故障报警类型 ： 1 故障
	 */
	String FAULT = "1";
	/**
	 * 采集方式。1-前端设备上传
	 */
	String COLLECT_METHOD = "1";
	
	@Override
	DeviceFault translate(SurveyDeviceFault input) {
		DeviceInfo d = deviceCache.get(input.getDeviceNo());

		if (d != null) {
			DeviceFault fault = new DeviceFault();
			fault.setDeviceId(d.getDeviceId());
			fault.setSysComponentId(d.getSysComponentId());
			fault.setFaultSubType(String.valueOf(input.getFaultCode()));
			String faultSubType = StringUtil.padLeft(fault.getFaultSubType(), ConstValue.INT_3, ConstValue.CHAR_ZERO);
			fault.setFaultType(faultSubType.substring(ConstValue.INT_0, faultSubType.length() - ConstValue.INT_2));
			fault.setFaultEventTime(DateUtil.parseDate(input.getFaultTime()));
			fault.setRemark(input.getFaultDetail());
			fault.setFaultAlarm(FAULT);
			fault.setCollectMethod(COLLECT_METHOD);	
			fault.setOrgPrivilegeCode(d.getOrgPrivilegeCode());
			return fault;
		}

		return null;
	}

	@Override
	BaseValidator<DeviceFault> validator() {
		return new DeviceFaultValidator();
	}

}
