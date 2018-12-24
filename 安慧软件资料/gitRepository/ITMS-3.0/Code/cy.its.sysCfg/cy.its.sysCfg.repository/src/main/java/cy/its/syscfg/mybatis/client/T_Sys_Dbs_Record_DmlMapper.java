package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Dbs_Record_Dml;

public interface T_Sys_Dbs_Record_DmlMapper {
    int deleteByPrimaryKey(String operateContentId);

    int insert(T_Sys_Dbs_Record_Dml record);

    int insertSelective(T_Sys_Dbs_Record_Dml record);

    T_Sys_Dbs_Record_Dml selectByPrimaryKey(String operateContentId);

    int updateByPrimaryKeySelective(T_Sys_Dbs_Record_Dml record);

    int updateByPrimaryKey(T_Sys_Dbs_Record_Dml record);
}