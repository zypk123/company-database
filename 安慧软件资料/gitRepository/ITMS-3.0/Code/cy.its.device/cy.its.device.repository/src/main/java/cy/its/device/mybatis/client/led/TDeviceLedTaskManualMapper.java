package cy.its.device.mybatis.client.led;

import cy.its.device.mybatis.model.TDeviceLedTaskManual;

public interface TDeviceLedTaskManualMapper {
    int deleteByPrimaryKey(String taskId);

    int insert(TDeviceLedTaskManual record);

    int insertSelective(TDeviceLedTaskManual record);

    TDeviceLedTaskManual selectByPrimaryKey(String taskId);

    int updateByPrimaryKeySelective(TDeviceLedTaskManual record);

    int updateByPrimaryKey(TDeviceLedTaskManual record);
}