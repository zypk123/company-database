package cy.its.device.domain.criteria;


public class LedCriteria {
	
	/**
	 * led规格
	 */
	public String ledSpecId;
	
	/**
	 * 机构ID  指定本条件表明LED设备属于此机构及其下属机构
	 */
	public String orgId;
	
	/**
	 * 设备状态
	 */
	public String[] useStatusFlagArr;
		
	
//	/**
//	 * 道路ID数组
//	 */
//	public String[] roadIdArr;
}
