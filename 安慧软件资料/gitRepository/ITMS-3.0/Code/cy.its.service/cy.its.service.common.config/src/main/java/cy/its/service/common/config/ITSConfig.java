package cy.its.service.common.config;

import java.io.File;

public class ITSConfig {

	static File getUserDir() {
		return new File(System.getProperty("user.dir"));
	}

	public static String findValue(String propKey) {
		return ConfigType.findValue(propKey);
	}

	public static String findValueOrDefault(String propKey, String defaultValue) {
		return ConfigType.findValueOrDefault(propKey, defaultValue);
	}
}
