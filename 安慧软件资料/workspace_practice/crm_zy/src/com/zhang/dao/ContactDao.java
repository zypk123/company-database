package com.zhang.dao;

import java.util.List;
import java.util.Map;
import com.zhang.entity.Contact;

/**
 * 交往记录Dao接口
 * @author Administrator
 *
 */
public interface ContactDao {

	
	/**
	 * 查询交往记录集合
	 * @param map
	 * @return
	 */
	public List<Contact> find(Map<String,Object> map);
	
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 修改交往记录
	 * @param linkMan
	 * @return
	 */
	public int update(Contact linkMan);
	
	/**
	 * 添加交往记录
	 * @param linkMan
	 * @return
	 */
	public int add(Contact linkMan);
	
	/**
	 * 删除交往记录
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
}
