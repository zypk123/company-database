package cy.its.com.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map.Entry;

import cy.its.platform.common.utils.Log4jFactoryProxy;

public class ObjectMapUtils {

	private static Log4jFactoryProxy log = Log4jFactoryProxy.getSingleton(ObjectMapUtils.class);
	
	@SuppressWarnings("rawtypes")
	private static HashMap<Class, HashMap<String, Field>> ClassDef = new HashMap<Class, HashMap<String, Field>>();

	/**
	 * 将源对象的属性值传递到目标对象具有相同名称的属性上
	 * 
	 * @param target
	 * @param src
	 */
	public static void parseObject(Object target, Object src) {
		parseObject(target, src, false);
	}

	/**
	 * 将源对象的属性值传递到目标对象具有相同名称的属性上（覆盖式）
	 */
	public static void parseObjectOverWrite(Object target, Object src) {
		parseObject(target, src, true);
	}
	
	/**
	 * 将源对象的属性值传递到目标对象具有相同名称的属性上
	 * @param target
	 * @param src
	 * @param overWrite 是否覆盖
	 */
	public static void parseObject(Object target, Object src, boolean overWrite) {
		if (target == null || src == null) {
			return;
		}

		// 获取目标对象的属性
		HashMap<String, Field> tf = fieldOfClass(target.getClass());
		// 获取源对象的属性
		HashMap<String, Field> sf = fieldOfClass(src.getClass());
		// 属性值传递
		for (Entry<String, Field> kv : tf.entrySet()) {
			String tFn = kv.getKey();
			Field tFd = kv.getValue();
			try {
				if (sf.containsKey(tFn) && sf.get(tFn).getClass() == tFd.getClass()) {
					if (overWrite) {
						// 源对象包含同名属性且属性类型相同且目标对象 属性值为空
						// 将源属性值传递到目标属性
						kv.getValue().set(target, sf.get(tFn).get(src));
					} else {
						if (tFd.get(target) == null) {
							kv.getValue().set(target, sf.get(tFn).get(src));
						}
					}
				}
			} catch (Exception e) {
				log.debug(e.getMessage());
			}
		}

	}
	
	/**
	 * 递归提取类的所有属性
	 * 
	 * @param c
	 * @param map
	 */
	private static void extractFieldOfClass(Class<?> c,
			HashMap<String, Field> map) {
		if (c != Object.class) {
			for (Field field : c.getDeclaredFields()) {
				field.setAccessible(true);
				map.put(field.getName(), field);
			}

			if (c.getSuperclass() != null) {
				extractFieldOfClass(c.getSuperclass(), map);
			}
		}
	}

	/**
	 * 提取类的所有属性
	 * 
	 * @param c
	 * @return
	 */
	private static HashMap<String, Field> extractFieldOfClass(
			Class<? extends Object> c) {
		HashMap<String, Field> map = new HashMap<String, Field>();
		extractFieldOfClass(c, map);
		return map;
	}

	/**
	 * 查询类的所有属性
	 * 
	 * @param tClass
	 * @return
	 */
	private static HashMap<String, Field> fieldOfClass(
			Class<? extends Object> tClass) {
		if (!ClassDef.containsKey(tClass)) {
			try {
				ClassDef.put(tClass, extractFieldOfClass(tClass));
			} catch (Exception e) {
			}
		}

		return ClassDef.get(tClass);
	}
}
