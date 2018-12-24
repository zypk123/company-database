package cy.its.service.imageQuery.ice;

import java.util.List;

public enum ImageProxy {

	PASSVEH(new PassImageQuery()), 
	VIOLATION(new VioImageQuery()), 
	PLATE(new TzImageQuery());

	IImageQuery imageQuery;

	ImageProxy(IImageQuery imageQuery) {
		this.imageQuery = imageQuery;
	}

	public List<byte[]> queryImages(String imageServer, String deviceNbr, String snapNbr) {
		return this.imageQuery.retrieveImages(imageServer, deviceNbr, snapNbr);
	}
}
