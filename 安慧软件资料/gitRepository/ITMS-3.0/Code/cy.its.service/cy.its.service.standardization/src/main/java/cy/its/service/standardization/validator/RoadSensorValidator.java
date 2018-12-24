package cy.its.service.standardization.validator;

import cy.its.service.common.ConstValue;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataModel.RoadSensor;

public class RoadSensorValidator extends BaseValidator<RoadSensor> {

	@Override
	void fillFilters() {
		addFilter(u -> StringUtil.isNullOrEmpty(u.getDeviceSysNbr()), "DeviceSysNbr不能为空;");
		addFilter(u -> dbLen(u.getDeviceSysNbr()) > ConstValue.INT_18, "DeviceSysNbr不能大于18位;");
		addFilter(u -> dbLen(u.getOrgCode()) > ConstValue.INT_18, "OrgCode不能大于18位;");
		addFilter(u -> dbLen(u.getSiteCode()) > ConstValue.INT_32, "SiteCode不能大于32位;");
		addFilter(u -> !isRightDbNum(u.getRoadTemperature(),ConstValue.INT_4,ConstValue.INT_2), "RoadTemperature不能是整数位超过2位小数位超过2的数字;");
		addFilter(u -> !isRightDbNum(u.getRoadbedTemperature(),ConstValue.INT_4,ConstValue.INT_2), "RoadbedTemperature不能是整数位超过2位小数位超过2的数字;");
		addFilter(u -> !isRightDbNum(u.getWaterFileHeigh(),ConstValue.INT_4,ConstValue.INT_2), "WaterFileHeigh不能是整数位超过2位小数位超过2的数字;");
		addFilter(u -> !isRightDbNum(u.getSalinity(),ConstValue.INT_4,ConstValue.INT_2), "Salinity不能是整数位超过2位小数位超过2的数字;");
		addFilter(u -> !isRightDbNum(u.getFreezingTemperature(),ConstValue.INT_4,ConstValue.INT_2), "FreezingTemperature不能是整数位超过2位小数位超过2的数字;");
		addFilter(u -> dbLen(u.getRoadCondition()) > ConstValue.INT_2, "RoadCondition不能大于2位;");
		addFilter(u -> u.getRecordTime() == null, "RecordTime不可为空;");
		addFilter(u -> is1HMoreThanNow(u.getRecordTime()), "RecordTime时间超过当前时间1个小时,请校时;");
		addFilter(u -> dbLen(u.getOrgPrivilegeCode()) > ConstValue.INT_8, "OrgPrivilegeCode不能大于8位;");
	}
}
