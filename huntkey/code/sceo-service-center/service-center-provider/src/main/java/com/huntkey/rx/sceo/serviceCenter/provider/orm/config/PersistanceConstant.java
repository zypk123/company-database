package com.huntkey.rx.sceo.serviceCenter.provider.orm.config;

/**
 * Created by zhanglb on 2017/6/28.
 */
public class PersistanceConstant {

	// 条件where连接，目前只考虑and关系
	public static String P_AND = " AND ";

	// Id字段，默认Id是每个表主键，如果多条件查询或者删除，有Id
	public static String P_ID = "id";

	// 常量1
	public static int P_ONE = 1;

	// 常量4
	public static int P_FOUR = 4;

	// 定义常量tablename用户传给mapper.xml中用
	public static String TABLE_NAME = "tableName";

	// 定义常量condition用户传给mapper.xml中用
	public static String P_CONDITION = "condition";

	// 定义常量columns用户传给mapper.xml中用
	public static String P_COLUMNS = "columns";

	// 定义常量setCondition用户传给mapper.xml中用
	public static String SET_CONDITION = "setCondition";

	public static String P_LIMIT  = "limit";

	// mysql表都又有个is_del字段，0：有效，1：删除
	public static String SET_DELETE_CONDITION = "is_del = '1'";

	public static String ORDER_BY_CONDITION = "orderByCondition";

	public static String P_ROWS = "rows";

	// 添加时间
	public static String P_ADDTIME = "cretime";
	// 修改时间
	public static String P_MODTIME = "modtime";

	// add user
	public static String P_ADDUSER = "creuser";

	// 修改user
	public static String P_MODUSER = "moduser";
	
	 // 关联表的后缀名称
	public static final String LINK_SUFFIX="_linkagedetail";


	// 修改user
	public static String P_DATA = "data";
	public static String ID = "id";
	public static String EQUALS = " = ";

	//classname
	public static final String CLASSNAME = "classname";

	public static final String IDS = "ids";

	public static final String LIKE = "like";

	public static final String LLIKE = "llike";

	public static final String RLIKE = "rlike";

	public static final String NULL = "null";

	public static final String IS = "is";

	public static final String ISNULL = " IS NULL";

	public static final String ISNOTNULL = "  IS NOT NULL";
}
