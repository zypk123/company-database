/**
 * @Title: DevMaintainService.java
 * @Package cy.its.device.domain.service.impl
 * @Description: 设备故障、维护领域服务类
 * @author chenzhiying chenzy@cychina.cn
 * @date 2015年12月1日 上午8:37:25
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.device.domain.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.device.domain.criteria.DeviceFaultCriteria;
import cy.its.device.domain.criteria.DeviceMaintainCriteria;
import cy.its.device.domain.criteria.SystemCriteria;
import cy.its.device.domain.model.DeviceFault;
import cy.its.device.domain.model.DeviceFaultList;
import cy.its.device.domain.model.DeviceMaintainRegister;
import cy.its.device.domain.model.FalutMaintainAsso;
import cy.its.device.domain.model.Fault;
import cy.its.device.domain.repository.maintain.IDevMaintainRepository;
import cy.its.device.domain.repository.maintain.IDeviceFaultRepository;
import cy.its.device.domain.service.IDevMaintainService;

/**
  * @ClassName: DevMaintainService
  * @Description: 设备故障、维护领域服务类
  * @author chenzhiying chenzy@cychina.cn
  * @date 2015年12月1日 上午8:37:25
  *
  */
@Service
public class DevMaintainService implements IDevMaintainService {

	@Autowired
	IDeviceFaultRepository faultRepository;
	@Autowired
	IDevMaintainRepository devMaintainRepository;
	/*
	  * <p>Title: createDeviceFault</p>
	  * <p>Description: </p>
	  * @param fault
	  * @throws RuntimeException
	  * @see cy.its.device.domain.service.IDevMaintainService#createDeviceFault(cy.its.device.domain.model.DeviceFault)
	  */
	
	
	@Override
	public String createDeviceFault(DeviceFault fault) {
		return faultRepository.save(fault);
	}

	/*
	  * <p>Title: findFaults</p>
	  * <p>Description: </p>
	  * @param faultCriteria
	  * @return
	  * @see cy.its.device.domain.service.IDevMaintainService#findFaults(cy.its.device.domain.criteria.DeviceFaultCriteria)
	  */
	
	
	@Override
	public List<DeviceFault> findFaults(DeviceFaultCriteria faultCriteria) {
		return faultRepository.findDeviceFaults(faultCriteria);
	}
	
	/*
	  * <p>Title: findFaultsList</p>
	  * <p>Description: </p>
	  * @param faultCriteria
	  * @return
	  * @see cy.its.device.domain.service.IDevMaintainService#findFaultsList(cy.its.device.domain.criteria.DeviceFaultCriteria)
	  */
	
	
	@Override
	public List<DeviceFaultList> findFaultsList(DeviceFaultCriteria faultCriteria) {
		return faultRepository.findDeviceFaultsList(faultCriteria);
	}

	/*
	  * <p>Title: validateFault</p>
	  * <p>Description: </p>
	  * @param faultId
	  * @return
	  * @throws RuntimeException
	  * @see cy.its.device.domain.service.IDevMaintainService#validateFault(java.lang.String)
	  */
	
	
	@Override
	public Boolean validateFault(String faultId,String validType) {
		DeviceFault deviceFault=new DeviceFault();
		deviceFault.setFaultId(faultId);
		deviceFault.setIsValidity(validType);
		faultRepository.update(deviceFault);
		return true;
	}

	public int updateFault(DeviceFault deviceFault)
	{
		return faultRepository.update(deviceFault);
	}
	/*
	  * <p>Title: deleteNoValidFaults</p>
	  * <p>Description: </p>
	  * @param faultIdLst
	  * @return
	  * @throws RuntimeException
	  * @see cy.its.device.domain.service.IDevMaintainService#deleteNoValidFaults(java.util.List)
	  */
	
	
	@Override
	public int deleteNoValidFaults(List<String> faultIdLst) {
		return faultRepository.deleteNoValidFaults(faultIdLst);
	}

	/*
	  * <p>Title: selectDeviceFaultById</p>
	  * <p>Description: </p>
	  * @param faultId
	  * @return
	  * @see cy.its.device.domain.service.IDevMaintainService#selectDeviceFaultById(java.lang.String)
	  */
	
	
	@Override
	public DeviceFault selectDeviceFaultById(String faultId) {
		return faultRepository.selectDeviceFaultById(faultId);
	}

