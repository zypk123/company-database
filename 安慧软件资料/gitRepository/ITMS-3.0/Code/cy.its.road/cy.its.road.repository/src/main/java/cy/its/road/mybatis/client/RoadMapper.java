package cy.its.road.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.road.domain.criteria.RoadCriteria;
import cy.its.road.domain.model.road.Road;

public interface RoadMapper {
    int deleteByPrimaryKey(String roadId);

    int insert(Road record);

    int insertSelective(Road record);

    Road selectByPrimaryKey(String roadId);

    int updateByPrimaryKeySelective(Road record);

    int updateByPrimaryKey(Road record);
    
    List<Road> selectRoads(Map<String, Object> params);
     
    List<Road> selectAllRoads();
    //ÅúÁ¿É¾³ý
    int removeByPrimaryKey(Map<String,Object> roadIds);
    
   	List<Road> findRoad(RoadCriteria criteria);
}