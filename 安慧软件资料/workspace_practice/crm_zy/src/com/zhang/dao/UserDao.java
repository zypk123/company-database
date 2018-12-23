package com.zhang.dao;

import java.util.List;
import java.util.Map;

import com.zhang.entity.User;

/**
 * 用户Dao接口
 *
 */
public interface UserDao {
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public User login(User user);
	
	/**
	 * 查询用户集合
	 * @param map
	 * @return
	 */
	public List<User> find(Map<String,Object> map);//用map为参数，是为了迎合mybatis框架
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 添加用户
	 * @param user
	 * @return 添加的记录数
	 */
	public int add(User user);
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	public int update(User user);
	
}
