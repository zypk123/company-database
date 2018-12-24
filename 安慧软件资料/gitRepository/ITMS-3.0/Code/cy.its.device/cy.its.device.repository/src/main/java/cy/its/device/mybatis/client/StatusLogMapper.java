package cy.its.device.mybatis.client;

import cy.its.device.domain.model.StatusLog;

public interface StatusLogMapper {
    int deleteByPrimaryKey(String satusLogId);

    int insert(StatusLog record);

    int insertSelective(StatusLog record);

    StatusLog selectByPrimaryKey(String satusLogId);

    int updateByPrimaryKeySelective(StatusLog record);

    int updateByPrimaryKey(StatusLog record);
}