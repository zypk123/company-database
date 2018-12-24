package cy.its.syscfg.domain.repository.traffic;

import java.util.List;
import java.util.Map;

import cy.its.com.domain.IRepository;
import cy.its.syscfg.domain.model.traffic.OverRunCheckPoint;

public interface IOverRunCheckPointRepository extends IRepository<OverRunCheckPoint> {
	List<OverRunCheckPoint> selectAll(Map map);
}
