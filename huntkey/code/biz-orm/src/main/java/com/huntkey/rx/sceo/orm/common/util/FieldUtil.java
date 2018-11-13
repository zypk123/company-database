package com.huntkey.rx.sceo.orm.common.util;

import com.huntkey.rx.sceo.orm.common.constant.Constant;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumnName;
import org.apache.ibatis.reflection.ReflectionException;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by licj on 2017/11/17.
 * 字段工具类
 */
public class FieldUtil {

    /**
     * 根据列名获取字段对象,无匹配的字段抛异常
     *
     * @param clazz
     * @param columnName
     * @return
     */
    public static Field getFieldByColumnName(Class<?> clazz, String columnName) {
        Map<String, Field> mapping = getColumnFieldMapping(clazz);
        Field field = mapping.get(columnName);
        if (field == null) {
            throw new ReflectionException("没有找到列:" + columnName + " from " + clazz.getName());
        }
        return field;
    }

    /**
     * 获取主键字段
     *
     * @param clazz
     * @return
     */
    public static Field getPrimaryField(Class<?> clazz) {
        Field field = null;
        while (clazz != null) {
            try {
                field = clazz.getDeclaredField(EdmSysColumnName.ID);
                break;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        return field;
    }

    /**
     * 获取creuser 字段
     *
     * @param clazz
     * @return
     */
    public static Field getCreuserField(Class<?> clazz) {
        Field field = null;
        while (clazz != null) {
            try {
                field = clazz.getDeclaredField(EdmSysColumnName.CREUSER);
                break;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        return field;
    }

    public static Field getCretimeField(Class<?> clazz) {
        Field field = null;
        while (clazz != null) {
            try {
                field = clazz.getDeclaredField(EdmSysColumnName.CRETIME);
                break;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        return field;
    }

    /**
     * 根据Field 的名称获取Field
     *
     * @param clazz
     * @param fieldName
     * @return
     */
    public static Field getFieldByName(Class<?> clazz, String fieldName) {
        Field field = null;
        while (clazz != null) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        return field;
    }

    /**
     * 得到列名-字段之间的映射关系(columnName,Field)
     *
     * @param clazz
     * @return
     */
    public static Map<String, Field> getColumnFieldMapping(Class<?> clazz) {
        List<Field> fieldList = getPersistentFields(clazz);
        if (fieldList == null || fieldList.size() == 0) {
            return null;
        }
        Map<String, Field> mapping = new HashMap<String, Field>();
        for (Field field : fieldList) {
            mapping.put(PersistentUtil.getColumnName(field), field);
        }
        return mapping;
    }


    /**
     * 获取所有持久化字段
     *
     * @param clazz
     * @return
     */
    public static List<Field> getPersistentFields(Class<?> clazz) {
        List<Field> list = new ArrayList<Field>();
        while (!Object.class.equals(clazz) && clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                //过滤掉序列化字段以及List类型字段（属性集）
                if (!Constant.SERIAL_FIELD.equals(field.getName()) && !List.class.equals(field.getType())) {
                    list.add(field);
                }
            }
            clazz = clazz.getSuperclass();
        }
        return list;
    }

    /**
     * 获取List 类型的 Field
     *
     * @param clazz
     * @return
     */
    public static List<Field> getFieldInstanceOfList(Class<?> clazz) {
        List<Field> list = new ArrayList<Field>();
        while (!Object.class.equals(clazz) && clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (!Constant.SERIAL_FIELD.equals(field.getName()) && List.class.equals(field.getType())) {
                    //返回List 的Field
                    list.add(field);
                }
            }
            clazz = clazz.getSuperclass();
        }
        return list;
    }


    /**
     * 设置对象的字段值( mysql tinyint(1)返回的是Boolean类型)
     *
     * @param t
     * @param field
     * @param value
     * @param <T>
     * @throws Exception
     */
    public static <T> void setFieldValue(T t, Field field, Object value) throws Exception {
        ReflectionUtils.makeAccessible(field);
        field.set(t, value);
    }

    /**
     * 获取对象的字段值,空值返回null
     *
     * @param t
     * @param field
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> Object getFieldValue(T t, Field field) throws Exception {
        ReflectionUtils.makeAccessible(field);
        return field.get(t);
    }

}
