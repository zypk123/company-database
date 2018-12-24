package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Service_Resource;

public interface T_Sys_Service_ResourceMapper {
    int deleteByPrimaryKey(String serviceResourceId);

    int insert(T_Sys_Service_Resource record);

    int insertSelective(T_Sys_Service_Resource record);

    T_Sys_Service_Resource selectByPrimaryKey(String serviceResourceId);

    int updateByPrimaryKeySelective(T_Sys_Service_Resource record);

    int updateByPrimaryKey(T_Sys_Service_Resource record);
}