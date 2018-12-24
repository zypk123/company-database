package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Junction;

public interface T_Sys_JunctionMapper {
    int deleteByPrimaryKey(String junctionId);

    int insert(T_Sys_Junction record);

    int insertSelective(T_Sys_Junction record);

    T_Sys_Junction selectByPrimaryKey(String junctionId);

    int updateByPrimaryKeySelective(T_Sys_Junction record);

    int updateByPrimaryKey(T_Sys_Junction record);
}