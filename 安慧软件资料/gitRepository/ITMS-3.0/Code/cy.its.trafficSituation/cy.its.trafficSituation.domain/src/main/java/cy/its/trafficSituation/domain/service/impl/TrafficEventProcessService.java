package cy.its.trafficSituation.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.trafficSituation.domain.model.TrafficEventProcess;
import cy.its.trafficSituation.domain.repository.ITrafficEventProcessRepository;
import cy.its.trafficSituation.domain.service.ITrafficEventProcessService;

@Service
public class TrafficEventProcessService implements ITrafficEventProcessService {
	@Autowired
	ITrafficEventProcessRepository trafficEventProcessRepository;
	public TrafficEventProcess trafficEventProcessOfId(String id) throws Exception {
		return trafficEventProcessRepository.aggregateOfId(id);
	}

	public String save(TrafficEventProcess trafficEventProcess) {
		return trafficEventProcessRepository.save(trafficEventProcess);
	}

	public int delete(String id) {
		return trafficEventProcessRepository.delete(id);
	}

	public int update(TrafficEventProcess trafficEventProcess) {
		return trafficEventProcessRepository.update(trafficEventProcess);
	}

	public TrafficEventProcess selectByEventId(String eventId) {
		return trafficEventProcessRepository.selectByEventId(eventId);
	}
}
