package cy.its.service.common.dataAccess;

interface IDiscardedDbError {
	Boolean isDiscardedDataError(Throwable e);
	
	boolean isDiscardedDataErrorExcludeUniqueConstraint(Throwable e);
}