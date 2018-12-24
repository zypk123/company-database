package cy.its.device.mybatis.client;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cy.its.device.domain.model.SiteSys;
import cy.its.device.domain.model.SysComponent;

public interface SysComponentMapper {
	
	int deleteByPrimaryKey(@Param("sysComponentId") String sysComponentId);

	int insert(SysComponent record);

	int insertSelective(SysComponent record);

	SysComponent selectByPrimaryKey(String sysComponentId);

	int updateByPrimaryKeySelective(SysComponent record);
	
//	int updateLaneId(SiteSys siteSys);

	int updateByPrimaryKey(SysComponent record);

	List<SysComponent> selectComponents(@Param("deviceId") String deviceId);
	
	SysComponent componentOfDeviceKey(String deviceKey);
	
	SysComponent selectByDeviceNbr(String deviceNbr);
}