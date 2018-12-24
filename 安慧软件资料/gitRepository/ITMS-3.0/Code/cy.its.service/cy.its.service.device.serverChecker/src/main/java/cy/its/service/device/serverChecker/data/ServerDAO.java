package cy.its.service.device.serverChecker.data;

import java.util.List;

import cy.its.service.common.dataAccess.DbExecuter;
import cy.its.service.device.serverChecker.model.ServerInfo;

public class ServerDAO {
	public static List<ServerInfo> getServerInfo() throws Exception {
		return DbExecuter.executeDataset(ServerInfo.class, SQL);
	}

	static final String SQL = 
			"SELECT T.SERVER_ID serverId,                                 "
			+ "       T.SERVER_IP serverIp,                                 "
			+ "       S.SURVEY_SYSTEM_ID surveySystemId,                    "
			+ "       S.SURVEY_SYSTEM_NAME surveySystemName,                "
			+ "       O.ORG_PRIVILEGE_CODE orgPrivilegeCode                 "
			+ "  FROM T_DEVICE_SERVER T, T_DEVICE_SURVEY_SYS S, T_SYS_ORG O "
			+ " WHERE T.SURVEY_SYSTEM_ID = S.SURVEY_SYSTEM_ID               "
			+ "   AND S.ORG_ID = O.ORG_ID                                   ";
}
