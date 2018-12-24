package cy.its.syscfg.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.syscfg.domain.model.traffic.EnforceStation;
import cy.its.syscfg.domain.repository.traffic.IEnforceStationRepository;
import cy.its.syscfg.domain.service.IEnforceStationService;

@Service
public class EnforceStationService implements IEnforceStationService {
	@Autowired
	IEnforceStationRepository enforceStationRepository;
	public EnforceStation enforceStationOfId(String id) throws Exception {
		return enforceStationRepository.aggregateOfId(id);
	}

	public String save(EnforceStation enforceStation) {
		return enforceStationRepository.save(enforceStation);
	}

	public int delete(String id) {
		return enforceStationRepository.delete(id);
	}

	public int update(EnforceStation enforceStation) {
		return enforceStationRepository.update(enforceStation);
	}

	public List<EnforceStation> selectAll(Map map) {
		return enforceStationRepository.selectAll(map);
	}

}
