package com.zhang.dao;

import java.util.List;
import java.util.Map;

import com.zhang.entity.Customer;
import com.zhang.entity.CustomerFw;
import com.zhang.entity.CustomerGc;
import com.zhang.entity.CustomerGx;

/**
 * 客户Dao接口
 *
 */
public interface CustomerDao {

	/**
	 * 查询客户集合
	 * @param map
	 * @return
	 */
	public List<Customer> find(Map<String,Object> map);//用map为参数，是为了迎合mybatis框架
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 添加客户信息
	 * @return
	 */
	public int add(Customer customer);
	
	/**
	 * 修改客户信息
	 * @param customer
	 * @return
	 */
	public int update(Customer customer);
	
	/**
	 * 删除客户信息
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
	/**
	 * 通过Id查找客户
	 * @param id
	 * @return
	 */
	public Customer findById(Integer id);
	
	/**
	 * 查找流失的客户 6个月未下单的客户
	 * @return
	 */
	public List<Customer> findLossCustomer();
	
	/**
	 * 查询客户贡献集合
	 * @param map
	 * @return
	 */
	public List<CustomerGx> findCustomerGx(Map<String,Object> map);//用map为参数，是为了迎合mybatis框架
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public Long getTotalCustomerGx(Map<String,Object> map);
	
	/**
	 * 查询客户构成集合
	 * @return
	 */
	public List<CustomerGc> findCustomerGc();
	
	/**
	 * 查询客户服务集合
	 * @return
	 */
	public List<CustomerFw> findCustomerFw();
	
	

	
}
