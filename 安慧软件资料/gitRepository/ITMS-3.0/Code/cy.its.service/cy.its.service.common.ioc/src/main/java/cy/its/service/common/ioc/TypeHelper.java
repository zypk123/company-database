package cy.its.service.common.ioc;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

class TypeHelper {
	
	static List<Field> fieldOfClass(
			Class<?> tClass) {		
		List<Field> lst = new ArrayList<Field>();
		extractFieldOfClass(tClass, lst);
		return lst;
	}
	
	static void extractFieldOfClass(Class<?> c,
			List<Field> fields) {
		if (c != Object.class) {
			for (Field field : c.getDeclaredFields()) {
				if (field.isAnnotationPresent(Import.class)){
				field.setAccessible(true);
				fields.add(field);
				}
			}

			if (c.getSuperclass() != null) {
				extractFieldOfClass(c.getSuperclass(), fields);
			}
		}
	}
}
