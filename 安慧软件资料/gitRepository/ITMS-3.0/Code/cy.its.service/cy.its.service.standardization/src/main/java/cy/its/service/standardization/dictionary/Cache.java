package cy.its.service.standardization.dictionary;

import java.util.List;

import cy.its.service.common.dataAccess.DbAccess;
import cy.its.service.common.dataAccess.DbExecuter;
import cy.its.service.common.log.LogManager;

public abstract class Cache {
	abstract void load(DbAccess dbAccess) throws Exception;
	abstract List<String> getRelatedRouteKeys();
	
	public void receiveChange() {
		try {
			DbExecuter.customNonQuerys((da) -> {
				load(da);
			});
		} catch (Throwable e) {
			LogManager.error(e);
		}
	}
}
