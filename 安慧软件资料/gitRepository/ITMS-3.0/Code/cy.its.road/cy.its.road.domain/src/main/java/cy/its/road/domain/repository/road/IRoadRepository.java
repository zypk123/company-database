package cy.its.road.domain.repository.road;

import java.util.List;
import java.util.Map;

import cy.its.com.domain.IRepository;
import cy.its.road.domain.criteria.RoadCriteria;
import cy.its.road.domain.model.road.Road;

public interface IRoadRepository extends IRepository<Road> {
	 List<Road> findRoads(RoadCriteria criteria);
	 void roadChanged();
	 
	 int remove(Map<String,Object> roadIds);
}
