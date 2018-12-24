package cy.its.device.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.ParamUtil;
import cy.its.device.domain.criteria.ContractorCriteria;
import cy.its.device.domain.model.Contractor;
import cy.its.device.domain.repository.contract.IContractorRepository;
import cy.its.device.mybatis.client.ContractorMapper;

@Service
public class ContractorRepository implements IContractorRepository {

	@Autowired
	ContractorMapper contractorMapper;

	public Contractor aggregateOfId(String id) throws Exception {
		return contractorMapper.selectByPrimaryKey(id);
	}

	public String save(Contractor obj) {
		obj.setContractorId(UUID.randomUUID().toString().replace("-", ""));
		contractorMapper.insertSelective(obj);
		return obj.getContractorId();
	}

	public int delete(String id) {
		return contractorMapper.deleteByPrimaryKey(id);
	}

	public int update(Contractor obj) {
		return contractorMapper.updateByPrimaryKeySelective(obj);
	}

	public List<Contractor> findDeviceContractors(
			ContractorCriteria contractorCriteria) {
		return contractorMapper.selectContractors(ParamUtil
				.parseParams(contractorCriteria));
	}

	public int countDeviceContractors(ContractorCriteria contractorCriteria) {
		return contractorMapper.countContractors(ParamUtil
				.parseParams(contractorCriteria));
	}
}
