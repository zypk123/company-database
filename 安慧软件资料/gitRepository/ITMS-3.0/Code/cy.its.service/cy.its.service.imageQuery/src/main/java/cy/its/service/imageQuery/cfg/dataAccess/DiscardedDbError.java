package cy.its.service.imageQuery.cfg.dataAccess;

public class DiscardedDbError {
	static IDiscardedDbError dbErr = new OraDiscardedDbError();

	public static Boolean isDiscardedError(Exception e) {
		return dbErr.isDiscardedDataError(e);
	}

}