package cy.its.service.ledClient.proxy;

import Ice.ObjectPrx;
import cy.its.service.ledClient.generated.TgcsInterfacePrx;
import cy.its.service.ledClient.generated.TgcsInterfacePrxHelper;

public class TgcsInterfacePrxFactory extends ProxyFactory<TgcsInterfacePrx> {
	
	private static ProxyFactory<TgcsInterfacePrx> proxy = new TgcsInterfacePrxFactory();
	
	private TgcsInterfacePrxFactory() {}
	
	@Override
	protected TgcsInterfacePrx createPrx(ObjectPrx objectPrx) {
		return TgcsInterfacePrxHelper.checkedCast(objectPrx);
	}

	@Override
	String identity() {
		return "TgcsInterface";
	}

	@Override
	int port() {		
		return 35201;
	}

	public static TgcsInterfacePrx getProxy(String server){
		return proxy.getPrx(server);
	}
}
