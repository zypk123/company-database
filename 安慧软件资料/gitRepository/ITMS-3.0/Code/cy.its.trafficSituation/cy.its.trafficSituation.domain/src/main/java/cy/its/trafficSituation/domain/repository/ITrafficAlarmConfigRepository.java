package cy.its.trafficSituation.domain.repository;

import java.util.List;
import java.util.Map;

import cy.its.com.domain.IRepository;
import cy.its.trafficSituation.domain.model.TrafficAlarmConfig;

public interface ITrafficAlarmConfigRepository extends IRepository<TrafficAlarmConfig> {
	List<TrafficAlarmConfig> selectAll(Map map);
	List<TrafficAlarmConfig> selectByType(String alarmValueType);
}
