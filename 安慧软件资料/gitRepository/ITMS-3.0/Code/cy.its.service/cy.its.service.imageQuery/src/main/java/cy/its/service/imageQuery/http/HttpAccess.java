package cy.its.service.imageQuery.http;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import cy.its.service.imageQuery.utils.StreamHelper;

public class HttpAccess {
	public static byte[] download(String httpUrl) throws Throwable {
		InputStream inputStream = null;
		try {
			URL url = new URL(httpUrl);
			URLConnection conn = url.openConnection();
			inputStream = conn.getInputStream();
			return StreamHelper.readInputStream(inputStream);
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

	public static void main(String[] args) {
		String url = "https://img10.360buyimg.com/da/jfs/t2392/18/1370306251/91282/e82e5d6e/56962821N4c103ed4.jpg";

		try {
			byte[] bbb = download(url);
			System.out.println(bbb.length);
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}
}
