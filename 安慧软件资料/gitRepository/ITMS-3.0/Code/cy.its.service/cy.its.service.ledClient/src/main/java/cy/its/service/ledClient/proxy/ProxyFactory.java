package cy.its.service.ledClient.proxy;

import java.util.HashMap;
import java.util.Map;

import Ice.ObjectPrx;

abstract class ProxyFactory<PRX> {
	
	final static String ADDR_FMT = "%s:tcp -h %s -p %d -t %s";
	final static String TIME_OUT = "10000";

	Map<String, PRX> prxCollection = new HashMap<String, PRX>(1);

	protected abstract PRX createPrx(ObjectPrx objectPrx);

	abstract String identity();

	abstract int port();

	protected PRX getPrx(String server) {
		PRX prx = null;
		synchronized (prxCollection) {
			prx = prxCollection.get(server);
		}

		if (prx == null) {
			prx = createPrx(objectPrx(server));
			synchronized (prxCollection) {
				if(!prxCollection.containsKey(server)){
					prxCollection.put(server, prx);
				}
			}
		}

		return prx;
	}

	private ObjectPrx objectPrx(String server) {
		return ProxyUtil.commun.stringToProxy(String.format(ADDR_FMT, identity(), server, port(), TIME_OUT));
	}

	
}