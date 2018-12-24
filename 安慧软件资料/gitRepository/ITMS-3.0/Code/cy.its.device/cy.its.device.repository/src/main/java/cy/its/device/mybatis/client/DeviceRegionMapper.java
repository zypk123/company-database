package cy.its.device.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.device.domain.model.DeviceRegion;

public interface DeviceRegionMapper {
    int deleteByPrimaryKey(String regionalId);

    int insert(DeviceRegion record);

    int insertSelective(DeviceRegion record);

    DeviceRegion selectByPrimaryKey(String regionalId);

    int updateByPrimaryKeySelective(DeviceRegion record);

    int updateByPrimaryKey(DeviceRegion record);
    
    List<DeviceRegion> findDeviceRegions(Map<String, Object> params);

	int countDeviceRegion(Map<String, Object> params);
}