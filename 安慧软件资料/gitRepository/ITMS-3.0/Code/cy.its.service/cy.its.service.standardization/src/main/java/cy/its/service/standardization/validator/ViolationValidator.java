package cy.its.service.standardization.validator;

import cy.its.service.common.dataModel.Violation;
import cy.its.service.common.ConstValue;
import cy.its.service.common.StringUtil;

public class ViolationValidator extends BaseValidator<Violation> {

	@Override
	void fillFilters() {		
		addFilter(u -> dbLen(u.getVehicleRegId()) > ConstValue.INT_32, "VehicleRegId不能大于32位;");
		addFilter(u -> StringUtil.isNullOrEmpty(u.getDeviceSysNbr()), "DeviceSysNbr不能为空;");
		addFilter(u -> dbLen(u.getDeviceSysNbr()) > ConstValue.INT_32, "DeviceSysNbr不能大于32位;");
		addFilter(u -> dbLen(u.getSnapNbr()) > ConstValue.INT_32, "SnapNbr不能大于32位;");
		addFilter(u -> StringUtil.isNullOrEmpty(u.getOrgCode()), "OrgCode不能为空;");
		addFilter(u -> dbLen(u.getOrgCode()) > ConstValue.INT_32, "OrgCode不能大于32位;");
		addFilter(u -> dbLen(u.getOrgAuthorityCode()) > ConstValue.INT_32, "OrgAuthorityCode不能大于32位;");
		addFilter(u -> StringUtil.isNullOrEmpty(u.getCollectionType()), "CollectionType不能为空;");
		addFilter(u -> dbLen(u.getCollectionType()) > ConstValue.INT_1, "CollectionType不能大于1位;");
		addFilter(u -> StringUtil.isNullOrEmpty(u.getDistrictCode()), "DistrictCode不能为空;");
		addFilter(u -> dbLen(u.getDistrictCode()) > ConstValue.INT_6, "DistrictCode不能大于6位;");
		addFilter(u -> StringUtil.isNullOrEmpty(u.getVioSiteCode()), "VioSiteCode不能为空;");
		addFilter(u -> dbLen(u.getVioSiteCode()) > ConstValue.INT_16, "VioSiteCode不能大于16位;");
		addFilter(u -> StringUtil.isNullOrEmpty(u.getAddressDesc()), "AddressDesc不能为空;");
		addFilter(u -> dbLen(u.getAddressDesc()) > ConstValue.INT_128, "AddressDesc不能大于128位;");
		addFilter(u -> StringUtil.isNullOrEmpty(u.getRoadCode()), "RoadCode不能为空;");
		addFilter(u -> dbLen(u.getRoadCode()) > ConstValue.INT_6, "RoadCode不能大于6位;");
		addFilter(u -> dbLen(u.getRoadSectionCode()) > ConstValue.INT_4, "RoadSectionCode不能大于4位;");
		addFilter(u -> !isRightDbNum(u.getMileage(), ConstValue.INT_4), "Mileage不能是整数位超过4位小数位超过0的数字;");
		addFilter(u -> dbLen(u.getDirectionType()) > ConstValue.INT_2, "DirectionType不能大于2位;");
		addFilter(u -> dbLen(u.getDirectionName()) > ConstValue.INT_32, "DirectionName不能大于32位;");
		addFilter(u -> dbLen(u.getLaneNbr()) > ConstValue.INT_2, "LaneNbr不能大于2位;");
		addFilter(u -> StringUtil.isNullOrEmpty(u.getPlateNbr()), "PlateNbr不能为空;");
		addFilter(u -> dbLen(u.getPlateNbr()) > ConstValue.INT_16, "PlateNbr不能大于16位;");
		addFilter(u -> StringUtil.isNullOrEmpty(u.getPlateType()), "PlateType不能为空;");
		addFilter(u -> dbLen(u.getPlateType()) > ConstValue.INT_2, "PlateType不能大于2位;");
		addFilter(u -> StringUtil.isNullOrEmpty(u.getPlateColor()), "PlateColor不能为空;");
		addFilter(u -> dbLen(u.getPlateColor()) > ConstValue.INT_2, "PlateColor不能大于2位;");
		addFilter(u -> u.getViolationTime() == null, "ViolationTime不可为空;");
		addFilter(u -> is1HMoreThanNow(u.getViolationTime()), "ViolationTime时间超过当前时间1个小时,请校时;");
		addFilter(u -> StringUtil.isNullOrEmpty(u.getViolationType()), "ViolationType不能为空;");
		addFilter(u -> dbLen(u.getViolationType()) > ConstValue.INT_2, "ViolationType不能大于2位;");
		addFilter(u -> dbLen(u.getViolationCode()) > ConstValue.INT_8, "ViolationCode不能大于8位;");
		addFilter(u -> dbLen(u.getViolationDesc()) > ConstValue.INT_128, "ViolationDesc不能大于128位;");
		addFilter(u -> !isRightDbNum(u.getSpeed(), ConstValue.INT_5), "Speed不能是整数位超过5位小数位超过0的数字;");
		addFilter(u -> !isRightDbNum(u.getLimitLarge(),ConstValue.INT_10), "LimitLarge不能是整数位超过10位小数位超过0的数字;");
		addFilter(u -> !isRightDbNum(u.getLimitSmall(),ConstValue.INT_10), "LimitSmall不能是整数位超过10位小数位超过0的数字;");
        addFilter(u -> !isRightDbNum(u.getLimitLower(),ConstValue.INT_5), "LimitLower不能是整数位超过5位小数位超过0的数字;");
		addFilter(u -> !isRightDbNum(u.getOverSpeedPercent(),ConstValue.INT_5), "OverSpeedPercent不能是整数位超过5位小数位超过0的数字;");
		addFilter(u -> dbLen(u.getEntrySiteCode()) > ConstValue.INT_12, "EntrySiteCode不能大于12位;");
		addFilter(u -> is1HMoreThanNow(u.getRegionEntryTime()), "RegionEntryTime时间超过当前时间1个小时,请校时;");
		addFilter(u -> !isRightDbNum(u.getRegionDistance(),ConstValue.INT_10), "RegionDistance不能是整数位超过10位小数位超过0的数字;");
		addFilter(u -> is1HMoreThanNow(u.getRedLightBeginTime()), "RedLightBeginTime时间超过当前时间1个小时,请校时;");
		addFilter(u -> is1HMoreThanNow(u.getRedLightEndTime()), "RedLightEndTime时间超过当前时间1个小时,请校时;");
		addFilter(u -> dbLen(u.getSpeedingType()) > ConstValue.INT_1, "SpeedingType不能大于1位;");
		addFilter(u -> dbLen(u.getSpecialVehType()) > ConstValue.INT_1, "SpecialVehType不能大于1位;");
		addFilter(u -> dbLen(u.getImage()) > ConstValue.INT_1024, "Image不能大于1024位;");
		addFilter(u -> dbLen(u.getVideo()) > ConstValue.INT_1024, "Video不能大于1024位;");
		addFilter(u -> dbLen(u.getRemark()) > ConstValue.INT_256, "Remark不能大于256位;");
		addFilter(u -> dbLen(u.getVehLocalization()) > ConstValue.INT_1, "VehLocalization不能大于1位;");
		addFilter(u -> dbLen(u.getDeviceNbr()) > ConstValue.INT_32, "DeviceNbr不能大于32位;");
		addFilter(u -> dbLen(u.getCrossCode()) > ConstValue.INT_12, "CrossCode不能大于12位;");
	}
}
