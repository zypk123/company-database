package cy.its.trafficSituation.domain.service;

import java.util.Date;
import java.util.List;

import cy.its.trafficSituation.domain.model.TrafficDeviceFlow;

public interface ITrafficFlowService {
	List<TrafficDeviceFlow> countDeviceFlow(String deviceSysNbr, Date from, Date to) throws Exception;
}
