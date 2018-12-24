package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Config;

public interface T_Sys_ConfigMapper {
    int deleteByPrimaryKey(String configId);

    int insert(T_Sys_Config record);

    int insertSelective(T_Sys_Config record);

    T_Sys_Config selectByPrimaryKey(String configId);

    int updateByPrimaryKeySelective(T_Sys_Config record);

    int updateByPrimaryKey(T_Sys_Config record);
}