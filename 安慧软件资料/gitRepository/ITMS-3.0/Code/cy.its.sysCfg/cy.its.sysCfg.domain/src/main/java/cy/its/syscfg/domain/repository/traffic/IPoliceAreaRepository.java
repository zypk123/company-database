package cy.its.syscfg.domain.repository.traffic;

import java.util.List;
import java.util.Map;

import cy.its.com.domain.IRepository;
import cy.its.syscfg.domain.model.traffic.PoliceArea;

public interface IPoliceAreaRepository extends IRepository<PoliceArea>{
	List<PoliceArea> selectAll(Map map);
}
