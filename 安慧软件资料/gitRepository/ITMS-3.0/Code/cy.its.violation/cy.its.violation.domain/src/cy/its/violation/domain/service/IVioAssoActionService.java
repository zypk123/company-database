package cy.its.violation.domain.service;

import java.util.List;

import cy.its.violation.domain.model.config.VioAssoAction;

public interface IVioAssoActionService {

	int removeCode(String codeId);

	String saveVioAssoAction(VioAssoAction code);

	int updateVioAssoAction(VioAssoAction code);

	List<VioAssoAction> SelectByViolationCode(String violationCode) throws Exception;
}
