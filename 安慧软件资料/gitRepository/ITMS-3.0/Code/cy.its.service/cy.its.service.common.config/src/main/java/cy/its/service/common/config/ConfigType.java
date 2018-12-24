package cy.its.service.common.config;

import java.io.File;

import cy.its.service.common.StringUtil;

public enum ConfigType {
	COMMON(ITSConfig.getUserDir().getParentFile()),
	PRIVATE(ITSConfig.getUserDir());

	ConfigContainer configContainer;

	ConfigType(File file) {
		this.configContainer = new ConfigContainer(file);
	}
	
	
	public static String findValue(String propKey) {
		String propValue = ConfigType.PRIVATE.getValue(propKey);
		return StringUtil.isNullOrEmpty(propValue) ? ConfigType.COMMON.getValue(propKey) : propValue;
	}

	public static String findValueOrDefault(String propKey, String defaultValue) {
		String propValue = findValue(propKey);
		return StringUtil.isNullOrEmpty(propValue) ? defaultValue : propValue;
	}

	public String getValue(String propKey) {
		return configContainer.getValue(propKey);
	}
	
}
