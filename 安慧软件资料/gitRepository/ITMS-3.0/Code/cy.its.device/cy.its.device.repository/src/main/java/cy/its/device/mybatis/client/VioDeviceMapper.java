package cy.its.device.mybatis.client;

import cy.its.device.domain.model.VioDevice;

public interface VioDeviceMapper {
    int deleteByPrimaryKey(String deviceId);

    int insert(VioDevice record);

    int insertSelective(VioDevice record);

    VioDevice selectByPrimaryKey(String deviceId);

    int updateByPrimaryKeySelective(VioDevice record);

    int updateByPrimaryKey(VioDevice record);
}