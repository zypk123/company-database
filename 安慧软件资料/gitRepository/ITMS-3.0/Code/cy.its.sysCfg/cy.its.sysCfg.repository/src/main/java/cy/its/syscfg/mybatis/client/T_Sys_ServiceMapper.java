package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Service;

public interface T_Sys_ServiceMapper {
    int deleteByPrimaryKey(String serviceId);

    int insert(T_Sys_Service record);

    int insertSelective(T_Sys_Service record);

    T_Sys_Service selectByPrimaryKey(String serviceId);

    int updateByPrimaryKeySelective(T_Sys_Service record);

    int updateByPrimaryKey(T_Sys_Service record);
}