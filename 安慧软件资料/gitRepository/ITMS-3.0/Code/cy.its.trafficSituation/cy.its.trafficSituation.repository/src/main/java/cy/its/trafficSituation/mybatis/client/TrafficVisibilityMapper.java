package cy.its.trafficSituation.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.trafficSituation.domain.model.TrafficVisibility;

public interface TrafficVisibilityMapper {
    int deleteByPrimaryKey(String visibilityDataId);

    int insert(TrafficVisibility record);

    int insertSelective(TrafficVisibility record);

    TrafficVisibility selectByPrimaryKey(String visibilityDataId);

    int updateByPrimaryKeySelective(TrafficVisibility record);

    int updateByPrimaryKey(TrafficVisibility record);
    
    List<TrafficVisibility> selectVisibilitys(Map<String, Object> map);
    List<TrafficVisibility> select2HourVisibilitys(Map<String, Object> map);
}