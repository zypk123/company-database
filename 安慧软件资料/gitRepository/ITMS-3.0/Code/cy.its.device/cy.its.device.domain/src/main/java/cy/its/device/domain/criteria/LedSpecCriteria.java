package cy.its.device.domain.criteria;

import cy.its.com.domain.Criteria;

public class LedSpecCriteria extends Criteria{

	/**
	 * 诱导屏规格名称
	 */
	public String specName;
	
	/**
	 * 诱导屏颜色分类
	 */
	public String colorType;
	
	/**
	 * LED设备类型
	 */
	public String ledDeviceType;
	
	/**
	 * LED功能类型
	 */
	public String ledFunctionType;
	
	/**
	 * 控制方式
	 */
	public String[] controlTypeArr;
}
