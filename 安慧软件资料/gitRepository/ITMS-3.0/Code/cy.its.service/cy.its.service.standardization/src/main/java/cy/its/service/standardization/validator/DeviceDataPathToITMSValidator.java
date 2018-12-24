package cy.its.service.standardization.validator;

import cy.its.service.common.ConstValue;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataModel.DeviceDataPathToITMS;

public class DeviceDataPathToITMSValidator extends BaseValidator<DeviceDataPathToITMS> {

	@Override
	void fillFilters() {
		addFilter(u -> StringUtil.isNullOrEmpty(u.getSnapNbr()), "SnapNbr不能为空;");
		addFilter(u -> dbLen(u.getSnapNbr()) > ConstValue.INT_32, "SnapNbr不能大于32位;");
		addFilter(u -> u.getPassTime() == null, "PassTime不可为空;");
		addFilter(u -> u.getItmsTime() == null, "ItmsTime不可为空;");
		addFilter(u -> !isRightDbNum(u.getTotalTime(), ConstValue.INT_12, ConstValue.INT_2),
				"TotalTime不能是整数位超过10位小数位超过2的数字;");
		addFilter(u -> dbLen(u.getDeviceKey()) > ConstValue.INT_64, "DeviceKey不能大于64位;");
		addFilter(u -> dbLen(u.getSysComponentId()) > ConstValue.INT_32, "SysComponentId不能大于32位;");
		addFilter(u -> StringUtil.isNullOrEmpty(u.getDeviceId()), "DeviceId不能为空;");
		addFilter(u -> dbLen(u.getDeviceId()) > ConstValue.INT_32, "DeviceId不能大于32位;");
		addFilter(u -> StringUtil.isNullOrEmpty(u.getOrgPrivilegeCode()), "OrgPrivilegeCode不能为空;");
		addFilter(u -> dbLen(u.getOrgPrivilegeCode()) > ConstValue.INT_8, "OrgPrivilegeCode不能大于8位;");
	}

}
