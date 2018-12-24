package cy.its.trafficSituation.domain.service;

import java.util.List;
import java.util.Map;

import cy.its.trafficSituation.domain.model.TrafficAlarmEvent;

public interface ITrafficAlarmEventService {
	List<TrafficAlarmEvent> selectAll(Map<String, Object> map);
	int update(TrafficAlarmEvent trafficAlarmEvent);
	long selectCountByType(Map map);
}
