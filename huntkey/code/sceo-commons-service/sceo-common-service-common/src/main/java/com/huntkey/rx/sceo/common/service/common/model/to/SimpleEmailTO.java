package com.huntkey.rx.sceo.common.service.common.model.to;

/**
 * Created by zhaomj on 2017/4/28.
 */
public class SimpleEmailTO {
    private String mailModuleCode;
    private String mailRecipient;//接收者  【英文字符分号】隔开
    private String mailCopyRecipient;//抄送 多人【英文字符分号】隔开
    private String mailSubject;//发送主题
    private String mailContent;//发送内容
    private String addUser;//新增者
	public String getMailModuleCode() {
		return mailModuleCode;
	}
	public void setMailModuleCode(String mailModuleCode) {
		this.mailModuleCode = mailModuleCode;
	}
	public String getMailRecipient() {
		return mailRecipient;
	}
	public void setMailRecipient(String mailRecipient) {
		this.mailRecipient = mailRecipient;
	}
	public String getMailCopyRecipient() {
		return mailCopyRecipient;
	}
	public void setMailCopyRecipient(String mailCopyRecipient) {
		this.mailCopyRecipient = mailCopyRecipient;
	}
	public String getMailSubject() {
		return mailSubject;
	}
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}
	public String getMailContent() {
		return mailContent;
	}
	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}
	public String getAddUser() {
		return addUser;
	}
	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}
}
