package cy.its.common.duplex.domain;

import java.io.Serializable;
import java.util.Map;

import org.springframework.web.socket.WebSocketSession;

public class WebscoketSessionBean implements Serializable{
	/**
	 * 序列化
	 */
	private static long serialVersionUID = 6815612012608314160L;
	
	private  WebSocketSession  ws; //WS服务session
	
	private  String orgCode;//机构权限代码
	
	private  String loginName;//用户登录名

	private  Map    param;//服务参数
	
	private  String  key;//服务参数
	//构造器
	public WebscoketSessionBean(Map    param,WebSocketSession  ws,String orgCode,String loginName,String  key){
	    this.key=key;
	    this.param=param;
	    this.ws=ws;
	    this.orgCode=orgCode;
	    this.loginName = loginName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public WebSocketSession getWs() {
		return ws;
	}

	public void setWs(WebSocketSession ws) {
		this.ws = ws;
	}

	public Map getParam() {
		return param;
	}

	public void setParam(Map param) {
		this.param = param;
	}
	//通过WS找当前服务
	public boolean equals(WebSocketSession ws){
		return this.ws.equals(ws);
	}
	
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
}
