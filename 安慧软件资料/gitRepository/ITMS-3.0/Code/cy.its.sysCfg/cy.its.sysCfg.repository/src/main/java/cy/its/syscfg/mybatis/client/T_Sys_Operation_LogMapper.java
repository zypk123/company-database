package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Operation_Log;

public interface T_Sys_Operation_LogMapper {
    int deleteByPrimaryKey(String opeLogId);

    int insert(T_Sys_Operation_Log record);

    int insertSelective(T_Sys_Operation_Log record);

    T_Sys_Operation_Log selectByPrimaryKey(String opeLogId);

    int updateByPrimaryKeySelective(T_Sys_Operation_Log record);

    int updateByPrimaryKey(T_Sys_Operation_Log record);
}