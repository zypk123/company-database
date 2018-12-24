package cy.its.trafficSituation.mybatis.client;

import cy.its.trafficSituation.domain.model.TrafficEventImage;

public interface TrafficEventImageMapper {
    int deleteByPrimaryKey(String eventImageId);

    int insert(TrafficEventImage record);

    int insertSelective(TrafficEventImage record);

    TrafficEventImage selectByPrimaryKey(String eventImageId);

    int updateByPrimaryKeySelective(TrafficEventImage record);

    int updateByPrimaryKey(TrafficEventImage record);
    
    TrafficEventImage selectByAlarmId(String alarmEventId);
}