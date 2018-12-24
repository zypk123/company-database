package cy.its.road.domain.repository.region;

import java.util.List;
import java.util.Map;

import cy.its.com.domain.IRepository;
import cy.its.road.domain.criteria.RegionCriteria;
import cy.its.road.domain.model.region.Region;

public interface IRegionRepository extends IRepository<Region> {
	
	List<Region> findRegions(RegionCriteria criteria);

	int countRegions(RegionCriteria regionCriteria); 
	
	int remove(Map<String,Object> regionsIds);
}
