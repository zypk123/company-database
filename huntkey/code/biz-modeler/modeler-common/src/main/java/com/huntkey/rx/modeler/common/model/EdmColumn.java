package com.huntkey.rx.modeler.common.model;


public class EdmColumn{

    //表名称
    private String tableName;

    //字段名称
    private String columnName;

    //字段类型
    private String typeName;

    //字段长度
    private String columnSize;

    //字段描述
    private String remarks;

    //字段默认值
    private String columnDefault;

    //字段是否可空
    private String isNullable;

    //字段做删除时，存储字段修改后名称
    private String delName;

    public EdmColumn() {
        this.tableName = "";
        this.columnName = "";
        this.typeName = "";
        this.columnSize = "";
        this.remarks = "";
        this.columnDefault = "";
        this.isNullable = "";
        this.delName = "";
    }

    public String getDelName() {
        return delName;
    }

    public void setDelName(String delName) {
        this.delName = delName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(String columnSize) {
        this.columnSize = columnSize;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getColumnDefault() {
        return columnDefault;
    }

    public void setColumnDefault(String columnDefault) {
        this.columnDefault = columnDefault;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EdmColumn edmColumn = (EdmColumn) o;

        if (!typeName.equals(edmColumn.typeName)) return false;
        if (!columnSize.equals(edmColumn.columnSize)) return false;
        if (!columnDefault.equals(edmColumn.columnDefault)) return false;
        return remarks.equals(edmColumn.remarks);
    }

    @Override
    public int hashCode() {
        int result = typeName.hashCode();
        result = 31 * result + columnSize.hashCode();
        result = 31 * result + remarks.hashCode();
        result = 31 * result + columnDefault.hashCode();
        return result;
    }
}