	/*
	  * <p>Title: deleteDeviceFaultById</p>
	  * <p>Description: </p>
	  * @param faultId
	  * @return
	  * @see cy.its.device.domain.service.IDevMaintainService#deleteDeviceFaultById(java.lang.String)
	  */
	
	
	@Override
	public int deleteDeviceFaultById(String faultId) {
		return faultRepository.delete(faultId);
	}

	/*
	  * <p>Title: updateFaultProcessStatus</p>
	  * <p>Description: </p>
	  * @param faultId
	  * @param processStatus
	  * @param processTime
	  * @return
	  * @see cy.its.device.domain.service.IDevMaintainService#updateFaultProcessStatus(java.lang.String, java.lang.String, java.util.Date)
	  */
	
	
	@Override
	public int updateFaultProcessStatus(String faultId, String processStatus, Date processTime) {
		DeviceFault deviceFault=new DeviceFault();
		deviceFault.setFaultId(faultId);
		deviceFault.setProcessState(processStatus);
		deviceFault.setProcessTime(processTime);
		return faultRepository.update(deviceFault);
	}

	/*
	  * <p>Title: createMaintainRegister</p>
	  * <p>Description: </p>
	  * @param deviceMaintainRegister
	  * @return
	  * @see cy.its.device.domain.service.IDevMaintainService#createMaintainRegister(cy.its.device.domain.model.DeviceMaintainRegister)
	  */
	
	
	@Override
	public String createMaintainRegister(DeviceMaintainRegister deviceMaintainRegister) {
		return devMaintainRepository.save(deviceMaintainRegister);
	}

	/*
	  * <p>Title: createMainFaultAsso</p>
	  * <p>Description: </p>
	  * @param falutMaintainAsso
	  * @return
	  * @see cy.its.device.domain.service.IDevMaintainService#createMainFaultAsso(cy.its.device.domain.model.FalutMaintainAsso)
	  */
	
	
	@Override
	public int createMainFaultAsso(FalutMaintainAsso falutMaintainAsso) {
		return devMaintainRepository.createMainFaultAsso(falutMaintainAsso);
	}

	/*
	  * <p>Title: selectMaintainByKey</p>
	  * <p>Description: </p>
	  * @param maintenanceId
	  * @return
	  * @see cy.its.device.domain.service.IDevMaintainService#selectMaintainByKey(java.lang.String)
	  */
	
	
	@Override
	public DeviceMaintainRegister selectMaintainByKey(String maintenanceId) {
		return devMaintainRepository.selectMaintainByKey(maintenanceId);
	}

	/*
	  * <p>Title: selectMaintainAndFaultByKey</p>
	  * <p>Description: </p>
	  * @param maintenanceId
	  * @return
	  * @see cy.its.device.domain.service.IDevMaintainService#selectMaintainAndFaultByKey(java.lang.String)
	  */
	
	
	@Override
	public DeviceMaintainRegister selectMaintainAndFaultByKey(String maintenanceId) {
		return devMaintainRepository.selectMaintainAndFaultByKey(maintenanceId);
	}

	/*
	  * <p>Title: findMaintain</p>
	  * <p>Description: </p>
	  * @param deviceMaintainCriteria
	  * @return
	  * @see cy.its.device.domain.service.IDevMaintainService#findMaintain(cy.its.device.domain.criteria.DeviceMaintainCriteria)
	  */
	
	
	@Override
	public List<DeviceMaintainRegister> findMaintain(DeviceMaintainCriteria deviceMaintainCriteria) {
		return devMaintainRepository.findMaintain(deviceMaintainCriteria);
	}

