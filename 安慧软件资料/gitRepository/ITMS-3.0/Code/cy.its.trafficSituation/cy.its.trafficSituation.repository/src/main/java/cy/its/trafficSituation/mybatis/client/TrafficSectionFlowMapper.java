package cy.its.trafficSituation.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.trafficSituation.domain.model.TrafficSectionFlow;

public interface TrafficSectionFlowMapper {
	
	TrafficSectionFlow selectByPrimaryKey(String sectionStateId);
	
    int insert(TrafficSectionFlow record);

    int insertSelective(TrafficSectionFlow record);
    
    List<TrafficSectionFlow> selectAllSectionFlow(Map<String,Object> map);
    
     List<TrafficSectionFlow> select2HourSectionFlow(Map<String,Object> map);
    
    TrafficSectionFlow selectSectionFlowBySectionId(String sectionId);
}