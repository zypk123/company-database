package cy.its.trafficSituation.rest.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cy.its.trafficSituation.rest.dto.TrafficControlDto;

public interface ITrafficControlAction {
	String add(TrafficControlDto trafficControlDto)  throws Exception;
	int delete(String id);
	int update(TrafficControlDto trafficControlDto)  throws Exception;
	Object search(TrafficControlDto trafficControlDto)throws Exception;
}
