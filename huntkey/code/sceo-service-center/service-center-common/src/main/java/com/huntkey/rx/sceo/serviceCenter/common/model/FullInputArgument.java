package com.huntkey.rx.sceo.serviceCenter.common.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.sceo.serviceCenter.common.model.InputArgument;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import com.huntkey.rx.sceo.serviceCenter.common.model.Param;

/***********************************************************************
 * @author chenxj
 * 
 * @email: kaleson@163.com
 * 
 * @date : 2017年7月13日 下午12:00:47
 * 
 *       前端调用新增、修改服务的入参对象
 * 
 **********************************************************************/
public class FullInputArgument implements InputArgument {
	// edmName
	private String edmName;

	// 业务数据
	private JSONArray params;

	// 查询数据
	private JSONObject search;

	// 查询的列
	private JSONArray columns;

	// 数据源
	private String dataSource;

	//sessionId
	private String sessionId;

	public FullInputArgument(String json) {
		initParams(json);
	}

	public FullInputArgument(Param param) {
		this(param.toString());
	}

	public String getEdmName() {
		return edmName;
	}

	public void setEdmName(String edmName) {
		this.edmName = edmName;
	}

	public JSONArray getParams() {
		return params;
	}

	public void setParams(JSONArray params) {
		this.params = params;
	}

	public JSONObject getSearch() {
		return search;
	}

	public void setSearch(JSONObject search) {
		this.search = search;
	}

	public JSONArray getColumns() {
		return columns;
	}

	public void setColumns(JSONArray columns) {
		this.columns = columns;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	private void initParams(String json) {
		JSONObject root = JSONObject.parseObject(json);

		if (root.containsKey(NodeConstant.EDMNAME)) {
			this.setEdmName(root.getString(NodeConstant.EDMNAME));
		}

		if (root.containsKey(NodeConstant.PARAMS)) {
			this.setParams(root.getJSONArray(NodeConstant.PARAMS));
		}

		if (root.containsKey(NodeConstant.SEARCH)) {
			this.setSearch(root.getJSONObject(NodeConstant.SEARCH));
		}

		if (root.containsKey(NodeConstant.COLUMNS)) {
			this.setColumns(root.getJSONArray(NodeConstant.COLUMNS));
		}

		if (root.containsKey(NodeConstant.DATASOURCE)) {
			this.setDataSource(root.getString(NodeConstant.DATASOURCE));
		}else{
			this.setDataSource("");
		}

		if (root.containsKey(NodeConstant.SESSIONID)) {
			this.setSessionId(root.getString(NodeConstant.SESSIONID));
		}

	}
}
