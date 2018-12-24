package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Position_Type;

public interface T_Sys_Position_TypeMapper {
    int deleteByPrimaryKey(String positionTypeId);

    int insert(T_Sys_Position_Type record);

    int insertSelective(T_Sys_Position_Type record);

    T_Sys_Position_Type selectByPrimaryKey(String positionTypeId);

    int updateByPrimaryKeySelective(T_Sys_Position_Type record);

    int updateByPrimaryKey(T_Sys_Position_Type record);
}