	/*
	  * <p>Title: dispatchMaintain</p>
	  * <p>Description: </p>
	  * @param maintenanceId
	  * @param companyId
	  * @param contactor
	  * @param phone
	  * @param finishTime
	  * @return
	  * @see cy.its.device.domain.service.IDevMaintainService#dispatchMaintain(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date)
	  */
	
	
	@Override
	public int dispatchMaintain(String maintenanceId, String companyId, String contactor, String phone,
			String finishTime,String dispatchPerson) {
		DeviceMaintainRegister deviceMaintainRegister=new DeviceMaintainRegister();
		deviceMaintainRegister.setMaintenanceId(maintenanceId);
		deviceMaintainRegister.setMaintenanceCompany(companyId);
		deviceMaintainRegister.setContactPerson(contactor);
		deviceMaintainRegister.setPhoneNbr(phone);
		deviceMaintainRegister.setFinishTime(finishTime);
		deviceMaintainRegister.setAssignBy(dispatchPerson);
		deviceMaintainRegister.setAssignTime(new Date());
		deviceMaintainRegister.setMaintenanceStatus("1");//同时置为已分配
		return devMaintainRepository.update(deviceMaintainRegister);
	}

	/*
	  * <p>Title: callBackMaintain</p>
	  * <p>Description: </p>
	  * @param maintenanceId
	  * @param mainResult
	  * @param solution
	  * @param solutionTime
	  * @param photoUrl
	  * @return
	  * @see cy.its.device.domain.service.IDevMaintainService#callBackMaintain(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	  */
	
	
	@Override
	public int callBackMaintain(String maintenanceId, String mainResult, String solution, Date solutionTime,
			String photoUrl,String remark) {
		DeviceMaintainRegister deviceMaintainRegister=new DeviceMaintainRegister();
		deviceMaintainRegister.setMaintenanceId(maintenanceId);
		deviceMaintainRegister.setMaintendanceResult(mainResult);
		deviceMaintainRegister.setSolution(solution);
		deviceMaintainRegister.setSolutionTime(solutionTime);
		deviceMaintainRegister.setMaintenancePhoto(photoUrl);
		deviceMaintainRegister.setRemark(remark);
		return devMaintainRepository.update(deviceMaintainRegister);
	}

	/*
	  * <p>Title: deleteMaintain</p>
	  * <p>Description: </p>
	  * @param maintenanceId
	  * @return
	  * @see cy.its.device.domain.service.IDevMaintainService#deleteMaintain(java.lang.String)
	  */
	
	
	@Override
	public int deleteMaintain(String maintenanceId) {
		return devMaintainRepository.delete(maintenanceId);
	}

	/*
	  * <p>Title: deleteMainFaultAsso</p>
	  * <p>Description: </p>
	  * @param maintenanceId
	  * @return
	  * @see cy.its.device.domain.service.IDevMaintainService#deleteMainFaultAsso(java.lang.String)
	  */
	
	
	@Override
	public int deleteMainFaultAsso(String maintenanceId) {
		FalutMaintainAsso falutMaintainAsso=new FalutMaintainAsso();
		falutMaintainAsso.setMaintainId(maintenanceId);
		return devMaintainRepository.deleteMainFaultAsso(falutMaintainAsso);
	}

	@Override
	public int deleteMainAndFaultAsso(FalutMaintainAsso falutMaintainAsso) {
		return devMaintainRepository.deleteMainFaultAsso(falutMaintainAsso);
	}
	
	/*
	  * <p>Title: updateMaintain</p>
	  * <p>Description: </p>
	  * @param deviceMaintainRegister
	  * @return
	  * @see cy.its.device.domain.service.IDevMaintainService#updateMaintain(cy.its.device.domain.model.DeviceMaintainRegister)
	  */
	
	
	@Override
	public int updateMaintain(DeviceMaintainRegister deviceMaintainRegister) {
		return devMaintainRepository.update(deviceMaintainRegister);
	}
	@Override
	public List<DeviceFault> findDevFaultsMataince(String deviceId, List<String> faultTypeLst)
	{
	 return	faultRepository.findDevFaultsMataince(deviceId, faultTypeLst);
	}

	@Override
	public List<DeviceFault> findDevLatestFaultsMataince(SystemCriteria criteria)  {
		return faultRepository.findDevLatestFaultsMataince(criteria);
	}
	@Override
	public List<DeviceMaintainRegister> maintainsWithOpenFaultOfDevice(String deviceId) {
		return devMaintainRepository.maintainsWithOpenFaultOfDevice(deviceId);
	}

}
