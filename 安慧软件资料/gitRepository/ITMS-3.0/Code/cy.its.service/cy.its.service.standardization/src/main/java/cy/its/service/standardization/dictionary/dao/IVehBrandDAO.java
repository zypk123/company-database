package cy.its.service.standardization.dictionary.dao;

import java.util.List;

import cy.its.service.common.dataAccess.DbAccess;
import cy.its.service.standardization.dictionary.model.VehBrand;

public interface IVehBrandDAO {
	List<VehBrand> selectVehBrand(DbAccess dbAccess) throws Exception;
}