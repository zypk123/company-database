package cy.its.violation.domain.service;

import java.util.List;

import cy.its.violation.domain.criteria.WhiteVehicleCriteria;
import cy.its.violation.domain.model.config.WhiteVehicle;

public interface IWhiteVehicleService {

	WhiteVehicle whiteVehicleOfId(String id) throws Exception;

	int update(WhiteVehicle obj);

	String save(WhiteVehicle obj);

	int delete(String id);

	List<WhiteVehicle> findWhiteVehicles(WhiteVehicleCriteria criteria);

	int countWhiteVehicles(WhiteVehicleCriteria criteria);
}
