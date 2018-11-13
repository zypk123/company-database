package com.huntkey.rx.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.huntkey.rx.base.PropertyAnnotation;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by gaozhiy on 2017/11/20 0020.
 */
public class EntityUtils {
    private static String serviceMethodLoad = "load";
    private static String serviceMethodLoadPropertySet = "getAttrubuteSetByPID";
    private static String entityService = "ormServiceImpl";
    private static String entityPackagePath = "com.huntkey.rx.edm.entity.";
    private static String mainClassIdProperty = "id";

    public static Object getPropertyObj(Object obj,String propertyName){
        try {
            Field field = getSpecificField(obj,propertyName);
            field.setAccessible(true);
            Object propertyValueObj = field.get(obj);//属性值
            if(propertyValueObj == null){
                return null;
            }
            String propertyValue = propertyValueObj.toString();

            PropertyAnnotation propertyAnnotation = field.getAnnotation(PropertyAnnotation.class);//获取属性上的注解
            String className = propertyAnnotation.className();//属性className注解值
            String packagePathClass = entityPackagePath+className;//约定的路径
            Class entityClazz = Class.forName(packagePathClass);

            String serviceNameStr = entityService;//约定的值
            
            Object serviceObj = SpringContextHolderUtils.getBean(serviceNameStr);//service对象
            Class serviceClass = serviceObj.getClass();//属性对应的service Class
            Method[] methods = serviceClass.getMethods();
            for(Method method : methods){
                if(serviceMethodLoad.equalsIgnoreCase(method.getName())){
                    return method.invoke(serviceObj,entityClazz,propertyValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    private static Field getSpecificField(Object object,String propertyName){
        Class clazz = object.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null){
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        for(Field field : fieldList){
            if(propertyName.equalsIgnoreCase(field.getName())){
                return field;
            }
        }
        return null;
    }


    public static Object getPropertySetObjList(Object obj, String propertyName, String rootClass){
        Class mainClazz = obj.getClass();//主对象
        Field mainClazzIdField = getSpecificField(obj,mainClassIdProperty);//主对象的Id属性
        mainClazzIdField.setAccessible(true);
        String mainClazzIdValue = "";//主对象的属性集
        try {
            Object mainClassIdPropertyObj =  mainClazzIdField.get(obj);
            if(mainClassIdPropertyObj == null){
                return null;
            }else{
                mainClazzIdValue = mainClassIdPropertyObj.toString();
            }

            Field field = getSpecificField(obj,propertyName);
            field.setAccessible(true);
            PropertyAnnotation propertyAnnotation = field.getAnnotation(PropertyAnnotation.class);//获取属性上的注解
            String className = propertyAnnotation.className();//属性className注解值
            if(StringUtils.isBlank(className)){
                return null;
            }
            String packagePathClass = entityPackagePath+className;//约定的路径
            Class entityClazz = Class.forName(packagePathClass);//属性实体类

            if(!StringUtils.isBlank(rootClass)){
                packagePathClass = entityPackagePath+rootClass;//约定的路径
                mainClazz = Class.forName(packagePathClass);//主类
            }


            Object serviceObj = SpringContextHolderUtils.getBean(entityService);//service对象
            Class serviceClass = serviceObj.getClass();//属性对应的service Class
            Method[] methods = serviceClass.getMethods();
            for(Method method : methods){
                if(serviceMethodLoadPropertySet.equalsIgnoreCase(method.getName())){
                    return method.invoke(serviceObj,mainClazzIdValue,entityClazz,mainClazz);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    /*public static Object getPropertyObj(Object obj,String propertyName,int index){
        try {
            Field field = obj.getClass().getDeclaredField(propertyName);
            field.setAccessible(true);
            List<String> propertyValue = (List<String>) field.get(obj);
            String positionIdValue = propertyValue.get(index);

            PropertyAnnotation propertyAnnotation = field.getAnnotation(PropertyAnnotation.class);
            String className = propertyAnnotation.className();

            String serviceNameStr = className.substring(0,className.length()-6)+entityService;//service名称
            Object serviceObj = SpringContextHolderUtils.getBean(serviceNameStr);//service对象

            Class serviceClass = serviceObj.getClass();
            Method getMethod = serviceClass.getMethod(serviceMethodLoad,String.class);
            return getMethod.invoke(serviceObj,positionIdValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/
}
