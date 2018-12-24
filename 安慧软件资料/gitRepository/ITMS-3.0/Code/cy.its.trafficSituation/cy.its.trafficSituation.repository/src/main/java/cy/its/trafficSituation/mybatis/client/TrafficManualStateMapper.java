package cy.its.trafficSituation.mybatis.client;

import java.util.Map;

import cy.its.trafficSituation.domain.model.TrafficManualState;

public interface TrafficManualStateMapper {
    int deleteByPrimaryKey(String stateControlId);

    int insert(TrafficManualState record);

    int insertSelective(TrafficManualState record);

    TrafficManualState selectByPrimaryKey(String stateControlId);

    int updateByPrimaryKeySelective(TrafficManualState record);

    int updateByPrimaryKey(TrafficManualState record);
    
    TrafficManualState selectByRoadId(Map map);
}