package cy.its.syscfg.mybatis.client;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cy.its.syscfg.mybatis.model.Sys_Menu_Function;
import cy.its.syscfg.mybatis.model.T_Sys_Code;
import cy.its.syscfg.mybatis.model.T_Sys_Code_Type;
import cy.its.syscfg.mybatis.model.T_Sys_Operation_Log;
import cy.its.syscfg.mybatis.model.T_Sys_Org;
import cy.its.syscfg.mybatis.model.T_Sys_Role;
import cy.its.syscfg.mybatis.model.T_Sys_Role_Permission;
import cy.its.syscfg.mybatis.model.T_Sys_Role_PermissionKey;
import cy.its.syscfg.mybatis.model.T_Sys_Role_UserKey;
import cy.its.syscfg.mybatis.model.T_Sys_User;

public interface CustomMapper {
	
    List<T_Sys_Org> selectAllOrgs();
    
    int deleteUserRole(T_Sys_Role_UserKey userRoleKey);
    
    List<T_Sys_User> selectUsers(Map<String, Object> map);
    
    int countUsers(Map<String, Object> map);
    
    List<T_Sys_Role_UserKey> selectUserRoles(T_Sys_Role_UserKey userRoleKey);
    
    List<T_Sys_Role_UserKey> selectUserRolesByUserIds(@Param("userIds")List<String> userIds);
    
    List<T_Sys_Role_Permission> selectRolePermissions(T_Sys_Role_PermissionKey key);
    
    List<T_Sys_Role> selectAllRoles(@Param("orgCode")String orgCode);
    
    int deleteRolePermissions(T_Sys_Role_PermissionKey key);
        
    List<T_Sys_Code_Type> selectCodeTypes();
    
    List<T_Sys_Code> selectCodes();
        
    List<T_Sys_Operation_Log>  selectOptLogs(Map<String, Object> map);
    
    Integer countOptLogs(Map<String, Object> map);
    
    List<Sys_Menu_Function> selectMenus();
    
    List<Sys_Menu_Function> selectMenuFuntions(@Param("menuCode")String menuCode);
    
    int deleteFunctionsOfMenu(@Param("menuCode")String menuCode);
    
}
