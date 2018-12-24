package cy.its.service.common.rabbitmqClient;

public class MQAddress {
	public String mqIp;
	public int port = 15672;
	public String vHost;
	public String user;
	public String passWord;

	public MQAddress(String mqIp, String vHost, String user, String passWord) {
		this.mqIp = mqIp;
		this.vHost = vHost;
		this.user = user;
		this.passWord = passWord;
	}
}
