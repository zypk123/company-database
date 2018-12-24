package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Code_Type;

public interface T_Sys_Code_TypeMapper {
    int deleteByPrimaryKey(String codeType);

    int insert(T_Sys_Code_Type record);

    int insertSelective(T_Sys_Code_Type record);

    T_Sys_Code_Type selectByPrimaryKey(String codeType);

    int updateByPrimaryKeySelective(T_Sys_Code_Type record);

    int updateByPrimaryKey(T_Sys_Code_Type record);
}