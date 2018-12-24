package cy.its.device.rest.dto;

import com.wordnik.swagger.annotations.ApiParam;

public class MonitorAndPlatformDto {
	/*****接入平台******/
	
	@ApiParam(name="接入平台Id")
	private String serverPlatId;
	
	@ApiParam(name="接入平台名称")
	private String serverPlatName;
	
	@ApiParam(name="备注")
	private String remark;
	
	@ApiParam(name="监控中心Id")
	private String surveySystemId;
	
	@ApiParam(name="服务器组件Id")
	private String serverAppId;
	
	@ApiParam(name="服务器组件类型")
	private String appType;
	
	@ApiParam(name="服务器组件ID字符串")
	private String serverAppIdStr;
	
	@ApiParam(name="原先的服务器组件ID字符串")
	private String oldServerAppIdStr;
	
	@ApiParam(name="监控中心名称")
	private String surveySystemName;
	
	public String getServerPlatId() {
		return serverPlatId;
	}

	public void setServerPlatId(String serverPlatId) {
		this.serverPlatId = serverPlatId;
	}

	public String getServerPlatName() {
		return serverPlatName;
	}

	public void setServerPlatName(String serverPlatName) {
		this.serverPlatName = serverPlatName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSurveySystemId() {
		return surveySystemId;
	}

	public void setSurveySystemId(String surveySystemId) {
		this.surveySystemId = surveySystemId;
	}

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

	public String getServerAppIdStr() {
		return serverAppIdStr;
	}

	public void setServerAppIdStr(String serverAppIdStr) {
		this.serverAppIdStr = serverAppIdStr;
	}

	public String getOldServerAppIdStr() {
		return oldServerAppIdStr;
	}

	public void setOldServerAppIdStr(String oldServerAppIdStr) {
		this.oldServerAppIdStr = oldServerAppIdStr;
	}

	public String getSurveySystemName() {
		return surveySystemName;
	}

	public void setSurveySystemName(String surveySystemName) {
		this.surveySystemName = surveySystemName;
	}
	
}
