/**
 * @Title: WeatherDeviceDto.java
 * @Package cy.its.trafficSituation.rest.dto
 * @Description: TODO(这里要填写用途)
 * @author gyf guanyf@cychina.cn
 * @date 2015年12月21日 下午2:23:57
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.rest.dto;

public class WeatherDeviceDto extends DeviceDto {
	private String weatherTemperature;

	private String relativeHumidity;

	private String airPressure;

	private String windSpeed;

	private String windDirection;

	private String waterFilmHeight;

	private String waterType;

	private String rainStrong;

	private String recordTime;

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

}
