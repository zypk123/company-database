package cy.its.service.standardization.dictionary.dao.impl;

import java.util.List;

import cy.its.service.common.dataAccess.DbAccess;
import cy.its.service.common.ioc.Export;
import cy.its.service.standardization.dictionary.dao.IRegionDAO;
import cy.its.service.standardization.dictionary.model.DeviceRegion;

@Export
public class RegionDAO implements IRegionDAO {

	public List<DeviceRegion> selectRegions(DbAccess da) throws Exception {
		return da.executeDataset(DeviceRegion.class, SQL);
	}

	final static String SQL = 
			  "SELECT R.REGIONAL_CODE      regionalCode, "
			+ "       R.REGIONAL_NAME      regionalName, "
		    + "       R.DISTRICT_CODE      districtCode, "
			+ "       R.DIRECTION_TYPE     directionType,"
		    + "       R.DIRECTION_NAME     directionName,"
			+ "       R.DISTANCE           distance,     "
		    + "       R.LIMIT_LARGE        limitLarge,   "
			+ "       R.LIMIT_SMALL        limitSmall,   "
		    + "       R.LIMIT_LOWER        limitLower,   "
			+ "       O.ORG_CODE           orgCode,      "
		    + "       O.ORG_PRIVILEGE_CODE orgPrivilege, "
			+ "       RD.ROAD_CODE         roadCode,     "
		    + "       SS.SITE_CODE         entrySiteCode,"
			+ "       SS.SITE_NAME         entrySiteName,"
		    + "       ES.SITE_CODE         exitSiteCode, "
			+ "       ES.SITE_NAME         exitSiteName  "
		    + "  FROM T_DEVICE_REGION R                  "
			+ "  LEFT JOIN T_SYS_ORG O                   "
		    + "    ON R.ORG_ID = O.ORG_ID                "
			+ "  LEFT JOIN T_SYS_ROAD RD                 "
		    + "    ON R.ROAD_ID = RD.ROAD_ID             "
			+ "  LEFT JOIN T_SYS_SITE SS                 "
		    + "    ON R.ENTRY_SITE_ID = SS.SITE_ID       "
			+ "  LEFT JOIN T_SYS_SITE ES                 "
		    + "    ON R.EXIT_SITE_ID = ES.SITE_ID        ";

}
