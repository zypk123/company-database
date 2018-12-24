package cy.its.trafficSituation.domain.repository;

import java.util.List;
import java.util.Map;

import cy.its.com.domain.IRepository;
import cy.its.trafficSituation.domain.model.TrafficAlarmEvent;

public interface ITrafficAlarmEventRepository extends IRepository<TrafficAlarmEvent> {
	List<TrafficAlarmEvent> selectAll(Map<String, Object> map);
	 long selectCountByType(Map map);
}
