package cy.its.device.mybatis.client;

import cy.its.device.domain.model.EventDetection;

public interface EventDetectionMapper {
    int deleteByPrimaryKey(String deviceId);

    int insert(EventDetection record);

    int insertSelective(EventDetection record);

    EventDetection selectByPrimaryKey(String deviceId);

    int updateByPrimaryKeySelective(EventDetection record);

    int updateByPrimaryKey(EventDetection record);
}