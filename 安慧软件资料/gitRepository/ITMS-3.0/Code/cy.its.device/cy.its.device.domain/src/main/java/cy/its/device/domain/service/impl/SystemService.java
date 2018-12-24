package cy.its.device.domain.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.LedCriteria;
import cy.its.device.domain.criteria.MeteorologicCriteria;
import cy.its.device.domain.criteria.SystemCriteria;
import cy.its.device.domain.criteria.VTollgateCriteria;
import cy.its.device.domain.model.BaseStation;
import cy.its.device.domain.model.DeviceSysCapability;
import cy.its.device.domain.model.DeviceSysUseStatus;
import cy.its.device.domain.model.Equipment;
import cy.its.device.domain.model.EventDetection;
import cy.its.device.domain.model.FlowSys;
import cy.its.device.domain.model.Meteorologic;
import cy.its.device.domain.model.MeteorologicSys;
import cy.its.device.domain.model.SiteSys;
import cy.its.device.domain.model.Sys;
import cy.its.device.domain.model.SysParam;
import cy.its.device.domain.model.TollgateSys;
import cy.its.device.domain.model.VTollgate;
import cy.its.device.domain.model.Video;
import cy.its.device.domain.model.VioDevice;
import cy.its.device.domain.model.led.Led;
import cy.its.device.domain.model.led.LedSys;
import cy.its.device.domain.repository.ISysRepository;
import cy.its.device.domain.repository.led.ILedProgRepository;
import cy.its.device.domain.repository.led.ILedRemoteManageRepository;
import cy.its.device.domain.repository.led.ILedSpecRepository;
import cy.its.device.domain.service.ILedService;
import cy.its.device.domain.service.ISurveySystemService;
import cy.its.device.domain.service.ISystemService;

/**
 * 设备配置服务
 * 
 * @author STJ
 *
 */
@Service
public class SystemService implements ISystemService {

	@Autowired
	ISysRepository sysRepository;

	@Autowired
	ILedService ledService;
	
	@Autowired
	ILedSpecRepository ledSpecRepository;

	@Autowired
	ISurveySystemService surveySystemService;

	@Autowired
	ILedRemoteManageRepository ledRemoteManageRepository;

	@Autowired
	ILedProgRepository ledProgRepository;	

	public void removeSystem(String deviceId) throws Exception {				
		SysParam led = sysRepository.sysParamOfId(deviceId, Led.class);
		if (led != null) {
			Sys ledSys = sysRepository.aggregateOfId(deviceId);
			if(!ledSys.canDelete()) {
				throw new Exception("当前LED设备未处于废弃或新建状态, 无法删除");
			}
		}
		
		sysRepository.delete(deviceId);
	}

	public void updateSystem(Sys sys) throws Exception {
		SysParam sysParam = sysRepository.sysParamOfId(sys.getDeviceId(), Led.class);
		if(sysParam != null){
		    // 当前修改系统为LED系统
			Sys oldSys = sysRepository.aggregateOfId(sys.getDeviceId());			
			sys.checkLedSysCanModify(oldSys);
		}
		
		
		sysRepository.update(sys);
	}
	
	public void updateSectionIdSys(SiteSys siteSys) {
		sysRepository.updateSectionIdSys(siteSys);
		
	}

	public Sys systemOfId(String deviceId) throws Exception {
		return sysRepository.aggregateOfId(deviceId);
	}
	
	public Sys selectByNBR(String deviceSysNbr){
		return sysRepository.selectByNBR(deviceSysNbr);
	}

	public List<Sys> findSystems(SystemCriteria criteria) {
		if (criteria.getNeedTotal()) {
			criteria.setTotal(sysRepository.countSystems(criteria));
		}
		return sysRepository.findSystems(criteria);
	}
	public List<Sys> findSys(SystemCriteria criteria) {
		return sysRepository.findSys(criteria);
	}

	public String createSystem(Sys sys, SysParam sysParam) throws Exception {
		if(sysParam == null) {
			throw new Exception("系统参数不可为空");
		}
		
		if(sys.sysNbrIsRepeated(sysRepository)){
			return StringUtil.EMPTY_STR;
//			throw new Exception("已存在相同系统编号的电子监控系统");
		}
		
		sys.setDeviceId(StringUtil.generateUUID());
		if(sysParam instanceof Led) {
			((Led) sysParam).checkLedSpec(sys, ledSpecRepository);
		}
		
		sysRepository.createSys(sys);
		sysParam.attatchSys(sys.getDeviceId());		
		sysRepository.createSysParam(sysParam);		
		return sys.getDeviceId();
	}

