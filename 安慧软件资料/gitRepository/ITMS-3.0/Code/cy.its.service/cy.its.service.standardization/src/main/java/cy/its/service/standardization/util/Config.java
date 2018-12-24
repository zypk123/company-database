package cy.its.service.standardization.util;

import cy.its.service.common.config.ITSConfig;

public class Config {

//	static List<Properties> lstProperties;
	public static  String DEFAULT_VEHICLE_CITY = null;
	public static  String DEFAULT_VEHICLE_PROVINCE = null;
	
	static {
//		lstProperties = new ArrayList<Properties>();
//		lstProperties.add(BeanFactory.getProperty("mqConfig"));
//		lstProperties.add(BeanFactory.getProperty("privateConfig"));
		DEFAULT_VEHICLE_CITY = ITSConfig.findValue("DefaultVehicleCity");
		DEFAULT_VEHICLE_PROVINCE = ITSConfig.findValue("DefaultVehicleProvince");
	}	
//	
//	public static String getValue(String configKey) {		
//		for (Properties properties : lstProperties) {
//			String pValue = properties.getProperty(configKey);
//			if (pValue != null) {
//				return pValue;
//			}
//		}
//		
//		return null;
//	}
	
}
