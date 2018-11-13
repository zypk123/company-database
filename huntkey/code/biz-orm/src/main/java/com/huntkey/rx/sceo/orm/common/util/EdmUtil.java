package com.huntkey.rx.sceo.orm.common.util;

import com.huntkey.rx.base.BaseEntity;
import com.huntkey.rx.base.LinkdetailEntity;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.base.PropertyBaseEntity;
import com.huntkey.rx.edm.entity.Constants;
import com.huntkey.rx.edm.entity.EcosystemEntity;
import com.huntkey.rx.edm.entity.EdmEntity;
import com.huntkey.rx.sceo.orm.common.type.EdmFunctionEnum;
import com.huntkey.rx.sceo.orm.common.type.SQLCurdEnum;
import com.huntkey.rx.sceo.orm.common.type.TableEnum;
import com.huntkey.rx.sceo.orm.exception.ORMException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by linziy on 2017/12/6.
 * Edm 工具类
 */
public class EdmUtil {

    protected static final String ENTITY = "Entity";
    protected static final String BIZCLASSPACKAGEPATH = "com.huntkey.rx.edm.entity.";

    /**
     * 判断是否属性集
     *
     * @param field 属性的
     * @return
     */
    protected final static boolean isAttributeSet(Field field) {
        return List.class.equals(field.getType());
    }

    /**
     * 根据属性字段获取:set,get,load 函数名称
     *
     * @param fieldName       属性名
     * @param edmFunctionEnum edm函数的类型
     * @return
     */
    protected final static String getEdmMethodName(String fieldName, EdmFunctionEnum edmFunctionEnum) {
        String functionName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        functionName = edmFunctionEnum.getFunctionPreStuff() + functionName;
        return functionName;
    }
    //////////////////////////////////////////////Edm 通用函数//////////////////////////////////////////////////////////
    //////////////////////////////////////////////edm 类的公用方法//////////////////////////////////////////////////////

    /**
     * 获取Edm类名
     *
     * @param edmClass BaseEntity实体类
     * @return
     * @throws Exception
     */
    public static String getEdmClassName(Class<? extends BaseEntity> edmClass) {
        String className = edmClass.getSimpleName();
        className = className.substring(0, className.length() - 6).toLowerCase();
        return className;
    }

    /**
     * 改成驼峰式
     *
     * @param edmcNameEn 类英文名,不包含Entity后缀
     * @return
     */
    public static String convertClassName(String edmcNameEn) {
        OrmAccessUtil.accessNullOrEmputy(edmcNameEn);
        if (edmcNameEn.length() <= 1) {
            return edmcNameEn;
        }
        StringBuilder classNameStringBuilder = new StringBuilder("");
        String[] strs = edmcNameEn.split("_");
        for (int i = 0; i < strs.length; i++) {
            classNameStringBuilder.append(strs[i].substring(0, 1).toUpperCase())
                    .append(strs[i].substring(1));
        }
        return classNameStringBuilder.toString() + ENTITY;
    }

    /**
     * 获取edm实体类，
     *
     * @param edmcNameEn edm 实体类在modeler的英文名称
     * @return
     * @throws ClassNotFoundException
     */
    public static Class<? extends BaseEntity> getEntityClassByEdmName(String edmcNameEn)
            throws ClassNotFoundException {
        OrmAccessUtil.accessNullOrEmputy(edmcNameEn);
        String className = EdmUtil.BIZCLASSPACKAGEPATH + convertClassName(edmcNameEn);
        Class<? extends BaseEntity> entityClass = (Class<? extends BaseEntity>) Class.forName(className);
        return entityClass;
    }


    /**
     * 判断是否link able
     *
     * @param entityClazz edm 的类
     * @return
     */
    public static boolean isLinkTable(Class<? extends BaseEntity> entityClazz) {
        if (LinkdetailEntity.class.equals(entityClazz)) {
            return true;
        }
        return false;
    }

    /**
     * 判断实体类对应的表类型
     *
     * @param enityClazz edm 的类
     * @return
     */
    public static TableEnum getEntityTableType(Class<? extends BaseEntity> enityClazz) {
        if (isLinkTable(enityClazz)) {
            return TableEnum.Link;
        } else if (EdmEntity.class.isAssignableFrom(enityClazz)
                || EcosystemEntity.class.isAssignableFrom(enityClazz)) {
            return TableEnum.Main;
        } else if (PropertyBaseEntity.class.isAssignableFrom(enityClazz)) {
            return TableEnum.AttributeSet;
        } else {
            return TableEnum.Others;
        }
    }


