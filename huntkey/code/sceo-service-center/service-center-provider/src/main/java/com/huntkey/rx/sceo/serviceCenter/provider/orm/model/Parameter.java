package com.huntkey.rx.sceo.serviceCenter.provider.orm.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * Created by zhanglb on 2017/6/29.
 */
public class Parameter extends JSONObject implements Serializable {

    // 1. 参数数据
    private JSONObject data;

    // 2. 操作的edm类名称
    private String edmName;

    // 3. 操作的物理表名称
    private String tableName;

    // 4. 查询的条件
    private JSONArray conditions;

    // 5. 需要返回的属性集合
    private String[] columns;

    // 6. 分页信息
    private JSONObject pagenation;

    // 7. 需要持久化的数据集合
    private JSONArray dataset;

    // 8. 需要入索引的字段
    private String [] esFileds;

    // 9. 需要排序的字段
    private String[] orders;

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public String getEdmName() {
        return edmName;
    }

    public void setEdmName(String edmName) {
        this.edmName = edmName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public JSONArray getConditions() {
        return conditions;
    }

    public void setConditions(JSONArray conditions) {
        this.conditions = conditions;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public JSONObject getPagenation() {
        return pagenation;
    }

    public void setPagenation(JSONObject pagenation) {
        this.pagenation = pagenation;
    }

    public JSONArray getDataset() {
        return dataset;
    }

    public void setDataset(JSONArray dataset) {
        this.dataset = dataset;
    }

    public String[] getEsFileds() {
        return esFileds;
    }

    public void setEsFileds(String[] esFileds) {
        this.esFileds = esFileds;
    }

    public String[] getOrders() {
        return orders;
    }

    public void setOrders(String[] orders) {
        this.orders = orders;
    }

    @Override
    public String toString(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", data);
        jsonObject.put("edmName", edmName);
        jsonObject.put("tableName", tableName);
        jsonObject.put("conditions", conditions);
        jsonObject.put("columns", columns);
        jsonObject.put("pagenation", pagenation);
        jsonObject.put("dataset", dataset);
        jsonObject.put("esFileds", esFileds);
        jsonObject.put("orders", orders);
        return jsonObject.toString();
    }
}
