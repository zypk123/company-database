package com.huntkey.rx.sceo.common.service.provider.dao;

import java.util.List;

import com.huntkey.rx.sceo.common.service.common.model.Menu;


public interface MenuDao {
	
	/**
	 * 获取所有的菜单
	 * @return
	 */
	List<Menu> getAllMenu();

	/**
	 * 根据ID查询菜单信息
	 * @param id 菜单ID
	 * @return
	 */
	Menu getMenuById(String id);

	/**
	 * 添加菜单信息
	 * @param menu
	 * @return
	 */
	int save(Menu menu);

	/**
	 * 更新菜单信息
	 * @param menu
	 * @return
	 */
	int update(Menu menu);

	/**
	 * 批量删除菜单
	 * @param del_ids
	 * @return
	 */
	int delete(List<String> del_ids);

	/**
	 * 根据pid查询子菜单信息
	 * @param pid
	 * @return
	 */
	List<Menu> selMenuByParent(String pid);
}
