package cy.its.road.domain.repository.road;

import java.util.List;
import java.util.Map;

import cy.its.com.domain.IRepository;
import cy.its.road.domain.criteria.RoadSectionCriteria;
import cy.its.road.domain.model.road.RoadSection;

public interface IRoadSectionRepository extends IRepository<RoadSection> {
	List<RoadSection> findRoadSections(RoadSectionCriteria roadSectionCriteria) ;

	int countRoadSections(RoadSectionCriteria roadSectionCriteria);
	
	int remove(Map<String,Object> roadSectionIds);
}