    /**
     * link 表组装规则
     *
     * @param edmClazz edm 的类
     * @return
     * @throws Exception
     */
    public static String getLinkTableName(Class<? extends BaseEntity> edmClazz) throws Exception {
        String entityClassName = edmClazz.getSimpleName();
        if (entityClassName == null || !entityClassName.endsWith(ENTITY)) {
            throw new Exception("The name of the class of the edm entities is empty");
        }
        String tableName = entityClassName.substring(0, entityClassName.length() - 6);
        tableName = tableName + TableEnum.Link.getSuffix();
        return tableName.toLowerCase();
    }


    /**
     * 获取实体表名
     *
     * @param entityClazz edm 的类
     * @return
     * @throws Exception
     */
    public static String getEntityTableName(Class<? extends BaseEntity> entityClazz) throws Exception {
        String className = entityClazz.getSimpleName();
        Field entityField = Constants.class.getField(className);
        Object obj = entityField.get(Constants.class);
        OrmAccessUtil.accessNull(obj);
        return obj.toString();
    }

    /**
     * 获取属性集的表名
     *
     * @param entityClazz edm 的类
     * @return
     * @throws Exception
     */
    public static String getAttributeTableName(Class<? extends BaseEntity> entityClazz) throws Exception {
        return getEntityTableName(entityClazz);
    }

