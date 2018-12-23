package com.zhang.service;

import java.util.List;
import java.util.Map;

import com.zhang.entity.Order;

/**
 * 订单Service接口
 * @author zhangyu
 *
 */
public interface OrderService {
	
	/**
	 * 查询订单
	 * @param map
	 * @return
	 */
	public List<Order> find(Map<String,Object> map);
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 通过Id查找实体
	 * @param id
	 * @return
	 */
	public Order findById(Integer id);

}
