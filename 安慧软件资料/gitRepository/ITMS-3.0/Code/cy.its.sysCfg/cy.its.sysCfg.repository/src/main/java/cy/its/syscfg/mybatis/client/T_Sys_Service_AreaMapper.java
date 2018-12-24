package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Service_Area;

public interface T_Sys_Service_AreaMapper {
    int deleteByPrimaryKey(String serviceAreaId);

    int insert(T_Sys_Service_Area record);

    int insertSelective(T_Sys_Service_Area record);

    T_Sys_Service_Area selectByPrimaryKey(String serviceAreaId);

    int updateByPrimaryKeySelective(T_Sys_Service_Area record);

    int updateByPrimaryKey(T_Sys_Service_Area record);
}