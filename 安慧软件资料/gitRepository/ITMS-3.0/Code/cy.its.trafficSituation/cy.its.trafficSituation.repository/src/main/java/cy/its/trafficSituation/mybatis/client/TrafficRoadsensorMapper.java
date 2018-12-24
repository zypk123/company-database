package cy.its.trafficSituation.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.trafficSituation.domain.model.TrafficRoadsensor;

public interface TrafficRoadsensorMapper {
    int deleteByPrimaryKey(String roadsensorDataId);

    int insert(TrafficRoadsensor record);

    int insertSelective(TrafficRoadsensor record);

    TrafficRoadsensor selectByPrimaryKey(String roadsensorDataId);

    int updateByPrimaryKeySelective(TrafficRoadsensor record);

    int updateByPrimaryKey(TrafficRoadsensor record);
    
    List<TrafficRoadsensor> selectRoadsensors(Map<String,Object> map);
    
    List<TrafficRoadsensor> select2HourRoadsensors(Map<String,Object> map);
}