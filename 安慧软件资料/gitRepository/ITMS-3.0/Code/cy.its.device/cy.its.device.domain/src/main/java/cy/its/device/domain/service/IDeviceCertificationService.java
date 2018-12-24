
/**
 * @Title: IDeviceCertificationService.java
 * @Package cy.its.device.domain.service
 * @Description: TODO(这里要填写用途)
 * @author dell01 dell01@cychina.cn
 * @date 2016年3月29日 上午11:15:40
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2016 
 */
package cy.its.device.domain.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cy.its.device.domain.criteria.CertificationCriteria;
import cy.its.device.domain.model.Certification;
import cy.its.device.domain.model.CertificationRecord;

/**
  * @ClassName: IDeviceCertificationService
  * @Description: 对检定证书的单个、批量增加、修改；对检定证书查询。
  * @author wangk@cychina.cn
  * @date 2016年3月29日 上午11:15:40
  *
  */
public interface IDeviceCertificationService {

	
	/**
	 * 添加检定证书信息
	 * @param certification 
	 * @return String    
	 */
	public String addCertifition(Certification certification) throws Exception;
	
	/**
	 * 修改检定证书信息
	 * @param certification
	 * @return int    
	 */
	public int updateCertifition(Certification certification) throws Exception;
	
	/**
	 * 查询检定信息
	 * @param certificationId
	 * @return Certification   
	 * @throws Exception 
	 */
	public Certification queryCertifition(String certificationId) throws Exception;
	
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
	* 根据导入批次，证书编号查询证书信息
	*
	* @Title: getCertificationByNbrImportDate
	* @param importDate
	* @param certificationNbr
	* @return CertificationRecord
	*/
	public   CertificationRecord getCertificationByNbrImportDate(String importDate,String certificationNbr);
	
	/**
	 * 查询设备所有已入库的证书
	 * @Title: queryCertifition
	 * @param equipmentNbr 设备编码
	 * @param order 排序方式 1：降序 ，其他值升序
	 * @return List<Certification>
	 */
	public List<Certification> queryCertifitionOrder(String equipmentNbr,String order) throws Exception;

	/**
	 * 删除检定信息
	 * @param certificationId
	 * @return int    
	 */
	public int deleteCertifition(String certificationId) throws Exception;
	
	/**
	 * 查询已登记的设备年检信息
	 * @param certificationCriteria
	 * @return List<Map<String, String>>
	 */
	public List<Map<String, String>> getEquipmentCertificationList(CertificationCriteria certificationCriteria) throws Exception;
  
  
	/**
	 * 查询未登记的设备年检信息
	 * @param certificationCriteria
	 * @return List<Map<String,String>>
	 */
	public List<Map<String,String>> getNoEquipmentCertificationList(CertificationCriteria certificationCriteria) throws Exception;

   
   /**
    * 查询入库批次信息
    * @param certificationCriteria
    * @return List<Map<String, String>>
    */
	public List<Map<String, String>> getCertificationRecordList(CertificationCriteria certificationCriteria) throws Exception;
   
	
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
	  * 根据导入到检定证书信息记录表中的数据更新检定证书信息表
	  *
	  * @param paramsMap   包含操作所需参数 （ 目前包含	导入批次、导入流水、操作时间、操作人ID）
	  * @return int
	  */
    int updateCertificationByRecord(Map<String,Object> paramsMap);
    
    /**
     * 根据导入到检定证书信息记录表中的数据添加检定证书信息表
     * 
     * @param paramsMap   包含操作所需参数 （ 目前包含	导入批次、导入流水、操作时间、操作人ID）
     * @return int
     */
    int insertCertificationByRecord(Map<String,Object> paramsMap);
   
 
    /**
     * 获取导入结果
     *
     * @param importDate
     * @param importAccept
     * @return List<Map<String,Object>>
     */
    Map<String, Object> getImportResult(@Param("importDate") String importDate,@Param("importAccept") String importAccept);
    
    /**
     * 获取所有导入批次
     *
     * @return
     * @return List<String>
     */
    List<String> getImportDateList();
    
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
     * 根据证书编号、ID查询对应证书数量，用于添加修改证书信息时判断
     *
     * @param certificationNbr 证书编号
     * @param certificationId 证书ID
     * @return Certification
     */
    int getCertificationCountByNbrId(@Param("certificationNbr")String certificationNbr,@Param("certificationId")String certificationId);
    
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
