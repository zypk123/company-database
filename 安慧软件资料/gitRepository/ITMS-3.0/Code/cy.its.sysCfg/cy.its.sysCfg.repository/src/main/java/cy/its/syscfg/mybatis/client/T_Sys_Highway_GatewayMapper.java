package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Highway_Gateway;

public interface T_Sys_Highway_GatewayMapper {
    int deleteByPrimaryKey(String gatewayId);

    int insert(T_Sys_Highway_Gateway record);

    int insertSelective(T_Sys_Highway_Gateway record);

    T_Sys_Highway_Gateway selectByPrimaryKey(String gatewayId);

    int updateByPrimaryKeySelective(T_Sys_Highway_Gateway record);

    int updateByPrimaryKey(T_Sys_Highway_Gateway record);
}