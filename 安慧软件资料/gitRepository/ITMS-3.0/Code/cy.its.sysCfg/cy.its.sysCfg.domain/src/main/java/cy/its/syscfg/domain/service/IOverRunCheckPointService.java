package cy.its.syscfg.domain.service;

import java.util.List;
import java.util.Map;

import cy.its.syscfg.domain.model.traffic.OverRunCheckPoint;

public interface IOverRunCheckPointService {
	OverRunCheckPoint overRunCheckPointOfId(String id) throws Exception;
	String save(OverRunCheckPoint overRunCheckPoint);
	int delete(String id);
	int update(OverRunCheckPoint overRunCheckPoint);
	List<OverRunCheckPoint> selectAll(Map map);
}
