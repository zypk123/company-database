package cy.its.device.mybatis.client;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cy.its.device.domain.model.Certification;
import cy.its.device.domain.model.CertificationRecord;

public interface CertificationMapper {
	
    int deleteByPrimaryKey(@Param("certificationId") String certificationId);

    int insert(Certification certification);

    int insertSelective(Certification certification);

    Certification selectByPrimaryKey(String certificationId);

    int updateByPrimaryKeySelective(Certification certification);

    int updateByPrimaryKey(Certification certification);
    
    /**
     * 查询设备编号对应的所有的证书信息 <升序>
     * @param equipmentNbr 设备编号
     * @return List<Certification>
     */
    List<Certification> selectCertifications(@Param("equipmentNbr")String equipmentNbr);
    
//    List<Certification> findLatesCertByDevId(String deviceId);
    
    /**
     * 查询设备编号对应的所有的证书信息 <降序>
     * @param equipmentNbr 设备编号
     * @return List<Certification>
     */
    List<Certification> findLatesCertByDevSysNbr(String deviceSysNbr);
    
    /**
     * 根据证书编号、ID查询对应证书数量，用于添加修改证书信息时判断
     *	校验证书编号是否存在，添加时校验只传证书编号，修改时校验传证书编号与ID
     * @param certificationNbr 证书编号
     * @param certificationId 证书ID
     * @return int
     */
    int selectCertificationCountByNbrId(@Param("certificationNbr")String certificationNbr,@Param("certificationId")String certificationId);
    
	 /**
	  *	查询已登记的设备年检信息
	  * @Title: selectEquipmentList
	  * @param parseParamsMap
	  * @return List<Map<String, String>>    
	  */
    List<Map<String, String>> selectEquipmentCertificationList(Map<String, Object> params);
   
   
	/**
	 * 查询未登记的设备年检信息
	 * @Title: selectNoEquipmentCertificationList
	 * @param @param parseParamsMap
	 * @return List<Map<String,String>>
	 */
    List<Map<String,String>> selectNoEquipmentCertificationList(Map<String, Object> params);
    
    
    
    
    
}