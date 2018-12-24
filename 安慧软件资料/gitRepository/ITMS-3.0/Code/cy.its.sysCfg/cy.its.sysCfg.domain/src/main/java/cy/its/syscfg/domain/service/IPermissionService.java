package cy.its.syscfg.domain.service;

import java.util.List;
import java.util.Map;

import cy.its.syscfg.domain.model.duty.User;
import cy.its.syscfg.domain.model.permission.Menu;
import cy.its.syscfg.domain.model.permission.MenuFunction;
import cy.its.syscfg.domain.model.permission.Role;
import cy.its.syscfg.domain.model.permission.RoleFunction;

public interface IPermissionService {

	/**
	 * 查询所有功能点，包含菜单信息
	 * 
	 * @return 所有菜单（含一级菜单和二级菜单）
	 */
	public List<Menu> findMenuFunctions();
	
	/**
	 *  
	  * findMenus 查询所有菜单信息
	  * @Title: findMenus
	  * @Description: TODO
	  * @param @return    设定文件
	  * @return List<Menu>    返回类型
	  * @throws
	 */
	public List<Menu> findAllMenus();

	/**
	 * 添加菜单
	 * 
	 * @param menu
	 *            菜单信息
	 */
	public void addMenu(Menu menu);

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
			String functionName, String functionFlag) throws Exception;

	/**
	 * 删除指定的菜单
	 * 
	 * @param menuCode
	 *            菜单编码
	 */
	public void deleteMenu(String menuCode);

	/**
	 * 删除指定的菜单功能
	 * 
	 * @param functionCode
	 */
	public void deleteMenuFuntion(String functionCode);

	/**
	 * 查询所有角色
	 * @param orgCode 
	 * 
	 * @return 角色列表
	 */
	public List<Role> findAllRoles(String orgCode);

	/**
	 * 创建新角色
	 * 
	 * @param role
	 *            新角色信息
	 */
	public void createRole(Role role);

	/**
	 * 更新角色
	 * 
	 * @param role
	 *            角色信息
	 */
	public void updateRole(Role role);

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
			throws Exception;

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
			throws Exception;

	/**
	 * 删除指定的角色
	 * 
	 * @param roleId
	 *            角色唯一标识ID
	 */
	public void removeRole(String roleId);
	
	/**
	 * 查询指定用户的所有菜单
	 * @param userId
	 * @return
	 */
	public List<Menu> menusOfUser(String userId);

	/**
	 * 根据用户查找所有的功能点
	 * @param user
	 */
	public List<String> findPermissinFunctionsByUser(User user);
	
	/**
	 * 获取所有的功能
	 * @return 功能MAP  key:功能编码  value:功能信息
	 */
	public Map<String, MenuFunction> allMenuFunctions();
	
	/**
	 * 查找预警订阅权限
	 * @param user
	 * @return
	 */
	public List<String> findsubsribeKeys(User user);

	/**
	 * 根据权限查找报警订阅权限
	 * @param roleId
	 * @return
	 */
	public List<String> findsubsribeKeysByRoleId(String roleId);
	
	/**
	 * 保存报警订阅权限
	 * @param roleId
	 * @param keys
	 */
	public void saveAlarmSubsribePermission(String roleId, String keys);
	
	/**
	 * 保存报警订阅权限
	 * @param roleId
	 */
	public void deleteAlarmSubsribePermission(String roleId);

}