package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Region;

public interface T_Sys_RegionMapper {
    int deleteByPrimaryKey(String regionalId);

    int insert(T_Sys_Region record);

    int insertSelective(T_Sys_Region record);

    T_Sys_Region selectByPrimaryKey(String regionalId);

    int updateByPrimaryKeySelective(T_Sys_Region record);

    int updateByPrimaryKey(T_Sys_Region record);
}