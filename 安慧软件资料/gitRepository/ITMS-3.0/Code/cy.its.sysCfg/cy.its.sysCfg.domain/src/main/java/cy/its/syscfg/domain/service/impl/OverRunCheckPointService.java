package cy.its.syscfg.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.syscfg.domain.model.traffic.OverRunCheckPoint;
import cy.its.syscfg.domain.repository.traffic.IOverRunCheckPointRepository;
import cy.its.syscfg.domain.service.IOverRunCheckPointService;

@Service
public class OverRunCheckPointService implements IOverRunCheckPointService {
	
	@Autowired
	IOverRunCheckPointRepository overRunCheckPointRepository;
	public OverRunCheckPoint overRunCheckPointOfId(String id) throws Exception {
		return overRunCheckPointRepository.aggregateOfId(id);
	}

	public String save(OverRunCheckPoint overRunCheckPoint) {
		return overRunCheckPointRepository.save(overRunCheckPoint);
	}

	public int delete(String id) {
		return overRunCheckPointRepository.delete(id);
	}

	public int update(OverRunCheckPoint overRunCheckPoint) {
		return overRunCheckPointRepository.update(overRunCheckPoint);
	}

	public List<OverRunCheckPoint> selectAll(Map map) {
		return overRunCheckPointRepository.selectAll(map);
	}

}
