package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Direction_Code;

public interface T_Sys_Direction_CodeMapper {
    int deleteByPrimaryKey(String directionId);

    int insert(T_Sys_Direction_Code record);

    int insertSelective(T_Sys_Direction_Code record);

    T_Sys_Direction_Code selectByPrimaryKey(String directionId);

    int updateByPrimaryKeySelective(T_Sys_Direction_Code record);

    int updateByPrimaryKey(T_Sys_Direction_Code record);
}