package cy.its.service.standardization.dataMaker.originalModel;

import java.util.Map;

public class SurveyDeviceStatus extends BaseOrginalModel
{
    private String deviceNo;

    private String deviceIpAddr;

    private int status;

    private String faultDetail;

    private Map<java.lang.String, Integer> statsInfo;

    private String gpsLocation;

    private String startTime;

    private String deviceTime;

    private Map<String, String> otherStatusInfo;

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getDeviceIpAddr() {
		return deviceIpAddr;
	}

	public void setDeviceIpAddr(String deviceIpAddr) {
		this.deviceIpAddr = deviceIpAddr;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFaultDetail() {
		return faultDetail;
	}

	public void setFaultDetail(String faultDetail) {
		this.faultDetail = faultDetail;
	}

	public Map<java.lang.String, Integer> getStatsInfo() {
		return statsInfo;
	}

	public void setStatsInfo(Map<java.lang.String, Integer> statsInfo) {
		this.statsInfo = statsInfo;
	}

	public String getGpsLocation() {
		return gpsLocation;
	}

	public void setGpsLocation(String gpsLocation) {
		this.gpsLocation = gpsLocation;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getDeviceTime() {
		return deviceTime;
	}

	public void setDeviceTime(String deviceTime) {
		this.deviceTime = deviceTime;
	}

	public Map<String, String> getOtherStatusInfo() {
		return otherStatusInfo;
	}

	public void setOtherStatusInfo(Map<String, String> otherStatusInfo) {
		this.otherStatusInfo = otherStatusInfo;
	}
    
    
}
