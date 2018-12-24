package cy.its.service.standardization.validator;
 
import cy.its.service.common.ConstValue;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataModel.Weather;

public class WeatherValidator extends BaseValidator<Weather>{

	@Override
	void fillFilters() {
		addFilter(u -> StringUtil.isNullOrEmpty(u.getDeviceSysNbr()), "DeviceSysNbr不能为空;");
		addFilter(u -> dbLen(u.getDeviceSysNbr()) > ConstValue.INT_18, "DeviceSysNbr不能大于18位;");
		addFilter(u -> dbLen(u.getOrgCode()) > ConstValue.INT_18, "OrgCode不能大于18位;");
		addFilter(u -> dbLen(u.getSiteCode()) > ConstValue.INT_32, "SiteCode不能大于32位;");
		addFilter(u -> !isRightDbNum(u.getWeatherTemperature(),ConstValue.INT_5,ConstValue.INT_2), "WeatherTemperature不能是整数位超过3位小数位超过2的数字;");
		addFilter(u -> !isRightDbNum(u.getRelativeHumidity(),ConstValue.INT_8,ConstValue.INT_2), "RelativeHumidity不能是整数位超过6位小数位超过2的数字;");
		addFilter(u -> !isRightDbNum(u.getAirPressure(),ConstValue.INT_8,ConstValue.INT_2), "AirPressure不能是整数位超过6位小数位超过2的数字;");
		addFilter(u -> !isRightDbNum(u.getWindSpeed(),ConstValue.INT_8,ConstValue.INT_2), "WindSpeed不能是整数位超过6位小数位超过2的数字;");
		addFilter(u -> !isRightDbNum(u.getWindDirection(),ConstValue.INT_5,ConstValue.INT_2), "WindDirection不能是整数位超过3位小数位超过2的数字;");
		addFilter(u -> !isRightDbNum(u.getWaterFilmHeight(),ConstValue.INT_8,ConstValue.INT_2), "WaterFilmHeight不能是整数位超过6位小数位超过2的数字;");
		addFilter(u -> dbLen(u.getWaterType()) > ConstValue.INT_2, "WaterType不能大于2位;");
		addFilter(u -> !isRightDbNum(u.getRainStrong(),ConstValue.INT_8,ConstValue.INT_2), "RainStrong不能是整数位超过6位小数位超过2的数字;");
		addFilter(u -> u.getRecordTime() == null, "RecordTime不可为空;");
		addFilter(u -> is1HMoreThanNow(u.getRecordTime()), "RecordTime时间超过当前时间1个小时,请校时;");
		addFilter(u -> dbLen(u.getOrgPrivilegeCode()) > ConstValue.INT_8, "OrgPrivilegeCode不能大于8位;");
	}

}
