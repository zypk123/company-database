package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Asso_User_Role;

public interface T_Sys_Asso_User_RoleMapper {
    int deleteByPrimaryKey(String assoId);

    int insert(T_Sys_Asso_User_Role record);

    int insertSelective(T_Sys_Asso_User_Role record);

    T_Sys_Asso_User_Role selectByPrimaryKey(String assoId);

    int updateByPrimaryKeySelective(T_Sys_Asso_User_Role record);

    int updateByPrimaryKey(T_Sys_Asso_User_Role record);
}