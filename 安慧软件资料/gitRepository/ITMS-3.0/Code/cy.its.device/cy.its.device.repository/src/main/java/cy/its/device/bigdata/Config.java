package cy.its.device.bigdata;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

	public static Properties prop = null;

	/**
	 * 加载大数据配置文件
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void loadConfig() throws FileNotFoundException, IOException {
		prop = new Properties();
		InputStream input = Config.class.getResourceAsStream("/bigData.properties");
		prop.load(input);
		input.close();
	}

	/**
	 * 获取配置项
	 * 
	 * @param key
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getProperties(String key) throws FileNotFoundException, IOException {
		if (prop == null) {
			loadConfig();
		}
		// 如果是use_big_data，bigdata_url为key，直接取值否则加上bigdata_url
		if ("use_big_data".equals(key) || "bigdata_url".equals(key)) {
			return prop.getProperty(key);
		} else {
			return prop.getProperty("bigdata_url") + prop.getProperty(key);
		}
	}

}
