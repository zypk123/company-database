package cy.its.service.standardization.validator;

import cy.its.service.common.ConstValue;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataModel.DeviceFault;

public class DeviceFaultValidator extends BaseValidator<DeviceFault> {

	@Override
	void fillFilters() {
		addFilter(u -> StringUtil.isNullOrEmpty(u.getDeviceId()), "DeviceId不能为空;");
		addFilter(u -> dbLen(u.getDeviceId()) > ConstValue.INT_32, "DeviceId不能大于32位;");
		addFilter(u -> u.getFaultEventTime() == null, "FaultEventTime不可为空;");
		addFilter(u -> is1HMoreThanNow(u.getFaultEventTime()), "FaultEventTime时间超过当前时间1个小时,请校时;");
		addFilter(u -> dbLen(u.getFaultSubType()) > ConstValue.INT_4, "FaultSubType不能大于4位;");
		addFilter(u -> dbLen(u.getFaultAlarm()) > ConstValue.INT_1, "FaultAlarm不能大于1位;");
		addFilter(u -> dbLen(u.getFaultType()) > ConstValue.INT_2, "FaultType不能大于2位;");
		addFilter(u -> dbLen(u.getCollectMethod()) > ConstValue.INT_1, "CollectMethod不能大于1位;");
		addFilter(u -> dbLen(u.getRemark()) > ConstValue.INT_256, "Remark不能大于256位;");		
		addFilter(u -> dbLen(u.getSysComponentId()) > ConstValue.INT_32, "SysComponentId不能大于32位;");
		addFilter(u -> StringUtil.isNullOrEmpty(u.getOrgPrivilegeCode()), "OrgPrivilegeCode不能为空;");
		addFilter(u -> dbLen(u.getOrgPrivilegeCode()) > ConstValue.INT_8, "OrgPrivilegeCode不能大于8位;");
	}
}
