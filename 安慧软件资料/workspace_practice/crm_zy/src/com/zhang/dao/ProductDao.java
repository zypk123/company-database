package com.zhang.dao;

import java.util.List;
import java.util.Map;

import com.zhang.entity.Product;

/**
 * 产品Dao接口
 *
 */
public interface ProductDao {

	/**
	 * 查询产品集合
	 * @param map
	 * @return
	 */
	public List<Product> find(Map<String,Object> map);//用map为参数，是为了迎合mybatis框架
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
}
