package cy.its.syscfg.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.syscfg.domain.model.traffic.PoliceArea;
import cy.its.syscfg.domain.repository.traffic.IPoliceAreaRepository;
import cy.its.syscfg.domain.service.IPoliceAreaService;

@Service
public class PoliceAreaService implements IPoliceAreaService {
	@Autowired
	IPoliceAreaRepository policeAreaRepository;

	public PoliceArea policeAreaOfId(String id) throws Exception {
		return policeAreaRepository.aggregateOfId(id);
	}

	public String save(PoliceArea policeArea) {
		return policeAreaRepository.save(policeArea);
	}

	public int delete(String id) {
		return policeAreaRepository.delete(id);
	}

	public int update(PoliceArea policeArea) {
		return policeAreaRepository.update(policeArea);
	}

	public List<PoliceArea> selectAll(Map map) {
		return policeAreaRepository.selectAll(map);
	}

}
