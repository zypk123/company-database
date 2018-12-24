package cy.its.road.mybatis.client;

import cy.its.road.domain.model.interflow.RoadInterflow;


public interface RoadInterflowMapper {
    int insert(RoadInterflow record);

    int insertSelective(RoadInterflow record);
    
    int deleteSelective(RoadInterflow record);
}