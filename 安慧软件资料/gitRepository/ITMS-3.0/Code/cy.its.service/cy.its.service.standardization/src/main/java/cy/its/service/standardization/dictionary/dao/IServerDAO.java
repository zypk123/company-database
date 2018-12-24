package cy.its.service.standardization.dictionary.dao;

import java.util.List;

import cy.its.service.common.dataAccess.DbAccess;
import cy.its.service.standardization.dictionary.model.ServerInfo;

public interface IServerDAO {
	List<ServerInfo> selectServers(DbAccess dbAccess) throws Exception;
}
