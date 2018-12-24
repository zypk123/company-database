package cy.its.device.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.device.domain.model.Certification;
import cy.its.device.domain.model.Photo;
import cy.its.device.domain.model.SiteSys;
import cy.its.device.domain.model.SysComponent;
import cy.its.device.domain.repository.ISystemAttachRepository;
import cy.its.device.domain.service.ISystemAttachService;

@Service
public class SystemAttachService implements ISystemAttachService {

	@Autowired
	ISystemAttachRepository systemAttachRepository;
	
	public void createPhotoes(List<Photo> photoes) {
		systemAttachRepository.savePhotes(photoes);
	}

	public List<Photo> photesOfDevice(String deviceId) {
		return systemAttachRepository.photesOfDevice(deviceId);
	}

	public void createCertification(Certification certification) {
		systemAttachRepository.createCertification(certification);
	}

	public List<Certification> certificationsOfSystem(String deviceSysNbr) {
		return systemAttachRepository.certificationsOfSystem(deviceSysNbr);
	}

	public Certification certificationOfId(String certificationId) {
		return systemAttachRepository.certificationOfId(certificationId);
	}

	public void createSysComponent(SysComponent sysComponent) {
		systemAttachRepository.createSysComponent(sysComponent);
	}

	public List<SysComponent> componentsOfSystem(String deviceId) {
		return systemAttachRepository.componentsOfSystem(deviceId);
	}

	public SysComponent componentOfId(String sysComponentId) {
		return systemAttachRepository.componentOfId(sysComponentId);
	}
	
	public SysComponent componentOfDeviceNbr(String deviceNbr) {
		return systemAttachRepository.componentOfDeviceNbr(deviceNbr);
	}

	/*
	  * <p>Title: deletePhotoes</p>
	  * <p>Description: </p>
	  * @param photoIdlst
	  * @see cy.its.device.domain.service.ISystemAttachService#deletePhotoes(java.util.List)
	  */
	
	
	@Override
	public void deletePhotoes(List<String> photoIdlst) {
		systemAttachRepository.deletePhotoes(photoIdlst);		
	}

	/*
	  * <p>Title: removeCertification</p>
	  * <p>Description: </p>
	  * @param certificationId
	  * @see cy.its.device.domain.service.ISystemAttachService#removeCertification(java.lang.String)
	  */
	
	
	@Override
	public void removeCertification(String certificationId) {
		systemAttachRepository.removeCertification(certificationId);
	}

	/*
	  * <p>Title: updateCertification</p>
	  * <p>Description: </p>
	  * @param certification
	  * @see cy.its.device.domain.service.ISystemAttachService#updateCertification(cy.its.device.domain.model.Certification)
	  */
	
	
	@Override
	public void updateCertification(Certification certification) {
		systemAttachRepository.updateCertification(certification);
	}

//	public Certification findLatesCertByDevId(String deviceId)
//	{
//	  return systemAttachRepository.findLatesCertByDevId(deviceId);
//	}
	public Certification findLatesCertByDevSysNbr(String deviceSysNbr)
	{
		return systemAttachRepository.findLatesCertByDevSysNbr(deviceSysNbr);
	}
	/*
	  * <p>Title: removeComponents</p>
	  * <p>Description: </p>
	  * @param sysComponentId
	  * @see cy.its.device.domain.service.ISystemAttachService#removeComponents(java.lang.String)
	  */
	
	
	@Override
	public void removeComponents(String sysComponentId) {
		systemAttachRepository.removeComponents(sysComponentId);
	}

	/*
	  * <p>Title: updateComponents</p>
	  * <p>Description: </p>
	  * @param sysComponent
	  * @see cy.its.device.domain.service.ISystemAttachService#updateComponents(cy.its.device.domain.model.SysComponent)
	  */
	
	
	@Override
	public void updateComponents(SysComponent sysComponent) {
		systemAttachRepository.updateComponents(sysComponent);
	}

//	@Override
//	public void updateLaneId(SiteSys siteSys) {
//		systemAttachRepository.updateLaneId(siteSys);
//		
//	}

	@Override
	public SysComponent componentOfDeviceKey(String deviceKey) {
		return systemAttachRepository.componentOfDeviceKey(deviceKey);
	}
	
	@Override
	public void removeComponentsOfDevice(String deviceId) {
		List<SysComponent> lst = systemAttachRepository.componentsOfSystem(deviceId);
		
		if(lst != null) {
			for (SysComponent c : lst) {
				systemAttachRepository.removeComponents(c.getSysComponentId());
			}
		}
	}
}
