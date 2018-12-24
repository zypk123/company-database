package cy.its.device.rest.dto;

import cy.its.com.dto.BaseDto;

/**
 * @author qianfuxing
 *
 */
public class ServerApplicationDto extends BaseDto {
	private String serverAppId;		//服务器组件ID

	private String appType;			//服务器组件类型

    private String serverId;		//服务器ID
    
    private String serverPort;		//端口号

    private String contex;			//上下文

    private String suffix;			//后缀

    private String remark;			//备注

	public String getServerAppId() {
		return serverAppId;
	}

	public void setServerAppId(String serverAppId) {
		this.serverAppId = serverAppId;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
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
