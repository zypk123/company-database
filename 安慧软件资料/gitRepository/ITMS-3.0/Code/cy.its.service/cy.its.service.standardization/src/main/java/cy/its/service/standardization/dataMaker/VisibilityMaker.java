package cy.its.service.standardization.dataMaker;

import cy.its.service.common.ConstValue;
import cy.its.service.common.DateUtil;
import cy.its.service.common.dataModel.Visibility;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.standardization.dataMaker.originalModel.FrontVisibility;
import cy.its.service.standardization.dictionary.SurveyUpgrade_DeviceCache;
import cy.its.service.standardization.dictionary.model.IDeviceInfo;
import cy.its.service.standardization.validator.BaseValidator;
import cy.its.service.standardization.validator.VisibilityValidator;

@Export
public class VisibilityMaker extends DataMaker<FrontVisibility, Visibility> {

//	@Import
//	DeviceCache deviceCache;

	@Import
	SurveyUpgrade_DeviceCache surveyUpgrade_DeviceCache;

	public VisibilityMaker() {
		super("ÄÜ¼û¶È", FrontVisibility.class, Visibility.class, ConstValue.ROUTE_KEY_ORIGINAL_VISIBILITY,
				ConstValue.ROUTE_KEY_STANDARD_VISIBILITY, "original_traffic_visibility");
	}

	@Override
	Visibility translate(FrontVisibility input) {
		IDeviceInfo d = surveyUpgrade_DeviceCache.get(input.getDevNbr());
		if (d != null) {
			Visibility v = new Visibility();
			v.setDeviceId(d.getDeviceId());
			v.setDeviceSysNbr(d.getSysNbr());
			v.setOrgCode(d.getOrgCode());
			v.setSiteCode(d.getSiteCode());
			v.setOneMinuteVisibility(input.getOneMinuteVisibilityValue());
			v.setTenMinuteVisibility(input.getTenMinuteVisibilityValue());
			v.setCleanDegre(input.getCleanDegree());
			v.setCaseTemperature(input.getCaseTemperature());
			v.setPowerVolatage(input.getPowerVolatage());
			v.setRecordTime(DateUtil.parseDate(input.getRecordTime()));
			v.setOrgPrivilegeCode(d.getOrgPrivilegeCode());

			return v;
		} else {
			input.validateResult = STR_DEVICE_UNREG;
		}

		return null;
	}

	@Override
	BaseValidator<Visibility> validator() {
		return new VisibilityValidator();
	}

}
