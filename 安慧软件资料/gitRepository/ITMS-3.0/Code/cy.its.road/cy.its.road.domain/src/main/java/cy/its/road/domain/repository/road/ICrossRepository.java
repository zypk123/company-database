package cy.its.road.domain.repository.road;

import java.util.List;
import java.util.Map;

import cy.its.com.domain.IRepository;
import cy.its.road.domain.criteria.CrossCriteria;
import cy.its.road.domain.model.road.Cross;

public interface ICrossRepository extends IRepository<Cross> {
	List<Cross> findCrosses(CrossCriteria criteria);

	int countCrosses(CrossCriteria crossCriteria);
	
	int removeCross(Map<String,Object> crossIds);
}
