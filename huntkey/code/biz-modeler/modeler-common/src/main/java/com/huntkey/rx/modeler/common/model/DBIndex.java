package com.huntkey.rx.modeler.common.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoucj on 2017/11/4.
 */
public class DBIndex {
    private String tableName;
    private String indexName;
    private String indexType;
    private List<String> columnList;

    public DBIndex() {
        this.tableName = "";
        this.indexName = "";
        this.indexType = "";
        this.columnList = new ArrayList<>();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public List<String> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<String> columnList) {
        this.columnList = columnList;
    }
}
