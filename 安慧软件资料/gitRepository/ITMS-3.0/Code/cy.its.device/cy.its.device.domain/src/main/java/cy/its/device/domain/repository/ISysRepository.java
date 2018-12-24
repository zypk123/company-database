package cy.its.device.domain.repository;

import java.util.Date;
import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.device.domain.criteria.LedCriteria;
import cy.its.device.domain.criteria.MeteorologicCriteria;
import cy.its.device.domain.criteria.SystemCriteria;
import cy.its.device.domain.criteria.VTollgateCriteria;
import cy.its.device.domain.model.DeviceGenalSituation;
import cy.its.device.domain.model.DeviceSysCapability;
import cy.its.device.domain.model.DeviceSysUseStatus;
import cy.its.device.domain.model.Equipment;
import cy.its.device.domain.model.Meteorologic;
import cy.its.device.domain.model.MeteorologicSys;
import cy.its.device.domain.model.SiteSys;
import cy.its.device.domain.model.Sys;
import cy.its.device.domain.model.SysParam;
import cy.its.device.domain.model.VTollgate;
import cy.its.device.domain.model.Video;
import cy.its.device.domain.model.led.Led;
import cy.its.device.domain.model.led.LedSys;
import cy.its.platform.common.model.DeviceCertiStatusModel;

public interface ISysRepository extends IRepository<Sys>{

	List<Sys> findSystems(SystemCriteria criteria);	
	
	List<Sys> findSys(SystemCriteria criteria); 
	
	String saveSysAndParam(Sys sys, SysParam sysParam) ;
	
	int updateSectionIdSys(SiteSys siteSys);
	
	void updateSysParam(SysParam sysParam);
	
	void createSys(Sys sys);
	
	void createSysParam(SysParam sysParam);
	
	@SuppressWarnings("rawtypes")
	SysParam sysParamOfId(String deviceId, Class c);
	
	Sys selectByNBR(String deviceSysNbr);

	int countSystems(SystemCriteria criteria);
	
	int countSys(SystemCriteria criteria);
	
	void changeSysUseStauts(String devId,String useStatus, String updateReason,String userName) throws RuntimeException ;

	void deviceSysChanged();
	
	Equipment getEquipmentOfId(String id);	
	
	Equipment getEquipmentOfNbr(String equipmentNbr);
			
	String saveEquipment(Equipment obj);

	int deleteEquipment(String id);

	int updateEquipment(Equipment obj) ;
	
	void changeEquipmentUseStauts(String id, String useStatus, String updateReason, String userName);
	
//	List<DeviceGenalSituation> getDevGeneralSituation(String orgPrevilegeCode);
	
	void changeCertiDevice();
//	Sys sysOfDeviceKey(String deviceKey);
	Video sysVideoOfCameraSn(String cameraSn);

	int countVTollgate(VTollgateCriteria criteria);

	List<VTollgate> selectVTollgate(VTollgateCriteria criteria);

	List<DeviceSysUseStatus> useStatusesOfDevice(String deviceId, Date updateTimeFrom, Date updateTimeTo);

	DeviceSysCapability deviceSysCapabilityOfId(String deviceId);

	long countMeteorologics(MeteorologicCriteria criteria);

	List<Meteorologic> selectMeteorologics(MeteorologicCriteria criteria);

	List<MeteorologicSys> selectMeteorSys(MeteorologicCriteria criteria);
	
	void insertUseStatus(String devId, String originalStatus, String useStatus, String updateReason, String userName, Date dt);
	
	List<Led> findLeds(LedCriteria criteria);

	List<LedSys> findLedSyses(LedCriteria criteria);
}
