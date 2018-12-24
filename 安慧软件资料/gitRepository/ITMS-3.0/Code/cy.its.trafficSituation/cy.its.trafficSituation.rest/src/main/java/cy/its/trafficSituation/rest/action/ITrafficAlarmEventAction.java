package cy.its.trafficSituation.rest.action;

import java.text.ParseException;

import cy.its.trafficSituation.rest.dto.TrafficAlarmEventDto;

public interface ITrafficAlarmEventAction {
	int update(TrafficAlarmEventDto trafficAlarmEventDto) throws ParseException;
	Object search(TrafficAlarmEventDto trafficAlarmEventDto) throws  Exception;
}
