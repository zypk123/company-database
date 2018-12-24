package cy.its.device.domain.criteria;

import java.util.Date;

import cy.its.com.domain.Criteria;

public class DeviceDataPathCriteria extends Criteria{
	//设备ID
	public String deviceId;
	
	//相机ID
	public String sysComponentId;
	
	//过车开始时间(查询条件)
	public Date passTimeFrom;
	
	//过车结束时间(查询条件)
	public Date passTimeTo;
	
	//开始总耗时(查询条件)
	private String totalTimeFrom;
	
	//结束总耗时(查询条件)
	private String totalTimeTo;				
	
}
