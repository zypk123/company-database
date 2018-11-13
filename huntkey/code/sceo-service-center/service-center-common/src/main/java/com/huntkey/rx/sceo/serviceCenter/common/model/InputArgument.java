package com.huntkey.rx.sceo.serviceCenter.common.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/***********************************************************************
 * @author chenxj
 * 
 * @email: kaleson@163.com
 * 
 * @date : 2017年7月13日 上午11:37:38
 * 
 **********************************************************************/
public interface InputArgument {

	/**
	 * 获取EDM 模型类名称
	 * 
	 * @return
	 */
	public String getEdmName();

	/**
	 * 获取业务数据
	 * 
	 * @return
	 */
	public JSONArray getParams();

	/**
	 * 获取查询数据
	 * 
	 * @return
	 */
	public JSONObject getSearch();

	/**
	 * 获取查询的列
	 * 
	 * @return
	 */
	public JSONArray getColumns();
}
