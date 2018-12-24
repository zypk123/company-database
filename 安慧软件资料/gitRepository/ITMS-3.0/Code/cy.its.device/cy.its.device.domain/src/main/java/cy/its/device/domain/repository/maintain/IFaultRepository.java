package cy.its.device.domain.repository.maintain;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.device.domain.criteria.FaultCriteria;
import cy.its.device.domain.model.Fault;

public interface IFaultRepository extends IRepository<Fault>
{
	List<Fault> findFaults(FaultCriteria faultCriteria);
	int countFaults(FaultCriteria faultCriteria);
}