package cy.its.road.mybatis.client;

import org.springframework.stereotype.Service;

public interface ServiceMapper {
    int deleteByPrimaryKey(String serviceId);

    int insert(Service record);

    int insertSelective(Service record);

    Service selectByPrimaryKey(String serviceId);

    int updateByPrimaryKeySelective(Service record);

    int updateByPrimaryKey(Service record);
}