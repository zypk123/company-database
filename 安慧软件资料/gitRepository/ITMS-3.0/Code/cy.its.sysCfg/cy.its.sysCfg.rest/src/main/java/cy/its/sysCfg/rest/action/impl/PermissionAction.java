/**
 * @Title: PermissionAction.java
 * @Package cy.its.sysCfg.rest.aciton.impl
 * @Description: TODO(这里要填写用途)
 * @author zuop zuop@cychina.cn
 * @date 2015年10月29日 上午10:10:48
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.sysCfg.rest.action.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cy.its.sysCfg.rest.action.IPermissionAction;
import cy.its.sysCfg.rest.dto.MenuTreeDto;
import cy.its.sysCfg.rest.dto.RoleDto;
import cy.its.syscfg.domain.model.duty.User;
import cy.its.syscfg.domain.model.permission.Menu;
import cy.its.syscfg.domain.model.permission.Role;
import cy.its.syscfg.domain.model.permission.RoleFunction;
import cy.its.syscfg.domain.service.IDutyService;
import cy.its.syscfg.domain.service.IPermissionService;

/**
  * @ClassName: PermissionAction
  * @Description: TODO(这里要填写用途)
  * @author zuop zuop@cychina.cn
  * @date 2015年10月29日 上午10:10:48
  *
  */
@RestController
@RequestMapping("/sysCfg/permission")
public class PermissionAction implements IPermissionAction {

	@Autowired
	IPermissionService permissionService;
	
	@Autowired
	IDutyService dutyService;
	
	/**
	 * 
	  * getRoleList 查询角色权限信息
	  * @Title: getRoleList
	  * @Description: TODO
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return List<RoleDto>    返回类型
	  * @throws
	 */
	@Override
	@RequestMapping("/findPermission")
	public Map findPermission(String currentUserLoginName,String currentOrgCode) throws Exception {
		String orgCode = currentOrgCode;
		if("ADMIN".equals(currentUserLoginName)){
			orgCode = "";
		}
		List<Role> roleList = permissionService.findAllRoles(orgCode);
		List<RoleDto> dtoList = new ArrayList<RoleDto>();
		roleList.stream().forEach(item -> {
			dtoList.add(new RoleDto(item));
		});
		return returnResult(dtoList,0);
	}
	
	@RequestMapping("/findAlarmSubsribePermission")
	public List<String> findAlarmSubsribePermission(String roleId){
		return permissionService.findsubsribeKeysByRoleId(roleId);
	}
	
	/**
	 * 保存报警订阅权限
	 * @param roleId
	 * @param keys
	 * @return
	 */
	@RequestMapping("/saveAlarmSubsribePermission")
	public String saveAlarmSubsribePermission(String roleId,String keys){
		permissionService.deleteAlarmSubsribePermission(roleId);
		permissionService.saveAlarmSubsribePermission(roleId,keys); 
		return "success";
	}
	
	
	/**
	 * 
	  * findMenuTree 查找菜单树
	  * @Title: findMenuTree
	  * @Description: 查找菜单树
	  * @param @return    设定文件
	  * @return MenuTreeDto    返回类型
	  * @throws
	 */
	@RequestMapping("/findMenuTree")
	public List<MenuTreeDto> findMenuTree(){
		List<Menu> functionList = permissionService.findMenuFunctions();
		List<Menu> menuList = permissionService.findAllMenus();
		MenuTreeDto dto = new MenuTreeDto(menuList,functionList);
		List<MenuTreeDto> returnList = new ArrayList<MenuTreeDto>();
		returnList.add(dto);
		return returnList;
	}
	
	/**
	 * 获得用户操作权限
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/findUserResource")
	public Map<String,List> findUserResource(String userId) throws Exception{
		//获取所有的菜单树
		List<Menu> functionList = permissionService.findMenuFunctions();
		List<Menu> menuList = permissionService.findAllMenus();
		MenuTreeDto dto = new MenuTreeDto(menuList,functionList);
		User user = dutyService.userOfId(userId);
		if("ADMIN".equals(user.getLoginName().toUpperCase())){
			//管理员账户返回所有菜单
			Map<String,List> returnMap = new HashMap<String,List>();
			returnMap.put("functionPermission", dto.getChildren());
			return returnMap;
		}
		//查找权限下的功能点
		List<String> functionCodes = permissionService.findPermissinFunctionsByUser(user);
		removeNoPermissionMenu(dto.getChildren(),functionCodes);
		//查找订阅权限
		List<String> subsribeKeys = permissionService.findsubsribeKeys(user);
		Map<String,List> returnMap = new HashMap<String,List>();
		returnMap.put("functionPermission", dto.getChildren());
		returnMap.put("subsribePermission", subsribeKeys);
		return returnMap;
	}
	
	/**
	 * 删除没有权限的菜单
	 * @return
	 */
	private void removeNoPermissionMenu(List<MenuTreeDto> menuTreeList,List<String> functionCodes){
		for(int index=0; index<menuTreeList.size(); index++){
			MenuTreeDto item = menuTreeList.get(index);
			if(item.getChildren() != null && item.getChildren().size() > 0){
				removeNoPermissionMenu(item.getChildren(),functionCodes);
				if(item.getChildren().size() == 0){
					menuTreeList.remove(index--);
				}
			}else{
				if(!stringInList(item.getId(), functionCodes)){
					menuTreeList.remove(index--);
				}
			}
		}
	}
	
	/**
	 * 判断字符串是否在列表中
	 * @param str
	 * @param list
	 * @return
	 */
	private boolean stringInList(String str,List<String> list){
		for(String s : list){
			if(s.equals(str)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @throws Exception 
	 * 
	  * saveRole 保存角色信息
	  * @Title: saveRole
	  * @Description: TODO
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping("/saveRole")
	public String saveRole(RoleDto roleDto) throws Exception{
		Role role = roleDto.convertToRole();
		role.setRoleEnableFlag("1");
		role.setDataAccessType("0");
		//保存角色信息
		permissionService.createRole(role);
		//保存权限信息
		List<RoleFunction> functionList = roleDto.convertToRoleFunctions(role.getRoleId());
		RoleFunction[] roleFunctions = new RoleFunction[functionList.size()];
		functionList.toArray(roleFunctions);
		permissionService.addRoleFunctions(role.getRoleId(), roleFunctions);
		return "success";
	}
	
	/**
	 * @throws Exception 
	 * 
	  * updateRole 更新权限信息和权限
	  *
	  * @Title: updateRole
	  * @Description: TODO
	  * @param @param roleDto
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping("/updateRole")
	public String updateRole(RoleDto roleDto) throws Exception{
		//更新权限信息
		Role role = roleDto.convertToRole();
		permissionService.updateRole(role);
		return "success";
	}
	/**
	 * 
	  * removeRole 删除角色，包括预支对应的权限
	  * @Title: removeRole
	  * @Description: TODO
	  * @param @param ids
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping("/removeRole")
	public String removeRole(String ids){
		String[] idss = ids.split(",");
		for(String id : idss){
			permissionService.removeRole(id);
		}
		return "success";
	}
	/**
	 * 封装页面返回数据
	 * 
	 * @param dataList
	 * @param total
	 * @return
	 */
	private Map<String, Object> returnResult(List dataList, int total) {
		// 封装返回集
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("error", "");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", total);
		resultMap.put("rows", dataList);
		returnMap.put("result", resultMap);
		return returnMap;
	}
}
