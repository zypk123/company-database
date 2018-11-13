package com.huntkey.rx.sceo.common.service.common.model;

public class UserPass {
	private String username;
	private String password;
	private String visa;
	private String msgfmt;
	private String ext;
	
	public String getUsername() {
		return username;
	}
	public String getVisa() {
		return visa;
	}
	public void setVisa(String visa) {
		this.visa = visa;
	}
	public String getMsgfmt() {
		return msgfmt;
	}
	public void setMsgfmt(String msgfmt) {
		this.msgfmt = msgfmt;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
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

}
