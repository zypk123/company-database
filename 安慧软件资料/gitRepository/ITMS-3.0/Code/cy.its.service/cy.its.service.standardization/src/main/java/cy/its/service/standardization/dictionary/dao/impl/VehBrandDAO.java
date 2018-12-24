package cy.its.service.standardization.dictionary.dao.impl;

import java.util.List;

import cy.its.service.common.dataAccess.DbAccess;
import cy.its.service.common.ioc.Export;
import cy.its.service.standardization.dictionary.dao.IVehBrandDAO;
import cy.its.service.standardization.dictionary.model.VehBrand;


@Export
public class VehBrandDAO implements IVehBrandDAO {

	@Override
	public List<VehBrand> selectVehBrand(DbAccess dbAccess) throws Exception {
		return dbAccess.executeDataset(VehBrand.class, SQL);
	}

	final static String SQL = 
			"SELECT T.HAIKANG_CODE brandCode,                         "+
			"       T.BRAND_CODE subBrand,                            "+
			"       SUBSTR(T.BRAND_CODE, 0, 3) || '00' brand           "+
			"  FROM T_SYS_VEH_BRAND T                                  "+
			" WHERE T.HAIKANG_CODE IS NOT NULL                         "+
			"UNION                                                     "+
			"SELECT T.DAHUA_CODE brandCode, NULL subBrand, T.BRAND_CODE brand   "+
			"  FROM T_SYS_VEH_BRAND T                                  "+
			" WHERE T.DAHUA_CODE IS NOT NULL                           ";


}
