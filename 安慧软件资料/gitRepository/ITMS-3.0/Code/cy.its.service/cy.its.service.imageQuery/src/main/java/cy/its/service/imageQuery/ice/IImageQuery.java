package cy.its.service.imageQuery.ice;

import java.util.List;

interface IImageQuery {
	List<byte[]> retrieveImages(String imageServer, String deviceNbr, String snapNbr);
}
