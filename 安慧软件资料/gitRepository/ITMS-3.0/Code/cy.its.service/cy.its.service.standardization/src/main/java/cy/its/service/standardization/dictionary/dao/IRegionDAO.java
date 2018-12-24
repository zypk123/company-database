package cy.its.service.standardization.dictionary.dao;

import java.util.List;

import cy.its.service.common.dataAccess.DbAccess;
import cy.its.service.standardization.dictionary.model.DeviceRegion;

public interface IRegionDAO {
	List<DeviceRegion> selectRegions(DbAccess da) throws Exception;
}