package cy.its.road.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.road.domain.model.highway.ServiceResource;


public interface ServiceResourceMapper {
    int deleteByPrimaryKey(String serviceResourceId);

    int insert(ServiceResource record);

    int insertSelective(ServiceResource record);

    ServiceResource selectByPrimaryKey(String serviceResourceId);

    int updateByPrimaryKeySelective(ServiceResource record);

    int updateByPrimaryKey(ServiceResource record);    

    List<ServiceResource> selectServiceResources(Map<String, Object> params);

	int countServiceResources(Map<String, Object> parseParams);
}