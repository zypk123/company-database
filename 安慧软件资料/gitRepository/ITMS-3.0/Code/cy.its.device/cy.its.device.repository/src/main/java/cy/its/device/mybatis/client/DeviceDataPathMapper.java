package cy.its.device.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.device.domain.model.DataPathBasic;
import cy.its.device.domain.model.DataPathDetail;
import cy.its.device.domain.model.DeviceDataPath;
import cy.its.device.mybatis.model.DynamicDataPathDetail;

public interface DeviceDataPathMapper {
    int deleteByPrimaryKey(String dataPathId);

    int insert(DeviceDataPath record);

    int insertSelective(DeviceDataPath record);

    DeviceDataPath selectByPrimaryKey(String dataPathId);

    int updateByPrimaryKeySelective(DeviceDataPath record);

    int updateByPrimaryKey(DeviceDataPath record);
    
    List<DeviceDataPath> findDeviceDataPath(Map<String, Object> params);
    
    int countDataPath(Map<String, Object> parseParams);
        
    List<DataPathBasic> selectDataPathBasics(Map<String, Object> parseParams);
    
    DataPathDetail dataPathDetailOfDevice(Map<String, Object> parseParams);
    
    DynamicDataPathDetail selectDynamicDataPath(Map<String, Object> parseParams);

	List<DataPathBasic> selectDataPathToItmsBasics(Map<String, Object> parseParams);

	DynamicDataPathDetail selectDynamicDataPathToItms(Map<String, Object> createParam);
	
	List<DataPathBasic> selectAllDataPathBasics(Map<String, Object> parseParams);
    
}