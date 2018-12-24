package cy.its.service.common.dataModel;

import java.util.List;
import java.util.Map;

/**
 * 监控服务器改造后状态
 * 
 * @author STJ
 *
 */
public class SurveyUpgrade_DeviceStatus extends Model {
	private String deviceKey;
	private String deviceSysId;
	private String deviceSysNbr;
	
	private String cameraNbr;
	private String cameraId;
	
	private int statusCode;
	private List<String> faultCodes;
	private Long statusTime;
	private Long startTime;
	private Long deviceTime;
	
	private int timeDiff;

	private Map<String, String> extendedProperties;
	
	private String orgPrivilegeCode;

	public String getDeviceKey() {
		return deviceKey;
	}

	public void setDeviceKey(String deviceKey) {
		this.deviceKey = deviceKey;
	}

	public String getDeviceSysId() {
		return deviceSysId;
	}

	public void setDeviceSysId(String deviceSysId) {
		this.deviceSysId = deviceSysId;
	}

	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}

	public void setDeviceSysNbr(String deviceSysNbr) {
		this.deviceSysNbr = deviceSysNbr;
	}

	public String getCameraNbr() {
		return cameraNbr;
	}

	public void setCameraNbr(String cameraNbr) {
		this.cameraNbr = cameraNbr;
	}

	public String getCameraId() {
		return cameraId;
	}

	public void setCameraId(String cameraId) {
		this.cameraId = cameraId;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public List<String> getFaultCodes() {
		return faultCodes;
	}

	public void setFaultCodes(List<String> faultCodes) {
		this.faultCodes = faultCodes;
	}

	public Long getStatusTime() {
		return statusTime;
	}

	public void setStatusTime(Long statusTime) {
		this.statusTime = statusTime;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getDeviceTime() {
		return deviceTime;
	}

	public void setDeviceTime(Long deviceTime) {
		this.deviceTime = deviceTime;
	}

	public Map<String, String> getExtendedProperties() {
		return extendedProperties;
	}

	public void setExtendedProperties(Map<String, String> extendedProperties) {
		this.extendedProperties = extendedProperties;
	}

	public int getTimeDiff() {
		return timeDiff;
	}

	public void setTimeDiff(int timeDiff) {
		this.timeDiff = timeDiff;
	}

	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}
}
