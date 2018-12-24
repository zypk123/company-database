package cy.its.road.domain.repository.highway;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.road.domain.criteria.ServiceAreaCriteria;
import cy.its.road.domain.model.highway.ServiceArea;

public interface IServiceAreaRepository extends
		IRepository<ServiceArea> {

	List<ServiceArea> findHighwayServiceAreas(
			ServiceAreaCriteria highwayServiceAreaCriteria);

	int deleteByRoadId(String roadId);

	int countHighwayServiceAreas(ServiceAreaCriteria serviceAreaCriteria);
}
