package cy.its.violation.domain.service;

import java.util.List;

import cy.its.violation.domain.criteria.AssoUserDeviceCriteria;
import cy.its.violation.domain.model.config.VioAssoUserDevice;

public interface IVioAssoUserDeviceService {

	int removeVioAssoUserDevice(String id);

	int removeVioAssoUserDeviceByUserID(String userID);

	String saveVioAssoUserDevice(VioAssoUserDevice code);

	int updateVioAssoUserDevice(VioAssoUserDevice code);

	List<VioAssoUserDevice> findVioAssoUserDevice(AssoUserDeviceCriteria crieria);

	VioAssoUserDevice findVioAssoUserDeviceByPrimaryKey(String id) throws Exception;
}
