package cy.its.syscfg.domain.service;

import java.util.List;

import cy.its.syscfg.domain.model.config.SysConfig;
import cy.its.syscfg.domain.model.sysCode.CodeType;

public interface ISysConfigService {
	
	List<SysConfig> findConfig(String type); 

    int updateByPrimaryKey(SysConfig record);
}
