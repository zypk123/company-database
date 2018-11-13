package com.huntkey.rx.sceo.serviceCenter.provider.orm.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/***********************************************************************
 * @author chenxj
 * 
 * @email: kaleson@163.com
 * 
 * @date : 2017年6月27日 上午10:24:55
 * 
 **********************************************************************/
public abstract class AbstractCriteria implements Criteria {

	// 1. 参数数据
	private JSONObject data;

	// 2. 操作的edm类名称
	private String edmName;

	// 3. 操作的物理表名称
	private String tableName;

	// 4. 查询的条件
	private JSONArray conditions;

	// 5. 需要返回的属性集合
	private JSONArray columns;

	// 6. 分页信息
	private JSONObject pagenation;

	// 7. 需要持久化的数据集合
	private JSONArray dataset;

	// 8. 需要入索引的字段
	private JSONArray esFileds;

	// 9. 排序属性
	private JSONArray orderBy;

	// 10. edmName对应的简称
	private String shortName;

	// 11. load 查询时需要用的id数组
	private JSONArray ids;

	// 主子关系表关联
	private String pid;
	//12. where 优先级查询
	private String conditionsInWhere;

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

	public JSONArray getColumns() {
		return columns;
	}

	public void setColumns(JSONArray columns) {
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

	public JSONArray getEsFileds() {
		return esFileds;
	}

	public void setEsFileds(JSONArray esFileds) {
		this.esFileds = esFileds;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public JSONArray getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(JSONArray orderBy) {
		this.orderBy = orderBy;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public JSONArray getIDs() {
		return ids;
	}

	public void setIDs(JSONArray ids) {
		this.ids = ids;
	}

	public String getConditionsInWhere() {
		return conditionsInWhere;
	}

	public void setConditionsInWhere(String conditionsInWhere) {
		this.conditionsInWhere = conditionsInWhere;
	}
}
