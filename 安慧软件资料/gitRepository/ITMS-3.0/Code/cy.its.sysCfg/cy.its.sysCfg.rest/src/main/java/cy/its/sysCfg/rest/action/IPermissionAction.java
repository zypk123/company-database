package cy.its.sysCfg.rest.action;

import java.util.Map;

import cy.its.sysCfg.rest.dto.RoleDto;


public interface IPermissionAction {
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
	public Map  findPermission(String currentUserId,String currentOrgCode) throws Exception; 
	
}
