package cy.its.service.common.dataAccess;

public class DiscardedDbError {
	static IDiscardedDbError dbErr = new OraDiscardedDbError();

	public static Boolean isDiscardedError(Throwable e) {
		return dbErr.isDiscardedDataError(e);
	}

	public static boolean isDiscardedDataErrorExcludeUniqueConstraint(Throwable e){
		return dbErr.isDiscardedDataErrorExcludeUniqueConstraint(e);
	}
}