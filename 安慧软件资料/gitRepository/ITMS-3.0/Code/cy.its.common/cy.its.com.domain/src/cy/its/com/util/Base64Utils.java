package cy.its.com.util;

import java.io.UnsupportedEncodingException;

public class Base64Utils {

	public static byte[] decode(String base64Str) {
		return org.apache.commons.codec.binary.Base64.decodeBase64(base64Str);
	}

	public static String encode(byte[] binaryData) {
		return org.apache.commons.codec.binary.Base64.encodeBase64String(binaryData);
	}

	public static String encodeString(String str, String charSet) throws UnsupportedEncodingException {
		byte[] binaryData = str.getBytes(charSet);
		return org.apache.commons.codec.binary.Base64.encodeBase64String(binaryData);
	}
	
	public static String decodeString(String base64Str, String charSet) throws UnsupportedEncodingException {
		byte[] binaryData = org.apache.commons.codec.binary.Base64.decodeBase64(base64Str);		
		return new String(binaryData, charSet);
	}
}
