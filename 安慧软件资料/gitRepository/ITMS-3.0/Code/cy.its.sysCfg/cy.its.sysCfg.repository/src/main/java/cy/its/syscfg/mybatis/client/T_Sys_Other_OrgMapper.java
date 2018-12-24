package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Other_Org;

public interface T_Sys_Other_OrgMapper {
    int deleteByPrimaryKey(String otherOrgId);

    int insert(T_Sys_Other_Org record);

    int insertSelective(T_Sys_Other_Org record);

    T_Sys_Other_Org selectByPrimaryKey(String otherOrgId);

    int updateByPrimaryKeySelective(T_Sys_Other_Org record);

    int updateByPrimaryKey(T_Sys_Other_Org record);
}