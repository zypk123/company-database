package cy.its.device.domain.criteria;

import cy.its.com.domain.Criteria;

/**
  * @ClassName: CertificationCriteria
  * @Description: 设备检定信息查询条件
  * @author wangk@cychina.cn
  * @date 2016年3月26日 下午3:40:13
  *
 */
public class CertificationCriteria extends Criteria{
	
	/** 检定状态 */
	public String[] expireDateSectionArray;
	/**管理单位*/
	public String orgId;
	/** 管理单位名称*/
	public String orgName;
	/** 设备编号*/
	public String equipmentNbr;
	/**设备类型*/
	public String[] equipmentType;
	/** 起始检定日期*/
	public String beginCertificatedDate;
	/**截止检定日期*/
	public String endCertificatedDate;
	/**检定批次*/
	public String importDate;
	/**导入标记*/
	public String[] importMarkArray;
	/**检定证书信息记录表ID*/
	public String[] recordIdArray;
	/**设备类型*/
	public String[] equipmentTypeArray;
	
	
	/**导入成功数量*/
	public int importSucessCount;
	/**未登记数量*/
	public int unRegisteredCount;
	/**非交警设备数量*/
	public int unPoliceCount;
	/**其他数量*/
	public int otherCount;
	/**重复数量*/
	public int repeatCount;

	

}