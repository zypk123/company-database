package cy.its.syscfg.domain.repository.traffic;

import java.util.List;
import java.util.Map;

import cy.its.com.domain.IRepository;
import cy.its.syscfg.domain.model.traffic.EnforceStation;

public interface IEnforceStationRepository extends IRepository<EnforceStation> {
	List<EnforceStation> selectAll(Map map);
}
