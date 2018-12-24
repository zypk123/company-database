
/**
 * @Title: DeviceCertifitionRecordRepository.java
 * @Package cy.its.device.repository
 * @Description: TODO(这里要填写用途)
 * @author dell01 dell01@cychina.cn
 * @date 2016年3月29日 上午11:46:20
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
import cy.its.device.domain.model.CertificationRecord;
import cy.its.device.domain.repository.IDeviceCertificationRecordRepository;
import cy.its.device.mybatis.client.CertificationRecordMapper;

/**
  * @ClassName: DeviceCertifitionRecordRepository
  * @Description: 检定信息批量导入  对导入批次信息进行查询
  * @author wangk@cychina.cn
  * @date 2016年3月29日 上午11:46:20
  *
  */
@Service
public class DeviceCertifitionRecordRepository implements IDeviceCertificationRecordRepository {

	@Autowired
	CertificationRecordMapper certificationRecordMapper;

	@Override
	public CertificationRecord aggregateOfId(String recordId) throws Exception {
		return certificationRecordMapper.selectByPrimaryKey(recordId);
	}

	@Override
	public String save(CertificationRecord obj) {
		return null;
	}

	@Override
	public int delete(String recordId) {
		return certificationRecordMapper.deleteByPrimaryKey(recordId);
	}

	@Override
	public int update(CertificationRecord record) {
		return certificationRecordMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public List<Map<String, String>> getCertificationRecordList(CertificationCriteria certificationCriteria) {
		if(!StringUtil.isNullOrEmpty(certificationCriteria.getOrderName())){
			PageHelper.orderBy(certificationCriteria.getOrderName() + " " + certificationCriteria.getOrderType());
		}
		return certificationRecordMapper.selectCertificationRecordList(ParamUtil.parseParams(certificationCriteria));
	}

	@Override
	public CertificationRecord getCertificationByNbrImportDate(String importDate, String certificationNbr) {
		return certificationRecordMapper.selectCertificationByNbrImportDate(importDate, certificationNbr);
	}

	
	@Override
	public int addCertifitionRecord(CertificationRecord certificationRecord) throws Exception {
		return certificationRecordMapper.insert(certificationRecord);
	}

	
	@Override
	public int updateCertifitionRecord(CertificationRecord certificationRecord) throws Exception {
		return certificationRecordMapper.updateByByNbrImportDate(certificationRecord);
	}

	@Override
	public int updateCertificationByRecord(Map<String, Object> paramsMap) {
		return certificationRecordMapper.updateCertificationByRecord(paramsMap);
	}

	@Override
	public int insertCertificationByRecord(Map<String, Object> paramsMap) {
		return certificationRecordMapper.insertCertificationByRecord(paramsMap);
	}

	
	@Override
	public Map<String, Object> selectImportResult(String importDate, String importAccept) {
		return certificationRecordMapper.selectImportResult(importDate, importAccept);
	}

	
	@Override
	public List<String> selectImportDateList() {
		return certificationRecordMapper.selectImportDateList();
	}

	
	@Override
	public List<Map<String, String>> getCertificationRecordListByIds(String[] recordIdArray) {
		return certificationRecordMapper.selectCertificationRecordListByIds(recordIdArray);
	}

	
	@Override
	public List<String> getCertificationNbrListByImportDate(String importDate) {
		return certificationRecordMapper.selectCertificationNbrListByImportDate(importDate);
	}

	
	@Override
	public String getVerificationEquipmentTypeByNbr(String equipmentNbr) {
		return certificationRecordMapper.selectVerificationEquipmentTypeByNbr(equipmentNbr);
	}

	
	@Override
	public List<String> getAllVerificationEquipmentNbr() {
		return certificationRecordMapper.selectAllVerificationEquipmentNbr();
	}

	@Override
	public int updateCertificationRecordBatchByNbrImportDate(List<CertificationRecord> certificationRecordList) {
		return certificationRecordMapper.updateCertificationRecordBatchByNbrImportDate(certificationRecordList);
	}

	@Override
	public int insertCertificationRecordBatch(List<CertificationRecord> certificationRecordList) {
		return certificationRecordMapper.insertCertificationRecordBatch(certificationRecordList);
	}
	
	
	
	
	
	

}
