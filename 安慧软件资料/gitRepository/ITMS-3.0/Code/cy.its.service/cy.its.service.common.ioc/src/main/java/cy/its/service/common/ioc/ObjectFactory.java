package cy.its.service.common.ioc;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class ObjectFactory {

	@SuppressWarnings("rawtypes")
	static Map<Class, Object> map = new HashMap<Class, Object>();

	@SuppressWarnings({ "rawtypes" })
	public static Object getBean(Class clazz) throws Exception {
		try {
			if (map.containsKey(clazz)) {
				return map.get(clazz);
			}

			return create(clazz);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("rawtypes")
	private static Object create(Class clazz) throws InstantiationException, IllegalAccessException, Exception {
		Object t = clazz.newInstance();
		for (Field f : TypeHelper.fieldOfClass(clazz)) {
			create(t, f);
		}
		map.put(clazz, t);
		return t;
	}

	@SuppressWarnings("unchecked")
	private static void create(Object t, Field f) throws IllegalAccessException, Exception {
		if (f.isAnnotationPresent(Import.class)) {
			f.setAccessible(true);
			// 字段的类型
			@SuppressWarnings("rawtypes")
			Class fc = f.getType();
			// 非数组或列表类型
			if (fc.isArray()) {
				// 数组类型
			} else if (fc.isAssignableFrom(List.class)) {
				// List类型
				fieldOfList(t, f);
			} else if (fc.isAssignableFrom(Set.class)) {
				// 集合类型
			} else {
				// 普通类型
				fieldOfGeneral(t, f, fc);
			}
		}
	}

	/**
	 * 处理非列表或集合类型的字段
	 * 
	 * @param t
	 * @param f
	 * @param fc
	 * @throws IllegalAccessException
	 * @throws Exception
	 */
	private static void fieldOfGeneral(Object t, Field f, Class<?> fc) throws IllegalAccessException, Exception {
		// 获取字段类型对应的实现类，可能有多个
		List<Class<?>> lst = RegTypeFactory.typesOfSuper(fc);
		if (lst != null && lst.size() > 0) {
			f.set(t, getBean(lst.get(0)));
		}
	}

	/**
	 * 处理列表类型的字段
	 * 
	 * @param t
	 * @param f
	 * @throws IllegalAccessException
	 */
	private static void fieldOfList(Object t, Field f) throws IllegalAccessException {
		// 获取list中元素的类型
		Type gType = f.getGenericType();
		ParameterizedType pType = (ParameterizedType) gType;
		Type eleType = pType.getActualTypeArguments()[0];
		Class<?> eleC = (Class<?>) eleType;

		// 获取List元素类型对应的实现类
		List<Class<?>> lst = RegTypeFactory.typesOfSuper(eleC);
		if (lst != null && lst.size() > 0) {
			// 列表类型
			f.set(t, lst.stream().map(n -> {
				try {
					return getBean(n);
				} catch (Exception e) {
					return null;
				}
			}).collect(Collectors.toList()));
		}
	}
}
