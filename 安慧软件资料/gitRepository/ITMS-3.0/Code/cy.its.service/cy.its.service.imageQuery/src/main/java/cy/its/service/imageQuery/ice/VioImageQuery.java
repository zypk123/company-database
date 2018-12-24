package cy.its.service.imageQuery.ice;

import java.util.List;

import Ice.ObjectPrx;
import cy.its.service.imageQuery.ice.generated.ViolationImageStorePrx;
import cy.its.service.imageQuery.ice.generated.ViolationImageStorePrxHelper;

class VioImageQuery extends ImageQuery<ViolationImageStorePrx> {

	@Override
	protected List<byte[]> retrieveImages(ViolationImageStorePrx imagePrx, String deviceNbr, String snapNbr) {
		return convert(imagePrx.RetrieveViolationImages(deviceNbr, snapNbr));
	}

	@Override
	protected ViolationImageStorePrx createImagePrx(ObjectPrx objectPrx) {
		return ViolationImageStorePrxHelper.uncheckedCast(objectPrx);
	}

	@Override
	String identity() {
		return "ViolationImageStore";
	}

	@Override
	int port() {
		return 35004;
	}

}
