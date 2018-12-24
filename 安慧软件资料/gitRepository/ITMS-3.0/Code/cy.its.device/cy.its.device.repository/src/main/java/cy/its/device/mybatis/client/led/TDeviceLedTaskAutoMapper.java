package cy.its.device.mybatis.client.led;

import cy.its.device.mybatis.model.TDeviceLedTaskAuto;

public interface TDeviceLedTaskAutoMapper {
    int deleteByPrimaryKey(String taskId);

    int insert(TDeviceLedTaskAuto record);

    int insertSelective(TDeviceLedTaskAuto record);

    TDeviceLedTaskAuto selectByPrimaryKey(String taskId);

    int updateByPrimaryKeySelective(TDeviceLedTaskAuto record);

    int updateByPrimaryKey(TDeviceLedTaskAuto record);
}