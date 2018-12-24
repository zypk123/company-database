package cy.its.service.common.ioc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RegTypeFactory {
	static Map<Class<?>, List<Class<?>>> tmap = new HashMap<Class<?>, List<Class<?>>>();
	
	public static void init(String pack) {
		Set<Class<?>> cs = TypeFinder.getClasses(pack);		
		for (Class<?> c : cs) {
			if (c.isAnnotationPresent(Export.class)) {
				construct(c, c);
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	static void construct(Class<?> c, Class<?> t) {
		if (c == Object.class) {
			return;
		}

		if (tmap.containsKey(c)) {
			List<Class<?>> lc = tmap.get(c);
			if (!lc.contains(t)) {
				lc.add(t);
			}
		} else {
			List<Class<?>> l = new ArrayList<Class<?>>();
			l.add(t);
			tmap.put(c, l);
		}

		if (c.getSuperclass() != null) {
			construct(c.getSuperclass(), t);
		}

		if (c.getInterfaces() != null) {
			for (Class i : c.getInterfaces()) {
				construct(i, t);
			}
		}
	}

	static List<Class<?>> typesOfSuper(Class<?> superType){
		return tmap.get(superType);
	}
}
