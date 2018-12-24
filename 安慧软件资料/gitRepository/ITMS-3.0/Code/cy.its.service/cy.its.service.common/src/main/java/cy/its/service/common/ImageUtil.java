package cy.its.service.common;

public class ImageUtil {

	private static final String EncryptionKey;

	static {
		EncryptionKey = "anhui8011@£¡¡­¡­#&£¤*@£©£¨*&¡­¡­%£¤#@£¡£¨£¡@=+£©£©#";
	}

	public static String encryptUrl(String url) {
		return SecurityEncode.encoderByDES(url, EncryptionKey);
	}
	
	public static String deEncryptUrl(String encryptUrl) {
		return SecurityEncode.decoderByDES(encryptUrl, EncryptionKey);
	}
}
