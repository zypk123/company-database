package cy.its.road.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.road.domain.criteria.InterflowCriteria;
import cy.its.road.domain.model.interflow.Interflow;
import cy.its.road.domain.repository.interflow.IInterFlowRepository;
import cy.its.road.domain.service.IInterflowService;

/**
 * 高速相关服务
 * 
 * @author STJ
 *
 */
@Service
public class InterflowService implements IInterflowService {

	@Autowired
	IInterFlowRepository interFlowRepository;

	public List<Interflow> findInterflows(InterflowCriteria interflowCriteria) {
		if(interflowCriteria.getNeedTotal()){
			interflowCriteria.setTotal(interFlowRepository
			.countInterflows(interflowCriteria));
		}
		return interFlowRepository.findInterflows(interflowCriteria);
	}

	public Interflow interflowOfId(String id) throws Exception {
		return interFlowRepository.aggregateOfId(id);
	}

	public void saveInterflow(Interflow interflow) {
		interFlowRepository.save(interflow);
	}

	public void updateInterflow(Interflow interflow) {
		interFlowRepository.update(interflow);
	}

	public void deleteInterflow(String interflowId) {
		interFlowRepository.delete(interflowId);
	}
	
}
