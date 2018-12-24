package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Special_Section;

public interface T_Sys_Special_SectionMapper {
    int deleteByPrimaryKey(String specialSectionId);

    int insert(T_Sys_Special_Section record);

    int insertSelective(T_Sys_Special_Section record);

    T_Sys_Special_Section selectByPrimaryKey(String specialSectionId);

    int updateByPrimaryKeySelective(T_Sys_Special_Section record);

    int updateByPrimaryKey(T_Sys_Special_Section record);
}