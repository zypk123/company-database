package com.huntkey.rx.sceo.common.service.common.model;

import org.springframework.beans.factory.annotation.Value;

public class EmailSetting {
	private String host;//邮箱服务器
	private String username;//发送邮箱用户名
	private String password;//发送邮箱密码
	private String contenttype;//发送内容编码类型
	private String auth;//发送邮件是否需要账户认证
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContenttype() {
		return contenttype;
	}
	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}

}
