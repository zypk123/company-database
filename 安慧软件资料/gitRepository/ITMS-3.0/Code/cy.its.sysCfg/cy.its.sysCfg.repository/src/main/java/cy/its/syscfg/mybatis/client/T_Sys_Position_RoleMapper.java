package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Position_RoleKey;

public interface T_Sys_Position_RoleMapper {
    int deleteByPrimaryKey(T_Sys_Position_RoleKey key);

    int insert(T_Sys_Position_RoleKey record);

    int insertSelective(T_Sys_Position_RoleKey record);
}