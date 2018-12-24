package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Lane;

public interface T_Sys_LaneMapper {
    int deleteByPrimaryKey(String laneId);

    int insert(T_Sys_Lane record);

    int insertSelective(T_Sys_Lane record);

    T_Sys_Lane selectByPrimaryKey(String laneId);

    int updateByPrimaryKeySelective(T_Sys_Lane record);

    int updateByPrimaryKey(T_Sys_Lane record);
}