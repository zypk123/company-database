package cy.its.trafficSituation.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.trafficSituation.domain.model.MapRoad;


public interface MapRoadMapper {

    List<MapRoad> selectAllRoad(Map map);
    MapRoad selectByPrimaryKey(String cyid);
    List<MapRoad> selectByRegionalId(String regionalId);
    int selectRoadCount(Map map);
}