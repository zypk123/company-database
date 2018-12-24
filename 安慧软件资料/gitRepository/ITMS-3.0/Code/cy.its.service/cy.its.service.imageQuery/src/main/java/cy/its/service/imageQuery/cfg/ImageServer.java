package cy.its.service.imageQuery.cfg;

import cy.its.service.imageQuery.cfg.dataAccess.MapColumn;

public class ImageServer {
	@MapColumn
	private String serverType;
	
	@MapColumn
	private String serverIp;
	
	@MapColumn
	private String serverPort;
	
	@MapColumn
	private String context;
	
	@MapColumn
	private String suffix;

	public String getServerType() {
		return serverType;
	}

	public void setServerType(String serverType) {
		this.serverType = serverType;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getServerPort() {
		return serverPort;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}
