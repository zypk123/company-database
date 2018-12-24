package cy.its.syscfg.repository.permission;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.ObjectMapUtils;
import cy.its.syscfg.domain.model.permission.Menu;
import cy.its.syscfg.domain.model.permission.MenuFunction;
import cy.its.syscfg.domain.repository.permission.IMenuRepository;
import cy.its.syscfg.mybatis.client.CustomMapper;
import cy.its.syscfg.mybatis.client.T_Sys_FunctionMapper;
import cy.its.syscfg.mybatis.client.T_Sys_MenuMapper;
import cy.its.syscfg.mybatis.client.T_Sys_Role_PermissionMapper;
import cy.its.syscfg.mybatis.model.Sys_Menu_Function;
import cy.its.syscfg.mybatis.model.T_Sys_Function;
import cy.its.syscfg.mybatis.model.T_Sys_Menu;
import cy.its.syscfg.util.Convert;

@Service
public class MenuRepository implements IMenuRepository {

	@Autowired
	T_Sys_MenuMapper t_Sys_MenuMapper;

	@Autowired
	CustomMapper customMapper;

	@Autowired
	T_Sys_FunctionMapper t_Sys_FunctionMapper;
	
	@Autowired
	T_Sys_Role_PermissionMapper t_Sys_Role_PermissionMapper;

	@Override
	public Menu aggregateOfId(String menuCode) throws Exception {
		List<Sys_Menu_Function> lstMf = customMapper
				.selectMenuFuntions(menuCode);
		return Convert.convert(lstMf);
	}

	@Override
	public String save(Menu obj) {
		T_Sys_Menu sysMenu = Convert.convert(obj);
		t_Sys_MenuMapper.insertSelective(sysMenu);
		return sysMenu.getMenuCode();
	}

	@Override
	public int delete(String id) {
		return t_Sys_MenuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(Menu obj) {
		T_Sys_Menu sysMenu = Convert.convert(obj);
		return t_Sys_MenuMapper.updateByPrimaryKeySelective(sysMenu);
	}

	@Override
	public List<Menu> findMenuFunctions() {
		List<Sys_Menu_Function> lstMf = customMapper.selectMenuFuntions("");
		Map<String, List<Sys_Menu_Function>> map = lstMf.stream().collect(
				Collectors.groupingBy(Sys_Menu_Function::getMenuCode));

		return map.entrySet().stream().map((kv) -> Convert.convert(kv.getValue()))
				.collect(Collectors.toList());
	}
	/**
	 * 
	  * findAllMenus 查找所有的菜单
	  * @Title: findAllMenus
	  * @Description: TODO
	  * @param     设定文件
	  * @return void    返回类型
	  * @throws
	 */
	@Override
	public List<Menu> findAllMenus(){
		List<Sys_Menu_Function> menuList = customMapper.selectMenus();
		List<Menu> menus = new ArrayList<Menu>();
		menuList.stream().forEach(item -> {
			Menu menu = new Menu();
			ObjectMapUtils.parseObject(menu, item);
			menus.add(menu);
		});
		return menus; 
	}
	@Override
	public void addFunction(MenuFunction menuFunction) {
		T_Sys_Function sysFunc = new T_Sys_Function();
		ObjectMapUtils.parseObject(sysFunc, menuFunction);
		t_Sys_FunctionMapper.insertSelective(sysFunc);
	}

	@Override
	public void deleteFunctionOfCode(String functionCode) {
		t_Sys_FunctionMapper.deleteByPrimaryKey(functionCode);
	}

	@Override
	public void deleteFunctionsOfMenu(String menuCode) {
		customMapper.deleteFunctionsOfMenu(menuCode);
	}

	@Override
	public void modifyFunction(MenuFunction menuFunction) {
		T_Sys_Function sysFunc = new T_Sys_Function();
		ObjectMapUtils.parseObject(sysFunc, menuFunction);
		t_Sys_FunctionMapper.updateByPrimaryKeySelective(sysFunc);
	}

	@Override
	public List<Menu> menusOfUser(String userId) {
		List<T_Sys_Menu> lm = t_Sys_MenuMapper.selectMenusByUser(userId);

		if (lm != null) {
			return lm.stream().map(m -> {
				Menu menu = new Menu();
				ObjectMapUtils.parseObject(menu, m);
				return menu;
			}).collect(Collectors.toList());
		}

		return null;
	}

	@Override
	public List<String> findFunctionCodesByRoleIds(List<String> roleIds) {
		return t_Sys_Role_PermissionMapper.findFunctionCodesByRoleIds(roleIds);
	}

	@Override
	public List<MenuFunction> allFuctions() {
		List<T_Sys_Function> funcs = t_Sys_FunctionMapper.selectAll();

		if (funcs != null) {
			return funcs.stream().map(f -> {
				try {
					MenuFunction mf = new MenuFunction(
							f.getFunctionCode(), f.getFunctionName(),
							f.getFunctionFlag(), f.getMenuCode());
					mf.functionDependency = f.getFunctionDependency();
					return mf;
				} catch (Exception e) {
					return null;
				}
			}).filter(t -> t != null).collect(Collectors.toList());
		}

		return null;
	}
}
