package cy.its.service.standardization.dataMaker.originalModel;

public class SMSInfo extends BaseOrginalModel {
	private String Content;
	private String Receiver;
	private Integer SendStatus;
	private String SendTime;
	private String Sender;
	private Integer Type;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getReceiver() {
		return Receiver;
	}

	public void setReceiver(String receiver) {
		Receiver = receiver;
	}

	public Integer getSendStatus() {
		return SendStatus;
	}

	public void setSendStatus(Integer sendStatus) {
		SendStatus = sendStatus;
	}

	public String getSendTime() {
		return SendTime;
	}

	public void setSendTime(String sendTime) {
		SendTime = sendTime;
	}

	public String getSender() {
		return Sender;
	}

	public void setSender(String sender) {
		Sender = sender;
	}

	public Integer getType() {
		return Type;
	}

	public void setType(Integer type) {
		Type = type;
	}
}
