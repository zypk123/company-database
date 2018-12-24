package cy.its.syscfg.mybatis.client;

import java.util.Map;

import cy.its.syscfg.mybatis.model.T_Sys_Code;

public interface T_Sys_CodeMapper {
    int deleteByPrimaryKey(String codeId);

    int insert(T_Sys_Code record);

    int insertSelective(T_Sys_Code record);

    T_Sys_Code selectByPrimaryKey(String codeId);

    int updateByPrimaryKeySelective(T_Sys_Code record);

    int updateByPrimaryKey(T_Sys_Code record);
    
    int removeByPrimaryKey(Map<String,Object> codeIds);
}