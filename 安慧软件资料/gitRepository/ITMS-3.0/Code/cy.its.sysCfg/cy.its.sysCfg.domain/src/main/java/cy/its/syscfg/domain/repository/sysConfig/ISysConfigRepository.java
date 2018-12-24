package cy.its.syscfg.domain.repository.sysConfig;
 

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.syscfg.domain.model.config.SysConfig; 

public interface ISysConfigRepository extends IRepository<SysConfig>{ 

    List<SysConfig> findConfig(String type);

    int updateByPrimaryKeySelective(SysConfig record);

    int updateByPrimaryKey(SysConfig record);

}
