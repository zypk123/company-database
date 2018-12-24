package cy.its.service.device.serverChecker.model;

import cy.its.service.common.dataAccess.MapColumn;

public class ServerInfo {
	@MapColumn
	public String serverId;
	
	@MapColumn
	public String serverIp;
	
	@MapColumn
	public String surveySystemId;
	
	@MapColumn
	public String surveySystemName;
	
	@MapColumn
	public String orgPrivilegeCode;

	public String getServerIp() {
		return serverIp;
	}
}
