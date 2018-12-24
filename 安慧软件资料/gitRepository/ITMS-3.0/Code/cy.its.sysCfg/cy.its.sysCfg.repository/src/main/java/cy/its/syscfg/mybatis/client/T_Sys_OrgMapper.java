package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Org;
import cy.its.syscfg.mybatis.model.T_Sys_OrgWithBLOBs;

public interface T_Sys_OrgMapper {
    int deleteByPrimaryKey(String orgId);

    int insert(T_Sys_OrgWithBLOBs record);

    int insertSelective(T_Sys_OrgWithBLOBs record);

    T_Sys_OrgWithBLOBs selectByPrimaryKey(String orgId);

    int updateByPrimaryKeySelective(T_Sys_OrgWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(T_Sys_OrgWithBLOBs record);

    int updateByPrimaryKey(T_Sys_Org record);
}