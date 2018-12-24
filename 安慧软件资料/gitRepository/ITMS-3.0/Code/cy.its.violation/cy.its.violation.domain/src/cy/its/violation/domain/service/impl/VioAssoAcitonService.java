package cy.its.violation.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.violation.domain.model.config.VioAssoAction;
import cy.its.violation.domain.service.IVioAssoActionService;
import cy.its.violation.repository.IVioAssoActionRepository;

@Service
public class VioAssoAcitonService implements IVioAssoActionService {

	@Autowired
	IVioAssoActionRepository vioAssoActionRepository;

	@Override
	public int removeCode(String codeId) {
		return vioAssoActionRepository.delete(codeId);
	}

	@Override
	public String saveVioAssoAction(VioAssoAction code) {
		return vioAssoActionRepository.save(code);
	}

	@Override
	public int updateVioAssoAction(VioAssoAction code) {
		return vioAssoActionRepository.update(code);
	}

	@Override
	public List<VioAssoAction> SelectByViolationCode(String violationCode) throws Exception {
		return vioAssoActionRepository.SelectByViolationCode(violationCode);
	}

}
