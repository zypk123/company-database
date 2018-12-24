package cy.its.device.domain.repository.site;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.device.domain.model.site.Lane;

public interface ILaneRepository extends  IRepository<Lane>{
	List<Lane> findLaneBySectionId(String sectionId);
}
