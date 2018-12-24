package cy.its.trafficSituation.mybatis.client;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cy.its.trafficSituation.domain.model.TrafficRegionFlow;

public interface TrafficRegionFlowMapper {
    int deleteByPrimaryKey(String regionStateId);

    int insert(TrafficRegionFlow record);

    int insertSelective(TrafficRegionFlow record);

    TrafficRegionFlow selectByPrimaryKey(String regionStateId);

    int updateByPrimaryKeySelective(TrafficRegionFlow record);

    int updateByPrimaryKey(TrafficRegionFlow record);
    
    List<TrafficRegionFlow> selectAllRegionFlow(Map<String,Object> map); 
    List<TrafficRegionFlow> select2HourRegionFlow(Map<String,Object> map); 
    
    List<TrafficRegionFlow> selectByRegionId(@Param(value="regionalId")String regionalId);
    TrafficRegionFlow selectLastFlowByRegionId(@Param(value="regionalId")String regionalId);
}