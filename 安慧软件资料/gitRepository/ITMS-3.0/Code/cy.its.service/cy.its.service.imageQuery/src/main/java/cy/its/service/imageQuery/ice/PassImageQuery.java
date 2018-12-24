package cy.its.service.imageQuery.ice;

import java.util.List;

import Ice.ObjectPrx;
import cy.its.service.imageQuery.ice.generated.PassingImageStorePrx;
import cy.its.service.imageQuery.ice.generated.PassingImageStorePrxHelper;

class PassImageQuery extends ImageQuery<PassingImageStorePrx> {

	@Override
	protected List<byte[]> retrieveImages(PassingImageStorePrx imagePrx, String deviceNbr, String snapNbr) {
		return convert(imagePrx.RetrieveVehicleImages(deviceNbr, snapNbr));
	}

	@Override
	protected PassingImageStorePrx createImagePrx(ObjectPrx objectPrx) {
		return PassingImageStorePrxHelper.uncheckedCast(objectPrx);
	}

	@Override
	String identity() {
		return "PassingImageStore";
	}

	@Override
	int port() {
		return 35003;
	}
}
