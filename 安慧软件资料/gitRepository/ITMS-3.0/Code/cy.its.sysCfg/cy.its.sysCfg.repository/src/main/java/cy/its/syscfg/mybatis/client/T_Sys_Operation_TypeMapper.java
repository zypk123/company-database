package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Operation_Type;

public interface T_Sys_Operation_TypeMapper {
    int deleteByPrimaryKey(String operationTypeId);

    int insert(T_Sys_Operation_Type record);

    int insertSelective(T_Sys_Operation_Type record);

    T_Sys_Operation_Type selectByPrimaryKey(String operationTypeId);

    int updateByPrimaryKeySelective(T_Sys_Operation_Type record);

    int updateByPrimaryKey(T_Sys_Operation_Type record);
}