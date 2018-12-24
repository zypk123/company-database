package cy.its.violation.domain.criteria;

import cy.its.com.domain.Criteria;

public class AssoUserDeviceCriteria extends Criteria {

	public String assoUserDeviceId;

	public String userId;

	public String deviceSysNbr;

	public String getAssoUserDeviceId() {
		return assoUserDeviceId;
	}

	public void setAssoUserDeviceId(String assoUserDeviceId) {
		this.assoUserDeviceId = assoUserDeviceId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}

	public void setDeviceSysNbr(String deviceNbr) {
		this.deviceSysNbr = deviceNbr;
	}

}
