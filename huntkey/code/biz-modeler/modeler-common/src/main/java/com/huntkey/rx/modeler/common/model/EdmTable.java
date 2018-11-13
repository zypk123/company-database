package com.huntkey.rx.modeler.common.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoucj on 2017/10/31.
 */
public class EdmTable {
    //表名
    private String tableName;
    //表名简称
    private String tableShortName;
    //主键列名
    private String primaryKey;
    //表描述
    private String remarks;
    //是否为类或者属性集
    private String flag;
    //字段集合
    private List<EdmColumn> columnList;

    public EdmTable(){
        this.tableName = "";
        this.tableShortName = "";
        this.primaryKey = "";
        this.remarks = "";
        this.flag = "1";
        this.columnList = new ArrayList<>();
    }

    public List<EdmColumn> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<EdmColumn> columnList) {
        this.columnList = columnList;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }


    public String getTableShortName() {
        return tableShortName;
    }

    public void setTableShortName(String tableShortName) {
        this.tableShortName = tableShortName;
    }
}
