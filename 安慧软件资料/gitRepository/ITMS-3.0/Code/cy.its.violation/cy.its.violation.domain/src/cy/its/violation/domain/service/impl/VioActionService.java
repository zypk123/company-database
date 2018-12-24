package cy.its.violation.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.violation.domain.criteria.VioActionCriteria;
import cy.its.violation.domain.model.config.VioActionCode;
import cy.its.violation.domain.service.IVioActionService;
import cy.its.violation.repository.IVioActionRepository;

@Service
public class VioActionService implements IVioActionService {

	@Autowired
	IVioActionRepository vioActionRepository;

	@Override
	public int removeCode(String codeId) {
		return vioActionRepository.delete(codeId);
	}

	@Override
	public String saveActionCode(VioActionCode code) {
		return vioActionRepository.save(code);
	}

	@Override
	public int updateActionCode(VioActionCode code) {
		return vioActionRepository.update(code);
	}

	@Override
	public List<VioActionCode> findVioAction(VioActionCriteria crieria) {
		return vioActionRepository.findVioAction(crieria);
	}

	@Override
	public VioActionCode findVioActionByPrimaryKey(String id) throws Exception {
		return vioActionRepository.aggregateOfId(id);
	}

}
