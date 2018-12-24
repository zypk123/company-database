package cy.its.syscfg.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanUtil {
	static ApplicationContext AppContext;

	static {
		try {
			AppContext = new ClassPathXmlApplicationContext(new String[] {
					"spring.xml", "spring-mybatis.xml" });
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static Object findBean(String name) {
		return AppContext.getBean(name);
	}
}
