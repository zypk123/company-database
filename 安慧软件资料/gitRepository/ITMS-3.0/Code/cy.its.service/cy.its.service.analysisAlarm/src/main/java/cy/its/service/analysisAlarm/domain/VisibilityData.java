package cy.its.service.analysisAlarm.domain;

import java.util.Date;

/**
* @Title: Visibility.java 
* @Package cy.its.service.visibility.domain 
* @Description: 能见度模型
* @author lil@cychina.cn
* @date 2015年11月6日 上午10:24:27 
* @version V1.0   
 */
public class VisibilityData {
    
	/**
	 * 设备系统编码
	 */
	private  String  deviceSysNbr;
	
	/**
	* 机构代码
	*/
	private String orgCode;
	
	/**
	* 管辖机构权限代码
	*/
	private String orgPrivilegeCode;
	
	/**
	* 点位编码
	*/
	private String siteCode;
	
	/**
	 * 仪分钟能见度值
	 */
	private  double  oneMinuteVisibility;
	
	/**
	 * 清洁等级
	 */
	private  String  cleanDegre;
	
	/**
	 * RECORD_TIME
	 */
	private  Date  recordTime;
	
	/**
	 * 道路类型
	 */
	private  String  roadType="0";
	
	
	/**
	 *设备ID
	 */
	private String  deviceId="";
	
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	

	public String getRoadType() {
		return roadType;
	}

	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}

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

	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public double getOneMinuteVisibility() {
		return oneMinuteVisibility;
	}

	public void setOneMinuteVisibility(double oneMinuteVisibility) {
		this.oneMinuteVisibility = oneMinuteVisibility;
	}

	public String getCleanDegre() {
		return cleanDegre;
	}

	public void setCleanDegre(String cleanDegre) {
		this.cleanDegre = cleanDegre;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	
}
