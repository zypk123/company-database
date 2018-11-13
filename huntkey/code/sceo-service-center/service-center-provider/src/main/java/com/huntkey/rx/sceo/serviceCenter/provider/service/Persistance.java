package com.huntkey.rx.sceo.serviceCenter.provider.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.sceo.serviceCenter.common.model.InputArgument;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.config.PersistanceConstant;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.exception.DBException;

/***********************************************************************
 * @author chenxj
 * 
 * @email: kaleson@163.com
 * 
 * @date : 2017年7月13日 上午11:35:24
 * 
 **********************************************************************/
public interface Persistance {
	// 关联表调用时，使用类名 + _link_records；
	public static final String PREFIX = PersistanceConstant.LINK_SUFFIX;

	/**
	 * 保存对象数据 返回主表的ID
	 * 
	 * @param args
	 */
	public Set<String> save(InputArgument args) throws DBException;

	/**
	 * 修改对象数据
	 * 
	 * @param args
	 */
	public Set<String> update(InputArgument args) throws DBException;

	/**
	 * 删除对象数据
	 * 
	 * @param args
	 */
	public void remove(InputArgument args) throws DBException;

	/**
	 * 查询对象数据
	 * 
	 * @param args
	 */
	public JSONObject find(InputArgument args) throws DBException;

	/**
	 * 根据id 加载数据 返回主表的ID
	 *
	 * @param args：
	 * 
	 *            <pre>
	 * 	-base: 查询对象的基本数据
	 *  -sub：     查询对象的基本数据 + 属性集的数据
	 *  -ref：  查询对象的基本数据 + 关联对象的数据
	 *  -all：  查询对象的基本数据 + 属性集的数据 + 关联对象的数据
	 *  参数示例：
	    {
			  "edmName":"staff",
			  "search":{
				  "type":"base",// sub/ ref / all
				  "columns":["staf001","staf002","staf003"],
				  "ids": ["15431353","185431563"]
			}
		 }
	 *            </pre>
	 */
	public JSONObject load(InputArgument args) throws DBException;

	/**
	 * 根据查询条件获取统计数量 返回数据条数
	 *
	 */
	public long count(InputArgument args) throws DBException;

	/**
	 *  丰富的查,支持and or not 查询
	 */
	public JSONObject richfind(InputArgument args) throws DBException;

    List<Map<String, Object>> query(String sql);

}
