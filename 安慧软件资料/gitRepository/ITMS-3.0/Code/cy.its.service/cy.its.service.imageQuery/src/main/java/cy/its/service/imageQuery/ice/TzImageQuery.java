package cy.its.service.imageQuery.ice;

import java.util.List;

import Ice.ObjectPrx;
import cy.its.service.imageQuery.ice.generated.PlateImageStorePrx;
import cy.its.service.imageQuery.ice.generated.PlateImageStorePrxHelper;

class TzImageQuery extends ImageQuery<PlateImageStorePrx> {

	@Override
	protected List<byte[]> retrieveImages(PlateImageStorePrx imagePrx, String deviceNbr, String snapNbr) {
		return convert(imagePrx.RetrieveVehicleImages(deviceNbr, snapNbr));
	}

	@Override
	protected PlateImageStorePrx createImagePrx(ObjectPrx objectPrx) {
		return PlateImageStorePrxHelper.uncheckedCast(objectPrx);
	}

	@Override
	String identity() {
		return "PlateImageStore";
	}

	@Override
	int port() {
		return 35050;
	}

}
