package cy.its.service.standardization.dictionary;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cy.its.service.common.ConstValue;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataAccess.DbAccess;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.standardization.dictionary.dao.IServerDAO;
import cy.its.service.standardization.dictionary.model.ServerInfo;

@Export
public class ServerCache extends Cache {

	@Import
	IServerDAO serverDAO;

	private Map<String, ServerInfo> cache = new HashMap<String, ServerInfo>();

	@Override
	void load(DbAccess dbAccess) throws Exception {
		List<ServerInfo> list = serverDAO.selectServers(dbAccess);
		synchronized (cache) {
			cache.clear();
			for (ServerInfo s : list) {
				if (!cache.containsKey(s.serverIp)) {
					cache.put(s.serverIp, s);
				}
			}
		}
	}

	@Override
	List<String> getRelatedRouteKeys() {
		return Arrays.asList(ConstValue.ROUTE_KEY_CACHECHANGE_SERVER);
	}

	public boolean isExisted(String serverIp) {
		if (StringUtil.isNullOrEmpty(serverIp)) {
			return ConstValue.BOOL_FALSE;
		}

		synchronized (cache) {
			return cache.containsKey(serverIp);
		}
	}
}
