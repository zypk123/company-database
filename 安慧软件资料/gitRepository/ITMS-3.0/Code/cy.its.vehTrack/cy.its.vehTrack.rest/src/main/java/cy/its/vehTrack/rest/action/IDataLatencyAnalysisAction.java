package cy.its.vehTrack.rest.action;

import java.util.Map;


import cy.its.vehTrack.rest.dto.DataLatencyAnalysisInputBean;

public interface IDataLatencyAnalysisAction {
	public Map<String,Object> findDataLatencyList(DataLatencyAnalysisInputBean inputBean) throws Exception;
	
	public Map<String, Object> findDataLatencyList2(DataLatencyAnalysisInputBean inputBean) throws Exception;

}
