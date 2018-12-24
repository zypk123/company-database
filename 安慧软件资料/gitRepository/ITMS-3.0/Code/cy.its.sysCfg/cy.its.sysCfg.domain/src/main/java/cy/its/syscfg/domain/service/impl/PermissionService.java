package cy.its.syscfg.domain.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.StringUtil;
import cy.its.syscfg.domain.model.duty.User;
import cy.its.syscfg.domain.model.permission.Menu;
import cy.its.syscfg.domain.model.permission.MenuFunction;
import cy.its.syscfg.domain.model.permission.Role;
import cy.its.syscfg.domain.model.permission.RoleFunction;
import cy.its.syscfg.domain.repository.duty.IUserRepository;
import cy.its.syscfg.domain.repository.home.IDailyMenuRepository;
import cy.its.syscfg.domain.repository.permission.IMenuRepository;
import cy.its.syscfg.domain.repository.permission.IRoleRepository;
import cy.its.syscfg.domain.service.IPermissionService;

/**
 * 权限服务
 *
 */
@Service
public class PermissionService implements IPermissionService {
	@Autowired
	IMenuRepository menuRepository;
	@Autowired
	IRoleRepository roleRepository;
	@Autowired
	IUserRepository userRepository;
	@Autowired
	IDailyMenuRepository dailyMenuRepository;

	/**
	 * 查询所有功能点
	 * 
	 * @return 所有菜单（含一级菜单和二级菜单）
	 */
	public List<Menu> findMenuFunctions() {
		return menuRepository.findMenuFunctions();
	}

	@Override
	public List<Menu> findAllMenus() {
		return menuRepository.findAllMenus();
	}
	/**
	 * 添加菜单
	 * 
	 * @param menu
	 *            菜单信息
	 */
	public void addMenu(Menu menu) {
		menuRepository.save(menu);
	}

	/**
	 * 为指定菜单添加指定功能
	 * 
	 * @param menuCode
	 *            指定菜单编码
	 * @param functionCode
	 *            添加功能编码
	 * @param functionName
	 *            添加功能名称
	 * @param functionFlag
	 *            功能启用标记
	 * @throws Exception
	 */
	public void addMenuFunction(String menuCode, String functionCode,
			String functionName, String functionFlag) throws Exception {
		menuRepository.addFunction(new MenuFunction(functionCode, functionName,
				functionFlag, menuCode));
	}

	/**
	 * 删除指定的菜单
	 * 
	 * @param menuCode
	 *            菜单编码
	 */
	public void deleteMenu(String menuCode) {
		menuRepository.delete(menuCode);
		menuRepository.deleteFunctionsOfMenu(menuCode);
		dailyMenuRepository.deleteByUserPermmission();
	}

	/**
	 * 删除指定的菜单功能
	 * 
	 * @param functionCode
	 */
	public void deleteMenuFuntion(String functionCode) {
		menuRepository.deleteFunctionOfCode(functionCode);
		dailyMenuRepository.deleteByUserPermmission();
	}

	/**
	 * 查询所有角色
	 * 
	 * @return 角色列表
	 */
	public List<Role> findAllRoles(String orgCode) {
		return roleRepository.findAllRoles(orgCode);
	}
	

	
	/**
	 * 创建新角色
	 * 
	 * @param role
	 *            新角色信息
	 */
	public void createRole(Role role) {
		roleRepository.save(role);
		//roleRepository.saveAllFunctions(role.allFunctions());
	}

	/**
	 * 更新角色
	 * 
	 * @param role
	 *            角色信息
	 */
	public void updateRole(Role role) {
		roleRepository.update(role);
		roleRepository.removeAllFunctions(role.getIdentityId());
		roleRepository.saveAllFunctions(role.allFunctions());
		dailyMenuRepository.deleteByUserPermmission();
	}

	/**
	 * 为指定的角色添加功能列表
	 * 
	 * @param roleId
	 *            角色唯一标识ID
	 * @param roleFunctions
	 *            功能列表
	 * @throws Exception
	 *             角色不存在
	 */
	public void addRoleFunctions(String roleId, RoleFunction[] roleFunctions)
			throws Exception {
		Role role = roleRepository.aggregateOfId(roleId);
		if (role == null) {
			throw new Exception("角色不存在");
		}
		role.addFunctions(roleFunctions);
		roleRepository.removeAllFunctions(roleId);
		roleRepository.saveAllFunctions(role.allFunctions());
		dailyMenuRepository.deleteByUserPermmission();
	}

	/**
	 * 删除指定角色下的多个功能
	 * 
	 * @param roleId
	 *            角色唯一标识ID
	 * @param functionCodes
	 *            角色下的多个功能编码
	 * @throws Exception
	 */
	public void removeRoleFunctions(String roleId, String[] functionCodes)
			throws Exception {
		Role role = roleRepository.aggregateOfId(roleId);
		if (role == null) {
			throw new Exception("角色不存在");
		}
		role.removeFunctions(functionCodes);
		roleRepository.removeAllFunctions(roleId);
		roleRepository.saveAllFunctions(role.allFunctions());
		dailyMenuRepository.deleteByUserPermmission();
	}

	/**
	 * 删除指定的角色
	 * 
	 * @param roleId
	 *            角色唯一标识ID
	 */
	public void removeRole(String roleId) {
		userRepository.removeUserRolesByRoleId(roleId);
		roleRepository.removeAllFunctions(roleId);
		roleRepository.delete(roleId);
		dailyMenuRepository.deleteByUserPermmission();
	}

	@Override
	public List<Menu> menusOfUser(String userId) {
		return menuRepository.menusOfUser(userId);
	}

	@Override
	public List<String> findPermissinFunctionsByUser(User user) {
		if(user.roleIds == null || user.roleIds.isEmpty()){
			return new ArrayList<String>();
		}
		return menuRepository.findFunctionCodesByRoleIds(user.roleIds);
	}

	@Override
	public Map<String, MenuFunction> allMenuFunctions() {
		List<MenuFunction> lstF = menuRepository.allFuctions();
		if (lstF != null) {
			return lstF.stream().collect(Collectors.toMap(MenuFunction::getFunctionCode, (f) -> f));
		}
		return null;
	}

	@Override
	public List<String> findsubsribeKeys(User user) {
		return roleRepository.findSubsribeKeyByRoleIds(user.roleIds);
	}

	@Override
	public List<String> findsubsribeKeysByRoleId(String roleId) {
		List<String> roleIds = new ArrayList<String>();
		roleIds.add(roleId);
		return roleRepository.findSubsribeKeyByRoleIds(roleIds);
	}

	@Override
	public void saveAlarmSubsribePermission(String roleId, String keyStr) {
		if(!StringUtil.isNullOrEmpty(keyStr)){
			String[] keys = keyStr.split(",");
			for(String key : keys){
				roleRepository.saveAlarmSubsribePermission(roleId,key);
			}
		}
		
	}

	@Override
	public void deleteAlarmSubsribePermission(String roleId) {
		roleRepository.deleteAlarmSubsribePermission(roleId);
	}
}
