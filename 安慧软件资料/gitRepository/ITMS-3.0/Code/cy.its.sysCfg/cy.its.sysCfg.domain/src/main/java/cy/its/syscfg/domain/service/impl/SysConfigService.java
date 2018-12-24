package cy.its.syscfg.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.syscfg.domain.model.config.SysConfig;
import cy.its.syscfg.domain.repository.sysCode.ISysCodeRepository;
import cy.its.syscfg.domain.repository.sysConfig.ISysConfigRepository;
import cy.its.syscfg.domain.service.ISysConfigService;

@Service
public class SysConfigService implements ISysConfigService {

	@Autowired
	ISysConfigRepository sysConfigRepository;
	
	@Override
	public List<SysConfig> findConfig(String type) { 
		return sysConfigRepository.findConfig(type);
	}  

	@Override
	public int updateByPrimaryKey(SysConfig record) {
		return sysConfigRepository.updateByPrimaryKeySelective(record);
	}

}
