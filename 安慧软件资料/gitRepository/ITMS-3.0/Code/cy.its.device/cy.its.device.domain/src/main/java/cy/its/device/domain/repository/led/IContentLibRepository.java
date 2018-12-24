package cy.its.device.domain.repository.led;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.device.domain.criteria.LedContentLibCriteria;
import cy.its.device.domain.model.led.LedContentLib;

public interface IContentLibRepository extends IRepository<LedContentLib>{

	long countContentLibs(LedContentLibCriteria criteria);

	List<LedContentLib> findContentLibs(LedContentLibCriteria criteria);

}
