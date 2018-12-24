package cy.its.device.mybatis.client;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cy.its.device.domain.criteria.MeteorologicCriteria;
import cy.its.device.domain.model.DeviceGenalSituation;
import cy.its.device.domain.model.DeviceSysCapability;
import cy.its.device.domain.model.Meteorologic;
import cy.its.device.domain.model.MeteorologicSys;
import cy.its.device.domain.model.SiteSys;
import cy.its.device.domain.model.Sys;
import cy.its.device.domain.model.Video;
import cy.its.platform.common.model.DeviceCertiStatusModel;

public interface SysMapper {
    int deleteByPrimaryKey(String deviceId);

	int insert(Sys record);

	int insertSelective(Sys record);

	Sys selectByPrimaryKey(String deviceId);
	
	Sys selectByNBR(String deviceSysNbr);

	int updateByPrimaryKeySelective(Sys record);

	int updateByPrimaryKey(Sys record);	
	
	int updateBySectionId(SiteSys siteSys);
    
    List<Sys> selectSys(Map<String, Object> params);

	int countSys(Map<String, Object> parseParams);	
	
	List<Sys> selectSysAndEquipment(Map<String, Object> params);

	int countSysAndEquipment(Map<String, Object> parseParams);
	
	List<DeviceSysCapability> selectSysCapability(@Param("deviceId")String deviceId);
	
//	List<DeviceGenalSituation> getDevGeneralSituation(String orgPrevilegeCode);

//	List<Sys> findDeviceIdByServerTypeGrpId(String serverTypeGrpId);
	
	List<DeviceCertiStatusModel> getAllCertiDevice();
	
//	Sys sysOfDeviceKey(String deviceKey);
	
	List<MeteorologicSys> selectMeteorSys(Map<String, Object> parseParams);
}