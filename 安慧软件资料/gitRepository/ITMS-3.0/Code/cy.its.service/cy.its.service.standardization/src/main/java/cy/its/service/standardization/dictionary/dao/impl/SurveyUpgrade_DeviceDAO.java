package cy.its.service.standardization.dictionary.dao.impl;

import java.util.List;

import cy.its.service.common.dataAccess.DbAccess;
import cy.its.service.common.ioc.Export;
import cy.its.service.standardization.dictionary.dao.ISurveyUpgrade_DeviceDAO;
import cy.its.service.standardization.dictionary.model.SurveyUpgrade_DeviceInfo;

@Export
public class SurveyUpgrade_DeviceDAO implements ISurveyUpgrade_DeviceDAO {
	
	@Override
	public List<SurveyUpgrade_DeviceInfo> selectDeviceInfo(DbAccess dbAccess) throws Exception {
		return dbAccess.executeDataset(SurveyUpgrade_DeviceInfo.class, SQL);
	}
	
	static String SQL = 
             "SELECT D.DTYPE              dtype,                               "+
             "       D.DEVICE_KEY         deviceKey,                           "+
             "       D.DEVICE_ID          deviceId,                            "+
             "       D.SYS_COMPONENT_ID   sysComponentId,                      "+
             "       D.DEVICE_NBR         deviceNbr,                           "+
             "       D.SYS_NBR            sysNbr,                              "+
//             "       D.COLLECTION_TYPE    collectionType,                      "+
//             "       D.SECTION_ID_LIST    sectionIdList,                       "+
//             "       D.DIRECTION_TYPE     directionType,                       "+
             "       D.ORG_ID             orgId,                               "+
             "       D.SITE_ID            siteId,                              "+
             "       D.ORG_CODE           orgCode,                             "+
             "       D.ORG_NAME           orgName,                             "+
             "       D.ORG_PRIVILEGE_CODE orgPrivilegeCode,                    "+
             "       D.DEVICE_TYPE        deviceType,                          "+
             "       R.SITE_CODE          siteCode,                            "+
             "       R.DISTRICT           district,                            "+
             "       R.SITE_NAME          siteName,                            "+
             "       R.ROAD_TYPE          roadType,                            "+
             "       R.ROAD_SEG_CODE      roadSegCode,                         "+
             "       R.CROSS_CODE         crossCode,                           "+
             "       R.ROAD_CODE          roadCode                             "+
             "  FROM (SELECT '1' DTYPE,                                        "+
             "               S.DEVICE_SYS_NBR DEVICE_KEY,                      "+
             "               S.DEVICE_ID,                                      "+
             "               '' SYS_COMPONENT_ID,                              "+
             "               S.DEVICE_SYS_NBR DEVICE_NBR,                      "+
             "               S.DEVICE_SYS_NBR SYS_NBR,                         "+
//             "               S.SECTION_ID_LIST,                                "+
//             "               S.COLLECTION_TYPE,                                "+
//             "               '' DIRECTION_TYPE,                                "+
             "               S.ORG_ID,                                         "+
             "               S.SITE_ID,                                        "+
             "               O.ORG_CODE,                                       "+
             "               O.ORG_NAME ORG_NAME,                              "+
             "               O.ORG_PRIVILEGE_CODE,                             "+
             "               S.DEVICE_TYPE                                     "+
             "          FROM T_DEVICE_SYS S, T_SYS_ORG O                       "+
             "         WHERE S.ORG_ID = O.ORG_ID                               "+
             "           AND S.USE_STATUS_FLAG = '1'                           "+
             "           AND S.ARCHITECTURE = '1'                              "+
             "        UNION                                                    "+
             "        SELECT '2' DTYPE,                                        "+
             "               C.DEVICE_KEY,                                     "+
             "               C.DEVICE_ID,                                      "+
             "               C.SYS_COMPONENT_ID,                               "+
             "               C.DEVICE_NBR,                                     "+
             "               S.DEVICE_SYS_NBR SYS_NBR,                         "+
//             "               '' SECTION_ID_LIST,                               "+
//             "               S.COLLECTION_TYPE,                                "+
//             "               C.DIRECTION_TYPE DIRECTION_TYPE,                  "+
             "               S.ORG_ID,                                         "+
             "               S.SITE_ID,                                        "+
             "               O.ORG_CODE,                                       "+
             "               O.ORG_NAME ORG_NAME,                              "+
             "               O.ORG_PRIVILEGE_CODE,                             "+
             "               S.DEVICE_TYPE                                     "+
             "          FROM T_DEVICE_SYS_COMPONENT C,                         "+
             "               T_DEVICE_SYS           S,                         "+
             "               T_SYS_ORG              O                          "+
             "         WHERE C.DEVICE_ID = S.DEVICE_ID                         "+
             "           AND C.DEVICE_KEY IS NOT NULL                          "+
             "           AND S.USE_STATUS_FLAG = '1'                           "+
             "           AND S.ARCHITECTURE = '2'                              "+             
             "           AND S.ORG_ID = O.ORG_ID) D,                           "+
             "                                                                 "+
             "       (SELECT ST.SITE_ID SITE_ID,                               "+
             "               ST.SITE_CODE SITE_CODE,                           "+
             "               ST.SITE_NAME SITE_NAME,                           "+
             "               ST.DISTRICT_CODE DISTRICT,                        "+
             "               R.ROAD_TYPE ROAD_TYPE,                            "+
             "               '' ROAD_SEG_CODE,                                 "+
             "               '' CROSS_CODE,                                    "+
             "               R.ROAD_CODE ROAD_CODE                             "+
             "          FROM T_SYS_SITE ST, T_SYS_ROAD R                       "+
             "         WHERE ST.ROAD_ID = R.ROAD_ID                            "+
             "        UNION                                                    "+
             "        SELECT ST.SITE_ID SITE_ID,                               "+
             "               ST.SITE_CODE SITE_CODE,                           "+
             "               ST.SITE_NAME SITE_NAME,                           "+
             "               ST.DISTRICT_CODE DISTRICT,                        "+
             "               R.ROAD_TYPE ROAD_TYPE,                            "+
             "               RS.ROAD_SECTION_CODE ROAD_SEG_CODE,               "+
             "               '' CROSS_CODE,                                    "+
             "               R.ROAD_CODE ROAD_CODE                             "+
             "          FROM T_SYS_SITE ST, T_SYS_ROAD_SECTION RS, T_SYS_ROAD R"+
             "         WHERE ST.ROAD_SECTION_ID = RS.ROAD_SECTION_ID           "+
             "           AND RS.ROAD_ID = R.ROAD_ID                            "+
             "        UNION                                                    "+
             "        SELECT ST.SITE_ID SITE_ID,                               "+
             "               ST.SITE_CODE SITE_CODE,                           "+
             "               ST.SITE_NAME SITE_NAME,                           "+
             "               ST.DISTRICT_CODE DISTRICT,                        "+
             "               R.ROAD_TYPE,                                      "+
             "               '' ROAD_SEG_CODE,                                 "+
             "               RC.CROSS_CODE CROSS_CODE,                         "+
             "               R.ROAD_CODE                                       "+
             "          FROM T_SYS_SITE ST, T_SYS_CROSS RC, T_SYS_ROAD R       "+
             "         WHERE ST.CROSS_ID = RC.CROSS_ID                         "+
             "           AND RC.ROAD_ID = R.ROAD_ID) R                         "+
             " WHERE D.SITE_ID = R.SITE_ID                                     ";

}
