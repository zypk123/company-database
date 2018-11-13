package com.huntkey.rx.sceo.orm.common.model;

import com.huntkey.rx.sceo.orm.common.util.FieldUtil;
import com.huntkey.rx.sceo.orm.common.util.PersistentUtil;

import java.lang.reflect.Field;

/**
 * Created by licj on 2017/11/17.
 */
public class OrmColumnFactory {

    public static <T> OrmColumn createSQLColumn(T t, Field field) throws Exception {
        String columnName = PersistentUtil.getColumnName(field);
        Object columnValue = FieldUtil.getFieldValue(t, field);
        Class<?> fieldType = field.getType();
        return createSQLColumn(columnName, columnValue, matchJdbcType(fieldType));
    }

    public static OrmColumn createSQLColumn(String columnName, Object columnValue, String jdbcType) {
        OrmColumn column = new OrmColumn();
        column.setColumnName(columnName);
        column.setColumnValue(columnValue);
        column.setJdbcType(jdbcType);
        return column;
    }

    public static String matchJdbcType(Class<?> fieldType) {
        if (fieldType.isEnum()) {
            //枚举类型存ordinal
            return "Integer";
        }
        if (String.class.equals(fieldType)) {
            //CLOB未完成
            return "VARCHAR";
        }
        if (Integer.class.equals(fieldType) || Integer.TYPE.equals(fieldType)) {
            return "INTEGER";
        }
        if (Double.class.equals(fieldType) || Double.TYPE.equals(fieldType)) {
            return "DOUBLE";
        }
        if (Float.class.equals(fieldType) || Float.TYPE.equals(fieldType)) {
            return "FLOAT";
        }
        if (java.util.Date.class.isAssignableFrom(fieldType)) {
            return "TIMESTAMP";
        }
        //CLOB BLOB未完成
        return null;
    }

}
