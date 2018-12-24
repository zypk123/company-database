package cy.its.service.imageQuery.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamHelper {
	
	public static byte[] readInputStream(InputStream inputStream) throws IOException {
		ByteArrayOutputStream outputStream = null;
		try {
			
			byte[] buffer = new byte[1024];
			int len = -1;
			outputStream = new ByteArrayOutputStream();
			while ((len = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
			}
			
			return outputStream.toByteArray();
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}
}
