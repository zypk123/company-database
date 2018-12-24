package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Site;

public interface T_Sys_SiteMapper {
    int deleteByPrimaryKey(String siteId);

    int insert(T_Sys_Site record);

    int insertSelective(T_Sys_Site record);

    T_Sys_Site selectByPrimaryKey(String siteId);

    int updateByPrimaryKeySelective(T_Sys_Site record);

    int updateByPrimaryKey(T_Sys_Site record);
}