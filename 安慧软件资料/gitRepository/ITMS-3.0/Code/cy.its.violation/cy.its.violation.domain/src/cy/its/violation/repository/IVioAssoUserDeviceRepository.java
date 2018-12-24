package cy.its.violation.repository;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.violation.domain.criteria.AssoUserDeviceCriteria;
import cy.its.violation.domain.model.config.VioAssoUserDevice;

public interface IVioAssoUserDeviceRepository extends IRepository<VioAssoUserDevice> {

	List<VioAssoUserDevice> findVioAssoUserDevice(AssoUserDeviceCriteria crieria);

	int deleteByUserID(String userID);

}
