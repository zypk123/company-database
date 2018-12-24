package cy.its.com.bus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EventBus implements Receiver {	

	IBusEngine busEngine;
	HashMap<String, List<Receiver>> eventHandlers = new HashMap<String, List<Receiver>>();

	public EventBus() {
		busEngine = new PBusEngine();
		busEngine.register(this);
	}

	public void publish(String topic, String message) {
		busEngine.send(new Event(topic, message));
	}

	public void subscribe(String topic, Receiver eventHandler) {
		if (eventHandlers.containsKey(topic)) {
			List<Receiver> handlers = eventHandlers.get(topic);
			synchronized (handlers) {
				handlers.add(eventHandler);
			}
		} else {
			List<Receiver> lstReceivers = new ArrayList<Receiver>();
			lstReceivers.add(eventHandler);
			eventHandlers.put(topic, lstReceivers);
		}
	}

	List<Receiver> getHandler(String topic) {
		synchronized (eventHandlers) {
			return eventHandlers.containsKey(topic) ? eventHandlers.get(topic)
					: null;
		}
	}

	public void accept(Event event) {
		if (event != null) {
			List<Receiver> handlers = getHandler(event.getTopic());
			if (handlers != null) {
				synchronized (handlers) {
					for (Receiver handler : handlers) {
						handler.accept(event);
//						threadPool.submit(() -> {
//							handler.accept(event);
//						});
					}
				}
			}
		}
	}
	
	public void start(){
		busEngine.start();
	}
}
