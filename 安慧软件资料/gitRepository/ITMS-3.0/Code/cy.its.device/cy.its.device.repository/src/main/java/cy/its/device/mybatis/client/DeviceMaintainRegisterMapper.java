package cy.its.device.mybatis.client;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cy.its.device.domain.model.DeviceMaintainRegister;
import cy.its.device.domain.model.FalutMaintainAsso;

public interface DeviceMaintainRegisterMapper {
    int deleteByPrimaryKey(String maintenanceId);

    int insert(DeviceMaintainRegister record);

    int insertSelective(DeviceMaintainRegister record);

    DeviceMaintainRegister selectByPrimaryKey(String maintenanceId);

    int updateByPrimaryKeySelective(DeviceMaintainRegister record);

    int updateByPrimaryKey(DeviceMaintainRegister record);
    
    List<DeviceMaintainRegister> findMaintain(Map<String, Object> params);
    
    int countMaintain(Map<String, Object> params);
	
	int createMainFaultAsso(FalutMaintainAsso falutMaintainAsso);
	
	DeviceMaintainRegister selectMaintainAndFaultByKey(String maintenanceId);
	
	int deleteMainFaultAsso(FalutMaintainAsso falutMaintainAsso);

	List<DeviceMaintainRegister> selectWithOpenFault(@Param("deviceId")String deviceId);
}