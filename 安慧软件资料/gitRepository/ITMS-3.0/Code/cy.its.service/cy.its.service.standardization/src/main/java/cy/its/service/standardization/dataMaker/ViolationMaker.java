package cy.its.service.standardization.dataMaker;

import cy.its.service.common.dataModel.Violation;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;

import cy.its.service.common.ConstValue;
import cy.its.service.common.StringUtil;
import cy.its.service.standardization.dataMaker.originalModel.ViolationVehicle;
import cy.its.service.standardization.dictionary.RegionCache;
import cy.its.service.standardization.dictionary.SurveyUpgrade_DeviceCache;
import cy.its.service.standardization.dictionary.model.DeviceRegion;
import cy.its.service.standardization.dictionary.model.IDeviceInfo;
import cy.its.service.standardization.util.vioConverter.RegionVioConverter;
import cy.its.service.standardization.util.vioConverter.SiteVioConverter;
import cy.its.service.standardization.validator.BaseValidator;
import cy.its.service.standardization.validator.ViolationValidator;

@Export
public class ViolationMaker extends DataMaker<ViolationVehicle, Violation> {

//	@Import
//	DeviceCache deviceCache;

	@Import
	RegionCache regionCache;

	@Import
	SiteVioConverter siteVioConverter;

	@Import
	RegionVioConverter regionVioConverter;

	@Import
	SurveyUpgrade_DeviceCache surveyUpgrade_DeviceCache;

	public ViolationMaker() {
		super("违法", ViolationVehicle.class, Violation.class, ConstValue.ROUTE_KEY_ORIGINAL_VIO,
				ConstValue.ROUTE_KEY_STANDARD_VIO, "original_vio_vehicle");
	}

	String STR_ENTRYLOCATION = "entryLocation";
	String STR_REGION_UNREG = "区间信息未注册";
	
	@Override
	Violation translate(ViolationVehicle v) {
		if (v.getExtendedProperties() != null) {
			if (v.getExtendedProperties().containsKey(STR_ENTRYLOCATION)) {
				// 区间违法
				DeviceRegion dRegion = regionCache.get(v.getDeviceNo());
				if (dRegion != null) {
					return regionVioConverter.convert(v, dRegion);
				} else {
					v.validateResult = STR_REGION_UNREG;
				}
			} else {
				// 单点违法
				String devKey = v.getExtendedProperties().get(ConstValue.DEVICE_KEY);
				if (!StringUtil.isNullOrEmpty(devKey)) {
					IDeviceInfo dInfo = surveyUpgrade_DeviceCache.get(devKey);
					if (dInfo != null) {
						return siteVioConverter.convert(v, dInfo);
					} else {
						v.validateResult = STR_DEVICE_UNREG;
					}
				} else {
					v.validateResult = STR_EXT_NOKEY;
				}
			}
		} else {
			v.validateResult = STR_EXT_NULL;
		}

		return null;
	}

	@Override
	BaseValidator<Violation> validator() {
		return new ViolationValidator();
	}

	IDeviceInfo getDeviceInfo(ViolationVehicle v) {
		String devKey = getMapValue(ConstValue.DEVICE_KEY, v.getExtendedProperties());
		if (!StringUtil.isNullOrEmpty(devKey)) {
			v.validateResult = "扩展属性不包含deviceID";
			return null;
		}
		return surveyUpgrade_DeviceCache.get(devKey);
	}
}