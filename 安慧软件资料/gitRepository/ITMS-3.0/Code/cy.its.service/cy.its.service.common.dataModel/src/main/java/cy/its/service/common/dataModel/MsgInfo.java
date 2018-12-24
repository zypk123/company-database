package cy.its.service.common.dataModel;

import java.util.Date;

public class MsgInfo extends Model {
//	/**
//	* 短信信息ID
//	*/
//	@Mapper("ID")
//	private String id;
	
	/**
	* 发送者
	*/
	@Mapper("SENDER")
	private String sender;
	
	/**
	* 发送时间
	*/
	@Mapper("SEND_TIME")
	private Date sendTime;
	
	/**
	* 发送状态
	*/
	@Mapper("SEND_STATUS")
	private String sendStatus;
	
	/**
	* 内容类型
	*/
	@Mapper("MSG_TYPE")
	private String msgType;
	
	/**
	* 内容标题
	*/
	@Mapper("MSG_TITLE")
	private String msgTitle;
	
	/**
	* 短信内容
	*/
	@Mapper("MSG_CONTENT")
	private String msgContent;
	
	/**
	* 接收者
	*/
	@Mapper("RECEIVER")
	private String receiver;
	
	/**
	* 备注
	*/
	@Mapper("REMARK")
	private String remark;

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
