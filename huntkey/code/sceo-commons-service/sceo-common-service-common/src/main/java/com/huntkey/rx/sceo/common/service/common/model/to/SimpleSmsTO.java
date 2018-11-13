package com.huntkey.rx.sceo.common.service.common.model.to;

/**
 * Created by zhaomj on 2017/4/28.
 */
public class SimpleSmsTO {
	private String smlgModuleCode;//调用模块信息
	private String addUser;//调用者
    private String smlgSendTime;//短信发送时间  空时为立即发送  有时间就是定时发送
    private String smlgContent;//发送内容
    private String smlgPhone;//发送手机号
	public String getSmlgModuleCode() {
		return smlgModuleCode;
	}
	public void setSmlgModuleCode(String smlgModuleCode) {
		this.smlgModuleCode = smlgModuleCode;
	}
	public String getAddUser() {
		return addUser;
	}
	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}
	public String getSmlgSendTime() {
		return smlgSendTime;
	}
	public void setSmlgSendTime(String smlgSendTime) {
		this.smlgSendTime = smlgSendTime;
	}
	public String getSmlgContent() {
		return smlgContent;
	}
	public void setSmlgContent(String smlgContent) {
		this.smlgContent = smlgContent;
	}
	public String getSmlgPhone() {
		return smlgPhone;
	}
	public void setSmlgPhone(String smlgPhone) {
		this.smlgPhone = smlgPhone;
	}
}
