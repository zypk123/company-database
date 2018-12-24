package cy.its.trafficSituation.mybatis.client;

import cy.its.trafficSituation.domain.model.TrafficEventProcess;

public interface TrafficEventProcessMapper {
    int deleteByPrimaryKey(String eventProcessId);

    int insert(TrafficEventProcess record);

    int insertSelective(TrafficEventProcess record);

    TrafficEventProcess selectByPrimaryKey(String eventProcessId);
    
    TrafficEventProcess selectByEventId(String eventId);

    int updateByPrimaryKeySelective(TrafficEventProcess record);

    int updateByPrimaryKey(TrafficEventProcess record);
}