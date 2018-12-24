/**
 * @Title: DeviceCertification.java
 * @Package cy.its.device.domain.service.impl
 * @Description: TODO(设备检定证书管理   证书单个、批量增加、修改)
 * @author wangk@cychina.cn
 * @date 2016年3月29日 上午11:19:50
 * @version V1.0
 * 
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2016 
 */
package cy.its.device.domain.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.device.domain.criteria.CertificationCriteria;
import cy.its.device.domain.model.Certification;
import cy.its.device.domain.model.CertificationRecord;
import cy.its.device.domain.repository.IDeviceCertificationRecordRepository;
import cy.its.device.domain.repository.IDeviceCertificationRepository;
import cy.its.device.domain.service.IDeviceCertificationService;

/**
  * @ClassName: DeviceCertification
  * @Description: 对检定证书的单个、批量增加、修改；对检定证书查询。
  * @author wangk@cychina.cn
  * @date 2016年3月29日 上午11:19:50
  *
  */
@Service
public class DeviceCertificationService implements IDeviceCertificationService {

	@Autowired
	IDeviceCertificationRecordRepository certificationRecordRepository;
	
	@Autowired
	IDeviceCertificationRepository certificationRepository;

	
	@Override
	public String addCertifition(Certification certification) throws Exception{
		return certificationRepository.save(certification);
	}

	
	@Override
	public int updateCertifition(Certification certification) throws Exception{
		return certificationRepository.update(certification);
	}

	
	@Override
	public Certification queryCertifition(String certificationId) throws Exception {
		return certificationRepository.aggregateOfId(certificationId);
	}

	
	@Override
	public List<Certification> queryCertifitionOrder(String equipmentNbr, String order) throws Exception{
		List<Certification> certificationList = new ArrayList<Certification>();
		if(order != null && "1".equals(order.trim())){
			certificationList = certificationRepository.queryCertificationByDeviceID_DESC(equipmentNbr);
		}else{
			certificationList = certificationRepository.queryCertificationByDeviceID_ASC(equipmentNbr);
		}
		return certificationList;
	}

	
	@Override
	public int deleteCertifition(String certificationId)  throws Exception{
		return certificationRepository.delete(certificationId);
	}

	
	@Override
	public List<Map<String, String>> getEquipmentCertificationList(CertificationCriteria certificationCriteria) throws Exception {
		
		return certificationRepository.getEquipmentCertificationList(certificationCriteria);
	}

	
	@Override
	public List<Map<String,String>> getNoEquipmentCertificationList(CertificationCriteria certificationCriteria)  throws Exception{
		  return certificationRepository.getNoEquipmentCertificationList(certificationCriteria);  
	}

	@Override
	public List<Map<String, String>> getCertificationRecordList(CertificationCriteria certificationCriteria)  throws Exception{
		return certificationRecordRepository.getCertificationRecordList(certificationCriteria);
	}
	
	@Override
	public CertificationRecord getCertificationByNbrImportDate(String importDate, String certificationNbr) {
		return certificationRecordRepository.getCertificationByNbrImportDate(importDate, certificationNbr);
	}
	
	@Override
	public int addCertifitionRecord(CertificationRecord certificationRecord) throws Exception {
		return certificationRecordRepository.addCertifitionRecord(certificationRecord);
	}
	
	@Override
	public int updateCertifitionRecord(CertificationRecord certificationRecord) throws Exception {
		return certificationRecordRepository.updateCertifitionRecord(certificationRecord);
	}

	@Override
	public int updateCertificationByRecord(Map<String, Object> paramsMap) {
		return certificationRecordRepository.updateCertificationByRecord(paramsMap);
	}

	@Override
	public int insertCertificationByRecord(Map<String, Object> paramsMap) {
		return certificationRecordRepository.insertCertificationByRecord(paramsMap);
	}


	
	@Override
	public Map<String, Object> getImportResult(String importDate, String importAccept) {
		return certificationRecordRepository.selectImportResult(importDate, importAccept);
	}


	
	@Override
	public List<String> getImportDateList() {
		return certificationRecordRepository.selectImportDateList();
	}


	@Override
	public List<Map<String, String>> getCertificationRecordListByIds(String[] recordIdArray) {
		return certificationRecordRepository.getCertificationRecordListByIds(recordIdArray);
	}

	@Override
	public List<String> getCertificationNbrListByImportDate(String importDate) {
		return certificationRecordRepository.getCertificationNbrListByImportDate(importDate);
	}

	@Override
	public String getVerificationEquipmentTypeByNbr(String equipmentNbr) {
		return certificationRecordRepository.getVerificationEquipmentTypeByNbr(equipmentNbr);
	}

	@Override
	public List<String> getAllVerificationEquipmentNbr() {
		return certificationRecordRepository.getAllVerificationEquipmentNbr();
	}

	@Override
	public int getCertificationCountByNbrId(String certificationNbr,String certificationId) {
		return certificationRepository.getCertificationCountByNbrId(certificationNbr,certificationId);
	}

	@Override
	public int updateCertificationRecordBatchByNbrImportDate(List<CertificationRecord> certificationRecordList) {
		return certificationRecordRepository.updateCertificationRecordBatchByNbrImportDate(certificationRecordList);
	}

	@Override
	public int insertCertificationRecordBatch(List<CertificationRecord> certificationRecordList) {
		return certificationRecordRepository.insertCertificationRecordBatch(certificationRecordList);
	}
	
}
