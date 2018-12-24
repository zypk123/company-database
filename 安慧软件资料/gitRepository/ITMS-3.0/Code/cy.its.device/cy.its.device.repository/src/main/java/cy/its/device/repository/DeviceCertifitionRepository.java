
/**
 * @Title: DeviceCertifitionRepository.java
 * @Package cy.its.device.repository
 * @Description: TODO(这里要填写用途)
 * @author dell01 dell01@cychina.cn
 * @date 2016年3月29日 上午11:31:27
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2016 
 */
package cy.its.device.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.CertificationCriteria;
import cy.its.device.domain.model.Certification;
import cy.its.device.domain.model.CertificationRecord;
import cy.its.device.domain.repository.IDeviceCertificationRepository;
import cy.its.device.mybatis.client.CertificationMapper;

/**
  * @ClassName: DeviceCertifitionRepository
  * @Description: 对检定证书信息进行单个添加修改。对检定证书进行查询
  * @author wangk@cychina.cn
  * @date 2016年3月29日 上午11:31:27
  *
  */
@Service
public class DeviceCertifitionRepository implements IDeviceCertificationRepository {
	
	@Autowired
	CertificationMapper certificationMapper;
	
	/**
	 * 查询检定证书信息
	 * @param certificationId 证书表ID
	 * @return Certification   
	 */
	@Override
	public Certification aggregateOfId(String certificationId) throws Exception {
		return certificationMapper.selectByPrimaryKey(certificationId);
	}

	/**
	 * 添加检定证书信息
	 * @param certification  
	 * @return String   证书表ID
	 */
	@Override
	public String save(Certification certification) {
		certification.setCertificationId(StringUtil.generateUUID());
		int count = certificationMapper.insertSelective(certification);
		
		return (count == 1 ? certification.getCertificationId() : null);
	}

	/**
	 * 删除检定证书信息
	 * @param certificationId  证书表ID
	 * @return int    
	 */
	@Override
	public int delete(String certificationId) {
		return certificationMapper.deleteByPrimaryKey(certificationId);
	}

	/**
	 * 修改检定证书信息
	 * @param certification    
	 * @return int   
	 */
	@Override
	public int update(Certification certification) {
		return certificationMapper.updateByPrimaryKeySelective(certification);
	}

	@Override
	public List<Map<String, String>> getEquipmentCertificationList(CertificationCriteria certificationCriteria) {
		if(!StringUtil.isNullOrEmpty(certificationCriteria.getOrderName())){
			PageHelper.orderBy(certificationCriteria.getOrderName() + " " + certificationCriteria.getOrderType());
		}
		return certificationMapper.selectEquipmentCertificationList(ParamUtil.parseParams(certificationCriteria));
	}

	
	@Override
	public List<Map<String,String>> getNoEquipmentCertificationList(CertificationCriteria certificationCriteria) {
		if(!StringUtil.isNullOrEmpty(certificationCriteria.getOrderName())){
			PageHelper.orderBy(certificationCriteria.getOrderName() + " " + certificationCriteria.getOrderType());
		}
		return certificationMapper.selectNoEquipmentCertificationList(ParamUtil.parseParams(certificationCriteria));

	}

	
	@Override
	public List<Certification> queryCertificationByDeviceID_ASC(String equipmentNbr) {
		return certificationMapper.selectCertifications(equipmentNbr);
	}

	
	@Override
	public List<Certification> queryCertificationByDeviceID_DESC(String equipmentNbr) {
		return certificationMapper.findLatesCertByDevSysNbr(equipmentNbr);
	}

	
	@Override
	public int getCertificationCountByNbrId(String certificationNbr,String certificationId) {
		return certificationMapper.selectCertificationCountByNbrId(certificationNbr,certificationId);
	}

}
