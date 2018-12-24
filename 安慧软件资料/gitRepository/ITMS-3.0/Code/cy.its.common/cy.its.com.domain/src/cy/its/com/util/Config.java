package cy.its.com.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Config {

	private static Map<String, String> confMap;

	public static void init(Properties props) {
		if (confMap == null) {
			confMap = new HashMap<String, String>(props.size());
			props.entrySet().forEach(e -> {
				confMap.put(e.getKey().toString(), e.getValue().toString());
			});
		}
	}
	
	
	public static String value(String key){
		return confMap.get(key);
	}
}
