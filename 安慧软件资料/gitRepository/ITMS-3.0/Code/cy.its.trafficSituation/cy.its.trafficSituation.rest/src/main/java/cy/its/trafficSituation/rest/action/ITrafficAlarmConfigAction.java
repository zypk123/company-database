package cy.its.trafficSituation.rest.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cy.its.trafficSituation.rest.dto.TrafficAlarmConfigDto;

public interface ITrafficAlarmConfigAction {
	String add(TrafficAlarmConfigDto trafficAlarmConfigDto);
	int delete(String id);
	int deleteMultiple(String ids);
	String update(TrafficAlarmConfigDto trafficAlarmConfigDto);
	Object selectAll(HttpServletRequest  request);
}
