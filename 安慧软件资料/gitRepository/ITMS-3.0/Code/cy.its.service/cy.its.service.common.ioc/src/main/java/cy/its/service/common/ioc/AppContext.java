package cy.its.service.common.ioc;

public class AppContext {

	public static void init(String packageName) {
		RegTypeFactory.init(packageName);
	}

	public static <T> T getBean(Class<T> clazz) throws Exception {
		Object obj = ObjectFactory.getBean(clazz);
		return clazz.cast(obj);
	}
}
