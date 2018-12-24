package cy.its.syscfg.mybatis.client;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cy.its.syscfg.mybatis.model.T_Sys_Config;


public interface SysConfigMapper { 
	
	List<T_Sys_Config> selectConfigByType(@Param(value = "type") String type);
}
