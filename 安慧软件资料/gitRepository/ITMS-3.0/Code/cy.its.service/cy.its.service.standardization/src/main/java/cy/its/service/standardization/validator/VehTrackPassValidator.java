package cy.its.service.standardization.validator;


import cy.its.service.common.dataModel.VehTrackPass;
import cy.its.service.common.ConstValue;
import cy.its.service.common.StringUtil;

public class VehTrackPassValidator extends BaseValidator<VehTrackPass> {

	@Override
	void fillFilters() {
		addFilter(u -> StringUtil.isNullOrEmpty(u.getSnapNbr()), "SnapNbr不能为空;");
		addFilter(u -> StringUtil.isNullOrEmpty(u.getDeviceSysNbr()), "DeviceSysNbr不能为空;");
		addFilter(u -> dbLen(u.getDeviceSysNbr()) > ConstValue.INT_18, "DeviceSysNbr不能大于18位;");
		addFilter(u -> dbLen(u.getSnapNbr()) > ConstValue.INT_32, "SnapNbr不能大于32位;");
		addFilter(u -> StringUtil.isNullOrEmpty(u.getDeviceNbr()), "DeviceNbr不能为空;");
		addFilter(u -> dbLen(u.getDeviceNbr()) > ConstValue.INT_18, "DeviceNbr不能大于18位;");
		addFilter(u -> dbLen(u.getDistrictCode()) > ConstValue.INT_6, "DistrictCode不能大于6位;");
		addFilter(u -> StringUtil.isNullOrEmpty(u.getSiteCode()), "SiteCode不能为空;");
		addFilter(u -> dbLen(u.getSiteCode()) > ConstValue.INT_16, "SiteCode不能大于16位;");
		addFilter(u -> StringUtil.isNullOrEmpty(u.getRoadCode()), "RoadCode不能为空;");
		addFilter(u -> dbLen(u.getRoadCode()) > ConstValue.INT_6, "RoadCode不能大于6位;");
		addFilter(u -> StringUtil.isNullOrEmpty(u.getRoadType()), "RoadType不能为空;");
		addFilter(u -> dbLen(u.getRoadType()) > ConstValue.INT_2, "RoadType不能大于2位;");
		addFilter(u -> dbLen(u.getRoadSectionCode()) > ConstValue.INT_4, "RoadSectionCode不能大于4位;");
		addFilter(u -> StringUtil.isNullOrEmpty(u.getDirectionName()), "DirectionName不能为空;");
		addFilter(u -> dbLen(u.getDirectionName()) > ConstValue.INT_128, "DirectionName不能大于128位;");
		addFilter(u -> StringUtil.isNullOrEmpty(u.getDirectionType()), "DirectionType不能为空;");
		addFilter(u -> dbLen(u.getDirectionType()) > ConstValue.INT_2, "DirectionType不能大于2位;");
		addFilter(u -> dbLen(u.getLane()) > ConstValue.INT_2, "Lane不能大于2位;");
		addFilter(u -> dbLen(u.getPlateNbr()) > ConstValue.INT_16, "PlateNbr不能大于16位;");
		addFilter(u -> dbLen(u.getPlateColor()) > ConstValue.INT_2, "PlateColor不能大于2位;");
		addFilter(u -> dbLen(u.getPlateType()) > ConstValue.INT_2, "PlateType不能大于2位;");
		addFilter(u -> dbLen(u.getTailPlateNbr()) > ConstValue.INT_16, "TailPlateNbr不能大于16位;");
		addFilter(u -> dbLen(u.getTailPlateColor()) > ConstValue.INT_2, "TailPlateColor不能大于2位;");
		addFilter(u -> dbLen(u.getVehicleBrand()) > ConstValue.INT_32, "VehicleBrand不能大于32位;");
		addFilter(u -> dbLen(u.getVehicleSubBrand()) > ConstValue.INT_32, "VehicleSubBrand不能大于32位;");
		addFilter(u -> dbLen(u.getVehicleType()) > ConstValue.INT_3, "VehicleType不能大于3位;");
		addFilter(u -> dbLen(u.getVehCharcter()) > ConstValue.INT_2, "VehCharcter不能大于2位;");
		addFilter(u -> dbLen(u.getVehicleShape()) > ConstValue.INT_2, "VehicleShape不能大于2位;");
		addFilter(u -> !isRightDbNum(u.getVehicleLength(), ConstValue.INT_4, ConstValue.INT_2), "VehicleLength不能是整数位超过2位小数位超过2的数字;");
		addFilter(u -> dbLen(u.getVehicleColor()) > ConstValue.INT_5, "VehicleColor不能大于5位;");
		addFilter(u -> u.getPassTime() == null, "PassTime不可为空;");
		addFilter(u -> is1HMoreThanNow(u.getPassTime()), "PassTime时间超过当前时间1个小时,请校时;");
		addFilter(u -> !isRightDbNum(u.getSpeed(), ConstValue.INT_5), "Speed不能是整数位超过5位小数位超过0的数字;");
		addFilter(u -> dbLen(u.getDriveMode()) > ConstValue.INT_4, "DriveMode不能大于4位;");
		addFilter(u -> dbLen(u.getVehLocalization()) > ConstValue.INT_1, "VehLocalization不能大于1位;");
		addFilter(u -> dbLen(u.getVehCategory()) > ConstValue.INT_1, "VehCategory不能大于1位;");
		addFilter(u -> dbLen(u.getOrgCode()) > ConstValue.INT_32, "OrgCode不能大于32位;");
		addFilter(u -> dbLen(u.getOrgAuthorityCode()) > ConstValue.INT_32, "OrgAuthorityCode不能大于32位;");
		addFilter(u -> dbLen(u.getImageUrlPath()) > ConstValue.INT_256, "ImageUrlPath不能大于256位;");
		addFilter(u -> dbLen(u.getImageUrl1()) > ConstValue.INT_128, "ImageUrl1不能大于128位;");
		addFilter(u -> dbLen(u.getImageUrl2()) > ConstValue.INT_128, "ImageUrl2不能大于128位;");
		addFilter(u -> dbLen(u.getImageUrl3()) > ConstValue.INT_128, "ImageUrl3不能大于128位;");
		addFilter(u -> dbLen(u.getVehiclePlatePlace()) > ConstValue.INT_32, "VehiclePlatePlace不能大于32位;");
		addFilter(u -> dbLen(u.getFacePlace()) > ConstValue.INT_32, "FacePlace不能大于32位;");
		addFilter(u -> !isRightDbNum(u.getTransferDelay(), ConstValue.INT_10), "TransferDelay不能是整数位超过10位小数位超过0的数字;");
		addFilter(u -> dbLen(u.getHighwayAccessFlag()) > ConstValue.INT_1, "HighwayAccessFlag不能大于1位;");
		addFilter(u -> dbLen(u.getServiceAreaAccessFlag()) > ConstValue.INT_1, "ServiceAreaAccessFlag不能大于1位;");
		addFilter(u -> dbLen(u.getErtranceOrExit()) > ConstValue.INT_1, "ErtranceOrExit不能大于1位;");
	}
}
