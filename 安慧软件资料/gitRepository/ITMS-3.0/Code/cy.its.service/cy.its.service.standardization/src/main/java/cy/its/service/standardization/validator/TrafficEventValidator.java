package cy.its.service.standardization.validator;

import cy.its.service.common.ConstValue;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataModel.TrafficEvent;

public class TrafficEventValidator extends BaseValidator<TrafficEvent> {

	@Override
	void fillFilters() {
		addFilter(u -> StringUtil.isNullOrEmpty(u.getOrgCode()), "OrgCode不能为空;");
		addFilter(u -> dbLen(u.getOrgCode()) > ConstValue.INT_18, "OrgCode不能大于18位;");
		addFilter(u -> dbLen(u.getDeviceSysNbr()) > ConstValue.INT_18, "DeviceSysNbr不能大于18位;");
		addFilter(u -> dbLen(u.getSiteCode()) > ConstValue.INT_32, "SiteCode不能大于32位;");
		addFilter(u -> dbLen(u.getAlarmEventType()) > ConstValue.INT_3, "AlarmEventType不能大于3位;");
		addFilter(u -> dbLen(u.getSubAlarmEvent()) > ConstValue.INT_3, "SubAlarmEvent不能大于3位;");
		addFilter(u -> u.getStartAlarmTime() == null, "StartAlarmTime不可为空;");
		addFilter(u -> is1HMoreThanNow(u.getStartAlarmTime()), "StartAlarmTime时间超过当前时间1个小时,请校时;");
		addFilter(u -> dbLen(u.getAlarmDesc()) > ConstValue.INT_256, "AlarmDesc不能大于256位;");		
		addFilter(u -> dbLen(u.getOrgPrivilegeCode()) > ConstValue.INT_8, "OrgPrivilegeCode不能大于8位;");
	}
}
