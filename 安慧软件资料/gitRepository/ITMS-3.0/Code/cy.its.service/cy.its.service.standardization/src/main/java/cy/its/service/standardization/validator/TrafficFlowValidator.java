package cy.its.service.standardization.validator;

import cy.its.service.common.ConstValue;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataModel.TrafficFlow;

public class TrafficFlowValidator extends BaseValidator<TrafficFlow> {

	@Override
	void fillFilters() {
		addFilter(u -> StringUtil.isNullOrEmpty(u.getSiteCode()), "SiteCode不能为空;");
		addFilter(u -> dbLen(u.getSiteCode()) > ConstValue.INT_16, "SiteCode不能大于16位;");
		addFilter(u -> StringUtil.isNullOrEmpty(u.getDeviceSysNbr()), "DeviceSysNbr不能为空;");
		addFilter(u -> dbLen(u.getDeviceSysNbr()) > ConstValue.INT_18, "DeviceSysNbr不能大于18位;");
		addFilter(u -> dbLen(u.getDirectionType()) > ConstValue.INT_2, "DirectionType不能大于2位;");
		addFilter(u -> dbLen(u.getLane()) > ConstValue.INT_2, "Lane不能大于2位;");
		addFilter(u -> u.getCountTime() == null, "CountTime不可为空;");
		addFilter(u -> is1HMoreThanNow(u.getCountTime()), "CountTime时间超过当前时间1个小时,请校时;");		
		addFilter(u -> !isRightDbNum(u.getPeriod(),ConstValue.INT_6), "Period不能是整数位超过6位小数位超过0的数字;");		
		addFilter(u -> !isRightDbNum(u.getTotalNum(),ConstValue.INT_6), "TotalNum不能是整数位超过6位小数位超过0的数字;");		
		addFilter(u -> !isRightDbNum(u.getAvrSpeed(),ConstValue.INT_6,ConstValue.INT_2), "AvrSpeed不能是整数位超过4位小数位超过2的数字;");		
		addFilter(u -> !isRightDbNum(u.getAvrLength(),ConstValue.INT_6,ConstValue.INT_2), "AvrLength不能是整数位超过4位小数位超过2的数字;");		
		addFilter(u -> !isRightDbNum(u.getTimePercent(),ConstValue.INT_6,ConstValue.INT_2), "TimePercent不能是整数位超过4位小数位超过2的数字;");		
		addFilter(u -> !isRightDbNum(u.getSpacePercent(),ConstValue.INT_6,ConstValue.INT_2), "SpacePercent不能是整数位超过4位小数位超过2的数字;");		
		addFilter(u -> !isRightDbNum(u.getVehSpaceHeadway(),ConstValue.INT_6,ConstValue.INT_2), "VehSpaceHeadway不能是整数位超过4位小数位超过2的数字;");		
		addFilter(u -> !isRightDbNum(u.getVehTimeHeadway(),ConstValue.INT_6,ConstValue.INT_2), "VehTimeHeadway不能是整数位超过4位小数位超过2的数字;");		
		addFilter(u -> !isRightDbNum(u.getVehDensity(),ConstValue.INT_6,ConstValue.INT_2), "VehDensity不能是整数位超过4位小数位超过2的数字;");		
		addFilter(u -> !isRightDbNum(u.getLowSpeedVehNum(),ConstValue.INT_6,ConstValue.INT_2), "LowSpeedVehNum不能是整数位超过4位小数位超过2的数字;");		
		addFilter(u -> !isRightDbNum(u.getHighSpeedVehNum(),ConstValue.INT_6,ConstValue.INT_2), "HighSpeedVehNum不能是整数位超过4位小数位超过2的数字;");		
		addFilter(u -> !isRightDbNum(u.getLargeNum(),ConstValue.INT_6), "LargeNum不能是整数位超过6位小数位超过0的数字;");		
		addFilter(u -> !isRightDbNum(u.getMiddleNum(),ConstValue.INT_6), "MiddleNum不能是整数位超过6位小数位超过0的数字;");		
		addFilter(u -> !isRightDbNum(u.getSmallNum(),ConstValue.INT_6), "SmallNum不能是整数位超过6位小数位超过0的数字;");		
		addFilter(u -> !isRightDbNum(u.getMotorNum(),ConstValue.INT_6), "MotorNum不能是整数位超过6位小数位超过0的数字;");		
		addFilter(u -> !isRightDbNum(u.getSupperLenVehNum(),ConstValue.INT_6,ConstValue.INT_2), "SupperLenVehNum不能是整数位超过4位小数位超过2的数字;");		
		addFilter(u -> !isRightDbNum(u.getOtherNum(),ConstValue.INT_6), "OtherNum不能是整数位超过6位小数位超过0的数字;");		
		addFilter(u -> dbLen(u.getOrgPrivilegeCode()) > ConstValue.INT_16, "OrgCode不能大于16位;");
		addFilter(u -> StringUtil.isNullOrEmpty(u.getDeviceNbr()), "DeviceNbr不能为空;");
		addFilter(u -> dbLen(u.getDeviceNbr()) > ConstValue.INT_32, "DeviceNbr不能大于32位;");
	}
}
