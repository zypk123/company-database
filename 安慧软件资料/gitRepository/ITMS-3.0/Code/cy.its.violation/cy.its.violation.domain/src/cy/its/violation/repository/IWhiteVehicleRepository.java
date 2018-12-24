package cy.its.violation.repository;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.violation.domain.criteria.WhiteVehicleCriteria;
import cy.its.violation.domain.model.config.WhiteVehicle;

public interface IWhiteVehicleRepository extends IRepository<WhiteVehicle> {

	List<WhiteVehicle> findWhiteVehicle(WhiteVehicleCriteria crieria);

	int countWhiteVehicle(WhiteVehicleCriteria crieria);
}
