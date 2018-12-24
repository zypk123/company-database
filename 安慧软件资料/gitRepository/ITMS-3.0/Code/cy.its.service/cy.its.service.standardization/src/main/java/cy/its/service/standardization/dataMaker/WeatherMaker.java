package cy.its.service.standardization.dataMaker;

import cy.its.service.common.ConstValue;
import cy.its.service.common.DateUtil;
import cy.its.service.common.dataModel.Weather;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.standardization.dataMaker.originalModel.FrontWeather;
import cy.its.service.standardization.dictionary.SurveyUpgrade_DeviceCache;
import cy.its.service.standardization.dictionary.model.IDeviceInfo;
import cy.its.service.standardization.validator.BaseValidator;
import cy.its.service.standardization.validator.WeatherValidator;

@Export
public class WeatherMaker extends DataMaker<FrontWeather, Weather> {

//	@Import
//	DeviceCache deviceCache;

	@Import
	SurveyUpgrade_DeviceCache surveyUpgrade_DeviceCache;

	public WeatherMaker() {
		super("ÆøÏó", FrontWeather.class, Weather.class, ConstValue.ROUTE_KEY_ORIGINAL_WEATHER,
				ConstValue.ROUTE_KEY_STANDARD_WEATHER, "original_weather");
	}

	@Override
	Weather translate(FrontWeather input) {
		IDeviceInfo d = surveyUpgrade_DeviceCache.get(input.getDEVICE_NBR());
		if (d != null) {
			Weather w = new Weather();
			w.setDeviceId(d.getDeviceId());
			w.setDeviceSysNbr(d.getSysNbr());
			w.setOrgCode(d.getOrgCode());
			w.setSiteCode(d.getSiteCode());
			w.setWeatherTemperature(input.getTEMPERATURE());
			w.setRelativeHumidity(input.getRELATIVE_HUMIDITY());
			w.setAirPressure(input.getAIR_PRESSURE());
			w.setWindSpeed(input.getWIND_SPEED());
			w.setWindDirection(input.getWIND_DIRECTION());
			w.setWaterFilmHeight(input.getWATER_FILM_HEIGHT());
			w.setWaterType(input.getWATER_TYPE());
			w.setRainStrong(input.getRAIN_STRONG());
			w.setRecordTime(DateUtil.parseDate(input.getRECORD_TIME()));
			w.setOrgPrivilegeCode(d.getOrgPrivilegeCode());
			return w;
		} else {
			input.validateResult = STR_DEVICE_UNREG;
		}

		return null;
	}

	@Override
	BaseValidator<Weather> validator() {
		return new WeatherValidator();
	}

}