	public void updateSystem(SysParam sysParam) throws Exception {
		if(sysParam instanceof Led) {
			Led newLed = (Led)sysParam;
			Led oldLed = (Led)sysRepository.sysParamOfId(newLed.getDeviceId(), Led.class);			
			newLed.checkLedCanModify(oldLed,
					sysRepository.aggregateOfId(newLed.getDeviceId()),
					ledProgRepository);
		}
		
		sysRepository.updateSysParam(sysParam);
	}

	public EventDetection eventDetectionOfId(String deviceId) {
		SysParam sysParam = sysRepository.sysParamOfId(deviceId, EventDetection.class);
		if (sysParam == null) {
			return null;
		}
		return (EventDetection) sysParam;
	}

	public Meteorologic meteorologicOfId(String deviceId) {
		SysParam sysParam = sysRepository.sysParamOfId(deviceId, Meteorologic.class);
		if (sysParam == null) {
			return null;
		}
		return (Meteorologic) sysParam;
	}

	public TollgateSys tollgateSysOfId(String deviceId) {
		SysParam sysParam = sysRepository.sysParamOfId(deviceId, TollgateSys.class);
		if (sysParam == null) {
			return null;
		}
		return (TollgateSys) sysParam;
	}

	public Video videoOfId(String deviceId) {
		SysParam sysParam = sysRepository.sysParamOfId(deviceId, Video.class);
		if (sysParam == null) {
			return null;
		}
		return (Video) sysParam;
	}

	public VioDevice vioDeviceOfId(String deviceId) {
		SysParam sysParam = sysRepository.sysParamOfId(deviceId, VioDevice.class);
		if (sysParam == null) {
			return null;
		}
		return (VioDevice) sysParam;
	}
	
	@Override
	public Led ledOfId(String deviceId) {
		SysParam sysParam = sysRepository.sysParamOfId(deviceId, Led.class);
		if (sysParam == null) {
			return null;
		}
		return (Led) sysParam;
	}

	@Override
	public void enableDevice(String devId, String reason, String userName) throws Exception {
		Sys sys = sysRepository.aggregateOfId(devId);
		String originalStatus = sys.getUseStatusFlag();
		sys.enable();
		
		SysParam led = sysRepository.sysParamOfId(devId, Led.class);		
		if(led != null) {
			((Led)led).enable(sys, ledSpecRepository, surveySystemService, ledRemoteManageRepository);
		}
		
		sysRepository.update(sys);
		sysRepository.insertUseStatus(devId, originalStatus, sys.getUseStatusFlag(),
				reason, userName, sys.getEnableUpdateDate());
	}	

	@Override
	public void stopDevice(String devId, String reason, String userName) throws Exception {		
		Sys sys = sysRepository.aggregateOfId(devId);
		String originalStatus = sys.getUseStatusFlag();
		sys.stop();
		
		SysParam led = sysRepository.sysParamOfId(devId, Led.class);		
		if(led != null) {
			((Led)led).stop(sys, ledProgRepository, surveySystemService, ledRemoteManageRepository);
		}
		
		sysRepository.update(sys);
		sysRepository.insertUseStatus(devId, originalStatus, sys.getUseStatusFlag(), reason, userName, sys.getEnableUpdateDate());
	}
	
	@Override
	public void brokenDevice(String devId, String reason, String userName) throws Exception {		
		Sys sys = sysRepository.aggregateOfId(devId);
		String originalStatus = sys.getUseStatusFlag();
		sys.broken();
		sysRepository.update(sys);
		sysRepository.insertUseStatus(devId, originalStatus, sys.getUseStatusFlag(),
				reason, userName, sys.getEnableUpdateDate());
	}

	/**
	 * 设备系统货部件变更通知
	 * 设备系统或部件 修改、删除和增加时,并且在变更提交到数据库后才可调用本接口
	 */
	@Override
	public void deviceSysChanged(){
		sysRepository.deviceSysChanged();;
	}

	/*
	  * <p>Title: getEquipmentOfId</p>
	  * <p>Description: </p>
	  * @param id
	  * @return
	  * @see cy.its.device.domain.service.ISystemService#getEquipmentOfId(java.lang.String)
	  */
	
	
	@Override
	public Equipment getEquipmentOfId(String id) {
		return sysRepository.getEquipmentOfId(id);
	}
	

