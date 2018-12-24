package cy.its.service.standardization.dictionary.dao;

import java.util.List;

import cy.its.service.common.dataAccess.DbAccess;
import cy.its.service.standardization.dictionary.model.VioAction;

public interface IVioActionDAO {

	List<VioAction> selectVioAction(DbAccess dbAccess) throws Exception;

}