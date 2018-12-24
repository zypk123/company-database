package cy.its.road.domain.repository.highway;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.road.domain.criteria.ServiceResourceCriteria;
import cy.its.road.domain.model.highway.ServiceResource;

public interface IServiceResourceRepository extends IRepository<ServiceResource> {

	List<ServiceResource> findServiceResources(
			ServiceResourceCriteria serviceResourceCriteria);

	int countServiceResources(ServiceResourceCriteria resourceCriteria);

}
