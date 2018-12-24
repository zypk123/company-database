package cy.its.device.domain.repository.led;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.device.domain.criteria.LedProgTemplateCriteria;
import cy.its.device.domain.model.led.LedProgTemplate;

public interface ILedProgTempRepository extends IRepository<LedProgTemplate>{

	int templateCountOfSpecId(String specId);
	
	List<LedProgTemplate> findLedProgTemplates(LedProgTemplateCriteria criteria);
}
