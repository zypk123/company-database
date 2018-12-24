package cy.its.service.standardization.dictionary.dao.impl;

import java.util.List;

import cy.its.service.common.dataAccess.DbAccess;
import cy.its.service.common.ioc.Export;
import cy.its.service.standardization.dictionary.dao.IVioActionDAO;
import cy.its.service.standardization.dictionary.model.VioAction;

@Export
public class VioActionDAO implements IVioActionDAO {

	@Override
	public List<VioAction> selectVioAction(DbAccess dbAccess) throws Exception {
		return dbAccess.executeDataset(VioAction.class, SQL);
	}
	
	final static String SQL = "SELECT V.VIO_ACTION_CODE vioActionCode, V.VIO_TYPE vioType, V.VIO_SUMMARY vioSummary FROM T_VIO_ACTION V";
}
