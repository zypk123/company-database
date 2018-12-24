package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Role_UserKey;

public interface T_Sys_Role_UserMapper {
    int deleteByPrimaryKey(T_Sys_Role_UserKey key);

    int insert(T_Sys_Role_UserKey record);

    int insertSelective(T_Sys_Role_UserKey record);
}