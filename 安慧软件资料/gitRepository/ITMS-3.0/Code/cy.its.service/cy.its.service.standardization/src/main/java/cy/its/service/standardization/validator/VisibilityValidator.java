package cy.its.service.standardization.validator;

import cy.its.service.common.ConstValue;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataModel.Visibility;

public class VisibilityValidator extends BaseValidator<Visibility>{

	@Override
	void fillFilters() {
		addFilter(u -> StringUtil.isNullOrEmpty(u.getDeviceSysNbr()), "DeviceSysNbr不能为空;");
		addFilter(u -> dbLen(u.getDeviceSysNbr()) > ConstValue.INT_18, "DeviceSysNbr不能大于18位;");
		addFilter(u -> dbLen(u.getOrgCode()) > ConstValue.INT_18, "OrgCode不能大于18位;");
		addFilter(u -> dbLen(u.getSiteCode()) > ConstValue.INT_32, "SiteCode不能大于32位;");
		addFilter(u -> !isRightDbNum(u.getOneMinuteVisibility(),ConstValue.INT_10), "OneMinuteVisibility不能是整数位超过10位小数位超过0的数字;");
		addFilter(u -> !isRightDbNum(u.getTenMinuteVisibility(),ConstValue.INT_10), "TenMinuteVisibility不能是整数位超过10位小数位超过0的数字;");
		addFilter(u -> !isRightDbNum(u.getCleanDegre(),ConstValue.INT_10), "CleanDegre不能是整数位超过10位小数位超过0的数字;");
		addFilter(u -> !isRightDbNum(u.getCaseTemperature(),ConstValue.INT_10), "CaseTemperature不能是整数位超过10位小数位超过0的数字;");
		addFilter(u -> dbLen(u.getPowerVolatage()) > ConstValue.INT_8, "PowerVolatage不能大于8位;");
		addFilter(u -> u.getRecordTime() == null, "RecordTime不可为空;");
		addFilter(u -> is1HMoreThanNow(u.getRecordTime()), "RecordTime时间超过当前时间1个小时,请校时;");
		addFilter(u -> dbLen(u.getOrgPrivilegeCode()) > ConstValue.INT_8, "OrgPrivilegeCode不能大于8位;");
	}
}
