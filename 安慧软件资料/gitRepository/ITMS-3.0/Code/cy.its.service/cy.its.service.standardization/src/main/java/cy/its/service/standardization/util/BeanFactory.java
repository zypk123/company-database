package cy.its.service.standardization.util;

public class BeanFactory {
	
//	static ApplicationContext AppContext;
//
//	static {
//		try {
//			AppContext = new ClassPathXmlApplicationContext(new String[] {
//					"spring.xml"});
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	public static <T> T findBean(String name, Class<T> clazz) {
//		return (T) AppContext.getBean(name);
//	}
//	
//	@SuppressWarnings("unchecked")
//	public static <T> T findBean(Class<T> clazz) {
//		String simpleName = clazz.getSimpleName();
//		String name = String.valueOf(simpleName.charAt(0)).
//				toLowerCase() + simpleName.substring(1);
//		return (T) AppContext.getBean(name);
//	}
//	
//	public static Properties getProperty(String propertyBean) {
//		return (Properties)AppContext.getBean(propertyBean);
//	}
}
