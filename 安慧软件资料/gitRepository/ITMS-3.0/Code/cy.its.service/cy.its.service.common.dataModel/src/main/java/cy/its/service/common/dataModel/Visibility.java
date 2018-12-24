package cy.its.service.common.dataModel;

import java.util.Date;

/**
 * 能见度
 * @author STJ
 *
 */
public class Visibility extends Model {
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
	 * 一分钟能见度值
	 */
	@Mapper("ONE_MINUTE_VISIBILITY")
	private long oneMinuteVisibility;

	/**
	 * 十分钟能见度值
	 */
	@Mapper("TEN_MINUTE_VISIBILITY")
	private long tenMinuteVisibility;

	/**
	 * 清洁等级
	 */
	@Mapper("CLEAN_DEGRE")
	private long cleanDegre;

	/**
	 * 机箱温度
	 */
	@Mapper("CASE_TEMPERATURE")
	private long caseTemperature;

	/**
	 * 电源电压
	 */
	@Mapper("POWER_VOLATAGE")
	private String powerVolatage;

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

	public long getOneMinuteVisibility() {
		return oneMinuteVisibility;
	}

	public void setOneMinuteVisibility(long oneMinuteVisibility) {
		this.oneMinuteVisibility = oneMinuteVisibility;
	}

	public long getTenMinuteVisibility() {
		return tenMinuteVisibility;
	}

	public void setTenMinuteVisibility(long tenMinuteVisibility) {
		this.tenMinuteVisibility = tenMinuteVisibility;
	}

	public long getCleanDegre() {
		return cleanDegre;
	}

	public void setCleanDegre(long cleanDegre) {
		this.cleanDegre = cleanDegre;
	}

	public long getCaseTemperature() {
		return caseTemperature;
	}

	public void setCaseTemperature(long caseTemperature) {
		this.caseTemperature = caseTemperature;
	}

	public String getPowerVolatage() {
		return powerVolatage;
	}

	public void setPowerVolatage(String powerVolatage) {
		this.powerVolatage = powerVolatage;
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
