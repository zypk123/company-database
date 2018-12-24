package cy.its.violation.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.violation.domain.criteria.WhiteVehicleCriteria;
import cy.its.violation.domain.model.config.WhiteVehicle;
import cy.its.violation.domain.service.IWhiteVehicleService;
import cy.its.violation.repository.IWhiteVehicleRepository;

@Service
public class WhiteVehicleService implements IWhiteVehicleService {

	@Autowired
	IWhiteVehicleRepository whiteVehicleRepository;

	@Override
	public WhiteVehicle whiteVehicleOfId(String id) throws Exception {
		return whiteVehicleRepository.aggregateOfId(id);
	}

	@Override
	public String save(WhiteVehicle obj) {
		return whiteVehicleRepository.save(obj);
	}

	@Override
	public int delete(String id) {
		return whiteVehicleRepository.delete(id);
	}

	@Override
	public int update(WhiteVehicle obj) {
		return whiteVehicleRepository.update(obj);
	}

	@Override
	public List<WhiteVehicle> findWhiteVehicles(WhiteVehicleCriteria criteria) {
		return whiteVehicleRepository.findWhiteVehicle(criteria);
	}

	@Override
	public int countWhiteVehicles(WhiteVehicleCriteria criteria) {
		return whiteVehicleRepository.countWhiteVehicle(criteria);
	}
}
