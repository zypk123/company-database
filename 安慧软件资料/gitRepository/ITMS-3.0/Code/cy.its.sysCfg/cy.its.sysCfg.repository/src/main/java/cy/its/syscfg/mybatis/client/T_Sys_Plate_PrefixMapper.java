package cy.its.syscfg.mybatis.client;


import java.util.List;

import cy.its.syscfg.mybatis.model.T_Sys_Plate_Prefix;

public interface T_Sys_Plate_PrefixMapper {
    List<T_Sys_Plate_Prefix> selectAllData();
}