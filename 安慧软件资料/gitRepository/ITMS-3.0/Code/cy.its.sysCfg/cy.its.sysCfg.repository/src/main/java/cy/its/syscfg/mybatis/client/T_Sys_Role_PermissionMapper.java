package cy.its.syscfg.mybatis.client;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cy.its.syscfg.mybatis.model.T_Sys_Role_Permission;
import cy.its.syscfg.mybatis.model.T_Sys_Role_PermissionKey;

public interface T_Sys_Role_PermissionMapper {
    int deleteByPrimaryKey(T_Sys_Role_PermissionKey key);

    int insert(T_Sys_Role_Permission record);

    int insertSelective(T_Sys_Role_Permission record);

    T_Sys_Role_Permission selectByPrimaryKey(T_Sys_Role_PermissionKey key);

    int updateByPrimaryKeySelective(T_Sys_Role_Permission record);

    int updateByPrimaryKey(T_Sys_Role_Permission record);
    
	List<String> findFunctionCodesByRoleIds(@Param("roleIds") List<String> roleIds);
}