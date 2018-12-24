package cy.its.trafficSituation.domain.service;

import java.util.List;
import java.util.Map;

import cy.its.trafficSituation.domain.model.TrafficControl;

public interface ITrafficControlService {
	TrafficControl trafficControlOfId(String id) throws Exception;	
	String save(TrafficControl trafficControl);	
	int delete(String id);
	int update(TrafficControl trafficControl);
	int updateSelective(TrafficControl trafficControl);
	List<TrafficControl> selectAll(Map<String,Object> map);
	List<TrafficControl> selectValid(Map<String,Object> map);
	long selectCount(Map<String,Object> map);
}
