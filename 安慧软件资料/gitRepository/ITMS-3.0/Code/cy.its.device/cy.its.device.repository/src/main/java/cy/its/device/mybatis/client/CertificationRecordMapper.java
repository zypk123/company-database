package cy.its.device.mybatis.client;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cy.its.device.domain.model.CertificationRecord;

public interface CertificationRecordMapper {

	int deleteByPrimaryKey(String recordId);

	int insert(CertificationRecord record);

	int insertSelective(CertificationRecord record);

	CertificationRecord selectByPrimaryKey(String recordId);

	int updateByPrimaryKeySelective(CertificationRecord record);

	int updateByPrimaryKey(CertificationRecord record);


	
	/**
	 * 查询入库批次信息
	 * @Title: selectCertificationRecordList
	 * @return List<Map<String, String>>
	 */
	List<Map<String, String>> selectCertificationRecordList(Map<String, Object> parseParamsMap);
    
    /**
     * 查询需年检设备的设备编码、设备类型    传空则查询所有
     * @param equipmentNbr  设备编码  传空则查询所有
     * @return List<CertificationRecord>
     */
    List<Map<String, String>> selectVerificationEquipmentAll(@Param("equipmentNbr") String equipmentNbr);
    
    /**
     * 根据导入批次,证书编号查询导入记录
     *
     * @Title: selectByNbrImportDate
     * @return CertificationRecord
     */
    CertificationRecord selectCertificationByNbrImportDate(@Param("importDate") String importDate, @Param("certificationNbr") String certificationNbr );

	/**
	 * 根据导入批次 证书编号更新
	 * 
	 * @param certificationRecord
	 * @return int
	 */
    int updateByByNbrImportDate(CertificationRecord certificationRecord);
    
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
    
    
    /**
     * 根据导入到检定证书信息记录表中的数据更新检定证书信息表
     *
     * @param paramsMap   包含操作所需参数 （ 目前包含	导入批次、导入流水、操作时间、操作人ID）
     * @return int
     */
    int updateCertificationByRecord(Map<String, Object> paramsMap);
    
    /**
     * 根据导入到检定证书信息记录表中的数据添加检定证书信息表
     *
     * @param paramsMap   包含操作所需参数 （ 目前包含	导入批次、导入流水、操作时间、操作人ID）
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
    List<Map<String, String>> selectCertificationRecordListByIds(String[] recordIdArray);
    
    
    /**
     * 根据入库批次查询当前批次已经入库的证书编号。
     *
     * @param importDate 入库批次
     * @return List<String> 证书编号列表
     */
    List<String> selectCertificationNbrListByImportDate(String importDate);
    
    
    
    /**
     * 查询需年检的设备编码对应的设备类型
     * @param equipmentNbr  设备编码  
     * @return String
     */
 	public String selectVerificationEquipmentTypeByNbr(@Param("equipmentNbr") String equipmentNbr);
 	
 	/**
 	 * 查询所有需年检的设备编码
 	 *
 	 * @return List<String>
 	 */
 	public List<String> selectAllVerificationEquipmentNbr();
    
    
    
}