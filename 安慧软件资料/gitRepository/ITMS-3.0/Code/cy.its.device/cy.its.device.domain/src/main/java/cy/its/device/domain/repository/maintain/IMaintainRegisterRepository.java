package cy.its.device.domain.repository.maintain;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.device.domain.criteria.MaintainRegisterCriteria;
import cy.its.device.domain.model.MaintainRegister;

public interface IMaintainRegisterRepository extends IRepository<MaintainRegister>
{
	List<MaintainRegister> findMaintainRegisters(MaintainRegisterCriteria maintainRegisterCriteria);

	int countMaintainRegisters(MaintainRegisterCriteria criteria);
}