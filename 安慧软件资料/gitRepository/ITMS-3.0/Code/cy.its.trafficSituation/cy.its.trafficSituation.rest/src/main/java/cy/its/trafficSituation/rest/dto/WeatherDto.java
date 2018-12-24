/**
 * @Title: WeatherDto.java
 * @Package cy.its.trafficSituation.rest.dto
 * @Description: 气象仪Dto
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月6日 下午2:50:12
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.rest.dto;

import java.text.SimpleDateFormat;

import cy.its.com.dto.BaseDto;
import cy.its.com.util.ObjectMapUtils;
import cy.its.trafficSituation.domain.model.TrafficWeather;

public class WeatherDto extends BaseDto{

	private String meteorologicDataId;

	private String deviceSysNbr;

	private String orgCode;

	private String siteCode;

	private String weatherTemperature;

	private String relativeHumidity;

	private String airPressure;

	private String windSpeed;

	private String windDirection;

	private String waterFilmHeight;

	private String waterType;

	private String rainStrong;

	private String recordTime;

	// 关联字段
	private String deviceName;
	private String siteName;
	private String lonLat;

	// 查询条件中的字段
	private String startTime;
	private String endTime;
	/**
	 * getter method
	 * @return the meteorologicDataId
	 */
	
	public String getMeteorologicDataId() {
		return meteorologicDataId;
	}
	/**
	 * setter method
	 * @param meteorologicDataId the meteorologicDataId to set
	 */
	
	public void setMeteorologicDataId(String meteorologicDataId) {
		this.meteorologicDataId = meteorologicDataId;
	}
	/**
	 * getter method
	 * @return the deviceSysNbr
	 */
	
	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}
	/**
	 * setter method
	 * @param deviceSysNbr the deviceSysNbr to set
	 */
	
	public void setDeviceSysNbr(String deviceSysNbr) {
		this.deviceSysNbr = deviceSysNbr;
	}
	/**
	 * getter method
	 * @return the orgCode
	 */
	
	public String getOrgCode() {
		return orgCode;
	}
	/**
	 * setter method
	 * @param orgCode the orgCode to set
	 */
	
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	/**
	 * getter method
	 * @return the siteCode
	 */
	
	public String getSiteCode() {
		return siteCode;
	}
	/**
	 * setter method
	 * @param siteCode the siteCode to set
	 */
	
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	/**
	 * getter method
	 * @return the weatherTemperature
	 */
	
	public String getWeatherTemperature() {
		return weatherTemperature;
	}
	/**
	 * setter method
	 * @param weatherTemperature the weatherTemperature to set
	 */
	
	public void setWeatherTemperature(String weatherTemperature) {
		this.weatherTemperature = weatherTemperature;
	}
	/**
	 * getter method
	 * @return the relativeHumidity
	 */
	
	public String getRelativeHumidity() {
		return relativeHumidity;
	}
	/**
	 * setter method
	 * @param relativeHumidity the relativeHumidity to set
	 */
	
	public void setRelativeHumidity(String relativeHumidity) {
		this.relativeHumidity = relativeHumidity;
	}
	/**
	 * getter method
	 * @return the airPressure
	 */
	
	public String getAirPressure() {
		return airPressure;
	}
	/**
	 * setter method
	 * @param airPressure the airPressure to set
	 */
	
	public void setAirPressure(String airPressure) {
		this.airPressure = airPressure;
	}
	/**
	 * getter method
	 * @return the windSpeed
	 */
	
	public String getWindSpeed() {
		return windSpeed;
	}
	/**
	 * setter method
	 * @param windSpeed the windSpeed to set
	 */
	
	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}
	/**
	 * getter method
	 * @return the windDirection
	 */
	
	public String getWindDirection() {
		return windDirection;
	}
	/**
	 * setter method
	 * @param windDirection the windDirection to set
	 */
	
	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}
	/**
	 * getter method
	 * @return the waterFilmHeight
	 */
	
	public String getWaterFilmHeight() {
		return waterFilmHeight;
	}
	/**
	 * setter method
	 * @param waterFilmHeight the waterFilmHeight to set
	 */
	
	public void setWaterFilmHeight(String waterFilmHeight) {
		this.waterFilmHeight = waterFilmHeight;
	}
	/**
	 * getter method
	 * @return the waterType
	 */
	
	public String getWaterType() {
		return waterType;
	}
	/**
	 * setter method
	 * @param waterType the waterType to set
	 */
	
	public void setWaterType(String waterType) {
		this.waterType = waterType;
	}
	/**
	 * getter method
	 * @return the rainStrong
	 */
	
	public String getRainStrong() {
		return rainStrong;
	}
	/**
	 * setter method
	 * @param rainStrong the rainStrong to set
	 */
	
	public void setRainStrong(String rainStrong) {
		this.rainStrong = rainStrong;
	}
	/**
	 * getter method
	 * @return the recordTime
	 */
	
	public String getRecordTime() {
		return recordTime;
	}
	/**
	 * setter method
	 * @param recordTime the recordTime to set
	 */
	
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	/**
	 * getter method
	 * @return the deviceName
	 */
	
	public String getDeviceName() {
		return deviceName;
	}
	/**
	 * setter method
	 * @param deviceName the deviceName to set
	 */
	
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	/**
	 * getter method
	 * @return the siteName
	 */
	
	public String getSiteName() {
		return siteName;
	}
	/**
	 * setter method
	 * @param siteName the siteName to set
	 */
	
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	/**
	 * getter method
	 * @return the lonLat
	 */
	
	public String getLonLat() {
		return lonLat;
	}
	/**
	 * setter method
	 * @param lonLat the lonLat to set
	 */
	
	public void setLonLat(String lonLat) {
		this.lonLat = lonLat;
	}
	/**
	 * getter method
	 * @return the startTime
	 */
	
	public String getStartTime() {
		return startTime;
	}
	/**
	 * setter method
	 * @param startTime the startTime to set
	 */
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * getter method
	 * @return the endTime
	 */
	
	public String getEndTime() {
		return endTime;
	}
	/**
	 * setter method
	 * @param endTime the endTime to set
	 */
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public WeatherDto(){
		
	}
	public WeatherDto(TrafficWeather weather){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ObjectMapUtils.parseObject(this, weather);
		if(weather.getAirPressure()!=null){
			this.setAirPressure(weather.getAirPressure().toString());
		}
		if(weather.getRainStrong() != null){
			this.setRainStrong(weather.getRainStrong().toString());
		}
		if(weather.getRecordTime() != null){
			this.setRecordTime(sdf.format(weather.getRecordTime()));
		}
		if(weather.getRelativeHumidity() != null){
			this.setRelativeHumidity(weather.getRelativeHumidity().toString());
		}
		if(weather.getWaterFilmHeight() != null){
			this.setWaterFilmHeight(weather.getWaterFilmHeight().toString());
		}
		if(weather.getWeatherTemperature() != null){
			this.setWeatherTemperature(weather.getWeatherTemperature().toString());
		}
		if(weather.getWindDirection() != null){
			this.setWindDirection(weather.getWindDirection().toString());
		}
		if(weather.getWindSpeed() != null){
			this.setWindSpeed(weather.getWindSpeed().toString());
		}
		
	}
	
	
	
	
	
}
