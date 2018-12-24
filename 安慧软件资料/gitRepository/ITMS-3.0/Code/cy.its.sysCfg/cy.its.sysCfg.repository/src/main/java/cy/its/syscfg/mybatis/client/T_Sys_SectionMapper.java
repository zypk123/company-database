package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Section;

public interface T_Sys_SectionMapper {
    int deleteByPrimaryKey(String sectionId);

    int insert(T_Sys_Section record);

    int insertSelective(T_Sys_Section record);

    T_Sys_Section selectByPrimaryKey(String sectionId);

    int updateByPrimaryKeySelective(T_Sys_Section record);

    int updateByPrimaryKey(T_Sys_Section record);
}