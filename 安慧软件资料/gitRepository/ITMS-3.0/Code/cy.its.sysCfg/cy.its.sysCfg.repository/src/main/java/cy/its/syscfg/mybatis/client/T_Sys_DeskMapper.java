package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Desk;

public interface T_Sys_DeskMapper {
    int deleteByPrimaryKey(String deskId);

    int insert(T_Sys_Desk record);

    int insertSelective(T_Sys_Desk record);

    T_Sys_Desk selectByPrimaryKey(String deskId);

    int updateByPrimaryKeySelective(T_Sys_Desk record);

    int updateByPrimaryKey(T_Sys_Desk record);
}