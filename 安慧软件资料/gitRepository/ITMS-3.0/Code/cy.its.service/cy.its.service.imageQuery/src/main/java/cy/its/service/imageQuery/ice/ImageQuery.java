package cy.its.service.imageQuery.ice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Ice.ObjectPrx;
import cy.its.service.imageQuery.ice.generated.VehicleImage;

abstract class ImageQuery<PRX> implements IImageQuery{

	final static String ADDR_FMT = "%s:tcp -h %s -p %d -t %s";
	final static String TIME_OUT = "10000";

	Map<String, PRX> DicImagePrx = new HashMap<String, PRX>(1);

	protected abstract List<byte[]> retrieveImages(PRX imagePrx, String deviceNbr, String snapNbr);

	protected abstract PRX createImagePrx(ObjectPrx objectPrx);

	abstract String identity();

	abstract int port();

	@Override
	public List<byte[]> retrieveImages(String imageServer, String deviceNbr, String snapNbr) {
		return retrieveImages(getImagePrx(imageServer), deviceNbr, snapNbr);
	}

	private PRX getImagePrx(String imageServer) {
		PRX prx = null;
		synchronized (DicImagePrx) {
			prx = DicImagePrx.get(imageServer);
		}

		if (prx == null) {
			prx = createImagePrx(objectPrx(imageServer));
			synchronized (DicImagePrx) {
				if(!DicImagePrx.containsKey(imageServer)){
					DicImagePrx.put(imageServer, prx);
				}
			}
		}

		return prx;
	}

	protected ObjectPrx objectPrx(String imageServer) {
		return ProxyUtil.commun.stringToProxy(String.format(ADDR_FMT, identity(), imageServer, port(), TIME_OUT));
	}

	protected List<byte[]> convert(VehicleImage[] images) {
		if (images != null) {
			return Arrays.asList(images).stream().filter(c -> c != null).map(c -> c.imageData)
					.collect(Collectors.toList());
		}

		return null;
	}
}
