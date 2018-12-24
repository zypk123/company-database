package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Road;

public interface T_Sys_RoadMapper {
    int deleteByPrimaryKey(String roadId);

    int insert(T_Sys_Road record);

    int insertSelective(T_Sys_Road record);

    T_Sys_Road selectByPrimaryKey(String roadId);

    int updateByPrimaryKeySelective(T_Sys_Road record);

    int updateByPrimaryKey(T_Sys_Road record);
}