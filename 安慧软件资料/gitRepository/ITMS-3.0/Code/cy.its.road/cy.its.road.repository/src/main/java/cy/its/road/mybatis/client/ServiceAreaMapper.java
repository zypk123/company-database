package cy.its.road.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.road.domain.model.highway.ServiceArea;

public interface ServiceAreaMapper {
	
    int deleteByPrimaryKey(String serviceAreaId);

    int insert(ServiceArea record);

    int insertSelective(ServiceArea record);

    ServiceArea selectByPrimaryKey(String serviceAreaId);

    int updateByPrimaryKeySelective(ServiceArea record);

    int updateByPrimaryKey(ServiceArea record);
    
    List<ServiceArea> selectServiceAreas(Map<String, Object> params);
    
    int deleteByRoadId(String roadId);

	int countServiceAreas(Map<String, Object> parseParams);
}