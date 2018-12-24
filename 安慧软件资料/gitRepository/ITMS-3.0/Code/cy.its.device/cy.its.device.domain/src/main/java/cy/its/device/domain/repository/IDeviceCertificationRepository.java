/**
 * @Title: ICertificationRepository.java
 * @Package cy.its.device.domain.repository
 * @Description: 对检定信息表的操作
 * @author wangk@cychina.cn
 * @date 2016年3月28日 下午5:48:51
 * @version V1.0
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2016 
 */
package cy.its.device.domain.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cy.its.com.domain.IRepository;
import cy.its.device.domain.criteria.CertificationCriteria;
import cy.its.device.domain.model.Certification;
import cy.its.device.domain.model.CertificationRecord;

/**
  * @ClassName: ICertification
  * @Description: 处理单个检定证书。对设备与检定证书进行查询
  * @author wangk@cychina.cn
  * @date 2016年3月28日 下午5:48:51
  *
  */
public interface IDeviceCertificationRepository extends IRepository<Certification> {
	
	
	
	/**
	 * 有效日期升序查询设备对应的证书
	 * @Title: queryCertificationByID_ASC
	 * @param equipmentNbr 设备编码
	 * @return List<Certification>
	 */
	List<Certification> queryCertificationByDeviceID_ASC(String equipmentNbr) ;
    
	/**
	 * 有效日期降序查询设备对应的证书
	 * @Title: queryCertificationByID_DESC
	 * @param equipmentNbr 设备编码
	 * @return List<Certification>
	 */
    List<Certification> queryCertificationByDeviceID_DESC(String equipmentNbr);
	
	
	/**
	 * 获取已登记设备的年检信息
	 * @param @param certificationCriteria
	 * @return List<Map<String, String>>    
	 */
	public List<Map<String, String>> getEquipmentCertificationList(CertificationCriteria certificationCriteria);

	
	/**
	 * 获取未登记设备的检定证书信息
	 * @param @param certificationCriteria
	 * @return List<Map<String,String>>    
	 */
	public List<Map<String,String>> getNoEquipmentCertificationList(CertificationCriteria certificationCriteria);

	
	/**
     * 根据证书编号、ID查询对应证书数量，用于添加修改证书信息时判断
     *
     * @param certificationNbr 证书编号
     * @param certificationId 证书ID
     * @return Certification
     */
    int getCertificationCountByNbrId(@Param("certificationNbr")String certificationNbr,@Param("certificationId")String certificationId);

}
