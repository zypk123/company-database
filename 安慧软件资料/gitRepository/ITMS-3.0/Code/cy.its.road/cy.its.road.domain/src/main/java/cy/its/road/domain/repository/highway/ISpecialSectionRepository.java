package cy.its.road.domain.repository.highway;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.road.domain.criteria.SpecialSectionCriteria;
import cy.its.road.domain.model.highway.SpecialSection;

public interface ISpecialSectionRepository extends
		IRepository<SpecialSection> {

	List<SpecialSection> findHighwaySpecialSections(
			SpecialSectionCriteria highwaySpecialSectionCriteria);

	int deleteByRoadId(String roadId);

	int countHighwaySpecialSections(
			SpecialSectionCriteria specialSectionCriteria);
}
