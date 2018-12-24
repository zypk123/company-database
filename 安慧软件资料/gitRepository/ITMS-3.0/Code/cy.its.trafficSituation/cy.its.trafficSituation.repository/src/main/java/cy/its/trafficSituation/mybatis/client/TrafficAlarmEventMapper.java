package cy.its.trafficSituation.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.trafficSituation.domain.model.TrafficAlarmEvent;

public interface TrafficAlarmEventMapper {
    int deleteByPrimaryKey(String alarmEventId);

    int insert(TrafficAlarmEvent record);

    int insertSelective(TrafficAlarmEvent record);

    TrafficAlarmEvent selectByPrimaryKey(String alarmEventId);

    int updateByPrimaryKeySelective(TrafficAlarmEvent record);

    int updateByPrimaryKey(TrafficAlarmEvent record);
    
    List<TrafficAlarmEvent> selectAll(Map<String, Object> map);
    
    long selectCountByType(Map map);
}