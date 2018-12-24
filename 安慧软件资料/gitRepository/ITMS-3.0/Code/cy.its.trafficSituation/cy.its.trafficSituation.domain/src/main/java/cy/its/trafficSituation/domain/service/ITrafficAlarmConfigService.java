package cy.its.trafficSituation.domain.service;

import java.util.List;
import java.util.Map;

import cy.its.trafficSituation.domain.model.TrafficAlarmConfig;

public interface ITrafficAlarmConfigService {
	TrafficAlarmConfig trafficAlarmConfigOfId(String id) throws Exception;	
	String save(TrafficAlarmConfig trafficAlarmConfig);	
	int delete(String id);
	int update(TrafficAlarmConfig trafficAlarmConfig);
	List<TrafficAlarmConfig> selectAll(Map map);
	List<TrafficAlarmConfig> selectByType(String alarmValueType);
}
