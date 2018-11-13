package com.huntkey.rx.util;

import com.huntkey.rx.base.PropertyAnnotation;

import java.lang.reflect.Field;

/**
 * 注解工具
 * Created by gaozhiy on 2017/11/16 0016.
 */
public class PropertyAnnotationUtils {
	
	/**
	 * 获取属性在edm中定义的元数据
	 * @param clazz：实体类
	 * @param fieldName：属性名称
	 * @param propertyAnnotationEnum：属性的配置信息
	 * 
	 * @return 指定的元数据
	 */
    public static String getPropertyAnnotation(Class clazz,String fieldName,PropertyAnnotationEnum propertyAnnotationEnum){
        try {
            Field field = clazz.getDeclaredField(fieldName);
            switch (propertyAnnotationEnum){
                case fomula: return getFomulaAnnotation(field);
                case limitFomula: return getLimitFomulaAnnotation(field);
                case dataType : return getDataTypeAnnotation(field);
                case relatedBy : return getRelatedByAnnotation(field);
                case dataClass : return getDataClassAnnotation(field);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getFomulaAnnotation(Field field){
        PropertyAnnotation propertyAnnotation = field.getAnnotation(PropertyAnnotation.class);
        return propertyAnnotation.fomula();
    }

    private static String getLimitFomulaAnnotation(Field field){
        PropertyAnnotation propertyAnnotation = field.getAnnotation(PropertyAnnotation.class);
        return propertyAnnotation.limitFomula();
    }

    private static String getDataTypeAnnotation(Field field){
        PropertyAnnotation propertyAnnotation = field.getAnnotation(PropertyAnnotation.class);
        return propertyAnnotation.limitFomula();
    }
    private static String getRelatedByAnnotation(Field field){
        PropertyAnnotation propertyAnnotation = field.getAnnotation(PropertyAnnotation.class);
        return propertyAnnotation.relatedBy();
    }

    private static String getDataClassAnnotation(Field field){
        PropertyAnnotation propertyAnnotation = field.getAnnotation(PropertyAnnotation.class);
        return propertyAnnotation.dataClass();
    }

}
