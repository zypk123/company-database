package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Veh_Brand;

public interface T_Sys_Veh_BrandMapper {
    int deleteByPrimaryKey(String vehicleBrandId);

    int insert(T_Sys_Veh_Brand record);

    int insertSelective(T_Sys_Veh_Brand record);

    T_Sys_Veh_Brand selectByPrimaryKey(String vehicleBrandId);

    int updateByPrimaryKeySelective(T_Sys_Veh_Brand record);

    int updateByPrimaryKey(T_Sys_Veh_Brand record);
}