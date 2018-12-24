package cy.its.service.standardization.dataMaker;

import cy.its.service.common.ConstValue;
import cy.its.service.common.DateUtil;
import cy.its.service.common.dataModel.RoadSensor;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.standardization.dataMaker.originalModel.FrontRoadSensor;
import cy.its.service.standardization.dictionary.SurveyUpgrade_DeviceCache;
import cy.its.service.standardization.dictionary.model.IDeviceInfo;
import cy.its.service.standardization.validator.BaseValidator;
import cy.its.service.standardization.validator.RoadSensorValidator;

@Export
public class RoadSensorMaker extends DataMaker<FrontRoadSensor, RoadSensor> {

//	@Import
//	DeviceCache deviceCache;

	@Import
	SurveyUpgrade_DeviceCache surveyUpgrade_DeviceCache;

	public RoadSensorMaker() {
		super("Â·¸Ð", FrontRoadSensor.class, RoadSensor.class, ConstValue.ROUTE_KEY_ORIGINAL_ROAD_SENSOR,
				ConstValue.ROUTE_KEY_STANDARD_ROAD_SENSOR, "original_road_sensor");
	}

	@Override
	RoadSensor translate(FrontRoadSensor input) {
		IDeviceInfo di = surveyUpgrade_DeviceCache.get(input.getDevNbr());
		if (di != null) {
			RoadSensor rs = new RoadSensor();
			rs.setDeviceId(di.getDeviceId());
			rs.setDeviceSysNbr(di.getSysNbr());
			rs.setOrgCode(di.getOrgCode());
			rs.setSiteCode(di.getSiteCode());
			rs.setRoadTemperature(input.getRoanTemperature());
			rs.setRoadbedTemperature(input.getTemperature());
			rs.setWaterFileHeigh(input.getWaterFilmHeigh());
			rs.setSalinity(input.getSalinity());
			rs.setFreezingTemperature(input.getFreezingTemperature());
			rs.setRoadCondition(input.getRoadCondition());
			rs.setRecordTime(DateUtil.parseDate(input.getRecordTime()));
			rs.setOrgPrivilegeCode(di.getOrgPrivilegeCode());

			return rs;
		} else {
			input.validateResult = STR_DEVICE_UNREG;
		}

		return null;
	}

	@Override
	BaseValidator<RoadSensor> validator() {
		return new RoadSensorValidator();
	}

}
