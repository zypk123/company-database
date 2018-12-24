package cy.its.trafficSituation.mybatis.client;

import cy.its.trafficSituation.domain.model.TrafficState;

public interface TrafficStateMapper {
    int deleteByPrimaryKey(String roadStateId);

    int insert(TrafficState record);

    int insertSelective(TrafficState record);

    TrafficState selectByPrimaryKey(String roadStateId);

    int updateByPrimaryKeySelective(TrafficState record);

    int updateByPrimaryKey(TrafficState record);
    
    TrafficState selectByRoadSectionID(String roadSectionId);
}