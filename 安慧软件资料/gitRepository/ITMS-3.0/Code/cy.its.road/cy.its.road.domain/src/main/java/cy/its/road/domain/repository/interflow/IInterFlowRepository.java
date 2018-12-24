package cy.its.road.domain.repository.interflow;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.road.domain.criteria.InterflowCriteria;
import cy.its.road.domain.criteria.RoadInterflowCriteria;
import cy.its.road.domain.model.interflow.Interflow;

public interface IInterFlowRepository extends IRepository<Interflow> {

	List<Interflow> findInterflows(
			InterflowCriteria interflowCriteria);
	
	int deleteRoadInterflow(RoadInterflowCriteria criteria);

	int countInterflows(InterflowCriteria interflowCriteria);

}
