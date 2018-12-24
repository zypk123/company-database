package cy.its.device.domain.model;

import cy.its.com.domain.AggregateRoot;

public class ServerApplication extends AggregateRoot {
    
	private String serverAppId;

	private String serverId;

	private String appType;

	private String serverPort;

	private String contex;

	private String suffix;

	private String remark;

	@Override
	public String getIdentityId() {
		return this.serverAppId;
	}

	public String getServerAppId() {
		return serverAppId;
	}

	public void setServerAppId(String serverAppId) {
		this.serverAppId = serverAppId;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getServerPort() {
		return serverPort;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	public String getContex() {
		return contex;
	}

	public void setContex(String contex) {
		this.contex = contex;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}	
}
