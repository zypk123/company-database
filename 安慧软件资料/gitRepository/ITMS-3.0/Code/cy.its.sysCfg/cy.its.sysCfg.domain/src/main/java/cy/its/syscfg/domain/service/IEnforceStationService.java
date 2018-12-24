package cy.its.syscfg.domain.service;

import java.util.List;
import java.util.Map;

import cy.its.syscfg.domain.model.traffic.EnforceStation;

public interface IEnforceStationService {
	EnforceStation enforceStationOfId(String id) throws Exception;
	String save(EnforceStation enforceStation);
	int delete(String id);
	int update(EnforceStation enforceStation);
	List<EnforceStation> selectAll(Map map);
}
