package cy.its.device.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.StringUtil;
import cy.its.device.domain.model.Certification;
import cy.its.device.domain.model.Photo;
import cy.its.device.domain.model.SiteSys;
import cy.its.device.domain.model.SysComponent;
import cy.its.device.domain.repository.ISystemAttachRepository;
import cy.its.device.mybatis.client.CertificationMapper;
import cy.its.device.mybatis.client.PhotoMapper;
import cy.its.device.mybatis.client.SysComponentMapper;

@Service
public class SystemAttachRepository implements ISystemAttachRepository {

	@Autowired
	PhotoMapper photoMapper;

	@Autowired
	CertificationMapper certificationMapper;

	@Autowired
	SysComponentMapper sysComponentMapper;

	public void savePhotes(List<Photo> photoes) {
		for (Photo photo : photoes) {
			photo.setPhotoId(StringUtil.generateUUID());
			photoMapper.insertSelective(photo);
		}
	}

	public List<Photo> photesOfDevice(String deviceId) {
		return photoMapper.selectPhotoes(deviceId);
	}

	public void createCertification(Certification certification) {
		certification.setCertificationId(StringUtil.generateUUID());
		certificationMapper.insertSelective(certification);
	}

	public List<Certification> certificationsOfSystem(String deviceSysNbr) {
		return certificationMapper.selectCertifications(deviceSysNbr);
	}

	public Certification certificationOfId(String certificationId) {
		return certificationMapper.selectByPrimaryKey(certificationId);
	}

	public void createSysComponent(SysComponent sysComponent) {
		sysComponent.setSysComponentId(StringUtil.generateUUID());
		sysComponentMapper.insertSelective(sysComponent);
	}

	public List<SysComponent> componentsOfSystem(String deviceId) {
		return sysComponentMapper.selectComponents(deviceId);
	}

	public SysComponent componentOfId(String sysComponentId) {
		return sysComponentMapper.selectByPrimaryKey(sysComponentId);
	}
	
	public SysComponent componentOfDeviceNbr(String deviceNbr) {
		return sysComponentMapper.selectByDeviceNbr(deviceNbr);
	}

	/*
	 * <p>Title: deletePhotoes</p> <p>Description: </p>
	 * 
	 * @param photoIdlst
	 * 
	 * @see
	 * cy.its.device.domain.repository.ISystemAttachRepository#deletePhotoes(
	 * java.util.List)
	 */

	@Override
	public void deletePhotoes(List<String> photoIdlst) {
		for (String photoId : photoIdlst) {
			photoMapper.deleteByPrimaryKey(photoId);
		}
	}

	/*
	 * <p>Title: removeCertification</p> <p>Description: </p>
	 * 
	 * @param certificationId
	 * 
	 * @see cy.its.device.domain.repository.ISystemAttachRepository#
	 * removeCertification(java.lang.String)
	 */

	@Override
	public void removeCertification(String certificationId) {
		certificationMapper.deleteByPrimaryKey(certificationId);
	}

	/*
	 * <p>Title: updateCertification</p> <p>Description: </p>
	 * 
	 * @param certification
	 * 
	 * @see cy.its.device.domain.repository.ISystemAttachRepository#
	 * updateCertification(cy.its.device.domain.model.Certification)
	 */

	@Override
	public void updateCertification(Certification certification) {
		certificationMapper.updateByPrimaryKeySelective(certification);
	}

//	@Override
//	public Certification findLatesCertByDevId(String deviceId) {
//		List<Certification> certifications = certificationMapper.findLatesCertByDevId(deviceId);
//        if(certifications!=null&&certifications.size()>0)
//        {
//        	return certifications.get(0);
//        }
//        return null;
//	}
	
	@Override
	public Certification findLatesCertByDevSysNbr(String deviceSysNbr) {
		List<Certification> certifications = certificationMapper.findLatesCertByDevSysNbr(deviceSysNbr);
        if(certifications!=null&&certifications.size()>0)
        {
        	return certifications.get(0);
        }
        return null;
	}

	/*
	 * <p>Title: removeComponents</p> <p>Description: </p>
	 * 
	 * @param sysComponentId
	 * 
	 * @see
	 * cy.its.device.domain.repository.ISystemAttachRepository#removeComponents(
	 * java.lang.String)
	 */

	@Override
	public void removeComponents(String sysComponentId) {
		sysComponentMapper.deleteByPrimaryKey(sysComponentId);
	}

	/*
	 * <p>Title: updateComponents</p> <p>Description: </p>
	 * 
	 * @param sysComponent
	 * 
	 * @see
	 * cy.its.device.domain.repository.ISystemAttachRepository#updateComponents(
	 * cy.its.device.domain.model.SysComponent)
	 */

	@Override
	public void updateComponents(SysComponent sysComponent) {
		sysComponentMapper.updateByPrimaryKeySelective(sysComponent);
	}

	
//	public void updateLaneId(SiteSys siteSys) {
//		sysComponentMapper.updateLaneId(siteSys);
//		
//	}

	@Override
	public SysComponent componentOfDeviceKey(String deviceKey) {
		return sysComponentMapper.componentOfDeviceKey(deviceKey);
	}

}
