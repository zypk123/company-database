package cy.its.violation.repository;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.violation.domain.criteria.VioActionCriteria;
import cy.its.violation.domain.model.config.VioActionCode;

public interface IVioActionRepository extends IRepository<VioActionCode> {

	List<VioActionCode> findVioAction(VioActionCriteria crieria);
}
