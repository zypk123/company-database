package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Vio_Action;

public interface T_Sys_Vio_ActionMapper {
    int deleteByPrimaryKey(String vioActionCode);

    int insert(T_Sys_Vio_Action record);

    int insertSelective(T_Sys_Vio_Action record);

    T_Sys_Vio_Action selectByPrimaryKey(String vioActionCode);

    int updateByPrimaryKeySelective(T_Sys_Vio_Action record);

    int updateByPrimaryKey(T_Sys_Vio_Action record);
}