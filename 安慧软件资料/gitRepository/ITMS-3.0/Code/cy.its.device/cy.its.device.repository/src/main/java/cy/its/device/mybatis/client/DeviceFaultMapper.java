package cy.its.device.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.device.domain.model.DeviceFault;
import cy.its.device.domain.model.DeviceFaultList;

public interface DeviceFaultMapper {
    int deleteByPrimaryKey(String faultId);

    int insert(DeviceFault record);

    int insertSelective(DeviceFault record);

    DeviceFault selectByPrimaryKey(String faultId);

    int updateByPrimaryKeySelective(DeviceFault record);

    int updateByPrimaryKey(DeviceFault record);
    
    List<DeviceFault> findDevFaultsMataince(Map<String, Object> params);
    
   	List<DeviceFault> findDevLatestFaultsMataince(Map<String, Object> params);
   	
    List<DeviceFault> findDeviceFaults(Map<String, Object> params);
    
	int countDeviceFaults(Map<String, Object> params);
	
	int deleteNoValidFaults(String faultIds);
	
	List<DeviceFaultList> selectFaultList(Map<String, Object> params);
}