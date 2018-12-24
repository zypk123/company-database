package cy.its.trafficSituation.domain.repository;

import cy.its.com.domain.IRepository;
import cy.its.trafficSituation.domain.model.TrafficEventProcess;

public interface ITrafficEventProcessRepository extends IRepository<TrafficEventProcess> {
	TrafficEventProcess selectByEventId(String eventId);
}
