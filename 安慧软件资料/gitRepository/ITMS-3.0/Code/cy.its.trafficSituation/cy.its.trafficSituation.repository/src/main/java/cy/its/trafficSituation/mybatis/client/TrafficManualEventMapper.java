package cy.its.trafficSituation.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.trafficSituation.domain.model.TrafficManualEvent;

public interface TrafficManualEventMapper {
    int deleteByPrimaryKey(String roadEventId);

    int insert(TrafficManualEvent record);

    int insertSelective(TrafficManualEvent record);

    TrafficManualEvent selectByPrimaryKey(String roadEventId);

    int updateByPrimaryKeySelective(TrafficManualEvent record);

    int updateByPrimaryKey(TrafficManualEvent record);
    
    List<TrafficManualEvent> selectAll(Map<String, Object> map);
}