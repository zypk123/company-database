package cy.its.violation.repository;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.violation.domain.model.config.VioAssoAction;

public interface IVioAssoActionRepository extends IRepository<VioAssoAction> {

	List<VioAssoAction> SelectByViolationCode(String violationCode);
}
