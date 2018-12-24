package cy.its.device.domain.repository.led;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.device.domain.criteria.LedSpecCriteria;
import cy.its.device.domain.model.led.LedSpec;

public interface ILedSpecRepository extends IRepository<LedSpec> {

	List<LedSpec> findLedSpecs(LedSpecCriteria criteria);
	
	int countLedSpecs(LedSpecCriteria criteria);
}
