package cy.its.com.bus;

public class Event {
	private String topic;

	private String jsonMessage;
	
	public Event(String topic, String jsonMessage) {
		this.topic = topic;
		this.jsonMessage = jsonMessage;
	}

	public String getTopic() {
		return topic;
	}

	public String getJsonMessage() {
		return jsonMessage;
	}
}