package cy.its.device.domain.criteria;

import java.util.Date;

import cy.its.com.domain.Criteria;

public class FaultCriteria extends Criteria
{
	// 所属机构、设备类型、所属道路、、、设备厂商、

	/**
	 * 
	 */
	public String maintenanceId;
	

    /**
     * 故障类型
     */
	public String faultType;

    /**
     * 发生时间
     */
	public Date startTimeFrom;
	

    /**
     * 发生时间
     */
	public Date startTimeTo;

    /**
     * 有效/无效
     */
	public String isValidity;
}