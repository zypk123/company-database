package cy.its.device.domain.repository;

import java.util.List;

import cy.its.device.domain.model.Certification;
import cy.its.device.domain.model.Photo;
import cy.its.device.domain.model.SiteSys;
import cy.its.device.domain.model.SysComponent;

public interface ISystemAttachRepository {

	void savePhotes(List<Photo> photoes);

	List<Photo> photesOfDevice(String deviceId);
	
	void deletePhotoes(List<String> photoIdlst);

	void createCertification(Certification certification);

	List<Certification> certificationsOfSystem(String deviceSysNbr);

	Certification certificationOfId(String certificationId);
	
	/**
	 * 删除鉴定证书
	 * @param deviceId 鉴定证书ID
	 */
	void removeCertification(String  certificationId);
	
	/**
	 * 更新鉴定证书
	 * @param certification 鉴定证书
	 */
	void updateCertification(Certification certification);

	void createSysComponent(SysComponent sysComponent);
	
//	Certification findLatesCertByDevId(String deviceId);
	
	Certification findLatesCertByDevSysNbr(String deviceSysNbr);
	
	List<SysComponent> componentsOfSystem(String deviceId);

	SysComponent componentOfId(String sysComponentId);
	SysComponent componentOfDeviceNbr(String deviceNbr);
	
	/**
	 * 删除部件
	 * @param deviceId 部件ID
	 */
	void removeComponents(String  sysComponentId);
	
	/**
	 * 更新部件
	 * @param certification 部件
	 */
	void updateComponents(SysComponent sysComponent);
	
//	void updateLaneId(SiteSys siteSys);
	SysComponent componentOfDeviceKey(String deviceKey);
}
