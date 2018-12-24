package cy.its.violation.mybatis.model;

public class T_Vio_Asso_User_Device {
	private String assoUserDeviceId;

	private String userId;

	private String deviceSysNbr;

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