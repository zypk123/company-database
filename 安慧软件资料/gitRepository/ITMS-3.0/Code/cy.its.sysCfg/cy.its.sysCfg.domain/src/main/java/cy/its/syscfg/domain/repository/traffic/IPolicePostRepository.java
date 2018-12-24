package cy.its.syscfg.domain.repository.traffic;

import java.util.List;
import java.util.Map;

import cy.its.com.domain.IRepository;
import cy.its.syscfg.domain.model.traffic.PolicePost;

public interface IPolicePostRepository extends IRepository<PolicePost> {
	
	List<PolicePost> selectAll(Map map);
}
