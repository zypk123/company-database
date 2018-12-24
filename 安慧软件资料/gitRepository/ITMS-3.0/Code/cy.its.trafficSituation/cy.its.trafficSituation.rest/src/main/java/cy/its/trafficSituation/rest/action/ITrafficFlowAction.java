
package cy.its.trafficSituation.rest.action;

import com.alibaba.fastjson.JSONObject;

//import cy.its.trafficSituation.domain.model.EchartData;

public interface ITrafficFlowAction {
/**
 * 
 * @description: query Vehicle flow by deviceSysNbr and date information
 * @return: json data format
 */
	
	public JSONObject queryTrafficFlow(String deviceSysNbr, String startDate, String endDate); 
}
