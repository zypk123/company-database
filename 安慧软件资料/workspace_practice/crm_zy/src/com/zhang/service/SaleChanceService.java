package com.zhang.service;

import java.util.List;
import java.util.Map;

import com.zhang.entity.SaleChance;

/**
 * 营销机会Sercice接口
 * 
 * @author zhangyu
 *
 */
public interface SaleChanceService {

	/**
	 * 查询产品集合
	 * 
	 * @param map
	 * @return
	 */
	public List<SaleChance> find(Map<String, Object> map);// 用map为参数，是为了迎合mybatis框架

	/**
	 * 获取总记录数
	 * 
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String, Object> map);
	
	/**
	 * 创建营销机会
	 * @param saleChance
	 * @return
	 */
	public int add(SaleChance saleChance);
	
	/**
	 * 修改营销机会
	 * @param saleChance
	 * @return
	 */
	public int update(SaleChance saleChance);
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
	/**
	 * 通过Id查找实体
	 * @param id
	 * @return
	 */
	public SaleChance findById(Integer id);

}
