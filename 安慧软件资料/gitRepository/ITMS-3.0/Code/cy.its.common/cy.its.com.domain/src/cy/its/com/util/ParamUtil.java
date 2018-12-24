package cy.its.com.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import cy.its.com.domain.Criteria;

public class ParamUtil {

	@SuppressWarnings("rawtypes")
	private static HashMap<Class, Map<String, Field>> ClassDef = new HashMap<Class, Map<String, Field>>();

	public static Map<String, Object> parseParams(Criteria criteria) {
		
		Map<String, Object> paramMap = parseForMap(criteria); 
		
		int from = criteria.getFromIndex() + 1;
		int to = criteria.getToIndex() + 1;
		paramMap.put("from", from > 0 ? from : "");
		paramMap.put("to", to > 0 ? to : "");
		paramMap.put("orderName", criteria.getOrderName());
		paramMap.put("orderType", criteria.getOrderType());
		return paramMap;
	}

	public static Map<String,Object> parseParams(Object object) {
		if(object.getClass().getSuperclass() == Criteria.class){
			return parseParams(object);
		}

		return parseForMap(object);
	}
	
	private static Map<String, Object> parseForMap(Object criteria) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		Map<String, Field> tf = fieldOfClass(criteria.getClass());
		
		for (Entry<String, Field> kv : tf.entrySet()) {
			try {
				Field tFd = kv.getValue();
				if(tFd.get(criteria) != null)
				{
					paramMap.put(kv.getKey(), tFd.get(criteria));
				}
			} catch (Exception e) {
			}
		}
		
		return paramMap;
	}
	
	/**
	 * 查询类的所有属性
	 * 
	 * @param tClass
	 * @return
	 */
	private static Map<String, Field> fieldOfClass(
			Class<? extends Object> tClass) {
		if (!ClassDef.containsKey(tClass)) {
			try {
				ClassDef.put(tClass, extractFieldOfClass(tClass));
			} catch (Exception e) {
			}
		}

		return ClassDef.get(tClass);
	}
	
	/**
	 * 递归提取类的所有属性
	 * 
	 * @param c
	 * @param map
	 */
	private static Map<String, Field> extractFieldOfClass(Class<?> c
			) {
		if (c != Object.class) {
			Map<String, Field> map = new HashMap<String, Field>();
			for (Field field : c.getDeclaredFields()) {
				field.setAccessible(true);
				map.put(field.getName(), field);
			}
			
			return map;
		}
		
		return null;
	}


}
