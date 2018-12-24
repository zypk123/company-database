/**
 * @Title: ICertificationRepository.java
 * @Package cy.its.device.domain.repository
 * @Description: 对检定信息导入记录表的操作
 * @author wangk@cychina.cn
 * @date 2016年3月28日 下午5:48:51
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2016 
 */
package cy.its.device.domain.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cy.its.com.domain.IRepository;
import cy.its.device.domain.criteria.CertificationCriteria;
import cy.its.device.domain.model.CertificationRecord;

/**
  * @ClassName: ICertification
  * @Description: 对检定信息进行批量导入，查询导入记录
  * @author wangk@cychina.cn
  * @date 2016年3月28日 下午5:48:51
  */
public interface IDeviceCertificationRecordRepository extends IRepository<CertificationRecord> {

	
	
	/**
	 * 添加检定证书信息记录表
	 * @param certification 
	 * @return String    
	 */
	public int addCertifitionRecord(CertificationRecord certificationRecord) throws Exception;
	
	/**
	 * 修改检定证书信息记录表
	 * @param certification
	 * @return int    
	 */
	public int updateCertifitionRecord(CertificationRecord certificationRecord) throws Exception;
	
	/**
	 * 查询入库批次信息
	 * @Title: selectCertificationRecordList
	 * @return List<Map<String, String>> 
	 */
	List<Map<String, String>> getCertificationRecordList(CertificationCriteria certificationCriteria);
	
    
    /**
     * 根据导入批次，证书编号查询证书信息
     *
     * @Title: getCertificationByNbrImportDate
     * @param importDate
     * @param certificationNbr
     * @return CertificationRecord
     */
    CertificationRecord getCertificationByNbrImportDate(String importDate,String certificationNbr);
    
    
    /**
     * 根据导入到检定证书信息记录表中的数据更新检定证书信息表
     *
     * @param paramsMap   包含操作所需参数 （ 目前包含	导入批次、导入流水、操作时间、操作人ID）
     * @return
     * @return int
     */
    int updateCertificationByRecord(Map<String, Object> paramsMap);
    
    /**
     * 根据导入到检定证书信息记录表中的数据添加检定证书信息表
     * 
     * @param paramsMap   包含操作所需参数 （ 目前包含	导入批次、导入流水、操作时间、操作人ID）
     * @return
     * @return int
     */
    int insertCertificationByRecord(Map<String, Object> paramsMap);
    
    /**
     * 获取导入结果
     *
     * @param importDate
     * @param importAccept
     * @return List<Map<String,Object>>
     */
    Map<String, Object> selectImportResult(@Param("importDate") String importDate,@Param("importAccept") String importAccept);
    
    /**
     * 获取所有导入批次
     *
     * @return
     * @return List<String>
     */
    List<String> selectImportDateList();
    
    /**
	 * 查询入库批次信息
	 * @Title: selectCertificationRecordList
	 * @param: recordIdArray 检定证书信息记录表ID数组
	 * @return List<Map<String, String>> 
	 */
    List<Map<String, String>> getCertificationRecordListByIds(String[] recordIdArray);
    
    /**
     * 根据入库批次查询当前批次已经入库的证书编号。
     *
     * @param importDate 入库批次
     * @return List<String> 证书编号列表
     */
    List<String> getCertificationNbrListByImportDate(String importDate);
    
    /**
     * 查询需年检的设备编码对应的设备类型
     * @param equipmentNbr  设备编码  
     * @return String
     */
 	public String getVerificationEquipmentTypeByNbr(String equipmentNbr);
 	
 	/**
 	 * 查询所有需年检的设备编码
 	 *
 	 * @return List<String>
 	 */
 	public List<String> getAllVerificationEquipmentNbr();
 	
 	 /**
     * 根据导入批次 证书编号批量更新检定信息
     *
     * @param certificationRecordList 包含检定证书信息记录
     * @return int
     */
    int updateCertificationRecordBatchByNbrImportDate(List<CertificationRecord> certificationRecordList);
    
    /**
     * 批量插入检定证书信息记录
     *
     * @param certificationRecordList  包含检定证书信息记录
     * @return int
     */
    int insertCertificationRecordBatch(List<CertificationRecord> certificationRecordList);
    
}