    /**
     * 获取从属表的表名
     *
     * @param entityClazz 主类
     * @param edmClazz    edm 的类
     * @return
     * @throws Exception
     */
    public static String getDependentEntityTableName(Class<? extends BaseEntity> entityClazz,
                                                     Class<? extends BaseEntity> edmClazz) throws Exception {
        String tableName = null;
        if (isLinkTable(entityClazz)) {
            //联动属性表
            tableName = getLinkTableName(edmClazz);
        } else {
            //主类或者属性集表
            tableName = getAttributeTableName(entityClazz);
        }
        return tableName;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////Edm的动态映射//////////////////////////////////////////////////////////

    /**
     * 设置类普通属性值
     *
     * @param object     edm 对象
     * @param fieldName  edm属性名
     * @param fieldValue edm 属性值
     * @throws Exception
     */
    public final static void setFieldValue(Object object, String fieldName, Object fieldValue) throws Exception {
        Class<?> clazz = object.getClass();
        Field field = FieldUtil.getFieldByName(clazz, fieldName);
        if (field == null || isAttributeSet(field)) {
            throw new ORMException("It is not a common-attribute field.");
        }
        FieldUtil.setFieldValue(object, field, fieldValue);
    }

    /**
     * 设置EDM 类普通属性值
     *
     * @param t          edm 泛型对象
     * @param fieldName  edm 普通属性字段
     * @param fieldValue edm 普通属性值
     * @param <T>
     * @throws Exception
     */
    public final static <T extends BaseEntity> void setFieldValue(T t, String fieldName, Object fieldValue) throws Exception {
        Class clazz = t.getClass();
        Field field = FieldUtil.getFieldByName(clazz, fieldName);
        if (field == null || isAttributeSet(field)) {
            throw new ORMException("It is not a common-attribute field.");
        }
        FieldUtil.setFieldValue(t, field, fieldValue);
    }

    /**
     * 返回普通属性值
     *
     * @param object    edm 对象
     * @param fieldName edm 普通属性名称
     * @return
     * @throws Exception
     */
    public final static Object getFieldValue(Object object, String fieldName) throws Exception {
        Class clazz = object.getClass();
        Field field = FieldUtil.getFieldByName(clazz, fieldName);
        if (field == null || isAttributeSet(field)) {
            throw new ORMException("It is not a common-attribute field.");
        }
        return FieldUtil.getFieldValue(object, field);
    }

    /**
     * 返回普通属性值
     *
     * @param t         edm 的泛型 对象
     * @param fieldName 属性名称
     * @return
     * @throws Exception
     */
    public final static <T extends BaseEntity> Object getFieldValue(T t, String fieldName) throws Exception {
        Class clazz = t.getClass();
        Field field = FieldUtil.getFieldByName(clazz, fieldName);
        if (field == null || isAttributeSet(field)) {
            throw new ORMException("It is not a common-attribute field.");
        }
        return FieldUtil.getFieldValue(t, field);
    }

    /**
     * 根据属性集的名称 t 对象的属性集
     *
     * @param t          edm 泛型对象
     * @param properties 属性集对象list
     * @param fieldName  属性名
     * @param <T>
     * @return
     * @throws Exception
     */
    public final static <T extends BaseEntity> T setPropertiesByName(T t, List<? extends PropertyBaseEntity> properties, String fieldName) throws Exception {
        Class<? extends BaseEntity> clazz = t.getClass();
        Field field = FieldUtil.getFieldByName(clazz, fieldName);
        if (field == null || !isAttributeSet(field)) {
            throw new ORMException("It is not a common-attribute field.");
        }
        FieldUtil.setFieldValue(t, field, properties);
        return t;
    }

    /**
     * 获取属性集
     *
     * @param t         edm 泛型对象
     * @param fieldName 属性名称
     * @param <T>       泛型类型
     * @return 返回值
     * @throws Exception
     */
    public final static <T extends BaseEntity> List<Object> getAttributeSetObjectByName(T t, String fieldName)
            throws Exception {
        Class<? extends BaseEntity> clazz = t.getClass();
        Field field = FieldUtil.getFieldByName(clazz, fieldName);
        if (field == null || !isAttributeSet(field)) {
            throw new ORMException("It is not a attribute-set field.");
        }
        return (List<Object>) FieldUtil.getFieldValue(t, field);
    }

    /**
     * 获取返回的属性集
     *
     * @param t         泛型对象
     * @param fieldName 属性名称
     * @param <T>       泛型类型
     * @return 返回值
     * @throws Exception
     */
    public final static <T extends BaseEntity> List<? extends PropertyBaseEntity> getAttributeSetByName(T t, String fieldName)
            throws Exception {
        List<Object> attributeSetObject = getAttributeSetObjectByName(t, fieldName);
        Class propertyEntityClass = EdmUtil.getPropertyEntityClass(t.getClass(), fieldName);
        List<? extends PropertyBaseEntity> attributeSets = restoreObjects(propertyEntityClass, attributeSetObject);
        return attributeSets;
    }

    /**
     * 根据属性名称加载映射对象
     *
     * @param t         泛型对象
     * @param fieldName 属性名称
     * @param <T>       泛型类型
     * @return
     * @throws Exception
     */
    public final static <T extends BaseEntity> List<Object> loadMappingPropertiesByName(T t, String fieldName) throws Exception {
        Class<? extends BaseEntity> clazz = t.getClass();
        String functionName = getEdmMethodName(fieldName, EdmFunctionEnum.LOAD);
        Method method = clazz.getMethod(functionName);
        return (List<Object>) method.invoke(t, null);//调用方法 load 方法
    }

    /**
     * 根据clazz类型对Object对象还原
     *
     * @param clazz  Object 还原的类型
     * @param object Object 还原
     * @param <T>    泛型类型
     * @return
     * @throws Exception
     */
    public final static <T extends BaseEntity> T restoreObject(Class<T> clazz, Object object) throws Exception {
        OrmAccessUtil.accessNull(object);
        return PersistentUtil.parseToBean(objectToMap(clazz, object), clazz);
    }

    /**
     * Object List 转 对应的具体类List
     *
     * @param clazz 原类
     * @param list  实体对象
     * @param <T>   泛型类型
     * @return
     * @throws Exception
     */
    public final static <T extends BaseEntity> List<T> restoreObjects(Class<T> clazz, List<Object> list) throws Exception {
        List<T> rsList = new ArrayList<T>();
        for (Object obj : list) {
            T t = restoreObject(clazz, obj);
            rsList.add(t);
        }
        return rsList;
    }

    /**
     * Object 转为Map 容器
     *
     * @param clazz  原类
     * @param object 实体对象
     * @return
     * @throws Exception
     */
    public final static Map<String, Object> objectToMap(Class<? extends BaseEntity> clazz, Object object) throws Exception {
        OrmAccessUtil.accessNull(object);
        Map<String, Field> mapping = FieldUtil.getColumnFieldMapping(clazz);
        Map<String, Object> mapper = new HashMap<String, Object>();
        for (Map.Entry<String, Field> entry : mapping.entrySet()) {
            Object valueObject = FieldUtil.getFieldValue(object, entry.getValue());
            mapper.put(entry.getKey(), valueObject);
        }
        return mapper;
    }


    /**
     * 获取edm实体类
     *
     * @param edmSimpleClassName edm 实体类简称
     * @return
     * @throws ClassNotFoundException
     */
    protected static Class<? extends BaseEntity> getEntityClassByName(String edmSimpleClassName)
            throws ClassNotFoundException {
        String className = BIZCLASSPACKAGEPATH + edmSimpleClassName;
        Class<? extends BaseEntity> entityClass = (Class<? extends BaseEntity>) Class.forName(className);
        return entityClass;
    }


    /**
     * 获取属性集或者关联对象的实体类型
     *
     * @param entityClazz 实体类型
     * @param fieldName   属性名称
     * @return
     */
    public final static Class<? extends BaseEntity> getPropertyEntityClass(Class<? extends BaseEntity> entityClazz, String fieldName)
            throws Exception {
        Field field = FieldUtil.getFieldByName(entityClazz, fieldName);
        if (field.isAnnotationPresent(PropertyAnnotation.class)) {
            PropertyAnnotation propertyAnnotation = (PropertyAnnotation) field.getAnnotation(PropertyAnnotation.class);
            String className = propertyAnnotation.className();
            className = className.trim();
            return getEntityClassByName(className);
        } else {
            return null;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////edm 属性集//////////////////////////////////////////////////////////////////////////

    /**
     * 添加属性集类的特有字段
     *
     * @param mainClazz   主表类类型
     * @param edmEntity   属性集的父对象
     * @param t           属性集对象
     * @param sqlCurdEnum sql 操作的类型
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T extends PropertyBaseEntity> T setPropertyBaseEntitySysColumns(
            Class<? extends BaseEntity> mainClazz, BaseEntity edmEntity, T t, SQLCurdEnum sqlCurdEnum)
            throws Exception {
        String className = getEdmClassName(mainClazz);
        if (sqlCurdEnum.equals(sqlCurdEnum.INSERT)) {
            t.setPid(edmEntity.getId());
            t.setClassName(className);
            t.setCreuser(edmEntity.getModuser());
        } else if (sqlCurdEnum.equals(sqlCurdEnum.UPDATE)) {
            t.setPid(edmEntity.getId());
            t.setClassName(className);
            t.setModuser(edmEntity.getModuser());
        }
        return t;
    }

    /**
     * 添加属性集类的特有字段
     *
     * @param mainClazz            所属主类
     * @param edmEntity            属性集的上级对象
     * @param propertyBaseEntities 属性集
     * @param sqlCurdEnum          sql的操作类型
     * @param <T>
     * @return 上级对象传递给属性集对象的数据
     * @throws Exception
     */
    public static <T extends PropertyBaseEntity> List<T> setPropertyBaseEntitiesSysColumns(
            Class<? extends BaseEntity> mainClazz, BaseEntity edmEntity, List<T> propertyBaseEntities, SQLCurdEnum sqlCurdEnum)
            throws Exception {
        for (T t : propertyBaseEntities) {
            setPropertyBaseEntitySysColumns(mainClazz, edmEntity, t, sqlCurdEnum);
        }
        return propertyBaseEntities;
    }


//    public final static void main(String[] args) {
//        String id = "xxxxx";
//        try {
//            //动态创建类对象
//            Object object = EmployeeEntity.class.newInstance();
//            PersistentUtil.setFieldValue(object, Constant.ID, id);
//            //Object 还原类
//            EmployeeEntity employeeEntity = restoreObject(EmployeeEntity.class, object);
//            //普通测试
//            EmployeeEntity employeeEntity = new EmployeeEntity();
//            PersistentUtil.setFieldValue(employeeEntity, Constant.ID, id);
//            Object obj = PersistentUtil.getFieldValue(employeeEntity, Constant.ID);
//            System.out.println(obj.getClass().getName());
//            System.out.println(obj);
//            return;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
