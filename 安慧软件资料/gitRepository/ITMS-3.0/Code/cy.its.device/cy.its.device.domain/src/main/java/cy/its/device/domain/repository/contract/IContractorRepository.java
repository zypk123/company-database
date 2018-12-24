package cy.its.device.domain.repository.contract;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.device.domain.criteria.ContractorCriteria;
import cy.its.device.domain.model.Contractor;

public interface IContractorRepository extends IRepository<Contractor>
{
	List<Contractor> findDeviceContractors(ContractorCriteria contractorCriteria);
	
	int countDeviceContractors(ContractorCriteria contractorCriteria);
}