package cy.its.service.common.dataAccess;

@FunctionalInterface
public interface Action<T> {
	void accept(T t) throws Exception;
}
