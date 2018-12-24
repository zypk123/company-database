package cy.its.trafficSituation.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.trafficSituation.domain.model.TrafficAlarmEvent;
import cy.its.trafficSituation.domain.repository.ITrafficAlarmEventRepository;
import cy.its.trafficSituation.domain.service.ITrafficAlarmEventService;

@Service
public class TrafficAlarmEventService implements ITrafficAlarmEventService {
	@Autowired
	ITrafficAlarmEventRepository trafficAlarmEventRepository;
	public List<TrafficAlarmEvent> selectAll(Map<String, Object> map) {
		return trafficAlarmEventRepository.selectAll(map);
	}

	public int update(TrafficAlarmEvent trafficAlarmEvent) {
		return trafficAlarmEventRepository.update(trafficAlarmEvent);
	}

	/*
	  * <p>Title: selectCountByType</p>
	  * <p>Description: </p>
	  * @param map
	  * @return
	  * @see cy.its.trafficSituation.domain.service.ITrafficAlarmEventService#selectCountByType(java.util.Map)
	  */
	
	
	@Override
	public long selectCountByType(Map map) {
		return trafficAlarmEventRepository.selectCountByType(map);
	}

}
