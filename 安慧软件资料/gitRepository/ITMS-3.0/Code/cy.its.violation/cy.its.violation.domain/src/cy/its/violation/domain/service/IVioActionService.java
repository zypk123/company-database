package cy.its.violation.domain.service;

import java.util.List;

import cy.its.violation.domain.criteria.VioActionCriteria;
import cy.its.violation.domain.model.config.VioActionCode;

public interface IVioActionService {

	int removeCode(String codeId);

	String saveActionCode(VioActionCode code);

	int updateActionCode(VioActionCode code);

	List<VioActionCode> findVioAction(VioActionCriteria crieria);

	VioActionCode findVioActionByPrimaryKey(String id) throws Exception;

}
