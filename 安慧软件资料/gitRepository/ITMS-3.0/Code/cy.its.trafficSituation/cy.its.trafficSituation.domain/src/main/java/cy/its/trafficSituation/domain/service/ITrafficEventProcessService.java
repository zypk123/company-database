package cy.its.trafficSituation.domain.service;

import cy.its.trafficSituation.domain.model.TrafficEventProcess;

public interface ITrafficEventProcessService {
	TrafficEventProcess trafficEventProcessOfId(String id) throws Exception;	
	String save(TrafficEventProcess trafficEventProcess);	
	int delete(String id);
	int update(TrafficEventProcess trafficEventProcess);
	TrafficEventProcess selectByEventId(String eventId);
}
