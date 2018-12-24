package cy.its.syscfg.mybatis.client;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cy.its.syscfg.domain.model.permission.Menu;
import cy.its.syscfg.mybatis.model.T_Sys_Role;

public interface T_Sys_RoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(T_Sys_Role record);

    int insertSelective(T_Sys_Role record);

    T_Sys_Role selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(T_Sys_Role record);

    int updateByPrimaryKey(T_Sys_Role record);

	List<Menu> selectByUserId(String userId);

	List<String> findSubsribeKeyByRoleIds(@Param("roleIds")List<String> roleIds);

	void deleteAlarmSubsribePermission(@Param("roleId") String roleId);

	void saveAlarmSubsribePermission(@Param("roleId")String roleId, @Param("key")String key);
}