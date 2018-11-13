package com.huntkey.rx.sceo.serviceCenter.common.model;

/***********************************************************************
 * @author chenxj
 * 
 * @email: kaleson@163.com
 * 
 * @date : 2017年8月17日 下午5:46:41
 * 
 **********************************************************************/

// 定义参数节点中的名称
public interface NodeConstant {

	// 查询列节点
	public final static String COLUMNS = "columns";

	// 查询条件节点
	public final static String CONDITIONS = "conditions";

	// 分页节点
	public final static String PAGENATION = "pagenation";

	// 排序节点
	public final static String ORDERBY = "orderBy";

	// edmName 节点
	public final static String EDMNAME = "edmName";

	// dataSource 节点
	public final static String DATASOURCE = "dataSource";

	// dataSource 节点
	public final static String SESSIONID = "sessionId";

	// search节点
	public final static String SEARCH = "search";

	public final static String ATTR = "attr";

	public final static String OPERATOR = "operator";

	public final static String VALUE = "value";

	public final static String TYPE = "type";

	// load 接口中的 ids 节点
	public final static String IDS = "ids";

	public final static String SORT = "sort";

	// 新增、修改接口中的 params 节点
	public final static String PARAMS = "params";
	//数据库主键字段(默认)
	public static String ID = "id";
	//属性表关联字段(默认)
	public static String PID = "pid";

	public static String DATASET = "dataset";

	public static String START_PAGE = "startPage";

	public static String ROWS = "rows";

	public static String ES_FIELDS = "esFileds";

	public static String WHERE = "where";
	//查询返回的结果
	//查询的表名
	public final static String TABLENAME = "tableName";
	public final static String TOTALSIZE = "totalSize";
	public final static String SIZE = "size";

}
