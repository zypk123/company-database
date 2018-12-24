package cy.its.trafficSituation.domain.repository;

import java.util.Date;
import java.util.List;

import cy.its.trafficSituation.domain.model.TrafficDeviceFlow;

public interface ITrafficFlowRepository {

	List<TrafficDeviceFlow> countDeviceFlow(String deviceSysNbr, Date from, Date to);
}
