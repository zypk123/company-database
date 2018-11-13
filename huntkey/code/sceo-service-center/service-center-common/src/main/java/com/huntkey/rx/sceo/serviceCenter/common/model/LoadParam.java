package com.huntkey.rx.sceo.serviceCenter.common.model;

import java.util.Collection;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.sceo.serviceCenter.common.emun.ReferanceType;

/**
 * 查询对象 Created by chenxiaojun on 2017/8/10.
 */
public class LoadParam extends JSONObject implements Param {
	private static final long serialVersionUID = 5893811486061638516L;

	public LoadParam(String edmName) {
		this.put(NodeConstant.EDMNAME, edmName);
		this.put(NodeConstant.SEARCH, new JSONObject());
	}

	/**
	 * 添加load类型
	 * 
	 * @param type：
	 *            base、 sub、ref、all
	 * @return
	 */
	public LoadParam addLoadType(ReferanceType type) {
		JSONObject search = this.getJSONObject(NodeConstant.SEARCH);
		search.put(NodeConstant.TYPE, type.getValue());

		return this;
	}

	/**
	 * 添加查询列属性
	 * 
	 * @param columns
	 * @return
	 */
	public LoadParam addColumns(String columns) {
		JSONObject search = this.getJSONObject(NodeConstant.SEARCH);
		search.put(NodeConstant.COLUMNS, columns.split(","));

		return this;
	}

	/**
	 * 添加查询列属性
	 * 
	 * @param columns
	 * @return
	 */
	public LoadParam addColumns(Collection<String> columns) {
		JSONObject search = this.getJSONObject(NodeConstant.SEARCH);
		search.put(NodeConstant.COLUMNS, columns);

		return this;
	}

	/**
	 * 添加查询列属性
	 * 
	 * @param columns
	 * @return
	 */
	public LoadParam addColumns(String[] columns) {
		JSONObject search = this.getJSONObject(NodeConstant.SEARCH);
		search.put(NodeConstant.COLUMNS, columns);

		return this;
	}

	/**
	 * 添加查询条件 ids
	 * 
	 * @param ids
	 * @return
	 */
	public LoadParam addIDs(String ids) {
		JSONObject search = this.getJSONObject(NodeConstant.SEARCH);
		search.put(NodeConstant.IDS, ids.split(","));

		return this;
	}

	/**
	 * 添加查询条件 ids
	 * 
	 * @param columns
	 * @return
	 */
	public LoadParam addIDs(Collection<String> ids) {
		JSONObject search = this.getJSONObject(NodeConstant.SEARCH);
		search.put(NodeConstant.IDS, ids);

		return this;
	}

	/**
	 * 添加查询条件 ids
	 * 
	 * @param columns
	 * @return
	 */
	public LoadParam addIDs(String[] ids) {
		JSONObject search = this.getJSONObject(NodeConstant.SEARCH);
		search.put(NodeConstant.IDS, ids);

		return this;
	}

	@Override
	public String getEdmName() {
		return this.getString(NodeConstant.EDMNAME);
	}

	@Override
	public JSONObject getSearch() {
		return this.getJSONObject(NodeConstant.SEARCH);
	}

	@Override
	public InputArgument build() {
		return new FullInputArgument(this);
	}
}
