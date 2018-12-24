package cy.its.device.mybatis.client.led;

import cy.its.device.mybatis.model.TDeviceLedProgTemplate;

public interface TDeviceLedProgTemplateMapper {
    int deleteByPrimaryKey(String progTemplateId);

    int insert(TDeviceLedProgTemplate record);

    int insertSelective(TDeviceLedProgTemplate record);

    TDeviceLedProgTemplate selectByPrimaryKey(String progTemplateId);

    int updateByPrimaryKeySelective(TDeviceLedProgTemplate record);

    int updateByPrimaryKey(TDeviceLedProgTemplate record);
}