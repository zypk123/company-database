package com.zhang.service;

import java.util.List;
import java.util.Map;

import com.zhang.entity.CustomerService;

/**
 * 客户服务Service接口
 * @author zhangyu
 *
 */
public interface CustomerServiceService {
	
	/**
	 * 创建客户服务
	 * @param customerService
	 * @return
	 */
	public int add(CustomerService customerService);
	
	/**
	 * 查询客户服务
	 * @param map
	 * @return
	 */
	public List<CustomerService> find(Map<String,Object> map);
	
	/**
	 * 查询客户服务记录数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 修改客户服务
	 * @param customerService
	 * @return
	 */
	public int update(CustomerService customerService);

}
