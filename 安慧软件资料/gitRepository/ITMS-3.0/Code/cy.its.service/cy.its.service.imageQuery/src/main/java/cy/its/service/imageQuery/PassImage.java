package cy.its.service.imageQuery;

import cy.its.service.imageQuery.ice.ImageProxy;

public class PassImage extends SurveyImage {
	public static byte[] query(String encryptImageUrl) {
		return getImage(encryptImageUrl, "¹ý³µ", ImageProxy.PASSVEH);
	}
}