	@Override
	public Equipment getEquipmentOfNbr(String equipmentNbr) {
		// TODO Auto-generated method stub
		return sysRepository.getEquipmentOfNbr(equipmentNbr);
	}
	
	/*
	  * <p>Title: saveEquipment</p>
	  * <p>Description: </p>
	  * @param obj
	  * @return
	  * @see cy.its.device.domain.service.ISystemService#saveEquipment(cy.its.device.domain.model.Equipment)
	  */
	
	
	@Override
	public String saveEquipment(Equipment obj) {
		return sysRepository.saveEquipment(obj);
	}

	/*
	  * <p>Title: deleteEquipment</p>
	  * <p>Description: </p>
	  * @param id
	  * @return
	  * @see cy.its.device.domain.service.ISystemService#deleteEquipment(java.lang.String)
	  */
	
	
	@Override
	public int deleteEquipment(String id) {
		return sysRepository.deleteEquipment(id);
	}

	/*
	  * <p>Title: updateEquipment</p>
	  * <p>Description: </p>
	  * @param obj
	  * @return
	  * @see cy.its.device.domain.service.ISystemService#updateEquipment(cy.its.device.domain.model.Equipment)
	  */
	
	
	@Override
	public int updateEquipment(Equipment obj) {
		return sysRepository.updateEquipment(obj);
	}

	/*
	  * <p>Title: changeEquipmentUseStauts</p>
	  * <p>Description: </p>
	  * @param id
	  * @param useStatus
	  * @param updateReason
	  * @param userName
	  * @see cy.its.device.domain.service.ISystemService#changeEquipmentUseStauts(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	  */
	
	
	@Override
	public void changeEquipmentUseStauts(String id, String useStatus, String updateReason, String userName) {
		sysRepository.changeEquipmentUseStauts(id, useStatus, updateReason, userName);
	}
	
//	public List<DeviceGenalSituation> getDevGeneralSituation(String orgPrevilegeCode)
//	{
//	  return sysRepository.getDevGeneralSituation(orgPrevilegeCode);
//	}
	
	public void changeCertiDevice()
	{
		sysRepository.changeCertiDevice();
	}

//	@Override
//	public Sys sysOfDeviceKey(String deviceKey) {
//		return sysRepository.sysOfDeviceKey(deviceKey);
//	}

	@Override
	public Video sysVideoOfCameraSn(String cameraSn) {
		// TODO Auto-generated method stub
		return sysRepository.sysVideoOfCameraSn(cameraSn);
	}

	@Override
	public List<VTollgate> findVTollgates(VTollgateCriteria criteria) {
		return sysRepository.selectVTollgate(criteria);
	}

	@Override
	public List<DeviceSysUseStatus> useStatusesOfDevice(String deviceId, Date updateTimeFrom, Date updateTimeTo) {
		return sysRepository.useStatusesOfDevice(deviceId, updateTimeFrom, updateTimeTo);
	}

	@Override
	public DeviceSysCapability deviceSysCapabilityOfId(String deviceId) {
		return sysRepository.deviceSysCapabilityOfId(deviceId);
	}

	@Override
	public FlowSys flowSysOfId(String deviceId) {
		SysParam sysParam = sysRepository.sysParamOfId(deviceId, FlowSys.class);
		if (sysParam == null) {
			return null;
		}
		return (FlowSys) sysParam;
	}

	
	
	@Override
	public List<Meteorologic> findMeteorologics(MeteorologicCriteria criteria) {
		if(criteria.getNeedTotal()){
			criteria.setTotal(sysRepository.countMeteorologics(criteria));;
		}
		return sysRepository.selectMeteorologics(criteria);
	}

	@Override
	public List<MeteorologicSys> findMeteorologicSys(MeteorologicCriteria criteria) {
		
		return sysRepository.selectMeteorSys(criteria);
	}

	@Override
	public BaseStation baseStationOfId(String deviceId) {
		SysParam sysParam = sysRepository.sysParamOfId(deviceId, BaseStation.class);
		if (sysParam == null) {
			return null;
		}
		return (BaseStation) sysParam;
	}

	@Override
	public List<LedSys> findLedSyses(LedCriteria criteria) {
		return sysRepository.findLedSyses(criteria);
	}	
}
