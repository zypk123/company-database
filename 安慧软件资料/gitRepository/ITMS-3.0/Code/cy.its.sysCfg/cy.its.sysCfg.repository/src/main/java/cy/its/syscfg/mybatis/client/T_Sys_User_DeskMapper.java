package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_User_DeskKey;

public interface T_Sys_User_DeskMapper {
    int deleteByPrimaryKey(T_Sys_User_DeskKey key);

    int insert(T_Sys_User_DeskKey record);

    int insertSelective(T_Sys_User_DeskKey record);
}