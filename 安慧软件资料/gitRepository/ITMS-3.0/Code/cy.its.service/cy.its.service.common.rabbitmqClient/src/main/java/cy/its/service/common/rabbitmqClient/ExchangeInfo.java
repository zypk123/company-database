package cy.its.service.common.rabbitmqClient;

public class ExchangeInfo {
	public String name;
	public Boolean durable;
	public Boolean autoDelete;
	public ExchangeType type;

	public ExchangeInfo(String name, Boolean durable, Boolean autoDelete,
			ExchangeType type) {
		this.name = name;
		this.durable = durable;
		this.autoDelete = autoDelete;
		this.type = type;
	}
}
