package cy.its.com.bus;

public interface IBusEngine {
	void register(Receiver eventAction);

	void send(Event event);

	void receive(Event event);
	
	void start();
	
	void stop();
}