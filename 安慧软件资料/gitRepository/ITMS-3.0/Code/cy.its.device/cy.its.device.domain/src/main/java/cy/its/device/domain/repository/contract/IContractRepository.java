package cy.its.device.domain.repository.contract;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.device.domain.criteria.ContractCriteria;
import cy.its.device.domain.model.Contract;

public interface IContractRepository extends IRepository<Contract> {
	List<Contract> findDeviceContracts(ContractCriteria criteria);

	int countDeviceContracts(ContractCriteria contractorCriteria);
}