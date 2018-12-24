package cy.its.service.common.dataModel;

import java.math.BigDecimal;
import java.util.Date;

public class RoadSensor extends Model {
	
	/**
	 * 系统编号
	 */
	@Mapper("DEVICE_SYS_NBR")
	private String deviceSysNbr;

	/**
	 * 机构代码
	 */
	@Mapper("ORG_CODE")
	private String orgCode;

	/**
	 * 点位代码
	 */
	@Mapper("SITE_CODE")
	private String siteCode;

	/**
	 * 路面温度
	 */
	@Mapper("ROAD_TEMPERATURE")
	private BigDecimal roadTemperature;

	/**
	 * 路基温度
	 */
	@Mapper("ROADBED_TEMPERATURE")
	private BigDecimal roadbedTemperature;

	/**
	 * 水膜厚度
	 */
	@Mapper("WATER_FILE_HEIGH")
	private BigDecimal waterFileHeigh;

	/**
	 * 含盐量
	 */
	@Mapper("SALINITY")
	private BigDecimal salinity;

	/**
	 * 结冰点温度
	 */
	@Mapper("FREEZING_TEMPERATURE")
	private BigDecimal freezingTemperature;

	/**
	 * 道路状况
	 */
	@Mapper("ROAD_CONDITION")
	private String roadCondition;

	/**
	 * 记录时间
	 */
	@Mapper("RECORD_TIME")
	private Date recordTime;

	/**
	 * 机构权限过滤代码
	 */
	@Mapper("ORG_PRIVILEGE_CODE")
	private String orgPrivilegeCode;

	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}

	public void setDeviceSysNbr(String deviceSysNbr) {
		this.deviceSysNbr = deviceSysNbr;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public BigDecimal getRoadTemperature() {
		return roadTemperature;
	}

	public void setRoadTemperature(BigDecimal roadTemperature) {
		this.roadTemperature = roadTemperature;
	}

	public BigDecimal getRoadbedTemperature() {
		return roadbedTemperature;
	}

	public void setRoadbedTemperature(BigDecimal roadbedTemperature) {
		this.roadbedTemperature = roadbedTemperature;
	}

	public BigDecimal getWaterFileHeigh() {
		return waterFileHeigh;
	}

	public void setWaterFileHeigh(BigDecimal waterFileHeigh) {
		this.waterFileHeigh = waterFileHeigh;
	}

	public BigDecimal getSalinity() {
		return salinity;
	}

	public void setSalinity(BigDecimal salinity) {
		this.salinity = salinity;
	}

	public BigDecimal getFreezingTemperature() {
		return freezingTemperature;
	}

	public void setFreezingTemperature(BigDecimal freezingTemperature) {
		this.freezingTemperature = freezingTemperature;
	}

	public String getRoadCondition() {
		return roadCondition;
	}

	public void setRoadCondition(String roadCondition) {
		this.roadCondition = roadCondition;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}
	
    private String deviceId;	
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
}
