package cy.its.syscfg.domain.service;

import java.util.List;
import java.util.Map;

import cy.its.syscfg.domain.model.traffic.PoliceArea;

public interface IPoliceAreaService {
	PoliceArea policeAreaOfId(String id) throws Exception;
	String save(PoliceArea policeArea);
	int delete(String id);
	int update(PoliceArea policeArea);
	List<PoliceArea> selectAll(Map map);
}
