package com.huntkey.rx.sceo.serviceCenter.provider.orm.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/***********************************************************************
 * @author chenxj
 * 
 * @email: kaleson@163.com
 * 
 * @date : 2017年6月22日 下午5:42:16
 * 
 **********************************************************************/
public interface Criteria {

	public final static String NODE_EDMNAME = "edmName";

	public final static String NODE_COLUMNS = "columns";

	public final static String NODE_ESFILEDS = "esFileds";

	public final static String NODE_IDS = "ids";

	public final static String NODE_CONDITIONS = "conditions";

	public final static String NODE_PAGENATION = "pagenation";

	public final static String NODE_ORDERBY = "orderBy";

	public final static String NODE_DATASET = "dataset";

	public final static String NODE_WHERE = "where";

	/**
	 * 获取json格式查询条件数据
	 * 
	 * @return
	 */
	public JSONObject getData();

	/**
	 * 获取 edm 的名称
	 * 
	 * @return
	 */
	public String getEdmName();

	/**
	 * 获取物理表的名称
	 * 
	 * @return
	 */
	public String getTableName();

	/**
	 * 获取条件
	 * 
	 * @return
	 */
	public JSONArray getConditions();

	/**
	 * 获取需要查询的属性列
	 * 
	 * @return
	 */
	public JSONArray getColumns();

	/**
	 * 分页信息
	 * 
	 * @return
	 */
	public JSONObject getPagenation();

	/**
	 * 需要持久化的数据对象集合
	 * 
	 * @return
	 */
	public JSONArray getDataset();

	/**
	 * 获取需要建立索引的属性
	 * 
	 * @return
	 */
	public JSONArray getEsFileds();

	/**
	 * 获取需要建立索引的属性
	 *
	 * @return
	 */
	public JSONArray getOrderBy();

	/**
	 * 获取类的简称
	 * 
	 * @return
	 */
	public String getShortName();
	/**
	 * 获取load的id参数
	 *
	 * @return
	 */
	public JSONArray getIDs();
	/**
	 * 获取 search where 参数
	 */
	public String getConditionsInWhere();
}
