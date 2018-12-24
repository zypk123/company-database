package cy.its.vehTrack.mybatis.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ObjectToMap {
	public static Map<String, Object> beanToMap(Object entity) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		Field[] fields = getAllFields(entity.getClass());
		for (int i = 0; i < fields.length; i++) {
			String fieldName = fields[i].getName();
			if("UNDERLINE".equals(fieldName)){
				continue;
			}
			Object o = null;
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getMethodName = "get" + firstLetter + fieldName.substring(1);
			Method getMethod; 
			try {
				getMethod = entity.getClass().getMethod(getMethodName, new Class[] {});
				o = getMethod.invoke(entity, new Object[] {});
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (o != null) {
				parameter.put(fieldName, o);
			}
		}
		return parameter;
	}
	
	private static Field[] getAllFields(Class clazz){
		Field[] fields = clazz.getDeclaredFields();
		if(clazz.getSuperclass() != null){
			Field[] result = concatArray(fields, getAllFields(clazz.getSuperclass()));
			return result;
		}else{
			return fields;
		}
	}
	
	private static Field[] concatArray(Field[] a, Field[] b){
		Field[] fields = new Field[a.length + b.length];
		int index = 0;
		for(int i=0; i<a.length; i++,index++){
			fields[index] = a[i];
		}
		for(int i=0; i<b.length; i++,index++){
			fields[index] = b[i];
		}
		return fields;
	}
}
