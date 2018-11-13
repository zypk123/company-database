package com.huntkey.rx.sceo.serviceCenter.common.model;

import com.alibaba.fastjson.JSONObject;

/***********************************************************************
 * @author chenxj
 * 
 * @email: kaleson@163.com
 * 
 * @date : 2017年8月17日 下午8:23:42
 * 
 **********************************************************************/

// 参数对象封装
public interface Param {
	/**
	 * 获取edmName 名称
	 * 
	 * @return
	 */
	public String getEdmName();

	/**
	 * 获取查询对象
	 * 
	 * @return
	 */
	public JSONObject getSearch();
	
	/**
	 * 生成入参对象数据
	 * 
	 * @return
	 */
	public InputArgument build();
}
