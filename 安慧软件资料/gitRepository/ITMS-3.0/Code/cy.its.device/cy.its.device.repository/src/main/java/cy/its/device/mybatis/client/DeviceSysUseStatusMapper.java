package cy.its.device.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.device.domain.model.DeviceSysUseStatus;

public interface DeviceSysUseStatusMapper {
    int deleteByPrimaryKey(String sysUseStatusId);

    int insert(DeviceSysUseStatus record);

    int insertSelective(DeviceSysUseStatus record);

    DeviceSysUseStatus selectByPrimaryKey(String sysUseStatusId);

    int updateByPrimaryKeySelective(DeviceSysUseStatus record);

    int updateByPrimaryKey(DeviceSysUseStatus record);
    
    List<DeviceSysUseStatus> selectUseStatus(Map<String, Object> mapParams);
}