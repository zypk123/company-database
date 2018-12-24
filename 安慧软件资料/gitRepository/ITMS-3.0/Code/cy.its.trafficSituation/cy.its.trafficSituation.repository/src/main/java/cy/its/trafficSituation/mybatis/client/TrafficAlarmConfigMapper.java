package cy.its.trafficSituation.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.trafficSituation.domain.model.TrafficAlarmConfig;


public interface TrafficAlarmConfigMapper { 
	
	int deleteByPrimaryKey(String alarmValueId);

	int insert(TrafficAlarmConfig record);

	int insertSelective(TrafficAlarmConfig record);

	TrafficAlarmConfig selectByPrimaryKey(String alarmValueId);

	int updateByPrimaryKeySelective(TrafficAlarmConfig record);

	int updateByPrimaryKey(TrafficAlarmConfig record);

	List<TrafficAlarmConfig> selectAll(Map map);
	
	List<TrafficAlarmConfig> selectByType(String alarmValueType);
}