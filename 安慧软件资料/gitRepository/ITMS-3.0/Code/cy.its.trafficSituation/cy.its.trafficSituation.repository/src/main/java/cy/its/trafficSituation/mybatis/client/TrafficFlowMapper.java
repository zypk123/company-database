package cy.its.trafficSituation.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.trafficSituation.domain.model.TrafficDeviceFlow;

public interface TrafficFlowMapper {

	List<TrafficDeviceFlow> countDeviceFlow(Map<String, Object> p);

}
