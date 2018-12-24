package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Daily_Menu;

public interface T_Sys_Daily_MenuMapper {
    int deleteByPrimaryKey(String dailyMenuId);

    int insert(T_Sys_Daily_Menu record);

    int insertSelective(T_Sys_Daily_Menu record);

    T_Sys_Daily_Menu selectByPrimaryKey(String dailyMenuId);

    int updateByPrimaryKeySelective(T_Sys_Daily_Menu record);

    int updateByPrimaryKey(T_Sys_Daily_Menu record);
}