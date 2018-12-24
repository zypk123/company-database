package cy.its.device.mybatis.client;

import cy.its.device.domain.model.FaultLog;

public interface FaultLogMapper {
    int deleteByPrimaryKey(String faultEventId);

    int insert(FaultLog record);

    int insertSelective(FaultLog record);

    FaultLog selectByPrimaryKey(String faultEventId);

    int updateByPrimaryKeySelective(FaultLog record);

    int updateByPrimaryKey(FaultLog record);
}