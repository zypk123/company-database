package cy.its.trafficSituation.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.trafficSituation.domain.model.TrafficControl;

public interface TrafficControlMapper {
    int deleteByPrimaryKey(String trafficControlId);

    int insert(TrafficControl record);

    int insertSelective(TrafficControl record);

    TrafficControl selectByPrimaryKey(String trafficControlId);

    int updateByPrimaryKeySelective(TrafficControl record);

    int updateByPrimaryKey(TrafficControl record);
    
    List<TrafficControl> selectAll(Map<String,Object> map);
    List<TrafficControl> selectValid(Map<String,Object> map);
    long selectCount(Map<String,Object> map);
}