package com.huntkey.rx.sceo.common.service.provider.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huntkey.rx.commons.abstracts.BaseService;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.commons.utils.uuid.UuidCreater;
import com.huntkey.rx.sceo.common.service.common.model.Menu;
import com.huntkey.rx.sceo.common.service.provider.dao.MenuDao;


@Service
@Transactional(readOnly=true)
public class MenuService extends BaseService {
	@Autowired
	private MenuDao menuDao;
	
	public List<Menu> getAllMenu(){
		List<Menu> list = menuDao.getAllMenu();
		List<Menu> result = new ArrayList<Menu>();
		for (Menu menu : list) {
			if("0".equals(menu.getParentId())){ //parentId为0为一级菜单
				result.add(menu);
			}
		}
		for (Menu menu : result) { //查询二级菜单
			List<Menu> sec_list = getChildList(list,menu.getId());
			if(sec_list.size()>0){
				for (Menu sec_menu : sec_list) { //三级菜单
					sec_menu.setChildList(getChildList(list,sec_menu.getId()));
				}
			}
			menu.setChildList(sec_list);
		}
		return result;
	}
	
	private List<Menu> getChildList(List<Menu> list,String parentId){
		List<Menu> childList = new ArrayList<Menu>();
		for (Menu menu : list) {
			if(menu.getParentId().equals(parentId)){
				childList.add(menu);
			}
		}
		return childList;
	}

	/**
	 * 根据ID查询菜单信息
	 * @param id 菜单ID
	 * @return
	 */
	public Menu getMenuById(String id) {
		return menuDao.getMenuById(id);
	}

	/**
	 * 添加菜单信息
	 * @param menu
	 * @return
	 */
	@Transactional(readOnly=false)
	public int save(Menu menu) {
		if(StringUtil.isNullOrEmpty(menu.getId())){
			menu.setId(UuidCreater.uuid());
		}
		return menuDao.save(menu);
	}

	/**
	 * 更新菜单信息
	 * @param menu
	 * @return
	 */
	@Transactional(readOnly=false)
	public int update(Menu menu) {
		return menuDao.update(menu);
	}

	/**
	 * 查询当前菜单下是否有子菜单
	 * @param id
	 * @return
	 */
	public boolean hasChild(String id) {
		List<Menu> menus = menuDao.selMenuByParent(id);
		return menus.size()>0?true:false;
	}

	/**
	 * 删除菜单,包括所有的子节点
	 * @param id 当前节点的ID
	 * @return
	 */
	@Transactional(readOnly=false)
	public void delete(String id) {
		List<String> del_ids = new ArrayList<String>(); //将所有子节点的ID存放在list中
		del_ids.add(id); //首先加入本身
		batchQueryChildId(id,del_ids); //递归查询出嵌套的子节点ID
		menuDao.delete(del_ids);
	}
	
	/**
	 * 给出一个PID查询出所有的子节点ID做批量删除使用
	 * @param pid
	 * @return
	 */
	private void batchQueryChildId(String pid, List<String> del_ids){
		List<Menu> menus = menuDao.selMenuByParent(pid);
		if(menus.size() > 0){
			for (Menu menu : menus) {
				del_ids.add(menu.getId());
				batchQueryChildId(menu.getId(),del_ids);
			}
		}
	}

	/**
	 * 查询子菜单信息.根据PID
	 * @param pid
	 * @return
	 */
	public List<Menu> getSecAllMenu(String pid) {
		List<Menu> list = menuDao.getAllMenu();
		List<Menu> result = new ArrayList<Menu>();
		for (Menu menu : list) {
			if(pid.equals(menu.getParentId())){
				menu.setChildList(getChildList(list,menu.getId()));
				result.add(menu);
			}
		}
		return result;
	}
}
