package com.huntkey.rx.sceo.orm.common.model;

/**
 * Created by licj on 2017/11/17.
 */
public class OrmColumn {

    private String columnName;

    private Object columnValue;

    private String jdbcType;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Object getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(Object columnValue) {
        this.columnValue = columnValue;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }
}
