package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Branch;

public interface T_Sys_BranchMapper {
    int deleteByPrimaryKey(String roadBranchId);

    int insert(T_Sys_Branch record);

    int insertSelective(T_Sys_Branch record);

    T_Sys_Branch selectByPrimaryKey(String roadBranchId);

    int updateByPrimaryKeySelective(T_Sys_Branch record);

    int updateByPrimaryKey(T_Sys_Branch record);
}