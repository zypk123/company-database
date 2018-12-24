package cy.its.trafficSituation.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.trafficSituation.domain.repository.IRoadsensorRepository;
import cy.its.trafficSituation.domain.repository.ITrafficAlarmEventRepository;
import cy.its.trafficSituation.domain.repository.ITrafficVisibilityRepository;
import cy.its.trafficSituation.domain.repository.ITrafficWeatherRepository;
import cy.its.trafficSituation.domain.service.ITrafficAllEventService;

@Service
public class TrafficAllEventService implements ITrafficAllEventService {
	
	
	@Autowired
	ITrafficAlarmEventRepository trafficAlarmEventRepository;
	
	@Autowired
	ITrafficVisibilityRepository trafficVisibilityRepository;
	
	@Autowired
	ITrafficWeatherRepository trafficWeatherRepository;
	
	
	@Autowired
	IRoadsensorRepository roadsensorRepository;
	
	
	@Override
	public List queryWeatherEvent(Map map) {
		return trafficWeatherRepository.selectWeathers(map);
	}

	public List queryVisibilityEvent(Map map) {
		return trafficVisibilityRepository.selectVisibilitys(map);
	}

	@Override
	public List queryRoadSensorEvent(Map map) {
		return roadsensorRepository.selectRoadsensors(map);
	}

	@Override
	public List queryTrafficEvent(Map map) {
		return trafficAlarmEventRepository.selectAll(map);
	}

}
