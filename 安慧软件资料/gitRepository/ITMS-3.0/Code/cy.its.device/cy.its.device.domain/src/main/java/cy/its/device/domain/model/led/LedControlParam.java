package cy.its.device.domain.model.led;

public class LedControlParam {
	
	private String ledSvrIp;
	
	private String deviceSysNbr;
	
	private String orgPrivilegeCode;

	public LedControlParam(String ledSvrIp, String deviceSysNbr, String orgPrivilegeCode) {
		super();
		this.ledSvrIp = ledSvrIp;
		this.deviceSysNbr = deviceSysNbr;
		this.orgPrivilegeCode = orgPrivilegeCode;
	}

	public String getLedSvrIp() {
		return ledSvrIp;
	}

	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}

	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}
}
