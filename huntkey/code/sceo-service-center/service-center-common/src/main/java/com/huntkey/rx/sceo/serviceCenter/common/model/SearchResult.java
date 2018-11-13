package com.huntkey.rx.sceo.serviceCenter.common.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by linziy on 2017/8/1.
 *
 * 查询返回的结果 格式
 *
 *
 *
 * 
 * { "totalSize": 3, "size": 3, "pagenation": { "startPage": "1", "rows": "50"
 * }, "dataset": [ { "pid": "4eb438b1b1fe4d96867bf63abb4e062c", "id":
 * "5fd10fd9a8e14b2bb8af3eab40daa312", "staf_033": "zz22", "staf_032": "zzz11"
 * }, { "pid": "4eb438b1b1fe4d96867bf63abb4e062c", "id":
 * "93163cecb53a40f8be54c2608777b4e0", "staf_033": "zz22", "staf_032": "zzz11"
 * }, { "pid": "4eb438b1b1fe4d96867bf63abb4e062c", "id":
 * "eb247ea86de5476993c7a168ee87938c", "staf_033": "0033", "staf_032": "0032" }
 * ], "tableName": "staf_staf_031a" }
 */
public class SearchResult {
//	////////////////////// 常量节点设置///////////////////////////////////////
//	public final static String TABLENAME = "tableName";
//	public final static String TOTALSIZE = "totalSize";
//	public final static String SIZE = "size";
//	public final static String PAGENATION = "pagenation";
//	public final static String DATASET = "dataset";
	//此处统一到NodeConstant 上
	/////////////////////////////////////////////////////////////
	// 表名
	private String tableName;

	// 符合条件的总数
	private int totalSize = 1;

	// 当前获得的结果数
	private int size = 10;

	// 分页信息
	private PagenationNode pagenation = null;

	// 查询到的数据队列
	private JSONArray dataset = null;

	// 构造初始化
	public SearchResult() {
		tableName = "";
		totalSize = 0;
		size = 0;
		dataset = new JSONArray();
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public PagenationNode getPagenation() {
		return pagenation;
	}

	public void setPagenation(PagenationNode pagenation) {
		this.pagenation = pagenation;
	}

	public JSONArray getDataset() {
		return dataset;
	}

	public void setDataset(JSONArray dataset) {
		this.dataset = dataset;
	}

	// 添加结果数据
	public boolean addData(JSONObject data) {
		return this.dataset.add(data);
	}

	@Override
	public String toString() {
		return "${" + "'tableName':'" + tableName + '\'' + ",'totalSize':'" + String.valueOf(totalSize) + '\''
				+ ",'size':'" + String.valueOf(size) + '\'' + ",'pagenation':'" + pagenation.toString() + '\''
				+ ",'dataset':'" + dataset.toJSONString() + '\'' + '}';
	}
}
